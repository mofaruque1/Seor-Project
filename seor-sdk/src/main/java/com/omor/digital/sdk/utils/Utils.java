package com.omor.digital.sdk.utils;

public class Utils {

	/**
	 * Check if the object is NULL
	 * 
	 * @param o
	 * @return
	 */
	public static boolean checkIfNullObject(Object o) {
		return o == null;
	}

	/**
	 * Throw exception if the object is NULL
	 * 
	 * @param o
	 * @param exceptionMessage
	 */
	public static void throwIfNullObject(Object o, String exceptionMessage) {
		if (o == null) {
			throw new IllegalArgumentException(exceptionMessage);
		}

	}

}
