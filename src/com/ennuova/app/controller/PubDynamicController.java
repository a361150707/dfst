package com.ennuova.app.controller;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.ennuova.app.config.AppObjectResult;
import com.ennuova.app.config.SystemInfo;
import com.ennuova.app.service.DynamicReplyService;
import com.ennuova.app.service.PubDynamicService;
import com.ennuova.common.CommonClass;
import com.ennuova.controller.GeneralControl;
import com.ennuova.entity.ClickGoodEntity;
import com.ennuova.entity.PubContacts;
import com.ennuova.entity.PubDynamic;
import com.ennuova.util.DateUtil;
import com.ennuova.util.JSONUtil;
import com.ennuova.util.JsonUtils;
import com.ennuova.util.StringUtil;
import com.ennuova.util.Util;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

@Controller
@RequestMapping("/pubDynamicController")
@Api(value="", description="悦生活")
public class PubDynamicController extends GeneralControl{
	
	private static Logger logger = Logger.getLogger(PubDynamicController.class);
	
	/**
	 * 动态和话题的服务
	 */
	@Resource
	private PubDynamicService pubDynamicService;
	
	/**
	 * 动态回复的服务
	 */
	@Resource
	private DynamicReplyService dynamicReplyService; 
	
	/**
	 * 发布动态
	 *@author sududa
	 *@date 2016年10月19日
	 * @param params
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(params="method=doSendDynamic",method=RequestMethod.POST)
	@ResponseBody
	@ApiOperation(value = "发布动态", notes="{sendId:发布人的id(登录的id),sendType:发送人的类型(1-车主,2-员工),title:标题,content:内容,dynamicType:动态类型(1-动态,2-话题),shrinkDiagram:缩列图,imgPath:图片路径([图片1,图片2]),showType:动态显示的类型(1-公开,2-好友可见),dymanicPlateId:话题的id,state:话题的状态(1-编辑,2-发布,3-关闭),cusLocation:客户位置}")
	public void doSendDynamic(@ApiParam(value="输入{sendId:发布人的id(登录的id),sendType:发送人的类型(1-车主,2-员工),title:标题,content:内容,dynamicType:动态类型(1-动态,2-话题),shrinkDiagram:缩列图,imgPath:图片路径([图片1,图片2]),showType:动态显示的类型(1-公开,2-好友可见),dymanicPlateId:话题的id,state:话题的状态(1-编辑,2-发布,3-关闭),cusLocation:客户位置}")String params,HttpServletResponse response) throws Exception {	
		String getParams = Util.aesDecrypt(params);
		Map<String, Object> jsonMap = CommonClass.getMapByJson(getParams);
		String sendId = jsonMap.get("sendId")+"".trim();//发布人的id(登录的id)
		String sendType = jsonMap.get("sendType")+"".trim();//发送人的类型(1-车主,2-员工)
		String content = jsonMap.get("content")+"".trim();//内容
		String imgPath = jsonMap.get("imgPath")+"".trim();//图片路径
    	String dynamicType = jsonMap.get("dynamicType")+"".trim();//动态类型(1-动态,2-话题)
    	String dymanicPlateId = jsonMap.get("dymanicPlateId")+"".trim();//话题ID
		String title = jsonMap.get("title")+"".trim();//标题
//		String sendId = "4028fb8c573cbe9001573cc162230012".trim();//发布人的id(登录的id)
//		String content = "推荐使用动态看车款1".trim();//内容
//		String imgPath = "[]";//图片路径
//		PubDynamic pubDynamic = new PubDynamic();
//		pubDynamic.setSendId(sendId);
//		pubDynamic.setContent(content);
//		pubDynamic.setSendType("2");
//		pubDynamic.setDynamicType("1");
//		pubDynamic.setShowType("1");
//		pubDynamic.setImgPath(imgPath);
//		pubDynamic.setCusLocation("厦门市");
//		pubDynamic.setTitle(title);
		AppObjectResult appObjectResult = null;
		if(StringUtil.isnotObjectsNotEmpty(sendId,content) || StringUtil.isnotObjectsNotEmpty(sendId,imgPath)){//当发送人和内容或者发送人和图片都不为空时才能发布动态
			PubDynamic pubDynamic = JsonUtils.toObject(getParams, PubDynamic.class);
			if(StringUtil.isEmpty(dymanicPlateId)){
				pubDynamic.setTitle("");
			}
			if(StringUtil.isEmpty(dynamicType)){
				pubDynamic.setDynamicType("1");;
			}
			if(StringUtil.isEmpty(sendType)){
				pubDynamic.setSendType("1");
			}
			
			Date createTime = DateUtil.getCrruentTimestamp();
			pubDynamic.setCreatetime(createTime);
			PubDynamic redultEntity = pubDynamicService.doSendDynamic(pubDynamic);
			if(StringUtil.isNotEmpty(redultEntity)){
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
	 * 删除动态
	 *@author sududa
	 *@date 2016年10月19日
	 * @param params
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(params="method=doDelete",method=RequestMethod.POST)
	@ResponseBody
	@ApiOperation(value = "删除动态", notes="{sendId:发布人的id(登录的id),id:动态id}")
	public void doDelete(@ApiParam(value="输入{sendId:发布人的id(登录的id),id:动态id}")String params,HttpServletResponse response) throws Exception {	
		String getParams = Util.aesDecrypt(params);
		Map<String, Object> jsonMap = CommonClass.getMapByJson(getParams);
		String sendId = jsonMap.get("sendId")+"".trim();//发布人的id(登录的id)
		String id = jsonMap.get("id")+"".trim();//动态id
//		String sendId = "250".trim();//发布人的id(登录的id)
//		String id = "21".trim();//动态id
		AppObjectResult appObjectResult = null;
		if(StringUtil.isnotObjectsNotEmpty(sendId,id)){
			String delfLag = "1";
			Integer resultInt = pubDynamicService.doDelete(delfLag, id, sendId);
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
	 * 动态列表
	 *@author sududa
	 *@date 2016年10月26日
	 * @param params
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(params="method=doList",method=RequestMethod.POST)
	@ResponseBody
	@ApiOperation(value = "动态列表", notes="{loginId:登录id,page:当前页(默认值为1),rows:每页显示的条数(默认值为5)}")
	public void doList(@ApiParam(value="输入{loginId:登录id,page:当前页(默认值为1),rows:每页显示的条数(默认值为5)}")String params,HttpServletResponse response){	
		try {
//			long startTime=System.currentTimeMillis();
			String getParams = Util.aesDecrypt(params);
			Map<String, Object> jsonMap = CommonClass.getMapByJson(getParams);
			String loginId = jsonMap.get("loginId")+"".trim();//登录id
			String getPage = jsonMap.get("page")+"".trim();//当前页(默认值为1)
			String getRows = jsonMap.get("rows")+"".trim();//每页显示的条数(默认值为10)
			Integer page = 1;
			Integer rows = 10;
			if(StringUtil.isNotEmpty(getPage)){
				page = Integer.valueOf(getPage);
			}
			if(StringUtil.isNotEmpty(getRows)){
				rows = Integer.valueOf(getRows);
			}
//			String loginId = "966".trim();//登录id
			AppObjectResult appObjectResult = null;
			if(StringUtil.isNotEmpty(loginId)){
				List<Map<String, Object>> resultList = pubDynamicService.findByIdWithPage(page,rows,loginId);
//				long endTime=System.currentTimeMillis();
//				float excTime=(float)(endTime-startTime)/1000;
//		        System.out.println("控制层动态列表执行时间："+excTime+"s");
				appObjectResult = new AppObjectResult(SystemInfo.QUERY_SUCCESS.getCode(), SystemInfo.QUERY_SUCCESS.getMsg(),resultList);
			}else{
				appObjectResult = new AppObjectResult(SystemInfo.ILLEGAL_PARAMETER.getCode(), SystemInfo.ILLEGAL_PARAMETER.getMsg());
			}
			sendDataResponse(response,JSONUtil.getJSONString(appObjectResult));
		} catch (Exception e) {
			logger.error("悦生活控制类_动态列表:"+e.getMessage());
			e.printStackTrace();
		}
	}
	
	/**
	 * 个人动态列表
	 * @param params
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(params="method=doPersonalList",method=RequestMethod.POST)
	@ResponseBody
	@ApiOperation(value = "个人动态列表", notes="{loginId:登录id,page:当前页(默认值为1),rows:每页显示的条数(默认值为3)}")
	public void doPersonalList(@ApiParam(value="输入{loginId:登录id,page:当前页(默认值为1),rows:每页显示的条数(默认值为3)}")String params,HttpServletResponse response) throws Exception {	
		String getParams = Util.aesDecrypt(params);
		Map<String, Object> jsonMap = CommonClass.getMapByJson(getParams);
		String loginId = jsonMap.get("loginId")+"".trim();//登录id
		String getPage = jsonMap.get("page")+"".trim();//当前页(默认值为1)
		String getRows = jsonMap.get("rows")+"".trim();//每页显示的条数(默认值为10)
		Integer page = 1;
		Integer rows = 3;
		if(StringUtil.isNotEmpty(getPage)){
			page = Integer.valueOf(getPage);
		}
		if(StringUtil.isNotEmpty(getRows)){
			rows = Integer.valueOf(getRows);
		}
		//String loginId = "966".trim();//登录id
		AppObjectResult appObjectResult = null;
		if(StringUtil.isNotEmpty(loginId)){
			List<Map<String, Object>> resultList = pubDynamicService.doPersonalList(page,rows,loginId);
			appObjectResult = new AppObjectResult(SystemInfo.QUERY_SUCCESS.getCode(), SystemInfo.QUERY_SUCCESS.getMsg(),resultList);
		}else{
			appObjectResult = new AppObjectResult(SystemInfo.ILLEGAL_PARAMETER.getCode(), SystemInfo.ILLEGAL_PARAMETER.getMsg());
		}
		sendDataResponse(response,JSONUtil.getJSONString(appObjectResult));
	}
	
	
	/**
	 * 个人盒子激活时间,累计里程,累计油耗
	 * @param params
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(params="method=doPersonalOne",method=RequestMethod.POST)
	@ResponseBody
	@ApiOperation(value = "个人盒子激活时间,累计里程,累计油耗", notes="{loginId:登录id}")
	public void doPersonalOne(@ApiParam(value="输入{loginId:登录id}")String params,HttpServletResponse response) throws Exception {	
		String getParams = Util.aesDecrypt(params);
		Map<String, Object> jsonMap = CommonClass.getMapByJson(getParams);
		String loginId = jsonMap.get("loginId")+"".trim();//登录id
//		String loginId = "911".trim();//登录id
		AppObjectResult appObjectResult = null;
		if(StringUtil.isNotEmpty(loginId)){
			Map<String, Object> resultMap = pubDynamicService.doPersonalOne(loginId);
			appObjectResult = new AppObjectResult(SystemInfo.QUERY_SUCCESS.getCode(), SystemInfo.QUERY_SUCCESS.getMsg(),resultMap);
		}else{
			appObjectResult = new AppObjectResult(SystemInfo.ILLEGAL_PARAMETER.getCode(), SystemInfo.ILLEGAL_PARAMETER.getMsg());
		}
		sendDataResponse(response,JSONUtil.getJSONString(appObjectResult));
	}
	
	/**
	 * 点赞的人列表
	 *@author 陈晓珊
	 *@date 2016年10月27日
	 * @param params
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(params="method=doClickGoodList",method=RequestMethod.POST)
	@ResponseBody
	@ApiOperation(value = "点赞的人列表", notes="{loginId:登陆id,id:动态ID}")
	public void doClickGoodList(@ApiParam(value="输入{loginId:登陆id,id:动态ID}")String params,HttpServletResponse response) throws Exception {	
		String getParams = Util.aesDecrypt(params);
		Map<String, Object> jsonMap = CommonClass.getMapByJson(getParams);
		String loginId = jsonMap.get("loginId")+"".trim();//登陆人ID
		String id = jsonMap.get("id")+"".trim();//动态ID
		//String loginId = "32".trim();//登陆人ID
		//String id = "25".trim();//动态ID
		AppObjectResult appObjectResult = null;
		if(StringUtil.isnotObjectsNotEmpty(loginId,id)){
			List<Map<String, Object>> resultList = dynamicReplyService.doClickGoodList(loginId,id,"3");
			appObjectResult = new AppObjectResult(SystemInfo.QUERY_SUCCESS.getCode(), SystemInfo.QUERY_SUCCESS.getMsg(),resultList);
		}else{
			appObjectResult = new AppObjectResult(SystemInfo.ILLEGAL_PARAMETER.getCode(), SystemInfo.ILLEGAL_PARAMETER.getMsg(),"请登录!");
		}
		sendDataResponse(response,JSONUtil.getJSONString(appObjectResult));
	}
	
	
	/**
	 * 话题列表
	 *@author 陈晓珊
	 *@date 2016年10月27日
	 * @param params
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(params="method=doTopicList",method=RequestMethod.POST)
	@ResponseBody
	@ApiOperation(value = "话题列表", notes="{page:当前页(默认值为1),rows:每页显示的条数(默认值为5),title:标题(不为空则根据标题查找)}")
	public void doTopicList(@ApiParam(value="输入{page:当前页(默认值为1),rows:每页显示的条数(默认值为5),title:标题(不为空则根据标题查找)}")String params,HttpServletResponse response) throws Exception {	
		String getParams = Util.aesDecrypt(params);
		Map<String, Object> jsonMap = CommonClass.getMapByJson(getParams);
		String getPage = jsonMap.get("page")+"".trim();//当前页(默认值为1)
		String getRows = jsonMap.get("rows")+"".trim();//每页显示的条数(默认值为3)
		String title = jsonMap.get("title")+"".trim();//当前页(默认值为1)
		//String title = "悦".trim();//当前页(默认值为1)
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
		if(StringUtil.isnotObjectsNotEmpty(page,rows)){
			List<Map<String, Object>> resultList = pubDynamicService.doTopicList(title,page,rows);
			appObjectResult = new AppObjectResult(SystemInfo.QUERY_SUCCESS.getCode(), SystemInfo.QUERY_SUCCESS.getMsg(),resultList);
		}else{
			appObjectResult = new AppObjectResult(SystemInfo.ILLEGAL_PARAMETER.getCode(), SystemInfo.ILLEGAL_PARAMETER.getMsg());
		}
		sendDataResponse(response,JSONUtil.getJSONString(appObjectResult));
	}
	
	/**
	 * 话题详情列表
	 *@author 陈晓珊
	 *@date 2016年10月28日
	 * @param params
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(params="method=doTopicDetilList",method=RequestMethod.POST)
	@ResponseBody
	@ApiOperation(value = "话题详情列表", notes="{loginId:登陆id,id:话题ID,page:当前页(默认值为1),rows:每页显示的条数(默认值为5)}")
	public void doTopicDetilList(@ApiParam(value="输入{loginId:登陆id,id:话题ID,page:当前页(默认值为1),rows:每页显示的条数(默认值为5)}")String params,HttpServletResponse response) throws Exception {
		long startTime=System.currentTimeMillis();
		String getParams = Util.aesDecrypt(params);
		Map<String, Object> jsonMap = CommonClass.getMapByJson(getParams);
		String loginId = jsonMap.get("loginId")+"".trim();//登陆人ID
		String id = jsonMap.get("id")+"".trim();//话题ID
		String getPage = jsonMap.get("page")+"".trim();//当前页(默认值为1)
		String getRows = jsonMap.get("rows")+"".trim();//每页显示的条数(默认值为3)
//		String id = "32".trim();//话题ID
//		String loginId = "966".trim();//登陆人ID
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
		if(StringUtil.isnotObjectsNotEmpty(loginId,id)){
			List<Map<String, Object>> resultList = pubDynamicService.doTopicDetilList(loginId,id,page,rows);
			long endTime=System.currentTimeMillis();
			float excTime=(float)(endTime-startTime)/1000;
	        System.out.println("动态列表执行时间："+excTime+"s");
			appObjectResult = new AppObjectResult(SystemInfo.QUERY_SUCCESS.getCode(), SystemInfo.QUERY_SUCCESS.getMsg(),resultList);
		}else{
			appObjectResult = new AppObjectResult(SystemInfo.ILLEGAL_PARAMETER.getCode(), SystemInfo.ILLEGAL_PARAMETER.getMsg());
		}
		sendDataResponse(response,JSONUtil.getJSONString(appObjectResult));
	}
	
	
	
	/**
	 * 个人动态详情
	 *@author 陈晓珊
	 *@date 2016年11月1日
	 * @param params
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(params="method=doDyDetilList",method=RequestMethod.POST)
	@ResponseBody
	@ApiOperation(value = "个人动态详情", notes="{loginId:登陆ID,id:动态ID}")
	public void doDyDetilList(@ApiParam(value="输入{loginId:登陆ID,id:动态ID}")String params,HttpServletResponse response) throws Exception {	
		String getParams = Util.aesDecrypt(params);
		Map<String, Object> jsonMap = CommonClass.getMapByJson(getParams);
		String loginId = jsonMap.get("loginId")+"".trim();//登陆人ID
		String id = jsonMap.get("id")+"".trim();//动态ID
//		String loginId = "32".trim();//登陆人ID
//		String id = "26".trim();//动态ID
		AppObjectResult appObjectResult = null;
		if(StringUtil.isnotObjectsNotEmpty(id)){
			Map<String, Object> resultList = pubDynamicService.doDyDetilList(loginId,id);
			appObjectResult = new AppObjectResult(SystemInfo.QUERY_SUCCESS.getCode(), SystemInfo.QUERY_SUCCESS.getMsg(),resultList);
		}else{
			appObjectResult = new AppObjectResult(SystemInfo.ILLEGAL_PARAMETER.getCode(), SystemInfo.ILLEGAL_PARAMETER.getMsg());
		}
		sendDataResponse(response,JSONUtil.getJSONString(appObjectResult));
	}
	
	
	/**
	 * 点赞，取消点赞
	 *@author 陈晓珊
	 *@date 2016年11月1日
	 * @param params
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(params="method=doClickGood",method=RequestMethod.POST)
	@ResponseBody
	@ApiOperation(value = "点赞，取消点赞", notes="{fromId:点赞的来源ID(动态ID),toId:点赞人的ID,goodType:点赞的类型(0_新闻,1_评论,2_视频,3_动态),isClickGood:点赞或者取消点赞(0_点赞，1_取消点赞)}")
	public void doClickGood(@ApiParam(value="输入{fromId:点赞的来源ID(动态ID),toId:点赞人的ID,goodType:点赞的类型(0_新闻,1_评论,2_视频,3_动态),isClickGood:点赞或者取消点赞(0_点赞，1_取消点赞)}")String params,HttpServletResponse response) throws Exception {	
		String getParams = Util.aesDecrypt(params);
		Map<String, Object> jsonMap = CommonClass.getMapByJson(getParams);
		String fromId = jsonMap.get("fromId")+"".trim();//动态ID
		String toId = jsonMap.get("toId")+"".trim();//登陆ID
		String goodType = jsonMap.get("goodType")+"".trim();//点赞的类型(0_新闻,1_评论,2_视频,3_动态)
		String isClickGood = jsonMap.get("isClickGood")+"".trim();//点赞或者取消点赞(0_点赞，1_取消点赞)
//		String fromId = "100000".trim();//点赞的来源ID(动态ID)
//		String toId = "966".trim();//点赞人的ID
//		String goodType = "3".trim();//点赞类型
//		String isClickGood = "0".trim();
//		ClickGoodEntity clickGood = new ClickGoodEntity();
//		clickGood.setFromId(Long.valueOf(fromId));
//		clickGood.setToId(Long.valueOf(toId));
//		clickGood.setGoodType(goodType);
		AppObjectResult appObjectResult = null;
		if(StringUtil.isnotObjectsNotEmpty(fromId,toId,goodType,isClickGood)){//当发送人和内容或者发送人和图片都不为空时才能发布动态
			if(isClickGood.equals("0")){
				Map<String, Object>  isClick = pubDynamicService.doIsClickGood(toId,fromId,goodType);
				if(StringUtil.isEmpty(isClick)){//判断是否重复点赞
					ClickGoodEntity clickGood = JsonUtils.toObject(getParams, ClickGoodEntity.class);
					Date createTime = DateUtil.getCrruentTimestamp();
					clickGood.setCreateDate(createTime);
					ClickGoodEntity redultEntity = pubDynamicService.doClickGood(clickGood);
					if(StringUtil.isNotEmpty(redultEntity)){
						appObjectResult = new AppObjectResult(SystemInfo.PLAY_SUCCESS.getCode(), SystemInfo.PLAY_SUCCESS.getMsg());
					}else{
						appObjectResult = new AppObjectResult(SystemInfo.PLAY_FAIL.getCode(), SystemInfo.PLAY_FAIL.getMsg());
					}
				}else{
						appObjectResult = new AppObjectResult(SystemInfo.PLAY_FAIL.getCode(), SystemInfo.PLAY_FAIL.getMsg());
				}
				
			}else{
				Integer redultInt = pubDynamicService.cancelClickGood(fromId,toId,goodType);
				if(redultInt > 0){
					appObjectResult = new AppObjectResult(SystemInfo.PLAY_SUCCESS.getCode(), SystemInfo.PLAY_SUCCESS.getMsg());
				}else{
					appObjectResult = new AppObjectResult(SystemInfo.PLAY_FAIL.getCode(), SystemInfo.PLAY_FAIL.getMsg());
				}
			}
		}else{
			appObjectResult = new AppObjectResult(SystemInfo.ILLEGAL_PARAMETER.getCode(), SystemInfo.ILLEGAL_PARAMETER.getMsg());
		}
		sendDataResponse(response,JSONUtil.getJSONString(appObjectResult));
	}
	
	/**
	 * 取消点赞
	 *@author sududa
	 *@date 2016年10月19日
	 * @param params
	 * @param response
	 * @throws Exception
	 */
/*	@RequestMapping(params="method=cancelClickGood",method=RequestMethod.POST)
	@ResponseBody
	@ApiOperation(value = "取消点赞", notes="{fromId:点赞的来源ID(动态ID),toId:点赞人的ID,goodType:点赞的类型(0_新闻,1_评论,2_视频,3_动态)}")
	public void cancelClickGood(@ApiParam(value="输入{fromId:点赞的来源ID(动态ID),toId:点赞人的ID,goodType:点赞的类型(0_新闻,1_评论,2_视频,3_动态)}")String params,HttpServletResponse response) throws Exception {	
		
		String getParams = Util.aesDecrypt(params);
		Map<String, Object> jsonMap = CommonClass.getMapByJson(getParams);
		String fromId = jsonMap.get("fromId")+"".trim();//点赞的来源ID(动态ID)
		String toId = jsonMap.get("toId")+"".trim();//点赞人的ID
		String goodType = jsonMap.get("goodType")+"".trim();//点赞的类型(0_新闻,1_评论,2_视频,3_动态)
		
//		String fromId = "25".trim();//点赞的来源ID(动态ID)
//		String toId = "330".trim();//点赞人的ID
//		String goodType = "3".trim();//点赞的类型(0_新闻,1_评论,2_视频,3_动态)
		AppObjectResult appObjectResult = null;
		if(StringUtil.isnotObjectsNotEmpty(fromId,toId,goodType)){
			Integer redultInt = pubDynamicService.cancelClickGood(fromId,toId,goodType);
			if(redultInt > 0){
				appObjectResult = new AppObjectResult(SystemInfo.PLAY_SUCCESS.getCode(), SystemInfo.PLAY_SUCCESS.getMsg());
			}else{
				appObjectResult = new AppObjectResult(SystemInfo.PLAY_FAIL.getCode(), SystemInfo.PLAY_FAIL.getMsg());
			}
		}else{
			appObjectResult = new AppObjectResult(SystemInfo.ILLEGAL_PARAMETER.getCode(), SystemInfo.ILLEGAL_PARAMETER.getMsg());
		}
		sendDataResponse(response,JSONUtil.getJSONString(appObjectResult));
	
	}*/
	@RequestMapping(params="method=file_upload",method=RequestMethod.POST)
	@ResponseBody
	@ApiOperation(value = "文件上传", notes="{file:文件,index:排序Id}")
	public void file_upload(@ApiParam(value="输入{file:文件,index:排序Id}")String params,MultipartFile file,Long userId,Long index,HttpServletResponse response) throws Exception {	
		String path = "";
		AppObjectResult appObjectResult = null;
		if(file!=null){//当发送人和内容或者发送人和图片都不为空时才能发布动态
			path = saveFile(file,userId,index);  
			if(StringUtil.isNotEmpty(path)){
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("index", index);
				map.put("path", path);
				appObjectResult = new AppObjectResult(SystemInfo.PLAY_SUCCESS.getCode(), SystemInfo.PLAY_SUCCESS.getMsg(),map);
			}else{
				appObjectResult = new AppObjectResult(SystemInfo.PLAY_FAIL.getCode(), SystemInfo.PLAY_FAIL.getMsg());
			}
		}else{
			appObjectResult = new AppObjectResult(SystemInfo.ILLEGAL_PARAMETER.getCode(), SystemInfo.ILLEGAL_PARAMETER.getMsg());
		}
		sendDataResponse(response,JSONUtil.getJSONString(appObjectResult));
	}
	private String saveFile(MultipartFile file,Long userId,Long index) {  
        // 判断文件是否为空  
		String uploadPath = "";
        if (!file.isEmpty()) {  
            try {  
                // 文件保存路径  
            	String path = "D:\\data_file\\InnovPic\\Dynamic";
                // 转存文件  
                String fileName = file.getOriginalFilename();
    	        fileName = userId+index+System.currentTimeMillis()+".png";  
    	        File targetFile = new File(path, fileName);  
    	        if(!targetFile.getParentFile().exists()){  
    	            targetFile.getParentFile().mkdirs();  
    	        }
                file.transferTo(targetFile);
                uploadPath = "Dynamic/"+fileName;
            } catch (Exception e) {  
                e.printStackTrace();  
            }  
        }  
        return uploadPath;  
    } 
}
