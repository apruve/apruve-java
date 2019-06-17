package com.apruve.models;

import org.junit.Test;

import com.apruve.test.ShipmentItemOM;

public class ShipmentItemTest {

	@Test
	public void testMarshal() throws Exception {
		ShipmentItem item = ShipmentItemOM.getShipmentItem();
		ApruveModelTestHelper.doMarshalTest(item);
	}
}
