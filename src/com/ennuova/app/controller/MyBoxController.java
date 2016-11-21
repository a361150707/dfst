package com.ennuova.app.controller;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ennuova.app.service.CarInfoService;
import com.ennuova.app.service.MyBoxService;
import com.ennuova.common.CommonClass;
import com.ennuova.entity.PubBox;
import com.ennuova.entity.PubCarinfo;
import com.ennuova.entity.PubCustomer;
import com.ennuova.util.Util;

@Controller("myBoxController")
@RequestMapping("/mybox.do")
public class MyBoxController {

	@Resource
	private MyBoxService myBoxService;
	@Resource
	private CarInfoService carInfoService;

	/**
	 * 获取我的盒子信息
	 * @Description: TODO 
	 * @author felix
	 * @date 2015-12-17
	 */
	@RequestMapping(params="method=queryMyBox")
	public String queryMyBox(String params,HttpServletRequest request,HttpServletResponse response) throws Exception{
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setContentType("text/json;charset=utf-8");
		System.out.println("myBox");    
		String params_log = "";
		params_log = Util.aesDecrypt(params);
		Map<String,Object> jMap = CommonClass.getMapByJson(params_log);
		long cusId = Long.valueOf(jMap.get("cusId").toString());
		List<PubBox> boxlist = myBoxService.queryMyBox(cusId);
		System.out.println("boxlistSize:"+boxlist.size());
		PrintWriter out = response.getWriter();
		out.write(JSONArray.fromObject(boxlist).toString());
		System.out.println(JSONArray.fromObject(boxlist).toString());
		out.flush();
		out.close();
		return null;
	}

	/**
	 * 检查盒子是否可以绑定(可以result为1，不可以result为-1)
	 * @param params
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params="method=checkbox")
	public String checkbox(String params,HttpServletRequest request,HttpServletResponse response) throws Exception{
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setContentType("text/json;charset=utf-8");
		System.out.println("checkbox");
		String params_log = "";
		params_log = Util.aesDecrypt(params);
		Map<String,Object> jMap = CommonClass.getMapByJson(params_log);
		String fxhl = jMap.get("fxhl").toString();
		long result = myBoxService.checkBox(fxhl);
		PrintWriter out = response.getWriter();
		out.write(String.valueOf(result));
		out.flush();
		out.close();
		return null;
	}

	/**
	 * 获取我的未绑定盒子的车辆信息
	 * @Description: TODO 
	 * @author felix
	 * @date 2015-12-17
	 */
	@RequestMapping(params="method=queryNoBindBoxCar")
	public String queryNoBindBoxCar(String params,HttpServletRequest request,HttpServletResponse response) throws Exception{
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setContentType("text/json;charset=utf-8");
		System.out.println("myBox");    
		String params_log = "";
		params_log = Util.aesDecrypt(params);
		Map<String,Object> jMap = CommonClass.getMapByJson(params_log);
		long cusId = Long.valueOf(jMap.get("cusId").toString());
		List<PubCarinfo> boxlist = myBoxService.queryNoBindBoxCar(cusId);
		PrintWriter out = response.getWriter();
		out.write(JSONArray.fromObject(boxlist).toString());
		System.out.println(JSONArray.fromObject(boxlist).toString());
		out.flush();
		out.close();
		return null;
	}

	/**
	 * 添加盒子绑定
	 * @param params
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params="method=addBindBox")
	public String addBindBox(String params,HttpServletRequest request,HttpServletResponse response) throws Exception{
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setContentType("text/json;charset=utf-8");
		System.out.println("bindbox");    
		String params_log = "";
		params_log = Util.aesDecrypt(params);
		JSONObject jsonObject = new JSONObject();
		Map<String,Object> jMap = CommonClass.getMapByJson(params_log);
		long boxid = Long.valueOf(jMap.get("boxid").toString());
		long fclid = Long.valueOf(jMap.get("fclid").toString());
		String fxlh = jMap.get("fxlh").toString();
		String pwd = jMap.get("password").toString();
		String fcarCode = jMap.get("fcarCode").toString();
		System.out.println(fxlh);
		carInfoService.updateCarCode(fclid, fcarCode);
		boolean bool = myBoxService.addBindBox(fxlh, boxid, fclid,pwd);
		if(bool){
			Map<String, Object> map = new HashMap<String, Object>();
			map = carInfoService.getCarBoxInfo(fclid);
			jsonObject.put("message", map.get("message"));
			if((Boolean) map.get("success")){
				System.out.println("成功");
				try {
					PubCustomer memInfo = (PubCustomer) request.getSession().getAttribute("memInfo");
					System.out.println(memInfo.getId());
					myBoxService.setDefBox(memInfo.getId(), fclid);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}else {
				System.out.println("失败");
				myBoxService.jiebangBox(boxid);
				bool = false;
			}
		}
		jsonObject.put("success", bool);
		PrintWriter out = response.getWriter();
		out.write(jsonObject.toString());
		System.out.println(bool);
		out.flush();
		out.close();
		return null;
	}
	
	/**
	 * 修改盒子默认
	 * @param params
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params = "method=changeDefBox")
	public String changeDefBox(String params,HttpServletRequest request,HttpServletResponse response) throws Exception{
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setContentType("text/json;charset=utf-8");
		System.out.println("修改盒子默认");    
		String params_log = "";
		params_log = Util.aesDecrypt(params);
		Map<String,Object> jMap = CommonClass.getMapByJson(params_log);
		long cusId = Long.valueOf(jMap.get("cusId").toString());
		long clId = Long.valueOf(jMap.get("clId").toString());
		int result = myBoxService.setDefBox(cusId, clId);
		PrintWriter out = response.getWriter();
		out.write(result);
		System.out.println(result);
		out.flush();
		out.close();
		return null;
	}

	
	/**
	 * 解绑盒子
	 * @param params
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params = "method=jiebangBox")
	public String jiebangBox(String params,HttpServletRequest request,HttpServletResponse response) throws Exception{
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setContentType("text/json;charset=utf-8");
		System.out.println("解绑盒子");    
		String params_log = "";
		params_log = Util.aesDecrypt(params);
		Map<String,Object> jMap = CommonClass.getMapByJson(params_log);
		long boxId = Long.valueOf(jMap.get("boxId").toString());
		int result = myBoxService.jiebangBox(boxId);
		PrintWriter out = response.getWriter();
		out.write(result);
		System.out.println(result);
		out.flush();
		out.close();
		return null;
	}
}
