package com.apruve;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

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
			StringWriter buffer = new StringWriter();
			HashMap<String, Object> jaxbProps = new HashMap<String, Object>();
			jaxbProps.put("eclipselink.media-type", "application/json");
			jaxbProps.put("eclipselink.json.include-root", false);
			JAXBContext ctx = JAXBContext.newInstance("com.apruve.models", this
					.getClass().getClassLoader(), jaxbProps);
			Marshaller m = ctx.createMarshaller();
			m.marshal(o, buffer);

			return buffer.toString();
		} catch (JAXBException e) {
			log.log(Level.WARNING,
					"Object provided is not configured for conversion to JSON");
			return "";
		}
	}	
}
