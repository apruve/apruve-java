package com.apruve;

/**
 * A convenient class for integrating to the Apruve API. Implemented as a
 * Singleton.
 * 
 * Initialize with: ApruveMerchant.init(apiKey, Environment);
 * 
 * More documentation at https://www.apruve.com/doc
 * 
 * @author https://github.com/nealtovsen
 * 
 */
public class ApruveClient {
	private Environment env;
	private String apiKey;
	private static ApruveClient client = null;

	protected ApruveClient(String apiKey, Environment env) {
		if (apiKey == null)
			throw new RuntimeException("apiKey cannot be null");
		this.apiKey = apiKey;
		this.env = env;
	}

	public static ApruveClient getInstance() {
		if (client == null)
			throw new RuntimeException(
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
	public static synchronized void init(String apiKey, Environment env) {
		client = new ApruveClient(apiKey, env);
	}

	protected String getApiKey() {
		return this.apiKey;
	}

	protected Environment getEnvironment() {
		return this.env;
	}

	/**
	 * Intended for use in unit testing only.
	 */
	protected static void initToNull() {
		client = null;
	}

	protected String getApruveUrl() {
		String url;
		switch (env) {
		case PROD:
			url = "https://www.apruve.com";
			break;
		default:
			url = "https://test.apruve.com";
			break;
		}

		return url;
	}

	public enum Environment {
		PROD, TEST
	}
}
