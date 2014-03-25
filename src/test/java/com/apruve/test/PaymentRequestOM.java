package com.apruve.test;

import java.util.ArrayList;

import com.apruve.models.LineItem;
import com.apruve.models.PaymentRequest;

public class PaymentRequestOM {

	private static final String A_MERCHANT_ID = "AMerchantId";

	public static PaymentRequest getPaymentRequestSimple() {
		PaymentRequest request = new PaymentRequest(A_MERCHANT_ID);
		request.setAmountCents(new Integer(100));
		request.setLineItems(new ArrayList<LineItem>());
		request.getLineItems().add(LineItemOM.getMinimalLineItem());
		return request;
	}
	
	public static PaymentRequest getPaymentRequest() {
		PaymentRequest request = new PaymentRequest(A_MERCHANT_ID);
		request.setAmountCents(new Integer(100));
		request.setLineItems(LineItemOM.getLineItems());
		
		return request;
	}
}
