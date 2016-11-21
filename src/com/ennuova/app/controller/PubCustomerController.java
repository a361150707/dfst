package com.ennuova.app.controller;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ennuova.app.config.AppObjectResult;
import com.ennuova.app.config.SystemInfo;
import com.ennuova.app.service.PubCustomerService;
import com.ennuova.common.CommonClass;
import com.ennuova.controller.GeneralControl;
import com.ennuova.util.JSONUtil;
import com.ennuova.util.StringUtil;
import com.ennuova.util.Util;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

@Controller
@RequestMapping("/PubCustomerController")
@Api(value="", description="客户")
public class PubCustomerController extends GeneralControl{

	/**
	 * 客户的服务
	 */
	@Resource
	private PubCustomerService pubCustomerService;
	
	/**
	 * 查询客户
	 * @author 陈晓珊
	 * @date 2016年11月2日
	 * @param params
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(params="method=doQueryCustomer",method=RequestMethod.POST)
	@ResponseBody
	@ApiOperation(value = "查询客户", notes="{page:当前页(默认值为1),rows:每页显示的条数(默认值为5),loginId:登陆人的ID,queryText:搜索条件}")
	public void doQueryCustomer(@ApiParam(value="输入{page:当前页(默认值为1),rows:每页显示的条数(默认值为5),loginId:登陆人的ID,queryText:搜索条件}")String params,HttpServletResponse response) throws Exception {	
		String getParams = Util.aesDecrypt(params);
		Map<String, Object> jsonMap = CommonClass.getMapByJson(getParams);
		String getPage = jsonMap.get("page")+"".trim();//当前页(默认值为1)
		String getRows = jsonMap.get("rows")+"".trim();//每页显示的条数(默认值为5)
		String loginId = jsonMap.get("loginId")+"".trim();//登录人的ID
		String queryText = jsonMap.get("queryText")+"".trim();//搜索条件
		
//		String loginId = "32".trim();//登录
//		String queryText ="15959279267".trim();//搜索条件
		Integer page = null;
		Integer rows = null;
		if(StringUtil.isNotEmpty(getPage)){
			page = Integer.valueOf(getPage);
		}else{
			page = 1;
		}
		if(StringUtil.isNotEmpty(getRows)){
			rows = Integer.valueOf(getRows);
		}else{
			rows = 5;
		}
		AppObjectResult appObjectResult = null;
		if(StringUtil.isnotObjectsNotEmpty(page,rows,loginId)){
			List<Map<String, Object>> resultList = pubCustomerService.doQueryCustomer(page,rows,loginId,queryText);
			appObjectResult = new AppObjectResult(SystemInfo.QUERY_SUCCESS.getCode(), SystemInfo.QUERY_SUCCESS.getMsg(),resultList);
		}else{
			appObjectResult = new AppObjectResult(SystemInfo.ILLEGAL_PARAMETER.getCode(), SystemInfo.ILLEGAL_PARAMETER.getMsg(),"请登录!");
		}
		sendDataResponse(response,JSONUtil.getJSONString(appObjectResult));
	}
	
	/**
	 * 是否能通过手机号查找客户
	 *@author 陈晓珊
	 *@date 2016年11月4日
	 * @param params
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(params="method=doIsSearchTel",method=RequestMethod.POST)
	@ResponseBody
	@ApiOperation(value = "是否能通过手机号查找客户", notes="{loginId:登陆人的id,isSearchTel:是否应许通过手机号找到自己(1_应许,0_不应许)}")
	public void doIsSearchTel(@ApiParam(value="输入{loginId:登陆人的id,isSearchTel:是否应许通过手机号找到自己(1_应许,0_不应许)}")String params,HttpServletResponse response) throws Exception {	
		String getParams = Util.aesDecrypt(params);
		Map<String, Object> jsonMap = CommonClass.getMapByJson(getParams);
		String loginId = jsonMap.get("loginId")+"".trim();//登陆人的id
		String isSearchTel = jsonMap.get("isSearchTel")+"".trim();//是否应许通过手机号找到自己(1_应许,2_不应许)
//		String loginId = "32".trim();//登陆人的id
//		String isSearchTel = "2".trim();//是否应许通过手机号找到自己(1_应许,2_不应许)
		AppObjectResult appObjectResult = null;
		if(StringUtil.isnotObjectsNotEmpty(loginId,isSearchTel)){
			Integer resultInt = pubCustomerService.doIsSearchTel(loginId,isSearchTel);
			if(resultInt > 0){
				appObjectResult = new AppObjectResult(SystemInfo.PLAY_SUCCESS.getCode(), SystemInfo.PLAY_SUCCESS.getMsg());
			}else{
				appObjectResult = new AppObjectResult(SystemInfo.PLAY_FAIL.getCode(), SystemInfo.PLAY_FAIL.getMsg());
			}
		}else{
			appObjectResult = new AppObjectResult(SystemInfo.ILLEGAL_PARAMETER.getCode(), SystemInfo.ILLEGAL_PARAMETER.getMsg());
		}
		sendDataResponse(response,JSONUtil.getJSONString(appObjectResult));
	}
	
	/**
	 * 修改兴趣标签
	 *@author 陈晓珊
	 *@date 2016年11月7日
	 * @param params
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(params="method=doEditInterest",method=RequestMethod.POST)
	@ResponseBody
	@ApiOperation(value = "修改兴趣标签", notes="{loginId:登陆人的id,interest:兴趣)}")
	public void doEditInterest(@ApiParam(value="输入{loginId:登陆人的id,interest:兴趣}")String params,HttpServletResponse response) throws Exception {	
		String getParams = Util.aesDecrypt(params);
		Map<String, Object> jsonMap = CommonClass.getMapByJson(getParams);
		String loginId = jsonMap.get("loginId")+"".trim();//登陆人的id
		String interest = jsonMap.get("interest")+"".trim();//兴趣
//		String loginId = "966".trim();//登陆人的id
//		String interest = "羽毛球".trim();//兴趣
		AppObjectResult appObjectResult = null;
		if(StringUtil.isnotObjectsNotEmpty(loginId,interest)){
			Integer resultInt = pubCustomerService.doEditInterest(loginId,interest);
			if(resultInt > 0){
				appObjectResult = new AppObjectResult(SystemInfo.PLAY_SUCCESS.getCode(), SystemInfo.PLAY_SUCCESS.getMsg());
			}else{
				appObjectResult = new AppObjectResult(SystemInfo.PLAY_FAIL.getCode(), SystemInfo.PLAY_FAIL.getMsg());
			}
		}else{
			appObjectResult = new AppObjectResult(SystemInfo.ILLEGAL_PARAMETER.getCode(), SystemInfo.ILLEGAL_PARAMETER.getMsg());
		}
		sendDataResponse(response,JSONUtil.getJSONString(appObjectResult));
	}
	
}
