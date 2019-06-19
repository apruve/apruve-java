package com.apruve.models;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.apruve.test.OrderLinksOM;

public class OrderLinksTest {

	@Test
	public void testMarshal() throws Exception {
		OrderLinks links = OrderLinksOM.getOrderLinks();
		ApruveModelTestHelper.doMarshalTest(links);
	}

	@Test
	public void testToJson() {
		OrderLinks obj = OrderLinksOM.getOrderLinks();
		String json = obj.toJson();
		assertNotNull(json);
		assertFalse(json.isEmpty());
	}
}
