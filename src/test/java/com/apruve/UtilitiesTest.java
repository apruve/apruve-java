package com.apruve;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.Date;

import org.junit.Test;

public class UtilitiesTest {

	@Test
	public void testFormatTimestamp() {
		Calendar c = Calendar.getInstance();

		c.set(Calendar.YEAR, 2014);
		c.set(Calendar.MONTH, Calendar.APRIL);
		c.set(Calendar.DAY_OF_MONTH, 15);
		c.set(Calendar.HOUR_OF_DAY, 21);
		c.set(Calendar.MINUTE, 30);
		c.set(Calendar.SECOND, 43);
		System.out.println(c.getTime());
		
		assertEquals("2014-04-15T21:30:43-05:00", Utilities.formatTimestamp(c.getTime()));
	}
	
	@Test
	public void testParseTimestamp() {
		Date d = Utilities.parseTimestamp("2014-04-15T01:01:22-05:00");
		Calendar c = Calendar.getInstance();
		c.setTime(d);
		assertEquals(2014, c.get(Calendar.YEAR));
		assertEquals(Calendar.APRIL, c.get(Calendar.MONTH));
		assertEquals(15, c.get(Calendar.DAY_OF_MONTH));
		assertEquals(1, c.get(Calendar.HOUR_OF_DAY));
		assertEquals(1, c.get(Calendar.MINUTE));
		assertEquals(22, c.get(Calendar.SECOND));
	}
	
	@Test
	public void testHasText() {
		assertTrue(Utilities.hasText("."));
		assertTrue(Utilities.hasText("1236"));
		assertFalse(Utilities.hasText(""));
		assertFalse(Utilities.hasText("   "));
		assertFalse(Utilities.hasText(null));
	}	
}
