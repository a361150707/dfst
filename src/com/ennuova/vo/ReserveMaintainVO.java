package com.ennuova.vo;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;

import com.ennuova.common.AppBaseVO;

public class ReserveMaintainVO extends AppBaseVO {
	private Long reserveMaintainId;
	/** 保养方案ID */
	private Long maintainPlayId;
	private String reserveName;
	private String reserveTel;
	private String reserveState;
	private String reserveDateString;
	private Date reserveDate;
	private Long customerId;
	private Long carinfoId;
	/**里程数  */
	private Integer reserveMaintainMilage;
	private String reserveAddress;
	private String reserveContent;
	private String reserveEmark;
	private String reserveMaintainHomeCar;
	private String createBy;
	private Date createDate;
	private String updateBy;
	private Date updateDate;
	private String delFlag;
	private String createName;
	private String sysOrgCode;
	private String sysCompanyCode;
	private String updateName;
	private String userId;
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	/**保养项目ID */
	private String maintainItemId;
	
	public String getMaintainItemId() {
		return maintainItemId;
	}

	public void setMaintainItemId(String maintainItemId) {
		this.maintainItemId = maintainItemId;
	}


	public Long getReserveMaintainId() {
		return reserveMaintainId;
	}

	public void setReserveMaintainId(Long reserveMaintainId) {
		this.reserveMaintainId = reserveMaintainId;
	}

	public String getReserveName() {
		return reserveName;
	}

	public void setReserveName(String reserveName) {
		this.reserveName = reserveName;
	}

	public String getReserveTel() {
		return reserveTel;
	}

	public void setReserveTel(String reserveTel) {
		this.reserveTel = reserveTel;
	}

	public String getReserveState() {
		return reserveState;
	}

	public void setReserveState(String reserveState) {
		this.reserveState = reserveState;
	}

	public Date getReserveDate() {
		return reserveDate;
	}

	public void setReserveDate(Date reserveDate) {
		this.reserveDate = reserveDate;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public Long getCarinfoId() {
		return carinfoId;
	}

	public void setCarinfoId(Long carinfoId) {
		this.carinfoId = carinfoId;
	}

	public Integer getReserveMaintainMilage() {
		return reserveMaintainMilage;
	}

	public void setReserveMaintainMilage(Integer reserveMaintainMilage) {
		this.reserveMaintainMilage = reserveMaintainMilage;
	}

	public String getReserveAddress() {
		return reserveAddress;
	}

	public void setReserveAddress(String reserveAddress) {
		this.reserveAddress = reserveAddress;
	}

	public String getReserveContent() {
		return reserveContent;
	}

	public void setReserveContent(String reserveContent) {
		this.reserveContent = reserveContent;
	}

	public String getReserveEmark() {
		return reserveEmark;
	}

	public void setReserveEmark(String reserveEmark) {
		this.reserveEmark = reserveEmark;
	}

	public String getReserveMaintainHomeCar() {
		return reserveMaintainHomeCar;
	}

	public void setReserveMaintainHomeCar(String reserveMaintainHomeCar) {
		this.reserveMaintainHomeCar = reserveMaintainHomeCar;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public String getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}

	public String getCreateName() {
		return createName;
	}

	public void setCreateName(String createName) {
		this.createName = createName;
	}

	public String getSysOrgCode() {
		return sysOrgCode;
	}

	public void setSysOrgCode(String sysOrgCode) {
		this.sysOrgCode = sysOrgCode;
	}

	public String getSysCompanyCode() {
		return sysCompanyCode;
	}

	public void setSysCompanyCode(String sysCompanyCode) {
		this.sysCompanyCode = sysCompanyCode;
	}

	public String getUpdateName() {
		return updateName;
	}

	public void setUpdateName(String updateName) {
		this.updateName = updateName;
	}

	public long getMaintainPlayId() {
		return maintainPlayId;
	}

	public void setMaintainPlayId(Long maintainPlayId) {
		this.maintainPlayId = maintainPlayId;
	}

	public String getReserveDateString() {
		return reserveDateString;
	}

	public void setReserveDateString(String reserveDateString) {
		this.reserveDateString = reserveDateString;
	}
}
