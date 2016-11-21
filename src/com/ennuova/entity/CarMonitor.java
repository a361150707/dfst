package com.ennuova.entity;

import java.util.Date;

/**车辆监控
 * @author 李智辉 
 * 2015-12-25下午5:21:17
 */
public class CarMonitor {
	private Long id;	//主键id
	private String waterTemp;		//水温
	private String oilInstantaneous;		//瞬时百公里油耗
	private String addMileage;	//累计里程
	private String voltage;	//电池电压
	private String oilAverage;	//平均油耗
	private String oOilpercent;		//油量百分比
	private String carSpeed;	//车速
	private String remainOil;	//剩余油量
	private String seatbelt;	//安全带
	private String engineSpeed;	//发动机转速
	private String remaiMileage;	//剩余英里
	private String carLock;		//车锁
	private Date fcreateTime;		//时间
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getWaterTemp() {
		return waterTemp;
	}
	public void setWaterTemp(String waterTemp) {
		this.waterTemp = waterTemp;
	}
	public String getOilInstantaneous() {
		return oilInstantaneous;
	}
	public void setOilInstantaneous(String oilInstantaneous) {
		this.oilInstantaneous = oilInstantaneous;
	}
	public String getAddMileage() {
		return addMileage;
	}
	public void setAddMileage(String addMileage) {
		this.addMileage = addMileage;
	}
	public String getVoltage() {
		return voltage;
	}
	public void setVoltage(String voltage) {
		this.voltage = voltage;
	}
	public String getOilAverage() {
		return oilAverage;
	}
	public void setOilAverage(String oilAverage) {
		this.oilAverage = oilAverage;
	}
	public String getoOilpercent() {
		return oOilpercent;
	}
	public void setoOilpercent(String oOilpercent) {
		this.oOilpercent = oOilpercent;
	}
	public String getCarSpeed() {
		return carSpeed;
	}
	public void setCarSpeed(String carSpeed) {
		this.carSpeed = carSpeed;
	}
	public String getRemainOil() {
		return remainOil;
	}
	public void setRemainOil(String remainOil) {
		this.remainOil = remainOil;
	}
	public String getSeatbelt() {
		return seatbelt;
	}
	public void setSeatbelt(String seatbelt) {
		this.seatbelt = seatbelt;
	}
	public String getEngineSpeed() {
		return engineSpeed;
	}
	public void setEngineSpeed(String engineSpeed) {
		this.engineSpeed = engineSpeed;
	}
	public String getRemaiMileage() {
		return remaiMileage;
	}
	public void setRemaiMileage(String remaiMileage) {
		this.remaiMileage = remaiMileage;
	}
	public String getCarLock() {
		return carLock;
	}
	public void setCarLock(String carLock) {
		this.carLock = carLock;
	}
	public Date getFcreateTime() {
		return fcreateTime;
	}
	public void setFcreateTime(Date fcreateTime) {
		this.fcreateTime = fcreateTime;
	}
	
}
