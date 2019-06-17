package com.apruve.models;

import com.apruve.ApruveClient;
import com.apruve.ApruveResponse;
import com.apruve.JsonUtil;

public class User {
	static final String USER_PATH = "/users/%userId";
	
	private String id; 
	private String name;
	private String email;

	public static String getUserPath(String userId) {
		return USER_PATH.replace("%userId", userId);
	}
	
	public static ApruveResponse<User> get(ApruveClient client, String userId) {
		return client.get(getUserPath(userId), User.class);
	}
	
	@Override
	public String toString() {
		return toJson();
	}

	/**
	 * Returns the JSON string for a model.
	 * 
	 * @return JSON representation of the model.
	 */
	public String toJson() {
		return JsonUtil.getInstance().toJson(this);
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
}
