package com.apruve.test;

import java.util.ArrayList;
import java.util.Calendar;

import com.apruve.models.Order;
import com.apruve.models.OrderItem;

public class OrderOM {
	private static final String AN_API_KEY = "AnApiKey";
	private static final String A_MERCHANT_ID = "AMerchantId";
	private static final String AN_ORDER_ID = "AnOrderId";

	public static Order getOrderSimple() {
		Order order = new Order(A_MERCHANT_ID, AN_API_KEY);
		order.setAmountCents(new Integer(100));
		order.setOrderItems(new ArrayList<OrderItem>());
		order.getOrderItems().add(OrderItemOM.getMinimalLineItem());
		return order;
	}
	
	public static Order getOrder() {
		Order order = new Order(A_MERCHANT_ID, AN_API_KEY);
		order.setMerchantOrderId(AN_ORDER_ID);
		order.setAmountCents(new Integer(100));
		order.setOrderItems(OrderItemOM.getLineItems());
		order.setLinks(OrderLinksOM.getOrderLinks());
		
		Calendar c = Calendar.getInstance();
		c.set(2014, Calendar.DECEMBER, 31, 0, 0, 0);
		order.setExpireAt(c.getTime());
		
		return order;
	}
}
