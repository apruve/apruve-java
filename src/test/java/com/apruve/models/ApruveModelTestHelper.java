package com.apruve.models;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.module.jaxb.JaxbAnnotationModule;

public class ApruveModelTestHelper {
	private static final ObjectMapper mapper;
	
	static {
		mapper=new ObjectMapper();
		mapper.registerModule(new JaxbAnnotationModule());
	}

	protected static String doMarshalTest(Object obj) throws JsonProcessingException {
		return mapper.writeValueAsString(obj);
	}
}
