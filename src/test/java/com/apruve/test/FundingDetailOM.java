package com.apruve.test;

import com.apruve.models.FundingDetail;
import com.github.javafaker.Faker;

public class FundingDetailOM {
	private static Faker faker = new Faker();

	public static FundingDetail getFundingDetail() {
		FundingDetail detail = new FundingDetail();
		detail.setInvoiceId(faker.internet().uuid());
		detail.setAmountCents(faker.number().numberBetween(100, 1000000));
		detail.setFeeCents(faker.number().numberBetween(1, 20000));
		return detail;
	}
}
