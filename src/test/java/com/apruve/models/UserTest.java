package com.apruve.models;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.apruve.test.UserOM;

public class UserTest {

	@Test
	public void testMarshal() throws Exception {
		User user = UserOM.getUser();
		ApruveModelTestHelper.doMarshalTest(user);
	}
	
	@Test
	public void testGetUserPath() {
		assertEquals("/users/user_id", User.getUserPath("user_id"));
	}

	@Test
	public void testToJson() {
		User obj = UserOM.getUser();
		String json = obj.toJson();
		assertNotNull(json);
		assertFalse(json.isEmpty());
	}
}
