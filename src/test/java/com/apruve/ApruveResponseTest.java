package com.apruve;

import static org.junit.Assert.*;

import org.junit.Test;

public class ApruveResponseTest {

	@Test
	public void testSuccess() { 
		ApruveResponse<Object> successful = new ApruveResponse<Object>(200, null);
		assertTrue(successful.success());
		assertEquals(200, successful.getResponseCode());
	}
	
	@Test
	public void testFailure() {
		ApruveResponse<Object> failure = new ApruveResponse<Object>(400, null, "foo");
		assertFalse(failure.success());
		assertEquals(400, failure.getResponseCode());
		assertEquals("foo", failure.getErrorResponse());
	}
}
