package com.ennuova.app.controller;

import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ennuova.app.service.OwNewcarService;
import com.ennuova.app.service.OwNewcarindexService;
import com.ennuova.app.service.OwNewsctService;
import com.ennuova.common.CommonClass;
import com.ennuova.entity.OwNewcar;
import com.ennuova.entity.OwNewcarindex;
import com.ennuova.util.Util;

/**
 * app新车上市控制器
 * 
 * @author felix
 * 
 */
@Controller("owNewcarindexController")
@RequestMapping("/owNewcarindex.do")
public class OwNewcarindexController {

	@Resource
	private OwNewcarindexService owNewcarindexService;

	@Resource
	private OwNewcarService owNewcarService;
	
	@Resource
	private OwNewsctService owNewsctService;

	@RequestMapping(params = "method=newcarindex")
	public String newcarindex(String params, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setContentType("text/json;charset=utf-8");
		String content = "";
		content = Util.aesDecrypt(params);
		Map<String, Object> jMap = CommonClass.getMapByJson(content);
		long fcompany = Long.valueOf(jMap.get("companyId").toString());
		System.out.println("新车上市首页" + fcompany);
		List<OwNewcar> owNewcarList = owNewcarService.queryAllByState(2l,fcompany);
		List<OwNewcarindex> owNewcarindexList = owNewcarindexService.queryNewcarindex(fcompany);	
		JSONObject json = new JSONObject();
		json.put("ncarlist", owNewcarList);
		json.put("indexlist", owNewcarindexList);
		PrintWriter out = response.getWriter();
		out.write(json.toString());
		out.flush();
		out.close();
		return null;
	}

	/**
	 * 
	 * @Description: 查看新车上市详情，并增加浏览量 
	 * @author felix
	 * @date 2015-12-10
	 */
	@RequestMapping(params = "method=newcardetail")
	public String newcardetail(String params, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setContentType("text/json;charset=utf-8");
		String content = "";
		content = Util.aesDecrypt(params);
		Map<String, Object> jMap = CommonClass.getMapByJson(content);
		long id = Long.valueOf(jMap.get("newcarid").toString());
		System.out.println("新车上市详情" + id);
		OwNewcar newcar = owNewcarService.getNewcarById(id);
		int i=owNewsctService.growthFviewCount(id, "OW_NEWCAR");
		System.out.println("增长："+i);
		PrintWriter out = response.getWriter();
		out.write(JSONObject.fromObject(newcar).toString());
		out.flush();
		out.close();
		return null;
	}
}
