package com.apruve.models;

import org.junit.Test;

import com.apruve.test.CorporateAccountOM;

public class CorporateAccountTest {

	@Test
	public void testMarshal() throws Exception {
		CorporateAccount account = CorporateAccountOM.getCorporateAccountComplete();
		ApruveModelTestHelper.doMarshalTest(account, CorporateAccount.class);
	}
}
