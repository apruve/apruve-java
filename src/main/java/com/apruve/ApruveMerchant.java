package com.apruve;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

/**
 * A convenient class for performing a merchant integration to the Apruve
 * Payments API. This class builds on ApruveClient by adding methods to help
 * merchants. Implemented as a Singleton.
 * 
 * Initialize with: ApruveMerchant.init(merchantId, apiKey, Environment);
 * 
 * More documentation at https://www.apruve.com/doc
 * 
 * @author https://github.com/nealtovsen
 * 
 */
public class ApruveMerchant extends ApruveClient {

	private static Log LOG = LogFactory.getLog(ApruveMerchant.class);

	private static ApruveMerchant client = null;

	private String merchantId;


	private ApruveMerchant(String merchantId, String apiKey, ApruveEnvironment env) {
		super(apiKey, env);
		if (merchantId == null)
			throw new ApruveException("merchantId cannot be null");
		this.merchantId = merchantId;
	}


	public static ApruveMerchant getInstance() {
		if (client == null)
			throw new ApruveException(
					"Must first initialize with ApruveMerchant.init");
		return client;
	}

	/**
	 * Provides a single point of initialization for the ApruveClient library.
	 * Use this init method when you are integrating an e-commerce platform as a
	 * merchant.
	 * 
	 * @param merchantId
	 *            Your merchant ID for this account. Create on for testing on
	 *            https://test.apruve.com/merchants, or create one for live
	 *            transactions at https://www.apruve.com/merchants.
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
	public static synchronized void init(String merchantId, String apiKey,
			ApruveEnvironment env) {
		LOG.info("ApruveMerchant is initializing with merchantId " + merchantId);
		client = new ApruveMerchant(merchantId, apiKey, env);
		// also initialize ApruveClient to the same
		ApruveClient.init(apiKey, env);
	}


	/**
	 * Override the normal ApruveClient.init method to throw an exception.
	 * ApruveMerchant should be initialized with the merchantId.
	 * 
	 * @param apiKey
	 * @param env
	 */
	public static synchronized void init(String apiKey, ApruveEnvironment env) {
		throw new ApruveException(
				"ApruveMerchant should be initialized with init(merchantId, apiKey, environment)");
	}


	/**
	 * @return
	 */
	protected String getMerchantId() {
		return this.merchantId;
	}


	/**
	 * @param payment
	 */
	public void post(Payment payment) {
		try {
			CloseableHttpClient httpclient = HttpClients.createDefault();
			try {
				HttpPost post = new HttpPost(payment.getUrl());
				post.setHeader("Content-Type", "application/json");
				post.setHeader("Apruve-Api-Key", getApiKey());
				StringEntity body = new StringEntity("{\"amount_cents\":"
						+ payment.getAmountCents() + "}");
				post.setEntity(body);
				HttpResponse response = httpclient.execute(post);
				// TODO - Need error handling for 400 error response
				BufferedReader rd = new BufferedReader(new InputStreamReader(
						response.getEntity().getContent()));
				String line = "";
				while ((line = rd.readLine()) != null) {
					// Parse our JSON response
					LOG.debug("response from posting Payment: " + line);
					JSONObject json = JSONObject.fromObject(line);
					payment.setId((String) json.get("id"));
					payment.setStatus((String) json.get("status"));
				}

			} catch (Exception ex) {
				throw new ApruveException("Unable to post Payment", ex);
			} finally {
				httpclient.close();
			}
		} catch (IOException ioex) {
			throw new RuntimeException("Exception while closing HTTPClient",
					ioex);
		}
	}

	/**
	 * Intended for use in unit testing only.
	 */
	protected static void initToNull() {
		client = null;
	}
}
