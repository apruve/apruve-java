package com.apruve.test;

import java.util.ArrayList;
import java.util.List;

import com.apruve.models.PaymentItem;

public class PaymentItemOM {

	public static PaymentItem getMinimalPaymentItem() {
		PaymentItem line = new PaymentItem();
		line.setAmountCents(100);
		line.setTitle("A Payment Item");
		return line;
	}
	
	public static PaymentItem getPaymentItem() {
		PaymentItem line = new PaymentItem();
		line.setAmountCents(100);
		line.setTitle("Another Payment Item");
		line.setDescription("A discription for this item");
		line.setSku("A_SKU_NUMBER");
		return line;
	}
	
	public static List<PaymentItem> getPaymentItems() {
		ArrayList<PaymentItem> result = new ArrayList<PaymentItem>();
		result.add(getMinimalPaymentItem());
		result.add(getPaymentItem());
		
		return result;
	}
}
