package com.ennuova.obd.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ennuova.common.CommonClass;
import com.ennuova.obd.http.WeixinUtil;
import com.ennuova.obd.tools.SignUtils;
import com.ennuova.obd.util.GoloApplication;
import com.ennuova.util.Util;

@Controller("odbController")
@RequestMapping("/odb.do")
public class OdbController {
	
	/**
	 * 根据设备序列号获取设备信息
	 * @param params
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(params = "action=data_develop.get_devices_info")
	public String get_devices_info(String serial_no,
			HttpServletResponse response) throws IOException {
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setContentType("text/json;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		//develop_id=1000&serial_no=97119000001&time=1404296554
		
		Map<String, String> paramss = new HashMap<String, String>();
		paramss.put("action", "data_develop.get_devices_info");
		paramss.put("app_id", GoloApplication.app_id);
		paramss.put("develop_id", GoloApplication.develop_id);
		paramss.put("devicesn", serial_no);
		paramss.put("time", Long.toString(System.currentTimeMillis() / 1000));
		paramss.put("devicetype", "golo3CU");
		String sign = SignUtils.getSign(GoloApplication.delevop_key,paramss);
		paramss.put("sign", sign);
		String param = SignUtils.createLinkString(paramss);
		String res = WeixinUtil.httpsend("http://open.api.dbscar.com/?"
				+ param);
		
		JSONObject jsonObject=JSONObject.fromObject(res);
		
		out.write(jsonObject.toString());
		out.flush();
		out.close();
		return null;
	}

	/**
	 * 通过序列号获取vin码
	 * @param params
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(params = "action=data_develop.get_vin_by_devicesn")
	public String get_vin_by_devicesn(String serial_no,
			HttpServletResponse response) throws IOException {
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setContentType("text/json;charset=utf-8");
		PrintWriter out = response.getWriter();

		//develop_id=1000&serial_no=97119000001&time=1404296554
	
		Map<String, String> paramss = new HashMap<String, String>();
		paramss.put("action", "data_develop.get_vin_by_devicesn");
		paramss.put("develop_id", GoloApplication.develop_id);
		paramss.put("serial_no", serial_no);
		paramss.put("time", Long.toString(System.currentTimeMillis() / 1000));
		String sign = SignUtils.getSign(GoloApplication.delevop_key,paramss);
		paramss.put("sign", sign);
		String param = SignUtils.createLinkString(paramss);
		String res = WeixinUtil.httpsend("http://open.api.dbscar.com/?"
				+ param);
		
		JSONObject jsonObject=JSONObject.fromObject(res);

		out.write(jsonObject.toString());
		out.flush();
		out.close();
		return null;
	}
	/**
	 * 通过序列号获取手机号码
	 * @param params
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(params = "action=sim_card_service.get_sim_phone")
	public String get_sim_phone(String serial_no,
			HttpServletResponse response) throws IOException {
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setContentType("text/json;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		//String serial_no = jMap.get("serial_no").toString();
		String devicesn = serial_no;
		
		//develop_id=1000&serial_no=97119000001&time=1404296554
		
		Map<String, String> paramss = new HashMap<String, String>();
		paramss.put("action", "sim_card_service.get_sim_phone");
		paramss.put("develop_id", GoloApplication.develop_id);
		paramss.put("devicesn", devicesn);
		paramss.put("time", Long.toString(System.currentTimeMillis() / 1000));
		String sign = SignUtils.getSign(GoloApplication.delevop_key,paramss);
		paramss.put("sign", sign);
		String param = SignUtils.createLinkString(paramss);
		String res = WeixinUtil.httpsend("http://open.api.dbscar.com/?"
				+ param);
		JSONObject jsonObject=JSONObject.fromObject(res);
		
		out.write(jsonObject.toString());
		out.flush();
		out.close();
		return null;
	}
	/**
	 * 通过接头号获取卡中流量(剩余流量或者所用流量)
	 * @param params
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(params = "action=sim_card_service.get_flow_by_serial")
	public String get_flow_by_serial(String serial_no,
			HttpServletResponse response) throws IOException {
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setContentType("text/json;charset=utf-8");
		PrintWriter out = response.getWriter();
		
//		String content = Util.aesDecrypt(params);
//		Map<String, Object> jMap = CommonClass.getMapByJson(content);
		//String serial_no = jMap.get("serial_no").toString();
		String devicesn = serial_no;
		
		//develop_id=1000&serial_no=97119000001&time=1404296554
		
		Map<String, String> paramss = new HashMap<String, String>();
		paramss.put("action", "sim_card_service. get_flow_by_serial");
		paramss.put("develop_id", GoloApplication.develop_id);
		paramss.put("devicesn", devicesn);
		paramss.put("time", Long.toString(System.currentTimeMillis() / 1000));
		String sign = SignUtils.getSign(GoloApplication.delevop_key,paramss);
		paramss.put("sign", sign);
		String param = SignUtils.createLinkString(paramss);
		
		String res = WeixinUtil.httpsend("http://open.api.dbscar.com/?"
				+ param);
		
		JSONObject jsonObject=JSONObject.fromObject(res);
		
		out.write(jsonObject.toString());
		out.flush();
		out.close();
		return null;
	}
}
