package com.apruve.models;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.apruve.test.OrderOM;

public class OrderTest {
	private static final String VALUES_SIMPLE = "AMerchantId100A Line Item100";
	private static final String VALUES_COMPLEX = "AMerchantIdAnOrderId1002014-12-31T00:00:00-06:00A Line Item100Another Line Item100A description for this lineA_SKU_NUMBER";
	
	@Test
	public void testMarshal() throws Exception {
		Order order = OrderOM.getOrder();
		ApruveModelTestHelper.doMarshalTest(order);
	}
	
	@Test
	public void testCreateOrder() {
		Order order = new Order();
		assertNotNull(order);
		assertNotNull(order.getOrderItems());
	}

	@Test
	public void testValueStringSimple() {
		Order order = OrderOM.getOrderSimple();
		assertEquals(VALUES_SIMPLE, order.toValueString());
	}
	
	@Test
	public void testSecureHashSimple() {
		Order order = OrderOM.getOrderSimple();
		String hash = "b0824ba617aa53e52828de5e2a3bbbda279def354cf536f38f728a309fad208a";
		assertEquals(hash, order.toSecureHash());
	}

	@Test
	public void testValueStringComplex() {
		Order order = OrderOM.getOrder();
		assertEquals(VALUES_COMPLEX, order.toValueString());
	}
	
	@Test
	public void testSecureHashComplex() {
		String hash = "652a09328c16dbe48db1822cd490341e25196a495763b39a12c574ccb8a363c5";
		Order order = OrderOM.getOrder();
		assertEquals(hash, order.toSecureHash());
	}
}
