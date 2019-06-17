package com.apruve.models;

import org.junit.Test;

import com.apruve.test.FundingDetailOM;

public class FundingDetailTest {

	@Test
	public void testMarshal() throws Exception {
		FundingDetail detail = FundingDetailOM.getFundingDetail();
		ApruveModelTestHelper.doMarshalTest(detail);
	}
}
