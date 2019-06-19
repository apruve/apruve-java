package com.apruve.test;

import java.util.ArrayList;
import java.util.List;

import com.apruve.models.ShipmentItem;
import com.github.javafaker.Faker;

public class ShipmentItemOM {
	private static Faker faker = new Faker();
	
	public static ShipmentItem getShipmentItem() {
		ShipmentItem item = new ShipmentItem();
		item.setPriceTotalCents(faker.number().numberBetween(1, 100000));
		item.setDescription(faker.lorem().characters(10, 100));
		item.setSku(faker.commerce().promotionCode());		
		return item;
	}
	public static List<ShipmentItem> getShipmentItems() {
		return getShipmentItems(2);
	}
	
	public static List<ShipmentItem> getShipmentItems(int count) {
		ArrayList<ShipmentItem> items = new ArrayList<ShipmentItem>(count);
		for (int i=0; i<count; i++) {
			items.add(getShipmentItem());
		}
		return items;
	}
}
