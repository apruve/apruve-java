package com.apruve;

import static us.monoid.web.Resty.*;

import java.io.IOException;

import us.monoid.web.JSONResource;
import us.monoid.web.Resty;

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
	private static final String APRUVE_JS_PATH = "/js/apruve.js";
	private static ApruveMerchant client = null;
	private String merchantId;

	private ApruveMerchant(String merchantId, String apiKey, Environment env) {
		super(apiKey, env);
		if (merchantId == null)
			throw new RuntimeException("merchantId cannot be null");
		this.merchantId = merchantId;
	}

	public static ApruveMerchant getInstance() {
		if (client == null)
			throw new RuntimeException(
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
			Environment env) {
		client = new ApruveMerchant(merchantId, apiKey, env);
	}

	/**
	 * Override the normal ApruveClient.init method to throw an exception.
	 * ApruveMerchant should be initialized with the merchantId.
	 * 
	 * @param apiKey
	 * @param env
	 */
	public static synchronized void init(String apiKey, Environment env) {
		throw new RuntimeException(
				"ApruveMerchant should be initialized with init(merchantId, apiKey, environment)");
	}

	protected String getMerchantId() {
		return this.merchantId;
	}

	public String getApruveJSTag() {
		String url = getApruveUrl() + APRUVE_JS_PATH;
		String tag = "<script src=\"" + url
				+ "\" type=\"text/javascript\"></script>";
		return tag;
	}

	public String post(Payment payment) {
		String paymentId = null;
		Resty resty = new Resty();
		try {
			JSONResource json = resty.json(payment.getUrl(),
					content(payment.toJson()));
			payment.setId(json.get("id").toString());
			payment.setStatus(json.get("status").toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return paymentId;
	}

	/**
	 * Intended for use in unit testing only.
	 */
	protected static void initToNull() {
		client = null;
	}
}
