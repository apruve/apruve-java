package com.apruve.models;

import org.junit.Test;

import com.apruve.test.ShipmentOM;

public class ShipmentTest {

	@Test
	public void testMarshal() throws Exception {
		Shipment shipment = ShipmentOM.getShipment();
		ApruveModelTestHelper.doMarshalTest(shipment, Shipment.class);
	}
}
