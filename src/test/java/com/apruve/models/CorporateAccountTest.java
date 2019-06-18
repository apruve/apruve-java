package com.apruve.models;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.apruve.test.CorporateAccountOM;

public class CorporateAccountTest {

	@Test
	public void testMarshal() throws Exception {
		CorporateAccount account = CorporateAccountOM.getCorporateAccountComplete();
		ApruveModelTestHelper.doMarshalTest(account);
	}
	
	@Test
	public void testGetCorporateAccountsPath() throws Exception {
		assertEquals("/merchants/merchant_id/corporate_accounts/", CorporateAccount.getCorporateAccountsPath("merchant_id"));
	}
	
	@Test
	public void testGetCorporateAccountPath() throws Exception {
		assertEquals("/merchants/merchant_id/corporate_accounts/account_id", CorporateAccount.getCorporateAccountPath("merchant_id", "account_id"));
	}
}
