package com.apruve.models;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.apruve.test.FundingDetailOM;

public class FundingDetailTest {

	@Test
	public void testMarshal() throws Exception {
		FundingDetail detail = FundingDetailOM.getFundingDetail();
		ApruveModelTestHelper.doMarshalTest(detail);
	}

	@Test
	public void testToJson() {
		FundingDetail obj = FundingDetailOM.getFundingDetail();
		String json = obj.toJson();
		assertNotNull(json);
		assertFalse(json.isEmpty());
	}
}
