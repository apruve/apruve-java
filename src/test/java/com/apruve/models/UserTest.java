package com.apruve.models;

import org.junit.Test;

import com.apruve.test.UserOM;

public class UserTest {

	@Test
	public void testMarshal() throws Exception {
		User user = UserOM.getUser();
		ApruveModelTestHelper.doMarshalTest(user, User.class);
	}
}
