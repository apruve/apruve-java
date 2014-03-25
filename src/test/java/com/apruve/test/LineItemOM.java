package com.apruve.test;

import java.util.ArrayList;
import java.util.List;

import com.apruve.models.LineItem;

public class LineItemOM {

	public static LineItem getMinimalLineItem() {
		LineItem line = new LineItem("A Line Item");
		line.setAmountCents(100);
		return line;
	}
	
	public static LineItem getLineItem() {
		LineItem line = new LineItem("Another Line Item");
		line.setAmountCents(100);
		line.setDescription("A discription for this line");
		line.setSku("A_SKU_NUMBER");
		return line;
	}
	
	public static List<LineItem> getLineItems() {
		ArrayList<LineItem> result = new ArrayList<LineItem>();
		result.add(getMinimalLineItem());
		result.add(getLineItem());
		
		return result;
	}
}
