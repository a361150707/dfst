package com.ennuova.common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.Clob;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

/**
 * 公共类
 * @author lin
 *
 */
public class CommonClass {
	
	/**
	 * oracle.sql.Clob 类型转换成 String类型
	 * @param clob
	 * @return	String
	 * @throws SQLException
	 * @throws IOException
	 */
	public String ClobToString(Clob clob) throws SQLException, IOException {
		String returnString = "";
		if(clob != null){
			Reader reader = clob.getCharacterStream();	//得到流
			BufferedReader br = new BufferedReader(reader);
			String s = br.readLine();
			StringBuffer sb = new StringBuffer();
			while (s != null) {	//执行循环将字符串全部取出付值给StringBuffer由StringBuffer转成STRING
				sb.append(s);
				s = br.readLine();
			}
			returnString = sb.toString();
		}
		
		return returnString;
	}
	
	/**
	 * 解析百度返回的城市信息，并返回城市名称
	 * 
	 * @param CityData 城市信息
	 * @return String 城市名称
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static String getCityCodeByLngAndLat(String CityData) {
		String tempStr = "";
		Map<String, Object> map1 = new HashMap<String, Object>();
		JsonConfig config = new JsonConfig();
		
		if(CityData == null || CityData.isEmpty()){
			return null;
		}
		JSONObject object = JSONObject.fromObject(CityData, config);
		Set keys = object.keySet();
	    Iterator obj = keys.iterator();
	    while (obj.hasNext()) {
	    	String key = (String)obj.next();
	    	if("result".equals(key)){
	    		map1 = (Map<String, Object>)object.get(key);
	    		if(map1 != null){
	    			map1 = (Map<String, Object>)map1.get("addressComponent");
	    			tempStr = (String)map1.get("city");
	    		}
	    	}
	    }
	    
	    return tempStr.isEmpty()? null: tempStr.substring(0, tempStr.length()-1);
	}
	
	/**
	 * json格式转成map格式
	 * 
	 * @param otherPropertyJson
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static Map<String, Object> getMapByJson(String otherPropertyJson) {
		Map<String, Object> map = new HashMap<String, Object>();
		if ("".equals(otherPropertyJson)) {
			return map;
		}
		JsonConfig config = new JsonConfig();
		JSONObject object = JSONObject.fromObject(otherPropertyJson, config);
		Set keys = object.keySet();
		Iterator obj = keys.iterator();
		while (obj.hasNext()) {
			String key = (String) obj.next();
			map.put(key, object.get(key));
		}
		
		return map;
	}
	
	
}