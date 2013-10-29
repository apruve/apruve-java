package com.apruve;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ShaUtilTest {
	@Test
	public void testToHash() {
		String data = "asdfjkl;123kjljkl4313123USDatitle123";
		String hash = "7114ee7046d2a04ca876fbbf65b2bab5c988022b919e3c4653d2f7814cb514dc";
		assertEquals(hash, ShaUtil.getDigest(data));
	}
	
}
