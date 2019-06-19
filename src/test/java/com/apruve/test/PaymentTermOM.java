package com.apruve.test;

import java.util.Calendar;

import com.apruve.models.PaymentTerm;
import com.github.javafaker.Faker;

public class PaymentTermOM {
	private static Faker faker = new Faker();
	
	public static PaymentTerm getPaymentTerm() {
		PaymentTerm pt = new PaymentTerm();
		pt.setOrderId(faker.internet().uuid());
		pt.setStatus("approved");
		pt.setType("CorporateAccount");
		pt.setStatus("accepted");
		pt.setOrderId(faker.internet().uuid());
		pt.setCorpAccountId(faker.internet().uuid());
		
		Calendar c = Calendar.getInstance();
		c.set(2019, Calendar.APRIL, 15, 0, 0, 0);
		pt.setFinalStateAt(c.getTime());
		return pt;
	}
}
