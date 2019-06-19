package com.apruve.models;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertFalse;

import org.junit.Test;

import com.apruve.test.AddressOM;

public class AddressTest {

	@Test
	public void testMarshal() throws Exception {
		Address address = AddressOM.getAddress();
		ApruveModelTestHelper.doMarshalTest(address);
	}

	@Test
	public void testToJson() {
		Address obj = AddressOM.getAddress();
		String json = obj.toJson();
		assertNotNull(json);
		assertFalse(json.isEmpty());
	}
}
