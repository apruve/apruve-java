package com.apruve;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Test;


public class ApruveClientTest {
	private static final String AN_API_KEY = "AnApiKey";
	
	@After
	public void teardown() {
		ApruveClient.initToNull();
	}

	@Test
	public void testInit() {
		ApruveClient.init(AN_API_KEY, ApruveEnvironment.TEST);
		assertNotNull(ApruveClient.getInstance());
	}

	@Test
	public void testGetInstance() {
		ApruveClient.init(AN_API_KEY, ApruveEnvironment.TEST);
		assertEquals(AN_API_KEY, ApruveClient.getInstance().getApiKey());
	}

	
	@Test(expected=RuntimeException.class)
	public void testGetInstanceNoInit() {
		ApruveClient.getInstance();
	}
	
	
}
