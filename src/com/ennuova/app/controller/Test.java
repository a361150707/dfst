package com.ennuova.app.controller;

import java.text.DecimalFormat;
import java.util.Map;

import com.ennuova.tools.weather.WeatherQuery;
import com.ennuova.util.JSONUtil;
import com.ennuova.util.JsonUtils;
import com.ennuova.util.StringUtil;

public class Test {

	public static void main(String[] args) {
//		System.out.println("-------------"+File.listRoots());
//		//System.out.println("-------------"+File);
//		File f = new File("f:/test/ss");
//		File f = new File("f:/ss");
//		System.out.println(f.mkdir());
//		file.renameTo(new File("f:/test/test24932877"));
//		String[] arrs = new String[3];
//		arrs[0] = "ssss";
//		arrs[1] = "ssss";
//		System.out.println(arrs.toString());
		
//		String toString = arrStr.toString();
		/*StringBuilder str = new StringBuilder();
		for(int i=0;i<arrStr.length;i++){
			str.append(arrStr[i]);
		}
		System.out.println(str.toString());*/
		String city = WeatherQuery.getCityDataByLngAndLat("24.47,118.06");
//		JSONUtil
//		System.out.println("--------------"+city);
		//JsonUtils
//		Map<String, Object> toMap = JSONUtil.getMapFromJson(city);
//		System.out.println(toMap);
//		System.out.println(toMap.get("city"));
//		String string = "33667";
//		System.out.println(Integer.valueOf(string)/10.0);
//		double d = 3.1415926;
//		DecimalFormat df = new DecimalFormat(".##");
//		System.out.println(df.format(d));
		String string = "98.9876";
		System.out.println(string.indexOf("."));
		System.out.println(string.substring(0, string.indexOf(".")+2));
	}
}
