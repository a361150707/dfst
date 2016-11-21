package com.ennuova.entity;

/**行程起终点地址VO
 * @author 李智辉
 * @time 2016-4-11
 */
public class XcrecodeVO {
	private Long id;
	private String timeStr;	//时间
	private String startAddress;	//开始地点
	private String endAddress;	//结束地点
	private Double fbeginjd;
	private Double fbeginwd;
	private Double fendjd;
	private Double fendwd;
	private Double fbcxslc;			//里程
	private Double fbcxsyh;			//油耗
	private Double averageOil;		//百公里平均油耗
	private String travelMin;	//行驶时间分钟数
	private String tA;				//急加速次数		
	private String tB;				//急减速次数
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTimeStr() {
		return timeStr;
	}
	public void setTimeStr(String timeStr) {
		this.timeStr = timeStr;
	}
	public String getStartAddress() {
		return startAddress;
	}
	public void setStartAddress(String startAddress) {
		this.startAddress = startAddress;
	}
	public String getEndAddress() {
		return endAddress;
	}
	public void setEndAddress(String endAddress) {
		this.endAddress = endAddress;
	}
	public Double getFbeginjd() {
		return fbeginjd;
	}
	public void setFbeginjd(Double fbeginjd) {
		this.fbeginjd = fbeginjd;
	}
	public Double getFbeginwd() {
		return fbeginwd;
	}
	public void setFbeginwd(Double fbeginwd) {
		this.fbeginwd = fbeginwd;
	}
	public Double getFendjd() {
		return fendjd;
	}
	public void setFendjd(Double fendjd) {
		this.fendjd = fendjd;
	}
	public Double getFendwd() {
		return fendwd;
	}
	public void setFendwd(Double fendwd) {
		this.fendwd = fendwd;
	}
	public Double getFbcxslc() {
		return fbcxslc;
	}
	public void setFbcxslc(Double fbcxslc) {
		this.fbcxslc = fbcxslc;
	}
	public Double getFbcxsyh() {
		return fbcxsyh;
	}
	public void setFbcxsyh(Double fbcxsyh) {
		this.fbcxsyh = fbcxsyh;
	}
	public Double getAverageOil() {
		return averageOil;
	}
	public void setAverageOil(Double averageOil) {
		this.averageOil = averageOil;
	}
	public String getTravelMin() {
		return travelMin;
	}
	public void setTravelMin(String travelMin) {
		this.travelMin = travelMin;
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
