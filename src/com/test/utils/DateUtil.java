package com.test.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtil {
	
	public static LocalDateTime getLocalDateTimeFromString(String date) {
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(Constants.DATE_FORMAT);
		
		return LocalDateTime.parse(date.trim(), formatter);
	}

}
