package com.ennuova.obd.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ennuova.obd.http.WeixinUtil;
import com.ennuova.obd.tools.SignUtils;
import com.ennuova.obd.util.GoloApplication;

@Controller("obdReleaseController")
@RequestMapping("/obdRelease.do")
public class ObdReleaseController {

	/**
	 * 取消激活
	 * @param params
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(params = "action=releaseConnector")
	public String releaseConnector(String serial_no,
			HttpServletResponse response) throws IOException {
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setContentType("text/json;charset=utf-8");
		PrintWriter out = response.getWriter();

		Map<String, String> params = new HashMap<String, String>();
		params.put("action",
				"product_service.cancel_mycar_register");
		params.put("develop_id", GoloApplication.develop_id);
		params.put("time", Long.toString(System
				.currentTimeMillis() / 1000));
		params.put("serial_no", serial_no);
		String sign = SignUtils.getSign(
				GoloApplication.delevop_key, params);
		params.put("sign", sign);
		String param = SignUtils.createLinkString(params);
		System.out.println("http://open.api.dbscar.com/?"
				+ param);
		String res = WeixinUtil.httpsend("http://open.api.dbscar.com/?"
				+ param);
		
		
		JSONObject jsonObject=JSONObject.fromObject(res);

		out.write(jsonObject.toString());
		out.flush();
		out.close();
		return null;
	}
	
}
