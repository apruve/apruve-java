package com.apruve.models;

import org.junit.Test;

import com.apruve.test.OrderChildLinksOM;

public class OrderChildLinksTest {

	@Test
	public void testMarshal() throws Exception {
		OrderChildLinks links = OrderChildLinksOM.getOrderChildLinks();
		ApruveModelTestHelper.doMarshalTest(links);
	}
}
