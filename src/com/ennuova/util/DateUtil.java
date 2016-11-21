package com.ennuova.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
/**
 * @author jimmy(王志明)
 *2016年4月26日
 */
public class DateUtil {
	
	private static final String DEFAULT_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
	public static void main(String[] args) {
//		List<String> list = new ArrayList<String>();
//		list.add("2013-02-01");
//		list.add("2012-05-11");
//		System.out.println(getAvgAge(list));
//		Date now = new Date();
//		Date date = DateUtil.getDateByAddDay(now,NumberUtil.parseInt("-7"));
//		System.out.println(DateUtil.DateToString(date, DEFAULT_TIME_FORMAT));
//		List<String> list = getYearMonth();
//		System.err.println(list);
		
	}
	
	/**
	 * 计算平均年龄
	 * @param birthdays
	 * @return
	 */
	public static String getAvgAge(List<String> birthdays){
		String avgAge = "";
		try{
			if(birthdays!=null && birthdays.size()>0){
				int count = birthdays.size();
				int totalMargin = 0;
				String now = getCrruentTime("yyyy-MM-dd");
				for(String birthday : birthdays){
					int margin = getDayMargin(birthday, now);
					totalMargin += margin;
				}
				int avgMargin = totalMargin/count;
				if(avgMargin/365>0){
					avgAge = avgMargin/365 + " years ";
					int m = avgMargin%365;
					if(m/30>0){
						avgAge += m/30 + " months";
					}else{
						avgAge += "1 months";
					}
				}else if(avgMargin/365==0 && avgMargin/30>0){
					avgAge = avgMargin/30 + " months";
				}else{
					avgAge = "1 months";
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return avgAge;
	}
	
	/**
	 * 根据生日计算年龄
	 * @param birthday
	 * @return
	 */
	public static String getAge(String birthday){
		String age = "";
		try{
			String now = getCrruentTime("yyyy-MM-dd");
			int margin = getDayMargin(birthday, now);
			if(margin/(365*2)>0){
				age = ">2yr";
			}else if(margin/(365*2)==0 && margin/365>0){
				age = "1yr-2yr";
			}else if(margin/365==0 && margin/180>0){
				age = "6m-1yr";
			}else{
				age = "1-6months";
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return age;
	}
	
	public static List<String> getAgeArray(){
		List<String> list = new ArrayList<String>();
		list.add("1-6months");
		list.add("6m-1yr");
		list.add("1yr-2yr");
		list.add(">2yr");
		return list;
	}
	
	/**
	 * 取得往前12个月到当前时间的年月,不包括当月
	 * @return
	 */
	public static List<String> getYearMonth(){
		List<String> yearMonth = new ArrayList<String>();
		try{
			String yearNow = addAndMinusYear(getCrruentTime(),-1);
			for(int i = 0;i<12;i++){
				String m = DateToString(StringToDate(addAndMinusMonth(yearNow,i),"yyyy-MM-dd HH:mm:ss"),"yyyy-MM");
				yearMonth.add(m);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return yearMonth;
	}
	
	/**
	 * 两个日期比较
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static boolean compareDate(Object obj1,Object obj2){
		if(obj1==null || obj2==null){
			return false;
		}else{
			Date date1 = StringToDate(obj1,"yyyy-MM-dd");
			Date date2 = StringToDate(obj2,"yyyy-MM-dd");
			if(date1.after(date2)){
				return true;
			}else{
				return false;
			}
		}
	}
	/**
	 * 两个时间比较
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static boolean compareTime(Object obj1,Object obj2){
		if(obj1==null || obj2==null){
			return false;
		}else{
			Date date1 = StringToDate(obj1,"yyyy-MM-dd HH:mm:ss");
			Date date2 = StringToDate(obj2,"yyyy-MM-dd HH:mm:ss");
			if(date1.after(date2)){
				return true;
			}else{
				return false;
			}
		}
	}
	/**
	 * 时间转换String
	 * @param date
	 * @param format
	 * @return
	 */
	public static String DateToString(Date date,String format){
		if(date==null)
			return "";
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(date);
	}
	/**
	 * String 转换Date
	 * @param obj
	 * @param format
	 * @return
	 */
	public static Date StringToDate(Object obj,String format){
		if(obj==null)
			return null;
		String date=obj.toString().trim();
		if(StringUtil.isEmpty(date))
			return null;
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		try {
			return sdf.parse(date.trim());
		} catch (ParseException e) {
			return null;
		}
	}
	/**
	 * String 转换Date 如果String 空 默认取当前时间
	 * @param obj
	 * @param format
	 * @return
	 */
	public static Date StringToDate2_NewDate(Object obj,String format){
		if(obj==null)
			return new Date();
		String date=obj.toString().trim();
		if(StringUtil.isEmpty(date))
			return new Date();
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		try {
			
			return sdf.parse(date.trim());
		} catch (ParseException e) {
			return new Date();
		}
	}

	/**
	 *  获取当前时间,请注意format 24小时制 HH:mm:ss
	 * @param format
	 * @return
	 */
	public static String getCurrentTimeMillis(String format){
		  Date nowTime = new Date(System.currentTimeMillis());
		  SimpleDateFormat sdFormatter = new SimpleDateFormat(format);
		  String retStrFormatNowDate = sdFormatter.format(nowTime);
		  return retStrFormatNowDate;
	}
	
	/**
	 *  获取当前时间,默认 24小时制 HH:mm:ss 
	 * @return
	 */
	public static String getCurrentTimeMillis(){
		  Date nowTime = new Date(System.currentTimeMillis());
		  SimpleDateFormat sdFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		  String retStrFormatNowDate = sdFormatter.format(nowTime);
		  return retStrFormatNowDate;
	}
	
	/**
	 * 获取当前时间 
	 * @return
	 */
	public static Date getCurrentTime(){
		  Date nowTime = new Date(System.currentTimeMillis());
		  return nowTime;
	}
	
	/**
	 * 获取当前日期
	 * @return
	 */
	public static Date getCurrentDate(){
		return new Date();
	}
	
	/**
	 * 把时间化为秒   HH:MM:SS
	 * @param obj
	 * @return
	 */
	public static int DateToSecond(Object obj){
		if(obj==null)
			return 0;
		String date = obj.toString().trim();
		if(StringUtil.isEmpty(date))
			return 0;
		int hour = Integer.valueOf(date.split(":")[0]);
		int minute = Integer.valueOf(date.split(":")[1]);
		int second = Integer.valueOf(date.split(":")[2]);
		return hour*60*60+minute*60+second;
	}
	public static String SecondToDate(int seconds){
		if(seconds==0){
			return "00:00:00:00";
		}else{
			int day = seconds/(60*60*60);
			int hour = (seconds%(60*60*60))/(60*60);
			int minute = ((seconds%(60*60*60))%(60*60))/60;
			int second = ((seconds%(60*60*60))%(60*60))%60;
			return NumberUtil.pad(StringUtil.ToString(day), 2)+":"+
					NumberUtil.pad(StringUtil.ToString(hour), 2)+":"+
					NumberUtil.pad(StringUtil.ToString(minute), 2)+":"+
					NumberUtil.pad(StringUtil.ToString(second), 2);
		}
	}
	
	//-----------------------------------------------------------------------------------------------------------------------
	/**
	 * 取得两个日期相差的天数
	 * 
	 * @param beginDate
	 * @param endDate
	 * @return
	 */
	public static int getDayMargin(Date beginDate, Date endDate) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(beginDate);
		calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
				calendar.get(Calendar.DAY_OF_MONTH), 1, 0, 0);
		long beginTime = calendar.getTime().getTime();
		calendar.setTime(endDate);
		calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
				calendar.get(Calendar.DAY_OF_MONTH), 1, 0, 0);
		long endTime = calendar.getTime().getTime();
		return (int) ((endTime - beginTime) / (1000 * 60 * 60 * 24));
	}
	/**
	 * 取得两个日期相差的天数
	 * 
	 * @param beginDate
	 * @param endDate
	 * @return
	 */
	public static int getDayMargin(String begin, String end) {
		Date beginDate = StringToDate(begin, "yyyy-MM-dd");
		Date endDate = StringToDate(end, "yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(beginDate);
		calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
				calendar.get(Calendar.DAY_OF_MONTH), 1, 0, 0);
		long beginTime = calendar.getTime().getTime();
		calendar.setTime(endDate);
		calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
				calendar.get(Calendar.DAY_OF_MONTH), 1, 0, 0);
		long endTime = calendar.getTime().getTime();
		return (int) ((endTime - beginTime) / (1000 * 60 * 60 * 24));
	}

	/**
	 * 计算第二个时间与第一个时间相差分钟数
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static int getMinuteMargin(Date beginDate, Date endDate) {
		long result = (endDate.getTime() / (60 * 1000))
				- (beginDate.getTime() / (60 * 1000));
		return (int) result;
	}

	/**
	 * 判断一个时间是否在某一个时间段内
	 * 
	 * @param targetDate
	 *            目标时间
	 * @param beginDate
	 *            时间段的开始时间
	 * @param endDate
	 *            时间段的结束时间
	 * @return
	 */
	public static boolean isBetween(Date targetDate, Date beginDate,
			Date endDate) {
		boolean temp = false;
		long target = targetDate.getTime();
		long begin = beginDate.getTime();
		long end = endDate.getTime();
		if (target >= begin && target <= end) {// 需要考虑在考勤中 时间 分钟以后的秒跟毫秒忽略
			temp = true;
		}
		return temp;
	}

	/**
	 * 判断一个时间是否在另外两个时间分别加上几分钟后的时间段内
	 * 
	 * @param targetDate目标时间
	 * @param beginDate开始时间
	 * @param endDate结束时间
	 * @param start开始分钟数
	 * @param after结束分钟数
	 * @return
	 */
	public static boolean isBetween(Date targetDate, Date beginDate,
			Date endDate, int start, int after) {
		boolean temp = false;
		long target = targetDate.getTime();
		long begin = beginDate.getTime() + start * 60 * 1000;
		long end = endDate.getTime() + after * 60 * 1000;
		if (target >= begin && target <= end) {// 需要考虑在考勤中 时间 分钟以后的秒跟毫秒忽略
			temp = true;
		}
		return temp;
	}

	/**
	 * 判断两个日期是否为同一天
	 * 
	 * @param d1
	 *            日期一
	 * @param d2
	 *            日期二
	 * @return 同一天true，不是同一天false
	 */
	public static boolean isSameDay(Date d1, Date d2) {
		boolean result = false;
		Calendar c1 = Calendar.getInstance();
		c1.setTime(d1);
		Calendar c2 = Calendar.getInstance();
		c2.setTime(d2);
		if (c1.get(Calendar.YEAR) == c2.get(Calendar.YEAR)
				&& c1.get(Calendar.MONTH) == c2.get(Calendar.MONTH)
				&& c1.get(Calendar.DAY_OF_MONTH) == c2
						.get(Calendar.DAY_OF_MONTH)) {
			result = true;
		}
		return result;
	}

	public static String getHourMinute(Date date) {
		return new SimpleDateFormat("HH:mm").format(date);
	}

	/**
	 * 取得一个年月日组合的长度为8的字符串
	 * 
	 * @param date
	 * @return
	 */
	public static String getYMD(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		String id = calendar.get(Calendar.YEAR)
				+ isAddZero(calendar.get(Calendar.MONTH) + 1)
				+ isAddZero(calendar.get(Calendar.DAY_OF_MONTH));
		return id;
	}

	public static String isAddZero(int a) {
		String s = "";
		if (a < 10) {
			s = "0" + a;
		} else {
			s += a;
		}
		return s;

	}

	/**
	 * 取得一个时间所在月的第一天
	 * 
	 * @param date
	 * @return
	 */
	public static String getCurrentMondthOne(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.YEAR) + "-"
				+ (calendar.get(Calendar.MONTH) + 1) + "-01";
	}

	/**
	 * 根据一个标准的字符串获得一个时间
	 * 
	 * @param str
	 * @return
	 */
	public static Date getDateByStr(String str) {
		try {
			return new SimpleDateFormat(DEFAULT_TIME_FORMAT).parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 获取当前年
	 * @return
	 * @throws ParseException
	 */
	public static int getCurrentYear() throws ParseException{
		Date d = new Date(System.currentTimeMillis());
		Calendar c = Calendar.getInstance();
		c.setTime(d);
		int year = c.get(Calendar.YEAR);
//		int month = c.get(Calendar.MONTH) + 1;
//		int day = c.get(Calendar.DAY_OF_MONTH);
		return year;
	}
	/**
	 * 获取当前月
	 * @return
	 * @throws ParseException
	 */
	public static int getCurrentMonth() throws ParseException{
		Date d = new Date(System.currentTimeMillis());
		Calendar c = Calendar.getInstance();
		c.setTime(d);
//		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH) + 1;
//		int day = c.get(Calendar.DAY_OF_MONTH);
		return month;
	}
	
	public static Date getDateByStr(String str, String format) {
		try {
			return new SimpleDateFormat(format).parse(str);
		} catch (ParseException e) {
		}
		return null;
	}
	
	public static Timestamp getTimestampByStr(String str, String format) {
		try {
			Date date=new SimpleDateFormat(format).parse(str);
			return new Timestamp(date.getTime());
		} catch (ParseException e) {
		}
		return null;
	}
	
	public static String getCrruentTime() {
		return new SimpleDateFormat(DEFAULT_TIME_FORMAT).format(new Date());
	}

	
	public static String getCrruentTime(String format) {
		return new SimpleDateFormat(format).format(new Date());
	}

	public static Timestamp getCrruentTimestamp() {
		return new Timestamp(System.currentTimeMillis());
	}

	public static String getTime(Timestamp timestamp) {
		return new SimpleDateFormat(DEFAULT_TIME_FORMAT).format(timestamp);
	}

	public static String getTime(Timestamp timestamp, String format) {
		return new SimpleDateFormat(format).format(timestamp);
	}

	/**
	 * 给当期指定的日期加上指定的天数
	 * @param now 指定的日期
	 * @param day 添加的天数
	 * @return
	 */
	public static Date getDateByAddDay(Date now, int day)
	{
		return setDate(now, Calendar.DAY_OF_MONTH,day);
	}
	
	/**
	 * 给当期指定的日期加上指定的天数
	 * @param now 指定的日期
	 * @param month 添加的月份数
	 * @return
	 */
	public static Date getDateByAddMonth(Date now, int month)
	{
		return setDate(now, Calendar.MONTH,month);
	}
	
	/**
	 * 给当期指定的日期加上指定的月份
	 * @param now  指定的日期
	 * @param field  日期字段：YEAR，MONTH， DAY_OF_MONTH  WEEK_OF_MONTH， DAY_OF_WEEK， DAY_OF_WEEK_IN_MONTH， WEEK_OF_YEAR
					   时间字段：HOUR_OF_DAY AM_PM + HOUR
	 * @param value 加减的量制
	 * @return
	 */
	private static Date setDate(Date now , int field, int value)
	{
		if(now==null) return null;
		Calendar cad= Calendar.getInstance();
		cad.setTime(now);
		cad.add(Calendar.DAY_OF_MONTH, value);
		return cad.getTime();
	}
	
	/**
	 * 年份加减
	 * @param now
	 * @param value  正数为加   负数为减  
	 * @return
	 */
	public static String addAndMinusYear(String now,int value){
		Calendar calendar=Calendar.getInstance(); 
		Date nowDate = StringToDate(now, "yyyy-MM-dd HH:mm:ss");
		calendar.setTime(nowDate);
		calendar.set(Calendar.YEAR,calendar.get(Calendar.YEAR)+value);
		return DateToString(calendar.getTime(), "yyyy-MM-dd HH:mm:ss");
	}
	/**
	 * 月份加减
	 * @param now
	 * @param value  正数为加   负数为减  
	 * @return
	 */
	public static String addAndMinusMonth(String now,int value){
		Calendar calendar=Calendar.getInstance(); 
		Date nowDate = StringToDate(now, "yyyy-MM-dd HH:mm:ss");
		calendar.setTime(nowDate);
		calendar.set(Calendar.MONTH,calendar.get(Calendar.MONTH)+value);
		return DateToString(calendar.getTime(), "yyyy-MM-dd HH:mm:ss");
	}
	/**
	 * 日期加减
	 * @param now
	 * @param value  正数为加   负数为减  
	 * @return
	 */
	public static String addAndMinusDate(String now,int value){
		Calendar calendar=Calendar.getInstance(); 
		Date nowDate = StringToDate(now, "yyyy-MM-dd HH:mm:ss");
		calendar.setTime(nowDate);
		calendar.set(Calendar.DAY_OF_MONTH,calendar.get(Calendar.DAY_OF_MONTH)+value);
		return DateToString(calendar.getTime(), "yyyy-MM-dd HH:mm:ss");
	}
	
	/**
	 * 当前时间加减分钟数
	 * @param value
	 * @return
	 */
	public static String addAndMinusMinute(int value){
		Calendar calendar=Calendar.getInstance(); 
		Date nowDate = getCurrentTime();
		calendar.setTime(nowDate);
		calendar.set(Calendar.MINUTE,calendar.get(Calendar.MINUTE)+value);
		return DateToString(calendar.getTime(), "yyyy-MM-dd HH:mm:ss");
	}
	
	
//	public static void main(String[] args) {
//		Date now = new Date();
//	      Calendar cal = Calendar.getInstance();
//	     
//	      DateFormat d1 = DateFormat.getDateInstance(); //默认语言（汉语）下的默认风格（MEDIUM风格，比如：2008-6-16 20:54:53）
//	      String str1 = d1.format(now);
//	      DateFormat d2 = DateFormat.getDateTimeInstance();
//	      String str2 = d2.format(now);
//	      DateFormat d3 = DateFormat.getTimeInstance();
//	      String str3 = d3.format(now);
//	      DateFormat d4 = DateFormat.getInstance(); //使用SHORT风格显示日期和时间
//	      String str4 = d4.format(now);
//
//	      DateFormat d5 = DateFormat.getDateTimeInstance(DateFormat.FULL,DateFormat.FULL); //显示日期，周，时间（精确到秒）
//	      String str5 = d5.format(now);
//	      DateFormat d6 = DateFormat.getDateTimeInstance(DateFormat.LONG,DateFormat.LONG); //显示日期。时间（精确到秒）
//	      String str6 = d6.format(now);
//	      DateFormat d7 = DateFormat.getDateTimeInstance(DateFormat.SHORT,DateFormat.SHORT); //显示日期，时间（精确到分）
//	      String str7 = d7.format(now);
//	      DateFormat d8 = DateFormat.getDateTimeInstance(DateFormat.MEDIUM,DateFormat.MEDIUM); //显示日期，时间（精确到分）
//	      String str8 = d8.format(now);//与SHORT风格相比，这种方式最好用
//
//	      System.out.println("用Date方式显示时间: " + now);//此方法显示的结果和Calendar.getInstance().getTime()一样
//	     
//	      System.out.println("用DateFormat.getDateInstance()格式化时间后为：" + str1);
//	      System.out.println("用DateFormat.getDateTimeInstance()格式化时间后为：" + str2);
//	      System.out.println("用DateFormat.getTimeInstance()格式化时间后为：" + str3);
//	      System.out.println("用DateFormat.getInstance()格式化时间后为：" + str4);
//	     
//	      System.out.println("用DateFormat.getDateTimeInstance(DateFormat.FULL,DateFormat.FULL)格式化时间后为：" + str5);
//	      System.out.println("用DateFormat.getDateTimeInstance(DateFormat.LONG,DateFormat.LONG)格式化时间后为：" + str6);
//	      System.out.println("用DateFormat.getDateTimeInstance(DateFormat.SHORT,DateFormat.SHORT)格式化时间后为：" + str7);
//	      System.out.println("用DateFormat.getDateTimeInstance(DateFormat.MEDIUM,DateFormat.MEDIUM)格式化时间后为：" + str8);
//	}
	
}
