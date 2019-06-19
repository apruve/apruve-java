package com.apruve.test;

import com.apruve.models.InvoiceReturn;
import com.github.javafaker.Faker;

public class InvoiceReturnOM {
	private static Faker faker = new Faker();
	
	public static InvoiceReturn getInvoiceReturn() {
		InvoiceReturn ir = new InvoiceReturn();
		ir.setAmountCents(faker.number().numberBetween(100, 50000));
		ir.setCurrency("USD");
		ir.setInvoiceId(faker.internet().uuid());
		ir.setReason("Duplicate");
		return ir;
	}
}
