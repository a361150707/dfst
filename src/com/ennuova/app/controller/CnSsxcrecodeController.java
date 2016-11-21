package com.ennuova.app.controller;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ennuova.app.config.AppObjectResult;
import com.ennuova.app.config.SystemInfo;
import com.ennuova.app.service.CnSsxcrecodService;
import com.ennuova.common.CommonClass;
import com.ennuova.controller.GeneralControl;
import com.ennuova.entity.CarMonitor;
import com.ennuova.util.DateUtils;
import com.ennuova.util.JSONUtil;
import com.ennuova.util.StringUtil;
import com.ennuova.util.Util;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

/**实时行程记录控制器
 * @author 李智辉 
 * 2015-12-11上午11:21:44
 */
@Controller
@RequestMapping("/nowrecord.do")
public class CnSsxcrecodeController extends GeneralControl{
	@Resource
	private CnSsxcrecodService cnSsxcrecodService;
	@RequestMapping(params="method=getNowLbs")
	public ModelAndView  getLbsList(String params,HttpServletRequest request,HttpServletResponse response){
		String json = "";
			String content = Util.aesDecrypt(params);
			Map<String, Object> map = new HashMap<String, Object>();
			if (content!=null) {
				Map<String, Object> jMap = CommonClass.getMapByJson(content);
				Long fclid = Long.valueOf(jMap.get("fclid").toString());
				map = this.cnSsxcrecodService.getCarNowLBS(fclid);
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("lat", map.get("lat"));
				jsonObject.put("address", map.get("address"));
				jsonObject.put("lng", map.get("lng"));
				jsonObject.put("success", map.get("success"));
				json = jsonObject.toString();
			}else {
				JSONObject jsonObject = new JSONObject();
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
	@RequestMapping(params="method=getMonitorInfo")
	public ModelAndView  getMonitorInfo(String params,HttpServletRequest request,HttpServletResponse response){
		String json = "";
			String content = Util.aesDecrypt(params);
			Map<String, Object> map = new HashMap<String, Object>();
			if (content!=null) {
				Map<String, Object> jMap = CommonClass.getMapByJson(content);
				Long fclid = Long.valueOf(jMap.get("fclid").toString());
				CarMonitor carMonitor = this.cnSsxcrecodService.getCarMonitor(fclid);
				JSONObject jsonObject = new JSONObject();
				if (carMonitor.getId()!=null) {
					jsonObject.put("success", true);
					jsonObject.put("carMonitor", carMonitor);
				}else {
					jsonObject.put("success", false);
				}
				json = jsonObject.toString();
			}else {
				JSONObject jsonObject = new JSONObject();
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
	@RequestMapping(params="method=getMonitorInfoMax")
	public ModelAndView  getMonitorInfoMax(String params,HttpServletRequest request,HttpServletResponse response){
		String json = "";
			String content = Util.aesDecrypt(params);
			Map<String, Object> map = new HashMap<String, Object>();
			if (content!=null) {
				Map<String, Object> jMap = CommonClass.getMapByJson(content);
				Long fclid = Long.valueOf(jMap.get("fclid").toString());
				CarMonitor carMonitor = this.cnSsxcrecodService.getCarMonitorMax(fclid);
				JSONObject jsonObject = new JSONObject();
				if (carMonitor.getId()!=null) {
					jsonObject.put("success", true);
					jsonObject.put("carMonitor", carMonitor);
				}else {
					jsonObject.put("success", false);
				}
				json = jsonObject.toString();
			}else {
				JSONObject jsonObject = new JSONObject();
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
	
	@RequestMapping(params="method=getRealTimeLocation")
	public ModelAndView  getRealTimeLocation(String params,HttpServletRequest request,HttpServletResponse response){
		String json = "";
		JSONObject jsonObject = new JSONObject();
			String content = Util.aesDecrypt(params);
			Map<String, Object> map = new HashMap<String, Object>();
			if (content!=null) {
				Map<String, Object> jMap = CommonClass.getMapByJson(content);
				Long fclid = Long.valueOf(jMap.get("fclid").toString());
				if(fclid!=null){
					map = this.cnSsxcrecodService.getRealTimeLocation(fclid);
					jsonObject.put("lat", map.get("lat"));
					jsonObject.put("lng", map.get("lng"));
					jsonObject.put("success", map.get("success"));
					json = jsonObject.toString();
				}else {
					jsonObject.put("success", false);
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
	@RequestMapping(params="method=getLBSList",method=RequestMethod.POST)
	@ResponseBody
	@ApiOperation(value = "根据车辆Id和行程Id获取该段行程的LBS集合",notes="{carId:车辆ID,xcrodeId:行程Id}")
	public void getLBSList(@ApiParam(value="输入{carId:车辆ID,xcrodeId:行程Id}")String params,HttpServletResponse response) throws Exception {	
		String getParams = Util.aesDecrypt(params);
		Map<String, Object> jsonMap = CommonClass.getMapByJson(getParams);
		Long carId = Long.valueOf(jsonMap.get("carId")+"");
		Long xcrodeId = Long.valueOf(jsonMap.get("xcrodeId")+"");
		AppObjectResult appObjectResult = null;
		if(StringUtil.isNotEmpty(carId)&&StringUtil.isNotEmpty(xcrodeId)){
			List<Map<String, Object>> list = cnSsxcrecodService.getLBSList(xcrodeId, carId);
			appObjectResult = new AppObjectResult(SystemInfo.QUERY_SUCCESS.getCode(), SystemInfo.QUERY_SUCCESS.getMsg(),list);
		}else{
			appObjectResult = new AppObjectResult(SystemInfo.ILLEGAL_PARAMETER.getCode(), SystemInfo.ILLEGAL_PARAMETER.getMsg());
		}
		sendDataResponse(response,JSONUtil.getJSONString(appObjectResult));
	}
	@RequestMapping(params="method=getCnSsxcrecodeByTime",method=RequestMethod.POST)
	@ResponseBody
	@ApiOperation(value = "根据车辆Id和开始时间结束时间获取该段行程的LBS集合",notes="{carId:车辆ID,startTime:开始时间,endTime:结束时间}")
	public void getCnSsxcrecodeByTime(@ApiParam(value="输入{carId:车辆ID,startTime:开始时间,endTime:结束时间}")String params,HttpServletResponse response) throws Exception {	
		String getParams = Util.aesDecrypt(params);
		Map<String, Object> jsonMap = CommonClass.getMapByJson(getParams);
		Long carId = Long.valueOf(jsonMap.get("carId")+"");
		String startTime = jsonMap.get("startTime")+"";
		String endTime = jsonMap.get("endTime")+"";
		AppObjectResult appObjectResult = null;
		if(StringUtil.isNotEmpty(carId)&&StringUtil.isNotEmpty(startTime)&&StringUtil.isNotEmpty(endTime)){
			List<Map<String, Object>> list = cnSsxcrecodService.getCnSsxcrecodeByTime(startTime, endTime, carId);
			appObjectResult = new AppObjectResult(SystemInfo.QUERY_SUCCESS.getCode(), SystemInfo.QUERY_SUCCESS.getMsg(),list);
		}else{
			appObjectResult = new AppObjectResult(SystemInfo.ILLEGAL_PARAMETER.getCode(), SystemInfo.ILLEGAL_PARAMETER.getMsg());
		}
		sendDataResponse(response,JSONUtil.getJSONString(appObjectResult));
	}
}
