package com.ennuova.obd.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ennuova.obd.http.WeixinUtil;

/**
 * 车辆体检
 * @author dofast-pc
 *
 */
@Controller("obdAndroidController")
@RequestMapping("/obdAndroid.do")
public class ObdAndroidController {
	
	@Autowired
	HttpServletRequest request;
	
	// 注册
	@RequestMapping(params = "action=develop.reg_user")
	public String login(String params, HttpServletResponse response)
			throws IOException {
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setContentType("text/json;charset=utf-8");
		PrintWriter out = response.getWriter();

		System.out.println("ObdAndroidController.login()");

		String param = request.getQueryString();
		// 请求服务器
		String res = WeixinUtil
				.httpsend("http://open.api.dbscar.com/?" + param);

		JSONObject jsonObject = JSONObject.fromObject(res);
		out.write(jsonObject.toString());
		out.flush();
		out.close();
		return null;
	}

	// 解绑
	@RequestMapping(params = "action=product_service.cancel_mycar_register")
	public String releaseConnector(String params, HttpServletResponse response)
			throws IOException {
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setContentType("text/json;charset=utf-8");
		PrintWriter out = response.getWriter();

		System.out.println("ObdAndroidController.releaseConnector()");

		String param = request.getQueryString();
		// 请求服务器
		String res = WeixinUtil
				.httpsend("http://open.api.dbscar.com/?" + param);

		JSONObject jsonObject = JSONObject.fromObject(res);
		out.write(jsonObject.toString());
		out.flush();
		out.close();
		return null;
	}

	// 激活
	@RequestMapping(params = "action=forjth.save_car_config")
	public String activateConnector(String params, HttpServletResponse response)
			throws IOException {
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setContentType("text/json;charset=utf-8");
		PrintWriter out = response.getWriter();

		System.out.println("ObdAndroidController.activateConnector()");

		String param = request.getQueryString();

		// 请求服务器
		String res = WeixinUtil
				.httpsend("http://open.api.dbscar.com/?" + param);

		JSONObject jsonObject = JSONObject.fromObject(res);
		out.write(jsonObject.toString());
		out.flush();
		out.close();
		return null;
	}

	// 获取品牌
	@RequestMapping(params = "action=forjth.getbrandid")
	public String getVehicleBrand(String params, HttpServletResponse response)
			throws IOException {
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setContentType("text/json;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		System.out.println("ObdAndroidController.getVehicleBrand()");

		String param = request.getQueryString();

		// 请求服务器
		String res = WeixinUtil
				.httpsend("http://open.api.dbscar.com/?" + param);

		JSONArray jsonArray = JSONArray.fromObject(res);
		out.write(jsonArray.toString());
		out.flush();
		out.close();
		return null;
	}

	// 除了获取车型
	@RequestMapping(params = "action=mine_car_service.query_market_car_type")
	public String getVehicleModels(String params, HttpServletResponse response)
			throws IOException {
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setContentType("text/json;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		System.out.println("ObdAndroidController.getVehicleModels()");
		
		String param = request.getQueryString();
		
		String res = WeixinUtil
				.httpsend("http://apps.api.dbscar.com/?" + param);

//		String url = "http://apps.api.dbscar.com/?action=mine_car_service.query_market_car_type"
//				+ "&lan_id_or_name=cn&detailId=" + detailId;
//
//		// 请求服务器
		//String res = WeixinUtil.httpsend(url);
		JSONObject jsonObject = JSONObject.fromObject(res);

		out.write(jsonObject.toString());
		out.flush();
		out.close();
		return null;
	}

	// 获取配置文件
	@RequestMapping(params = "action=data_develop.get_car_eobd")
	public String getConfigParams(String params, HttpServletResponse response)
			throws IOException {
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setContentType("text/json;charset=utf-8");
		PrintWriter out = response.getWriter();

		System.out.println("ObdAndroidController.getConfigParams()");

		String param = request.getQueryString();

		// 请求服务器
		String res = WeixinUtil
				.httpsend("http://open.api.dbscar.com/?" + param);

		JSONObject jsonObject = JSONObject.fromObject(res);
		out.write(jsonObject.toString());
		out.flush();
		out.close();
		return null;
	}

	// 获取序列号对应的车辆信息
	@RequestMapping(params = "action=data_develop.get_mine_car_info")
	public String getCarInfo(String params, HttpServletResponse response)
			throws IOException {
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setContentType("text/json;charset=utf-8");
		PrintWriter out = response.getWriter();

		System.out.println("ObdAndroidController.getCarInfo()");

		String param = request.getQueryString();

		// 请求服务器
		String res = WeixinUtil
				.httpsend("http://open.api.dbscar.com/?" + param);

		JSONObject jsonObject = JSONObject.fromObject(res);
		out.write(jsonObject.toString());
		out.flush();
		out.close();
		return null;
	}

	// 加载dpusys.ini文件的内容
	@RequestMapping(params = "action=data_develop.get_config_info")
	public String downloadSysIni(String params, HttpServletResponse response)
			throws IOException {
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setContentType("text/json;charset=utf-8");
		PrintWriter out = response.getWriter();

		System.out.println("ObdAndroidController.downloadSysIni()");
		
		String param = request.getQueryString();

		// 请求服务器
		String res = WeixinUtil
				.httpsend("http://open.api.dbscar.com/?" + param);

		JSONObject jsonObject = JSONObject.fromObject(res);
		out.write(jsonObject.toString());
		out.flush();
		out.close();
		return null;
	}
}
