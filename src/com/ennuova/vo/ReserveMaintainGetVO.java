package com.ennuova.vo;

import java.util.Date;

/**
 * 保养记录
 * @author jimmy(王志明)
 *2016年4月19日
 */
public class ReserveMaintainGetVO {
	private Long reserveMaintainId;
	/**预约状态名称 */
	private String reserveStatusName;
	/**里程数  */
	private Integer reserveMaintainMilage;
	/**预约状态时间 */
	private Date reserveDate;
	public Long getReserveMaintainId() {
		return reserveMaintainId;
	}
	public void setReserveMaintainId(Long reserveMaintainId) {
		this.reserveMaintainId = reserveMaintainId;
	}
	public String getReserveStatusName() {
		return reserveStatusName;
	}
	public void setReserveStatusName(String reserveStatusName) {
		this.reserveStatusName = reserveStatusName;
	}
	public Date getReserveDate() {
		return reserveDate;
	}
	public void setReserveDate(Date reserveDate) {
		this.reserveDate = reserveDate;
	}
	public Integer getReserveMaintainMilage() {
		return reserveMaintainMilage;
	}
	public void setReserveMaintainMilage(Integer reserveMaintainMilage) {
		this.reserveMaintainMilage = reserveMaintainMilage;
	}

}
