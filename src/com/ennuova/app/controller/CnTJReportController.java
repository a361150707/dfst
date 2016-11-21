package com.ennuova.app.controller;

import java.io.PrintWriter;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ennuova.app.service.CnTJReportService;
import com.ennuova.common.CommonClass;
import com.ennuova.entity.CnGzm;
import com.ennuova.entity.CnTjdetail;
import com.ennuova.entity.CnTjgzdetail;
import com.ennuova.entity.CnTjreport;
import com.ennuova.entity.CnTjreportVO;
import com.ennuova.util.Util;

@Controller
@RequestMapping("/report.do")
public class CnTJReportController {

	@Resource
	private CnTJReportService reportService;
	@RequestMapping(params="method=addReport")
	public ModelAndView  addReport(String params,HttpServletRequest request,HttpServletResponse response){
		System.out.println(new Date().getTime());
		String json = "";
		JSONObject jsonObject = new JSONObject();
		String content = Util.aesDecrypt(params);
		if (content!=null) {
			Map<String, Object> jMap = CommonClass.getMapByJson(content);
			Long fid = Long.valueOf(jMap.get("fclid").toString());
			String fcph = jMap.get("fcph").toString();
			String jsonContent = jMap.get("jsonContent").toString();
			boolean success = this.reportService.addCnTJReport(jsonContent, fid, fcph);
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
			System.out.println(new Date().getTime());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 获取体检报告
	 * @author 伟灿
	 * @time 2016-1-5
	 * @param fclid 车辆id
	 * @return
	 */
	@RequestMapping(params = "method=queryTjReport")
	public String queryTjReport(String params,HttpServletRequest request,HttpServletResponse response) throws Exception{
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setContentType("text/json;charset=utf-8");
		String content = Util.aesDecrypt(params);
		Map<String,Object> jMap = CommonClass.getMapByJson(content);
		long fclid = Long.valueOf(jMap.get("fclid").toString());
		long id = Long.valueOf(jMap.get("tjId").toString());
		List<CnTjreportVO> reportList = reportService.queryTjReport(fclid,id);
		PrintWriter out = response.getWriter();
		out.write(JSONArray.fromObject(reportList).toString());
		out.flush();
		out.close();
		return null;
	}

	/**
	 * 根据体检报告id获取体检明细
	 * @author 伟灿
	 * @time 2016-1-5
	 * @param ftjid 体检报告id
	 * @return
	 */
	@RequestMapping(params = "method=queryTjDetail")
	public String queryTjDetail(String params,HttpServletRequest request,HttpServletResponse response) throws Exception{
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setContentType("text/json;charset=utf-8");
		String content = Util.aesDecrypt(params);
		Map<String,Object> jMap = CommonClass.getMapByJson(content);
		long ftjid = Long.valueOf(jMap.get("ftjid").toString());
		List<CnTjgzdetail> details = reportService.queryTjDetail(ftjid);
		PrintWriter out = response.getWriter();
		out.write(JSONArray.fromObject(details).toString());
		out.flush();
		out.close();
		return null;
	}
	
	/**根据故障码查询故障信息 
	 * @param params
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(params = "method=getFaultCode")
	public ModelAndView  getFaultCode(String params,HttpServletRequest request,HttpServletResponse response){
		String json = "";
		JSONObject jsonObject = new JSONObject();
		String content = Util.aesDecrypt(params);
		if (content!=null) {
			Map<String, Object> jMap = CommonClass.getMapByJson(content);
			String faultCode = jMap.get("faultCode").toString();
			CnGzm cnGzm = this.reportService.getCnGzm(faultCode);
			if(cnGzm.getId()!=null){
				jsonObject.put("cnGzm", cnGzm);
				jsonObject.put("success", true);
			}else{
				jsonObject.put("success", false);
			}
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
	@RequestMapping(params="method=getTjNum")
	public ModelAndView  getTjNum(String params,HttpServletRequest request,HttpServletResponse response){
		String json = "";
		JSONObject jsonObject = new JSONObject();
		String content = Util.aesDecrypt(params);
		if (content!=null) {
			Map<String, Object> jMap = CommonClass.getMapByJson(content);
			long ftjid = Long.valueOf(jMap.get("ftjid").toString());
			int num = this.reportService.getTJNum(ftjid);
			jsonObject.put("faultNum", num);
			jsonObject.put("success", true);
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
