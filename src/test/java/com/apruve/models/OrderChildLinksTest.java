package com.apruve.models;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.apruve.test.OrderChildLinksOM;

public class OrderChildLinksTest {

	@Test
	public void testMarshal() throws Exception {
		OrderChildLinks links = OrderChildLinksOM.getOrderChildLinks();
		ApruveModelTestHelper.doMarshalTest(links);
	}

	@Test
	public void testToJson() {
		OrderChildLinks obj = OrderChildLinksOM.getOrderChildLinks();
		String json = obj.toJson();
		assertNotNull(json);
		assertFalse(json.isEmpty());
	}
}
