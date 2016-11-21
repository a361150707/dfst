package com.ennuova.obd.service;

import java.util.List;

import com.ennuova.entity.CnDiagsoft;
import com.ennuova.entity.CnSbljinfo;
import com.ennuova.entity.PubBox;
import com.ennuova.entity.PubBrand;
import com.ennuova.obd.message.DataMessage;
import com.ennuova.obd.message.LoginMessage;
import com.ennuova.obd.message.LoginResMessage;

/**Odb登录service
 * @author janson 
 */
public interface OdbLoginService {

	/**
	 * odb登录
	 * @param loginMessage
	 * @param loginResMessage
	 * @param key
	 * @param ip
	 * @param port
	 * @return 
	 */
	boolean login(LoginMessage loginMessage, LoginResMessage loginResMessage,
			String keyString, String ip,long port);

	/**
	 * 根据设备号查询Odb识别码
	 * @param serialNumber
	 * @return
	 */
	CnDiagsoft getOdbModelBySerialNumber(String serialNumber);
	/**
	 * 根据设备号查询Odb识别码
	 * @param serialNumber
	 * @return
	 */
	PubBox getPubBoxBySerialNumber(String serialNumber);

	/**
	 * 根据IP和端口号
	 * @return
	 */
	CnSbljinfo findCnSbljinfoByIpAndPort(String ip, int port);

	/**
	 * 行程记录管理
	 * @param dataMessage
	 * @param cnSbljinfo
	 * @return
	 */
	boolean travelRecord(DataMessage dataMessage, CnSbljinfo cnSbljinfo);

	/**
	 * 业务打包数据解析
	 * @param dataMessageList
	 * @param cnSbljinfo
	 * @return
	 */
	boolean packageDataTransmission(List<DataMessage> dataMessageList,
			CnSbljinfo cnSbljinfo);

	/**
	 * 故障码
	 * @param dataMessage
	 * @param cnSbljinfo
	 * @return
	 */
	boolean faultCodes(DataMessage dataMessage, CnSbljinfo cnSbljinfo);

	/**
	 * 报警信息
	 * @param dataMessage
	 * @param cnSbljinfo
	 * @return
	 */
	boolean police(DataMessage dataMessage, CnSbljinfo cnSbljinfo);
	
	/**
	 * 列表
	 * @param dataMessage
	 * @param cnSbljinfo
	 * @return
	 */
	List<CnSbljinfo> findCnSbljinfoList();

}
