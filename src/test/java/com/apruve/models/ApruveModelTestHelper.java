package com.apruve.models;

import java.io.StringWriter;
import java.util.HashMap;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

public class ApruveModelTestHelper {

	protected static String doMarshalTest(Object obj, Class<?> clazz) throws JAXBException {
		HashMap<String,Object> jaxbProps = new HashMap<String, Object>();
		jaxbProps.put("jaxb.formatted.output", true);
		jaxbProps.put("eclipselink.media-type", "application/json");
		jaxbProps.put("eclipselink.json.include-root", false);
		JAXBContext ctx = JAXBContext.newInstance(new Class<?>[] {clazz}, jaxbProps);
		Marshaller m = ctx.createMarshaller();
		StringWriter writer = new StringWriter();
		
		m.marshal(obj, writer);
		String json = writer.toString();
		System.out.println(json);
		return json;
	}
}
