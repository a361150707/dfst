package com.ennuova.obd.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.binary.Base64;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * 常用工具类<br>
 * @author henrybit
 * @version 1.0
 * @since 2015/03/03
 */
public class Tools {
	private final static char[] randomChars = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z','1','2','3','4','5','6','7','8','9','_','.','|','-'};
	/**
	 * 返回网站的绝对URL地址<br>
	 * @param request HTTP请求
	 * @return String-url地址
	 */
	public static String getBaseUrl(HttpServletRequest request) {
		StringBuilder baseUrl = new StringBuilder();
		baseUrl.append(request.getScheme()).append("://").append(request.getServerName()).append(":")
		.append(request.getServerPort()).append(request.getContextPath());
		return baseUrl.toString();
	}
	/**
	 * 解析Base64编码数据<br>
	 * @param decodeContent 待解析内容
	 * @return String-解析后的结果数据
	 */
	public static String decodeBase64(String decodeContent) {
		String decodeStr = "";
		try {
			byte[] decodeBytes = Base64.decodeBase64(decodeContent.getBytes());
			decodeStr = new String(decodeBytes);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return decodeStr;
	}
	
	/**
	 * 解析Base64编码数据<br>
	 * @param decodeContent 待解析内容
	 * @param coder 字符串编码格式
	 * @return String-解析后的结果数据
	 */
	public static String decodeBase64(String decodeContent, String coder) {
		String decodeStr = "";
		try {
			byte[] decodeBytes = Base64.decodeBase64(decodeContent.getBytes());//decodeBase64
			decodeStr = new String(decodeBytes, coder);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return decodeStr;
	}
	/**
	 * Base64编码数据<br>
	 * @param encodeContent 待编码内容
	 * @return String-编码后的结果数据
	 */
	public static String encodeBase64(String encodeContent) {
		String encodeStr = "";
		try {
			byte[] encodeBytes = Base64.encodeBase64URLSafe(encodeContent.getBytes());//encodeBase64URLSafe
			encodeStr = new String(encodeBytes);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return encodeStr;
	}
	
	/**
	 * Base64编码数据<br>
	 * @param encodeContent 待编码内容
	 * @return String-编码后的结果数据
	 */
	public static String encodeBase64URLSafe(String encodeContent) {
		String encodeStr = "";
		try {
			byte[] encodeBytes = Base64.encodeBase64(encodeContent.getBytes());//encodeBase64URLSafe
			encodeStr = new String(encodeBytes);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return encodeStr;
	}
	/**
	 * 适用于form表单获取真实中文数据<br>
	 * @param origin 源数据
	 * @param charset 编码格式
	 * @return String-目标数据
	 */
	public static String getRealString(String origin, String charset) {
		String dest = origin;
		try {
			byte[] bytes = origin.getBytes("ISO-8859-1");
			dest = new String(bytes,charset);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dest;
	}
	/**
	 * 获取IP地址
	 * @param request
	 * @return String-IP地址
	 */
	public static String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip==null || ip.length()==0 || "unknown".equalsIgnoreCase(ip))  {
			ip = request.getHeader( "Proxy-Client-IP" );
		}
		if (ip==null || ip.length()==0 || "unknown".equalsIgnoreCase(ip))  {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip==null || ip.length()==0 || "unknown".equalsIgnoreCase(ip))  {
			ip = request.getRemoteAddr();
		}
		return ip;
	}
	/**
	 * 转义一些非法字符，使得可以插入数据库中
	 * @param str 待转义字符
	 * @return string-转义后字符
	 */
	public static String dbString(String str) {
		return str;
	}
	
	/**
     * 创建一个全局唯一的用户ID
     * @return String
     */
    public static String createRandomChars() {
        StringBuilder randoms = new StringBuilder();
        for(int i=0; i<10; i++) {
            Random random = new Random();
            int position = Math.abs(random.nextInt()) % randomChars.length;
            randoms.append(randomChars[position]);
        }
        return randoms.toString();
    }
    
    /**生成随机数
	 * @param length-随机数的长度
	 * @return 生成的随机数
	 */
	public static String getRandomChar(int length) {
		char[] chr = {'0','1','2','3','4','5','6','7','8','9',
				'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
				'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
		Random random = new Random();
		StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < length; i++) {
		buffer.append(chr[random.nextInt(62)]);
		}
		return buffer.toString();
		}
	
	/**生成订单编号 indentNumber 在a-z A-Z取一个长度为5的随机字符串 +系统当前时间毫秒数
	 * @param random
	 * @return indentNumber订单编号
	 */
	public static String getIndentNumber(){
		int lengthPrefix = 5;
		String prefix = Tools.getRandomChar(lengthPrefix);
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddhhmmss");
		String indentTime = dateFormat.format(new Date(System.currentTimeMillis()));
		String indentNumber = "itn"+indentTime+prefix;
		
		return indentNumber;
	}
	
	/**生成配送单编号 deliveryNumber
	 * @param random
	 * @return deliveryNumber配送单编号
	 */
	public static String getDeliveryNumber(){
		int lengthPrefix = 5;
		String prefix = Tools.getRandomChar(lengthPrefix);
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddhhmmss");
		String indentTime = dateFormat.format(new Date(System.currentTimeMillis()));
		String deliveryNumber = "den"+indentTime+prefix;
		
		return deliveryNumber;
	}
	
	/**
	 * 获取JSON中的String值
	 * @param object
	 * @param key
	 * @return String
	 */
	public static String getJsonS(JSONObject object, String key) {
		try {
			return object.getString(key);
		} catch (Exception e) {
		}
		return "";
	}
	
	/**
	 * 获取JSON中的Double值
	 * @param object
	 * @param key
	 * @return Double
	 */
	public static double getJsonD(JSONObject object, String key) {
		try {
			return object.getDouble(key);
		} catch (Exception e) {
		}
		return 0;
	}
	
	/**
	 * 获取JSON中的Int值
	 * @param object
	 * @param key
	 * @return int
	 */
	public static int getJsonI(JSONObject object, String key) {
		try {
			return object.getInt(key);
		} catch (Exception e) {
		}
		return 0;
	}
	
	/**
	 * 获取JSON中的long值
	 * @param object
	 * @param key
	 * @return long
	 */
	public static long getJSONL(JSONObject object, String key) {
		try {
			return object.getLong(key);
		} catch (Exception e) {
		}
		return 0;
	}
	
	/**
	 * 获取JSON的boolean值
	 * @param object
	 * @param key
	 * @return boolean
	 */
	public static boolean getJSONB(JSONObject object, String key) {
		try {
			return object.getBoolean(key);
		} catch (Exception e) {
		}
		return false;
	}
	
	/**
	 * 获取JSON的Array对象
	 * @param object
	 * @param key
	 * @return JSONArray
	 */
	public static JSONArray getJSONArray(JSONObject object, String key) {
		try {
			return object.getJSONArray(key);
		} catch (Exception e) {
		}
		return null;
	}
	
	/**
	 * 获取JSON的Object对象
	 * @param object
	 * @param key
	 * @return
	 */
	public static JSONObject getJSONObject(JSONObject object, String key) {
		try {
			return object.getJSONObject(key);
		} catch (Exception e) {
		}
		return null;
	}
	
	
    public static void main(String[] args) {
    	String str = "eyI2IjpbeyJpZCI6MTU1LCJ1c2VySWQiOjE5LCJpbmRlbnRJZCI6MTE0LCJzZWxsZXJJZCI6NiwicHJpY2UiOjQuMCwiZ29vZHNOdW0iOjEsInN0YXR1cyI6MSwiY3JlYXRlVGltZSI6Ikp1biAxMCwgMjAxNSAyOjQyOjM0IFBNIiwiZGF0YVN0YXR1cyI6MCwiZ29vZHNJZCI6MjEsImdvb2RzTmFtZSI6ItOq0fi_7M_fNDAwbWwifSx7ImlkIjoxNTUsInVzZXJJZCI6MTksImluZGVudElkIjoxMTQsInNlbGxlcklkIjo2LCJwcmljZSI6NC4wLCJnb29kc051bSI6MSwic3RhdHVzIjoxLCJjcmVhdGVUaW1lIjoiSnVuIDEwLCAyMDE1IDI6NDI6MzQgUE0iLCJkYXRhU3RhdHVzIjowLCJnb29kc0lkIjoyMSwiZ29vZHNOYW1lIjoi06rR-L_sz980MDBtbCJ9XX0";
    	str = "eyJjb250ZW50Ijp7Im5hbWUiOiLA7tbHu9QiLCJwaG9uZSI6IjE1NjU5ODM3Njg0IiwiYWRkcmVzcyI6Is_Dw8XI7bz-1LAiLCJpbmRlbnRJZCI6NDE4LCJpbmRlbnROdW1iZXIiOiJpdG4yMDE1MDcxMzEyNDg1NWFSNzlxIiwidHlwZSI6MTJ9LCJudW0iOjMsIi0xIjpbeyJpZCI6NzIyLCJ1c2VySWQiOjE5LCJpbmRlbnRJZCI6NDE4LCJzZWxsZXJJZCI6LTEsInByaWNlIjo0LjUsImdvb2RzTnVtIjoxLCJzdGF0dXMiOjEsImNyZWF0ZVRpbWVMb25nIjowLCJkYXRhU3RhdHVzIjowLCJnb29kc0lkIjoyMiwiZ29vZHNOYW1lIjoiwva2rzQwMG1sIn0seyJpZCI6NzIzLCJ1c2VySWQiOjE5LCJpbmRlbnRJZCI6NDE4LCJzZWxsZXJJZCI6LTEsInByaWNlIjo1LjUsImdvb2RzTnVtIjoxLCJzdGF0dXMiOjEsImNyZWF0ZVRpbWVMb25nIjowLCJkYXRhU3RhdHVzIjowLCJnb29kc0lkIjoyNSwiZ29vZHNOYW1lIjoiwva2rzQwMG1sIn0seyJpZCI6NzI0LCJ1c2VySWQiOjE5LCJpbmRlbnRJZCI6NDE4LCJzZWxsZXJJZCI6LTEsInByaWNlIjo0LjAsImdvb2RzTnVtIjoxLCJzdGF0dXMiOjEsImNyZWF0ZVRpbWVMb25nIjowLCJkYXRhU3RhdHVzIjowLCJnb29kc0lkIjoyMSwiZ29vZHNOYW1lIjoi06rR-L_sz980MDBtbCJ9XX0";
    	str = "eyJjb250ZW50Ijp7Im5hbWUiOiLmnY7mmbrovokiLCJwaG9uZSI6IjE1NjU5ODM3Njg0IiwiYWRkcmVzcyI6IuWOpumXqOi9r-S7tuWbrSIsImluZGVudElkIjo0MjAsImluZGVudE51bWJlciI6Iml0bjIwMTUwNzEzMTI1MjQzTGpqcWIiLCJ0eXBlIjoxMn0sIm51bSI6MywiLTEiOlt7ImlkIjo3MjgsInVzZXJJZCI6MTksImluZGVudElkIjo0MjAsInNlbGxlcklkIjotMSwicHJpY2UiOjQuNSwiZ29vZHNOdW0iOjEsInN0YXR1cyI6MSwiY3JlYXRlVGltZUxvbmciOjAsImRhdGFTdGF0dXMiOjAsImdvb2RzSWQiOjIyLCJnb29kc05hbWUiOiLohInliqg0MDBtbCJ9LHsiaWQiOjcyOSwidXNlcklkIjoxOSwiaW5kZW50SWQiOjQyMCwic2VsbGVySWQiOi0xLCJwcmljZSI6NS41LCJnb29kc051bSI6MSwic3RhdHVzIjoxLCJjcmVhdGVUaW1lTG9uZyI6MCwiZGF0YVN0YXR1cyI6MCwiZ29vZHNJZCI6MjUsImdvb2RzTmFtZSI6IuiEieWKqDQwMG1sIn0seyJpZCI6NzMwLCJ1c2VySWQiOjE5LCJpbmRlbnRJZCI6NDIwLCJzZWxsZXJJZCI6LTEsInByaWNlIjo0LjAsImdvb2RzTnVtIjoxLCJzdGF0dXMiOjEsImNyZWF0ZVRpbWVMb25nIjowLCJkYXRhU3RhdHVzIjowLCJnb29kc0lkIjoyMSwiZ29vZHNOYW1lIjoi6JCl5YW75b-r57q_NDAwbWwifV19";
    	System.out.println(Tools.decodeBase64(str, "utf-8"));
    	
    	str = "泡面";
    	//str = "我要牛奶可乐";
    	str = "我想看电影";
    	str = "我想吃香蕉可乐，看看电影";
    	str = "{\"牛肉\":\"n\",\"香蕉\":\"n\",\"泡面\":\"n\",\"土豆\":\"n\"}";
    	str = "{\"可乐\":\"n\",\"泡面\":\"n\"}";
    	str = "电影 快乐大本营";    
    	
    }
}