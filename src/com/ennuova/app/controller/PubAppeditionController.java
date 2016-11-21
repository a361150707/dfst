package com.ennuova.app.controller;

import java.io.PrintWriter;
import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ennuova.app.service.PubAppeditionService;
import com.ennuova.common.CommonClass;
import com.ennuova.entity.CnDzwl;
import com.ennuova.entity.PubAppediton;
import com.ennuova.entity.PubCarinfo;
import com.ennuova.util.Util;

/**APP版本控制器
 * @author 李智辉
 * @time 2016-2-23
 */
@Controller
@RequestMapping("/edition.do")
public class PubAppeditionController {
	@Resource
	private PubAppeditionService appeditionService;
	
	@RequestMapping(params="method=getEditionInfo")
	public ModelAndView getEditionInfo(String params,HttpServletRequest request,HttpServletResponse response){
		
		String json = "";
		JSONObject jsonObject = new JSONObject();
		String content = Util.aesDecrypt(params);
		if (content!=null) {
			Map<String, Object> jMap = CommonClass.getMapByJson(content);
			String appType = jMap.get("appType").toString();
			PubAppediton pubAppedition = appeditionService.getPubAppediton(appType);
			jsonObject.put("success", true);
			jsonObject.put("PubAppediton", pubAppedition);
		}else {
			jsonObject.put("success", false);
		}
		json = jsonObject.toString();
		try {
			response.setHeader("Content-type", "application/json;charset=UTF-8");
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/json;charset=UTF-8");
			response.setHeader("Access-Control-Allow-Origin", "*");
			PrintWriter pw = response.getWriter();
			pw.write(json);
			pw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
