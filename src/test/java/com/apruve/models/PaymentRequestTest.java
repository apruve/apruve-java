package com.apruve.models;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.HashMap;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import org.junit.After;
import org.junit.Test;

import com.apruve.ApruveClient;
import com.apruve.ApruveEnvironment;
import com.apruve.test.PaymentRequestOM;

public class PaymentRequestTest {
	private static final String AN_API_KEY = "AnApiKey";
	private static final String VALUES_SIMPLE = "AMerchantId100A Line Item100";
	private static final String VALUES_COMPLEX = "AMerchantIdAnOrderId1002014-12-31T00:00:00-06:00A Line Item100Another Line Item100A discription for this lineA_SKU_NUMBER";
	
	@Test
	public void testMarshal() throws Exception {
		PaymentRequest request = PaymentRequestOM.getPaymentRequest();
		
		HashMap<String,Object> jaxbProps = new HashMap<String, Object>();
		jaxbProps.put("jaxb.formatted.output", true);
		jaxbProps.put("eclipselink.media-type", "application/json");
		jaxbProps.put("eclipselink.json.include-root", false);
		JAXBContext ctx = JAXBContext.newInstance(new Class<?>[] {PaymentRequest.class}, jaxbProps);
		Marshaller m = ctx.createMarshaller();
		m.marshal(request, System.out);
	}
	
	@After
	public void teardown() {
		ApruveClient.initToNull();
	}

	@Test
	public void testCreatePaymentRequest() {
		ApruveClient.init(AN_API_KEY, ApruveEnvironment.TEST);
		PaymentRequest pr = new PaymentRequest();
		assertNotNull(pr);
	}

	@Test
	public void testToJsonSimple() {
		String hash = "b0824ba617aa53e52828de5e2a3bbbda279def354cf536f38f728a309fad208a";
		ApruveClient.init(AN_API_KEY, ApruveEnvironment.TEST);
		PaymentRequest pr = PaymentRequestOM.getPaymentRequestSimple();
		assertEquals(VALUES_SIMPLE, pr.toValueString());
		assertEquals(hash, pr.toSecureHash());
	}

	@Test
	public void testToJsonComplex() {
		String hash = "b2e468a46c289ceb3dc7d53f13bce879891c0183014d8fd066ef1425b3f5049d";
		ApruveClient.init(AN_API_KEY, ApruveEnvironment.TEST);
		PaymentRequest pr = PaymentRequestOM.getPaymentRequest();
		assertEquals(VALUES_COMPLEX, pr.toValueString());
		assertEquals(hash, pr.toSecureHash());
	}
}
