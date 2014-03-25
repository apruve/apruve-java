package com.apruve;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Utilities {
	private static final Logger logger = LoggerFactory
			.getLogger(Utilities.class);

	// TODO: This really needs to be tested.
	private static final DateFormat timestampParser = new SimpleDateFormat(
			"yyyy-MM-dd'T'HH:mm:ssXXX");

	/**
	 * Parses the <code>date</code> based on the ISO 8601 format.
	 * 
	 * @param date
	 * @return
	 */
	public static Date parseTimestamp(String date) {
		try {
			return hasText(date) ? timestampParser.parse(date) : null;
		} catch (ParseException e) {
			logger.warn("parseTimestamp - ParseException caught.", e);
		}

		return null;
	}

	public static String formatTimestamp(Date date) {
		return timestampParser.format(date);
	}
	
	/**
	 * @param s
	 * @return
	 */
	public static boolean hasText(String s) {
		return s != null && s.trim().length() > 0;
	}
}
