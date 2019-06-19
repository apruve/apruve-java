package com.apruve;

import java.util.Date;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import org.apache.commons.lang3.time.DateFormatUtils;

public class ISODateTimeAdapter extends XmlAdapter<String, Date> {

	@Override
	public Date unmarshal(String v) throws Exception {
		if (v != null) {
			return DateFormatUtils.ISO_DATETIME_TIME_ZONE_FORMAT.parse(v);
		} else {
			return null;
		}
	}

	@Override
	public String marshal(Date v) throws Exception {
		if (v != null) {
			return DateFormatUtils.ISO_DATETIME_TIME_ZONE_FORMAT.format(v);
		} else {
			return null;
		}
	}

}
