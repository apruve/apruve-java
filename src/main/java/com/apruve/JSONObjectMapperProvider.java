package com.apruve;

import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.module.jaxb.JaxbAnnotationModule;

/**
 * ContextResolver implementation used to tell JAX-RS to use Jackson JSON to
 * marshal/unmarshal payloads.
 * 
 * @author todd
 *
 */
@Provider
public class JSONObjectMapperProvider implements ContextResolver<ObjectMapper> {

	private final ObjectMapper mapper;
	
	public JSONObjectMapperProvider() {
		mapper = new ObjectMapper();
		mapper.registerModule(new JaxbAnnotationModule());
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	}
	
	@Override
	public ObjectMapper getContext(Class<?> arg0) {
		return mapper;
	}

}
