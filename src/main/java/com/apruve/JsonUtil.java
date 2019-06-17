package com.apruve;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.module.jaxb.JaxbAnnotationModule;

public class JsonUtil {
	private static final Logger log = Logger.getLogger(JsonUtil.class.getName());

	private static final JsonUtil instance = new JsonUtil();
	
	private JsonUtil() {
		//singleton
	}
	
	public static JsonUtil getInstance() {
		return instance;
	}
	
	public String toJson(Object o) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			mapper.registerModule(new JaxbAnnotationModule());
			return mapper.writeValueAsString(o);
		} catch (JsonProcessingException e) {
			log.log(Level.WARNING,
					"Object provided is not configured for conversion to JSON");
			return "";
		}
	}	
}
