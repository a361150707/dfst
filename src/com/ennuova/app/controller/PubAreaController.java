package com.ennuova.app.controller;

import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ennuova.app.service.PubAreaService;
import com.ennuova.common.CommonClass;
import com.ennuova.entity.PubArea;
import com.ennuova.util.Util;

@Controller("pubAreaController")
@RequestMapping("/pubArea.do")
public class PubAreaController {

	@Resource
	private PubAreaService pubAreaService;
	
	/**
	 * 查询地区对应的子项集合;fid为上级id，fgrade为等级（1：省级，2：市级，3：区（县）级），查询省份时，fid为0
	 * @Description: TODO
	 * @param @param params
	 * @param @param request
	 * @param @param response
	 * @param @return
	 * @param @throws Exception   
	 * @return String  
	 * @author felix
	 * @date 2015-12-9
	 */
	@RequestMapping(params = "method=queryPubArea")
	public String queryPubArea(String params,HttpServletRequest request,HttpServletResponse response) throws Exception{
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setContentType("text/json;charset=utf-8");
		String content = Util.aesDecrypt(params);
		Map<String ,Object> jMap=CommonClass.getMapByJson(content);
		System.out.println("获取省份、城市、区域");
		long fid=Long.valueOf(jMap.get("fid").toString());
		long fgrade=Long.valueOf(jMap.get("fgrade").toString());
		List<PubArea> areaList=pubAreaService.queryPubArea(fid, fgrade);
		PrintWriter out=response.getWriter();
		out.write(JSONArray.fromObject(areaList).toString());
		System.out.println(JSONArray.fromObject(areaList).toString());
		out.flush();
		out.close();
		return null;
	}
	
	/**
	 * 查询关键字的城市
	 * @Description: TODO
	 * @param @param params
	 * @param @param request
	 * @param @param response
	 * @param @return
	 * @param @throws Exception   
	 * @return String  
	 * @author felix
	 * @date 2015-12-9
	 */
	@RequestMapping(params = "method=queryCityByName")
	public String queryCityByName(String params,HttpServletRequest request,HttpServletResponse response) throws Exception{
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setContentType("text/json;charset=utf-8");
		String content = Util.aesDecrypt(params);
		Map<String ,Object> jMap=CommonClass.getMapByJson(content);
		String cname=jMap.get("cname").toString();
		List<PubArea> areaList=pubAreaService.queryCityByName(cname);
		PrintWriter out=response.getWriter();
		out.write(JSONArray.fromObject(areaList).toString());
		out.flush();
		out.close();
		return null;
	}
}
