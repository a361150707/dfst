package com.ennuova.app.controller;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ennuova.app.config.AppObjectResult;
import com.ennuova.app.config.SystemInfo;
import com.ennuova.app.service.CarInfoService;
import com.ennuova.common.CommonClass;
import com.ennuova.controller.GeneralControl;
import com.ennuova.entity.CarLocation;
import com.ennuova.entity.TestInsert;
import com.ennuova.tools.wxpay.util.JsonUtil;
import com.ennuova.util.JSONUtil;
import com.ennuova.util.JsonUtils;
import com.ennuova.util.StringUtil;
import com.ennuova.util.Util;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

@Controller
@RequestMapping("/nearbyCarController")
@Api(value="", description="附近的车")
public class NearbyCarController extends GeneralControl{
	private static Logger logger = Logger.getLogger(NearbyCarController.class);

	/**
	 * 车辆的服务
	 */
	@Resource
	private CarInfoService carInfoService;
	
	/**
	 * 附近的车列表
	 * @author sududa
	 * @date 2016年11月7日
	 */
	@RequestMapping(params="method=doList",method=RequestMethod.POST)
	@ResponseBody
	@ApiOperation(value = "附近的车列表", notes="{page:当前页(默认值为1),rows:每页显示的条数(默认值为10),cusId:登录用户的id,defCarId:默认车id,cusInterest:客户兴趣标签,cusNick:客户昵称,lineName:车系名称,lng:经度,lat:纬度,sex:性别(1_男,2_女)}")
	public void doList(@ApiParam(value="输入{page:当前页(默认值为1),rows:每页显示的条数(默认值为10),cusId:登录用户的id,defCarId:默认车id,cusInterest:客户兴趣标签,cusNick:客户昵称,lineName:车系名称,lng:经度,lat:纬度,sex:性别(1_男,2_女)}")String params,HttpServletResponse response){	
		AppObjectResult appObjectResult = null;
		try {
			String getParams = Util.aesDecrypt(params);
			Map<String, Object> jsonMap = CommonClass.getMapByJson(getParams);
			String loginId = jsonMap.get("cusId")+"".trim();//登录id
			String defCarId = jsonMap.get("defCarId")+"".trim();//登录用户的默认车辆id
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
//			String loginId = "990".trim();//登录id
//			String defCarId = "1027".trim();//登录用户的默认车辆id
//			CarLocation carLocation = new CarLocation();
//			carLocation.setCusId(Integer.valueOf(loginId));
//			carLocation.setDefCarId(defCarId);
//			carLocation.setLineName("宝马5系");
//			carLocation.setLng(118.10F);
//			carLocation.setLat(24.46F);
//			carLocation.setSex(1);
			if(StringUtil.isnotObjectsNotEmpty(loginId,defCarId)){
				CarLocation carLocation = JsonUtils.toObject(getParams, CarLocation.class);
				List<Map<String, Object>> resultList = carInfoService.doList(page,rows,carLocation,defCarId);
				appObjectResult = new AppObjectResult(SystemInfo.QUERY_SUCCESS.getCode(), SystemInfo.QUERY_SUCCESS.getMsg(),resultList);
			}else{
				appObjectResult = new AppObjectResult(SystemInfo.ILLEGAL_PARAMETER.getCode(), SystemInfo.ILLEGAL_PARAMETER.getMsg(),"请登录!");
			}
			sendDataResponse(response,JSONUtil.getJSONString(appObjectResult));
		} catch (Exception e) {
			logger.error("附近的车列表控制类:_附近的车列表"+e.getMessage());
			e.printStackTrace();
		}
	}
	
	/**
	 * 清除车辆位置信息并退出
	 * @author sududa
	 * @date 2016年11月7日
	 */
	@RequestMapping(params="method=doCleanCarLocation",method=RequestMethod.POST)
	@ResponseBody
	@ApiOperation(value = "清除车辆位置信息并退出", notes="{cusId:登录用户的id}")
	public void doCleanCarLocation(@ApiParam(value="输入{cusId:登录用户的id}")String params,HttpServletResponse response){	
		AppObjectResult appObjectResult = null;
		try {
			String getParams = Util.aesDecrypt(params);
			Map<String, Object> jsonMap = CommonClass.getMapByJson(getParams);
			String cusId = jsonMap.get("cusId")+"".trim();//登录id
//			String cusId = "210".trim();//登录id
			if(StringUtil.isNotEmpty(cusId)){
				Integer resultInt = carInfoService.doCleanCarLocation(cusId);
				if(resultInt > 0){
					appObjectResult = new AppObjectResult(SystemInfo.PLAY_SUCCESS.getCode(), SystemInfo.PLAY_SUCCESS.getMsg());
				}else{
					appObjectResult = new AppObjectResult(SystemInfo.IGNORE_ERROR.getCode(), SystemInfo.IGNORE_ERROR.getMsg());
				}
			}else{
				appObjectResult = new AppObjectResult(SystemInfo.ILLEGAL_PARAMETER.getCode(), SystemInfo.ILLEGAL_PARAMETER.getMsg(),"请登录!");
			}
			sendDataResponse(response,JSONUtil.getJSONString(appObjectResult));
		} catch (Exception e) {
			logger.error("附近的车列表控制类:_清除车辆信息并退出"+e.getMessage());
			e.printStackTrace();
		}
	}
	
	/**
	 * 查询我的车辆位置信息
	 * @author sududa
	 * @date 2016年11月10日
	 */
	@RequestMapping(params="method=QueryMyCarLocation",method=RequestMethod.POST)
	@ResponseBody
	@ApiOperation(value = "查询我的车辆位置信息", notes="{cusId:登录用户的id,defCarId:默认车id}")
	public void QueryMyCarLocation(@ApiParam(value="输入{cusId:登录用户的id,defCarId:默认车id}")String params,HttpServletResponse response){	
		AppObjectResult appObjectResult = null;
		try {
			String getParams = Util.aesDecrypt(params);
			Map<String, Object> jsonMap = CommonClass.getMapByJson(getParams);
			String cusId = jsonMap.get("cusId")+"".trim();//登录id
			String defCarId = jsonMap.get("defCarId")+"".trim();//登录用户的默认车辆id
//			String cusId = "326".trim();//登录id
//			String defCarId = "430".trim();//登录用户的默认车辆id
			if(StringUtil.isnotObjectsNotEmpty(cusId,defCarId)){
				Map<String, Object> resultMap = carInfoService.QueryMyCarLocation(cusId,defCarId);
				appObjectResult = new AppObjectResult(SystemInfo.QUERY_SUCCESS.getCode(), SystemInfo.QUERY_SUCCESS.getMsg(),resultMap);
			}else{
				appObjectResult = new AppObjectResult(SystemInfo.ILLEGAL_PARAMETER.getCode(), SystemInfo.ILLEGAL_PARAMETER.getMsg(),"请登录!");
			}
			sendDataResponse(response,JSONUtil.getJSONString(appObjectResult));
		} catch (Exception e) {
			logger.error("附近的车控制类_查询我的车信息:"+e.getMessage());
			e.printStackTrace();
		}
	}
	
	/**
	 * 测试批量插入数据库
	 * @author sududa
	 * @date 2016年11月17日
	 */
	@RequestMapping(params="method=testSaveBatch",method=RequestMethod.POST)
	@ResponseBody
	@ApiOperation(value = "测试批量插入数据库", notes="{cusId:登录用户的id,defCarId:默认车id}")
	public void testSaveBatch(@ApiParam(value="输入{cusId:登录用户的id,defCarId:默认车id}")String params,HttpServletResponse response){	
		AppObjectResult appObjectResult = null;
		try {
			long startTime=System.currentTimeMillis();
//			String getParams = Util.aesDecrypt(params);
//			Map<String, Object> jsonMap = CommonClass.getMapByJson(getParams);
//			String cusId = jsonMap.get("cusId")+"".trim();//登录id
//			String defCarId = jsonMap.get("defCarId")+"".trim();//登录用户的默认车辆id
			Integer cusId = 20001;//登录id
			float lng = 118.02F;
			float lat = 20.20F;
			String cusPhoto = "12345456sss";
			String defCarId = "430".trim();//登录用户的默认车辆id
			if(StringUtil.isnotObjectsNotEmpty(cusId,defCarId)){
				List<TestInsert> testInserts = new ArrayList<TestInsert>();
				TestInsert testInsert = null;
				for(int i=0;i<1000;i++){
					testInsert = new TestInsert();
					testInsert.setCusId(cusId+i);
					testInsert.setLng(lng+i);
					testInsert.setLat(lat+i);
					testInsert.setCreatetime(new Date());
					testInsert.setCusPhoto(cusPhoto+i);
					testInserts.add(testInsert);
//					carInfoService.saveOne(testInsert);
				}
				carInfoService.testSaveBatch(testInserts, 50);
				appObjectResult = new AppObjectResult(SystemInfo.QUERY_SUCCESS.getCode(), SystemInfo.QUERY_SUCCESS.getMsg());
			}else{
				appObjectResult = new AppObjectResult(SystemInfo.ILLEGAL_PARAMETER.getCode(), SystemInfo.ILLEGAL_PARAMETER.getMsg(),"请登录!");
			}
			sendDataResponse(response,JSONUtil.getJSONString(appObjectResult));
			long endTime=System.currentTimeMillis();
			float excTime=(float)(endTime-startTime)/1000;
			System.out.println("执行时间："+excTime+"s");
		} catch (Exception e) {
			logger.error("附近的车控制类_查询我的车信息:"+e.getMessage());
			e.printStackTrace();
		}
	}
	
	@RequestMapping(params="method=testGitAdd",method=RequestMethod.POST)
	@ResponseBody
	@ApiOperation(value = "测试批量插入数据库", notes="{cusId:登录用户的id,defCarId:默认车id}")
	public void testGitAdd(@ApiParam(value="输入{cusId:登录用户的id,defCarId:默认车id}")String params,HttpServletResponse response){	
		AppObjectResult appObjectResult = null;
		try {
			long startTime=System.currentTimeMillis();
//			String getParams = Util.aesDecrypt(params);
//			Map<String, Object> jsonMap = CommonClass.getMapByJson(getParams);
//			String cusId = jsonMap.get("cusId")+"".trim();//登录id
//			String defCarId = jsonMap.get("defCarId")+"".trim();//登录用户的默认车辆id
			Integer cusId = 20001;//登录id
			float lng = 118.02F;
			float lat = 20.20F;
			String cusPhoto = "12345456sss";
			String defCarId = "430".trim();//登录用户的默认车辆id
			if(StringUtil.isnotObjectsNotEmpty(cusId,defCarId)){
				List<TestInsert> testInserts = new ArrayList<TestInsert>();
				TestInsert testInsert = null;
				for(int i=0;i<1000;i++){
					testInsert = new TestInsert();
					testInsert.setCusId(cusId+i);
					testInsert.setLng(lng+i);
					testInsert.setLat(lat+i);
					testInsert.setCreatetime(new Date());
					testInsert.setCusPhoto(cusPhoto+i);
					testInserts.add(testInsert);
//					carInfoService.saveOne(testInsert);
				}
				carInfoService.testSaveBatch(testInserts, 50);
				appObjectResult = new AppObjectResult(SystemInfo.QUERY_SUCCESS.getCode(), SystemInfo.QUERY_SUCCESS.getMsg());
			}else{
				appObjectResult = new AppObjectResult(SystemInfo.ILLEGAL_PARAMETER.getCode(), SystemInfo.ILLEGAL_PARAMETER.getMsg(),"请登录!");
			}
			sendDataResponse(response,JSONUtil.getJSONString(appObjectResult));
			long endTime=System.currentTimeMillis();
			float excTime=(float)(endTime-startTime)/1000;
			System.out.println("执行时间："+excTime+"s");
		} catch (Exception e) {
			logger.error("附近的车控制类_查询我的车信息:"+e.getMessage());
			e.printStackTrace();
		}
	}
	
}
