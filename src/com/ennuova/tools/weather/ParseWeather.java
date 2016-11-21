package com.ennuova.tools.weather;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import com.ennuova.entity.WeatherEnumDemo;
import com.ennuova.entity.WeatherEnumDemo.WeatherEnum;
import com.ennuova.entity.WeatherInformation;

public class ParseWeather {
	
	/**
	 * 解析根据经纬度查询城市信息的资料，赋值给WeatherInformation对象
	 * 
	 * @param lngAndLat
	 * @return WeatherInformation
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static WeatherInformation parseCityDataByLngAndLat(String lngAndLat) {
		WeatherInformation weatherInformation = new WeatherInformation();
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> mapTemp = new HashMap<String, Object>();
		JsonConfig config = new JsonConfig();
		
		//把经纬度转化成百度的经纬度
		String baiduLngAndLat = WeatherQuery.changeBaiDuLngAndLat(lngAndLat);
		
		//获取城市信息
		String cityInformation = WeatherQuery.getCityDataByLngAndLat(baiduLngAndLat);
		if(cityInformation == null || cityInformation.isEmpty()){
			return null;
		}
		
		//解析资料
		JSONObject object = JSONObject.fromObject(cityInformation, config);
		Set keys = object.keySet();
	    Iterator obj = keys.iterator();
	    while (obj.hasNext()) {
	    	String key = (String)obj.next();
	    	if("result".equals(key)){
	    		map = (Map<String, Object>)object.get(key);
	    		if(map != null){
	    			//获取经纬度
	    			mapTemp = (Map<String, Object>)map.get("location");
	    			weatherInformation.setLng(new BigDecimal(mapTemp.get("lng").toString()));
	    			weatherInformation.setLat(new BigDecimal(mapTemp.get("lat").toString()));
	    			
	    			//获取区域信息
	    			mapTemp = (Map<String, Object>)map.get("addressComponent");
	    			weatherInformation.setCity((String)mapTemp.get("city"));
	    			weatherInformation.setCountry((String)mapTemp.get("country"));
	    			weatherInformation.setDistrict((String)mapTemp.get("district"));
	    			weatherInformation.setProvince((String)mapTemp.get("province"));
	    			weatherInformation.setStreet((String)mapTemp.get("street"));
	    			weatherInformation.setStreetNumber((String)mapTemp.get("street_number"));
	    			
	    			//获取地址
	    			weatherInformation.setAddress((String)map.get("formatted_address"));
	    		}
	    	}
	    }
	    
	    return weatherInformation;
	}
	
	/**
	 * 解析根据经纬度查询小米天气情况的资料，赋值给WeatherInformation对象
	 * 
	 * @param lngAndLat
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static WeatherInformation parseWeatherDataByXiaoMi(String cityCode) {
		WeatherInformation weatherInformation = new WeatherInformation();
		Map<String, Object> map = new HashMap<String, Object>();
		JsonConfig config = new JsonConfig();
		
		//获取天气信息
		String cityWeather = WeatherQuery.getWeatherDataByXiaoMi(cityCode);
		if(cityWeather == null || cityWeather.isEmpty()){
			return null;
		}
		
		//解析资料
		JSONObject object = JSONObject.fromObject(cityWeather, config);
		Set keys = object.keySet();
	    Iterator obj = keys.iterator();
	    while (obj.hasNext()) {
	    	String key = (String)obj.next();
	    	if("forecast".equals(key)){
	    		map = (Map<String, Object>)object.get(key);
	    		if(map != null){	    			
	    			//获取地址信息
	    			weatherInformation.setCity((String)map.get("city") + "市");
	    			weatherInformation.setCityCode((String)map.get("cityid"));
	    		}
	    	} else if("aqi".equals(key)){
	    		map = (Map<String, Object>)object.get(key);
	    		if(map != null){			
	    			//获取实时天气信息
	    			weatherInformation.setAqi((String)map.get("aqi"));
	    			weatherInformation.setPm25((String)map.get("pm25"));
	    		}
	    	} else if("index".equals(key)){
	    		JSONArray json = (JSONArray)object.get(key);
	    		if(map != null){  			
	    			//获取实时天气信息
	    			JSONObject index0 = json.getJSONObject(0);
	    			JSONObject index1 = json.getJSONObject(1);
	    			JSONObject index2 = json.getJSONObject(2);
	    			JSONObject index3 = json.getJSONObject(3);
	    			JSONObject index4 = json.getJSONObject(4);
	    			
	    			//防晒指数
	    			weatherInformation.setSpf(index0.getString("index"));
	    			weatherInformation.setSpfName(index0.getString("name"));
	    			weatherInformation.setSpfDetails(index0.getString("details"));
	    			
	    			//穿衣指数
	    			weatherInformation.setDressing(index1.getString("index"));
	    			weatherInformation.setDressingName(index1.getString("name"));
	    			weatherInformation.setDressingDetails(index1.getString("details"));
	    			
	    			//运动指数
	    			weatherInformation.setExercise(index2.getString("index"));
	    			weatherInformation.setExerciseName(index2.getString("name"));
	    			weatherInformation.setExerciseDetails(index2.getString("details"));
	    			
	    			//洗车指数
	    			weatherInformation.setWashing(index3.getString("index"));
	    			weatherInformation.setWashingName(index3.getString("name"));
	    			weatherInformation.setWashingDetails(index3.getString("details"));
	    			
	    			//晾晒指数
	    			weatherInformation.setDrying(index4.getString("index"));
	    			weatherInformation.setDryingName(index4.getString("name"));
	    			weatherInformation.setDryingDetails(index4.getString("details"));
	    		}
	    	} else if("today".equals(key)){
	    		map = (Map<String, Object>)object.get(key);
	    		String weather = "";
	    		if(map != null){			
	    			//获取当天具体天气信息
	    			weatherInformation.setDate((String)map.get("date"));
	    			weatherInformation.setHumidityMax(map.get("humidityMax")+"");
	    			weatherInformation.setHumidityMin(map.get("humidityMin")+"");
	    			weatherInformation.setPrecipitationMax(map.get("precipitationMax")+"");
	    			weatherInformation.setPrecipitationMin(map.get("precipitationMin")+"");
	    			weatherInformation.setTemperatureMax(map.get("tempMax") + "℃");
	    			weatherInformation.setTemperatureMin(map.get("tempMin") + "℃");
	    			if(map.get("weatherEnd") == map.get("weatherStart")){
	    				weather = (String)map.get("weatherEnd");
	    			} else{
	    				weather = map.get("weatherStart") + "转" + map.get("weatherEnd");
	    			}
	    			weatherInformation.setWeather(weather);
	    			weatherInformation.setWindDirectionEnd((String)map.get("windDirectionEnd"));
	    			weatherInformation.setWindDirectionStart((String)map.get("windDirectionStart"));
	    			weatherInformation.setWindMax(map.get("windMax")+"");
	    			weatherInformation.setWindMin(map.get("windMin")+"");
	    		}
	    	}
	    }
		
		return weatherInformation;
	}
	
	/**
	 * 解析根据经纬度查询百度天气情况的资料，赋值给WeatherInformation对象
	 * 
	 * @param lngAndLat
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked", "static-access" })
	public static WeatherInformation parseWeatherDataByLngAndLat(String lngAndLat) {
		WeatherInformation weatherInformation = new WeatherInformation();
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> mapTemp = new HashMap<String, Object>();
		Map<String, Object> mapTemp1 = new HashMap<String, Object>();
		Map<String, Object> mapTemp2 = new HashMap<String, Object>();
		JsonConfig config = new JsonConfig();
		
		// 根据经纬度，转成百度的经纬度值
		String baiduLngAndLat = WeatherQuery.changeBaiDuLngAndLat(lngAndLat);
		
		// 用百度的经纬度值去获取百度API提供的天气情况
		baiduLngAndLat = WeatherQuery.getWeatherDataByBaiduLngAndLat(baiduLngAndLat);
		
		// 解析资料
		JSONObject object = JSONObject.fromObject(baiduLngAndLat, config);
		Set keys = object.keySet();
		Iterator obj = keys.iterator();
		while (obj.hasNext()) {
			String key = (String) obj.next();
			if ("showapi_res_body".equals(key)) {
				map = (Map<String, Object>) object.get(key);
				if (map != null) {
					mapTemp = (Map<String, Object>) map.get("cityInfo");
					if(mapTemp != null){
						// 获取经纬度
						weatherInformation.setLng(new BigDecimal(mapTemp.get("longitude").toString()));
						weatherInformation.setLat(new BigDecimal(mapTemp.get("latitude").toString()));
						
						// 获取区域信息
						weatherInformation.setCityCode((String) mapTemp.get("c1"));
						weatherInformation.setDistrict((String) mapTemp.get("c3"));
						weatherInformation.setCity((String) mapTemp.get("c5") + "市");
						weatherInformation.setProvince((String) mapTemp.get("c7") + "省");
						weatherInformation.setCountry((String) mapTemp.get("c9"));
					}
					
					mapTemp = (Map<String, Object>)map.get("now");
					if(mapTemp != null){
						// 获取天气情况
						WeatherEnum weatherEnum = null;
						String weather = (String)mapTemp.get("weather");
						weatherInformation.setWeather(weather);
						if(weather != null && !weather.isEmpty()){
							if("晴".equals(weather)){
								weatherEnum = weatherEnum.qingtian;
							} else if("多云".equals(weather)){
								weatherEnum = weatherEnum.duoyun;
							} else if("阴".equals(weather)){
								weatherEnum = weatherEnum.yintian;
							} else if(weather.indexOf("雪") >= 0){
								weatherEnum = weatherEnum.xiaxue;
							} else{
								weatherEnum = weatherEnum.xiayu;
							}
						}
						WeatherEnumDemo weatherEnumDemo = new WeatherEnumDemo(weatherEnum);
						weatherInformation.setWeatherEnum(weatherEnumDemo.getWeatherEnum());
						weatherInformation.setTemperatureMax((String)mapTemp.get("temperature"));
						weatherInformation.setTemperatureMin((String)mapTemp.get("temperature"));
						weatherInformation.setHumidityMax((String)mapTemp.get("sd"));
						weatherInformation.setHumidityMin((String)mapTemp.get("sd"));
						weatherInformation.setWindDirectionStart((String)mapTemp.get("wind_direction"));
						weatherInformation.setWindDirectionEnd((String)mapTemp.get("wind_direction"));
						weatherInformation.setWindMax((String)mapTemp.get("wind_power"));
						weatherInformation.setWindMin((String)mapTemp.get("wind_power"));
						
						mapTemp = (Map<String, Object>)mapTemp.get("aqiDetail");
						weatherInformation.setAqi(mapTemp.get("aqi")+"");
						weatherInformation.setPm25(mapTemp.get("pm2_5")+"");
					}
					
					//获取天气指数
					mapTemp = (Map<String, Object>) map.get("f1");
					if(mapTemp != null){
						weatherInformation.setPrecipitationMax((String)mapTemp.get("jiangshui"));
						weatherInformation.setPrecipitationMin((String)mapTemp.get("jiangshui"));
						mapTemp1 = (Map<String, Object>) mapTemp.get("index");
						if(mapTemp1 != null){
							mapTemp2 = (Map<String, Object>) mapTemp1.get("clothes");
							if(mapTemp2!=null){

								weatherInformation.setDressingName("穿衣指数");
								weatherInformation.setDressing((String)mapTemp2.get("title"));
								weatherInformation.setDressingDetails((String)mapTemp2.get("desc"));
							}
							
							mapTemp2 = (Map<String, Object>) mapTemp1.get("sports");
							weatherInformation.setExerciseName("运动指数");
							weatherInformation.setExercise((String)mapTemp2.get("title"));
							weatherInformation.setExerciseDetails((String)mapTemp2.get("desc"));
							
							mapTemp2 = (Map<String, Object>) mapTemp1.get("wash_car");
							weatherInformation.setWashingName("洗车指数");
							weatherInformation.setWashing((String)mapTemp2.get("title"));
							weatherInformation.setWashingDetails((String)mapTemp2.get("desc"));
						}
					}
				}
			}
		}

		return weatherInformation;
	}
}