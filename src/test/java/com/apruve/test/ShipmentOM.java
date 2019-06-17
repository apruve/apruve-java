package com.apruve.test;

import com.apruve.models.Shipment;
import com.github.javafaker.Faker;

public class ShipmentOM {
	private static Faker faker = new Faker();
	
	public static Shipment getShipment() {
		Shipment shipment = new Shipment(faker.internet().uuid());
		shipment.setAmountCents(faker.number().numberBetween(100, 500000));
		shipment.setCurrency("USD");
		shipment.setShipper("UPS");
		shipment.setTrackingNumber(faker.commerce().promotionCode(15));
		shipment.setShipmentItems(ShipmentItemOM.getShipmentItems());
		
		return shipment;
	}
}
