package com.apruve;

import java.util.List;
import java.util.logging.Logger;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status.Family;

import org.glassfish.jersey.filter.LoggingFilter;

/**
 * A convenient class for integrating to the Apruve API. Implemented as a
 * Singleton.
 * 
 * Initialize with: ApruveClient.init(apiKey, Environment);
 * 
 * More documentation at https://www.apruve.com/doc
 * 
 * @author https://github.com/nealtovsen
 * 
 */
public class ApruveClient {
	private static final Logger log = Logger.getLogger(ApruveClient.class
			.getName());

	private ApruveEnvironment env;

	private String apiKey;

	protected static ApruveClient client = null;

	/**
	 * @param apiKey
	 * @param env
	 */
	protected ApruveClient(String apiKey, ApruveEnvironment env) {
		if (apiKey == null)
			throw new ApruveException("apiKey cannot be null");
		if (env == null)
			throw new ApruveException("env cannot be null");
		this.apiKey = apiKey;
		this.env = env;
	}

	/**
	 * @return
	 */
	public static ApruveClient getInstance() {
		if (client == null)
			throw new ApruveException(
					"Must first initialize with ApruveClient.init");
		return client;
	}

	/**
	 * Provides a single point of initialization for the ApruveClient library.
	 * 
	 * @param apiKey
	 *            An API Key from your user account. Create on for your test
	 *            account on https://test.apruve.com/merchants, or create one
	 *            for live transactions at https://www.apruve.com/merchants. We
	 *            recommend that you create a unique API Key for each merchant
	 *            account.
	 * @param env
	 *            com.apruve.ApruveClient.Environment.PROD or
	 *            com.apruve.ApruveClient.Environment.TEST, as appropriate.
	 */
	public static synchronized void init(String apiKey, ApruveEnvironment env) {
		client = new ApruveClient(apiKey, env);
	}

	/**
	 * @return
	 */
	public String getApiKey() {
		return this.apiKey;
	}

	/**
	 * @return
	 */
	public ApruveEnvironment getEnvironment() {
		return this.env;
	}

	/**
	 * Intended for use in unit testing only.
	 */
	public static void initToNull() {
		client = null;
	}

	protected Builder restRequest(String path) {
		// TODO Build a better logging filter that masks the API key and shows
		// the request/response body
		Client client = ClientBuilder.newClient().register(LoggingFilter.class);
		WebTarget target = client.target(getEnvironment().getBaseUrl() + path);
		Builder builder = target.request(MediaType.APPLICATION_JSON).header(
				"Apruve-Api-Key", getApiKey());

		return builder;
	}

	private <T> ApruveResponse<T> processResponse(Response response,
			Class<T> resultType) {
		T responseObject = null;
		ApruveResponse<T> result;
		if (response.getStatusInfo().getFamily() == Family.SUCCESSFUL) {
			responseObject = response.readEntity(resultType);
			result = new ApruveResponse<T>(response.getStatus(), responseObject);
		} else {
			result = new ApruveResponse<T>(response.getStatus(), null,
					response.readEntity(String.class));
		}

		return result;
	}

	/**
	 * As get(), but specialized to return lists of objects for REST index
	 * operations
	 * 
	 * @param path
	 *            The path to issue the GET against
	 * @param resultType
	 *            A {@link GenericType} instance that determines the type of
	 *            list returned
	 * @return List of objects of a type as determined by resultType
	 */
	// TODO see if there's a cleaner way to do this. The whole GenericType thing
	// is kind of a pain, and it duplicates processResponse.
	public <T> ApruveResponse<List<T>> index(String path,
			GenericType<List<T>> resultType) {
		Response response = restRequest(path).get();
		List<T> responseObject = null;
		ApruveResponse<List<T>> result;
		if (response.getStatusInfo().getFamily() == Family.SUCCESSFUL) {
			responseObject = response.readEntity(resultType);
			result = new ApruveResponse<List<T>>(response.getStatus(),
					responseObject);
		} else {
			result = new ApruveResponse<List<T>>(response.getStatus(), null,
					response.readEntity(String.class));
		}
		return result;
	}

	/**
	 * Issues a GET request against to the Apruve REST API, using the specified
	 * path.
	 * 
	 * @param path
	 *            The path to issue the GET against
	 * @param resultType
	 *            The type of the response
	 * @return A single object of type resultType
	 */
	public <T> ApruveResponse<T> get(String path, Class<T> resultType) {
		Response response = restRequest(path).get();
		return processResponse(response, resultType);
	}

	public <T, RT> ApruveResponse<RT> post(String path, T payload, Class<RT> responseType) {
		Response response = restRequest(path).post(Entity.json(payload));
		log.info(Entity.json(payload).toString());
		return processResponse(response, responseType);
	}

	public <T, RT> ApruveResponse<RT> put(String path, T payload, Class<RT> responseType) {
		Response response = restRequest(path).put(Entity.json(payload));
		log.info(Entity.json(payload).toString());
		return processResponse(response, responseType);
	}

	public <T> ApruveResponse<T> delete(String path, Class<T> responseType) {
		Response response = restRequest(path).delete();
		return processResponse(response, responseType);
	}
}
