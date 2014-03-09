package com.apruve.models;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Robert Nelson
 * @since 0.2
 */
public enum PaymentRequestStatus
{
	PENDING, CAPTURED, REJECTED, AUTHORIZED;

	private static final Map<PaymentRequestStatus, String> displayableValues;

	static
	{
		displayableValues = new HashMap<PaymentRequestStatus, String>();
		displayableValues.put(PENDING, "Pending");
		displayableValues.put(CAPTURED, "Captured");
		displayableValues.put(REJECTED, "Rejected");
		displayableValues.put(AUTHORIZED, "Authorized");
	}


	/**
	 * @return
	 */
	public String getDisplay()
	{
		return displayableValues.get(this);
	}
}
