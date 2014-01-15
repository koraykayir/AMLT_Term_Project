package com.travelling.retrieval;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * 
 * Functions to compute the distance and the similarity between values.
 * 
 * @author Claudia Cardei
 *
 */
public class CaseAttributeMeasures {
	
	/**
	 * Computes the distance between x and y.
	 * 
	 * @param x
	 * @param y
	 * @return the distance
	 */
	public static double getDistance(Object x, Object y) {
		if (x instanceof Integer) {
			Integer castedX = (Integer) x;
			Integer castedY = (Integer) y;
			return Math.abs(castedX - castedY);
		}
		
		if (x instanceof Double) {
			Double castedX = (Double) x;
			Double castedY = (Double) y;
			return Math.abs(castedX - castedY);
		}
		
		if (x instanceof Date) {
			Date castedX = (Date) x;
			Date castedY = (Date) y;
			Calendar c1 = new GregorianCalendar();
	        c1.setTime(castedX);
	        Calendar c2 = new GregorianCalendar();
	        c2.setTime(castedY);
	        int minutes1 = c1.get(Calendar.HOUR_OF_DAY) * 60 + c1.get(Calendar.MINUTE);
	        int minutes2 = c2.get(Calendar.HOUR_OF_DAY) * 60 + c2.get(Calendar.MINUTE);
	        return Math.abs(minutes2 - minutes1);
		}
		
		// Should never do this.
		return 0.0;
	}
	
	/**
	 * Computes the normalized similarity starting from the distance of two values. Needs 
	 * the maximum and the minimum values for normalization.
	 * 
	 * @param max
	 * @param min
	 * @param distance
	 * @return normalized similarity
	 */
	public static double getSimilarity( Object x, Object y, Object max, Object min) {
		double distance = CaseAttributeMeasures.getDistance(x, y);
		double maxMinDistance = CaseAttributeMeasures.getDistance(max, min);
		return (maxMinDistance - distance) / maxMinDistance;
	}
}
