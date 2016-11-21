package com.ennuova.app.controller;

import java.io.PrintWriter;
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

import com.ennuova.app.config.AppObjectResult;
import com.ennuova.app.config.SystemInfo;
import com.ennuova.app.service.OwNewsctService;
import com.ennuova.common.CommonClass;
import com.ennuova.controller.GeneralControl;
import com.ennuova.entity.OwNewsct;
import com.ennuova.util.HtmlUtil;
import com.ennuova.util.JSONUtil;
import com.ennuova.util.StringUtil;
import com.ennuova.util.Util;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

@Controller("owNewsindexController")
@RequestMapping("owNewsIndex.do")
public class OwNewsindexController extends GeneralControl{

	/*@Resource
	private OwNewsindexService owNewsindexService;*/

	@Resource
	private OwNewsctService owNewsctService;

	@RequestMapping(params = "method=newsIndex")
	public String newsindex(String params, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setContentType("text/json;charset=utf-8");
		String content = "";
		content = Util.aesDecrypt(params);
		Map<String, Object> jMap = CommonClass.getMapByJson(content);
		String fcompany = jMap.get("companyId").toString();
		long type =  Long.valueOf(jMap.get("type").toString());
		int page = Integer.parseInt(jMap.get("page").toString());
		//System.out.println("type:"+type);
		//int size = Integer.parseInt(jMap.get("size").toString()); 
		//System.out.println("新闻中心首页" + fcompany);
		List<OwNewsct> owNewsList = owNewsctService.queryPageByState(2l,fcompany,type,page,10);
		//List<OwNewsindex> owNewsindexList = owNewsindexService.queryNewsindex(fcompany);	
		JSONObject json = new JSONObject();
		json.put("newslist", owNewsList);
		//json.put("indexlist", owNewsindexList);
		PrintWriter out = response.getWriter();
		out.write(json.toString());
		out.flush();
		out.close();
		System.out.println(json.toString());
		return null;
	}

	/**
	 * 
	 * @Description: 进入新闻详情并增加浏览量
	 * @return String  
	 * @author felix
	 * @date 2015-12-10
	 */
	@RequestMapping(params = "method=newsdetail")
	public String newsdetail(String params, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setContentType("text/json;charset=utf-8");
		String content = "";
		content = Util.aesDecrypt(params);
		Map<String, Object> jMap = CommonClass.getMapByJson(content);
		long id = Long.valueOf(jMap.get("newsId").toString());
		//System.out.println("新闻中心详情" + id);
		OwNewsct news = owNewsctService.getNewsById(id);
		news.setFnrText(HtmlUtil.delATag(news.getFnrText()));
		int i=owNewsctService.growthFviewCount(id, "OW_NEWSCT");
		//System.out.println("增长："+i);
		PrintWriter out = response.getWriter();
		out.write(JSONObject.fromObject(news).toString());
		out.flush();
		out.close();
		//System.out.println(JSONObject.fromObject(news).toString());
		return null;
	}
	
	@RequestMapping(params = "method=newsIndex_v1_2",method=RequestMethod.POST)
	public String newsIndex_v1_2(String params, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setContentType("text/json;charset=utf-8");
		String content = "";
		content = Util.aesDecrypt(params);
		Map<String, Object> jMap = CommonClass.getMapByJson(content);
		String fcompany = jMap.get("companyId")+"";
		long type =  Long.valueOf(jMap.get("type").toString());
		int page = Integer.parseInt(jMap.get("page").toString());
		//System.out.println("type:"+type);
		//int size = Integer.parseInt(jMap.get("size").toString()); 
		//System.out.println("新闻中心首页" + fcompany);
		List<OwNewsct> owNewsList = owNewsctService.queryPageByState(2l,fcompany,type,page,10);
		//List<OwNewsindex> owNewsindexList = owNewsindexService.queryNewsindex(fcompany);	
		JSONObject json = new JSONObject();
		json.put("newslist", owNewsList);
		//json.put("indexlist", owNewsindexList);
		PrintWriter out = response.getWriter();
		out.write(json.toString());
		out.flush();
		out.close();
		//System.out.println(json.toString());
		return null;
	}

	/**
	 * 
	 * @Description: 进入新闻详情并增加浏览量
	 * @return String  
	 * @author felix
	 * @date 2015-12-10
	 */
	@RequestMapping(params = "method=newsdetail_v1_2",method=RequestMethod.POST)
	@ApiOperation(value="获取新闻详情",notes="参考英诺唯")
	public void newsdetail_v1_2(@ApiParam(value="请输入{newsId:新闻Id}AES加密后的结果")String params, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String getParams = Util.aesDecrypt(params);
		Map<String, Object> jsonMap = CommonClass.getMapByJson(getParams);
		String newsId = jsonMap.get("newsId")+"".trim();
		Long userId = Long.valueOf(jsonMap.get("userId").toString());
//		String newsId = "24253".trim();
		AppObjectResult appObjectResult = null;
		if(StringUtil.isNotEmpty(newsId)){
			Map<String, Object> resultMap = owNewsctService.findDetailAndRelation(newsId,userId);
			appObjectResult = new AppObjectResult(SystemInfo.QUERY_SUCCESS.getCode(), SystemInfo.QUERY_SUCCESS.getMsg(), resultMap);
		}else{
			appObjectResult = new AppObjectResult(SystemInfo.ILLEGAL_PARAMETER.getCode(), SystemInfo.ILLEGAL_PARAMETER.getMsg());
		}
		sendDataResponse(response,JSONUtil.getJSONString(appObjectResult));
		
	}
	
	/**
	 * 查找新闻的相关的列表
	 * @param params
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(params="method=newsRelation",method=RequestMethod.POST)
	@ResponseBody
	@ApiOperation(value = "新闻的相关",notes="{type:类型,keyword:关键字}")
	public void newsRelation(@ApiParam(value="输入{type:类型,keyword:关键字}")String params,HttpServletResponse response) throws Exception {	
		String getParams = Util.aesDecrypt(params);
		Map<String, Object> jsonMap = CommonClass.getMapByJson(getParams);
		String type = jsonMap.get("type")+"".trim();
		String keyword = jsonMap.get("keyword")+"".trim();
		AppObjectResult appObjectResult = null;
		if(StringUtil.isNotEmpty(type)){
			List<Map<String, Object>> resultList = owNewsctService.findListRelation(type,keyword);
			appObjectResult = new AppObjectResult(SystemInfo.QUERY_SUCCESS.getCode(), SystemInfo.QUERY_SUCCESS.getMsg(), resultList);
		}else{
			appObjectResult = new AppObjectResult(SystemInfo.ILLEGAL_PARAMETER.getCode(), SystemInfo.ILLEGAL_PARAMETER.getMsg(),"请登录!");
		}
		sendDataResponse(response,JSONUtil.getJSONString(appObjectResult));
	}
	
	
	@RequestMapping(params = "method=clickGood",method=RequestMethod.POST)
	@ApiOperation(value="点赞",notes="参考英诺唯")
	public String clickGood(@ApiParam(value="请输入{companyId:组织Id,type:获取类型(新车上市,新闻中心,优惠促销),page:页码}AES加密后的结果")String params, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setContentType("text/json;charset=utf-8");
		String content = "";
		content = Util.aesDecrypt(params);
		Map<String, Object> jMap = CommonClass.getMapByJson(content);
		long fromId =  Long.valueOf(jMap.get("fromId").toString());
		long userId =  Long.valueOf(jMap.get("userId").toString());
		int type = Integer.parseInt(jMap.get("type").toString());
		boolean success = false;
		AppObjectResult appObjectResult = null;
		try {
			if(StringUtil.isNotEmpty(userId)&&StringUtil.isNotEmpty(fromId)){
				success = owNewsctService.clickGood(type, fromId, userId);
				if(success){
					appObjectResult = new AppObjectResult(SystemInfo.PLAY_SUCCESS.getCode(), SystemInfo.PLAY_SUCCESS.getMsg());
				}else{
					appObjectResult = new AppObjectResult(SystemInfo.PLAY_FAIL.getCode(), SystemInfo.PLAY_FAIL.getMsg());
				}
			}else{
				appObjectResult = new AppObjectResult(SystemInfo.ILLEGAL_PARAMETER.getCode(), SystemInfo.ILLEGAL_PARAMETER.getMsg());
			}
		} catch (Exception e) {
			e.printStackTrace();
			try {
				appObjectResult = new AppObjectResult(SystemInfo.PLAY_FAIL.getCode(), SystemInfo.PLAY_FAIL.getMsg());
			} catch (Exception e2) {
				e.printStackTrace();
			}
		}
		sendDataResponse(response,JSONUtil.getJSONString(appObjectResult));
		return null;
	}

	@RequestMapping(params = "method=getNewsByLineIdOrVId",method=RequestMethod.POST)
	@ApiOperation(value="分页获取新闻首页列表",notes="参考英诺唯")
	public String getNewsByLineIdOrVId(@ApiParam(value="请输入{type:新闻类型 1-车系 2-车型,typeId:车系或者车型Id,page:页码,isDetatil:是否是车系详解}AES加密后的结果")String params, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setContentType("text/json;charset=utf-8");
		String content = "";
		content = Util.aesDecrypt(params);
		Map<String, Object> jMap = CommonClass.getMapByJson(content);
		long typeId =  Long.valueOf(jMap.get("typeId").toString());
		int page = Integer.parseInt(jMap.get("page").toString());
		int type = Integer.parseInt(jMap.get("type").toString());
		int isDetatil = Integer.parseInt(jMap.get("isDetatil").toString());
		if(page>0){
			page--;
		}
		List<OwNewsct> owNewsList = owNewsctService.getNewsByLineIdOrVId(type, typeId, page, 8, isDetatil);
		JSONObject json = new JSONObject();
		json.put("newslist", owNewsList);
		PrintWriter out = response.getWriter();
		out.write(json.toString());
		out.flush();
		out.close();
		//System.out.println(json.toString());
		return null;
	}
}
