package com.ennuova.app.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ennuova.app.config.AppObjectResult;
import com.ennuova.app.config.SystemInfo;
import com.ennuova.app.service.CarInfoService;
import com.ennuova.app.service.CnSbljinfoService;
import com.ennuova.common.CommonClass;
import com.ennuova.controller.GeneralControl;
import com.ennuova.entity.CarLocation;
import com.ennuova.entity.CnSbljinfo;
import com.ennuova.entity.OwNewsct;
import com.ennuova.entity.PubCarinfo;
import com.ennuova.entity.PubLine;
import com.ennuova.entity.PubVehiclemodel;
import com.ennuova.util.JSONUtil;
import com.ennuova.util.JsonUtils;
import com.ennuova.util.StringUtil;
import com.ennuova.util.Util;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller("myCarController")
@RequestMapping("/myCar.do")
public class CarInfoController extends GeneralControl{
	
	Logger logger = Logger.getLogger(CarInfoController.class);

	@Resource
	private CarInfoService carInfoService;
	@Resource
	private CnSbljinfoService cnSbljinfoService;

	/**
	 * 删除用户车辆
	 * @param params
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(params="method=delCusCar")
	public String delCusCar(String params,HttpServletResponse response) throws IOException {
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setContentType("text/json;charset=utf-8");
		PrintWriter out = response.getWriter();
		String params_log = "";
		params_log = Util.aesDecrypt(params);
		Map<String, Object> jsonMap=CommonClass.getMapByJson(params_log);
		long carId = Long.parseLong(jsonMap.get("carId").toString());
		boolean bool = carInfoService.delCusCar(carId);
		out.write(bool+"");
		out.flush();
		out.close();
		return null;
	}

/*
	*//**
	 * 根据车辆ID获取当前的车辆
	 * @param params
	 * @param response
	 * @return
	 * @throws IOException
	 *//*
	@RequestMapping(params="method=getMyCar")
	public String getMyCar(String params,HttpServletResponse response) throws IOException {	
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setContentType("text/json;charset=utf-8");
		PrintWriter out = response.getWriter();
		System.out.println("myCar");    
		String params_log = "";
		params_log = Util.aesDecrypt(params);
		System.out.println(params_log);
		Map<String, Object> jsonMap=CommonClass.getMapByJson(params_log);
		long carId = Long.parseLong(jsonMap.get("carId").toString());

		System.out.println(carId+"---------------------------");
		PubCarinfo v=carInfoService.getCarInfo(carId);
		System.out.println(JSONObject.fromObject(v).toString());
		out.write(JSONObject.fromObject(v).toString());


		out.flush();
		out.close();  
		return null;
	}*/

	/**
	 * 获取车辆品牌
	 * @param params
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(params="method=getCarBrand")
	public String getCarBrand(String params,HttpServletResponse response) throws IOException {	
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setContentType("text/json;charset=utf-8");
		PrintWriter out = response.getWriter();
		@SuppressWarnings("rawtypes")
		List v =new ArrayList();
		v=carInfoService.getCarBrand();
		out.write(JSONArray.fromObject(v).toString());
		out.flush();
		out.close();  
		return null;
	}

	//	@RequestMapping(params="method=queryCarInfoById")
	//	public String queryCarInfoById(String params,HttpServletResponse response) throws IOException{
	//		response.setHeader("Access-Control-Allow-Origin", "*");
	//		response.setContentType("text/json;charset=utf-8");
	//		PrintWriter out = response.getWriter();
	//		String content = "";
	//		content = Util.aesDecrypt(params);
	//		System.out.println(content);
	//		Map<String, Object> jsonMap=CommonClass.getMapByJson(content);
	//		long cusId= Long.valueOf(jsonMap.get("cusId").toString());
	//		PubCarinfo v =new PubCarinfo();
	//		v=carInfoService.getCarInfo(cusId);
	//		System.out.println(JSONArray.fromObject(v).toString());
	//        out.write(JSONArray.fromObject(v).toString());      
	//		out.flush();
	//		out.close();  
	//		return null;
	//		
	//	}

	/**
	 * 获取车辆信息(根据品牌获取车系车型)
	 * @param params
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(params="method=getCarInfo")
	public String getCarInfo(String params,HttpServletResponse response) throws IOException {	
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setContentType("text/json;charset=utf-8");
		PrintWriter out = response.getWriter();

		String content = "";

		content = Util.aesDecrypt(params);
		Map<String, Object> jsonMap=CommonClass.getMapByJson(content);
		String brandId= jsonMap.get("brandId").toString();

		@SuppressWarnings("rawtypes")
		List v =new ArrayList();
		v=carInfoService.getCarInfo(brandId);
		out.write(JSONArray.fromObject(v).toString());


		out.flush();
		out.close();  
		return null;
	}


	/**
	 * 获取我的车辆信息
	 * @param params
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(params="method=getMyCars")
	public String getMyCars(String params,HttpServletResponse response) throws IOException {	
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setContentType("text/json;charset=utf-8");
		PrintWriter out = response.getWriter();
		String content = "";
		content = Util.aesDecrypt(params);
//		System.out.println(content);
		Map<String, Object> jsonMap=CommonClass.getMapByJson(content);
		Long userId= Long.valueOf(jsonMap.get("userId").toString());
		//System.out.println(userId+"dofast");
		@SuppressWarnings("rawtypes")
		List v =new ArrayList();
		v=carInfoService.getAllCarInfoByUser(userId);
		if( v.size()> 0){
			out.write(JSONArray.fromObject(v).toString());
		}else{
			out.write("");
		}
//		System.out.println(JSONArray.fromObject(v).toString());
		out.flush();
		out.close();  
		return null;
	}
	/**
	 * 添加客户新车
	 * @param params
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(params="method=addNewCar")
	public String addNewCar(String params,HttpServletResponse response) throws IOException {	
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setContentType("text/json;charset=utf-8");
		PrintWriter out = response.getWriter();
		String content = "";
		content = Util.aesDecrypt(params);
//		System.out.println(content);
		Map<String, Object> jsonMap=CommonClass.getMapByJson(content);
		String userId= jsonMap.get("userId").toString();
		String brandId= jsonMap.get("brandId").toString();
		String chexiId= jsonMap.get("chexiId").toString();
		String chexingId= jsonMap.get("chexingId").toString();
		String flicenseno= jsonMap.get("flicenseno").toString();
		String carCode = jsonMap.get("fcarCode").toString();
		String result=new String();
		result=carInfoService.addCarInfo(userId, brandId, chexiId, chexingId,flicenseno,carCode);
		Map<String, String> ob = new HashMap<String, String>();
		ob.put("succ", result);	
//		System.out.println(JSONArray.fromObject(ob).toString());
		out.write(JSONArray.fromObject(ob).toString());
		out.flush();
		out.close();  
		return null;
	}

	/**
	 * 根据车辆ID修改车辆信息
	 * @param params
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(params="method=updateCarInfo",method=RequestMethod.POST)
	@ApiOperation(value="根据车辆Id,标签，内容修改车辆对应信息",notes="")
	public void updateCarInfo(@ApiParam(value="请输入{carId:车辆Id,label:修改项[fchassisno:车架号;flicenseno:车牌号;fengineno:发动机号;maintainTime:保养时间;maintainMileage:保养里程;fyearinsexpire:年检到期时间;finsexpire:保险到期;fguaexpire:保修到期],content:修改内容}AES加密后的结果")String params,HttpServletResponse response) {
		String str = Util.aesDecrypt(params);
		Map<String,Object> jsonMap=CommonClass.getMapByJson(str);
		String carId = jsonMap.get("carId")+"".trim(); //车辆id
		String  label= jsonMap.get("label")+"".trim();//修改的标签
		String  content=jsonMap.get("content")+"".trim();//修改的内容
//		String carId = "60451";
//		String  label= "fchassisno";
//		String  content="2018-08-08-88888888";
		AppObjectResult appObjectResult = null;
		try {
			if(StringUtil.isNotEmpty(carId)){
				int result = carInfoService.updateCarInfo(carId, label, content);
				if(result > 0){
					appObjectResult = new AppObjectResult(SystemInfo.UPDATE_SUCCESS.getCode(), SystemInfo.UPDATE_SUCCESS.getMsg());		
				}else{
					appObjectResult = new AppObjectResult(SystemInfo.UPDATE_FAIL.getCode(), SystemInfo.UPDATE_FAIL.getMsg());		
				}
			}else{
				appObjectResult = new AppObjectResult(SystemInfo.ILLEGAL_PARAMETER.getCode(), SystemInfo.ILLEGAL_PARAMETER.getMsg());
			}
			sendDataResponse(response,JSONUtil.getJSONString(appObjectResult));
		} catch (Exception e) {
			e.printStackTrace();
			sendDataResponse(response,SystemInfo.UPDATE_FAIL.getMsg());
		}
	}
	/**
	 * 修改车辆
	 * @param params
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(params="method=modifyCar")
	public String modifyCar(String params,HttpServletResponse response) throws IOException {	
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setContentType("text/json;charset=utf-8");
		PrintWriter out = response.getWriter();
		String content = "";
		content = Util.aesDecrypt(params);
//		System.out.println(content);
		Map<String, Object> jsonMap=CommonClass.getMapByJson(content);
		String carId= jsonMap.get("carId").toString();
		String brandId= jsonMap.get("brandId").toString();
		String chexiId= jsonMap.get("chexiId").toString();
		String chexingId= jsonMap.get("chexingId").toString();
		String result=new String();
		result=carInfoService.modifyCarInfo(carId, brandId, chexiId, chexingId);
		Map<String, String> ob = new HashMap<String, String>();
		ob.put("succ", result);	
//		System.out.println(JSONArray.fromObject(ob).toString());
		out.write(JSONArray.fromObject(ob).toString()); 
		out.flush();
		out.close();  
		return null;
	}

	/**
	 * 根据车系ID获取车型
	 * @param params
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(params="method=getModel")
	public String getModel(String params,HttpServletResponse response) throws IOException {	
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setContentType("text/json;charset=utf-8");
		PrintWriter out = response.getWriter();
//		System.out.println("getModel");
		List<PubVehiclemodel> m =new ArrayList<PubVehiclemodel>();
		String content = Util.aesDecrypt(params);
//		System.out.println(content);	
		Map<String, Object> jsonMap=CommonClass.getMapByJson(content);
		String lid= jsonMap.get("lid").toString();
//		System.out.println(lid);
		m=carInfoService.getModel(Long.valueOf(lid));
//		System.out.println(JSONArray.fromObject(m).toString());
		out.write(JSONArray.fromObject(m).toString());
		out.flush();
		out.close();  
		return null;
	}

	/**
	 * 获取我的爱车列表信息
	 * @param params
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(params="method=getCarList")
	public String getCarList(String params,HttpServletResponse response) throws IOException {	
		long startTime=System.currentTimeMillis();
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setContentType("text/json;charset=utf-8");
		PrintWriter out = response.getWriter();
		String content = Util.aesDecrypt(params);
		Map<String, Object> jsonMap=CommonClass.getMapByJson(content);
		String userId= jsonMap.get("userId")+"";
		if(StringUtil.isNotEmpty(userId)){
			List<PubCarinfo> list =carInfoService.getAllCarInfoByUser(Long.valueOf(userId));
			if(StringUtil.isNotEmpty(list) && list.size()> 0){
				out.write(JSONArray.fromObject(list).toString());
			}else{
				out.write("");
			}			
		}else{
			out.write("");
		}
		out.flush();
		out.close();  
		long endTime=System.currentTimeMillis();
		float excTime=(float)(endTime-startTime)/1000;
//		System.out.println("执行时间："+excTime+"s");
		return null;
	}

	//根据用户Id查询默认车辆
	@RequestMapping(params="method=getDefaultCar")
	public String getDefaultCar(String params,HttpServletResponse response) throws IOException {	
		String json = "";
		JSONObject jsonObject = new JSONObject();
		String content = Util.aesDecrypt(params);
		if (content!=null) {
			Map<String, Object> jMap = CommonClass.getMapByJson(content);
			Long userId = Long.valueOf(jMap.get("userId").toString());
			String cityName = jMap.get("cityName").toString();
			PubCarinfo pubCarinfo = carInfoService.getDefaultCarId(userId);
			Map<String,Object> weaTherData = carInfoService.getWeatherInfo(cityName);
			jsonObject.put("weather", weaTherData);
			if(pubCarinfo!=null){
				PubCarinfo pubCarinfo1 = carInfoService.getCarInfo(pubCarinfo.getId());
				PubLine pubLine = carInfoService.getPubLine(pubCarinfo1.getLid());
				pubCarinfo1.setFxlh(pubCarinfo.getFxlh());
				jsonObject.put("success", true);
				jsonObject.put("pubCarinfo", pubCarinfo1);
				jsonObject.put("pubLine", pubLine);
			}else{
				jsonObject.put("success", false);
			}

			json = jsonObject.toString();
		}else {
			jsonObject.put("success", false);
			json = jsonObject.toString();
		}
		try {
			response.setHeader("Content-type", "application/json;charset=UTF-8");
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/json;charset=UTF-8");
			response.setHeader("Access-Control-Allow-Origin", "*");
			PrintWriter pw = response.getWriter();
			pw.write(json);
			pw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	//根据用户Id查询用户是否绑定了车辆和盒子
	@RequestMapping(params="method=getUserInfo")
	public String getUserInfo(String params,HttpServletResponse response) throws IOException {	
		String json = "";
		JSONObject jsonObject = new JSONObject();
		String content = Util.aesDecrypt(params);
		if (content!=null) {
			Map<String, Object> jMap = CommonClass.getMapByJson(content);
			Long userId = Long.valueOf(jMap.get("userId").toString());
			List<PubCarinfo> list =new ArrayList<PubCarinfo>();
			list=carInfoService.getAllCarInfoByUser(Long.valueOf(userId));
			if(list.size()>0){
				jsonObject.put("success", true);
				jsonObject.put("hasCar", true);
				jsonObject.put("hasBox", false);
				jsonObject.put("userId", userId);
				for (PubCarinfo pubCarinfo : list) {
					if(pubCarinfo.getFdefault()==1){
						jsonObject.put("hasBox", true);
					}
				}
			}else {
				jsonObject.put("success", true);
				jsonObject.put("hasCar", false);
				jsonObject.put("hasBox", false);
				jsonObject.put("userId", userId);
			}

			json = jsonObject.toString();
		}else {
			jsonObject.put("success", false);
			json = jsonObject.toString();
		}
		try {
			response.setHeader("Content-type", "application/json;charset=UTF-8");
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/json;charset=UTF-8");
			response.setHeader("Access-Control-Allow-Origin", "*");
			PrintWriter pw = response.getWriter();
			pw.write(json);
			pw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	//盒子激活
	@RequestMapping(params="method=activationBox")
	public String activationBox(String params,HttpServletResponse response) throws IOException {	
		String json	 = "";
		JSONObject jsonObject = new JSONObject();
		String content = Util.aesDecrypt(params);
		Map<String, Object> jMap = CommonClass.getMapByJson(content);
		Long fclid = Long.valueOf(jMap.get("fclid").toString());
		Map<String, Object> map = new HashMap<String, Object>();
		map = carInfoService.getCarBoxInfo(fclid);
		//System.out.println(map.toString());

		jsonObject.put("success", map.get("success"));
		json = jsonObject.toString();			
		try {
			response.setHeader("Content-type", "application/json;charset=UTF-8");
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/json;charset=UTF-8");
			response.setHeader("Access-Control-Allow-Origin", "*");
			PrintWriter pw = response.getWriter();
			pw.write(json);
			pw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	//获取车架号
	@RequestMapping(params="method=setCJH")
	public String setCJH(String params,HttpServletResponse response) throws IOException {	
		String json = "";
		JSONObject jsonObject = new JSONObject();
		String content = Util.aesDecrypt(params);
		Map<String, Object> jMap = CommonClass.getMapByJson(content);
		Long fclid = Long.valueOf(jMap.get("fclid").toString());
		Map<String, Object> map = new HashMap<String, Object>();
		map = carInfoService.getCarJHInfo(fclid);
		jsonObject.put("carJHInfo", map);
		json = jsonObject.toString();			
		try {
			response.setHeader("Content-type", "application/json;charset=UTF-8");
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/json;charset=UTF-8");
			response.setHeader("Access-Control-Allow-Origin", "*");
			PrintWriter pw = response.getWriter();
			pw.write(json);
			pw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(params="method=updateCarCode")
	public String updateCarCode(String params,HttpServletResponse response) throws IOException {	
		String json = "";
		JSONObject jsonObject = new JSONObject();
		String content = Util.aesDecrypt(params);
		Map<String, Object> jMap = CommonClass.getMapByJson(content);
		Long fclid = Long.valueOf(jMap.get("fclid").toString());
		String carCode = jMap.get("carCode").toString();
		boolean success = carInfoService.updateCarCode(fclid, carCode);
		jsonObject.put("success", success);
		json = jsonObject.toString();			
		try {
			response.setHeader("Content-type", "application/json;charset=UTF-8");
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/json;charset=UTF-8");
			response.setHeader("Access-Control-Allow-Origin", "*");
			PrintWriter pw = response.getWriter();
			pw.write(json);
			pw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
/*	*//**
	 * 根据盒子序列号查询盒子iccid
	 * @param params
	 * @param response
	 * @return
	 * @throws IOException
	 *//*
	@RequestMapping(params="method=getSbInfo")
	public String getSbInfo(String params,HttpServletResponse response) throws IOException {	
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setContentType("text/json;charset=utf-8");
		PrintWriter out = response.getWriter();
		String params_log = "";
		params_log = Util.aesDecrypt(params);
		Map<String, Object> jsonMap=CommonClass.getMapByJson(params_log);
		System.out.println(jsonMap.toString());
		String fsbxlh = jsonMap.get("fsbxlh").toString();
		CnSbljinfo cnSbljinfo=cnSbljinfoService.getSbInfo(fsbxlh);
		out.write(JSONObject.fromObject(cnSbljinfo.getFsim()).toString());


		out.flush();
		out.close();  
		return null;
	}*/
	/**
	 * 
	* @Title: CarInfoController.java 
	* @Package com.ennuova.app.controller 
	* @Description: 根据车辆id获取对应车辆信息(描述) 
	* @author felix
	* @date 2016年5月11日
	* @version V1.0
	 */
	@RequestMapping(params="method=getMyCar",method=RequestMethod.POST)
	@ApiOperation(value="根据车辆id获取对应车辆信息",notes="{carinfo:车辆信息}")
	public void getMyCar(@ApiParam(value="请输入{carId:车辆id}AES加密后的结果")String params,HttpServletResponse response) {	
		String params_log = Util.aesDecrypt(params);
		Map<String, Object> jsonMap=CommonClass.getMapByJson(params_log);
		String carId = jsonMap.get("carId")+"".trim();
//		String carId = "60451".trim();
//		System.out.println(carId);
		AppObjectResult appObjectResult = null;
		try {
			if(StringUtil.isNotEmpty(carId)){
				Map<String, Object> resultMap = carInfoService.findMyCarByCarId(carId);
			    appObjectResult = new AppObjectResult(SystemInfo.QUERY_SUCCESS.getCode(), SystemInfo.QUERY_SUCCESS.getMsg(), resultMap);		
			}else{
				appObjectResult = new AppObjectResult(SystemInfo.ILLEGAL_PARAMETER.getCode(), SystemInfo.ILLEGAL_PARAMETER.getMsg());
			}
			sendDataResponse(response,JSONUtil.getJSONString(appObjectResult));
		} catch (Exception e) {
			e.printStackTrace();
			sendDataResponse(response,SystemInfo.QUERY_ERROR.getMsg());
		}
	}
	@RequestMapping(params="method=getSbInfo")
	@ApiOperation(value = "分享页面",notes="{id:新闻Id}")
	public ModelAndView shareNews(String params,String id,Model model,HttpServletResponse response,HttpServletRequest request) throws IOException, ServletException {
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setContentType("text/json;charset=utf-8");
		PrintWriter out = response.getWriter();
		CnSbljinfo cnSbljinfo=cnSbljinfoService.getSbInfo(id);
		out.write(cnSbljinfo.getFsim().toString());
		out.flush();
		out.close();  
		return null;
	}
}
