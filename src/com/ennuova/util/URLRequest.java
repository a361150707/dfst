package com.ennuova.util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

/**url请求工具
 * @author 李智辉
 * 2015年9月14日
 * 下午2:09:25
 */
public class URLRequest {
	/**输入流转字符串
	 * @param inputStream
	 * @return 转换后的字符串
	 * @throws UnsupportedEncodingException 
	 */
	public static String ConvertToString(InputStream inputStream) throws UnsupportedEncodingException{  
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream,"utf-8");  
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);  
        StringBuilder result = new StringBuilder();  
        String line = null;  
        try {  
            while((line = bufferedReader.readLine()) != null){  
                result.append(line + "\n");  
            }  
        } catch (IOException e) {  
            e.printStackTrace();  
        } finally {  
            try{  
                inputStreamReader.close();  
                inputStream.close();  
                bufferedReader.close();  
            }catch(IOException e){  
                e.printStackTrace();  
            }  
        }  
        return result.toString();
    }  
	/**文件流转字符串
	 * @param inputStream
	 * @return 转换后的字符串
	 */
	public static String ConvertToString(FileInputStream inputStream){  
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);  
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);  
        StringBuilder result = new StringBuilder();  
        String line = null;  
        try {  
            while((line = bufferedReader.readLine()) != null){  
                result.append(line + "\n");  
            }  
        } catch (IOException e) {  
            e.printStackTrace();  
        } finally {  
            try{  
                inputStreamReader.close();  
                inputStream.close();  
                bufferedReader.close();  
            }catch(IOException e){  
                e.printStackTrace();  
            }  
        }  
        return result.toString();  
    } 
	/**url请求工具
	 * @param effectiveURL
	 * @return responseStr 请求后的数据
	 */
	public final static String requestURL(String effectiveURL){
		System.out.println(effectiveURL);
		String responseStr = "";
		try {
			URL url = new URL(effectiveURL);  
	        HttpURLConnection urlConnection = (HttpURLConnection)url.openConnection();
	        urlConnection.setRequestProperty("Content-type", "text/html");
	        urlConnection.setRequestProperty("Accept-Charset", "utf-8");
	        urlConnection.setRequestProperty("contentType", "utf-8");
	        urlConnection.setRequestMethod("GET"); 
	        urlConnection.connect();
	        InputStream inputStream = urlConnection.getInputStream();  
	        responseStr = ConvertToString(inputStream); 
		} catch (Exception e) {
			return "不是有效的URL";
		}
		return responseStr;
	}
}
