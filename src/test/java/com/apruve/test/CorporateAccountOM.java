package com.apruve.test;

import com.apruve.models.CorporateAccount;
import com.github.javafaker.Faker;

public class CorporateAccountOM {
  private static Faker faker = new Faker();
  
  public static CorporateAccount getCorporateAccount() {
	  CorporateAccount account = new CorporateAccount();
	  account.setAuthorizedBuyers(UserOM.getUserList(3));
	  account.setCreditAmountCents(faker.number().numberBetween(100000, 1000000));
	  account.setCreditAvailableCents(account.getCreditAmountCents());
	  account.setCreditBalanceCents(0);
	  account.setCustomerUuid(faker.internet().uuid());
	  account.setMerchantUuid(faker.internet().uuid());
	  account.setName(faker.company().name());
	  account.setPaymentTermStrategyName("Net30");
	  account.setType("CorporateAccount");
	  return account;
  }
  public static CorporateAccount getCorporateAccountComplete() {
	  CorporateAccount account = getCorporateAccount();
	  account.setCreditAvailableCents(faker.number().numberBetween(0, 1000000));
	  account.setCreditBalanceCents(account.getCreditAmountCents()-account.getCreditAvailableCents());
	  account.setMerchantAccountId(faker.commerce().promotionCode());
	  return account;
  }
}
