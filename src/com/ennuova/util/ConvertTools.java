package com.ennuova.util;

/**
 * 对象转换工具
 * @author 李智辉
 * @version 1.0
 * @since 2015/02/26
 */
public class ConvertTools {
	
	/**
	 * String类型转换成int型数据<br>
	 * @param str 字符数据
	 * @return int-整形值
	 */
	public static int stringToInt(String str) {
		int value = 0;
		try {
			value = Integer.valueOf(str);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return value;
	}
	/**
	 * String类型转换成long型数据<br>
	 * @param str 字符串数据
	 * @return long-长整型值
	 */
	public static long stringToLong(String str) {
		long value = 0;
		try {
			value = Long.valueOf(str);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return value;
	}
	/**
	 * int类型转换成String类型数据<br>
	 * @param a 整形值
	 * @return str-字符串
	 */
	public static String intToString(int a) {
		return String.valueOf(a);
	}
	/**
	 * long类型转换成String类型数据<br>
	 * @param a 长整型值
	 * @return str-字符串
	 */
	public static String longToString(long a) {
		return String.valueOf(a);
	}
	/**
	 * String类型转换成float类型<br>
	 * @param a String类型值
	 * @return str-字符串
	 */
	public static float stringToFloat(String a) {
		float value = 0f;
		try {
			value = Float.valueOf(a);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return value;
	}
	/**
	 * float类型数据转换成String类型<br>
	 * @param a float类型值
	 * @return String-字符串
	 */
	public static String floatToString(float a) {
		return String.valueOf(a);
	}
	/**
	 * double类型数据转String类型
	 * @param a double类型值
	 * @return String-字符串
	 */
	public static String doubleToString(double a) {
		return String.valueOf(a);
	}
	
	public static float doubleToFloat(double a) {
		return (float)a;
	}
	
	/**
	 * String类型数据转double类型
	 * @param str 数据的字符串标示
	 * @return double
	 */
	public static double stringToDouble(String str) {
		double value = 0d;
		try {
			value = Double.valueOf(str);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return value;
	}
}
