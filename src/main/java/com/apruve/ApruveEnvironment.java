package com.apruve;

/**
 * @author Robert Nelson
 * @since 0.1
 */
public enum ApruveEnvironment {
	PROD, TEST;

	private static final String JS_PATH = "/js/apruve.js";


	/**
	 * @return
	 */
	public String getBaseUrl() {
		if (this == PROD) {
			return "https://www.apruve.com";
		} else {
			return "https://test.apruve.com";
		}
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