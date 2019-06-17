package com.apruve.test;

import com.apruve.models.Merchant;
import com.github.javafaker.Faker;

public class MerchantOM {
	private static Faker faker = new Faker();
	
	public static Merchant getMerchant() {
		Merchant merchant = new Merchant();
		merchant.setName(faker.company().name());
		merchant.setEmail(faker.internet().safeEmailAddress());
		merchant.setPhone(faker.phoneNumber().phoneNumber());
		merchant.setUrlSlug(faker.internet().slug());
		merchant.setWebUrl(faker.internet().url());
		return merchant;
	}
}
