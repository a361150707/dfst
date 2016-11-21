package com.ennuova.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

public class GeneralControl {

public Logger log = Logger.getLogger(GeneralControl.class.getName());
	
	// 向页面发送信息数据，json格式
	public void sendDataResponse(HttpServletResponse response, String json) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setContentType("text/json;charset=utf-8");
		try {
			PrintWriter pw = response.getWriter();
			pw.print(json);
			pw.flush();
			pw.close();
		} catch (IOException e) {
		}
	}
}
