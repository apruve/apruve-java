package com.apruve;

/**
 * @author Robert Nelson
 * @since 0.1
 */
public enum ApruveEnvironment {
	PROD("https://www.apruve.com"), TEST("https://test.apruve.com"), DEV("http://localhost:3000");

	private static final String API_V3_PATH = "/api/v3";
	private static final String JS_PATH = "/js/apruve.js";

	private String baseUrl;

	private ApruveEnvironment(String baseURL) {
		this.baseUrl = baseURL;
	}

	public String getBaseUrl() {
		return baseUrl;
	}

	/**
	 * @return The root URL for making API calls in the environment
	 */
	public String getApiV3Url() {
		return getBaseUrl() + API_V3_PATH;
	}

	/**
	 * @return The apruve.js URL for the environment
	 */
	public String getJsUrl() {
		return getBaseUrl() + JS_PATH;
	}

	/**
	 * @return HTML tag that will include apruve.js in a page
	 */
	public String getJsTag() {
		return "<script src=\"" + getJsUrl()
				+ "\" type=\"text/javascript\"></script>";
	}
}