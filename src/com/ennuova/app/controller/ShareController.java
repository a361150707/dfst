package com.ennuova.app.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ennuova.app.service.OwNewsctService;
import com.ennuova.entity.OwNewsct;
import com.ennuova.util.StringUtil;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;

/**分享控制
 * @author 李智辉
 * @time 2016-7-21
 */
@Controller
@RequestMapping("/share")
@Api(value="", description="分享")
public class ShareController {
	/**
	 * 新闻的服务
	 */
	@Resource
	private OwNewsctService owNewsctService;
	
	/*@RequestMapping(params="method=News")
	@ApiOperation(value = "分享页面",notes="{id:新闻Id}")
	public String shareNews(String params,String id,Model model,HttpServletResponse response,HttpServletRequest request) throws IOException, ServletException {
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setContentType("text/json;charset=utf-8");
		if(StringUtil.isNotEmpty(id)){
			Long longId = Long.valueOf(id);
			OwNewsct news = owNewsctService.getNewsById(longId);
			request.setAttribute("news", news);
			return "share/test";
		}else{
			return null;
		}
	}*/
	
	/**
	 * 点击分享链接的跳转页面
	 * @author sududa
	 * @date 2016年7月26日
	 * @param params
	 * @param id
	 * @param model
	 * @param response
	 * @param request
	 * @return
	 * @throws IOException
	 * @throws ServletException
	 */
	@RequestMapping(params="method=News")
	@ApiOperation(value = "分享页面",notes="{id:新闻Id}")
	public ModelAndView shareNews(String params,String id,Model model,HttpServletResponse response,HttpServletRequest request) throws IOException, ServletException {
		if(StringUtil.isNotEmpty(id)){
			Long longId = Long.valueOf(id);
			OwNewsct news = owNewsctService.getNewsById(longId);
			request.setAttribute("news", news);
			return new ModelAndView("share/test");
		}else{
			return null;
		}
	}
	
	
}
