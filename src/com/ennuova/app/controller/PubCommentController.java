package com.ennuova.app.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ennuova.app.config.AppObjectResult;
import com.ennuova.app.config.SystemInfo;
import com.ennuova.app.service.PubCommentService;
import com.ennuova.common.CommonClass;
import com.ennuova.controller.GeneralControl;
import com.ennuova.entity.PubCommentEntity;
import com.ennuova.util.JSONUtil;
import com.ennuova.util.StringUtil;
import com.ennuova.util.UrlUtil;
import com.ennuova.util.Util;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

/**评论控制器
 * @author 李智辉
 * @time 2016-7-27
 */
@Controller
@RequestMapping("/comment.do")
@Api(value="", description="在线展厅")
public class PubCommentController extends GeneralControl{
	
	@Resource
	private PubCommentService commentService;
	
	@RequestMapping(params="method=commentNews",method=RequestMethod.POST)
	@ApiOperation(value="评论新闻")
	public void commentNews(@ApiParam(value="请输入{userId:用户id}AES加密后的结果")String params,HttpServletResponse response){	
		String content = Util.aesDecrypt(params);
		Map<String, Object> jsonMap=CommonClass.getMapByJson(content);
		Long userId = Long.valueOf(jsonMap.get("userId").toString());
		Long fromId = Long.valueOf(jsonMap.get("fromId").toString());
		String comment = jsonMap.get("content")+"".trim();
		AppObjectResult appObjectResult = null;
		
		boolean success = false;
		
		try {
			if(StringUtil.isNotEmpty(userId)&&StringUtil.isNotEmpty(fromId)){
				
				success = commentService.commentNews(fromId, userId, comment);	//评论新闻
				if(success){
					appObjectResult = new AppObjectResult(SystemInfo.QUERY_SUCCESS.getCode(), SystemInfo.QUERY_SUCCESS.getMsg());		
				}else{
					appObjectResult = new AppObjectResult(SystemInfo.ILLEGAL_PARAMETER.getCode(), SystemInfo.ILLEGAL_PARAMETER.getMsg());
				}
			}else{
				appObjectResult = new AppObjectResult(SystemInfo.ILLEGAL_PARAMETER.getCode(), SystemInfo.ILLEGAL_PARAMETER.getMsg());
			}
			sendDataResponse(response,JSONUtil.getJSONString(appObjectResult));
		} catch (Exception e) {
			e.printStackTrace();
			sendDataResponse(response,"评论失败");
		}
	}
	
	@RequestMapping(params="method=getCommentListByFromId",method=RequestMethod.POST)
	@ApiOperation(value="获取评论列表")
	public void getCommentListByFromId(@ApiParam(value="请输入{userId:用户id}AES加密后的结果")String params,HttpServletResponse response){	
		String content = Util.aesDecrypt(params);
		Map<String, Object> jsonMap=CommonClass.getMapByJson(content);
		int pageNum = Integer.valueOf(jsonMap.get("pageNum").toString());
		Long fromId = Long.valueOf(jsonMap.get("fromId").toString());
		Long userId = Long.valueOf(jsonMap.get("userId").toString());
		int size = Integer.valueOf(jsonMap.get("size").toString());
		AppObjectResult appObjectResult = null;
		try {
			if(StringUtil.isNotEmpty(pageNum)&&StringUtil.isNotEmpty(fromId)){
				List<Map<String, Object>> list = commentService.getCommentListByFromId(fromId, pageNum, size, userId);
					appObjectResult = new AppObjectResult(SystemInfo.QUERY_SUCCESS.getCode(), SystemInfo.QUERY_SUCCESS.getMsg(),list);
			}else{
				appObjectResult = new AppObjectResult(SystemInfo.ILLEGAL_PARAMETER.getCode(), SystemInfo.ILLEGAL_PARAMETER.getMsg());
			}
			sendDataResponse(response,JSONUtil.getJSONString(appObjectResult));
		} catch (Exception e) {
			e.printStackTrace();
			sendDataResponse(response,"获取失败");
		}
	}
	
	@RequestMapping(params="method=replyComment",method=RequestMethod.POST)
	@ApiOperation(value="回复评论")
	public void replyComment(@ApiParam(value="请输入{userId:用户id}AES加密后的结果")String params,HttpServletResponse response){	
		String content = Util.aesDecrypt(params);
		Map<String, Object> jsonMap=CommonClass.getMapByJson(content);
		Long userId = Long.valueOf(jsonMap.get("userId").toString());
		Long fromId = Long.valueOf(jsonMap.get("fromId").toString());
		String userName = jsonMap.get("userName").toString();
		String comment = jsonMap.get("content")+"".trim();
		AppObjectResult appObjectResult = null;
		
		boolean success = false;
		
		try {
			if(StringUtil.isNotEmpty(userId)&&StringUtil.isNotEmpty(fromId)){
				
				success = commentService.replyComment(fromId, userId, userName, comment);	//评论新闻
				if(success){
					appObjectResult = new AppObjectResult(SystemInfo.QUERY_SUCCESS.getCode(), SystemInfo.QUERY_SUCCESS.getMsg());		
				}else{
					appObjectResult = new AppObjectResult(SystemInfo.ILLEGAL_PARAMETER.getCode(), SystemInfo.ILLEGAL_PARAMETER.getMsg());
				}
			}else{
				appObjectResult = new AppObjectResult(SystemInfo.ILLEGAL_PARAMETER.getCode(), SystemInfo.ILLEGAL_PARAMETER.getMsg());
			}
			sendDataResponse(response,JSONUtil.getJSONString(appObjectResult));
		} catch (Exception e) {
			e.printStackTrace();
			sendDataResponse(response,"回复失败");
		}
	}
	
	@RequestMapping(params="method=getReplyListByCommentId",method=RequestMethod.POST)
	@ApiOperation(value="获取回复列表")
	public void getReplyListByCommentId(@ApiParam(value="请输入{userId:用户id}AES加密后的结果")String params,HttpServletResponse response){	
		String content = Util.aesDecrypt(params);
		Map<String, Object> jsonMap=CommonClass.getMapByJson(content);
		int pageNum = Integer.valueOf(jsonMap.get("pageNum").toString());
		Long fromId = Long.valueOf(jsonMap.get("fromId").toString());
		int size = Integer.valueOf(jsonMap.get("size").toString());
		AppObjectResult appObjectResult = null;
		try {
			if(StringUtil.isNotEmpty(pageNum)&&StringUtil.isNotEmpty(fromId)){
				List<Map<String, Object>> list = commentService.getReplyListByCommentId(fromId,size, pageNum);
					appObjectResult = new AppObjectResult(SystemInfo.QUERY_SUCCESS.getCode(), SystemInfo.QUERY_SUCCESS.getMsg(),list);
			}else{
				appObjectResult = new AppObjectResult(SystemInfo.ILLEGAL_PARAMETER.getCode(), SystemInfo.ILLEGAL_PARAMETER.getMsg());
			}
			sendDataResponse(response,JSONUtil.getJSONString(appObjectResult));
		} catch (Exception e) {
			e.printStackTrace();
			sendDataResponse(response,"获取失败");
		}
	}
	
	@RequestMapping(params="method=getCommentEntityById",method=RequestMethod.POST)
	@ApiOperation(value="根据评论id获取评论详情")
	public void getCommentEntityById(@ApiParam(value="请输入{userId:用户id}AES加密后的结果")String params,HttpServletResponse response){	
		String content = Util.aesDecrypt(params);
		Map<String, Object> jsonMap=CommonClass.getMapByJson(content);
		Long fromId = Long.valueOf(jsonMap.get("fromId").toString());
		Long userId = Long.valueOf(jsonMap.get("userId").toString());
		AppObjectResult appObjectResult = null;
		try {
			if(StringUtil.isNotEmpty(fromId)){
				PubCommentEntity commentEntity = commentService.getCommentEntityById(fromId,userId);
					appObjectResult = new AppObjectResult(SystemInfo.QUERY_SUCCESS.getCode(), SystemInfo.QUERY_SUCCESS.getMsg(),commentEntity);
			}else{
				appObjectResult = new AppObjectResult(SystemInfo.ILLEGAL_PARAMETER.getCode(), SystemInfo.ILLEGAL_PARAMETER.getMsg());
			}
			sendDataResponse(response,JSONUtil.getJSONString(appObjectResult));
		} catch (Exception e) {
			e.printStackTrace();
			sendDataResponse(response,"获取失败");
		}
	}
}
