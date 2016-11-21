package com.ennuova.app.controller;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ennuova.app.service.CnDzwlService;
import com.ennuova.app.service.CnSsxcrecodService;
import com.ennuova.common.CommonClass;
import com.ennuova.entity.CnDzwl;
import com.ennuova.entity.PubCarinfo;
import com.ennuova.util.Util;

/**电子围栏控制器
 * @author 李智辉 
 * 2015-12-15下午4:09:21
 */
@Controller
@RequestMapping("/dzwl.do")
public class CnDzwlController {
	@Resource
	private CnDzwlService cnDzwlService;
	@Resource
	private CnSsxcrecodService cnSsxcrecodService;
	/**添加一条电子围栏信息
	 * @param params
	 * @param request
	 * @param response
	 * @return null
	 */
	@RequestMapping(params="method=addFence")
	public ModelAndView  addFence(String params,HttpServletRequest request,HttpServletResponse response){
		
		String json = "";
		JSONObject jsonObject = new JSONObject();
		String content = Util.aesDecrypt(params);
		if (content!=null) {
			Map<String, Object> jMap = CommonClass.getMapByJson(content);
			Long fclid = Long.valueOf(jMap.get("fclid").toString());
			Integer fsrsc = Integer.valueOf(jMap.get("finto").toString());
			String fname = jMap.get("fname").toString();
			Date date = new Date();
			Double flng = Double.valueOf(jMap.get("flng").toString());
			Double flat = Double.valueOf(jMap.get("flat").toString());
			Double fradius = Double.valueOf(jMap.get("fradius").toString());
			CnDzwl cnDzwl = new CnDzwl();
			PubCarinfo pubCarinfo = new PubCarinfo();
			pubCarinfo.setId(fclid);
			cnDzwl.setPubCarinfo(pubCarinfo);
			cnDzwl.setFsrsc(fsrsc);
			cnDzwl.setFname(fname);
			cnDzwl.setFcreatetime(date);
			cnDzwl.setFlng(flng);
			cnDzwl.setFlat(flat);
			cnDzwl.setFradius(fradius);
			cnDzwl.setFsrsc(0);
			cnDzwl.setFeffect(1);
			boolean success = this.cnDzwlService.addDzwl(cnDzwl);
			if (success) {
				jsonObject.put("success", success);
				json = jsonObject.toString();
				
			}else {
				jsonObject.put("success", success);
				json = jsonObject.toString();
			}
		}else {
			jsonObject.put("success", false);
			json = jsonObject.toString();
		}
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
	@RequestMapping(params="method=getFenceList")
	public ModelAndView  getFenceList(String params,HttpServletRequest request,HttpServletResponse response){
		String json = "";
		JSONObject jsonObject = new JSONObject();
		String content = Util.aesDecrypt(params);
		List<CnDzwl> cnDzwls = new ArrayList<CnDzwl>();
		if (content!=null) {
			Map<String, Object> jMap = CommonClass.getMapByJson(content);
			Long fclid = Long.valueOf(jMap.get("fclid").toString());
			cnDzwls = this.cnDzwlService.getCnDzwlList(fclid);
			Map<String, Object> map = cnSsxcrecodService.getCarNowLBS(fclid);
			jsonObject.put("cnDzwlList", cnDzwls);
			jsonObject.put("lbs", map);
			json = jsonObject.toString();
		}else {
			jsonObject.put("success", false);
			json = jsonObject.toString();
		}
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
	@RequestMapping(params="method=setFence")
	public ModelAndView  setFence(String params,HttpServletRequest request,HttpServletResponse response){
		String json = "";
		JSONObject jsonObject = new JSONObject();
		String content = Util.aesDecrypt(params);
		if (content!=null) {
			Map<String, Object> jMap = CommonClass.getMapByJson(content);
			Long fclid = Long.valueOf(jMap.get("fclid").toString());
			Long fid = Long.valueOf(jMap.get("fid").toString());
			int fsrcs = Integer.valueOf(jMap.get("fsrcs").toString());
			boolean success = this.cnDzwlService.setDzwl(fclid, fid, fsrcs);
			jsonObject.put("success", success);
			json = jsonObject.toString();
		}else {
			jsonObject.put("success", false);
			json = jsonObject.toString();
		}
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
	
	@RequestMapping(params="method=deleteFence")
	public ModelAndView  deleteFence(String params,HttpServletRequest request,HttpServletResponse response){
		String json = "";
		JSONObject jsonObject = new JSONObject();
		String content = Util.aesDecrypt(params);
		if (content!=null) {
			Map<String, Object> jMap = CommonClass.getMapByJson(content);
			Long fid = Long.valueOf(jMap.get("fid").toString());
			boolean success = this.cnDzwlService.deleteFence(fid);
			jsonObject.put("success", success);
			json = jsonObject.toString();
		}else {
			jsonObject.put("success", false);
			json = jsonObject.toString();
		}
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
	
	@RequestMapping(params="method=cancelFence")
	public ModelAndView  cancelFence(String params,HttpServletRequest request,HttpServletResponse response){
		String json = "";
		JSONObject jsonObject = new JSONObject();
		String content = Util.aesDecrypt(params);
		if (content!=null) {
			Map<String, Object> jMap = CommonClass.getMapByJson(content);
			Long fid = Long.valueOf(jMap.get("fid").toString());
			boolean success = this.cnDzwlService.cancelFence(fid);
			jsonObject.put("success", success);
			json = jsonObject.toString();
		}else {
			jsonObject.put("success", false);
			json = jsonObject.toString();
		}
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
