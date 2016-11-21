package com.ennuova.obd.service.impl;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ennuova.app.service.CnInfoService;
import com.ennuova.dao.CarInfoDao;
import com.ennuova.dao.CnBjrecodeDao;
import com.ennuova.dao.CnGzdetailDao;
import com.ennuova.dao.CnGzmDao;
import com.ennuova.dao.CnGzrecodeDao;
import com.ennuova.dao.CnInfoDao;
import com.ennuova.dao.CnSbljinfoDao;
import com.ennuova.dao.CnSsclxxDao;
import com.ennuova.dao.CnSsxcrecodeDao;
import com.ennuova.dao.CnXcrecodeDao;
import com.ennuova.dao.PubAlarmsettingDao;
import com.ennuova.dao.PubBoxDao;
import com.ennuova.dao.PubDatalmbDao;
import com.ennuova.entity.CnBjrecode;
import com.ennuova.entity.CnDiagsoft;
import com.ennuova.entity.CnGzdetail;
import com.ennuova.entity.CnGzm;
import com.ennuova.entity.CnGzrecode;
import com.ennuova.entity.CnSbljinfo;
import com.ennuova.entity.CnSsclxx;
import com.ennuova.entity.CnSsxcrecode;
import com.ennuova.entity.CnXcrecode;
import com.ennuova.entity.PubAlarmsetting;
import com.ennuova.entity.PubBox;
import com.ennuova.entity.PubCarinfo;
import com.ennuova.entity.PubDatalmb;
import com.ennuova.obd.message.DataMessage;
import com.ennuova.obd.message.LoginMessage;
import com.ennuova.obd.message.LoginResMessage;
import com.ennuova.obd.service.OdbLoginService;
import com.ennuova.obd.thread.ServerThread;
import com.ennuova.obd.util.DyMethodUtil;
import com.ennuova.obd.util.LogUtil;
import com.ennuova.obd.util.StringByteUtil;
import com.ennuova.obd.util.Tools;
import com.ennuova.util.URLRequest;

/**
 * Odb登录serviceimpl
 * 
 * @author janson
 */
@Service("odbLoginService")
public class OdbLoginServiceImpl implements OdbLoginService {
	
	Logger logger=LoggerFactory.getLogger("mylogger1");

	@Resource
	private CnSbljinfoDao cnSbljinfoDao;// 设备链接信息dao
	@Resource
	private PubBoxDao pubBoxDao;// 盒子dao
	@Resource
	private CnXcrecodeDao cnXcrecodeDao;// 行程管理dao
	@Resource
	private CnSsxcrecodeDao cnSsxcrecodeDao;// 实时行程管理dao
	@Resource
	private CnSsclxxDao cnSsclxxDao;// 实时车辆信息管理dao
	@Resource
	private CnGzrecodeDao cnGzrecodeDao;// 故障码记录dao
	@Resource
	private CnGzdetailDao cnGzdetailDao;// 故障码记录明细dao
	@Resource
	private CnGzmDao cnGzmDao;// 故障码表dao
	@Resource
	private CnBjrecodeDao cnBjrecodeDao;// 报警记录dao
	@Resource
	private PubAlarmsettingDao pubAlarmsettingDao;// 报警设置dao
	@Resource
	private PubDatalmbDao pubDatalmbDao;// 数据流dao
	@Resource
	private CarInfoDao carInfoDao;// 车辆信息dao
	@Resource
	private CnInfoService cnInfoService; //消息dao

	/**
	 * odb登录
	 */
	public boolean login(LoginMessage loginMessage,
			LoginResMessage loginResMessage, String keyString, String ip,
			long port) {

		boolean res = false;
		try {
			// 设备序列号
			String fxlh = StringByteUtil.AscIItoString(loginMessage
					.getSerialNumber());
			String imei = StringByteUtil.AscIItoString(loginMessage.getIMEI());

			// 判断有没有绑定
			PubBox pubBox = pubBoxDao.findByFxlh(fxlh);

			// 判断有没有登录过
			CnSbljinfo cnSbljinfo = cnSbljinfoDao.findByFsbxlhAndFsbmiei(fxlh,
					imei);
			
			if (cnSbljinfo == null) {

				System.out.println("新增登录信息");

				// 没有登录过：注册

				cnSbljinfo = new CnSbljinfo();
				
				//共有信息
				cnSbljinfo.setFrameid(loginMessage.getFrameId());
				cnSbljinfo.setFramelength(loginMessage.getFramelength());
				cnSbljinfo.setDataid(loginMessage.getDataId());
				cnSbljinfo.setMessageid(loginMessage.getMessageId());
				cnSbljinfo.setExtfield(loginMessage.getExtfield());
				cnSbljinfo.setProtocolno(loginMessage.getProtocolNo());
				cnSbljinfo.setTimestamp(loginMessage.getTimeStamp());
				cnSbljinfo.setParitybit(loginMessage.getParityBit());
				
				// 密钥
				cnSbljinfo.setFmy(keyString);

				// 设备序列号
				cnSbljinfo.setFsbxlh(fxlh);
				// ak
				cnSbljinfo.setFak(loginMessage.getaK());

				// 公钥
				cnSbljinfo.setFgy(loginMessage.getKey());
				// 响应私钥
				cnSbljinfo.setFxysy(loginResMessage.getToken());

				// 设备IMEI码
				cnSbljinfo.setFsbimei(imei);
				// SIM卡号
				cnSbljinfo.setFsim(StringByteUtil.AscIItoString(loginMessage
						.getSIM()));
				// 硬件版本号
				cnSbljinfo.setFyjedition(StringByteUtil
						.AscIItoString(loginMessage.getHardVersion()));
				// 硬件版本号
				cnSbljinfo.setFedition(StringByteUtil
						.AscIItoString(loginMessage.getSoftVersion()));
				// 请求时间戳
				cnSbljinfo.setFasksjc(new Timestamp(StringByteUtil
						.parseLong(loginMessage.getTimeStamp()) * 1000));
				// 响应 时间戳
				cnSbljinfo.setFxysjc(new Timestamp(StringByteUtil
						.parseLong(loginResMessage.getTimeStamp()) * 1000));
				// 校验位
				cnSbljinfo.setFjyw(loginMessage.getParityBit());
				// 车辆ID
				if (pubBox != null && pubBox.getPubCarinfo() != null) {
					cnSbljinfo.setFclid(pubBox.getPubCarinfo().getId());
				}
				// 登陆IP
				cnSbljinfo.setFloginip(ip);
				// 登陆端口
				cnSbljinfo.setFloginport(port);

				// 保存信息
				cnSbljinfoDao.save(cnSbljinfo);

			} else {
				// 登录过：更新
				System.out.println("更新登录信息");

				//共有信息
				cnSbljinfo.setFrameid(loginMessage.getFrameId());
				cnSbljinfo.setFramelength(loginMessage.getFramelength());
				cnSbljinfo.setDataid(loginMessage.getDataId());
				cnSbljinfo.setMessageid(loginMessage.getMessageId());
				cnSbljinfo.setExtfield(loginMessage.getExtfield());
				cnSbljinfo.setProtocolno(loginMessage.getProtocolNo());
				cnSbljinfo.setTimestamp(loginMessage.getTimeStamp());
				cnSbljinfo.setParitybit(loginMessage.getParityBit());
				
				// 密钥
				cnSbljinfo.setFmy(keyString);
				// ak
				cnSbljinfo.setFak(loginMessage.getaK());

				// 公钥
				cnSbljinfo.setFgy(loginMessage.getKey());
				// 响应私钥
				cnSbljinfo.setFxysy(loginResMessage.getToken());

				// SIM卡号
				cnSbljinfo.setFsim(StringByteUtil.AscIItoString(loginMessage
						.getSIM()));
				// 硬件版本号
				cnSbljinfo.setFyjedition(StringByteUtil
						.AscIItoString(loginMessage.getHardVersion()));
				// 硬件版本号
				cnSbljinfo.setFedition(StringByteUtil
						.AscIItoString(loginMessage.getSoftVersion()));
				// 请求 时间戳
				cnSbljinfo.setFasksjc(new Timestamp(StringByteUtil
						.parseLong(loginMessage.getTimeStamp()) * 1000));
				// 响应 时间戳
				cnSbljinfo.setFxysjc(new Timestamp(StringByteUtil
						.parseLong(loginResMessage.getTimeStamp()) * 1000));
				// 校验位
				cnSbljinfo.setFjyw(loginMessage.getParityBit());
				// 车辆ID
				if (pubBox != null && pubBox.getPubCarinfo() != null) {
					cnSbljinfo.setFclid(pubBox.getPubCarinfo().getId());
				}
				// 登陆IP
				cnSbljinfo.setFloginip(ip);
				// 登陆端口
				cnSbljinfo.setFloginport(port);

				// 更新
				cnSbljinfoDao.update(cnSbljinfo);
			}

			if (pubBox != null) {

				System.out.println("更新盒子信息");
				
				// 硬件版本号
				pubBox.setFyjedition(StringByteUtil.AscIItoString(loginMessage
						.getHardVersion()));
				// 硬件版本号
				pubBox.setFedition(StringByteUtil.AscIItoString(loginMessage
						.getSoftVersion()));

				// 更新盒子信息
				pubBoxDao.update(pubBox);
			}

			res = true;
		} catch (Exception e) {
			LogUtil.appendLog("登录解析出错--》" + e.getMessage());
			System.out.println("登录解析出错--》" + e.getMessage());
			res = false;
		}

		return res;
	}

	/**
	 * 根据设备号查询Odb识别码
	 * 
	 * @param serialNumber
	 * @return
	 */
	public CnDiagsoft getOdbModelBySerialNumber(String serialNumber) {
		// 查询盒子信息获取车辆信息
		// 设备序列号
		String fxlh = StringByteUtil.AscIItoString(serialNumber);
		System.out.println("根据设备序列号--》" + fxlh);
		PubBox pubBox = null;

		CnDiagsoft cnDiagsoft = null;

		// 判断有没有绑定
		if (fxlh != null)
			pubBox = pubBoxDao.findByFxlh(fxlh);
		//根据盒子信息查询车型对照表
		if (pubBox != null)
			cnDiagsoft = pubBoxDao.getCnDiagsoft(pubBox);

		return cnDiagsoft;
	}
	
	/**
	 * 根据设备号查询Odb识别码
	 * 
	 * @param serialNumber
	 * @return
	 */
	public PubBox getPubBoxBySerialNumber(String serialNumber) {
		// 查询盒子信息获取车辆信息
		// 设备序列号
		String fxlh = StringByteUtil.AscIItoString(serialNumber);
		System.out.println("根据设备序列号--》" + fxlh);
		PubBox pubBox = null;

		// 判断有没有绑定
		if (fxlh != null)
			pubBox = pubBoxDao.findByFxlh(fxlh);

		return pubBox;
	}

	/**
	 * 根据IP和端口号
	 * 
	 * @param serialNumber
	 * @return
	 */
	public CnSbljinfo findCnSbljinfoByIpAndPort(String ip, int port) {
		return cnSbljinfoDao.findByIpAndPort(ip, port);
	}

	/**
	 * 行程记录管理
	 * 
	 * @param dataMessage
	 * @param cnSbljinfo
	 * @return
	 */
	public boolean travelRecord(DataMessage dataMessage, CnSbljinfo cnSbljinfo) {

		System.out.println("行程记录管理！");
		
		String serialNumber=cnSbljinfo.getFsbxlh();

		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String now = df.format(new Date());
		Timestamp nowtime = Timestamp.valueOf(now);

		
		boolean res = false;
		// 判断是否有车辆ID
		PubBox pubBox = pubBoxDao.findByFxlh(cnSbljinfo.getFsbxlh());
		if (pubBox!=null&&pubBox.getPubCarinfo() != null) {

			// 车辆信息
			PubCarinfo pubCarinfo = pubBox.getPubCarinfo();
			try {

				
				// 车辆ID
				Long carId = pubCarinfo.getId();

				// 内容
				String data = dataMessage.getData();

				// 判断是开始行程还是结束行程
				String flag = data.substring(0, 2);
				// 行程序号
				String fxcxh = data.substring(2, 10);

				System.out.println("行程标志--》" + flag);
				if (flag.equals("01")) {
					// 开始

					// 构造数据
					CnXcrecode cnXcrecode = new CnXcrecode();

					// 行程序号
					cnXcrecode.setFxcxh(fxcxh);

					// 开始行程标志
					cnXcrecode.setFbeginflag(1L);
					// 结束行程标志
					cnXcrecode.setFendflag(0L);
					
					// 行程记录时间
					String timeString = data.substring(10, 18);
					Long timeLong = StringByteUtil.parseLong(timeString) * 1000;
					Timestamp ts = new Timestamp(timeLong);

					// 开始时间
					cnXcrecode.setFbegintime(ts);

					cnXcrecode.setPubCarinfo(pubCarinfo);

					// 登记时间
					cnXcrecode.setFcreatetime(nowtime);

					cnXcrecode = cnXcrecodeDao.save(cnXcrecode);

					
					logger.warn("盒子序列号:{} 开始行程管理:{}",serialNumber,cnXcrecode.toString());
//					LogUtil.appendLog("开始行程管理保存成功--》" + timeString + " "
//							+ timeLong + " " + ts);
//					System.out.println("开始行程管理保存成功");
					
					//添加盒子提醒
					cnInfoService.addMsg(pubBox,"FDXX001");

				} else if (flag.equals("02")) {

					// 结束
					// 根据行程序号查询
					CnXcrecode cnXcrecode = cnXcrecodeDao.findByXcxhAndcarId(
							carId, fxcxh);
					if (cnXcrecode != null) {

						// 开始经度
						double fbeginjd = StringByteUtil.parseLong(data
								.substring(18, 26))  / 10000.0 / 60.0;
						// 开始纬度
						double fbeginwd = StringByteUtil.parseLong(data
								.substring(26, 34))/ 10000.0 / 60.0;
						// 结束经度
						double fendjd = StringByteUtil.parseLong(data
								.substring(34, 42))  / 10000.0 / 60.0;
						// 结束纬度
						double fendwd = StringByteUtil.parseLong(data
								.substring(42, 50))  / 10000.0 / 60.0;

						String content = URLRequest
								.requestURL("http://api.map.baidu.com/ag/coord/convert?from=0&to=4&x="
										+ fbeginjd + "&y=" + fbeginwd);
						System.out.println("经纬度转化--》" + content);
						JSONObject jsonObject = JSONObject.fromObject(content);
						if (Integer
								.parseInt(jsonObject.get("error").toString()) == 0) {
							fbeginjd = Double.parseDouble(Tools.decodeBase64(
									jsonObject.get("x").toString(), "utf-8"));
							fbeginwd = Double.parseDouble(Tools.decodeBase64(
									jsonObject.get("y").toString(), "utf-8"));
						}

						content = URLRequest
								.requestURL("http://api.map.baidu.com/ag/coord/convert?from=0&to=4&x="
										+ fendjd + "&y=" + fendwd);
						System.out.println("经纬度转化--》" + content);
						jsonObject = JSONObject.fromObject(content);
						if (Integer
								.parseInt(jsonObject.get("error").toString()) == 0) {
							fendjd = Double.parseDouble(Tools.decodeBase64(
									jsonObject.get("x").toString(), "utf-8"));
							fendwd = Double.parseDouble(Tools.decodeBase64(
									jsonObject.get("y").toString(), "utf-8"));
						}
						
						/*
						if(cnXcrecode.getFbeginjd()==null||cnXcrecode.getFbeginwd()==null||cnXcrecode.getFbeginjd()==0||cnXcrecode.getFbeginwd()==0){
							String selectTime = dateFormat.format(new Date(cnXcrecode.getFbegintime().getTime()+10*60*1000));
							String selectTimeFiveAgo = dateFormat.format(cnXcrecode.getFbegintime());
							CnSsxcrecode cnSsxcrecode = cnSsxcrecodeDao.getXcrecodeLBSByBegin(fclid, selectTime, selectTimeFiveAgo);
							if(cnSsxcrecode.getFjd()!=null){
								cnXcrecode.setFbeginjd(cnSsxcrecode.getFjd());
								cnXcrecode.setFbeginwd(cnSsxcrecode.getFwd());
							}
						}*/
					/*	if(cnXcrecode.getFendjd()==null||cnXcrecode.getFendwd()==null||cnXcrecode.getFendjd()==0||cnXcrecode.getFendwd()==0){
							String selectTime = dateFormat.format(cnXcrecode.getFendtime());
							String selectTimeFiveAgo = dateFormat.format(new Date(cnXcrecode.getFendtime().getTime()-10*60*1000));
							CnSsxcrecode cnSsxcrecode = cnSsxcrecodeDao.getXcrecodeLBS(fclid, selectTime, selectTimeFiveAgo);
							if(cnSsxcrecode.getFjd()!=null){
								cnXcrecode.setFendjd(cnSsxcrecode.getFjd());
								cnXcrecode.setFendwd(cnSsxcrecode.getFwd());
							}
						}*/
						// 本次行驶里程
						double fbcxslc = StringByteUtil.parseLong(data
								.substring(50, 58));
						// 本次行驶里程
						double fbcxsyh = StringByteUtil.parseLong(data
								.substring(58, 62));

						
						
						// 行程记录时间
						String timeString = data.substring(10, 18);
						Long timeLong = StringByteUtil.parseLong(timeString) * 1000;
						Timestamp ts = new Timestamp(timeLong);
						// 开始时间
						cnXcrecode.setFendtime(ts);
						DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						if(fbeginjd==0||fbeginwd==0){
							String selectTime = dateFormat.format(new Date(cnXcrecode.getFbegintime().getTime()+10*60*1000));
							String selectTimeFiveAgo = dateFormat.format(cnXcrecode.getFbegintime());
							CnSsxcrecode cnSsxcrecode = cnSsxcrecodeDao.getXcrecodeLBSByBegin(carId, selectTime, selectTimeFiveAgo);
							if(cnSsxcrecode.getFjd()!=null){
								fbeginjd = cnSsxcrecode.getFjd();
								fbeginwd = cnSsxcrecode.getFwd();
							}
						}
						if(fendjd==0||fendwd==0){
							String selectTime = dateFormat.format(ts);
							String selectTimeFiveAgo = dateFormat.format(new Date(timeLong-10*60*1000));
							CnSsxcrecode cnSsxcrecode = cnSsxcrecodeDao.getXcrecodeLBS(carId, selectTime, selectTimeFiveAgo);
							if(cnSsxcrecode.getFjd()!=null){
								fendjd = cnSsxcrecode.getFjd();
								fendwd = cnSsxcrecode.getFwd();
							}
						}
						if(fbeginjd==0||fbeginwd==0||fendjd==0||fendwd==0){
							// 结束行程标志
							cnXcrecode.setFendflag(0L);
						}else{
							// 结束行程标志
							cnXcrecode.setFendflag(1L);
						}
						cnXcrecode.setFbeginjd(fbeginjd);
						cnXcrecode.setFbeginwd(fbeginwd);
						cnXcrecode.setFendjd(fendjd);
						cnXcrecode.setFendwd(fendwd);
						cnXcrecode.setFbcxslc(fbcxslc/100.0);
						cnXcrecode.setFbcxsyh(fbcxsyh/100.0);

						// 登记时间
						cnXcrecode.setFcreatetime(nowtime);

						// 更新
						cnXcrecodeDao.update(cnXcrecode);

//						LogUtil.appendLog("结束行程管理保存成功-->" + "开始经度：" + fbeginjd
//								+ " 开始纬度：" + fbeginwd + " 结束经度：" + fendjd
//								+ " 结束纬度：" + fendwd);
//						System.out.println("结束行程管理保存成功-->" + "开始经度：" + fbeginjd
//								+ " 开始纬度：" + fbeginwd + " 结束经度：" + fendjd
//								+ " 结束纬度：" + fendwd);
						
						logger.warn("盒子序列号:{} 结束行程管理:{}",serialNumber,cnXcrecode.toString());
					}
				}
				res = true;

			} catch (Exception e) {
				LogUtil.appendLog("行程管理解析出错--》" + e.getMessage());
				System.out.println("行程管理解析出错--》" + e.getMessage());
				res = false;
			}

		}
		return res;
	}
	/**
	 * 业务打包数据解析
	 * 
	 * @param dataMessageList
	 * @param cnSbljinfo
	 * @return
	 */
	public boolean packageDataTransmission(List<DataMessage> dataMessageList,
			CnSbljinfo cnSbljinfo) {
		
		String serialNumber=cnSbljinfo.getFsbxlh();

		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String now = df.format(new Date());
		Timestamp nowtime = Timestamp.valueOf(now);

		System.out.println("业务打包数据解析管理！");
		boolean res = false;
		try {
			PubBox pubBox = pubBoxDao.findByFxlh(cnSbljinfo.getFsbxlh());
			if (pubBox!=null&&pubBox.getPubCarinfo() != null) {

				// 车辆信息
				PubCarinfo pubCarinfo = pubBox.getPubCarinfo();

				for (DataMessage dataMessage : dataMessageList) {

					// 业务id
					String serviceId = dataMessage.getServiceId();
					// 获取数据
					String data = dataMessage.getData();

					if (serviceId.equals("0003")) {
						// 0003 位置信息
						// 消息标识
						String messgaeId = data.substring(0, 2);
						// num卫星个数
						Long number = StringByteUtil.parseLong(data.substring(
								2, 4));
						// 半径、精度
						Double fgpsjd = StringByteUtil.parseLong(data
								.substring(4, 8)) * 1.0;
						// 方向
						Double fgpsfx = StringByteUtil.parseLong(data
								.substring(8, 10)) * 1.0;
						// 速度
						Double fgpsspe = StringByteUtil.parseLong(data
								.substring(10, 14)) * 1.0;
						// 高度
						Double fgpsh = StringByteUtil.parseLong(data.substring(
								14, 18)) * 1.0;
						// 经度
						Double fjd = StringByteUtil.parseLong(data.substring(
								18, 26)) * 1.0 / (60 * 10000);
						// 纬度
						Double fwd = StringByteUtil.parseLong(data.substring(
								26, 34)) * 1.0 / (60 * 10000);

						String content = URLRequest
								.requestURL("http://api.map.baidu.com/ag/coord/convert?from=0&to=4&x="
										+ fjd + "&y=" + fwd);
						System.out.println("经纬度转化--》" + content);
						
						JSONObject jsonObject = JSONObject.fromObject(content);
						if (Integer
								.parseInt(jsonObject.get("error").toString()) == 0) {
							fjd = Double.parseDouble(Tools.decodeBase64(
									jsonObject.get("x").toString(), "utf-8"));
							fwd = Double.parseDouble(Tools.decodeBase64(
									jsonObject.get("y").toString(), "utf-8"));
						}

						// GPD时间
						Long gpgtime = StringByteUtil.parseLong(data.substring(
								34, 42)) * 1000;

						// 创建实体
						CnSsxcrecode cnSsxcrecode = new CnSsxcrecode();
						cnSsxcrecode.setPubCarinfo(pubCarinfo);
						cnSsxcrecode.setFgpstime(new Timestamp(gpgtime));
						cnSsxcrecode.setFgpsfx(fgpsfx);
						cnSsxcrecode.setFgpsspe(fgpsspe);
						cnSsxcrecode.setFgpsh(fgpsh);
						cnSsxcrecode.setFgpsjd(fgpsjd);
						cnSsxcrecode.setFjd(fjd);
						cnSsxcrecode.setFwd(fwd);

						// 保存数据库
						cnSsxcrecodeDao.save(cnSsxcrecode);
						
						logger.warn("盒子序列号:{} 实时行程记录:{}",serialNumber,cnSsxcrecode.toString());

						//LogUtil.appendLog("实时行程记录保存成功");

					} else if (serviceId.equals("0004")) {
						// 0004 obd 数据
						// 数据流长度
						Long length = Long.parseLong(data.substring(0, 2), 16) * 2;
						// 数据流(位置信息)
						String dataTable = data.substring(2,
								2 + length.intValue());

						// 转为二进制流
						String binaryString2 = StringByteUtil
								.hexString2binaryString(dataTable);

						// 截取内容
						data = data.substring(2 + length.intValue(),
								data.length());
						String databinaryString2 = StringByteUtil
								.hexString2binaryString(data);

						// 新建实时信息
						CnSsclxx cnSsclxx = new CnSsclxx();

						// 车辆信息
						cnSsclxx.setPubCarinfo(pubCarinfo);

						// 创建时间
						cnSsclxx.setFcreatetime(nowtime);

						/*
						 * Map<String,Object> map=new HashMap<String,Object>();
						 * map.put("cnSsclxx",cnSsclxx); map.put("value","1");
						 * String expression="cnSsclxx.setF1(value)";
						 * DyMethodUtil.invokeMethod(expression,map);
						 * System.out.println(cnSsclxx.getF1());
						 */

						// 查找1的位置
						for (int i = 0; i < binaryString2.length(); i++) {
							int ind = Integer.parseInt(binaryString2.substring(
									i, i + 1));
							if (ind == 1) {

								// 查询数据流
								PubDatalmb pubDatalmb = pubDatalmbDao
										.findByFcode((i + 1) + "");

								// 长度
								Long fbit = pubDatalmb.getFbit();

								// 获取值
								String dataString = databinaryString2
										.substring(0, fbit.intValue());
								Long dataLong = Long.parseLong(dataString, 2);

								// 执行方法
								Map<String, Object> map = new HashMap<String, Object>();
								map.put("cnSsclxx", cnSsclxx);
								map.put("value", dataLong + "");
								String expression = "cnSsclxx.setF" + (i + 1)
										+ "(value)";
								DyMethodUtil.invokeMethod(expression, map);

								// 剩余值
								databinaryString2 = databinaryString2
										.substring(fbit.intValue(),
												databinaryString2.length());
							}
						}

						// 保存数据库
						cnSsclxxDao.save(cnSsclxx);
						
						logger.warn("盒子序列号:{} 实时车辆信息:{}",serialNumber,cnSsclxx.toString());


						//LogUtil.appendLog("实时车辆信息保存成功");
					}
				}

			}
			res = true;
		} catch (Exception e) {
			LogUtil.appendLog("业务数据解析出错--》" + e.getMessage());
			System.out.println("业务数据解析出错--》" + e.getMessage());
			res = false;
		}
		return res;
	}

	/**
	 * 故障码
	 */
	public boolean faultCodes(DataMessage dataMessage, CnSbljinfo cnSbljinfo) {

		System.out.println("故障码解析管理！");
		
		String serialNumber=cnSbljinfo.getFsbxlh();

		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String now = df.format(new Date());
		Timestamp nowtime = Timestamp.valueOf(now);

		boolean res = false;
		try {
			
			PubBox pubBox = pubBoxDao.findByFxlh(cnSbljinfo.getFsbxlh());
			if (pubBox!=null&&pubBox.getPubCarinfo() != null) {

				// 车辆信息
				PubCarinfo pubCarinfo = pubBox.getPubCarinfo();

				// 数据信息
				String data = dataMessage.getData();
				// 系统No 0001
				String systemNo = data.substring(0, 4);
				// 系统Id ffffffff
				String systemId = data.substring(4, 12);
				// 故障码数量
				Long num = StringByteUtil.parseLong(data.substring(12, 16));

				// 故障码值
				data = data.substring(16, data.length());

				// 故障码记录
				CnGzrecode cnGzrecode = new CnGzrecode();
				cnGzrecode.setPubCarinfo(pubCarinfo);
				cnGzrecode.setFxtbs(systemNo);
				cnGzrecode.setFxtid(systemId);
				cnGzrecode.setFgzmsl(num);
				cnGzrecode.setFhqtime(nowtime);

				if (num > 0) {
					cnGzrecodeDao.save(cnGzrecode);
					
					logger.warn("盒子序列号:{} 故障码记录:{}",serialNumber,cnGzrecode.toString());
					//LogUtil.appendLog("故障码记录保存成功");
					//System.out.println("故障码记录保存成功");
					
					//添加盒子提醒
					cnInfoService.addMsg(pubBox,"CLGZ001");
				}

				while (num > 0) {

					System.out.println("故障码值--》" + data);

					// 故障码id
					String id = data.substring(0, 8);
					// 状态
					String status = data.substring(8, 16);
					// 值长
					Long length = StringByteUtil.parseLong(data.substring(16,
							20)) * 2;
					// 值
					String dataString = data.substring(20,
							20 + length.intValue());
					// 故障码
					String faultcode = StringByteUtil.AscIItoString(dataString);
					
					faultcode=faultcode.replace(" ", "");

					// 根据故障码查询故障码表
					CnGzm cnGzm = cnGzmDao.findByFno(faultcode);

					if (cnGzm != null) {
						//LogUtil.appendLog("故障码存在--》" + faultcode);
						//System.out.println(faultcode + "--》故障码存在");
					} else {
						//LogUtil.appendLog("故障码不存在--》" + faultcode);
						///System.out.println(faultcode + "--》故障码不存在");
					}
					// 创建故障码明细
					CnGzdetail cnGzdetail = new CnGzdetail();
					cnGzdetail.setCnGzrecode(cnGzrecode);
					cnGzdetail.setCnGzm(cnGzm);
					cnGzdetail.setFzt(status);
					cnGzdetail.setFaultcode(faultcode);
					cnGzdetailDao.save(cnGzdetail);
					
					logger.warn("盒子序列号:{} 故障码明细:{}",serialNumber,cnGzdetail.toString());

					// 剩余值
					data = data
							.substring(20 + length.intValue(), data.length());
					// 保存到数据库
					num--;

					//LogUtil.appendLog("故障码明细保存成功");
					//System.out.println("故障码明细保存成功");
				}

			}
			res = true;
		} catch (Exception e) {

			LogUtil.appendLog("故障码解析出错--》" + e.getMessage());
			System.out.println("故障码解析出错--》" + e.getMessage());
			res = false;
		}
		return res;
	}

	@Override
	public boolean police(DataMessage dataMessage, CnSbljinfo cnSbljinfo) {
		
		System.out.println("报警解析管理！");
		
		String serialNumber=cnSbljinfo.getFsbxlh();
		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String now = df.format(new Date());
		Timestamp nowtime = Timestamp.valueOf(now);

		boolean res = false;
		try {
			PubBox pubBox = pubBoxDao.findByFxlh(cnSbljinfo.getFsbxlh());
			if (pubBox!=null&&pubBox.getPubCarinfo() != null) {

				// 车辆信息
				PubCarinfo pubCarinfo = pubBox.getPubCarinfo();

				CnBjrecode cnBjrecode = new CnBjrecode();
				cnBjrecode.setPubCarinfo(pubCarinfo);

				// 获取数据
				String data = dataMessage.getData();

				// 报警ID
				String fno = data.substring(0, 4);

				cnBjrecode.setFxxid(fno);

				PubAlarmsetting pubAlarmsetting = null;
				// 获取报警设置
				if (fno.equalsIgnoreCase("0517")) {
					
					// 急加速
					pubAlarmsetting = pubAlarmsettingDao.findByFcode("AQXX001JIA");
					cnBjrecode.setFxxnr("急加速");
					//LogUtil.appendLog("报警解析--》急加速");
					
					cnBjrecode.setPubAlarmsetting(pubAlarmsetting);
					cnBjrecode.setFcreatetime(nowtime);
					cnBjrecode.setFtstamp(StringByteUtil.parseLong(dataMessage
							.getTimeStamp()) * 1000);
					cnBjrecodeDao.save(cnBjrecode);
					
					logger.warn("盒子序列号:{} 报警信息:{}",serialNumber,cnBjrecode.toString());

					
					//添加盒子提醒
					cnInfoService.addMsg(pubBox,"AQXX001JIA");
					
				} else if (fno.equalsIgnoreCase("0518")) {
					// 急减速
					pubAlarmsetting = pubAlarmsettingDao.findByFcode("AQXX001JIAN");
					cnBjrecode.setFxxnr("急减速");
					//LogUtil.appendLog("报警解析--》急减速");
					
					cnBjrecode.setPubAlarmsetting(pubAlarmsetting);
					cnBjrecode.setFcreatetime(nowtime);
					cnBjrecode.setFtstamp(StringByteUtil.parseLong(dataMessage
							.getTimeStamp()) * 1000);
					cnBjrecodeDao.save(cnBjrecode);
					
					logger.warn("盒子序列号:{} 报警信息:{}",serialNumber,cnBjrecode.toString());
					
					//添加盒子提醒
					cnInfoService.addMsg(pubBox,"AQXX001JIAN");
					
				} else if (fno.equalsIgnoreCase("EFF9")) {
					// 震动异常
					pubAlarmsetting = pubAlarmsettingDao.findByFcode("FDXX002");
					cnBjrecode.setFxxnr("驻车震动");
					//LogUtil.appendLog("报警解析--》驻车震动");
					
					cnBjrecode.setPubAlarmsetting(pubAlarmsetting);
					cnBjrecode.setFcreatetime(nowtime);
					cnBjrecode.setFtstamp(StringByteUtil.parseLong(dataMessage
							.getTimeStamp()) * 1000);
					cnBjrecodeDao.save(cnBjrecode);
					
					logger.warn("盒子序列号:{} 报警信息:{}",serialNumber,cnBjrecode.toString());
					
					//添加盒子提醒
					cnInfoService.addMsg(pubBox,"FDXX002");
					
				}else if (fno.equalsIgnoreCase("0520")) {
					// 盒子失联
					pubAlarmsetting = pubAlarmsettingDao.findByFcode("FDXX009");
					cnBjrecode.setFxxnr("盒子失联,"+ data.substring(4,data.length()));
					//LogUtil.appendLog("报警解析--》盒子失联,"+ data.substring(4,data.length()));
					
					cnBjrecode.setPubAlarmsetting(pubAlarmsetting);
					cnBjrecode.setFcreatetime(nowtime);
					cnBjrecode.setFtstamp(StringByteUtil.parseLong(dataMessage
							.getTimeStamp()) * 1000);
					cnBjrecodeDao.save(cnBjrecode);
					
					logger.warn("盒子序列号:{} 报警信息:{}",serialNumber,cnBjrecode.toString());
					
					//添加盒子提醒
					cnInfoService.addMsg(pubBox,"FDXX009");
					
				}else if(fno.equalsIgnoreCase("0560")){
					
					//System.out.println(cnSbljinfo.getFsbxlh()+",vin---data-->"+data);
					//LogUtil.Log(cnSbljinfo.getFsbxlh()+",vin---data-->"+data);
					
					logger.warn("盒子序列号:{} VIN---data-->{}",serialNumber,data);
					
					//获取vin码
					int start = 4;
					String vinLength=data.substring(start, start + 4);
					start=start+4;
					String vinASCII = data.substring(start, start + Integer.parseInt(vinLength, 16) * 2);
					//盒子判定车辆的vin码
					String vin=StringByteUtil.AscIItoString(vinASCII);
					
					//System.out.println(cnSbljinfo.getFsbxlh()+",Vin码-->"+vin);
					//LogUtil.Log(cnSbljinfo.getFsbxlh()+",Vin码-->"+vin);
					
					logger.warn("盒子序列号:{} VIN-->{}",serialNumber,vin);
					
					//车辆信息
					PubCarinfo carInfo=carInfoDao.getById(pubBox.getPubCarinfo().getId());
					carInfo.setFcarcode(vin);
					carInfoDao.update(carInfo);
				}

				LogUtil.appendLog("报警记录保存成功");
				System.out.println("报警记录保存成功");
			}
			res = true;
		} catch (Exception e) {

			LogUtil.appendLog("报警解析出错--》" + e.getMessage());
			System.out.println("报警解析出错--》" + e.getMessage());
			res = false;
		}
		return res;
	}

	@Override
	public List<CnSbljinfo> findCnSbljinfoList() {
		return cnSbljinfoDao.findAll();
	}

}
