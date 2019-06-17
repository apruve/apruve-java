package com.apruve;

import static org.junit.Assert.assertEquals;

import org.junit.Test;


public class ApruveClientTest {
	private static final String AN_API_KEY = "AnApiKey";
	
	@Test
	public void testConstructor() {
		ApruveClient client = new ApruveClient(AN_API_KEY, ApruveEnvironment.TEST);
		assertEquals(AN_API_KEY, client.getApiKey());
		assertEquals(ApruveEnvironment.TEST, client.getEnvironment());
	}

	
	@Test(expected=RuntimeException.class)
	public void testConstructorNullKey() {
		new ApruveClient(null, ApruveEnvironment.TEST);
	}
	
	@Test(expected=RuntimeException.class)
	public void testConstructorNullEnv() {
		new ApruveClient(AN_API_KEY, null);
	}	
}
