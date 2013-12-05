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
	protected String getApiKey() {
		return this.apiKey;
	}


	/**
	 * @return
	 */
	protected ApruveEnvironment getEnvironment() {
		return this.env;
	}


	/**
	 * Intended for use in unit testing only.
	 */
	protected static void initToNull() {
		client = null;
	}
}
