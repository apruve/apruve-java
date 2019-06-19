package com.apruve.test;

import java.util.ArrayList;
import java.util.List;

import com.apruve.models.OrderItem;

public class OrderItemOM {

	public static OrderItem getMinimalLineItem() {
		OrderItem line = new OrderItem("A Line Item");
		line.setPriceTotalCents(100);
		return line;
	}
	
	public static OrderItem getLineItem() {
		OrderItem line = new OrderItem("Another Line Item");
		line.setPriceTotalCents(100);
		line.setDescription("A description for this line");
		line.setSku("A_SKU_NUMBER");
		line.setLinks(OrderChildLinksOM.getOrderChildLinks());
		return line;
	}
	
	public static List<OrderItem> getLineItems() {
		ArrayList<OrderItem> result = new ArrayList<OrderItem>();
		result.add(getMinimalLineItem());
		result.add(getLineItem());
		
		return result;
	}
}
