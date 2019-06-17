package com.apruve.models;

import org.junit.Test;

import com.apruve.test.AddressOM;

public class AddressTest {

	@Test
	public void testMarshal() throws Exception {
		Address address = AddressOM.getAddress();
		ApruveModelTestHelper.doMarshalTest(address);
	}
}
