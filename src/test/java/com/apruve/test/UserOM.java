package com.apruve.test;

import java.util.ArrayList;
import java.util.List;

import com.apruve.models.User;
import com.github.javafaker.Faker;

public class UserOM {
	private static Faker faker = new Faker();

	public static User getUser() {
		User user = new User();
		user.setName(faker.name().fullName());
		user.setEmail(faker.internet().safeEmailAddress());
		return user;
	}
	
	public static List<User> getUserList() {
		return getUserList(1);
	}
	
	public static List<User> getUserList(int count) {
		assert count > 0;
		
		List<User> users = new ArrayList<User>(count);
		for (int i=0; i<count; i++) {
			users.add(getUser());
		}
		return users;
	}
}
