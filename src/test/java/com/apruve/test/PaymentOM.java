package com.apruve.test;

import java.util.Date;

import com.apruve.models.Payment;

public class PaymentOM {

	public static Payment getMinimalPayment() {
		Payment payment = new Payment("abc123", 100);
		
		return payment;
	}
	
	public static Payment getPayment() throws Exception {
		Payment payment = getMinimalPayment();
		payment.setMerchantNotes("Some notes from the merchant");
		payment.setApiUrl("http://example.com/foo");
		payment.setCurrency("USD");
		payment.setCreatedAt(new Date());
		payment.setUpdatedAt(new Date());
		
		return payment;
	}
}
