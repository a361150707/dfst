package com.ennuova.app.controller;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ennuova.app.config.AppObjectResult;
import com.ennuova.app.config.SystemInfo;
import com.ennuova.app.service.TSUserService;
import com.ennuova.common.CommonClass;
import com.ennuova.controller.GeneralControl;
import com.ennuova.util.JSONUtil;
import com.ennuova.util.StringUtil;
import com.ennuova.util.Util;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

@Controller
@RequestMapping("/TSUserController")
@Api(value="", description="顾问")
public class TSUserController extends GeneralControl{
	/**
	 * 联系人的服务
	 */
	@Resource
	private TSUserService tsUserService;
	
	/**
	 * 顾问的详情
	 *@author 陈晓珊
	 *@date 2016年10月24日
	 * @param params
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(params="method=doDetail",method=RequestMethod.POST)
	@ResponseBody
	@ApiOperation(value = "顾问的详情", notes="{loginId:登录id}")
	public void doDetail(@ApiParam(value="输入{loginId:登录id}")String params,HttpServletResponse response) throws Exception {	
		String getParams = Util.aesDecrypt(params);
		Map<String, Object> jsonMap = CommonClass.getMapByJson(getParams);
		String loginId = jsonMap.get("loginId")+"".trim();//登录id
		//String loginId = "258".trim();//登录id
		AppObjectResult appObjectResult = null;
		if(StringUtil.isnotObjectsNotEmpty(loginId)){
			Map<String, Object> resultMap = tsUserService.doDetail(loginId);
			appObjectResult = new AppObjectResult(SystemInfo.QUERY_SUCCESS.getCode(), SystemInfo.QUERY_SUCCESS.getMsg(),resultMap);
		}else{
			appObjectResult = new AppObjectResult(SystemInfo.ILLEGAL_PARAMETER.getCode(), SystemInfo.ILLEGAL_PARAMETER.getMsg(),"请登录!");
		}
		sendDataResponse(response,JSONUtil.getJSONString(appObjectResult));
	}
	
	
}
