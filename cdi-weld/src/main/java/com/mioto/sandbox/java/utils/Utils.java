package com.mioto.sandbox.java.utils;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * The Class Utils.
 */
public final class Utils {

	/**
	 * Instantiates a new probe utils.
	 */
	private Utils() {
		//Dummy
	}

	/**
	 * subtract days to date in java.
	 *
	 * @param date the date
	 * @param minutes the minutes
	 * @return the date
	 */
	public static Date subtractMinutes(final Date date, final int minutes) {
		final GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(date);
		cal.add(Calendar.MINUTE, -minutes);
		return cal.getTime();
	}
}
