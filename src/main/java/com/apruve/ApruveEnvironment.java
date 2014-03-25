package com.apruve;

/**
 * @author Robert Nelson
 * @since 0.1
 */
public enum ApruveEnvironment {
	PROD("https://www.apruve.com/api/v3"), 
	TEST("https://test.apruve.com/api/v3"),
	DEV("http://localhost:3000/api/v3");

	private static final String JS_PATH = "/js/apruve.js";
	
	private String baseUrl;

	private ApruveEnvironment(String baseURL) {
		this.baseUrl = baseURL;
	}

	/**
	 * @return
	 */
	public String getBaseUrl() {
		return baseUrl;
	}


	/**
	 * @return
	 */
	public String getJsUrl() {
		return getBaseUrl() + JS_PATH;
	}


	/**
	 * @return
	 */
	public String getJsTag() {
		return "<script src=\"" + getJsUrl() + "\" type=\"text/javascript\"></script>";
	}
}