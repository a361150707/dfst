package com.ennuova.app.controller;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ennuova.app.config.AppObjectResult;
import com.ennuova.app.config.AppResult;
import com.ennuova.app.config.SystemInfo;
import com.ennuova.app.service.CarReserveService;
import com.ennuova.app.service.CnMsgInfoPerService;
import com.ennuova.app.service.ReserveMaintainService;
import com.ennuova.app.service.ReserveRemewalService;
import com.ennuova.app.service.ReserveRepairService;
import com.ennuova.app.service.ReserveService;
import com.ennuova.app.service.TSBaseUserService;
import com.ennuova.common.CommonClass;
import com.ennuova.controller.GeneralControl;
import com.ennuova.entity.CarReserveEntity;
import com.ennuova.entity.ReserveMaintain;
import com.ennuova.entity.ReserveRemewal;
import com.ennuova.entity.ReserveRepair;
import com.ennuova.entity.TSBaseUser;
import com.ennuova.util.DateUtil;
import com.ennuova.util.DateUtils;
import com.ennuova.util.JSONUtil;
import com.ennuova.util.JsonUtils;
import com.ennuova.util.StringUtil;
import com.ennuova.util.UrlUtil;
import com.ennuova.util.Util;
import com.ennuova.vo.MainTainVO;
import com.ennuova.vo.ReserveMaintainPlanVO;
import com.ennuova.vo.ReserveMaintainVO;
import com.ennuova.vo.UserCompanyVO;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

@Controller
@RequestMapping("/carReserveController")
@Api(value="", description="车辆预约")
public class CarReserveController extends GeneralControl{
	private static Logger logger = Logger.getLogger(CarReserveController.class);
	
	@Resource
	private  ReserveService reserveService;
	@Resource
	private TSBaseUserService tsBaseUserService;
	/**
	 * 预约保养服务
	 */
	@Resource
	private ReserveMaintainService reserveMaintainService;
	
	/**
	 * 预约维修服务
	 */
	@Resource
	private ReserveRepairService reserveRepairService;
	/**
	 * 预约续保
	 */
	@Resource
	private ReserveRemewalService reserveRemewalService;
	
	@Resource
	private CarReserveService carReserveService;
	
	/**
	 * 消息推送的服务
	 */
	@Resource
	private CnMsgInfoPerService cnMsgInfoPerService;
	
	/**
	 * （根据车辆ID）获取车辆保养方案和服务顾问名称
	 * @param params
	 * @param response
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(params="method=getMaintainPlay",method=RequestMethod.POST)
	@ApiOperation(value = "获取车辆保养方案和服务顾问名称",notes="{'carinfoId':车辆ID}")
	public String getMaintainPlay(@ApiParam(value="请输入json{'carinfoId':车辆ID}AES加密后的结果",name="params",defaultValue="8440001",required=true) String params,HttpServletResponse response) throws Exception {	
		String params_log = Util.aesDecrypt(params);
		ReserveMaintain reserveMaintain=(ReserveMaintain) JSONUtil.getDTO(params_log, ReserveMaintain.class);
		long carId =0;
		try {
			carId = reserveMaintain.getCarinfoId();
		} catch (Exception e) {
			AppResult appResult = new AppResult(SystemInfo.ILLEGAL_PARAMETER.getCode(), SystemInfo.ILLEGAL_PARAMETER.getMsg());
			sendDataResponse(response, JSONUtil.getJSONString(appResult));
			System.exit(0);
		}
		ReserveMaintainPlanVO v = reserveMaintainService.getReserveMaintainVO(carId);
		AppObjectResult appObjectResult = new AppObjectResult(SystemInfo.QUERY_SUCCESS.getCode(), SystemInfo.QUERY_SUCCESS.getMsg(), v);
		sendDataResponse(response,JSONUtil.getJSONString(appObjectResult));
		return null;
	}
	
	/**
	 * （根据车辆ID）获取服务顾问名称
	 * @param params
	 * @param response
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(params="method=getUserInfo",method=RequestMethod.POST)
	@ApiOperation(value = "（根据车辆ID）获取服务顾问名称",notes="{'carinfoId':车辆ID}")
	public String getUserInfo(@ApiParam(value="请输入json{'carinfoId':车辆ID}AES加密后的结果",name="params",defaultValue="8440001",required=true) String params,HttpServletResponse response) throws Exception {	
		String params_log = Util.aesDecrypt(params);
		ReserveMaintain reserveMaintain=(ReserveMaintain) JSONUtil.getDTO(params_log, ReserveMaintain.class);
		System.out.println("获取专属顾问");
		long carId =0;
		try {
			carId = reserveMaintain.getCarinfoId();
		} catch (Exception e) {
			AppResult appResult = new AppResult(SystemInfo.ILLEGAL_PARAMETER.getCode(), SystemInfo.ILLEGAL_PARAMETER.getMsg());
			sendDataResponse(response, JSONUtil.getJSONString(appResult));
			System.exit(0);
		}
		TSBaseUser v = tsBaseUserService.getTsBaseUser(carId);
		AppObjectResult appObjectResult = new AppObjectResult(SystemInfo.QUERY_SUCCESS.getCode(), SystemInfo.QUERY_SUCCESS.getMsg(), v);
		sendDataResponse(response,JSONUtil.getJSONString(appObjectResult));
		return null;
	}
	/**
	 * 车辆保养预约
	 * @param params
	 * @param response
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(params="method=addReserveMaintain",method=RequestMethod.POST)
	@ApiOperation(value = "预约车辆保养",notes="{'carinfoId':车辆ID,'userId':服务顾问ID,'customerId':用户ID,'reserveMaintainMilage':车辆里程数,'maintainPlayId':保养方案ID用逗号拼接在传,'maintainItemId':保养项目ID,'reserveName':预约人名称,'reserveTel':预约人手机,'reserveContent':内容,'reserveDate':预约时间,'reserveMaintainHomeCar':上门取车,'reserveAddress':地址}")
	public String addReserveMaintain(@ApiParam(value="请输入jsonAES加密后的结果",name="params",defaultValue="8440001",required=true)String params,HttpServletResponse response) throws Exception {	
		String params_log = Util.aesDecrypt(params);
		ReserveMaintainVO reserveMaintainVO = (ReserveMaintainVO) JSONUtil.getDTO(params_log, ReserveMaintainVO.class);
		AppResult result=reserveMaintainService.addReserveMaintain(reserveMaintainVO);
		sendDataResponse(response,JSONUtil.getJSONString(result));
		return null;
	}
	
	/**
	 * 车辆保养记录
	 * @param params
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params="method=getReserveMaintain",method=RequestMethod.POST)
	@ResponseBody
	@ApiOperation(value = "车辆保养记录",notes="{'carinfoId':车辆ID,'maxId':上次查询的最小ID}")
	public String getReserveMaintain(@ApiParam(value="请输入jsonAES加密后的结果",name="params",defaultValue="8440001",required=true)String params,HttpServletResponse response) throws Exception {	
		String content = Util.aesDecrypt(params);
		Map<String, Object> jMap = CommonClass.getMapByJson(content);
		Long carId = Long.valueOf(jMap.get("carinfoId").toString());
		Long maxId = Long.valueOf(jMap.get("maxId").toString());
		if(maxId==null||maxId==0)
			maxId = 999999999999L;
		List<MainTainVO> mainTainVOs =reserveMaintainService.getReserveMaintain(carId,maxId);
		AppObjectResult appObjectResult = new AppObjectResult(SystemInfo.QUERY_SUCCESS.getCode(), SystemInfo.QUERY_SUCCESS.getMsg(), mainTainVOs);
		sendDataResponse(response,JsonUtils.toJson(appObjectResult));
		return null;
	}
	/**
	 * 车辆保养明细
	 * @param params
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params="method=getReserveMaintainDetail",method=RequestMethod.POST)
	@ApiOperation(value = "车辆保养明细",notes="{'reserveMaintainId':车辆保养记录ID")
	public String getReserveMaintainDetail(@ApiParam(value="请输入jsonAES加密后的结果",name="params",defaultValue="8440001",required=true)String params,HttpServletResponse response) throws Exception {	
		String params_log = Util.aesDecrypt(params);
		ReserveMaintainVO reserveMaintainVO = (ReserveMaintainVO) JSONUtil.getDTO(params_log, ReserveMaintainVO.class);
		ReserveMaintain result=reserveMaintainService.getReserveMaintainDetail(reserveMaintainVO);
		
		sendDataResponse(response,JSONUtil.getJSONString(result));
		return null;
	}
	
	/**
	 * 车辆维修预约
	 * @param params
	 * @param response
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(params="method=addReserveRepair",method=RequestMethod.POST)
	@ApiOperation(value = "车辆维修预约",notes="{'carinfoId':车辆ID,'userId':服务顾问ID,'customerId':用户ID,'reserveName':预约人名称,'reserveTel':预约人手机,'reserveContent':内容,'reserveDate':预约时间,'reserveMaintainHomeCar':上门取车,'reserveAddress':地址}")
	public String addReserveRepair(@ApiParam(value="请输入jsonAES加密后的结果",name="params",defaultValue="8440001",required=true)String params,HttpServletResponse response) throws Exception {	
		String params_log = Util.aesDecrypt(params);
		ReserveRepair reserveRepair = (ReserveRepair) JSONUtil.getDTO(params_log, ReserveRepair.class);
		AppResult result=reserveRepairService.addReserveRepair(reserveRepair);
		sendDataResponse(response,JSONUtil.getJSONString(result));
		return null;
	}
	
	/**
	 * 车辆维修记录
	 * @param params
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params="method=getReserveRepair",method=RequestMethod.POST)
	@ResponseBody
	@ApiOperation(value = "车辆维修记录",notes="{'carinfoId':车辆ID,'pageNum':第几页,'pageSize':每页显示多少条记录}")
	public String getReserveRepair(@ApiParam(value="请输入jsonAES加密后的结果",name="params",defaultValue="8440001",required=true)String params,HttpServletResponse response) throws Exception {	
		String content = Util.aesDecrypt(params);
		Map<String, Object> jMap = CommonClass.getMapByJson(content);
		Long carId = Long.valueOf(jMap.get("carinfoId").toString());
		Long maxId = Long.valueOf(jMap.get("maxId").toString());
		if(maxId==null||maxId==0)
			maxId = 999999999999L;
		List<MainTainVO> reserveRepairs=reserveRepairService.getReserveRepair(carId,maxId);
		AppObjectResult appObjectResult = new AppObjectResult(SystemInfo.QUERY_SUCCESS.getCode(), SystemInfo.QUERY_SUCCESS.getMsg(), reserveRepairs);
		sendDataResponse(response,JSONUtil.getJSONString(appObjectResult));
		return null;
	}
	
	/**
	 * 车辆维修明细
	 * @param params
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params="method=getReserveRepairDetail",method=RequestMethod.POST)
	@ApiOperation(value = "车辆维修明细",notes="{'reserveRepairId':车辆维修记录ID")
	public String getReserveRepairDetail(@ApiParam(value="请输入jsonAES加密后的结果",name="params",defaultValue="8440001",required=true)String params,HttpServletResponse response) throws Exception {	
		String params_log = Util.aesDecrypt(params);
		ReserveRepair reserveRepair = (ReserveRepair) JSONUtil.getDTO(params_log, ReserveRepair.class);
		ReserveRepair result=reserveRepairService.getReserveRepairDetail(reserveRepair);
		sendDataResponse(response,JSONUtil.getJSONString(result));
		return null;
	}
	
	/**
	 * 根据车辆ID获取保险名称和服务顾问信息
	 * @param params
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params="method=getInscompanyAndUserByCarinfoId",method=RequestMethod.POST)
	@ApiOperation(value = "根据车辆ID获取保险名称和服务顾问信息",notes="{'carinfoId':车辆ID")
	public String getInscompanyAndUserByCarinfoId(@ApiParam(value="请输入jsonAES加密后的结果",name="params",defaultValue="8440001",required=true)String params,HttpServletResponse response) throws Exception {	
		String params_log = Util.aesDecrypt(params);
		ReserveMaintainVO reserveMaintainVO = (ReserveMaintainVO) JSONUtil.getDTO(params_log, ReserveMaintainVO.class);
		UserCompanyVO result=reserveRemewalService.getInscompanyAndUserByCarinfoId(reserveMaintainVO);
		sendDataResponse(response,JSONUtil.getJSONString(result));
		return null;
	}
	
	/**
	 * 车辆续保预约
	 * @param params
	 * @param response
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(params="method=addReserveRemewal",method=RequestMethod.POST)
	@ApiOperation(value = "车辆续保预约",notes="{'carinfoId':车辆ID,'userId':服务顾问ID,'customerId':用户ID,'reserveName':预约人名称,'reserveTel':预约人手机,'reserveContent':内容,'reserveDate':预约时间,'reserveMaintainHomeCar':上门取车,'reserveAddress':地址}")
	public String addReserveRemewal(@ApiParam(value="请输入jsonAES加密后的结果",name="params",defaultValue="8440001",required=true)String params,HttpServletResponse response) throws Exception {	
		String params_log = Util.aesDecrypt(params);
		ReserveRemewal reserveRemewal = (ReserveRemewal) JSONUtil.getDTO(params_log, ReserveRemewal.class);
		AppResult result=reserveRemewalService.addReserveRemewal(reserveRemewal);
		sendDataResponse(response,JSONUtil.getJSONString(result));
		return null;
	}
	/**
	 * 车辆续保记录
	 * @param params
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params="method=getReserveRemewal",method=RequestMethod.POST)
	@ApiOperation(value = "车辆续保记录",notes="{'carinfoId':车辆ID,'pageNum':第几页,'pageSize':每页显示多少条记录}")
	public String getReserveRemewal(@ApiParam(value="请输入jsonAES加密后的结果",name="params",defaultValue="8440001",required=true)String params,HttpServletResponse response) throws Exception {	
		String content = Util.aesDecrypt(params);
		Map<String, Object> jMap = CommonClass.getMapByJson(content);
		Long carId = Long.valueOf(jMap.get("carinfoId").toString());
		Long maxId = Long.valueOf(jMap.get("maxId").toString());
		if(maxId==null||maxId==0)
			maxId = 999999999999L;
		List<MainTainVO> mainTainVOs =reserveRemewalService.getReserveRemewal(carId,maxId);
		AppObjectResult appObjectResult = new AppObjectResult(SystemInfo.QUERY_SUCCESS.getCode(), SystemInfo.QUERY_SUCCESS.getMsg(), mainTainVOs);
		sendDataResponse(response,JSONUtil.getJSONString(appObjectResult));
		return null;
	}
	
	/**
	 * 车辆续保明细
	 * @param params
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params="method=getReserveRemewalDetail",method=RequestMethod.POST)
	@ApiOperation(value = "车辆续保明细",notes="{'reserveRemewalId':车辆维修记录ID")
	public String getReserveRemewalDetail(@ApiParam(value="请输入jsonAES加密后的结果",name="params",defaultValue="8440001",required=true)String params,HttpServletResponse response) throws Exception {	
		String params_log = Util.aesDecrypt(params);
		ReserveRemewal reserveRemewal = (ReserveRemewal) JSONUtil.getDTO(params_log, ReserveRemewal.class);
		ReserveRemewal result=reserveRemewalService.getReserveRemewalDetail(reserveRemewal);
		sendDataResponse(response,JSONUtil.getJSONString(result));
		return null;
	}
	
	/**
	 * 取消车辆预约
	 * @param params
	 * @param response
	 * @return
	 * @throws Exception 
	 */
	/*@RequestMapping(params="method=updateReserve",method=RequestMethod.POST)
	@ApiOperation(value = "取消车辆预约",notes="{'reserveId':预约ID,'type':0表示保养预约1维修预约2续保预约")
	public String updateReserve(@ApiParam(value="请输入jsonAES加密后的结果",name="params",defaultValue="8440001",required=true)String params,HttpServletResponse response) throws Exception {	
		String params_log = Util.aesDecrypt(params);
		Map<String, Object> jsonMap=CommonClass.getMapByJson(params_log);
		Long  reserveId=Long.valueOf(jsonMap.get("reserveId").toString());
		int type=Integer.parseInt(jsonMap.get("type").toString());
		ReserveMaintainVO reserveMaintainVO = (ReserveMaintainVO) JSONUtil.getDTO(params_log, ReserveMaintainVO.class);
		AppResult result=reserveService.updateReserve(reserveId,type);
		sendDataResponse(response,JSONUtil.getJSONString(result));
		return null;
	}*/
	
	/**
	 * 增加除了道路救援的预约记录
	 *@author sududa
	 *@date 2016年9月14日
	 * @param params
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(params="method=doAdd",method=RequestMethod.POST)
	@ResponseBody
	@ApiOperation(value = "新增预约",notes="{fcusId:客户id,fcarId:车辆id,reserveType:预约类型,reserveDate:预约时间,isHome:是否上门取车Y_是,N_否,homeAddress:取车地址,remarks:备注,userId:用户id,cusName:客户名称,cusTel:客户手机号,carNumber:车牌号,carModel:车型,headImg:头像,nick:昵称}")
	public void doAdd(@ApiParam(value="输入{fcusId:客户id,fcarId:车辆id,reserveType:预约类型,reserveDate:预约时间,isHome:是否上门取车Y_是,N_否,homeAddress:取车地址,remarks:备注,userId:用户id,cusName:客户名称,cusTel:客户手机号,carNumber:车牌号,carModel:车型,headImg:头像,nick:昵称}")String params,HttpServletResponse response) throws Exception {	
		String getParams = Util.aesDecrypt(params);
		Map<String, Object> jsonMap = CommonClass.getMapByJson(getParams);
		String reserveDate = jsonMap.get("reserveDate")+"";
		String headImg = jsonMap.get("headImg")+"";//头像
		String nick = jsonMap.get("nick")+"";//昵称
		String cusTel = jsonMap.get("cusTel")+"";//昵称
		String reserveType = jsonMap.get("reserveType")+"";//预约类型
		if(StringUtil.isEmpty(nick)){//若昵称为空，则改为手机
			nick = cusTel;
		}
		CarReserveEntity carReserveEntity = (CarReserveEntity) JSONUtil.getDTO(getParams, CarReserveEntity.class);
		AppObjectResult appObjectResult = null;
		if(StringUtil.isNotEmpty(carReserveEntity)){
			if(StringUtil.isNotEmpty(reserveDate)){
				carReserveEntity.setReserveDate(DateUtils.parseDate(reserveDate));
			}else{
				Date createDate = DateUtil.getCurrentDate();
				carReserveEntity.setReserveDate(createDate);
			}
			Integer resultInt = carReserveService.saveReserveWithStateAndStaff(carReserveEntity);
			if(resultInt > 0){
				String content = nick+"预约了一条记录,请尽快处理";
				String receiveId = carReserveEntity.getUserId()+"";
				String sender = carReserveEntity.getFcusId()+"";
				cnMsgInfoPerService.savaMsg(receiveId, 1, sender, 0, content, 2, Long.valueOf(resultInt), headImg, nick,Integer.valueOf(reserveType),0);
			}
			appObjectResult = new AppObjectResult(SystemInfo.ADD_SUCCESS.getCode(), SystemInfo.ADD_SUCCESS.getMsg());
		}else{
			appObjectResult = new AppObjectResult(SystemInfo.ILLEGAL_PARAMETER.getCode(), SystemInfo.ILLEGAL_PARAMETER.getMsg());
		}
		sendDataResponse(response,JSONUtil.getJSONString(appObjectResult));
	}
	
	/**
	 * 取消预约的操作
	 *@author sududa
	 *@date 2016年9月14日
	 * @param params
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(params="method=doCancleReserve",method=RequestMethod.POST)
	@ResponseBody
	@ApiOperation(value = "取消预约",notes="{cusId:客户id,reserveId:预约id,reserveState:预约状态,remarks:备注}")
	public void doCancleReserve(@ApiParam(value="输入{cusId:客户id}")String params,HttpServletResponse response){	
		try {
			String getParams = Util.aesDecrypt(params);
			Map<String, Object> jsonMap = CommonClass.getMapByJson(getParams);
			String cusId = jsonMap.get("cusId")+"".trim();
			String reserveId = jsonMap.get("reserveId")+"".trim();
			String remarks = jsonMap.get("remarks")+"".trim();
//			String cusId = "32";
//			String reserveId = "1452";
//			String remarks = "新的测试";
			AppObjectResult appObjectResult = null;
			if(StringUtil.isNotEmpty(cusId) && StringUtil.isNotEmpty(reserveId)){
				Integer updateState = 3;//表示已取消
				Integer result = carReserveService.doCancleReserve(cusId,reserveId,remarks,updateState);
				if(result > 0){
					appObjectResult = new AppObjectResult(SystemInfo.PLAY_SUCCESS.getCode(), SystemInfo.PLAY_SUCCESS.getMsg());
				}else{
					appObjectResult = new AppObjectResult(SystemInfo.PLAY_FAIL.getCode(), SystemInfo.PLAY_FAIL.getMsg());
				}
			}else{
				appObjectResult = new AppObjectResult(SystemInfo.ILLEGAL_PARAMETER.getCode(), SystemInfo.ILLEGAL_PARAMETER.getMsg());
			}
			sendDataResponse(response,JSONUtil.getJSONString(appObjectResult));
		} catch (Exception e) {
			logger.error("车辆预约控制类_取消预约:"+e.getMessage());
			e.printStackTrace();
		}
	}
	
	/**
	 * 客户预约的历史记录
	 *@author sududa
	 *@date 2016年9月14日
	 * @param params
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(params="method=doList",method=RequestMethod.POST)
	@ResponseBody
	@ApiOperation(value = "客户预约的历史记录",notes="{cusId:客户id,reserveType:预约类型,page:当前页(默认值为1),rows:每页显示的条数(默认值为10)}")
	public void doList(@ApiParam(value="输入{cusId:客户id,reserveType:预约类型,page:当前页(默认值为1),rows:每页显示的条数(默认值为10)}")String params,HttpServletResponse response){	
		try {
			String getParams = Util.aesDecrypt(params);
			Map<String, Object> jsonMap = CommonClass.getMapByJson(getParams);
			String cusId = jsonMap.get("cusId")+"".trim();
			String reserveType = jsonMap.get("reserveType")+"".trim();
			String getPage = jsonMap.get("page")+"".trim();
			String getRows = jsonMap.get("rows")+"".trim();
			Integer page = 1;
			Integer rows = 10;
			if(StringUtil.isNotEmpty(getPage)){
				page = Integer.valueOf(getPage);
			}
			if(StringUtil.isNotEmpty(getRows)){
				rows = Integer.valueOf(getRows);
			}
			AppObjectResult appObjectResult = null;
//			String cusId = "32";
//			String reserveType = "";
			if(StringUtil.isNotEmpty(cusId)){
				List<Map<String, Object>> resultList = carReserveService.findListByCusIdAndReserveType(cusId,reserveType,page,rows);
				/*if(StringUtil.isNotEmpty(resultList) && resultList.size() > 0){
					for(Map<String, Object> map : resultList){
						if(StringUtil.isNotEmpty(map)){
							String linePicture = map.get("linePicture")+"";
							if(StringUtil.isNotEmpty(linePicture)){
								String imgUrl = UrlUtil.getInstance().getImgurl();
								map.put("linePicture", imgUrl+linePicture);
							}
						}
					}
				}*/
				appObjectResult = new AppObjectResult(SystemInfo.QUERY_SUCCESS.getCode(), SystemInfo.QUERY_SUCCESS.getMsg(), resultList);
			}else{
				appObjectResult = new AppObjectResult(SystemInfo.ILLEGAL_PARAMETER.getCode(), SystemInfo.ILLEGAL_PARAMETER.getMsg());
			}
			sendDataResponse(response,JSONUtil.getJSONString(appObjectResult));
		} catch (Exception e) {
			logger.error("车辆预约控制类_客户预约的历史记录:"+e.getMessage());
			e.printStackTrace();
		}
	}
	
	/**
	 * 进入预约界面的信息初始化
	 *@author sududa
	 *@date 2016年9月18日
	 * @param params
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(params="method=doInitData",method=RequestMethod.POST)
	@ResponseBody
	@ApiOperation(value = "进入预约界面的信息初始化",notes="{cusId:客户id,userId:管家id}")
	public void doInitData(@ApiParam(value="输入{cusId:客户id,userId:管家id}")String params,HttpServletResponse response) throws Exception {	
		String getParams = Util.aesDecrypt(params);
		Map<String, Object> jsonMap = CommonClass.getMapByJson(getParams);
		String cusId = jsonMap.get("cusId")+"".trim();
		String userId = jsonMap.get("userId")+"".trim();
//		String cusId = "32";
//		String userId = "a47ac58a573bc87a01573bd14e810007";
		AppObjectResult appObjectResult = null;
		if(StringUtil.isNotEmpty(cusId)){
			Map<String, Object> initMap = carReserveService.findInitMap(cusId);
			if(StringUtil.isNotEmpty(initMap)){
				/*String fsypicture = initMap.get("FSYPICTURE")+"";
				String flogo = initMap.get("FLOGO")+"";
				String fbackpc = initMap.get("FBACKPC")+"";
				if(StringUtil.isNotEmpty(fsypicture)){
					initMap.put("FSYPICTURE", imgUrl + fsypicture);
				}
				if(StringUtil.isNotEmpty(flogo)){
					initMap.put("FLOGO", imgUrl + flogo);
				}
				if(StringUtil.isNotEmpty(fbackpc)){
					initMap.put("FBACKPC", imgUrl + fbackpc);
				}*/
				Map<String, Object> manageMap = null;
				if(StringUtil.isNotEmpty(userId)){
					manageMap = carReserveService.findUserByCusId(userId);
				}
				initMap.put("manageMap", manageMap);
			}
			appObjectResult = new AppObjectResult(SystemInfo.QUERY_SUCCESS.getCode(), SystemInfo.QUERY_SUCCESS.getMsg(), initMap);
		}else{
			appObjectResult = new AppObjectResult(SystemInfo.ILLEGAL_PARAMETER.getCode(), SystemInfo.ILLEGAL_PARAMETER.getMsg());
		}
		sendDataResponse(response,JSONUtil.getJSONString(appObjectResult));
	}
	/**
	 * 根据客户id查询服务顾问(SA),管家(CRM),销售顾问(SC)列表
	 *@author sududa
	 *@date 2016年9月18日
	 * @param params
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(params="method=doSaList",method=RequestMethod.POST)
	@ResponseBody
	@ApiOperation(value = "根据客户id查询服务顾问(SA),管家(CRM),销售顾问(SC)列表",notes="{cusId:客户id,roleType:角色类型——服务顾问(SA),管家(CRM),销售顾问(SC),page:当前页(默认值为1),rows:每页显示的条数(默认值为10)}")
	public void doSaList(@ApiParam(value="输入{cusId:客户id,roleType:角色类型——服务顾问(SA),管家(CRM),销售顾问(SC),page:当前页(默认值为1),rows:每页显示的条数(默认值为10)}")String params,HttpServletResponse response) throws Exception {	
		String getParams = Util.aesDecrypt(params);
		Map<String, Object> jsonMap = CommonClass.getMapByJson(getParams);
		String cusId = jsonMap.get("cusId")+"".trim();
		String getPage = jsonMap.get("page")+"".trim();
		String getRows = jsonMap.get("rows")+"".trim();
		String roleType = jsonMap.get("roleType")+"".trim();//角色类型
		Integer page = 1;
		Integer rows = 10;
		if(StringUtil.isNotEmpty(getPage)){
			page = Integer.valueOf(getPage);
		}
		if(StringUtil.isNotEmpty(getRows)){
			rows = Integer.valueOf(getRows);
		}
//		String cusId = "145";
//		String roleType = "";
		AppObjectResult appObjectResult = null;
		if(StringUtil.isNotEmpty(cusId)){
			List<Map<String, Object>> saList = carReserveService.findsaList(cusId,roleType,page,rows);
			appObjectResult = new AppObjectResult(SystemInfo.QUERY_SUCCESS.getCode(), SystemInfo.QUERY_SUCCESS.getMsg(), saList);
		}else{
			appObjectResult = new AppObjectResult(SystemInfo.ILLEGAL_PARAMETER.getCode(), SystemInfo.ILLEGAL_PARAMETER.getMsg());
		}
		sendDataResponse(response,JSONUtil.getJSONString(appObjectResult));
	}
	
	/**
	 * 查询客户预约的订单信息详情
	 *@author sududa
	 *@date 2016年9月19日
	 * @param params
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(params="method=findReserveOrder",method=RequestMethod.POST)
	@ResponseBody
	@ApiOperation(value = "查询客户预约的订单信息详情",notes="{reserveId:预约id,reserveType:预约类型}")
	public void findReserveOrder(@ApiParam(value="输入{reserveId:预约id,reserveType:预约类型}")String params,HttpServletResponse response) throws Exception {	
		String getParams = Util.aesDecrypt(params);
		Map<String, Object> jsonMap = CommonClass.getMapByJson(getParams);
		String reserveId = jsonMap.get("reserveId")+"".trim();
		String reserveType = jsonMap.get("reserveType")+"".trim();
//		String cusId = "254";
		AppObjectResult appObjectResult = null;
		if(StringUtil.isNotEmpty(reserveId) && StringUtil.isNotEmpty(reserveType)){
			List<Map<String, Object>> orderList = carReserveService.findReserveOrderList(reserveId,reserveType);
			appObjectResult = new AppObjectResult(SystemInfo.QUERY_SUCCESS.getCode(), SystemInfo.QUERY_SUCCESS.getMsg(), orderList);
		}else{
			appObjectResult = new AppObjectResult(SystemInfo.ILLEGAL_PARAMETER.getCode(), SystemInfo.ILLEGAL_PARAMETER.getMsg());
		}
		sendDataResponse(response,JSONUtil.getJSONString(appObjectResult));
	}
	
	/**
	 * 根据门店id查询服务顾问(SA),管家(CRM),销售顾问(SC)列表
	 *@author sududa
	 *@date 2016年9月23日
	 * @param params
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(params="method=doUserList",method=RequestMethod.POST)
	@ResponseBody
	@ApiOperation(value = "根据门店id查询服务顾问(SA),管家(CRM),销售顾问(SC)列表",notes="{storeId:门店id,roleType:角色类型——服务顾问(SA),管家(CRM),销售顾问(SC),page:当前页(默认值为1),rows:每页显示的条数(默认值为10)}")
	public void doUserList(@ApiParam(value="输入{storeId:门店id,roleType:角色类型——服务顾问(SA),管家(CRM),销售顾问(SC),page:当前页(默认值为1),rows:每页显示的条数(默认值为10)}")String params,HttpServletResponse response) throws Exception {	
		String getParams = Util.aesDecrypt(params);
		Map<String, Object> jsonMap = CommonClass.getMapByJson(getParams);
		String storeId = jsonMap.get("storeId")+"".trim();
		String getPage = jsonMap.get("page")+"".trim();
		String getRows = jsonMap.get("rows")+"".trim();
		String roleType = jsonMap.get("roleType")+"".trim();//角色类型
		Integer page = 1;
		Integer rows = 10;
		if(StringUtil.isNotEmpty(getPage)){
			page = Integer.valueOf(getPage);
		}
		if(StringUtil.isNotEmpty(getRows)){
			rows = Integer.valueOf(getRows);
		}
//		String storeId = "1";
//		String roleType = "SA";
		AppObjectResult appObjectResult = null;
		if(StringUtil.isNotEmpty(storeId)){
			List<Map<String, Object>> saList = carReserveService.findUserListByStoreIdAndroleType(storeId,roleType,page,rows);
			appObjectResult = new AppObjectResult(SystemInfo.QUERY_SUCCESS.getCode(), SystemInfo.QUERY_SUCCESS.getMsg(), saList);
		}else{
			appObjectResult = new AppObjectResult(SystemInfo.ILLEGAL_PARAMETER.getCode(), SystemInfo.ILLEGAL_PARAMETER.getMsg());
		}
		sendDataResponse(response,JSONUtil.getJSONString(appObjectResult));
	}
	
	
	@RequestMapping(params="method=doDelete",method=RequestMethod.POST)
	@ResponseBody
	@ApiOperation(value = "删除预约订单",notes="{reserveId:预约ID}")
	public void doDelete(@ApiParam(value="输入{reserveId:预约ID}")String params,HttpServletResponse response) throws Exception {	
		String getParams = Util.aesDecrypt(params);
		Map<String, Object> jsonMap = CommonClass.getMapByJson(getParams);
		String reserveId = jsonMap.get("reserveId")+"".trim();
		AppObjectResult appObjectResult = null;
		if(StringUtil.isNotEmpty(reserveId)){
			carReserveService.doDelete(reserveId, 1);
			appObjectResult = new AppObjectResult(SystemInfo.QUERY_SUCCESS.getCode(), SystemInfo.QUERY_SUCCESS.getMsg());
		}else{
			appObjectResult = new AppObjectResult(SystemInfo.ILLEGAL_PARAMETER.getCode(), SystemInfo.ILLEGAL_PARAMETER.getMsg());
		}
		sendDataResponse(response,JSONUtil.getJSONString(appObjectResult));
	}
	
	
	
	
	
	
}
