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

import com.ennuova.app.service.OwDiscountactionService;
import com.ennuova.app.service.OwDiscountindexService;
import com.ennuova.app.service.OwNewsctService;
import com.ennuova.common.CommonClass;
import com.ennuova.entity.OwDiscountaction;
import com.ennuova.entity.OwDiscountindex;
import com.ennuova.util.Util;

@Controller("owDiscountindexController")
@RequestMapping("/owDiscountindex.do")
public class OwDiscountindexController {

	@Resource
	private OwDiscountindexService owDiscountindexService;

	@Resource
	private OwDiscountactionService owDiscountService;
	
	@Resource
	private OwNewsctService owNewsctService;

	@RequestMapping(params = "method=discountindex")
	public String discountindex(String params, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setContentType("text/json;charset=utf-8");
		String content = "";
		content = Util.aesDecrypt(params);
		Map<String, Object> jMap = CommonClass.getMapByJson(content);
		long fcompany = Long.valueOf(jMap.get("companyId").toString());
		System.out.println("优惠促销" + fcompany);
		List<OwDiscountaction> owDiscountList = owDiscountService.queryAllByState(2l,fcompany);
		List<OwDiscountindex> owDiscountindexList = owDiscountindexService.queryDiscountindex(fcompany);	
		JSONObject json = new JSONObject();
		json.put("discountlist", owDiscountList);
		json.put("indexlist", owDiscountindexList);
		PrintWriter out = response.getWriter();
		out.write(json.toString());
		out.flush();
		out.close();
		System.out.println(json.toString());
		return null;
	}

	/**
	 * 
	 * @Description: 查看折扣详情，并增加浏览量 
	 * @author felix
	 * @date 2015-12-10
	 */
	@RequestMapping(params = "method=discountdetail")
	public String discountdetail(String params, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setContentType("text/json;charset=utf-8");
		String content = "";
		content = Util.aesDecrypt(params);
		Map<String, Object> jMap = CommonClass.getMapByJson(content);
		long id = Long.valueOf(jMap.get("discountid").toString());
		System.out.println("优惠促销详情" + id);
		OwDiscountaction discount = owDiscountService.getDiscountById(id);
		int i=owNewsctService.growthFviewCount(id, "OW_DISCOUNTACTION");
		System.out.println("增长："+i);
		PrintWriter out = response.getWriter();
		out.write(JSONObject.fromObject(discount).toString());
		out.flush();
		out.close();
		System.out.println(JSONObject.fromObject(discount).toString());
		return null;
	}
}
