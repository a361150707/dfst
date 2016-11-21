package com.ennuova.app.controller;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.ennuova.app.config.AppObjectResult;
import com.ennuova.app.config.SystemInfo;
import com.ennuova.app.service.CarInfoService;
import com.ennuova.common.CommonClass;
import com.ennuova.controller.GeneralControl;
import com.ennuova.tools.weather.WeatherQuery;
import com.ennuova.util.JSONUtil;
import com.ennuova.util.StringUtil;
import com.ennuova.util.Util;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

@Controller
@RequestMapping("/commonController")
@Api(value="", description="通用控制类")
public class CommonController extends GeneralControl{
	private static Logger logger = Logger.getLogger(CommonController.class);

	@RequestMapping(params="method=getWeather",method=RequestMethod.POST)
	@ResponseBody
	@ApiOperation(value = "根据城市名称获取天气信息", notes="{cityName:城市名称}")
	public void getWeather(@ApiParam(value="输入{cityName:城市名称}")String params,HttpServletResponse response){	
		try {
			long startTime=System.currentTimeMillis();
			String getParams = Util.aesDecrypt(params);
			Map<String, Object> jsonMap = CommonClass.getMapByJson(getParams);
			String cityName = jsonMap.get("cityName")+"".trim();//登录id
//			String cityName = "厦门".trim();//登录id
			AppObjectResult appObjectResult = null;
			if(StringUtil.isNotEmpty(cityName)){
				String content = WeatherQuery.getWeatherByCityName(cityName);
//				JSONObject jsonObject = JSONObject.parseObject(content);
				appObjectResult = new AppObjectResult(SystemInfo.QUERY_SUCCESS.getCode(), SystemInfo.QUERY_SUCCESS.getMsg(),content);
			}else{
				appObjectResult = new AppObjectResult(SystemInfo.ILLEGAL_PARAMETER.getCode(), SystemInfo.ILLEGAL_PARAMETER.getMsg());
			}
			sendDataResponse(response,JSONUtil.getJSONString(appObjectResult));
		} catch (Exception e) {
			logger.error("通用控制类:"+e.getMessage());
			e.printStackTrace();
		}
	}
	
}
