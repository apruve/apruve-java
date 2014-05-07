package com.apruve;

import static org.junit.Assert.*;

import org.junit.Test;

public class ApruveEnvironmentTest {

	@Test
	public void testBaseUrl() {
		assertEquals("https://www.apruve.com", ApruveEnvironment.PROD.getBaseUrl());
		assertEquals("https://test.apruve.com", ApruveEnvironment.TEST.getBaseUrl());
	}
	
	@Test
	public void testApiUrl() {
		assertEquals("https://www.apruve.com/api/v3", ApruveEnvironment.PROD.getApiV3Url());
		assertEquals("https://test.apruve.com/api/v3", ApruveEnvironment.TEST.getApiV3Url());
	}
	
	@Test
	public void testJsUrl() {
		assertEquals("https://www.apruve.com/js/apruve.js", ApruveEnvironment.PROD.getJsUrl());
		assertEquals("https://test.apruve.com/js/apruve.js", ApruveEnvironment.TEST.getJsUrl());
	}
	
	@Test
	public void testJsTag() {
		assertEquals("<script src=\"https://www.apruve.com/js/apruve.js\" type=\"text/javascript\"></script>", ApruveEnvironment.PROD.getJsTag());
		assertEquals("<script src=\"https://test.apruve.com/js/apruve.js\" type=\"text/javascript\"></script>", ApruveEnvironment.TEST.getJsTag());
	}
}
