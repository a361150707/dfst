package com.ennuova.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;
/**
 * @author jimmy(王志明)
 *2016年4月26日
 */
public class NumberUtil {
	
	public static void main(String[] args) {
		System.out.println(1.2>2.1);
		System.out.println(getDistance(38.124342, 114.432244, 39.736438, 115.384758));
		
		System.out.println(formatNatotion("1.223343222E7"));
	}
	
	private static double EARTH_RADIUS = 6378.137;
	private static double rad(double d){
		return d * Math.PI / 180.0;
	}
	
	/**
	 * 格式化科学计数法
	 * @param obj
	 * @return
	 */
	public static String formatNatotion(Object obj){
		String n = "0";
		try{
			if(StringUtil.isNotEmpty(obj)){
				BigDecimal b = new BigDecimal(StringUtil.ToString(obj));
				n = b.toPlainString();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return n;
	}
	
	/**
	 * 格式化尾数   不足补0
	 * @param num
	 * @param n
	 * @return
	 */
	public static String pad(String num,int n){
		int len = num.length();
		while(len<n){
			num = "0" + num;
			len++;
		}
		return num;
	}
	
	/**
	 * 格式化数字类型为：BigDecimal
	 */
	public static BigDecimal parseBigDecimal(Object obj){
		return new BigDecimal(parseInt(obj));
	}
	
	/**
	 * 两个double类型相加
	 */
	public static double addDouble(Object obj1,Object obj2){
		return (new BigDecimal(StringUtil.ToString(parseDouble(obj1)))).add(new BigDecimal(StringUtil.ToString(parseDouble(obj2)))).doubleValue();
	}
	
	/**
	 * 两个double类型相减
	 */
	public static double minusDouble(Object obj1,Object obj2){
		return (new BigDecimal(StringUtil.ToString(parseDouble(obj1)))).subtract(new BigDecimal(StringUtil.ToString(parseDouble(obj2)))).doubleValue();
	}
	
	/**
	 * 两个double类型相乘
	 */
	public static double multiplyDouble(Object obj1,Object obj2){
		return (new BigDecimal(StringUtil.ToString(parseDouble(obj1)))).multiply(new BigDecimal(StringUtil.ToString(parseDouble(obj2)))).doubleValue();
	} 
	
	/**
	 * 两个double类型相除
	 */
	public static double divideDouble(Object obj1,Object obj2){
		return (new BigDecimal(StringUtil.ToString(parseDouble(obj1)))).divide(new BigDecimal(StringUtil.ToString(parseDouble(obj2)))).doubleValue();
	}
	
	/**
	 * 取百分比值
	 * @param obj1
	 * @param obj2
	 * @return
	 */
	public static int percent(Object obj1,Object obj2){
		int p = 0;
		try{
			BigDecimal b1 = new BigDecimal(StringUtil.ToString(obj1));
			BigDecimal b2 = new BigDecimal(StringUtil.ToString(obj2));
			BigDecimal c = b1.multiply(new BigDecimal(100));
			p = c.divide(b2,BigDecimal.ROUND_HALF_UP).intValue();
		}catch(Exception e){
			e.printStackTrace();
		}
		return p;
	}

	/**
	 * 格式化数字类型为：Int
	 */
	public static int parseInt(Object obj){
		if(obj==null)
			return 0;
		String temp = obj.toString().trim();
		if(StringUtil.isEmpty(temp))
			return 0;
		try{
			int num = Integer.parseInt(temp);
			return num;
		}catch(Exception ex){
//			ex.printStackTrace();
			return 0;
		}
	}
	
	/**
	 * 格式化数字类型为：long
	 */
	public static long parseLong(Object obj){
		if(obj==null)
			return 0L;
		String temp = obj.toString().trim();
		if(StringUtil.isEmpty(temp))
			return 0L;
		try{
			long num = Long.parseLong(temp);
			return num;
		}catch(Exception ex){
//			ex.printStackTrace();
			return 0L;
		}
	}
	
	/**
	 * 格式化数字类型为：double
	 */
	public static double parseDouble(Object obj){
		if(obj==null)
			return 0l;
		String temp = obj.toString().trim();
		if(StringUtil.isEmpty(temp))
			return 0l;
		try{
			double num = Double.parseDouble(temp);
			return num;
		}catch(Exception ex){
//			ex.printStackTrace();
			return 0l;
		}
	}
	/**
	 *  格式化金额 为 0.00
	 */
	public static String formatPrice(Object p){
		if(p==null)
			return new java.text.DecimalFormat("0.00").format(0l);
		double d = parseDouble(p);
		if(d==0l)
			return "0.00";
		return new java.text.DecimalFormat("0.00").format(d);
	}
	
	public static double formatPriceForDouble(Object p){
		return parseDouble(formatPrice(p));
	}
	
	/**
	 * 把分的金额格式化为元
	 * @param obj
	 * @return
	 */
	public static String formatToMoney(Object obj) {
		DecimalFormat df = new DecimalFormat("0.00");
	    try {
	    	if(obj==null || "".equals(obj)){
	    		return "0.00";
	    	}else{
	    		return df.format(divideDouble(obj,100));
	    	}
	    } catch (Exception e) {
	        return "0.00";
	    }
	}
	
	/**
	 * 按格式把分的金额格式化为元
	 * @param obj
	 * @param format
	 * @return
	 */
	public static String formatToMoney(Object obj,String format) {
		DecimalFormat df = new DecimalFormat(format);
	    try {
	    	if(obj==null || "".equals(obj)){
	    		return "0.00";
	    	}else{
	    		return df.format(divideDouble(obj,100));
	    	}
	    } catch (Exception e) {
	        return "0.00";
	    }
	}
	
	/**
	 * 把元的金额格式化为分
	 * @param obj
	 * @return
	 */
	public static String formatMoneyToCent(Object obj){
		try{
			if(obj==null || "".equals(obj)){
				return "0";
			}else{
				
				return new BigDecimal(obj.toString()).multiply(new BigDecimal(100)).toBigInteger().toString();
			}
		}catch(Exception e){
			e.printStackTrace();
			return "0";
		}
	}
	
	/**
	 * 按位数格式化数字,不足前面补0
	 * @param obj
	 * @param digit
	 * @return
	 */
	public static String formatNumberToDigit(Object obj,int digit) {
		try{
			String format = "";
			if(digit<1){
				format = "0";
			}
			for(int i=0;i<digit;i++){
				format += "0";
			}
			DecimalFormat nf = new DecimalFormat(format);
			if(obj==null || "".equals(obj)){
				return nf.format(0);
			}else{
				return nf.format(parseLong(obj));
			}
		}catch(Exception e){
			e.printStackTrace();
			return "0";
		}
	}
	
	/**
	 * 按格式格式化金额
	 * @param obj
	 * @param format
	 * @return
	 */
	public static String formatMoney(Object obj,String format) {
		DecimalFormat nf = new DecimalFormat(format);
		try {
			if(obj==null || "".equals(obj)){
				return "0.00";
			}else{
				return nf.format(new BigDecimal(obj.toString()).doubleValue());
			}
		} catch (Exception e) {
			return "0.00";
		}
	}
	
	/**
	 * 计算两个地点间距离
	 * @param lat1
	 * @param lng1
	 * @param lat2
	 * @param lng2
	 * @return
	 */
	public static double getDistance(double lat1, double lng1, 
			double lat2, double lng2){
		double radLat1 = rad(lat1);
		double radLat2 = rad(lat2);
		double a = radLat1 - radLat2;
		double b = rad(lng1) - rad(lng2);
		double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2) + Math.cos(radLat1) * Math.cos(radLat2) * Math.pow(Math.sin(b / 2), 2)));
		s = s * EARTH_RADIUS;//km
		s = Math.round(s * 1000);//m
		DecimalFormat df = new DecimalFormat("0.00");
		s = parseDouble(df.format(s));
		return s;
	}
}