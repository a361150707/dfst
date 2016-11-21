package com.ennuova.app.controller;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ennuova.app.service.CnInfoService;
import com.ennuova.common.CommonClass;
import com.ennuova.entity.CnExpress;
import com.ennuova.entity.CnInfo;
import com.ennuova.entity.CnInfodetail;
import com.ennuova.entity.MessageListVO;
import com.ennuova.util.ConvertTools;
import com.ennuova.util.StringUtil;
import com.ennuova.util.Util;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/message.do")
public class CnInfoController {

	@Resource
	private CnInfoService cnInfoService;
	
	/**
	 * 获取我的消息
	 * @param params
	 * @param response
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params = "method=getCusMsg")
	public String getCusMes(String params,HttpServletResponse response,HttpServletRequest request) throws Exception{
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setContentType("text/json;charset=utf-8");
		PrintWriter out = response.getWriter();
		System.out.println("msg");    
		String params_log = "";
		params_log = Util.aesDecrypt(params);
		System.out.println(params_log);
		Map<String, Object> jsonMap=CommonClass.getMapByJson(params_log);
		long cusId = Long.parseLong(jsonMap.get("cusId").toString());
		/*List<CnInfo> infoList = cnInfoService.getCusMsg(cusId);
		List<CnInfo> sysList = new ArrayList<CnInfo>();
	    List<CnInfo> zxList = new ArrayList<CnInfo>();
		for (int i = 0; i < infoList.size(); i++) {
			if(infoList.get(i).getFmodule().equals("系统")){
				sysList.add(infoList.get(i));
			}else{
				zxList.add(infoList.get(i));
			}
		}*/
		List<CnInfo> infoList = cnInfoService.UserMsgCore(cusId);
		JSONObject json = new JSONObject();
		json.put("infoList", infoList);
		/*json.put("sysList", sysList);
		json.put("zxList", zxList);*/
		System.out.println(json.toString());
		out.write(json.toString());
		out.flush();
		out.close();
		return null;
	}
	
	/**
	 * 获取我的消息
	 * @param params
	 * @param response
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params = "method=getMessageList")
	public String getMessageList(String params,HttpServletResponse response,HttpServletRequest request) throws Exception{
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setContentType("text/json;charset=utf-8");
		PrintWriter out = response.getWriter();
		System.out.println("msg");    
		String params_log = "";
		params_log = Util.aesDecrypt(params);
		System.out.println(params_log);
		Map<String, Object> jsonMap=CommonClass.getMapByJson(params_log);
		long cusId = Long.parseLong(jsonMap.get("cusId").toString());
		/*List<CnInfo> infoList = cnInfoService.getCusMsg(cusId);
		List<CnInfo> sysList = new ArrayList<CnInfo>();
	    List<CnInfo> zxList = new ArrayList<CnInfo>();
		for (int i = 0; i < infoList.size(); i++) {
			if(infoList.get(i).getFmodule().equals("系统")){
				sysList.add(infoList.get(i));
			}else{
				zxList.add(infoList.get(i));
			}
		}*/
		List<MessageListVO> infoList = cnInfoService.getMessageList(cusId);
		JSONObject json = new JSONObject();
		json.put("infoList", infoList);
		System.out.println(json.toString());
		out.write(json.toString());
		out.flush();
		out.close();
		return null;
	}
	@RequestMapping(params = "method=setBadge")
	public String setBadge(String params,HttpServletResponse response,HttpServletRequest request) throws Exception{
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setContentType("text/json;charset=utf-8");
		PrintWriter out = response.getWriter();
		String params_log = "";
		params_log = Util.aesDecrypt(params);
		Map<String, Object> jsonMap=CommonClass.getMapByJson(params_log);
		long cusId = Long.parseLong(jsonMap.get("cusId").toString());
		int messageType = ConvertTools.stringToInt(jsonMap.get("messageType").toString());
		cnInfoService.updateBadge(cusId, messageType);
		JSONObject json = new JSONObject();
		out.write(json.toString());
		out.flush();
		out.close();
		return null;
	}
	/**
	 * 获取消息详情
	 * @param fsubject
	 * @param cusId
	 * @return
	 */
	@RequestMapping(params = "method=getDetailMsg")
	public String getDetailMsg(String params,HttpServletResponse response) throws Exception{
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setContentType("text/json;charset=utf-8");
		System.out.println("getDetailMsg");    
		String params_log = "";
		params_log = Util.aesDecrypt(params);
		System.out.println(params_log);
		Map<String, Object> jsonMap=CommonClass.getMapByJson(params_log);
		long cusId = Long.parseLong(jsonMap.get("cusId").toString());
		int page = Integer.parseInt(jsonMap.get("page").toString());
		String module = jsonMap.get("module").toString();
		CnInfo info = new CnInfo();
		info.setCusId(cusId);
		info.setFmodule(module);
		List<CnInfo> infoList = cnInfoService.getModuleMsg(info, page, 10);
		//cnInfoService.getDetailMsg(fsubject, cusId);
		JSONObject json = new JSONObject();
		json.put("infoList", infoList);
		System.out.println("json:"+json.toString());
		PrintWriter out = response.getWriter();
		out.write(json.toString());
		out.flush();
		out.close();
		return null;
	}

	/**
	 * 获取消息详情列表
	 * @param fsubject
	 * @param cusId
	 * @return
	 */
	@RequestMapping(params = "method=getDetailMsgList")
	public String getDetailMsgList(String params,HttpServletResponse response) throws Exception{
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setContentType("text/json;charset=utf-8");
		String params_log = "";
		params_log = Util.aesDecrypt(params);
		Map<String, Object> jsonMap=CommonClass.getMapByJson(params_log);
		long cusId = Long.parseLong(jsonMap.get("cusId").toString());
		long maxId = Long.parseLong(jsonMap.get("maxId").toString());
		long messageType = Long.parseLong(jsonMap.get("message_type").toString());
		List<CnInfodetail> infoList = cnInfoService.getMoreCninfo(cusId, maxId, messageType);
		JSONObject json = new JSONObject();
		json.put("infoList", infoList);
		PrintWriter out = response.getWriter();
		out.write(json.toString());
		out.flush();
		out.close();
		return null;
	}

	/**
	 * 删除模块消息
	 * @param fsubject
	 * @param cusId
	 * @return
	 */
	@RequestMapping(params = "method=deleteSubjectMsg")
	public String deleteSubjectMsg(String params,HttpServletResponse response) throws Exception{
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setContentType("text/json;charset=utf-8");
		System.out.println("deleteSubjectMsg");    
		String params_log = "";
		params_log = Util.aesDecrypt(params);
		System.out.println(params_log);
		Map<String, Object> jsonMap=CommonClass.getMapByJson(params_log);
		long cusId = Long.parseLong(jsonMap.get("cusId").toString());
		String fsubject = jsonMap.get("fsubject").toString();
		int result = cnInfoService.deleteSubjectMsg(fsubject, cusId);
		JSONObject json = new JSONObject();
		json.put("result", result);
		PrintWriter out = response.getWriter();
		out.write(json.toString());
		out.flush();
		out.close();
		return null;
	}
	
	/**
	 * 批量删除具体消息
	 * @param detailId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(params = "method=deleteMsg")
	public String deleteMsg(String params,HttpServletResponse response) throws Exception{
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setContentType("text/json;charset=utf-8");
		System.out.println("deleteMsg");    
		String params_log = "";
		params_log = Util.aesDecrypt(params);
		System.out.println(params_log);
		//Map<String, Object> jsonMap=CommonClass.getMapByJson(params_log);
		JSONObject idjson =  JSONObject.fromObject(params_log);
		List<Long> ids = new ArrayList<Long>();
		ids = idjson.getJSONArray("ids");
		JSONObject json = new JSONObject();
		int result = cnInfoService.deleteMsg(ids);
		json.put("result", result);
		PrintWriter out = response.getWriter();
		out.write(json.toString());
		out.flush();
		out.close();
		return null;
	}
	@RequestMapping(params = "method=alertMessage")
	public String alertMessage(String params,HttpServletResponse response) throws Exception{
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setContentType("text/json;charset=utf-8");
		String params_log = "";
		params_log = Util.aesDecrypt(params);
		Map<String, Object> jsonMap=CommonClass.getMapByJson(params_log);
		boolean success = false;
		String getUserId = jsonMap.get("userId")+"";
		if(StringUtil.isNotEmpty(getUserId)){
			long userId = Long.valueOf(getUserId);
			success = cnInfoService.alertMessage(userId);
		}
		JSONObject json = new JSONObject();
		json.put("success", success);
		PrintWriter out = response.getWriter();
		out.write(json.toString());
		out.flush();
		out.close();
		return null;
	}
	@RequestMapping(params = "method=saveUserSimSendInfo")
	public String saveUserSimSendInfo(String params,HttpServletResponse response) throws Exception{
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setContentType("text/json;charset=utf-8");
		JSONObject json = new JSONObject();
		String params_log = "";
		params_log = Util.aesDecrypt(params);
		Map<String, Object> jsonMap=CommonClass.getMapByJson(params_log);
		boolean success = false;
		try {
			long userId = Long.parseLong(jsonMap.get("userId").toString());
			String userName = jsonMap.get("userName").toString();
			String userMobile = jsonMap.get("userMobile").toString();
			String userAddress = jsonMap.get("userAddress").toString();
			CnExpress cnExpress = new CnExpress();
			cnExpress.setUserId(userId);
			cnExpress.setUserName(userName);
			cnExpress.setUserMobile(userMobile);
			cnExpress.setUserAddress(userAddress);
			success = cnInfoService.saveUserSimSendInfo(cnExpress);
			json.put("success", success);
		} catch (Exception e) {
			json.put("success", false);
		}
		PrintWriter out = response.getWriter();
		out.write(json.toString());
		out.flush();
		out.close();
		return null;
	}
}
