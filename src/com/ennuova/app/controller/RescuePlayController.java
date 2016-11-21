package com.ennuova.app.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ennuova.app.config.AppObjectResult;
import com.ennuova.app.config.SystemInfo;
import com.ennuova.app.service.CarInfoService;
import com.ennuova.app.service.PubBdstoreService;
import com.ennuova.app.service.RescuePlayService;
import com.ennuova.app.service.ReserveRescueService;
import com.ennuova.common.CommonClass;
import com.ennuova.controller.GeneralControl;
import com.ennuova.entity.RescueItem;
import com.ennuova.entity.RescuePlay;
import com.ennuova.entity.ReserveRescue;
import com.ennuova.util.JSONUtil;
import com.ennuova.util.StringUtil;
import com.ennuova.util.Util;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

import net.sf.json.JSONObject;

@Controller
@Api(value="",description="道路救援")
@RequestMapping("/rescueplay")
public class RescuePlayController extends GeneralControl{

	@Resource
	private RescuePlayService rescuePlayService;
	
	@Resource
	private ReserveRescueService reserveRescueService;
	
	/**
	 * 客户绑定商店的服务
	 */
	@Resource
	private PubBdstoreService pubBdstoreService;
	
	/**
	 * 车辆信息的服务
	 */
	@Resource
	private CarInfoService carInfoService;
	
	/**
	 * 
	* @Title: RescuePlayController.java 
	* @Package com.ennuova.app.controller 
	* @Description: 获取救援方案(描述) 
	* @author felix
	* @date 2016年4月22日
	* @version V1.0
	 */
	@RequestMapping(params="method=getrescueplay",method=RequestMethod.POST)
	@ApiOperation(value="获取救援方案",notes="{rescuePlayId:救援方案ID,rescuePlayName:名称,rescuePlayAliases:别名,rescuePlayOrder:排序号,rescuePlayRemark:备注,rescueItems:方案项集合}")
	public void getRescuePlay(@ApiParam(value="请输入json{'无需参数'}AES加密后的结果")String params,HttpServletRequest req,HttpServletResponse response){
		List<RescuePlay> playList = rescuePlayService.queryRescuePlay();
		JSONObject json = new JSONObject();
		json.put("playList", playList);
		sendDataResponse(response, json.toString());
	}
	
	/**
	 * 
	* @Title: RescuePlayController.java 
	* @Package com.ennuova.app.controller 
	* @Description: 预约道路救援(描述) 
	* @author felix
	* @date 2016年4月26日
	* @version V1.0
	 */
	@RequestMapping(params="method=reserverescue",method=RequestMethod.POST)
	@ApiOperation(value="预约道路救援",notes="{result:返回结果值(值为0失败,为1成功)}")
	public void reserveRescue(@ApiParam(value="请输入json{carinfoId:车辆id,customerId:用户id,fcarnum:车牌号,latitude:纬度,longitude:经度,rescuePlayId:救援方案,rescueItems:救援项目(多个id用拼接的,id间用,隔开)}AES加密后的结果")String params,HttpServletRequest req,HttpServletResponse response){
		String paramsLog = Util.aesDecrypt(params);
		Map<String,Object> jsonMap = CommonClass.getMapByJson(paramsLog);
		long carinfoId = Long.valueOf(jsonMap.get("carinfoId").toString());//车辆id
		long customerId = Long.valueOf(jsonMap.get("customerId").toString());//用户id
		String fcarnum = jsonMap.get("fcarnum").toString();//车牌号
		double latitude = Double.valueOf(jsonMap.get("latitude").toString());//纬度
		double longitude = Double.valueOf(jsonMap.get("longitude").toString());//经度
		String address = jsonMap.get("addressInfo").toString();//纬度
		long rescuePlayId = Long.valueOf(jsonMap.get("rescuePlayId").toString());//救援方案id
		String rescueItems = jsonMap.get("rescueItems").toString();//救援项目id
		ReserveRescue reserveRescue = new ReserveRescue();
		if(StringUtil.isNotEmpty(carinfoId)){//获取客户车辆的车型
			reserveRescue.setCarinfoId(carinfoId);
			Map<String, Object> carInfoMap = carInfoService.findModelById(carinfoId);
			if(StringUtil.isNotEmpty(carInfoMap)){
				String modelName = carInfoMap.get("modelName")+"";
				reserveRescue.setCarName(modelName);
			}
		}
		if(StringUtil.isNotEmpty(customerId)){//获取客户绑定的门店id
			reserveRescue.setCustomerId(customerId);
			Map<String, Object> bdStoreMap = pubBdstoreService.findByCusId(customerId);
			if(StringUtil.isNotEmpty(bdStoreMap)){
				String storeId = bdStoreMap.get("FSTORE")+"";
				reserveRescue.setStoreId(storeId);
			}
		}
		reserveRescue.setFcarnum(fcarnum);
		reserveRescue.setLatitude(latitude);
		reserveRescue.setLongitude(longitude);
		reserveRescue.setAddress(address);
		reserveRescue.setReserveState("0");
		reserveRescue.setCreateDate(new Date());
		RescuePlay play = new RescuePlay();
		play.setRescuePlayId(rescuePlayId);
		reserveRescue.setRescuePlay(play);
		List<RescueItem> items = new ArrayList<RescueItem>();
		if(rescueItems != null && !rescueItems.equals("")){
			String[] splitItems = rescueItems.split(",");
			for(int i = 0 ; i < splitItems.length ; i++){
				//RescueItem rescueItem = new RescueItem();
				//rescueItem.setRescueItemId(Long.valueOf(splitItems[i]));
				items.add(reserveRescueService.getById(Long.valueOf(splitItems[i])));
			}
		}
		reserveRescue.setRescueItems(items);
		int result = 0;
		result = reserveRescueService.saveReserveRescue(reserveRescue);
		JSONObject json = new JSONObject();
		json.put("result", result);
		sendDataResponse(response, json.toString());
	}
	/**
	 * 救援任务的历史的详情
	 * @author sududa
	 * @date 2016年7月19日
	 * @param params
	 * @param req
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(params="method=detail",method=RequestMethod.POST)
	@ApiOperation(value="道路救援预约历史记录详情",notes="{id:记录id}")
	public void detail(@ApiParam(value="请输入json{id:记录id}AES加密后的结果")String params,HttpServletRequest req,HttpServletResponse response) throws Exception{
		String paramsLog = Util.aesDecrypt(params);
		Map<String,Object> jsonMap = CommonClass.getMapByJson(paramsLog);
		String id = jsonMap.get("id")+"".trim();
//		String id = "17250";
		AppObjectResult appObjectResult = null;
		if(StringUtil.isNotEmpty(id)){
			Map<String, Object> reserveMap = rescuePlayService.findDetail(id);
			//findRescueItems(reserveMap);//获取救援任务的救援项目
			appObjectResult = new AppObjectResult(SystemInfo.QUERY_SUCCESS.getCode(), SystemInfo.QUERY_SUCCESS.getMsg(), reserveMap);
		}else{
			appObjectResult = new AppObjectResult(SystemInfo.ILLEGAL_PARAMETER.getCode(), SystemInfo.ILLEGAL_PARAMETER.getMsg());
		}
		sendDataResponse(response,JSONUtil.getJSONString(appObjectResult));
	}
	
	/**
	 * 
	* @Title: RescuePlayController.java 
	* @Package com.ennuova.app.controller 
	* @Description: 获取道路救援详情(描述) 
	* @author felix
	* @date 2016年4月26日
	* @version V1.0
	 */
	@RequestMapping(params="method=getreserverescue",method=RequestMethod.POST)
	@ApiOperation(value="获取道路救援详情",notes="{reserveRescueId:预约救援ID,rescuePlayId:救援方案ID,rescuePlay:救援方案对象,reserveState:预约状态,customerId:用户ID,carinfoId:车辆ID,longitude:经度,latitude:纬度,reserveRescueContent:内容,reserveRescueRemark:备注,fcarnum:车牌号}")
	public void getReserveRescue(@ApiParam(value="请输入json{reserveRescueId:道路预约救援id}AES加密后的结果")String params,HttpServletRequest req,HttpServletResponse response){
		String paramsLog = Util.aesDecrypt(params);
		Map<String,Object> jsonMap = CommonClass.getMapByJson(paramsLog);
		String getId = jsonMap.get("reserveRescueId")+"";
		JSONObject json = new JSONObject();
		if(StringUtil.isNotEmpty(getId)){
			long reserveRescueId = Long.valueOf(getId);
			ReserveRescue reservRescue = reserveRescueService.getReserveRescue(reserveRescueId);
			json.put("reservRescue", reservRescue);
			sendDataResponse(response, json.toString());
		}else{
			json.put("eoore", "error");
			sendDataResponse(response, json.toString());
		}
	}
	
}
