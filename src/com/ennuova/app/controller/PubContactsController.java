package com.ennuova.app.controller;
import java.math.BigDecimal;
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
import com.ennuova.app.service.CusLocationService;
import com.ennuova.app.service.PubContactsService;
import com.ennuova.app.service.PubDynamicService;
import com.ennuova.app.service.PubReportService;
import com.ennuova.common.CommonClass;
import com.ennuova.controller.GeneralControl;
import com.ennuova.entity.Cuslocation;
import com.ennuova.entity.PubContacts;
import com.ennuova.entity.PubReport;
import com.ennuova.util.DateUtil;
import com.ennuova.util.JSONUtil;
import com.ennuova.util.JsonUtils;
import com.ennuova.util.MapDistanceUtil;
import com.ennuova.util.StringUtil;
import com.ennuova.util.Util;
import com.sun.xml.wss.impl.SecurityAnnotator;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

import scala.tools.nsc.transform.PostErasure;

@Controller
@RequestMapping("/PubContactsController")
@Api(value="", description="附近的人")
public class PubContactsController extends GeneralControl{

	/**
	 * 联系人的服务
	 */
	@Resource
	private PubContactsService pubContactsService;
	
	/**
	 * 客户位置服务
	 */
	@Resource
	private CusLocationService cusLocationService;
	
	/**
	 * 举报的服务
	 */
	@Resource
	private PubReportService pubReportService;
	
	/**
	 * 动态服务
	 */
	@Resource
	private PubDynamicService pubDynamicService;
	
	/**
	 * 关注,取消关注,拉黑
	 * @author sududa
	 * @date 2016年10月18日
	 * @param params
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(params="method=doFollowAndUnFollow",method=RequestMethod.POST)
	@ResponseBody
	@ApiOperation(value = "关注,取消关注,拉黑", notes="{loginId:登录的id,loginType:登录的类型(1-车主,2-员工),contactsId:联系人的id,contactsType:联系人的类型(1-车主,2-员工),relation:与联系人的关系(1-关注,2-拉黑,3-取消关注)}")
	public void doFollowAndUnFollow(@ApiParam(value="输入{loginId:登录的id,loginType:登录的类型(1-车主,2-员工),contactsId:联系人的id,contactsType:联系人的类型(1-车主,2-员工),relation:与联系人的关系(1-关注,2-拉黑,3-取消关注)}")String params,HttpServletResponse response) throws Exception {	
		String getParams = Util.aesDecrypt(params);
		Map<String, Object> jsonMap = CommonClass.getMapByJson(getParams);
		String loginId = jsonMap.get("loginId")+"".trim();//登录的id
		String loginType = jsonMap.get("loginType")+"".trim();//登录的类型(1-车主,2-员工)
		String contactsId = jsonMap.get("contactsId")+"".trim();//联系人的id
		String contactsType = jsonMap.get("contactsType")+"".trim();//联系人的类型(1-车主,2-员工)
		String relation = jsonMap.get("relation")+"".trim();//与联系人的关系(1-关注,2-拉黑)
//		String loginId = "966".trim();//登录的id
//		String loginType = "1".trim();//登录的类型(1-车主,2-员工)
//		String contactsId = "32".trim();//联系人的id
//		String contactsType = "1".trim();//联系人的类型(1-车主,2-员工)
//		String relation = "1".trim();//与联系人的关系(1-关注,2-拉黑,3-取消关注)
//		PubContacts pubContacts = new PubContacts();
//		pubContacts.setLoginId(loginId);
//		pubContacts.setLoginType(loginType);
//		pubContacts.setContactsId(contactsId);
//		pubContacts.setContactsType(contactsType);
//		pubContacts.setRelation(relation);
//		Date createTime = DateUtil.getCurrentTime();
//		pubContacts.setCreatetime(createTime);
		AppObjectResult appObjectResult = null;
		if(StringUtil.isnotObjectsNotEmpty(loginId,loginType,contactsId,contactsType,relation)){
			PubContacts pubContacts = JsonUtils.toObject(getParams, PubContacts.class);
			Date createTime = DateUtil.getCrruentTimestamp();
			pubContacts.setCreatetime(createTime);
			Integer redultInt = pubContactsService.doFollowAndUnFollow(pubContacts);
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
	 * 关注的人列表
	 *@author 陈晓珊
	 *@date 2016年10月21日
	 * @param params
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(params="method=doFollowList",method=RequestMethod.POST)
	@ResponseBody
	@ApiOperation(value = "关注的人列表", notes="{loginId:登录id}")
	public void doFollowList(@ApiParam(value="输入{loginId:登录id}")String params,HttpServletResponse response) throws Exception {	
		String getParams = Util.aesDecrypt(params);
		Map<String, Object> jsonMap = CommonClass.getMapByJson(getParams);
		String loginId = jsonMap.get("loginId")+"".trim();//登录id
		//String loginId = "32".trim();//登录id
		AppObjectResult appObjectResult = null;
		if(StringUtil.isNotEmpty(loginId)){
			List<Map<String, Object>> resultList = pubContactsService.doFollowList(loginId);
			appObjectResult = new AppObjectResult(SystemInfo.QUERY_SUCCESS.getCode(), SystemInfo.QUERY_SUCCESS.getMsg(),resultList);
		}else{
			appObjectResult = new AppObjectResult(SystemInfo.ILLEGAL_PARAMETER.getCode(), SystemInfo.ILLEGAL_PARAMETER.getMsg(),"请登录!");
		}
		sendDataResponse(response,JSONUtil.getJSONString(appObjectResult));
	}
	
	/**
	 * 粉丝列表
	 *@author 陈晓珊
	 *@date 2016年10月21日
	 * @param params
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(params="method=doFansList",method=RequestMethod.POST)
	@ResponseBody
	@ApiOperation(value = "粉丝列表", notes="{loginId:登录id}")
	public void doFansList(@ApiParam(value="输入{loginId:登录id}")String params,HttpServletResponse response) throws Exception {	
		String getParams = Util.aesDecrypt(params);
		Map<String, Object> jsonMap = CommonClass.getMapByJson(getParams);
		String loginId = jsonMap.get("loginId")+"".trim();//登录id
		//String loginId = "32".trim();//登录id
		AppObjectResult appObjectResult = null;
		if(StringUtil.isNotEmpty(loginId)){
			List<Map<String, Object>> resultList = pubContactsService.doFansList(loginId);
			appObjectResult = new AppObjectResult(SystemInfo.QUERY_SUCCESS.getCode(), SystemInfo.QUERY_SUCCESS.getMsg(),resultList);
		}else{
			appObjectResult = new AppObjectResult(SystemInfo.ILLEGAL_PARAMETER.getCode(), SystemInfo.ILLEGAL_PARAMETER.getMsg(),"请登录!");
		}
		sendDataResponse(response,JSONUtil.getJSONString(appObjectResult));
	}
	
	/**
	 * 黑名单列表
	 *@author 陈晓珊
	 *@date 2016年10月21日
	 * @param params
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(params="method=doBlackList",method=RequestMethod.POST)
	@ResponseBody
	@ApiOperation(value = "黑名单列表", notes="{loginId:登录id}")
	public void doBlackList (@ApiParam(value="输入{loginId:登录id}")String params,HttpServletResponse response) throws Exception {	
		String getParams = Util.aesDecrypt(params);
		Map<String, Object> jsonMap = CommonClass.getMapByJson(getParams);
		String loginId = jsonMap.get("loginId")+"".trim();//登录id
		//String loginId = "32".trim();//登录id
		AppObjectResult appObjectResult = null;
		if(StringUtil.isNotEmpty(loginId)){
			List<Map<String, Object>> resultList = pubContactsService.doBlackList(loginId);
			appObjectResult = new AppObjectResult(SystemInfo.QUERY_SUCCESS.getCode(), SystemInfo.QUERY_SUCCESS.getMsg(),resultList);
		}else{
			appObjectResult = new AppObjectResult(SystemInfo.ILLEGAL_PARAMETER.getCode(), SystemInfo.ILLEGAL_PARAMETER.getMsg(),"请登录!");
		}
		sendDataResponse(response,JSONUtil.getJSONString(appObjectResult));
	}
	
	/**
	 * 联系人列表
	 *@author 陈晓珊
	 *@date 2016年10月21日
	 * @param params
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(params="method=doContactsList",method=RequestMethod.POST)
	@ResponseBody
	@ApiOperation(value = "联系人列表", notes="{loginId:登录id}")
	public void doContactsList (@ApiParam(value="输入{loginId:登录id}")String params,HttpServletResponse response) throws Exception {	
		String getParams = Util.aesDecrypt(params);
		Map<String, Object> jsonMap = CommonClass.getMapByJson(getParams);
		String loginId = jsonMap.get("loginId")+"".trim();//登录id
//		String loginId = "32".trim();//登录id
		AppObjectResult appObjectResult = null;
		if(StringUtil.isNotEmpty(loginId)){
			List<Map<String, Object>> resultList = pubContactsService.doContactsList(loginId);
			appObjectResult = new AppObjectResult(SystemInfo.QUERY_SUCCESS.getCode(), SystemInfo.QUERY_SUCCESS.getMsg(),resultList);
		}else{
			appObjectResult = new AppObjectResult(SystemInfo.ILLEGAL_PARAMETER.getCode(), SystemInfo.ILLEGAL_PARAMETER.getMsg(),"请登录!");
		}
		sendDataResponse(response,JSONUtil.getJSONString(appObjectResult));
	}
	
	/**
	 * 联系人详情
	 *@author 陈晓珊
	 *@date 2016年10月25日
	 * @param params
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(params="method=doContactDetail",method=RequestMethod.POST)
	@ResponseBody
	@ApiOperation(value = "联系人详情", notes="{id:联系人id}")
	public void doContactDetail (@ApiParam(value="输入{id:联系人id}")String params,HttpServletResponse response) throws Exception {	
		String getParams = Util.aesDecrypt(params);
		Map<String, Object> jsonMap = CommonClass.getMapByJson(getParams);
		String id = jsonMap.get("id")+"".trim();//登录id
		//String id = "32".trim();//登录id
		AppObjectResult appObjectResult = null;
		if(StringUtil.isNotEmpty(id)){
			Map<String, Object> resultMap = pubContactsService.doContactDetail(id);
			appObjectResult = new AppObjectResult(SystemInfo.QUERY_SUCCESS.getCode(), SystemInfo.QUERY_SUCCESS.getMsg(),resultMap);
		}else{
			appObjectResult = new AppObjectResult(SystemInfo.ILLEGAL_PARAMETER.getCode(), SystemInfo.ILLEGAL_PARAMETER.getMsg(),"请登录!");
		}
		sendDataResponse(response,JSONUtil.getJSONString(appObjectResult));
	}
	
	/**
	 * 车友数量
	 * @author 陈晓珊
	 * @date 2016年10月21日
	 * @param params
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(params="method=doRidersNum",method=RequestMethod.POST)
	@ResponseBody
	@ApiOperation(value = "车友数量", notes="{loginId:登录id}")
	public void doRidersNum (@ApiParam(value="输入{loginId:登录id}")String params,HttpServletResponse response) throws Exception {	
		String getParams = Util.aesDecrypt(params);
		Map<String, Object> jsonMap = CommonClass.getMapByJson(getParams);
		String loginId = jsonMap.get("loginId")+"".trim();//登录id
//		String loginId = "258".trim();//登录id
//		String loginId = jsonMap.get("loginId")+"".trim();//登录id
		//String loginId = "258".trim();//登录id
		AppObjectResult appObjectResult = null;
		if(StringUtil.isNotEmpty(loginId)){
			Map<String, Object> resultList = pubContactsService.doRidersNum(loginId);
			appObjectResult = new AppObjectResult(SystemInfo.QUERY_SUCCESS.getCode(), SystemInfo.QUERY_SUCCESS.getMsg(),resultList);
		}else{
			appObjectResult = new AppObjectResult(SystemInfo.ILLEGAL_PARAMETER.getCode(), SystemInfo.ILLEGAL_PARAMETER.getMsg(),"请登录!");
		}
		sendDataResponse(response,JSONUtil.getJSONString(appObjectResult));
	}
	
	
	
	/**
	 * 保存客户的位置
	 *@author sududa
	 *@date 2016年10月18日
	 * @param params
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(params="method=doGetLocation",method=RequestMethod.POST)
	@ResponseBody
	@ApiOperation(value = "保存客户的位置", notes="{cusId:登录的id,lng:经度,lat:纬度,locationType:获取位置的类型(1-手机,2-车)}")
	public void doGetLocation(@ApiParam(value="输入{cusId:登录的id,lng:经度,lat:纬度,locationType:获取位置的类型(1-手机,2-车)}")String params,HttpServletResponse response) throws Exception {	
		String getParams = Util.aesDecrypt(params);
		Map<String, Object> jsonMap = CommonClass.getMapByJson(getParams);
		String cusId = jsonMap.get("cusId")+"".trim();//登录的id
		String lng = jsonMap.get("lng")+"".trim();//经度
		String lat = jsonMap.get("lat")+"".trim();//纬度
		String locationType = jsonMap.get("locationType")+"".trim();//获取位置的类型(1-手机,2-车)
//		String cusId = "332".trim();//登录的id
//		String lng = "118.0215".trim();//经度
//		String lat = "12.02154".trim();//纬度
//		String locationType = "1".trim();//获取位置的类型(1-手机,2-车)
//		Cuslocation cuslocation = new Cuslocation();
//		cuslocation.setCusId(Integer.valueOf(cusId));
//		cuslocation.setLng(Float.valueOf(lng));
//		cuslocation.setLat(Float.valueOf(lat));
//		cuslocation.setLocationType(locationType);
		AppObjectResult appObjectResult = null;
		if(StringUtil.isnotObjectsNotEmpty(cusId,lng,lat,locationType)){
			Cuslocation cuslocation = JsonUtils.toObject(getParams, Cuslocation.class);
			Date createTime = DateUtil.getCurrentDate();
			cuslocation.setCreatetime(createTime);
			Integer resultInt = cusLocationService.doGetLocation(cuslocation);
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
	 * 清除位置并退出
	 *@author sududa
	 *@date 2016年10月19日
	 * @param params
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(params="method=doCleanLocation",method=RequestMethod.POST)
	@ResponseBody
	@ApiOperation(value = "清除位置并退出", notes="{cusId:登录的id}")
	public void doCleanLocation(@ApiParam(value="输入{cusId:登录的id}")String params,HttpServletResponse response) throws Exception {	
		String getParams = Util.aesDecrypt(params);
		Map<String, Object> jsonMap = CommonClass.getMapByJson(getParams);
		String cusId = jsonMap.get("cusId")+"".trim();//登录的id
//		String cusId = "259".trim();//登录的id
		AppObjectResult appObjectResult = null;
		if(StringUtil.isNotEmpty(cusId)){
			Integer resultInt = cusLocationService.doCleanLocation(cusId);
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
	 * 附近的人列表-并保持当前人的位置，或者修改当前人的位置
	 *@author sududa
	 *@date 2016年10月19日
	 * @param params
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(params="method=doList",method=RequestMethod.POST)
	@ResponseBody
	@ApiOperation(value = "附近的人列表", notes="{loginId:登录id,interest:兴趣,authentication:1-认证的车主,sex:1-男 2-女,lng:经度,lat:纬度,locationType:获取位置的类型(1-手机,2-车),page:当前页(默认值为1),rows:每页显示的条数(默认值为10)}")
	public void doList(@ApiParam(value="输入{loginId:登录id,interest:兴趣,authentication:1-认证的车主,sex:1-男 2-女,lng:经度,lat:纬度,locationType:获取位置的类型(1-手机,2-车),page:当前页(默认值为1),rows:每页显示的条数(默认值为10)}")String params,HttpServletResponse response) throws Exception {	
		String getParams = Util.aesDecrypt(params);
		Map<String, Object> jsonMap = CommonClass.getMapByJson(getParams);
		String loginId = jsonMap.get("loginId")+"".trim();//登录id
		String interest = jsonMap.get("interest")+"".trim();//兴趣
		String authentication = jsonMap.get("authentication")+"".trim();//认证的车主
		String lng = jsonMap.get("lng")+"".trim();//经度
		String lat = jsonMap.get("lat")+"".trim();//纬度
		String locationType = jsonMap.get("locationType")+"".trim();//获取位置的类型(1-手机,2-车)
		String getPage = jsonMap.get("page")+"".trim();//当前页(默认值为1)
		String getRows = jsonMap.get("rows")+"".trim();//每页显示的条数(默认值为10)
		String sex = jsonMap.get("sex")+"".trim();//性别
		Integer page = 1;
		Integer rows = 10;
		if(StringUtil.isNotEmpty(getPage)){
			page = Integer.valueOf(getPage);
		}
		if(StringUtil.isNotEmpty(getRows)){
			rows = Integer.valueOf(getRows);
		}
//		String loginId = "32".trim();//登录id
//		String interest = "".trim();//兴趣
//		String authentication = "".trim();//认证的车主
//		String lng = "118".trim();//经度
//		String lat = "24".trim();//纬度
//		String sex = "".trim();//纬度
//		String locationType = "1".trim();//获取位置的类型(1-手机,2-车)
		AppObjectResult appObjectResult = null;
		if(StringUtil.isnotObjectsNotEmpty(loginId,lng,lat,locationType)){
			Cuslocation cuslocation = new Cuslocation();
			cuslocation.setCusId(Integer.valueOf(loginId));
			cuslocation.setLng(Float.valueOf(lng));
			cuslocation.setLat(Float.valueOf(lat));
			cuslocation.setLocationType(locationType);
			Date createTime = DateUtil.getCurrentDate();
			cuslocation.setCreatetime(createTime);
			Integer resultInt = cusLocationService.doGetLocation(cuslocation);
			if(resultInt > 0){
				List<Map<String, Object>> resultList = cusLocationService.doList(page,rows,interest,authentication,loginId,lng,lat,sex);
				appObjectResult = new AppObjectResult(SystemInfo.QUERY_SUCCESS.getCode(), SystemInfo.QUERY_SUCCESS.getMsg(),resultList);
			}else{
				appObjectResult = new AppObjectResult(SystemInfo.PLAY_FAIL.getCode(), SystemInfo.PLAY_FAIL.getMsg());
			}
		}else{
			appObjectResult = new AppObjectResult(SystemInfo.ILLEGAL_PARAMETER.getCode(), SystemInfo.ILLEGAL_PARAMETER.getMsg(),"请登录!");
		}
		sendDataResponse(response,JSONUtil.getJSONString(appObjectResult));
	}
	
	/**
	 * 附近的人详情
	 *@author sududa
	 *@date 2016年10月19日
	 * @param params
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(params="method=doDetail",method=RequestMethod.POST)
	@ResponseBody
	@ApiOperation(value = "附近的人详情", notes="{loginId:登录id,id:附近的人的主键id,page:当前页(默认值为1),rows:每页显示的条数(默认值为3)}")
	public void doDetail(@ApiParam(value="输入{loginId:登录id,,id:附近的人的主键id,page:当前页(默认值为1),rows:每页显示的条数(默认值为3)}")String params,HttpServletResponse response) throws Exception {	
		String getParams = Util.aesDecrypt(params);
		Map<String, Object> jsonMap = CommonClass.getMapByJson(getParams);
		String loginId = jsonMap.get("loginId")+"".trim();//登录id
		String id = jsonMap.get("id")+"".trim();//附近人的记录id
		String getPage = jsonMap.get("page")+"".trim();//当前页(默认值为1)
		String getRows = jsonMap.get("rows")+"".trim();//每页显示的条数(默认值为3)
		Integer page = 1;
		Integer rows = 3;
		if(StringUtil.isNotEmpty(getPage)){
			page = Integer.valueOf(getPage);
		}
		if(StringUtil.isNotEmpty(getRows)){
			rows = Integer.valueOf(getRows);
		}
//		String loginId = "258".trim();//登录id
//		String id = "966".trim();//附近人的记录id
		AppObjectResult appObjectResult = null;
		if(StringUtil.isnotObjectsNotEmpty(loginId,id)){
			Map<String, Object> resultMap = pubDynamicService.doNearbyDetail(loginId,id,page,rows);
			appObjectResult = new AppObjectResult(SystemInfo.QUERY_SUCCESS.getCode(), SystemInfo.QUERY_SUCCESS.getMsg(),resultMap);
		}else{
			appObjectResult = new AppObjectResult(SystemInfo.ILLEGAL_PARAMETER.getCode(), SystemInfo.ILLEGAL_PARAMETER.getMsg(),"请登录!");
		}
		sendDataResponse(response,JSONUtil.getJSONString(appObjectResult));
	}
	
	
	
	/**
	 * 举报
	 *@author chenxiaoshan
	 *@date 2016年10月20日
	 * @param params
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(params="method=doReport",method=RequestMethod.POST)
	@ResponseBody
	@ApiOperation(value = "举报", notes="{loginId:登录的id,loginType:登录的类型(1-车主,2-员工),reportId:被举报人的id,reportType:被举报人的类型(1-车主,2-员工),content:举报的内容}")
	public void doReport(@ApiParam(value="输入{loginId:登录的id,loginType:登录的类型(1-车主,2-员工),reportId:被举报人的id,reportType:被举报人的类型(1-车主,2-员工),content:举报的内容}")String params,HttpServletResponse response) throws Exception {	
		String getParams = Util.aesDecrypt(params);
		Map<String, Object> jsonMap = CommonClass.getMapByJson(getParams);
		String loginId = jsonMap.get("loginId")+"".trim();//登录的id
		String loginType = jsonMap.get("loginType")+"".trim();//登录的类型(1-车主,2-员工)
		String reportId = jsonMap.get("reportId")+"".trim();//被举报人的id
		String reportType = jsonMap.get("reportType")+"".trim();//被举报人的类型(1-车主,2-员工)
		String content = jsonMap.get("content")+"".trim();//举报的内容
//		String loginId = "250".trim();//登录的id
//		String loginType = "1".trim();//登录的类型(1-车主,2-员工)
//		String reportId = "254".trim();//被举报人的id
//		String reportType = "1".trim();//被举报人的类型(1-车主,2-员工)
//		String content = "水电费水电费".trim();//举报的内容
//		PubReport pubReport = new PubReport();
//		pubReport.setLoginId(loginId);
//		pubReport.setLoginType(loginType);
//		pubReport.setReportId(reportId);
//		pubReport.setReportType(reportType);
//		pubReport.setContent(content);
		AppObjectResult appObjectResult = null;
		if(StringUtil.isnotObjectsNotEmpty(loginId,loginType,reportId,reportType,content)){
			PubReport pubReport = JsonUtils.toObject(getParams, PubReport.class);
			Date createTime = DateUtil.getCrruentTimestamp();
			pubReport.setCreatetime(createTime);
			Integer redultInt = pubReportService.doReport(pubReport);
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
}
