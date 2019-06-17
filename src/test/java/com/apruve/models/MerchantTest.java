package com.apruve.models;

import org.junit.Test;

import com.apruve.test.MerchantOM;

public class MerchantTest {

	@Test
	public void testMarshal() throws Exception {
		Merchant merchant = MerchantOM.getMerchant();
		ApruveModelTestHelper.doMarshalTest(merchant);
	}
}
