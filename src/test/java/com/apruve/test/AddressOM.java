package com.apruve.test;

import com.apruve.models.Address;
import com.github.javafaker.Faker;

public class AddressOM {
	private static final com.github.javafaker.Address faker = new Faker().address();

	public static Address getAddress() {
		Address address = new Address();
		address.setAddress1(faker.streetAddress());
		address.setAddress2(faker.secondaryAddress());
		address.setCity(faker.city());
		address.setState(faker.stateAbbr());
		address.setCountryCode("US");
		address.setZip(faker.zipCode());
		return address;
	}
}
