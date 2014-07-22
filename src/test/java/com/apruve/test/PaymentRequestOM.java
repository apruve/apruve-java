package com.apruve.test;

import java.util.ArrayList;
import java.util.Date;
import java.util.Calendar;

import com.apruve.models.LineItem;
import com.apruve.models.PaymentRequest;

public class PaymentRequestOM {

	private static final String A_MERCHANT_ID = "AMerchantId";
	private static final String AN_ORDER_ID = "AnOrderId";

	public static PaymentRequest getPaymentRequestSimple() {
		PaymentRequest request = new PaymentRequest(A_MERCHANT_ID);
		request.setAmountCents(new Integer(100));
		request.setLineItems(new ArrayList<LineItem>());
		request.getLineItems().add(LineItemOM.getMinimalLineItem());
		return request;
	}
	
	public static PaymentRequest getPaymentRequest() {
		PaymentRequest request = new PaymentRequest(A_MERCHANT_ID);
		request.setMerchantOrderId(AN_ORDER_ID);
		request.setAmountCents(new Integer(100));
		request.setLineItems(LineItemOM.getLineItems());
		request.setExpiresAt("2014-12-31T00:00:00-06:00");
		
		return request;
	}
}
