package com.ennuova.util;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class MapDistanceUtil {

	 private static double EARTH_RADIUS = 6378.137; 
	   
     private static double rad(double d) { 
         return d * Math.PI / 180.0; 
     }
     
     /**
      * 根据两个位置的经纬度，来计算两地的距离（单位为KM）
      *@author sududa
      *@date 2016年10月20日
      * 参数为String类型
      * @param lat1 用户经度
      * @param lng1 用户纬度
      * @param lat2 商家经度
      * @param lng2 商家纬度
      * @return
      */
     public static String getDistance(String lat1Str, String lng1Str, String lat2Str, String lng2Str) {
         Double lat1 = Double.parseDouble(lat1Str);
         Double lng1 = Double.parseDouble(lng1Str);
         Double lat2 = Double.parseDouble(lat2Str);
         Double lng2 = Double.parseDouble(lng2Str);
          
         double radLat1 = rad(lat1);
         double radLat2 = rad(lat2);
         double difference = radLat1 - radLat2;
         double mdifference = rad(lng1) - rad(lng2);
         double distance = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(difference / 2), 2)
                 + Math.cos(radLat1) * Math.cos(radLat2)
                 * Math.pow(Math.sin(mdifference / 2), 2)));
         distance = distance * EARTH_RADIUS;
         distance = Math.round(distance * 10000) / 10000;
         String distanceStr = distance+"";
         distanceStr = distanceStr.substring(0, distanceStr.indexOf("."));
         return distanceStr;
     }
     
     /** 
      * 通过经纬度获取距离(单位：千米) 
      * @param selfLng 自己经度
      * @param selfLat 自己纬度 
      * @param otherLng 另一个经度
      * @param otherLat 另一个纬度 
      * @return 
      */  
     public static double getDistance(double selfLng, double selfLat, double otherLng,  
                                      double otherLat) {  
         double radSelfLng = rad(selfLng);  
         double radOtherLng = rad(otherLng);  
         double a = radSelfLng - radOtherLng;  
         double b = rad(selfLat) - rad(otherLat);  
         double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2)  
                 + Math.cos(radSelfLng) * Math.cos(radOtherLng)  
                 * Math.pow(Math.sin(b / 2), 2)));  
         s = s * EARTH_RADIUS;  
         s = Math.round(s * 10000d) / 10000d;  
         //s = s*1000;  
         return s;  
     }
     
     public static int getRound(double dSource){
    	 BigDecimal bigDecimal = new BigDecimal(dSource);
    	 //return bigDecimal.setScale(0, bigDecimal.ROUND_HALF_UP).intValue();
    	 return bigDecimal.setScale(0, bigDecimal.ROUND_HALF_EVEN).intValue();
     }
     
     public static void sortList(List<Map<String, Object>> data) {
         Collections.sort(data, new Comparator<Map>() {
             public int compare(Map o1, Map o2) {
             Integer a = (Integer) o1.get("PRECOUNTOUT");
             Integer b = (Integer) o2.get("PRECOUNTOUT");
             // 升序
             return a.compareTo(b);
             // 降序
             // return b.compareTo(a);
             }
         });
     }
	    
}
