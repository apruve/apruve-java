package com.apruve;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.module.jaxb.JaxbAnnotationModule;

public class JsonUtil {
	private static final Logger log;
	private static final ObjectMapper mapper;
	private static final JsonUtil instance;
	
	static {
		log = Logger.getLogger(JsonUtil.class.getName());
		mapper = new ObjectMapper();
		mapper.registerModule(new JaxbAnnotationModule());
		instance = new JsonUtil();
	}
	
	private JsonUtil() {
		//singleton
	}
	
	public static JsonUtil getInstance() {
		return instance;
	}
	
	public String toJson(Object o) {
		try {
			return mapper.writeValueAsString(o);
		} catch (JsonProcessingException e) {
			log.log(Level.WARNING,
					"Object provided is not configured for conversion to JSON");
			return "";
		}
	}	
}
