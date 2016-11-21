package com.ennuova.app.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ennuova.app.config.AppObjectResult;
import com.ennuova.app.config.SystemInfo;
import com.ennuova.app.service.PubCustomerService;
import com.ennuova.common.CommonClass;
import com.ennuova.controller.GeneralControl;
import com.ennuova.entity.PubCustomer;
import com.ennuova.tools.sms.sendMessage;
import com.ennuova.util.JSONUtil;
import com.ennuova.util.RandomNumberGenerator;
import com.ennuova.util.StringUtil;
import com.ennuova.util.Util;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

@Controller("regController")
@RequestMapping("/regUser.do")
public class RegController extends GeneralControl{

	@Resource
	private PubCustomerService pubCustomerService;

	/**
	 * 注册
	 * @param params
	 * @param response
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(params="method=reg")
	public String reg(String params,HttpServletResponse response,HttpServletRequest request) throws IOException {

		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setContentType("text/json;charset=utf-8");
		PrintWriter out = response.getWriter();
		System.out.println("reg");

		String params_log = "";

		//获取用户APP注册的json串
		params_log = Util.aesDecrypt(params);
		System.out.println(params_log);
		Map<String, Object> jsonMap=CommonClass.getMapByJson(params_log);
		//String name = jsonMap.get("name").toString();
		String tel = jsonMap.get("tel").toString();
		String pwd = jsonMap.get("pwd").toString();
		String getVcode=jsonMap.get("vcode").toString();
		System.out.println("用户名"+tel);
		Map<String, String> ob = new HashMap<String, String>();
		boolean m = pubCustomerService.ifTelEg(tel);
		if(m){
			ob.put("succ", "2");
			System.out.println("手机号已存在！");
		}else{
			//校验验证码是否正确
			HttpSession session = request.getSession();
			if(getVcode.equals(session.getAttribute("regVcode"))&&tel.equals(session.getAttribute("regMobile"))){
				System.out.println("验证码输入正确");
				long key=pubCustomerService.reg(tel, pwd);
				pubCustomerService.addAlarm(key);
				ob.put("succ", "1");
				ob.put("khID", Long.toString(key));
				System.out.println(JSONArray.fromObject(ob).toString());
			}else{
				System.out.println("验证码："+getVcode);
				System.out.println("验证码输入错误");
				ob.put("succ", "0");
			}
		}
		out.write(JSONArray.fromObject(ob).toString());
		out.flush();
		out.close();

		return null;
	}

	/**
	 * 短信测试
	 * 
	 * @param phone 发送到的手机号
	 * @param contentStr 发送内容
	 * @param map
	 * @return
	 */
	@SuppressWarnings("unused")
	//此方法通过接收APP客户端的手机号码从而发送验证码到对应的手机上
	@RequestMapping(params="method=sms")
	public String sms(String params,HttpServletResponse response,HttpSession httpSession)throws IOException {
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setContentType("text/json;charset=utf-8");
		String resultStr=new String();

		System.out.println("sms");

		String params_log = "";

		//获取用户输入的手机号码
		params_log = Util.aesDecrypt(params);
		System.out.println(params_log);
		Map<String, Object> jsonMap=CommonClass.getMapByJson(params_log);
		String mobile = jsonMap.get("mobile").toString();
		System.out.println(mobile);
		//调用发送短信的方法
		//生成验证码
		String code=RandomNumberGenerator.generateNumber();
		System.out.println(code);
		httpSession.setAttribute("regMobile", mobile);
		httpSession.setAttribute("regVcode", code);
		//发送验证码
		sendMessage sendMessage=new sendMessage();
		//sendMessage.sendSms(mobile,"您的验证码:"+code,null);
		sendMessage.sendCode(code, mobile);
		PrintWriter out = response.getWriter();
		out.write(1);
		out.flush();
		out.close();
		return null;
	}

	@RequestMapping(params="method=ifTelEg")
	public String isTelEg(String params,HttpServletResponse response)throws IOException {
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setContentType("text/json;charset=utf-8");
		String params_log = "";
		//获取用户输入的手机号码
		params_log = Util.aesDecrypt(params);
		System.out.println(params_log);
		Map<String, Object> jsonMap=CommonClass.getMapByJson(params_log);
		String mobile = jsonMap.get("mobile").toString();
		System.out.println(mobile);
		//判断该电话号码是否存在
		boolean c = pubCustomerService.ifTelEg(mobile);
		Map<String, String> ob = new HashMap<String, String>();
		if(c==true)
		{
			ob.put("succ", "0");
			System.out.println("该手机号已经注册");
		}else{
			ob.put("succ", "1");
		}
		PrintWriter out = response.getWriter();
		out.write(JSONArray.fromObject(ob).toString());
		out.flush();
		out.close();
		return null;
	}
	
	/**
	 * 忘记密码
	 * @param params
	 * @param response
	 * @return
	 */
	@RequestMapping(params = "method=forgetPwd")
	public String forgetPwd(String params,HttpServletResponse response,HttpServletRequest request) throws Exception{
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setContentType("text/json;charset=utf-8");
		PrintWriter out = response.getWriter();
		System.out.println("forget");
		String params_log = "";
		//获取用户APP注册的json串
		params_log = Util.aesDecrypt(params);
		System.out.println(params_log);
		Map<String, Object> jsonMap=CommonClass.getMapByJson(params_log);
		//String name = jsonMap.get("name").toString();
		String tel = jsonMap.get("tel").toString();
		String pwd = jsonMap.get("pwd").toString();
		String getVcode=jsonMap.get("vcode").toString();
		System.out.println("用户名"+tel);
		Map<String, String> ob = new HashMap<String, String>();
		boolean m = pubCustomerService.ifTelEg(tel);
		if(m){
			//校验验证码是否正确
			HttpSession session = request.getSession();
			if(getVcode.equals(session.getAttribute("regVcode"))&&tel.equals(session.getAttribute("regMobile"))){
				System.out.println("验证码输入正确");
				pubCustomerService.forgetPwd(tel, pwd);
				ob.put("succ", "1");
				System.out.println(JSONArray.fromObject(ob).toString());
			}else{
				System.out.println("验证码："+getVcode);
				System.out.println("验证码输入错误");
				ob.put("succ", "0");
			}
			
		}else{
			ob.put("succ", "2");
			System.out.println("手机号未注册！");
		}
		out.write(JSONArray.fromObject(ob).toString());
		out.flush();
		out.close();
		return null;
	}
}
