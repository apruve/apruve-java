package com.apruve.models;

import org.junit.Test;

import com.apruve.test.MerchantOM;

import junit.framework.Assert;

public class MerchantTest {

	@Test
	public void testMarshal() throws Exception {
		Merchant merchant = MerchantOM.getMerchant();
		ApruveModelTestHelper.doMarshalTest(merchant);
	}
	
	@Test
	public void testGetMerchantPath() {
		Assert.assertEquals("/merchants/merchant_id", Merchant.getMerchantPath("merchant_id"));
	}
}
