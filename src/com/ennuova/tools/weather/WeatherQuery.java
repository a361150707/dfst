package com.ennuova.tools.weather;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

import com.ennuova.util.URLRequest;

public class WeatherQuery {

	
	/**
	 * 根据城市名称使用百度API获取天气情况
	 * 
	 * @param cityName 查询的城市名称
	 * @return String 返回结果
	 */
	public static String getWeatherByCityName(String cityName){
		String url = "";
		try {
			url = "http://api.map.baidu.com/telematics/v3/weather?location="+URLEncoder.encode(cityName, "UTF-8")+"&output=json&ak=sQ3yqcfR4z5LGQvX3USgGvtb";			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return URLRequest.requestURL(url);
	}
	public static void main(String[] args) {
		String content = getWeatherByCityName("厦门");
		System.out.println(content);
	}
	/**
	 * 使用百度API获取天气情况
	 * 
	 * @param cityName 查询的城市名称
	 * @return String 返回结果
	 */
	public static String getWeatherDataByBaidu(String cityName) {
	    String httpUrl = "http://apis.baidu.com/heweather/weather/free";
	    //String str = "http://api.map.baidu.com/telematics/v3/weather?location=xiamen&output=json&ak=pslCS7GsfRzGQmNabFWEBg1Z";
	    cityName = "city=" + getPinYin(cityName);
	    httpUrl = httpUrl + "?" + cityName;
	    
	    return getWeatherDataConnection(httpUrl, true);
	}
	
	/**
	 * 根据经纬度，使用百度API获取天气情况
	 * @param baiduLngAndLat
	 * @return
	 */
	public static String getWeatherDataByBaiduLngAndLat(String baiduLngAndLat) {
	    String httpUrl = "http://apis.baidu.com/showapi_open_bus/weather_showapi/point";
	    String[] str2 = baiduLngAndLat.split(",");
	    String httpArg = "lng="+str2[1]+"&lat="+str2[0]+"&from=5&needIndex=1";//&needMoreDay=0
	    httpUrl = httpUrl + "?" + httpArg;
	    
	    return getWeatherDataConnection(httpUrl, true);
	}

	/**
	 * 使用小米API获取天气情况
	 * 
	 * @param cityCode 查询的城市名称
	 * @return String 返回结果
	 */
	public static String getWeatherDataByXiaoMi(String cityCode) {
	    String httpUrl = "http://weatherapi.market.xiaomi.com/wtr-v2/weather?cityId="+cityCode;
	    
	    return getWeatherDataConnection(httpUrl, false);
	}
	
	/**
	 * 根据经纬度获取城市信息
	 * 
	 * @param lngAndLat 经纬度，必须先纬度再经度，中间用逗号隔开
	 * @return String 返回结果
	 */
	public static String getCityDataByLngAndLat(String lngAndLat) {
	    String httpUrl = "http://api.map.baidu.com/geocoder/v2/?location="+lngAndLat+"&output=json&ak=ZvIkhEmfTTIFKLpr5sFrbglG";
	    
	    return getWeatherDataConnection(httpUrl, false);
	}
	
	/**
	 * 根据地理位置获取坐标经纬度
	 * 
	 * @param cityName 地理名称(包括地址所在的城市名或者所在商圈信息，如 "人民大学,中关村,苏州街")
	 * @return String 返回结果
	 */
	public static String getLngAndLatDataByCityName(String cityName) {
	    String httpUrl = "";
		try {
			httpUrl = "http://api.map.baidu.com/geocoder/v2/?address="+URLEncoder.encode(cityName, "UTF-8")+"&output=json&ak=ZvIkhEmfTTIFKLpr5sFrbglG";
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	    return getWeatherDataConnection(httpUrl, false);
	}
	
	/**
	 * 根据httpUrl，获取API接口返回资料
	 * 
	 * @param httpUrl API地址
	 * @param ApiType 是否百度API类型
	 * @return String
	 */
	public static String getWeatherDataConnection(String httpUrl, boolean ApiType) {
	    BufferedReader reader = null;
	    String result = null;
	    StringBuffer sbf = new StringBuffer();

	    try {
	        URL url = new URL(httpUrl);
	        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
	        connection.setRequestMethod("GET");
	        
	        // 填入apikey到HTTP header
	        if(ApiType){
	        	connection.setRequestProperty("apikey", "b2068bffd2c9a432becff6dc4c0e5b1c");	        	
	        }
	        connection.connect();
	        InputStream inputStream = connection.getInputStream();
	        reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
	        String strRead = null;
	        while ((strRead = reader.readLine()) != null) {
	            sbf.append(strRead);
	            sbf.append("\r\n");
	        }
	        reader.close();
	        result = sbf.toString();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    
	    return result;
	}
	
	/**
	 * 将非百度的经纬度坐标值转换为百度经纬度坐标值，必须先经度再纬度，中间用逗号隔开，e.g:"117.76201,24.633738"
	 * 
	 * @param lngAndLat
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static String changeBaiDuLngAndLat(String lngAndLat) {
		Map<String, Object> map = new HashMap<String, Object>();
		JsonConfig config = new JsonConfig();
	    String httpUrl = "http://api.map.baidu.com/geoconv/v1/?coords="+lngAndLat+"&output=json&ak=ZvIkhEmfTTIFKLpr5sFrbglG";
	    
	    String baiduLngAndLat = getWeatherDataConnection(httpUrl, false);
	    JSONObject object = JSONObject.fromObject(baiduLngAndLat, config);
		Set keys = object.keySet();
	    Iterator obj = keys.iterator();
	    while (obj.hasNext()) {
	    	String key = (String)obj.next();
	    	if("result".equals(key)){
	    		JSONArray json = (JSONArray)object.get(key);
	    		if(map != null){	    			
	    			//获取坐标值
	    			JSONObject index = json.getJSONObject(0);
	    			baiduLngAndLat = index.getString("y") + "," + index.getString("x");
	    		}
	    	}
	    }
	    
	    return baiduLngAndLat;
	}
	
	/**
	 * 把汉字转成拼音
	 * 
	 * @param hanzi
	 * @return String
	 */
	public static String getPinYin(String hanzi) {
		if(hanzi != null) {
			char[] t1 = null;
			t1 = hanzi.toCharArray();
			String[] t2 = new String[t1.length];
			
			// 设置汉字拼音输出的格式
			HanyuPinyinOutputFormat t3 = new HanyuPinyinOutputFormat();
			t3.setCaseType(HanyuPinyinCaseType.LOWERCASE);
			t3.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
			t3.setVCharType(HanyuPinyinVCharType.WITH_V);
			String t4 = "";
			int t0 = t1.length;
			try {
				for(int i = 0; i < t0; i++) {
					// 判断能否为汉字字符
					if(Character.toString(t1[i]).matches("[\\u4E00-\\u9FA5]+")) {
						t2 = PinyinHelper.toHanyuPinyinStringArray(t1[i], t3);// 将汉字的几种全拼都存到t2数组中
						t4 += t2[0];// 取出该汉字全拼的第一种读音并连接到字符串t4后
					} else {
						// 如果不是汉字字符，间接取出字符并连接到字符串t4后
						t4 += Character.toString(t1[i]);
					}
				}
			} catch (BadHanyuPinyinOutputFormatCombination e) {
				e.printStackTrace();
			}
			
			return t4;
		} else{
			return "";
		}
    }
}
