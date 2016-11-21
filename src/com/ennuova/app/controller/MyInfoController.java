package com.ennuova.app.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
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
import com.ennuova.app.service.OwCusalarmsService;
import com.ennuova.app.service.OwWyfkService;
import com.ennuova.app.service.PubBdstoreService;
import com.ennuova.app.service.PubCustomerService;
import com.ennuova.app.service.PubStoreService;
import com.ennuova.common.CommonClass;
import com.ennuova.controller.GeneralControl;
import com.ennuova.entity.CarLocation;
import com.ennuova.entity.OwCusalarms;
import com.ennuova.entity.PubCustomer;
import com.ennuova.util.JSONUtil;
import com.ennuova.util.JsonUtils;
import com.ennuova.util.StringUtil;
import com.ennuova.util.Util;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

import net.sf.json.JSONObject;

@Controller("myInfoController")
@RequestMapping("myInfo.do")
@Api(value="", description="我的信息控制类")
public class MyInfoController extends GeneralControl{
	
	Logger logger = Logger.getLogger(MyInfoController.class);

	/**
	 * 客户的服务
	 */
	@Resource
	private PubCustomerService pubCustomerService;
	
	@Resource
	private OwWyfkService owWyfkService;
	
	@Resource
	private OwCusalarmsService owCusalarmsService;
	
	@Resource
	private PubBdstoreService pubBdstoreService;
	
	@Resource
	private PubStoreService pubStoreService;
	
	/**
	 * 修改密码
	 * @param params
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params = "method=changePwd")
	public String changePwd(String params,HttpServletRequest request,HttpServletResponse response) throws Exception{
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setContentType("text/json;charset=utf-8");
		String content = Util.aesDecrypt(params);
		Map<String,Object> jMap = CommonClass.getMapByJson(content);
		long cusId = Long.valueOf(jMap.get("cusId").toString());
		String oldPwd = jMap.get("oldPwd").toString();
		String newPwd = jMap.get("newPwd").toString();
		int result = pubCustomerService.changePwd(cusId, oldPwd, newPwd);
		PrintWriter out = response.getWriter();
		JSONObject json = new JSONObject();
		json.put("result", result);
		//System.out.println(json.toString());
		out.write(json.toString());
		out.flush();
		out.close();
		return null;
	}
	/**
	 * 获取个人信息
	 * @param params
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params = "method=getMyMainInfo")
	public String getMyMainInfo(String params,HttpServletRequest request,HttpServletResponse response) throws Exception{
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setContentType("text/json;charset=utf-8");
		String content = Util.aesDecrypt(params);
		Map<String , Object> jMap = CommonClass.getMapByJson(content);
		long cusId = Long.valueOf(jMap.get("cusId").toString());
		PubCustomer customer = pubCustomerService.getCustomerById(cusId);
		long flag=pubCustomerService.queryCustomerStore(cusId);
		customer.setStoreId(flag);
		BigDecimal a = BigDecimal.valueOf(1);
		if(customer.getFsex().equals(a)){
			customer.setSex("男");
		}else{
			customer.setSex("女");
		}
		//System.out.println("sex:"+customer.getSex());
		PrintWriter out = response.getWriter();
		out.write(JSONObject.fromObject(customer).toString());
		//System.out.println(JSONObject.fromObject(customer).toString());
		out.flush();
		out.close();
		return null;
	}
	
	/**
	 * 修改头像
	 * @param file
	 * @param id
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(params="method=upload")
	public String upload(@RequestParam(value = "file", required = false) MultipartFile file,
	    		Long id,String name, HttpServletRequest request,HttpServletResponse response, ModelMap model)
	    		throws IOException{  
	     
//	        System.out.println("开始");  
//	        System.out.println(name);
	        //更改头像前先删除之前的
	        //String path = "D:\\server\\socket\\webapps\\InnovPic\\PubCustomer";
	        String path = "D:\\data_file\\InnovPic\\PubCustomer";
	        //String path = "D:\\server\\socket\\webapps\\InnovPic\\PubCustomer";
//	        System.out.println(path);
	        String fileName = file.getOriginalFilename();  
	        fileName = id+"head"+System.currentTimeMillis()+".png";  
	        File targetFile = new File(path, fileName);  
	        if(!targetFile.getParentFile().exists()){  
	            targetFile.getParentFile().mkdirs();  
	        } 
	        //保存  
	        try {  
	            file.transferTo(targetFile); 
	            pubCustomerService.modifyUrl(id,fileName);
	        } catch (Exception e) {  
	            e.printStackTrace();  
	        }  
	        PrintWriter out = response.getWriter();
			out.flush();
			out.close();  
//	        return "result";  
			return null;
	    }  
	
	/**
	 * 修改个人信息
	 * @param params
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(params="method=changeMyinfo")
	public String getModify(String params,HttpServletResponse response) throws IOException {	
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setContentType("text/json;charset=utf-8");
		PrintWriter out = response.getWriter();
//		System.out.println("getModify");   
		String params_log = "";
		params_log = Util.aesDecrypt(params);
//		System.out.println(params_log);
		Map<String, Object> jsonMap=CommonClass.getMapByJson(params_log);
		Long  cusId=Long.valueOf(jsonMap.get("cusId").toString());
		String  label= jsonMap.get("label").toString();
		String  content=jsonMap.get("content").toString();
//		System.out.println(label);
//		System.out.println(content);
//		System.out.println(pubCustomerService.changeMyinfo(cusId,label, content));
		PubCustomer customer = pubCustomerService.getCustomerById(cusId);
        out.write(JSONObject.fromObject(customer).toString());
		out.flush();
		out.close();  
		return null;
	}
	
	/**
	 * 我要反馈
	 * @param params
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(params="method=wyfk")
	public String wyfk(String params,HttpServletResponse response) throws IOException {	
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setContentType("text/json;charset=utf-8");
		PrintWriter out = response.getWriter();
//		System.out.println("我要反馈");   
		String params_log = "";
		params_log = Util.aesDecrypt(params);
//		System.out.println(params_log);
		Map<String, Object> jsonMap=CommonClass.getMapByJson(params_log);
		String  ffkr= jsonMap.get("ffkr").toString();
		String  fknr= jsonMap.get("fknr").toString();
		String  ftel= jsonMap.get("ftel").toString();
//		System.out.println(ffkr);
//		System.out.println(fknr);
//		System.out.println(ftel);
		owWyfkService.save(ffkr, ftel, fknr);
        out.write(1);
		out.flush();
		out.close();  
		return null;
	}
	
	/**
	 * 我的预警设置
	 * @param params
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(params="method=myAlarms")
	public String myAlarms(String params,HttpServletResponse response) throws IOException {	
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setContentType("text/json;charset=utf-8");
		PrintWriter out = response.getWriter();
//		System.out.println("我的预警设置");   
		String params_log = "";
		params_log = Util.aesDecrypt(params);
//		System.out.println(params_log);
		Map<String, Object> jsonMap=CommonClass.getMapByJson(params_log);
		long cusId= Long.valueOf(jsonMap.get("cusId").toString());
//		System.out.println(cusId);
		List<OwCusalarms> alarmList = owCusalarmsService.queryAlarmByCus(cusId);
		List<OwCusalarms> clgzList = new ArrayList<OwCusalarms>();//车辆故障
		List<OwCusalarms> fdxxList = new ArrayList<OwCusalarms>();//防盗消息
		List<OwCusalarms> jsxxList = new ArrayList<OwCusalarms>();//驾驶消息
		List<OwCusalarms> aqxxList = new ArrayList<OwCusalarms>();//安全消息
		for (int i = 0; i < alarmList.size(); i++) {
			if(alarmList.get(i).getFtype().equals("车辆故障")){
				clgzList.add(alarmList.get(i));
			}else if(alarmList.get(i).getFtype().equals("防盗消息")){
				fdxxList.add(alarmList.get(i));
			}else if(alarmList.get(i).getFtype().equals("驾驶消息")){
				jsxxList.add(alarmList.get(i));
			}else{
				aqxxList.add(alarmList.get(i));
			}
		}
		JSONObject json = new JSONObject();
		json.put("clgzList", clgzList);
		json.put("fdxxList", fdxxList);
		json.put("jsxxList", jsxxList);
		json.put("aqxxList", aqxxList);
//		System.out.println(json.toString());
        out.write(json.toString());
		out.flush();
		out.close();  
		return null;
	}
	
	/**
	 * 打开关闭预警设置
	 * @param params
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(params="method=updateSwitch")
	public String updateSwitch(String params,HttpServletResponse response) throws IOException {	
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setContentType("text/json;charset=utf-8");
		PrintWriter out = response.getWriter();
//		System.out.println("打开关闭预警设置");   
		String params_log = "";
		params_log = Util.aesDecrypt(params);
//		System.out.println(params_log);
		Map<String, Object> jsonMap=CommonClass.getMapByJson(params_log);
		long id= Long.valueOf(jsonMap.get("id").toString());
		long sw= Long.valueOf(jsonMap.get("sw").toString());
		owCusalarmsService.updateSwitch(id, sw);
        out.write(1);
		out.flush();
		out.close();  
		return null;
	}
	/**
	 * 根据用户id查询管家信息
	 *@author zhihui
	 *@date 2016年9月19日
	 * @param params
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(params="method=getHousekeeperById",method=RequestMethod.POST)
	@ResponseBody
	@ApiOperation(value = "根据用户Id查询管家信息",notes="{cusId:用户id}")
	public void getHousekeeperById(@ApiParam(value="输入{cusId:用户id}")String params,HttpServletResponse response) throws Exception {	
		String getParams = Util.aesDecrypt(params);
		Map<String, Object> jsonMap = CommonClass.getMapByJson(getParams);
		Long cusId = Long.valueOf(jsonMap.get("cusId")+"");
		AppObjectResult appObjectResult = null;
		if(cusId>0){
			List<Map<String, Object>> housekeeper = pubCustomerService.getHousekeeperById(cusId);
			appObjectResult = new AppObjectResult(SystemInfo.QUERY_SUCCESS.getCode(), SystemInfo.QUERY_SUCCESS.getMsg(), housekeeper);
		}else{
			appObjectResult = new AppObjectResult(SystemInfo.ILLEGAL_PARAMETER.getCode(), SystemInfo.ILLEGAL_PARAMETER.getMsg());
		}
		sendDataResponse(response,JSONUtil.getJSONString(appObjectResult));
	}
	
	/**
	 * 获取门店列表
	 * @param params
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(params="method=getPubStoreAll")
	@ResponseBody
	@ApiOperation(value = "获取门店列表",notes="{}")
	public void getPubStoreAll(String params,HttpServletResponse response) throws IOException {	
		String getParams = Util.aesDecrypt(params);
		Map<String, Object> jsonMap = CommonClass.getMapByJson(getParams);
		AppObjectResult appObjectResult = null;
		List<Map<String, Object>> list = pubStoreService.getPubStoreAll();
		appObjectResult = new AppObjectResult(SystemInfo.QUERY_SUCCESS.getCode(), SystemInfo.QUERY_SUCCESS.getMsg(), list);
		try {
			sendDataResponse(response,JSONUtil.getJSONString(appObjectResult));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 完善个人信息
	 * @param params
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(params="method=perfectInfo")
	@ResponseBody
	@ApiOperation(value = "完善个人信息",notes="{cusId:用户id,sex:性别,driving:驾龄,address:地区,nick:昵称,staffId:员工Id,storeId:门店id}")
	public String perfectInfo(String params,HttpServletResponse response) throws IOException {	
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setContentType("text/json;charset=utf-8");
		PrintWriter out = response.getWriter();
		String params_log = "";
		params_log = Util.aesDecrypt(params);
		Map<String, Object> jsonMap=CommonClass.getMapByJson(params_log);
		Long cusId= Long.valueOf(jsonMap.get("cusId").toString());
		Long storeId= Long.valueOf(jsonMap.get("storeId").toString());
		int sex= Integer.valueOf(jsonMap.get("sex").toString());
		int driving= Integer.valueOf(jsonMap.get("driving").toString());
		String address= jsonMap.get("address").toString();
		String nick= jsonMap.get("nick").toString();
		String staffId= jsonMap.get("staffId").toString();
		boolean success = false;
		try {
			success = pubCustomerService.perfectInfo(cusId, nick, address, driving, sex, staffId);
			long id = pubBdstoreService.getIsExistId(storeId, cusId);
			if(id==-1){//之前未绑定
				pubBdstoreService.updateDefault(cusId);
				pubBdstoreService.save(storeId, cusId);
			}else{//已经绑定门店（直接修改门店）
				pubBdstoreService.updateDefault(cusId);
				pubBdstoreService.updateCusDefault(id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		JSONObject json = new JSONObject();
		json.put("success", success);
        out.write(json.toString());
		out.flush();
		out.close();  
		return null;
	}
	@RequestMapping(params="method=updateManageId")
	@ResponseBody
	@ApiOperation(value = "完善管家门店信息",notes="{cusId:用户id,staffId:管家Id,storeId:门店id}")
	public String updateManageId(String params,HttpServletResponse response) throws IOException {	
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setContentType("text/json;charset=utf-8");
		PrintWriter out = response.getWriter();
		String params_log = "";
		params_log = Util.aesDecrypt(params);
		Map<String, Object> jsonMap=CommonClass.getMapByJson(params_log);
		Long cusId= Long.valueOf(jsonMap.get("cusId").toString());
		Long storeId= Long.valueOf(jsonMap.get("storeId").toString());
		String staffId= jsonMap.get("staffId").toString();
		boolean success = false;
		success = pubCustomerService.updateManageId(cusId, staffId, storeId);
		JSONObject json = new JSONObject();
		json.put("success", success);
        out.write(json.toString());
		out.flush();
		out.close();  
		return null;
	}
	
	/**
	 * 查找客户的基本信息(包括客户信息、车辆信息、车系信息、门店id)
	 * @author sududa
	 * @date 2016年11月14日
	 */
	@RequestMapping(params="method=queryCusInfos",method=RequestMethod.POST)
	@ResponseBody
	@ApiOperation(value = "查找客户的基本信息", notes="{cusId:登录用户的id}")
	public void queryCusInfos(@ApiParam(value="输入{cusId:登录用户的id}")String params,HttpServletResponse response){	
		AppObjectResult appObjectResult = null;
		try {
			String getParams = Util.aesDecrypt(params);
			Map<String, Object> jsonMap = CommonClass.getMapByJson(getParams);
			String cusId = jsonMap.get("cusId")+"".trim();//登录id
//			String cusId = "32".trim();//登录id
			if(StringUtil.isNotEmpty(cusId)){
				Map<String, Object> resultMap = pubCustomerService.queryCusInfos(cusId);
				appObjectResult = new AppObjectResult(SystemInfo.QUERY_SUCCESS.getCode(), SystemInfo.QUERY_SUCCESS.getMsg(),resultMap);
			}else{
				appObjectResult = new AppObjectResult(SystemInfo.ILLEGAL_PARAMETER.getCode(), SystemInfo.ILLEGAL_PARAMETER.getMsg());
			}
			sendDataResponse(response,JSONUtil.getJSONString(appObjectResult));
		} catch (Exception e) {
			logger.error("我的信息控制类:_查找客户的基本信息"+e.getMessage());
			e.printStackTrace();
		}
	}
	
	
}
