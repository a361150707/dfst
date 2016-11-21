package com.ennuova.app.controller;

import java.util.Date;
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
import com.ennuova.app.service.DynamicReplyService;
import com.ennuova.app.service.PubContactsService;
import com.ennuova.common.CommonClass;
import com.ennuova.controller.GeneralControl;
import com.ennuova.entity.DynamicCommentEntity;
import com.ennuova.entity.DynamicReply;
import com.ennuova.util.DateUtil;
import com.ennuova.util.JSONUtil;
import com.ennuova.util.StringUtil;
import com.ennuova.util.Util;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

@Controller
@RequestMapping("/dynamicReplyController")
@Api(value="", description="动态的评论")
public class DynamicReplyController extends GeneralControl{
	
	/**
	 * 动态评论的服务 
	 */
	@Resource
	private DynamicReplyService dynamicReplyService;
	
	/**
	 * 联系人的服务 
	 */
	@Resource
	private PubContactsService pubContactsService;
	
	/**
	 * 发布评论
	 *@author 陈晓珊
	 *@date 2016年10月20日
	 * @param params
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(params="method=doSendDynamicReply",method=RequestMethod.POST)
	@ResponseBody
	@ApiOperation(value = "发布评论", notes="{id:(动态ID_发布评论,评论ID_回复评论),fromId:回复人的id,fromNick:回复人的昵称,toId:接收人的ID(空值_评论),replyContrnt:回复的内容,isReply:空值_回复中保存toId}")
	public void doSendDynamicReply(@ApiParam(value="输入{id:(动态ID_发布评论,评论ID_回复评论),fromId:回复人的id,fromNick:回复人的昵称,toId:接收人的ID(空值_评论),replyContrnt:回复的内容,isReply:空值_回复中保存toId}")String params,HttpServletResponse response) throws Exception {	
		String getParams = Util.aesDecrypt(params);
		Map<String, Object> jsonMap = CommonClass.getMapByJson(getParams);
		String id = jsonMap.get("id")+"".trim();//动态ID_发布评论,评论ID_回复评论
		String fromId = jsonMap.get("fromId")+"".trim();//回复人的id
		String fromNick = jsonMap.get("fromNick")+"".trim();//回复人的昵称
		String toId = jsonMap.get("toId")+"".trim();//接收人的ID
		String replyContrnt = jsonMap.get("replyContrnt")+"".trim();//回复的内容
		String isReply = jsonMap.get("isReply")+"".trim();//回复的内容
		/*String id = "3".trim();//动态ID_发布评论,评论ID_回复评论
		String fromId = "966".trim();//回复人的id
		String fromNick = jsonMap.get("fromNick")+"".trim();//回复人的昵称
		String toId = "32".trim();//接收人的ID
		String replyContrnt = "发布评论测试".trim();//回复的内容
*/		AppObjectResult appObjectResult = null;
		if(StringUtil.isnotObjectsNotEmpty(id,fromId)){
			Date createTime = DateUtil.getCrruentTimestamp();
			Integer redultInt = 0;
			if(StringUtil.isEmpty(toId)){//评论
				DynamicCommentEntity dynamIicComment = new DynamicCommentEntity();
				dynamIicComment.setFromId(Integer.valueOf(id));
				dynamIicComment.setToId(Integer.valueOf(fromId));
				dynamIicComment.setToName(fromNick);
				dynamIicComment.setContent(replyContrnt);
				dynamIicComment.setCreateDate(createTime);
				redultInt = dynamicReplyService.doSendDynamicComment(dynamIicComment);
			}else{//回复
				DynamicReply dynamicReply = new DynamicReply();
				dynamicReply.setDynamicId(Integer.valueOf(id));
				dynamicReply.setReplyName(Integer.valueOf(fromId));
				dynamicReply.setReplyNick(fromNick);
				dynamicReply.setReplyContent(replyContrnt);
				if(StringUtil.isEmpty(isReply)){
					dynamicReply.setCusId(Integer.valueOf(toId));
				}
				dynamicReply.setCreateDate(createTime);
				redultInt = dynamicReplyService.doSendDynamicReply(dynamicReply);
			}
			if(redultInt > 0){
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
	 * 删除评论
	 *@author 陈晓珊
	 *@date 2016年10月20日
	 * @param params
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(params="method=doDelete",method=RequestMethod.POST)
	@ResponseBody
	@ApiOperation(value = "删除评论", notes="{id:评论id}")
	public void doDelete(@ApiParam(value="输入{id:评论id}")String params,HttpServletResponse response) throws Exception {	
		String getParams = Util.aesDecrypt(params);
		Map<String, Object> jsonMap = CommonClass.getMapByJson(getParams);
		String id = jsonMap.get("id")+"".trim();//评论id
		AppObjectResult appObjectResult = null;
		if(StringUtil.isnotObjectsNotEmpty(id)){
			String delfLag = "1";
			Integer resultInt = dynamicReplyService.doDelete(delfLag, id);
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
	 * 动态评论列表
	 *@author 陈晓珊
	 *@date 2016年10月24日
	 * @param params
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(params="method=doDetail",method=RequestMethod.POST)
	@ResponseBody
	@ApiOperation(value = "动态评论列表", notes="{id:动态id,page:当前页(默认值为1),rows:每页显示的条数(默认值为5)}")
	public void doDetail(@ApiParam(value="输入{id:动态id,page:当前页(默认值为1),rows:每页显示的条数(默认值为5)}")String params,HttpServletResponse response) throws Exception {	
		String getParams = Util.aesDecrypt(params);
		Map<String, Object> jsonMap = CommonClass.getMapByJson(getParams);
		String id = jsonMap.get("id")+"".trim();//动态id
		String getPage = jsonMap.get("page")+"".trim();//当前页(默认值为1)
		String getRows = jsonMap.get("rows")+"".trim();//每页显示的条数(默认值为5)
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
		//String id = "28".trim();
		AppObjectResult appObjectResult = null;
		if(StringUtil.isNotEmpty(id)){
			Map<String, Object> commentCountMap =  dynamicReplyService.doCommentCount(id);
			List<Map<String, Object>> commentList = dynamicReplyService.doCommentList(id,page,rows);
			commentCountMap.put("COMMENTLIST", commentList);
			appObjectResult = new AppObjectResult(SystemInfo.QUERY_SUCCESS.getCode(), SystemInfo.QUERY_SUCCESS.getMsg(),commentCountMap);
		}else{
			appObjectResult = new AppObjectResult(SystemInfo.ILLEGAL_PARAMETER.getCode(), SystemInfo.ILLEGAL_PARAMETER.getMsg(),"请登录!");
		}
		sendDataResponse(response,JSONUtil.getJSONString(appObjectResult));
	}
	

}
