package com.apruve.test;

import com.apruve.models.PaymentMethod;
import com.github.javafaker.Faker;

public class PaymentMethodOM {
	private static Faker faker = new Faker();
	
	public static PaymentMethod getPaymentMethod() {
		PaymentMethod pm = new PaymentMethod();
		pm.setNickname(faker.name().firstName());
		pm.setLast4(String.valueOf(faker.number().numberBetween(1000, 9999)));
		pm.setType("ECheckAccount");
		pm.setLinks(PaymentMethodLinksOM.getPaymentMethodLinks());
		return pm;
	}
}
