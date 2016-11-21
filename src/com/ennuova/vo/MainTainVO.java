package com.ennuova.vo;

import java.sql.Timestamp;
import java.util.Date;

/**保养维修续保实体
 * @author 李智辉
 * @time 2016-5-12
 */
public class MainTainVO {
	private Long id;	//预约状态ID
	private int reserveType;	//0表示预约保养 1表示预约维修 2表示预约续保
	private Long rdserveId;	//预约ID
	private int reserveStatus;	//0表示：待审核 1表示：已审核2表示：已完成3表示：审核未通过4表示：已取消5表示：已终止
	private Timestamp reserveDate; //预约时间
	private String reserveStatusName;	//预约状态名称
	private int reserveMaintainMilage;	//车辆真实里程
	private String reserveDateFormat;//预约时间格式化
	private String repairName;//维修项目
	private String sName;//保险公司名称
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public int getReserveType() {
		return reserveType;
	}
	public void setReserveType(int reserveType) {
		this.reserveType = reserveType;
	}
	public Long getRdserveId() {
		return rdserveId;
	}
	public void setRdserveId(Long rdserveId) {
		this.rdserveId = rdserveId;
	}
	public int getReserveStatus() {
		return reserveStatus;
	}
	public void setReserveStatus(int reserveStatus) {
		this.reserveStatus = reserveStatus;
	}
	public Date getReserveDate() {
		return reserveDate;
	}
	public void setReserveDate(Timestamp reserveDate) {
		this.reserveDate = reserveDate;
	}
	public String getReserveStatusName() {
		return reserveStatusName;
	}
	public void setReserveStatusName(String reserveStatusName) {
		this.reserveStatusName = reserveStatusName;
	}
	public int getReserveMaintainMilage() {
		return reserveMaintainMilage;
	}
	public void setReserveMaintainMilage(int reserveMaintainMilage) {
		this.reserveMaintainMilage = reserveMaintainMilage;
	}
	public String getReserveDateFormat() {
		return reserveDateFormat;
	}
	public void setReserveDateFormat(String reserveDateFormat) {
		this.reserveDateFormat = reserveDateFormat;
	}
	public String getRepairName() {
		return repairName;
	}
	public void setRepairName(String repairName) {
		this.repairName = repairName;
	}
	public String getsName() {
		return sName;
	}
	public void setsName(String sName) {
		this.sName = sName;
	}
}
