package com.apruve.models;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.apruve.test.ShipmentOM;

public class ShipmentTest {

	@Test
	public void testMarshal() throws Exception {
		Shipment shipment = ShipmentOM.getShipment();
		ApruveModelTestHelper.doMarshalTest(shipment);
	}
	
	@Test
	public void testGetShipmentsPath() {
		assertEquals("/invoices/invoice_id/shipments/", Shipment.getShipmentsPath("invoice_id"));
	}
	
	@Test
	public void testGetShipmentPath() {
		assertEquals("/invoices/invoice_id/shipments/shipment_id", Shipment.getShipmentPath("invoice_id", "shipment_id"));
	}

	@Test
	public void testToJson() {
		Shipment obj = ShipmentOM.getShipment();
		String json = obj.toJson();
		assertNotNull(json);
		assertFalse(json.isEmpty());
	}
}
