package com.travelling.retrieval;

import java.util.Date;

import com.travelling.utils.Utils;

/**
 * 
 * Functions to compute the distance and the similarity between values.
 * 
 * @author Claudia Cardei
 *
 */
public class CaseAttributeMeasures {
	
	/**
	 * Computes the normalized distance between x and y. Needs the maximum and the minimum values
	 * for normalization.
	 * 
	 * @param x
	 * @param y
	 * @return normalized distance
	 */
	public static double getDistance(Object x, Object y, Object max, Object min) {
		double distance = 0.0;
		double maxMinDistance = 0.0;
		if (x instanceof Integer) {
			Integer castedX = (Integer) x;
			Integer castedY = (Integer) y;
			Integer castedMax = (Integer) max;
			Integer castedMin = (Integer) min;
			distance = Math.abs(castedX - castedY);
			maxMinDistance = castedMax - castedMin;
		}
		
		if (x instanceof Double) {
			Double castedX = (Double) x;
			Double castedY = (Double) y;
			Double castedMax = (Double) max;
			Double castedMin = (Double) min;
			distance = Math.abs(castedX - castedY);
			maxMinDistance = castedMax - castedMin;
		}
		
		if (x instanceof Date) {
			Date castedX = (Date) x;
			Date castedY = (Date) y;
			Date castedMax = (Date) max;
			Date castedMin = (Date) min;
	        distance = Math.abs(
	        		Utils.getMinutesFromDate(castedX) - Utils.getMinutesFromDate(castedY));
	        		
			maxMinDistance =
					Utils.getMinutesFromDate(castedMax) - Utils.getMinutesFromDate(castedMin);
		}
		
		if (maxMinDistance == 0.0) {
			return 1.0;
		}
		
		return (distance / maxMinDistance);
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
	public static double getSimilarity(Object x, Object y, Object max, Object min) {
		return 1 - getDistance(x, y, max, min);
	}
}
