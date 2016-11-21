package com.ennuova.intercept;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.ennuova.entity.PubCustomer;


public class BaseInterceptor extends HandlerInterceptorAdapter{
	
	Logger logger=LoggerFactory.getLogger(getClass());
	
	//对用户进行的操作进行处理
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		request.setCharacterEncoding("UTF-8");
		String url = request.getRequestURI();
		//HandlerMethod method = (HandlerMethod) handler;
		//Object obj = method.getBean();
		PubCustomer memInfo = (PubCustomer) request.getSession().getAttribute("memInfo");
		if(memInfo != null){
			System.out.println("操作用户:"+memInfo.getFusername());
		}else{
			System.out.println("游客操作");
		}
		System.out.println("请求的controller:"+url);
		System.out.println("请求的----method:"+request.getParameter("method"));
		
		//info为登陆管理员对象，判断是否已登录(根据后台自己定义开发)
		return super.preHandle(request, response, handler);
	}
}
