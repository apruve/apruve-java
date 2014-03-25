package com.apruve.test;

import java.util.ArrayList;
import java.util.List;

import com.apruve.models.SubscriptionAdjustment;

public class SubscriptionAdjustmentOM {

	public static SubscriptionAdjustment getMinimalSubscriptionAdjustment() {
		SubscriptionAdjustment line = new SubscriptionAdjustment();
		line.setAmountCents(100);
		line.setTitle("A Adjustment");
		return line;
	}
	
	public static SubscriptionAdjustment getSubscriptionAdjustment() {
		SubscriptionAdjustment line = new SubscriptionAdjustment();
		line.setAmountCents(100);
		line.setTitle("Another Adjustment");
		line.setDescription("A discription for this adjustment");
		line.setSku("A_SKU_NUMBER");
		return line;
	}
	
	public static List<SubscriptionAdjustment> getSubscriptionAdjustments() {
		ArrayList<SubscriptionAdjustment> result = new ArrayList<SubscriptionAdjustment>();
		result.add(getMinimalSubscriptionAdjustment());
		result.add(getSubscriptionAdjustment());
		
		return result;
	}
}
