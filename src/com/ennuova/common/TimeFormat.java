package com.ennuova.common;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 时间格式转换类
 * 
 * @author lin
 *
 */
public class TimeFormat {
	
	public static void main(String[] args) {
		Timestamp tim = new Timestamp(System.currentTimeMillis());
		System.out.println(timestampChangeString(tim, null));
	}
	
	/**
	 * String 类型转换成Date 类型，时间值为空时，返回当前时间，时间格式为空时，返回yyyy-MM-dd HH:mm:ss格式的时间值
	 * @param dateStr 时间值
	 * @param dateType 时间格式
	 * @return Date
	 */
	public static Date stringChangeDate(String date,String dateType) {
		if(date != null && !date.isEmpty()){
			dateType = dateType(date.length(), dateType);
			Date currentTime = new Date();
			DateFormat formatter = new SimpleDateFormat(dateType);
			try {
				currentTime = formatter.parse(date);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return currentTime;
		} else {
			return new Date();
		}
	}
	
	/**
	 * Date 类型转换成 String 类型，时间值为空时，返回当前时间,时间格式为空时，返回yyyy-MM-dd HH:mm:ss格式的时间值
	 * @param date
	 * @param dateType
	 * @return
	 */
	public static String dateChangeString(Date date,String dateType) {
		if(date == null){
			date = new Date();			
		}
		if(dateType == null || dateType.isEmpty()){
			dateType = "yyyy-MM-dd HH:mm:ss";
		}
		
		SimpleDateFormat df = new SimpleDateFormat(dateType);
		String now = df.format(date);
		
		return now;
	}
	
	/**
	 * Date 类型转成 Timestamp 类型，时间值为空时，返回当前时间,时间格式为空时，返回yyyy-MM-dd HH:mm:ss格式的时间值
	 * 
	 * @param date
	 * @param dateType
	 * @return
	 */
	public static Timestamp dateChangeTimestamp(Date date,String dateType) {
		if(date == null){
			date = new Date();			
		}
		if(dateType == null || dateType.isEmpty()){
			dateType = "yyyy-MM-dd HH:mm:ss";
		}
		
		SimpleDateFormat df = new SimpleDateFormat(dateType);
		String now = df.format(date);
		
		return Timestamp.valueOf(now);
	}
	
	/**
	 * Timestamp 类型转成 Date 类型，时间值为空时，返回当前时间,时间格式为空时，返回yyyy-MM-dd HH:mm:ss格式的时间值
	 * 
	 * @param timestamp
	 * @param dateType
	 * @return
	 */
	public static Date timestampChangeDate(Timestamp timestamp, String dateType) {
		if(timestamp == null){
			timestamp = new Timestamp(System.currentTimeMillis());
		}
		if(dateType == null || dateType.isEmpty()){
			dateType = "yyyy-MM-dd HH:mm:ss";
		}
		
		try {
			DateFormat df = new SimpleDateFormat(dateType);
			String now = df.format(timestamp);
			
			return df.parse(now);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return new Date();
	}
	
	/**
	 * String 类型转换成Timestamp 类型，时间值为空时，返回当前时间，时间格式为空时，返回yyyy-MM-dd HH:mm:ss格式的时间值
	 * 
	 * @param date
	 * @param dateType
	 * @return Timestamp
	 */
	public static Timestamp stringChangeTimestamp(String date) {
		if(date != null && !date.isEmpty()){
			
			return Timestamp.valueOf(date);
		} else {
			
			return new Timestamp(System.currentTimeMillis());
		}
	}
	
	/**
	 * Timestamp 类型转换成String 类型，时间值为空时，返回当前时间，时间格式为空或者不符合长度时，返回yyyy-MM-dd HH:mm:ss格式的时间值
	 * 
	 * @param date
	 * @param dateType
	 * @return String
	 */
	public static String timestampChangeString(Timestamp date, String dateType) {
		if(date == null){
			date = new Timestamp(System.currentTimeMillis());
		} 
		if(dateType == null || dateType.isEmpty() || dateType.length() < 19){
			dateType = "yyyy-MM-dd HH:mm:ss";
		}
		
		DateFormat df = new SimpleDateFormat(dateType);
		String now = df.format(date);
		
		return now;
	}
	
	/**
	 * 判断没有设置时间格式时，有无需要包含时分秒
	 * 
	 * @param dateLength
	 * @param dateType
	 * @return String
	 */
	public static String dateType(int dateLength, String dateType) {
		if(dateType == null || dateType.isEmpty()) {
			if(dateLength > 10) {
				dateType = "yyyy-MM-dd HH:mm:ss";
			} else{
				dateType = "yyyy-MM-dd";
			}
		}

		return dateType;
	}
}