package com.apruve;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Test;


public class ApruveMerchantTest {
	private static final String A_MERCHANT_ID = "AMerchantId";
	private static final String AN_API_KEY = "AnApiKey";
	private static final String SCRIPT_TAG_TEST = "<script src=\"https://test.apruve.com/js/apruve.js\" type=\"text/javascript\"></script>";
	private static final String SCRIPT_TAG_PROD = "<script src=\"https://www.apruve.com/js/apruve.js\" type=\"text/javascript\"></script>";
	
	@After
	public void teardown() {
		ApruveMerchant.initToNull();
	}

	@Test
	public void testInit() {
		ApruveMerchant.init(A_MERCHANT_ID, AN_API_KEY, ApruveMerchant.Environment.TEST);
		assertNotNull(ApruveMerchant.getInstance());
	}
	
	@Test(expected=RuntimeException.class)
	public void testInitApruveClient() {
		ApruveMerchant.init(AN_API_KEY, ApruveMerchant.Environment.TEST);
	}

	@Test
	public void testGetInstance() {
		ApruveMerchant.init(A_MERCHANT_ID, AN_API_KEY, ApruveMerchant.Environment.TEST);
		assertEquals(A_MERCHANT_ID, ApruveMerchant.getInstance().getMerchantId());
		assertEquals(AN_API_KEY, ApruveMerchant.getInstance().getApiKey());
	}
	
	@Test(expected=RuntimeException.class)
	public void testGetInstanceNoInit() {
		ApruveMerchant.getInstance();
	}
	
	@Test
	public void testGetScriptForTest() {
		ApruveMerchant.init(A_MERCHANT_ID, AN_API_KEY, ApruveMerchant.Environment.TEST);
		assertEquals(SCRIPT_TAG_TEST, ApruveMerchant.getInstance().getApruveJSTag());
	}
	
	@Test
	public void testGetScriptForProd() {
		ApruveMerchant.init(A_MERCHANT_ID, AN_API_KEY, ApruveMerchant.Environment.PROD);
		assertEquals(SCRIPT_TAG_PROD, ApruveMerchant.getInstance().getApruveJSTag());
	}
	
}
