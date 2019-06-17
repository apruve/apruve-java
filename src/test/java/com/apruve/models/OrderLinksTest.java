package com.apruve.models;

import org.junit.Test;

import com.apruve.test.OrderLinksOM;

public class OrderLinksTest {

	@Test
	public void testMarshal() throws Exception {
		OrderLinks links = OrderLinksOM.getOrderLinks();
		ApruveModelTestHelper.doMarshalTest(links, OrderLinks.class);
	}
}
