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
import com.ennuova.app.service.CnMsgInfoPerService;
import com.ennuova.common.CommonClass;
import com.ennuova.controller.GeneralControl;
import com.ennuova.util.JSONUtil;
import com.ennuova.util.StringUtil;
import com.ennuova.util.Util;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

/**消息控制器
 * @author lee
 * @time 2016-9-26 下午1:42:17
 */
@Controller
@RequestMapping("/CnMsgInfoPer")
@Api(value="",description="消息控制器")
public class CnMsgInfoPerController extends GeneralControl{
	@Resource
	private CnMsgInfoPerService msgInfoPerService;
	
	@RequestMapping(params="method=getMsgList",method=RequestMethod.POST)
	@ResponseBody
	@ApiOperation(value = "获取消息列表",notes="{receiveId:接收人ID,rType:接收人类型,pageNum:页码 首次查询传0,pagesize:每页数量}")
	public void getMsgList(@ApiParam(value="输入{receiveId:接收人ID,rType:接收人类型,pageNum:页码 首次查询传0,pagesize:每页数量}")String params,HttpServletResponse response) throws Exception {	
		String getParams = Util.aesDecrypt(params);
		Map<String, Object> jsonMap = CommonClass.getMapByJson(getParams);
		String receiveId = jsonMap.get("receiveId")+"".trim();
		Integer rType = Integer.valueOf(jsonMap.get("rType")+"");
		AppObjectResult appObjectResult = null;
		if(StringUtil.isNotEmpty(receiveId)){
			List<Map<String, Object>> list = msgInfoPerService.getMsgList(receiveId, rType);
			appObjectResult = new AppObjectResult(SystemInfo.ADD_SUCCESS.getCode(), SystemInfo.ADD_SUCCESS.getMsg(),list);
		}else{
			appObjectResult = new AppObjectResult(SystemInfo.ILLEGAL_PARAMETER.getCode(), SystemInfo.ILLEGAL_PARAMETER.getMsg());
		}
		sendDataResponse(response,JSONUtil.getJSONString(appObjectResult));
	}
	
	@RequestMapping(params="method=getMsgDetailList",method=RequestMethod.POST)
	@ResponseBody
	@ApiOperation(value = "获取消息详情列表",notes="{receiveId:接收人ID,rType:接收人类型,sender:发送人Id,pageNum:页码 首次查询传0,pagesize:每页数量}")
	public void getMsgDetailList(@ApiParam(value="输入{receiveId:接收人ID,rType:接收人类型,,sender:发送人Id,pageNum:页码 首次查询传0,pagesize:每页数量}")String params,HttpServletResponse response) throws Exception {	
		String getParams = Util.aesDecrypt(params);
		Map<String, Object> jsonMap = CommonClass.getMapByJson(getParams);
		String receiveId = jsonMap.get("receiveId")+"".trim();
		Integer rType = Integer.valueOf(jsonMap.get("rType")+"");
		Long pageNum = Long.valueOf(jsonMap.get("pageNum")+"");
		String sender = jsonMap.get("sender")+"".trim();
		Long pagesize = Long.valueOf(jsonMap.get("pagesize")+"");
		AppObjectResult appObjectResult = null;
		if(StringUtil.isNotEmpty(receiveId) && StringUtil.isNotEmpty(pageNum) && StringUtil.isNotEmpty(pagesize)){
			List<Map<String, Object>> list = msgInfoPerService.getMsgDetailList(receiveId, rType, sender, pageNum, pagesize);
			appObjectResult = new AppObjectResult(SystemInfo.ADD_SUCCESS.getCode(), SystemInfo.ADD_SUCCESS.getMsg(),list);
		}else{
			appObjectResult = new AppObjectResult(SystemInfo.ILLEGAL_PARAMETER.getCode(), SystemInfo.ILLEGAL_PARAMETER.getMsg());
		}
		sendDataResponse(response,JSONUtil.getJSONString(appObjectResult));
	}
	
	@RequestMapping(params="method=deleteMsgByStaffIdAndCusId",method=RequestMethod.POST)
	@ResponseBody
	@ApiOperation(value = "删除消息",notes="{receiveId:接收人ID,sender:发送人Id}")
	public void deleteMsgByStaffIdAndCusId(@ApiParam(value="输入{receiveId:接收人ID,sender:发送人Id}")String params,HttpServletResponse response) throws Exception {	
		String getParams = Util.aesDecrypt(params);
		Map<String, Object> jsonMap = CommonClass.getMapByJson(getParams);
		String receiveId = jsonMap.get("receiveId")+"".trim();
		String sender = jsonMap.get("sender")+"".trim();
		AppObjectResult appObjectResult = null;
		if(StringUtil.isNotEmpty(receiveId) && StringUtil.isNotEmpty(sender)){
			int countNum = msgInfoPerService.deleteMsgByStaffIdAndCusId(receiveId, sender);
			appObjectResult = new AppObjectResult(SystemInfo.ADD_SUCCESS.getCode(), SystemInfo.ADD_SUCCESS.getMsg(),countNum);
		}else{
			appObjectResult = new AppObjectResult(SystemInfo.ILLEGAL_PARAMETER.getCode(), SystemInfo.ILLEGAL_PARAMETER.getMsg());
		}
		sendDataResponse(response,JSONUtil.getJSONString(appObjectResult));
	}
}
