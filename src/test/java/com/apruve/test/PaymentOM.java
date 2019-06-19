package com.apruve.test;

import com.apruve.models.Payment;
import com.github.javafaker.Faker;

public class PaymentOM {
	private static Faker faker = new Faker();
	
	public static Payment getPayment() {
		Payment payment = new Payment();
		payment.setAmountCents(faker.number().numberBetween(100, 100000));
		payment.setCurrency("USD");
		payment.setPaidOut(false);
		payment.setRefundedAmountCents(0);
		payment.setStatus("pending");
		payment.setLinks(PaymentLinksOM.getPaymentLinks());
		return payment;
	}
}
