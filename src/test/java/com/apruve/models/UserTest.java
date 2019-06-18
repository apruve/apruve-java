package com.apruve.models;

import static org.junit.Assert.assertEquals;

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
}
