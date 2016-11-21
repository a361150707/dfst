package com.ennuova.app.controller;

import java.io.PrintWriter;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ennuova.app.service.CnXcrecodeService;
import com.ennuova.common.CommonClass;
import com.ennuova.entity.CnXcrecode;
import com.ennuova.entity.XcRecord;
import com.ennuova.entity.XcrecodeVO;
import com.ennuova.util.Util;

@Controller("cnXcrecodeController")
@RequestMapping(value="/recode.do")
public class CnXcrecodeController {
	@Resource
	private CnXcrecodeService cnXcrecodeService;
	
	@RequestMapping(params="method=getLbsList")
	public ModelAndView  getLbsList(String params,HttpServletRequest request,HttpServletResponse response){
		String json = "";
		String content = Util.aesDecrypt(params);
		if (content!=null) {					//判断参数是否为空
			Map<String, Object> jMap = CommonClass.getMapByJson(content);
			Long fclid = Long.valueOf(jMap.get("fclid").toString());
			int status = Integer.valueOf(jMap.get("status").toString());
			Map<String, Object> map = new HashMap<String, Object>();
			map = this.cnXcrecodeService.getLbsList(fclid, 3);
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("cnXcrecodeList", map.get("cnXcrecodes"));
			jsonObject.put("addressList", map.get("addressList"));
			jsonObject.put("cityList", map.get("cityList"));
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
	@RequestMapping(params="method=getAYearData")
	public ModelAndView  getAYearData(String params,HttpServletRequest request,HttpServletResponse response){
		String json = "";
		String content = Util.aesDecrypt(params);
		if (content!=null) {					//判断参数是否为空
			Map<String, Object> jMap = CommonClass.getMapByJson(content);
			Long fclid = Long.valueOf(jMap.get("fclid").toString());
			String checkTime = jMap.get("checkTime").toString();
			List<XcrecodeVO> xcrecodeVOs = this.cnXcrecodeService.getXcRecordList(fclid,checkTime);
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("xcRecords", xcrecodeVOs);
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
