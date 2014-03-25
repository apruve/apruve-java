package com.apruve.test;

import java.util.Date;

import com.apruve.models.Subscription;

public class SubscriptionOM {

	public static Subscription getMinimalSubscription() {
		Subscription sub = new Subscription();
		sub.setPlanCode("abc123");;
		sub.setTitle("A Line Item");
		sub.setStartAt(new Date());
		sub.setNextChargeAt(new Date());
		return sub;
	}
	
	public static Subscription getSubscription() {
		Subscription sub = getMinimalSubscription();
		sub.setAmountCents(100);
		sub.setDescription("A discription for this line");
		sub.setSku("A_SKU_NUMBER");
		return sub;
	}
}
