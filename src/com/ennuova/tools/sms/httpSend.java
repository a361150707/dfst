package com.ennuova.tools.sms;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;


public class httpSend {
	String urlString;

	/*
	 * public static void main(String[] args) throws Exception { HttpTest client
	 * = new HttpTest(网址); client.run(); }
	 */
	public httpSend(String urlString) {
		this.urlString = urlString;
	}

	public String send() throws Exception {
		String line;
		
		// 生成一个URL对象
		URL url = new URL(urlString);
		
		// 打开URL
		HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
		
		// 得到输入流，即获得返回值
		BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(),"utf-8"));
		
		// 读取返回值，进行判断
		while((line = reader.readLine()) != null) {
		}
		
		return line;
	}
	
	public static String getSend(String strUrl,String param){
		URL url = null;
		HttpURLConnection connection = null;

		try {
			url = new URL(strUrl + "?" + param);
			connection = (HttpURLConnection) url.openConnection();
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setRequestMethod("GET");
			connection.setUseCaches(false);
			connection.connect();

			BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
			StringBuffer buffer = new StringBuffer();
			String line = "";
			while ((line = reader.readLine()) != null) {
				buffer.append(line);
			}

			reader.close();
			return buffer.toString();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} finally {
			if (connection != null) {
				connection.disconnect();
			}
		}
	}

	public static String postSend(String strUrl,String param){


		URL url = null;
		HttpURLConnection connection = null;

		try {
			url = new URL(strUrl);
			connection = (HttpURLConnection) url.openConnection();
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setRequestMethod("POST");
			connection.setUseCaches(false);
			connection.connect();

			//POST方法时使用
			DataOutputStream out = new DataOutputStream(connection.getOutputStream());
			out.writeBytes(param);
			out.flush();
			out.close();

			BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
			StringBuffer buffer = new StringBuffer();
			String line = "";
			while ((line = reader.readLine()) != null) {
				buffer.append(line);
			}

			reader.close();
			return buffer.toString();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} finally {
			if (connection != null) {
				connection.disconnect();
			}
		}


	}
	/**
	 * 转为16进制方法
	 * @param str
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	public static String paraTo16(String str) throws UnsupportedEncodingException {
		String hs = "";

		byte[] byStr = str.getBytes("UTF-8");
		for(int i=0;i<byStr.length;i++)
		{
			String temp = "";
			temp = (Integer.toHexString(byStr[i]&0xFF));
			if(temp.length()==1) temp = "%0"+temp;
			else temp = "%"+temp;
			hs = hs+temp;
		}
		return hs.toUpperCase();


	}

	
}