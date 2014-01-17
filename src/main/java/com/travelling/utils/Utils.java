package com.travelling.utils;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Contains useful functions.
 * 
 * @author Claudia Cardei
 *
 */
public class Utils {
	
	/**
	 * Returns the number of minutes contained in a date.
	 * 
	 * @param d
	 * @return number of minutes
	 */
	public static int getMinutesFromDate(Date d) {
		Calendar c = new GregorianCalendar();
	    c.setTime(d);
	    return c.get(Calendar.HOUR_OF_DAY) * 60 + c.get(Calendar.MINUTE);
	}
	
	/**
	 * Computes log2 of a number.
	 * 
	 * @param x
	 * @return log2
	 */
	public static double log2(double x) {
		return Math.log(x) / Math.log(2);
	}
}
