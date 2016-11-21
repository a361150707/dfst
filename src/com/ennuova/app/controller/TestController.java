package com.ennuova.app.controller;

import java.io.PrintWriter;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ennuova.app.service.PubCustomerService;
import com.ennuova.app.service.TestService;
import com.ennuova.entity.PubCustomer;
import com.ennuova.entity.Test;

@Controller
@RequestMapping("/test.do")
public class TestController {

	@Resource
	private TestService testService;
	
	@Resource
	private PubCustomerService pubCustomerService;
	
	@RequestMapping(params="method=query")
	public String query(String params,HttpServletRequest request,HttpServletResponse response) throws Exception{
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setContentType("text/json;charset=utf-8");
		List<Test> tlist=this.testService.query();
		for (Test test : tlist) {
			System.out.println("id:"+test.getId()+",name:"+test.getName());
		}
		PrintWriter out=response.getWriter();
		/*String content = "";

		content = Util.aesDecrypt(params);
		
		Map<String, Object> jsonMap=CommonClass.getMapByJson(content);
		System.out.println("-----"+content);
		String code= jsonMap.get("name").toString();*/
		String code="0.0";
		System.out.println(code);
		Test t=new Test();
		t.setName("你好！Hello world！");
		out.write(JSONObject.fromObject(t).toString());
		out.flush();
		out.close();
		return null;
	}
	
	@RequestMapping(params="method=turn")
	public String turn(){
		List<PubCustomer> list=this.pubCustomerService.queryAll();
		for (PubCustomer pubCustomer : list) {
			System.out.println("id:"+pubCustomer.getId()+",name:"+pubCustomer.getFtel()+",pwd:"+pubCustomer.getFpassword());
		}
		System.out.println("id:"+pubCustomerService.validate("110", "111"));
		return "test";
	}
	
	@RequestMapping(params="method=test")
	public String test(){
		
		
		pubCustomerService.reg("110","111");
		
		return null;
	}
}
