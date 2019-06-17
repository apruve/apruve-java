package com.apruve;

import static org.junit.Assert.*;

import org.junit.Test;

public class ApruveEnvironmentTest {

	@Test
	public void testBaseUrl() {
		assertEquals("https://app.apruve.com", ApruveEnvironment.PROD.getBaseUrl());
		assertEquals("https://test.apruve.com", ApruveEnvironment.TEST.getBaseUrl());
	}
	
	@Test
	public void testApiUrl() {
		assertEquals("https://app.apruve.com/api/v4", ApruveEnvironment.PROD.getApiV4Url());
		assertEquals("https://test.apruve.com/api/v4", ApruveEnvironment.TEST.getApiV4Url());
	}
	
	@Test
	public void testJsUrl() {
		assertEquals("https://app.apruve.com/js/v4/apruve.js", ApruveEnvironment.PROD.getJsUrl());
		assertEquals("https://test.apruve.com/js/v4/apruve.js", ApruveEnvironment.TEST.getJsUrl());
	}
	
	@Test
	public void testJsTag() {
		assertEquals("<script src=\"https://app.apruve.com/js/v4/apruve.js\" type=\"text/javascript\"></script>", ApruveEnvironment.PROD.getJsTag());
		assertEquals("<script src=\"https://test.apruve.com/js/v4/apruve.js\" type=\"text/javascript\"></script>", ApruveEnvironment.TEST.getJsTag());
	}
}
