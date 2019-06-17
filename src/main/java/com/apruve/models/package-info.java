@XmlJavaTypeAdapter(type = Date.class, value = ISODateTimeAdapter.class)
package com.apruve.models;

import java.util.Date;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.apruve.ISODateTimeAdapter;