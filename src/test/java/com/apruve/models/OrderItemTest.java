package com.apruve.models;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.apruve.test.OrderItemOM;

public class OrderItemTest {
	private static final String VALUE_STRING = "Another Line Item100A description for this lineA_SKU_NUMBER";

	@Test
	public void testMarshal() throws Exception {
		OrderItem obj = OrderItemOM.getLineItem();
		ApruveModelTestHelper.doMarshalTest(obj);
	}
	
	@Test
	public void testCreateOrderItem() {
		OrderItem item = new OrderItem("title");
		assertNotNull(item);
		assertEquals("title", item.getTitle());
	}
	
	@Test
	public void testToValueString() {
		OrderItem item = OrderItemOM.getLineItem();
		assertEquals(VALUE_STRING, item.toValueString());
	}
	
	@Test
	public void testGetOrderItemsPath() {
		assertEquals("/orders/order_id/order_items/", OrderItem.getOrderItemsPath("order_id"));
	}
	
	@Test
	public void testGetOrderItemPath() {
		assertEquals("/order_items/item_id", OrderItem.getOrderItemPath("item_id"));
	}
}
