package com.ennuova.entity;

/**行程管理数据Model
 * @author 李智辉 
 * 2015-12-18下午1:53:00
 */
public class XcRecord {
	private Long id;
	private String startDate;		//开始时间 格式 yyMM 1512 
	private String mileage;			//里程 格式 600km
	private String oil;				//油耗		单位L
	private String idleOil;			//怠速油耗	单位L
	private String maxSpeed;		//最大速度	单位km/h
	private String maxRpm;			//最大转速	单位rpm
	private String min;				//行驶分钟数	单位min
	private String tA;				//急加速次数		
	private String tB;				//急减速次数
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getMileage() {
		return mileage;
	}
	public void setMileage(String mileage) {
		this.mileage = mileage;
	}
	public String getOil() {
		return oil;
	}
	public void setOil(String oil) {
		this.oil = oil;
	}
	public String getIdleOil() {
		return idleOil;
	}
	public void setIdleOil(String idleOil) {
		this.idleOil = idleOil;
	}
	public String getMaxSpeed() {
		return maxSpeed;
	}
	public void setMaxSpeed(String maxSpeed) {
		this.maxSpeed = maxSpeed;
	}
	public String getMaxRpm() {
		return maxRpm;
	}
	public void setMaxRpm(String maxRpm) {
		this.maxRpm = maxRpm;
	}
	public String getMin() {
		return min;
	}
	public void setMin(String min) {
		this.min = min;
	}
	public String gettA() {
		return tA;
	}
	public void settA(String tA) {
		this.tA = tA;
	}
	public String gettB() {
		return tB;
	}
	public void settB(String tB) {
		this.tB = tB;
	}
}
