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
		String hash = "a082bc92a6f318bd0316aa9fa4476b7b03416f7292aab6f15d3823065d6b9545";
		ApruveMerchant.init(A_MERCHANT_ID, AN_API_KEY, ApruveEnvironment.TEST);
		PaymentRequest pr = new PaymentRequest();
		pr.setAmountCents(new Integer(100));
		pr.getLineItems().add(createLine1());
		assertEquals(JSON_SIMPLE, pr.toJson());
		assertEquals(hash, pr.toSecureHash());
	}

	@Test
	public void testToJsonComplex() {
		String hash = "95521aaff07dbdf44b37039ab6c6ed337c05900c5ec6b2a7c1415e08bfdb9b63";
		ApruveMerchant.init(A_MERCHANT_ID, AN_API_KEY, ApruveEnvironment.TEST);
		PaymentRequest pr = new PaymentRequest();
		pr.setAmountCents(new Integer(100));
		pr.getLineItems().add(createLine1());
		pr.getLineItems().add(createLine2());
		assertEquals(JSON_COMPLEX, pr.toJson());
		assertEquals(hash, pr.toSecureHash());
	}

	@Test
	public void testToJsonRecurring() {
		String hash = "bc910beef1ac61f93e2f93c05842a242fab3a414c966492c908eb1efb32f6fe6";
		ApruveMerchant.init(A_MERCHANT_ID, AN_API_KEY, ApruveEnvironment.TEST);
		PaymentRequest pr = new PaymentRequest();
		pr.setAmountCents(new Integer(100));
		pr.setRecurring(new Boolean(true));
		pr.getLineItems().add(createLine1());
		assertEquals(JSON_RECURRING, pr.toJson());
		assertEquals(hash, pr.toSecureHash());
	}
	
	public LineItem createLine1() {
		LineItem line = new LineItem();
		line.setAmount_cents(new Integer(100));
		line.setTitle("A Line Item");
		return line;
	}

	public LineItem createLine2() {
		LineItem line = new LineItem();
		line.setAmount_cents(new Integer(100));
		line.setTitle("Another Line Item");
		line.setDescription("A discription for this line");
		line.setSku("A_SKU_NUMBER");
		return line;
	}
}
