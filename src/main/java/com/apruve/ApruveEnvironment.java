package com.apruve;

import java.net.URL;

import org.apache.commons.lang3.StringUtils;

/**
 * @author Robert Nelson
 * @since 0.1
 */
public class ApruveEnvironment {
	public static final ApruveEnvironment PROD = new ApruveEnvironment("https", "app.apruve.com", null);
	public static final ApruveEnvironment TEST = new ApruveEnvironment("https", "test.apruve.com", null);

	private static final String API_V4_PATH = "/api/v4";
	private static final String JS_PATH = "/js/v4/apruve.js";

	private final URL url;
	private final URL apiV4Url;
	private final URL jsUrl;

	public ApruveEnvironment() {
		this(StringUtils.defaultIfBlank(System.getenv("APRUVE_SCHEME"), "https"),
				StringUtils.defaultIfBlank(System.getenv("APRUVE_HOST"), "test.apruve.com"),
				Integer.valueOf(StringUtils.defaultIfBlank(System.getenv("APRUVE_PORT"), "443")));
	}

	public ApruveEnvironment(String scheme, String host, Integer port) {
		this.url = URLUtil.buildURL(scheme, host, port, null);
		this.apiV4Url = URLUtil.buildURL(scheme, host, port, API_V4_PATH);
		this.jsUrl = URLUtil.buildURL(scheme, host, port, JS_PATH);
	}

	public String getBaseUrl() {
		return this.url.toString();
	}

	/**
	 * @return The root URL for making API calls in the environment
	 */
	public String getApiV4Url() {
		return this.apiV4Url.toString();
	}

	/**
	 * @return The apruve.js URL for the environment
	 */
	public String getJsUrl() {
		return this.jsUrl.toString();
	}

	/**
	 * @return HTML tag that will include apruve.js in a page
	 */
	public String getJsTag() {
		return "<script src=\"" + getJsUrl() + "\" type=\"text/javascript\"></script>";
	}
}
