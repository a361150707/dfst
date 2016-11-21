package com.ennuova.app.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ennuova.app.service.PubContactsService;
import com.ennuova.app.service.PubCustomerService;
import com.ennuova.common.CommonClass;
import com.ennuova.entity.PubCustomer;
import com.ennuova.util.StringUtil;
import com.ennuova.util.UrlUtil;
import com.ennuova.util.Util;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;

@Controller("logController")
@RequestMapping("/log.do")
@Api(value="", description="登录")
public class LogController {
	
	@Resource
	private PubCustomerService pubCustomerService;
	
	/**
	 * 附近的人,联系人服务
	 */
	@Resource
	private PubContactsService pubContactsService;
	
	
	/**
	 * 登录
	 * @author sududa
	 * @date 2016年11月3日
	 */
	@RequestMapping(params="method=dl")
	@ApiOperation(value = "登录", notes="{mobile:手机号,pwd:密码}")
	public String dljlb(String params,HttpServletResponse response,HttpServletRequest request) throws IOException {
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setContentType("text/json;charset=utf-8");
		PrintWriter out = response.getWriter();
		String params_log =Util.aesDecrypt(params);
		Map<String, Object> jsonMap=CommonClass.getMapByJson(params_log);
		String mobile = jsonMap.get("mobile")+"";
		String pwd= jsonMap.get("password")+"";
//		String mobile = "15959279267";
//		String pwd= "123456";
//		String loginIp = getIpAddr(request);
		long khID=pubCustomerService.validate(mobile, pwd);
		PubCustomer memInfo = new PubCustomer();
        if(khID !=-1){
        	memInfo = pubCustomerService.getCustomerById(khID);
        	request.getSession().setAttribute("memInfo", memInfo);
        }
        Map<String, Object> ob = new HashMap<String, Object>();
		ob.put("khID", khID);
		ob.put("tel", memInfo.getFtel());
		ob.put("name", memInfo.getFusername());
		ob.put("defCarId", memInfo.getDefCarId());
		ob.put("defboxid", memInfo.getDefBoxId());
		ob.put("xlh", memInfo.getBoxNum());
		ob.put("carnum", memInfo.getCarNum());
		ob.put("vname", memInfo.getVname());
		ob.put("headImg", memInfo.getFphoto());
		ob.put("address", memInfo.getFaddr());
		ob.put("managerID", memInfo.getUserId());
		ob.put("nick", memInfo.getFnick());
		ob.put("storeId", memInfo.getStoreId());
		ob.put("CUSINTEREST", memInfo.getCusInterest());
		ob.put("sex", memInfo.getSex());
		ob.put("isSearchTel", memInfo.getIsSearchTel());
		ob.put("LINENAME", memInfo.getLineName());
		List<Map<String, Object>> contactsList = null;
		if(StringUtil.isNotEmpty(memInfo.getId())){
			contactsList = pubContactsService.doContactsList(memInfo.getId()+"");
		}
		ob.put("contactsList", contactsList);
		/*ob.put("carId", info.getId());
		ob.put("boxId", info.getBoxId());
		ob.put("boxFxlh", info.getFxlh());*/
		//System.out.println(JSONObject.fromObject(ob).toString());
		out.write(JSONObject.fromObject(ob).toString());
		out.flush();
		out.close();
		return null;
	}
	
	/**
	 * 当用户打开APP时获取当前登陆账户的信息，并以日志形式打印
	 * @author 伟灿
	 * @time 2016-1-7
	 * @param params
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(params = "method=savaMemInfo")
	public String savaMemInfo(String params,HttpServletRequest request,HttpServletResponse response) throws Exception{
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setContentType("text/json;charset=utf-8");
		//System.out.println("打开APP时处理个人信息");
		String params_log = "";
		params_log = Util.aesDecrypt(params);
		//System.out.println(params_log);
		Map<String, Object> jsonMap=CommonClass.getMapByJson(params_log);
		long id = Long.valueOf(jsonMap.get("id").toString());
		PubCustomer memInfo = pubCustomerService.getCustomerById(id);
		request.getSession().setAttribute("memInfo", memInfo);
		PrintWriter out = response.getWriter();
		out.flush();
		out.close();
		return null;		
	}
	
	
	/**
	 * 清除session用户信息
	 * @author 伟灿
	 * @time 2016-1-7
	 * @param params
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(params="method=outLog")
	public String outLog(HttpServletRequest request,HttpServletResponse response) throws Exception{
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setContentType("text/json;charset=utf-8");
		//System.out.println("清除session用户信息");
		request.getSession().removeAttribute("memInfo");
		PrintWriter out = response.getWriter();
		out.flush();
		out.close();
		return null;		
	}
	
	public String getIpAddr(HttpServletRequest request) {      
        String ip = request.getHeader("x-forwarded-for");      
            if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {     
                ip = request.getHeader("Proxy-Client-IP");      
            }     
            if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {     
                ip = request.getHeader("WL-Proxy-Client-IP");     
            }     
            if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {     
                ip = request.getRemoteAddr();      
            }   
       return ip;     
    }
	
}