package com.ennuova.app.controller;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ennuova.app.service.CarInfoService;
import com.ennuova.app.service.CarRemindSetService;
import com.ennuova.common.CommonClass;
import com.ennuova.controller.GeneralControl;
import com.ennuova.entity.CarRemindSet;
import com.ennuova.entity.PubCarinfo;
import com.ennuova.util.Util;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

import net.sf.json.JSONObject;

/**
 * 
* @Title: CarRemindSetController.java 
* @Package com.ennuova.app.controller 
* @Description: 车务管理配置(描述) 
* @author felix
* @date 2016年4月19日
* @version V1.0
 */
@Controller
@RequestMapping("/carRemindSet.do")
@Api(value="",description="车务管理")
public class CarRemindSetController extends GeneralControl{
	
	private static Logger logger = Logger.getLogger(CarRemindSetController.class);

	@Resource
	private CarRemindSetService remindSetService;
	
	@Resource
	private CarInfoService carInfoService;
	
	/**
	 * 
	* @Title: CarRemindSetController.java 
	* @Package com.ennuova.app.controller 
	* @Description: 更新/添加车务提醒配置(描述) 
	* @author felix
	* @date 2016年4月20日
	* @version V1.0
	 */
	@RequestMapping(params="method=carremindset",method=RequestMethod.POST)
	@ApiOperation(value = "更新/添加车务提醒配置(描述)",notes="{result:返回结果值(值为0失败,为1成功)}")
	public void carRemindSet(@ApiParam(value="请输入json{customerId:用户id,carinfoId:车辆id,maintainMileage:保养里程(公里),maintainDate:保养时间,insuranceDate:保险到期,inspectorDate:年检到期,warrantyDate:保修到期}AES加密后的结果")String params,HttpServletRequest req,HttpServletResponse response){
		try{
		String params_log = Util.aesDecrypt(params);
		Map<String, Object> jsonMap=CommonClass.getMapByJson(params_log);
		long customerId = Long.valueOf(jsonMap.get("customerId").toString());//用户id
		long carinfoId = Long.valueOf(jsonMap.get("carinfoId").toString());//车辆id
		float maintainMileage = Float.valueOf(jsonMap.get("maintainMileage").toString());//保养里程(公里)
		int maintainDate = Integer.parseInt(jsonMap.get("maintainDate").toString());//保养时间
		int insuranceDate = Integer.parseInt(jsonMap.get("insuranceDate").toString());//保险到期
		int inspectorDate = Integer.parseInt(jsonMap.get("inspectorDate").toString());//年检到期
		int warrantyDate = Integer.parseInt(jsonMap.get("warrantyDate").toString());//保修到期
		CarRemindSet remindSet = new CarRemindSet();
		remindSet.setCustomerId(customerId);
		remindSet.setCarinfoId(carinfoId);
		remindSet.setMaintainMileage(maintainMileage);
		remindSet.setMaintainDate(maintainDate);
		remindSet.setInsuranceDate(insuranceDate);
		remindSet.setInspectorDate(inspectorDate);
		remindSet.setWarrantyDate(warrantyDate);
		int result = 0;
		CarRemindSet remindSet2 = remindSetService.getCarRemind(carinfoId);
		if(remindSet2==null){
			result = remindSetService.saveCarRemind(remindSet);
		}else {
			remindSet.setCarRemindSetId(remindSet2.getCarRemindSetId());
			result = remindSetService.updateCarRemind(remindSet);
		}
		JSONObject json = new JSONObject();
		json.put("result", result);
		sendDataResponse(response, json.toString());
		}catch(Exception e){
			JSONObject json = new JSONObject();
			json.put("result", "输入数据错误!");
			sendDataResponse(response, json.toString());
			logger.error("车务管理_更新/添加车务提醒配置(描述):"+e.getMessage());
		}
	}
	
	/**
	 * 
	* @Title: CarRemindSetController.java 
	* @Package com.ennuova.app.controller 
	* @Description: 获取车辆车务提醒配置(描述) 
	* @author felix
	* @date 2016年4月20日
	* @version V1.0
	 */
	@RequestMapping(params="method=getremindset",method=RequestMethod.POST)
	@ApiOperation(value = "获取车辆车务提醒配置",notes="{remindSet:{carRemindSetId:车务配置id,customerId:用户id,carinfoId:车辆id,maintainMileage:保养里程(公里),maintainDate:保养时间,insuranceDate:保险到期,inspectorDate:年检到期,warrantyDate:保修到期}}")
	public void getRemindSet(@ApiParam(value="请输入json{carId:车辆id}AES加密后的结果")String params,HttpServletRequest req,HttpServletResponse response){
		String paramsLog = Util.aesDecrypt(params);
		Map<String, Object> jsonMap = CommonClass.getMapByJson(paramsLog);
		long carId = Long.valueOf(jsonMap.get("carId").toString());//车辆Id
		CarRemindSet remindSet = remindSetService.getCarRemind(carId);
		JSONObject json = new JSONObject();
		json.put("remindSet", remindSet);
		sendDataResponse(response, json.toString());
	}
	
	/**
	 * 
	* @Title: CarInfoController.java 
	* @Package com.ennuova.app.controller 
	* @Description: 售后首页获取车辆信息(描述) 
	* @author felix
	* @date 2016年4月21日
	* @version V1.0
	 */
	@RequestMapping(params="method=aftersaleindex",method=RequestMethod.POST)
	@ApiOperation(value = "售后首页获取车辆信息",notes="{{'line':(车系){'id':车系id,'sContent':'车系广告语','mailboxCapacity':'邮箱容量','sPicture':'车系图片','fname':'车系名称','fnumber':'车系编码'},'carInfo':(车辆信息){'njianDay':年检到期天数,'finsexpire':保险到期日期,'vname':'车型名称','fenginenum':'发动机号','id':车辆id,'fcarnum':'车牌号','fdefault':是否默认,'byangDay':保养到期天数,'finscompany':保险公司,'boxId':盒子id,'fguaexpire':保修日期,'bxiuDay':保修到期天数,'fcarcode':'车架号','byangMileage':保养里程数,'lid':车系id,'bxianDay':保险到期天数,'fyearinsexpire':年检到期时间,'fxlh':'对应盒子序列号'}}}")
	public void afterSaleIndex(@ApiParam(value="请输入json{cusId:用户id}AES加密后的结果")String params,HttpServletRequest req,HttpServletResponse response){
		String paramsLog = Util.aesDecrypt(params);
		Map<String, Object> jsonMap = CommonClass.getMapByJson(paramsLog);
		long cusId = Long.valueOf(jsonMap.get("cusId").toString());
		PubCarinfo carInfo = carInfoService.afterSaleCar(cusId);
		JSONObject json = new JSONObject();
		if(carInfo.getLid() != null && carInfo.getLid() != 0){
			json.put("line", carInfoService.getPubLine(carInfo.getLid()));
		}
		json.put("carInfo", carInfo);
		System.out.println(json.toString());
		sendDataResponse(response, json.toString());
	}
	
}
