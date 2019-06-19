package com.apruve.models;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.apruve.test.ShipmentItemOM;

public class ShipmentItemTest {

	@Test
	public void testMarshal() throws Exception {
		ShipmentItem item = ShipmentItemOM.getShipmentItem();
		ApruveModelTestHelper.doMarshalTest(item);
	}

	@Test
	public void testToJson() {
		ShipmentItem obj = ShipmentItemOM.getShipmentItem();
		String json = obj.toJson();
		assertNotNull(json);
		assertFalse(json.isEmpty());
	}
}
