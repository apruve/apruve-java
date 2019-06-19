package com.apruve.models;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

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

	@Test
	public void testToJson() {
		Merchant obj = MerchantOM.getMerchant();
		String json = obj.toJson();
		assertNotNull(json);
		assertFalse(json.isEmpty());
	}
}
