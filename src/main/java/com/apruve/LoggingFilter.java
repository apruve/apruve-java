package com.apruve;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientRequestFilter;
import javax.ws.rs.client.ClientResponseContext;
import javax.ws.rs.client.ClientResponseFilter;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.WriterInterceptor;
import javax.ws.rs.ext.WriterInterceptorContext;

/**
 * Cribbed from the Jersey LoggingFilter class, but modified to allow filtering
 * sensitive header values.
 * 
 * @author todd
 *
 */
public class LoggingFilter implements ClientResponseFilter, ClientRequestFilter, WriterInterceptor {

	private static final String NOTIFICATION_PREFIX = "* ";
	private static final String REQUEST_PREFIX = "> ";
	private static final String RESPONSE_PREFIX = "< ";
	private static final String ENTITY_LOGGER_PROPERTY = LoggingFilter.class.getName()
			+ ".entityLogger";
	private static final Comparator<Map.Entry<String, List<String>>> COMPARATOR = new Comparator<Map.Entry<String, List<String>>>() {

		@Override
		public int compare(final Map.Entry<String, List<String>> o1, final Map.Entry<String, List<String>> o2) {
			return o1.getKey().compareToIgnoreCase(o2.getKey());
		}
	};

	private static final int DEFAULT_MAX_ENTITY_SIZE = 8 * 1024;

	private final Logger logger;
	private final AtomicLong _id = new AtomicLong(0);
	private final boolean printEntity;
	private final int maxEntitySize;
	private final HashMap<String, Object> filteredHeaders;

	/**
	 * Create a logging filter with custom logger and custom settings of entity
	 * logging.
	 *
	 * @param logger
	 *            the logger to log requests and responses.
	 * @param printEntity
	 *            if true, entity will be logged as well up to the default
	 *            maxEntitySize, which is 8KB
	 */
	public LoggingFilter(final Logger logger, final boolean printEntity, String... filterKeys) {
		this.logger = logger;
		this.printEntity = printEntity;
		this.maxEntitySize = DEFAULT_MAX_ENTITY_SIZE;
		this.filteredHeaders = new HashMap<String, Object>();
		for (String key : filterKeys) {
			this.filteredHeaders.put(key.toLowerCase(), true);
		}
	}

	/**
	 * Creates a logging filter with custom logger and entity logging turned on,
	 * but potentially limiting the size of entity to be buffered and logged.
	 *
	 * @param logger
	 *            the logger to log requests and responses.
	 * @param maxEntitySize
	 *            maximum number of entity bytes to be logged (and buffered) -
	 *            if the entity is larger, logging filter will print (and buffer
	 *            in memory) only the specified number of bytes and print
	 *            "...more..." string at the end.
	 */
	public LoggingFilter(final Logger logger, final int maxEntitySize, String... filterKeys) {
		this.logger = logger;
		this.printEntity = true;
		this.maxEntitySize = maxEntitySize;
		this.filteredHeaders = new HashMap<String, Object>();
		for (String key : filterKeys) {
			this.filteredHeaders.put(key.toLowerCase(), true);
		}
	}

	@Override
	public void filter(final ClientRequestContext context) throws IOException {
		final long id = this._id.incrementAndGet();
		final StringBuilder b = new StringBuilder();

		printRequestLine(b, "Sending client request", id, context.getMethod(), context.getUri());
		printPrefixedHeaders(b, id, REQUEST_PREFIX, context.getStringHeaders());

		if (printEntity && context.hasEntity()) {
			final OutputStream stream = new LoggingStream(b, context.getEntityStream());
			context.setEntityStream(stream);
			context.setProperty(ENTITY_LOGGER_PROPERTY, stream);
			// not calling log(b) here - it will be called by the interceptor
		} else {
			log(b);
		}
	}

	@Override
	public void filter(final ClientRequestContext requestContext, final ClientResponseContext responseContext)
			throws IOException {
		final long id = this._id.incrementAndGet();
		final StringBuilder b = new StringBuilder();

		printResponseLine(b, "Client response received", id, responseContext.getStatus());
		printPrefixedHeaders(b, id, RESPONSE_PREFIX, responseContext.getHeaders());

		if (printEntity && responseContext.hasEntity()) {
			responseContext.setEntityStream(logInboundEntity(b, responseContext.getEntityStream()));
		}

		log(b);
	}

	@Override
	public void aroundWriteTo(final WriterInterceptorContext writerInterceptorContext)
			throws IOException, WebApplicationException {
		final LoggingStream stream = (LoggingStream) writerInterceptorContext.getProperty(ENTITY_LOGGER_PROPERTY);
		writerInterceptorContext.proceed();
		if (stream != null) {
			log(stream.getStringBuilder());
		}
	}

	private class LoggingStream extends OutputStream {
		private final StringBuilder b;
		private final OutputStream inner;
		private final ByteArrayOutputStream baos = new ByteArrayOutputStream();

		LoggingStream(final StringBuilder b, final OutputStream inner) {
			this.b = b;
			this.inner = inner;
		}

		StringBuilder getStringBuilder() {
			// write entity to the builder
			final byte[] entity = baos.toByteArray();

			b.append(new String(entity, 0, Math.min(entity.length, maxEntitySize)));
			if (entity.length > maxEntitySize) {
				b.append("...more...");
			}
			b.append('\n');

			return b;
		}

		@Override
		public void write(final int i) throws IOException {
			if (baos.size() <= maxEntitySize) {
				baos.write(i);
			}
			inner.write(i);
		}
	}

	private void log(final StringBuilder b) {
		if (logger != null) {
			logger.info(b.toString());
		}
	}

	private StringBuilder prefixId(final StringBuilder b, final long id) {
		b.append(Long.toString(id)).append(" ");
		return b;
	}

	private void printRequestLine(final StringBuilder b, final String note, final long id, final String method,
			final URI uri) {
		prefixId(b, id).append(NOTIFICATION_PREFIX).append(note).append(" on thread ")
				.append(Thread.currentThread().getName()).append("\n");
		prefixId(b, id).append(REQUEST_PREFIX).append(method).append(" ").append(uri.toASCIIString()).append("\n");
	}

	private void printResponseLine(final StringBuilder b, final String note, final long id, final int status) {
		prefixId(b, id).append(NOTIFICATION_PREFIX).append(note).append(" on thread ")
				.append(Thread.currentThread().getName()).append("\n");
		prefixId(b, id).append(RESPONSE_PREFIX).append(Integer.toString(status)).append("\n");
	}

	private InputStream logInboundEntity(final StringBuilder b, InputStream stream) throws IOException {
		if (!stream.markSupported()) {
			stream = new BufferedInputStream(stream);
		}
		stream.mark(maxEntitySize + 1);
		final byte[] entity = new byte[maxEntitySize + 1];
		final int entitySize = stream.read(entity);
		b.append(new String(entity, 0, Math.min(entitySize, maxEntitySize)));
		if (entitySize > maxEntitySize) {
			b.append("...more...");
		}
		b.append('\n');
		stream.reset();
		return stream;
	}

	private void printPrefixedHeaders(final StringBuilder b, final long id, final String prefix,
			final MultivaluedMap<String, String> headers) {
		for (final Map.Entry<String, List<String>> headerEntry : getSortedHeaders(headers.entrySet())) {
			List<?> val = headerEntry.getValue();
			final String header = headerEntry.getKey();

			// filter any headers matching the configured keys
			if (filteredHeaders.containsKey(header.toLowerCase())) {
				val = Collections.singletonList("<filtered>");
			}

			if (val.size() == 1) {
				prefixId(b, id).append(prefix).append(header).append(": ").append(val.get(0)).append("\n");
			} else {
				final StringBuilder sb = new StringBuilder();
				boolean add = false;
				for (final Object s : val) {
					if (add) {
						sb.append(',');
					}
					add = true;
					sb.append(s);
				}
				prefixId(b, id).append(prefix).append(header).append(": ").append(sb.toString()).append("\n");
			}
		}
	}

	private Set<Map.Entry<String, List<String>>> getSortedHeaders(final Set<Map.Entry<String, List<String>>> headers) {
		final TreeSet<Map.Entry<String, List<String>>> sortedHeaders = new TreeSet<Map.Entry<String, List<String>>>(
				COMPARATOR);
		sortedHeaders.addAll(headers);
		return sortedHeaders;
	}

}
