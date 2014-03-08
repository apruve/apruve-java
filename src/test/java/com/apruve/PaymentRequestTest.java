package com.apruve;

import org.junit.After;
import org.junit.Test;


import static org.junit.Assert.*;

public class PaymentRequestTest {
	private static final String A_MERCHANT_ID = "AMerchantId";
	private static final String AN_API_KEY = "AnApiKey";
	private static final String JSON_SIMPLE = "{\"amount_cents\":100,\"line_items\":[{\"amount_cents\":100,\"title\":\"A Line Item\"}],\"merchant_id\":\"AMerchantId\"}";
	private static final String JSON_COMPLEX = "{\"amount_cents\":100,\"line_items\":[{\"amount_cents\":100,\"title\":\"A Line Item\"},{\"amount_cents\":100,\"description\":\"A discription for this line\",\"sku\":\"A_SKU_NUMBER\",\"title\":\"Another Line Item\"}],\"merchant_id\":\"AMerchantId\"}";
	private static final String JSON_RECURRING = "{\"amount_cents\":100,\"line_items\":[{\"amount_cents\":100,\"title\":\"A Line Item\"}],\"merchant_id\":\"AMerchantId\",\"recurring\":true}";
	private static final String VALUES_SIMPLE = "AMerchantId100A Line Item100";
	private static final String VALUES_COMPLEX = "AMerchantId100A Line Item100Another Line Item100A discription for this lineA_SKU_NUMBER";
	private static final String VALUES_RECURRING = "AMerchantId100trueA Line Item100";

	@After
	public void teardown() {
		ApruveMerchant.initToNull();
		ApruveClient.initToNull();
	}

	@Test
	public void testCreatePaymentRequest() {
		ApruveMerchant.init(A_MERCHANT_ID, AN_API_KEY, ApruveEnvironment.TEST);
		PaymentRequest pr = new PaymentRequest();
		assertNotNull(pr);
	}

	@Test(expected = RuntimeException.class)
	public void testCreatePaymentRequestNoInit() {
		new PaymentRequest();
	}

	@Test
	public void testToJsonSimple() {
		String hash = "b0824ba617aa53e52828de5e2a3bbbda279def354cf536f38f728a309fad208a";
		ApruveMerchant.init(A_MERCHANT_ID, AN_API_KEY, ApruveEnvironment.TEST);
		PaymentRequest pr = new PaymentRequest();
		pr.setAmountCents(new Integer(100));
		pr.getLineItems().add(createLine1());
		assertEquals(VALUES_SIMPLE, pr.toValueString());
		assertEquals(JSON_SIMPLE, pr.toJson());
		assertEquals(hash, pr.toSecureHash());
	}

	@Test
	public void testToJsonComplex() {
		String hash = "8e7e6508359eabe96201e89c452041fbc9687e4ecf91184418001be5dce96b26";
		ApruveMerchant.init(A_MERCHANT_ID, AN_API_KEY, ApruveEnvironment.TEST);
		PaymentRequest pr = new PaymentRequest();
		pr.setAmountCents(new Integer(100));
		pr.getLineItems().add(createLine1());
		pr.getLineItems().add(createLine2());
		assertEquals(VALUES_COMPLEX, pr.toValueString());
		assertEquals(JSON_COMPLEX, pr.toJson());
		assertEquals(hash, pr.toSecureHash());
	}

	@Test
	public void testToJsonRecurring() {
		String hash = "f3b49ae3ace5ebacda919d4ac80e1be03dea70e40ffa842116c56354773422e2";
		ApruveMerchant.init(A_MERCHANT_ID, AN_API_KEY, ApruveEnvironment.TEST);
		PaymentRequest pr = new PaymentRequest();
		pr.setAmountCents(new Integer(100));
		pr.setRecurring(new Boolean(true));
		pr.getLineItems().add(createLine1());
		assertEquals(VALUES_RECURRING, pr.toValueString());
		assertEquals(JSON_RECURRING, pr.toJson());
		assertEquals(hash, pr.toSecureHash());
	}
	
	public LineItem createLine1() {
		LineItem line = new LineItem();
		line.setAmountCents(100);
		line.setTitle("A Line Item");
		return line;
	}

	public LineItem createLine2() {
		LineItem line = new LineItem();
		line.setAmountCents(100);
		line.setTitle("Another Line Item");
		line.setDescription("A discription for this line");
		line.setSku("A_SKU_NUMBER");
		return line;
	}
}
