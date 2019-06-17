package com.apruve.models;

import com.apruve.JsonUtil;

public class ApruveModelTestHelper {

	protected static String doMarshalTest(Object obj) {
		String json = JsonUtil.getInstance().toJson(obj);
		System.out.println(obj.getClass().getName() + ":" + json);
		return json;
	}
}
