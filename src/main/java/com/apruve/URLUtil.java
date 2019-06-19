package com.apruve;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

import org.apache.http.client.utils.URIBuilder;

public class URLUtil {

	public static URL buildURL(String host) {
		return buildURL("http", host, null, null);
	}
	
	public static URL buildURL(String scheme, String host, Integer port, String path) {
		try {
			URIBuilder builder = new URIBuilder();
			if (scheme != null) 
				builder.setScheme(scheme);
			else
				builder.setScheme("http");
			builder.setHost(host);
			if (port != null) builder.setPort(port);
			if (path != null) builder.setPath(path);
			return builder.build().toURL();
		} catch (MalformedURLException e) {
			System.err.println(e.getMessage());
			return null;
		} catch (URISyntaxException e) {
			System.err.println(e.getMessage());
			return null;
		}
	}
}
