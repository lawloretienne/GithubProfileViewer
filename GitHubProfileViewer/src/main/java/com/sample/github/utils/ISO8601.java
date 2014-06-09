package com.sample.github.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

import android.text.format.DateUtils;
import android.util.Log;

public final class ISO8601 {
	/** Transform Calendar to ISO 8601 string. */
	public static String fromCalendar(final Calendar calendar) {
		Date date = calendar.getTime();
		String formatted = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ", Locale.US)
				.format(date);
		return formatted.substring(0, 22) + ":" + formatted.substring(22);
	}

	/** Get current date and time formatted as ISO 8601 string. */
	public static String now() {
		return fromCalendar(GregorianCalendar.getInstance());
	}

	/** Transform ISO 8601 string to Calendar. */
	public static Calendar toCalendar(final String iso8601string)
			throws ParseException {
		Calendar calendar = GregorianCalendar.getInstance();
		String s = iso8601string.replace("Z", "+07:00");
		try {
			s = s.substring(0, 22) + s.substring(23);
		} catch (IndexOutOfBoundsException e) {
			throw new ParseException("Invalid length", 0);
		}

		Date date = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ", Locale.US).parse(s);
		
		calendar.setTime(date);
		return calendar;
	}

	public static String formatDate(String timestamp) {
		String date = "";

		Calendar cal1 = null;
		try {
			cal1 = ISO8601.toCalendar(timestamp);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		Calendar cal2 = new GregorianCalendar();
		
	    cal2.setTime(new Date()); // sets calendar time/date
	    cal2.add(Calendar.HOUR_OF_DAY, -7); // subtract 7 hours
	    cal2.getTime();

	    CharSequence result = DateUtils.getRelativeTimeSpanString(cal1.getTimeInMillis(), cal2.getTimeInMillis(), 0);

	    date = (String)result;
	    
		return date;
	}
}