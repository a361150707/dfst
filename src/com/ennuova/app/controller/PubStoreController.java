package com.ennuova.app.controller;

import java.io.PrintWriter;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ennuova.app.service.PubBdstoreService;
import com.ennuova.app.service.PubCustomerService;
import com.ennuova.app.service.PubStoreService;
import com.ennuova.common.CommonClass;
import com.ennuova.entity.PubStore;
import com.ennuova.util.Util;

@Controller("pubStoreController")
@RequestMapping("/pubStore.do")
public class PubStoreController {

	@Resource
	private PubStoreService pubStoreService;
	
	@Resource
	private PubCustomerService pubCustomerService;
	
	@Resource
	private PubBdstoreService pubBdstoreService;
	
	
	/**
	 * APP打开获取用户默认门店Id
	 * @param params
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params="method=queryPubstore")
	public String queryPubStore(String params,HttpServletRequest request,HttpServletResponse response) throws Exception{
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setContentType("text/json;charset=utf-8");
		System.out.println("获取门店信息");
		String content = Util.aesDecrypt(params);
		Map<String,Object> jsonMap=CommonClass.getMapByJson(content);
		long cusId=Long.valueOf(jsonMap.get("cusId").toString());
		long storeId=1;
		//获取客户默认门店
	    long flag=pubCustomerService.queryCustomerStore(cusId);
	    if(flag!=-1) {
	    	storeId=flag;
	    }
	    System.out.println("默认门店:"+flag+",用户id:"+cusId);
		System.out.println(storeId);
	    PubStore store=pubStoreService.queryStoreById(storeId);//获取门店信息
	    /*List<OwNewsct> newsList = owNewsctService.queryAllByState(2l,store.getFcompany(),0);
	    List<OwNewsct> discountList = owNewsctService.queryAllByState(2l, store.getFcompany(), 2);
	    List<OwNewsindex> owNewsindexList = owNewsindexService.queryNewsindex(store.getFcompany(),2);	
	    json.put("newsList", newsList);
	    json.put("discountList", discountList);
	    json.put("owNewsindexList", owNewsindexList);*/
	    //System.out.println(json.toString());
		System.out.println(JSONObject.fromObject(store).toString());
	    PrintWriter out=response.getWriter();
	    out.write(JSONObject.fromObject(store).toString());
	    out.flush();
	    out.close();
		return null;
	}
	
	@RequestMapping(params="method=queryMyStore")
	public String querySomeStore(String params,HttpServletRequest request,HttpServletResponse response) throws Exception{
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setContentType("text/json;charset=utf-8");
		System.out.println("获取我的所有门店信息");
		String content = Util.aesDecrypt(params);
		Map<String,Object> jsonMap=CommonClass.getMapByJson(content);
		long cusId=Long.valueOf(jsonMap.get("cusId").toString());
		List<PubStore> storeList=pubStoreService.queryMyStore(cusId);
		PrintWriter out=response.getWriter();
	    out.write(JSONArray.fromObject(storeList).toString());
	    System.out.println(JSONArray.fromObject(storeList).toString());
	    out.flush();
	    out.close();
		return null;
	}
	
	/*@RequestMapping(params="method=queryDefaultStore")
	public String queryDefaultStore(String params,HttpServletRequest request,HttpServletResponse response) throws Exception{
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setContentType("text/json;charset=utf-8");
		System.out.println("获取用户默认门店id");
		String content = Util.aesDecrypt(params);
		Map<String,Object> jsonMap=CommonClass.getMapByJson(content);
		Long cusId=Long.valueOf(jsonMap.get("cusId").toString());
		long storeId=pubCustomerService.queryCustomerStore(cusId);
		System.out.println(storeId+"----");
		PrintWriter out=response.getWriter();
	    out.write(String.valueOf(storeId));
	    out.flush();
	    out.close();
		return null;
	}*/
	
	@RequestMapping(params = "method=queryStoreByCity")
	public String queryStoreByCity(String params,HttpServletResponse response,HttpServletRequest request) throws Exception{
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setContentType("text/json;charset=utf-8");
		System.out.println("获取城市地区的门店");
		String content = Util.aesDecrypt(params);
		Map<String,Object> jMap=CommonClass.getMapByJson(content);
		long city = Long.valueOf(jMap.get("cid").toString());
		List<PubStore> storeList=pubStoreService.querySomeStore(city);
		PrintWriter out = response.getWriter();
		out.write(JSONArray.fromObject(storeList).toString());
		out.flush();
		out.close();
		System.out.println(JSONArray.fromObject(storeList).toString());
		return null;
	}
	
	/**
	 * 绑定门店
	 * @Description: TODO 
	 * @author felix
	 * @date 2015-12-14
	 */
	@Transactional
	@RequestMapping(params = "method=bindingStore")
	public String bindingStore(String params,HttpServletResponse response,HttpServletRequest request) throws Exception{
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setContentType("text/json;charset=utf-8");
		System.out.println("绑定门店");
		String content = Util.aesDecrypt(params);
		Map<String,Object> jMap=CommonClass.getMapByJson(content);
		long storeId = Long.valueOf(jMap.get("storeId").toString());
		long cusId = Long.valueOf(jMap.get("cusId").toString());
		long id = pubBdstoreService.getIsExistId(storeId, cusId);
		if(id==-1){//之前未绑定
			pubBdstoreService.updateDefault(cusId);
			pubBdstoreService.save(storeId, cusId);
		}else{//已经绑定门店（直接修改门店）
			pubBdstoreService.updateDefault(cusId);
			pubBdstoreService.updateCusDefault(id);
		}
		PrintWriter out = response.getWriter();
		out.write(1);
		out.flush();
		out.close();
		return null;
	}
	
	/**
	 * 删除门店
	 * @param params
	 * @param response
	 * @return
	 */
	@RequestMapping(params="method=delCusStore")
	public String delCusStore(String params,HttpServletResponse response) throws Exception{
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setContentType("text/json;charset=utf-8");
		System.out.println("删除绑定门店");
		String content = Util.aesDecrypt(params);
		Map<String,Object> jMap=CommonClass.getMapByJson(content);
		long bId = Long.valueOf(jMap.get("bId").toString());
		boolean bool = pubBdstoreService.delCusStore(bId);
		PrintWriter out = response.getWriter();
		out.write(bool+"");
		out.flush();
		out.close();
		return null;
	}
	/**维修 根据地址信息获取 当前城市的4s店
	 * @param params
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(params="method=repairCar")
	public ModelAndView  repairCar(String params,HttpServletRequest request,HttpServletResponse response){
		String json = "";
		JSONObject jsonObject = new JSONObject();
		String content = Util.aesDecrypt(params);
		if (content!=null) {
			Map<String, Object> jMap = CommonClass.getMapByJson(content);
			String realTimeAddress = jMap.get("realTimeAddress").toString();
			List<PubStore> pubStores = this.pubStoreService.getPubStoreByAddress(realTimeAddress);
			jsonObject.put("success", true);
			jsonObject.put("pubStoreList", pubStores);
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
			System.out.println(new Date().getTime());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
