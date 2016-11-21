package com.ennuova.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Reader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.sql.Clob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;

import net.sf.json.JSONObject;

public class Util {
	public static boolean isNull(Object obj){
		if(obj == null || "".equals(obj) || "null".equals(obj)){
			return true;
		}else{
			return false;
		}
	}
	public static boolean isJson(String str){
		boolean bool = false;
		try {
			JSONObject.fromObject(str);
			bool = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bool;
	}
	public static boolean isNumber(String str){
		try {
			Integer.parseInt(str);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	@SuppressWarnings("static-access")
	public static String aesDecrypt(String str){
		AESUtil aesUtil = new AESUtil();
		String result = "";
		try {
			if(isNull(str)){
				return result;
			}
			result = aesUtil.decrypt(str);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	@SuppressWarnings("static-access")
	public static String aesEncrypt(String str){
		AESUtil aesUtil = new AESUtil();
		String result = "";
		try {
			if(isNull(str)){
				return result;
			}
			result = aesUtil.encrypt(str);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/*public static String getGroupValue(String group, String key){
		if(isNull(group) || isNull(key)){
			return "";
		}
		Map<String, Object> map = Constants.GROUP.get(group);
		if(map == null){
			return "";
		}
		return String.valueOf(map.get(key));
	}*/
	
	public static String doGet(String url, String param){
		String result = "";
        BufferedReader in = null;
        try {
            String urlNameString = url + "?" + param;
            URL realUrl = new URL(urlNameString);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 建立实际的连接
            connection.connect();
           /* // 获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();
            // 遍历所有的响应头字段
            for (String key : map.keySet()) {
                System.out.println(key + "--->" + map.get(key));
            }*/
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
	}
	
	public static String doPost(String url, String params){
		PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setUseCaches(false);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            out.print(params);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(conn.getInputStream(),"UTF-8"));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！"+e);
            e.printStackTrace();
        }
        //使用finally块来关闭输出流、输入流
        finally{
            try{
                if(out!=null){
                    out.close();
                }
                if(in!=null){
                    in.close();
                }
            }
            catch(IOException ex){
                ex.printStackTrace();
            }
        }
        return result;
		
	}
	
	public static String queryWeizhang(String carInfo, String appId, String appKey ){
		String result = "";
		try {
			long timestamp = System.currentTimeMillis();
			String sign = md5(appId + carInfo + timestamp + appKey);
			StringBuilder params = new StringBuilder();
			params.append("car_info=" + URLEncoder.encode(carInfo, "UTF-8"));
			params.append("&sign="+sign);
			params.append("&timestamp="+timestamp);
			params.append("&app_id="+appId);
			result = doPost("http://www.cheshouye.com/api/weizhang/query_task", params.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public static String md5(String msg) {
		try {
			MessageDigest instance = MessageDigest.getInstance("MD5");
			instance.update(msg.getBytes("UTF-8"));
			byte[] md = instance.digest();
			return byteArrayToHex(md);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private static String byteArrayToHex(byte[] a) {
		StringBuilder sb = new StringBuilder();
		for (byte b : a) {
			sb.append(String.format("%02x", b & 0xff));
		}
		return sb.toString();
	}
	public static String ClobToString(Clob clob) {
        String reString = "";
        Reader is = null;
        try {
            is = clob.getCharacterStream();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        // 得到流
        BufferedReader br = new BufferedReader(is);
        String s = null;
        try {
            s = br.readLine();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        StringBuffer sb = new StringBuffer();
        while (s != null) {
            //执行循环将字符串全部取出付值给StringBuffer由StringBuffer转成STRING
            sb.append(s);
            try {
                s = br.readLine();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        reString = sb.toString();
        return reString;
    }
	public static List<String> getAddress(List<String> LBSlist){
		List<String> list = new ArrayList<String>();
		if(LBSlist.size()>0){
			String LBSListStr = "";
			for (String string : LBSlist) {
				if(LBSListStr.length()>0){
					LBSListStr = LBSListStr+"|"+string;
				}else{
					LBSListStr = string;
				}
			}
			String urlStr = "http://restapi.amap.com/v3/geocode/regeo?key=34e78d282d0eed0d9e76e3a7ff9bcc84&extensions=base&batch=true&roadlevel=1&location="+LBSListStr;
			String rs = URLRequest.requestURL(urlStr);
			try {
				
				org.json.JSONObject jsonObject = new org.json.JSONObject(rs);
				String status = jsonObject.getString("status");
				if("1".equals(status)){
					JSONArray jsonArray = jsonObject.getJSONArray("regeocodes");
					for (int i = 0; i < jsonArray.length(); i++) {
						org.json.JSONObject jsonObject2 = (org.json.JSONObject) jsonArray.get(i);
						if(!"".equals(jsonObject2.getString("formatted_address"))){
							org.json.JSONObject jsonObject3 = jsonObject2.getJSONObject("addressComponent");
							try {
								org.json.JSONObject jsonObject4 = jsonObject3.getJSONObject("streetNumber");
								if(!"".equals(jsonObject4.getString("street"))){
									list.add(jsonObject4.getString("street")+jsonObject4.getString("number"));
								}else {
									list.add(jsonObject2.getString("formatted_address"));
								}
							} catch (Exception e) {
								list.add(jsonObject2.getString("formatted_address"));
							}
						}
						
					}
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		
		return list;
	}
	public static Map<String, List<String>> getAddressByLBS(List<String> LBSlist){
		Map<String, List<String>> map = new HashMap<String, List<String>>();
		List<String> list = new ArrayList<String>();
		List<String> list1 = new ArrayList<String>();
		if(LBSlist.size()>0){
			String LBSListStr = "";
			for (String string : LBSlist) {
				if(LBSListStr.length()>0){
					LBSListStr = LBSListStr+"|"+string;
				}else{
					LBSListStr = string;
				}
			}
			String urlStr = "http://restapi.amap.com/v3/geocode/regeo?key=34e78d282d0eed0d9e76e3a7ff9bcc84&extensions=base&batch=true&roadlevel=1&location="+LBSListStr;
			String rs = URLRequest.requestURL(urlStr);
			try {
				org.json.JSONObject jsonObject = new org.json.JSONObject(rs);
				String status = jsonObject.getString("status");
				if("1".equals(status)){
					JSONArray jsonArray = jsonObject.getJSONArray("regeocodes");
					for (int i = 0; i < jsonArray.length(); i++) {
						org.json.JSONObject jsonObject2 = (org.json.JSONObject) jsonArray.get(i);
						if(!"".equals(jsonObject2.getString("formatted_address"))){
							
							org.json.JSONObject jsonObject3 = jsonObject2.getJSONObject("addressComponent");
							try{
								list1.add(jsonObject3.getString("city"));
							} catch (Exception e) {
								list1.add(jsonObject3.getString("province"));
							}
							try {
								org.json.JSONObject jsonObject4 = jsonObject3.getJSONObject("streetNumber");
								if(!"".equals(jsonObject4.getString("street"))){
									list.add(jsonObject4.getString("street")+jsonObject4.getString("number"));
								}else {
									list.add(jsonObject2.getString("formatted_address"));
								}
							} catch (Exception e) {
								list.add(jsonObject2.getString("formatted_address"));
							}
							
						}
						
					}
				}
				map.put("address", list);
				map.put("city", list1);
				
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		
		return map;
	}
}