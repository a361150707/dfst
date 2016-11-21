package com.ennuova.app.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ennuova.app.service.CommonImagesService;
import com.ennuova.app.service.CommonVideosService;
import com.ennuova.app.service.CustomerVehiclemodelService;
import com.ennuova.app.service.PubBrandService;
import com.ennuova.app.service.PubCustomerService;
import com.ennuova.app.service.PubLineService;
import com.ennuova.app.service.PubVehicleModelService;
import com.ennuova.app.service.ReserveDriveService;
import com.ennuova.common.CommonClass;
import com.ennuova.controller.GeneralControl;
import com.ennuova.entity.CommonImages;
import com.ennuova.entity.CommonVideos;
import com.ennuova.entity.CustomerVehiclemodel;
import com.ennuova.entity.PubBrand;
import com.ennuova.entity.PubLine;
import com.ennuova.entity.PubVehiclemodel;
import com.ennuova.entity.ReserveDrive;
import com.ennuova.util.Util;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

import net.sf.json.JSONObject;

/**
 * 
 * @Title: PubBrandController.java 
 * @Package com.ennuova.app.controller 
 * @Description: 在线展厅处理类(描述) 
 * @author felix
 * @date 2016年4月15日
 * @version V1.0
 */
@Controller
@RequestMapping("/pubBrand.do")
@Api(value="", description="在线展厅")
public class PubBrandController extends GeneralControl{

	@Resource
	private PubBrandService pubBrandService;

	@Resource
	private PubLineService pubLineService;

	@Resource
	private PubVehicleModelService pubModelService;

	@Resource
	private CustomerVehiclemodelService cusModelService;

	@Resource
	private CommonImagesService ciService;

	@Resource
	private ReserveDriveService rDriveService;

	@Resource
	private PubCustomerService pubCustomerService;
	
	@Resource
	private CommonVideosService commonVideosService;



	/**
	 * 
	 * @Title: PubBrandController.java 
	 * @Package com.ennuova.app.controller 
	 * @Description: 获取在线展厅品牌及车系列表,只针对宝马(描述) 
	 * @author felix
	 * @date 2016年4月8日
	 * @version V1.0
	 */
	@RequestMapping(params = "method=getbrand",method=RequestMethod.POST)
	@ApiOperation(value = "获取宝马品牌及对应车系",notes="{'brand': {'id': 品牌id,'pubLines': [{'id': 车系id ,'mailboxCapacity':油箱容量 ,'sPicture':车系图片 , 'sContent':车系标语, 'fname': 车系名称,'fnumber': 车系编码 }],'fname':品牌名称 ,'fnumber':品牌编码}}")
	public void getbrand(@ApiParam(value="请输入json{无需参数}AES加密后的结果",name="params",defaultValue="",required=true)String params,HttpServletRequest req,HttpServletResponse response){
		PubBrand brand = new PubBrand();
		brand.setFname("宝马");
		brand.setFcodeabb("B");
		JSONObject json = new JSONObject();
		json.put("brand", pubBrandService.getBrand(brand));
		System.out.println(json.toString());
		sendDataResponse(response, json.toString());
	}

	/**
	 * 
	 * @Title: PubBrandController.java 
	 * @Package com.ennuova.app.controller 
	 * @Description: 获取车系下及对应车型列表(描述) 
	 * @author felix
	 * @date 2016年4月11日
	 * @version V1.0
	 */
	@RequestMapping(params = "method=getline",method=RequestMethod.POST)
	@ApiOperation(value = "获取车系及对应车型",notes="{id:车系id,fname:车系名称;	sPicture：车系图片; sContent：车系标语;pubVehiclemodels[{	fnumber:车型编码,fname:车型名称,fyearkuan:年款,funifiedprice:厂商指导价,fmodeldesc:车型说明,fengine:发动机,fgearbox:变速箱,fareas:车身规格(长*宽*高(mm)),fstructure:车身结构,fmaxspeed:最高车速(km/h),fexpedite:官方0-100km/h加速(s),fpl:排量,remarks:备注,borrowNum:浏览数,attentionNum:关注数,attentId:用户关注记录id}]}")
	public void getLine(@ApiParam(value="请输入json{lid:车系id}AES加密后的结果",name="params",required=true)String params,HttpServletRequest req,HttpServletResponse response){
		String params_log = Util.aesDecrypt(params);
		System.out.println(params_log);
		Map<String, Object> jsonMap=CommonClass.getMapByJson(params_log);
		long lid = Long.parseLong(jsonMap.get("lid").toString());
		PubLine line = new PubLine();
		//long lid = 70260;
		line.setId(lid);
		JSONObject json = new JSONObject();
		json.put("line", pubLineService.queryLine(line));
		System.out.println(json.toString());
		sendDataResponse(response, json.toString());
	}


	/**
	 * 
	 * @Title: PubBrandController.java 
	 * @Package com.ennuova.app.controller 
	 * @Description: 获取展厅首页收藏列表(描述) 
	 * @author felix
	 * @date 2016年4月12日
	 * @version V1.0
	 */
	@RequestMapping(params = "method=collectmodel",method=RequestMethod.POST)
	@ApiOperation(value = "获取展厅首页收藏列表",notes="{'cusModelList':[{customerVehiclemodelId:收藏id,customerId:用户id,model:关联车型(属于对象,参数意思参考getline接口),createDate:收藏时间}]}")
	public void collectModel(@ApiParam(value="请输入json{cusid:用户id}AES加密后的结果",name="params",required=true)String params,HttpServletRequest req,HttpServletResponse response){
		String paramsLog = Util.aesDecrypt(params);
		Map<String,Object> jsonMap = CommonClass.getMapByJson(paramsLog);
		long cusId = Long.parseLong(jsonMap.get("cusid").toString());
		//long cusId = 14250;
		//根据用户id获取用户收藏的车型列表
		List<CustomerVehiclemodel> cusModelList = cusModelService.queryCusModel(cusId);
		JSONObject json = new JSONObject();
		json.put("cusModelList", cusModelList);
		System.out.println(json.toString());
		sendDataResponse(response, json.toString());
	}

	/**
	 * 
	 * @Title: PubBrandController.java 
	 * @Package com.ennuova.app.controller 
	 * @Description: 关注车型(描述) 
	 * @author felix
	 * @date 2016年4月14日
	 * @version V1.0
	 */
	@RequestMapping(params = "method=attentmodel",method=RequestMethod.POST)
	@ApiOperation(value = "关注车型",notes="{result:返回结果值(值为0失败,为1成功)}")
	public void attentModel(@ApiParam(value="请输入json{cusid:用户id,modelId:车型id}AES加密后的结果")String params,HttpServletRequest req,HttpServletResponse response){
		String paramsLog = Util.aesDecrypt(params);
		Map<String,Object> jsonMap = CommonClass.getMapByJson(paramsLog);
		//获取关注车型id
		long modelId = Long.valueOf(jsonMap.get("modelId").toString());
		//获取关注用户id
		long cusId = Long.valueOf(jsonMap.get("cusid").toString());
		//进行保存
		CustomerVehiclemodel cusModel = new CustomerVehiclemodel();
		PubVehiclemodel model = new PubVehiclemodel();
		model.setId(modelId);//330303
		cusModel.setCustomerId(cusId);//14250
		cusModel.setModel(model);
		cusModel.setCreateDate(new Date());
		int i = cusModelService.saveCusModel(cusModel);
		JSONObject json = new JSONObject();
		json.put("result", i);
		sendDataResponse(response, json.toString());
	}

	/**
	 * 
	 * @Title: PubBrandController.java 
	 * @Package com.ennuova.app.controller 
	 * @Description: 删除关注(描述) 
	 * @author felix
	 * @date 2016年4月15日
	 * @version V1.0
	 */
	@RequestMapping(params="method=delattent",method=RequestMethod.POST)
	@ApiOperation(value = "删除关注车型",notes="{result:返回结果值(值为0失败,为1成功)}")
	public void delAttent(@ApiParam(value="请输入json{cusModelId:用户关注记录id}AES加密后的结果")String params,HttpServletRequest req,HttpServletResponse response){
		String paramsLog = Util.aesDecrypt(params);
		Map<String,Object> jsonMap = CommonClass.getMapByJson(paramsLog);
		long cusModelId = Long.valueOf(jsonMap.get("cusModelId").toString());
		int i = cusModelService.delCusModel(cusModelId);
		JSONObject json = new JSONObject();
		json.put("result", i);
		sendDataResponse(response, json.toString());
	}

	/**
	 * 
	 * @Title: PubBrandController.java 
	 * @Package com.ennuova.app.controller 
	 * @Description: 获取车型列表，只对宝马,tp表示获取内容类型(0表示获取热度排行,1表示获取默认)(描述) 
	 * @author felix
	 * @date 2016年4月13日
	 * @version V1.0
	 */
	@RequestMapping(params = "method=querymodel",method=RequestMethod.POST)
	@ApiOperation(value = "获取车型列表，只对宝马,tp表示获取内容类型(0表示获取热度排行,1表示获取默认)",notes="{modellist:返回车型列表集合(车型对象具体参数参照getline)}")
	public void queryModel(@ApiParam(value="请输入json{brandId:宝马品牌id,tp:获取内容类型(0表示获取热度排行,1表示获取默认),cusId:登录用户(如无登录用户值请传0)}AES加密后的结果")String params,HttpServletRequest req,HttpServletResponse response){
		String paramsLog = Util.aesDecrypt(params);
		Map<String,Object> jsonMap = CommonClass.getMapByJson(paramsLog);
		//获取宝马品牌id
		long brandId = Long.valueOf(jsonMap.get("brandId").toString());
		//获取内容类型
		int tp = Integer.parseInt(jsonMap.get("tp").toString());//0
		//获取是否有用户登录
		long cusId = Long.valueOf(jsonMap.get("cusId").toString());//14250
		PubVehiclemodel model = new PubVehiclemodel();
		PubBrand brand = new PubBrand();
		brand.setId(brandId);//默认宝马id  测试用(14600)
		model.setPubBrand(brand);
		List<PubVehiclemodel> modelList = pubModelService.queryModel(model, cusId,tp);
		JSONObject json = new JSONObject();
		json.put("modellist", modelList);
		sendDataResponse(response, json.toString());
	}

	/**
	 * 
	 * @Title: PubBrandController.java 
	 * @Package com.ennuova.app.controller 
	 * @Description: 获取具体车型(描述) 
	 * @author felix
	 * @date 2016年4月13日
	 * @version V1.0
	 */
	@RequestMapping(params = "method=getmodel",method=RequestMethod.POST)
	@ApiOperation(value = "获取具体车型",notes="{model:返回车型具体信心(车型对象具体参数参照getline)}")
	public void getModel(@ApiParam(value="请输入json{modelId:车型id}AES加密后的结果")String params,HttpServletRequest req,HttpServletResponse response){
		String paramsLog = Util.aesDecrypt(params);
		Map<String,Object> jsonMap = CommonClass.getMapByJson(paramsLog);
		//获取具体车型id
		long modelId = Long.valueOf(jsonMap.get("modelId").toString());
		//获取用户id
		long userId = Long.valueOf(jsonMap.get("customer").toString());
		PubVehiclemodel model = pubModelService.getModel(modelId,userId);//333300
		//更新浏览次数并保存浏览记录
		int i = pubModelService.updateBorrowNum(modelId);//333300
		System.out.println("result:"+i);
		JSONObject json = new JSONObject();
		json.put("model", model);
		System.out.println(json.toString());
		sendDataResponse(response, json.toString());
	}

	/**
	 * 
	 * @Title: PubBrandController.java 
	 * @Package com.ennuova.app.controller 
	 * @Description: 获取多媒体展厅图片数据(描述) 
	 * @author felix
	 * @date 2016年4月15日
	 * @version V1.0
	 */
	@RequestMapping(params = "method=showimg",method=RequestMethod.POST)
	@ApiOperation(value = "获取多媒体展厅图片数据",notes="{imgList:返回多媒体长廊集合,[id:图片id,fpath:图片路径(原图),ftype:鉴别字符(1来源车系2来源车型),ftypeid:所属类型(车系/车型)ID,thpath:缩略图路径]}")
	public void showImg(@ApiParam(value="请输入json{tp:对应类型(1车系/2车型),tpId:对应车系/车型id}AES加密后的结果")String params,HttpServletRequest req,HttpServletResponse response){
		String paramsLog = Util.aesDecrypt(params);
		Map<String,Object> jsonMap = CommonClass.getMapByJson(paramsLog);
		//获取对应类型(1车系/2车型)
		int tp = Integer.parseInt(jsonMap.get("tp").toString());
		//获取对应车系/车型id 
		long tpId = Long.valueOf(jsonMap.get("tpId").toString());
		List<CommonImages> imgList = ciService.queryImages(tp, tpId);//1,77300
		CommonVideos commonVideos = commonVideosService.queryVideos(tp, tpId);
		JSONObject json = new JSONObject();
		json.put("imgList", imgList);
		json.put("commonVideos", commonVideos);
		sendDataResponse(response, json.toString());
	}

	/**
	 * 
	 * @Title: PubBrandController.java 
	 * @Package com.ennuova.app.controller 
	 * @Description: 预约试驾(描述) 
	 * @author felix
	 * @date 2016年4月15日
	 * @version V1.0
	 */
	@RequestMapping(params = "method=reservedrive",method=RequestMethod.POST)
	@ApiOperation(value = "预约试驾",notes="{result:返回结果值(值为0失败,为1成功)}")
	public void reserveDrive(@ApiParam(value="请输入json{reserveName:预约人名称,reserveTel:预约人手机,reserveSex:预约人性别(1表示男,2表示女),reserveDate:预约时间,brandId:品牌ID,lineId:车系ID,vehiclemodelId:车型ID,"
			+ "brandName:品牌名称,lineName:车系名称,vehiclemodelName:车型名称,provinceId:省份ID,cityId:城市ID,provinceName:省份名称,cityName:城市名称,storeId:门店ID,storeName:门店名称}AES加密后的结果")String params,HttpServletRequest req,HttpServletResponse response) throws Exception{
		String paramsLog = Util.aesDecrypt(params);
		Map<String,Object> jsonMap = CommonClass.getMapByJson(paramsLog);
		String reserveName = jsonMap.get("reserveName").toString();//预约人名称
		String reserveTel = jsonMap.get("reserveTel").toString();//预约人手机
		String reserveSex = jsonMap.get("reserveSex").toString();//预约人性别(1表示男,2表示女)
		String reserveDate = jsonMap.get("reserveDate").toString();//预约时间
		long vehiclemodelId = Long.parseLong(jsonMap.get("vehiclemodelId").toString());//车型ID
		long provinceId = Long.parseLong(jsonMap.get("provinceId").toString());//省份ID
		long cityId = Long.parseLong(jsonMap.get("cityId").toString());//城市ID
		String provinceName = jsonMap.get("provinceName").toString();//省份名称
		String cityName = jsonMap.get("cityName").toString();//城市名称
		long storeId = Long.parseLong(jsonMap.get("storeId").toString());//门店ID
		String storeName = jsonMap.get("storeName").toString();//门店名称
		//封装实体对象
		ReserveDrive rd = new ReserveDrive();
		rd.setReserveName(reserveName);
		rd.setReserveTel(reserveTel);
		rd.setReserveSex(reserveSex);
		rd.setReserveState(0+"");//预约状态
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		Date date=sdf.parse(reserveDate);  
		rd.setReserveDate(date);
		rd.setVehiclemodelId(vehiclemodelId);
		rd.setProvinceId(provinceId);
		rd.setCityId(cityId);
		rd.setProvinceName(provinceName);
		rd.setCityName(cityName);
		rd.setStoreId(storeId);
		rd.setStoreName(storeName);
		int i = rDriveService.reserveDrive(rd);
		JSONObject json = new JSONObject();
		json.put("result", i);
		sendDataResponse(response, json.toString());
	}

}
