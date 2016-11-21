package com.ennuova.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 * ReserveRemewal entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "RESERVE_REMEWAL")
public class ReserveRemewal implements java.io.Serializable {

	private static final long serialVersionUID = 7132596504690024307L;
	private Long reserveRemewalId;
	private Long insuranceCompanyId;
	private String reserveName;
	private String reserveTel;
	private String reserveState;
	private Date reserveDate;
	private String reserveDateString;
	private Long customerId;
	private Long carinfoId;
	private String reserveAddress;
	private String reserveContent;
	private String reserveEmark;
	private String reserveRemewalProcessMode;
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
	@Column(name = "USER_ID", length = 50)
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	// Constructors

	/** default constructor */
	public ReserveRemewal() {
	}
	@Transient
	public String getReserveDateString() {
		return reserveDateString;
	}

	public void setReserveDateString(String reserveDateString) {
		this.reserveDateString = reserveDateString;
	}

	/** minimal constructor */
	public ReserveRemewal(Long reserveRemewalId) {
		this.reserveRemewalId = reserveRemewalId;
	}

	/** full constructor */
	public ReserveRemewal(Long reserveRemewalId, Long insuranceCompanyId,
			String reserveName, String reserveTel, String reserveState,
			Date reserveDate, Long customerId, Long carinfoId,
			String reserveAddress, String reserveContent, String reserveEmark,
			String reserveRemewalProcessMode, String createBy, Date createDate,
			String updateBy, Date updateDate, String delFlag,
			String createName, String sysOrgCode, String sysCompanyCode,
			String updateName,String userId) {
		this.reserveRemewalId = reserveRemewalId;
		this.insuranceCompanyId = insuranceCompanyId;
		this.reserveName = reserveName;
		this.reserveTel = reserveTel;
		this.reserveState = reserveState;
		this.reserveDate = reserveDate;
		this.customerId = customerId;
		this.carinfoId = carinfoId;
		this.reserveAddress = reserveAddress;
		this.reserveContent = reserveContent;
		this.reserveEmark = reserveEmark;
		this.reserveRemewalProcessMode = reserveRemewalProcessMode;
		this.createBy = createBy;
		this.createDate = createDate;
		this.updateBy = updateBy;
		this.updateDate = updateDate;
		this.delFlag = delFlag;
		this.createName = createName;
		this.sysOrgCode = sysOrgCode;
		this.sysCompanyCode = sysCompanyCode;
		this.updateName = updateName;
		this.userId = userId;
	}

	// Property accessors
	@Id
	@SequenceGenerator(name="sequenceGenerator",sequenceName="SEQ_RESERVE_REMEWAL",allocationSize=1,initialValue=1)
	@GeneratedValue(generator="sequenceGenerator",strategy=GenerationType.SEQUENCE)
	@Column(name = "RESERVE_REMEWAL_ID", unique = true, nullable = false, precision = 16, scale = 0)
	public Long getReserveRemewalId() {
		return this.reserveRemewalId;
	}

	public void setReserveRemewalId(Long reserveRemewalId) {
		this.reserveRemewalId = reserveRemewalId;
	}

	@Column(name = "INSURANCE_COMPANY_ID", precision = 16, scale = 0)
	public Long getInsuranceCompanyId() {
		return this.insuranceCompanyId;
	}

	public void setInsuranceCompanyId(Long insuranceCompanyId) {
		this.insuranceCompanyId = insuranceCompanyId;
	}

	@Column(name = "RESERVE_NAME", length = 50)
	public String getReserveName() {
		return this.reserveName;
	}

	public void setReserveName(String reserveName) {
		this.reserveName = reserveName;
	}

	@Column(name = "RESERVE_TEL", length = 50)
	public String getReserveTel() {
		return this.reserveTel;
	}

	public void setReserveTel(String reserveTel) {
		this.reserveTel = reserveTel;
	}

	@Column(name = "RESERVE_STATE", length = 50)
	public String getReserveState() {
		return this.reserveState;
	}

	public void setReserveState(String reserveState) {
		this.reserveState = reserveState;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "RESERVE_DATE", length = 7)
	public Date getReserveDate() {
		return this.reserveDate;
	}

	public void setReserveDate(Date reserveDate) {
		this.reserveDate = reserveDate;
	}

	@Column(name = "CUSTOMER_ID", precision = 16, scale = 0)
	public Long getCustomerId() {
		return this.customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	@Column(name = "CARINFO_ID", precision = 16, scale = 0)
	public Long getCarinfoId() {
		return this.carinfoId;
	}

	public void setCarinfoId(Long carinfoId) {
		this.carinfoId = carinfoId;
	}

	@Column(name = "RESERVE_ADDRESS", length = 50)
	public String getReserveAddress() {
		return this.reserveAddress;
	}

	public void setReserveAddress(String reserveAddress) {
		this.reserveAddress = reserveAddress;
	}

	@Column(name = "RESERVE_CONTENT", length = 500)
	public String getReserveContent() {
		return this.reserveContent;
	}

	public void setReserveContent(String reserveContent) {
		this.reserveContent = reserveContent;
	}

	@Column(name = "RESERVE_EMARK", length = 500)
	public String getReserveEmark() {
		return this.reserveEmark;
	}

	public void setReserveEmark(String reserveEmark) {
		this.reserveEmark = reserveEmark;
	}

	@Column(name = "RESERVE_REMEWAL_PROCESS_MODE", length = 50)
	public String getReserveRemewalProcessMode() {
		return this.reserveRemewalProcessMode;
	}

	public void setReserveRemewalProcessMode(String reserveRemewalProcessMode) {
		this.reserveRemewalProcessMode = reserveRemewalProcessMode;
	}

	@Column(name = "CREATE_BY", length = 32)
	public String getCreateBy() {
		return this.createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "CREATE_DATE", length = 7)
	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@Column(name = "UPDATE_BY", length = 32)
	public String getUpdateBy() {
		return this.updateBy;
	}

	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "UPDATE_DATE", length = 7)
	public Date getUpdateDate() {
		return this.updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	@Column(name = "DEL_FLAG", length = 1)
	public String getDelFlag() {
		return this.delFlag;
	}

	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}

	@Column(name = "CREATE_NAME", length = 50)
	public String getCreateName() {
		return this.createName;
	}

	public void setCreateName(String createName) {
		this.createName = createName;
	}

	@Column(name = "SYS_ORG_CODE", length = 50)
	public String getSysOrgCode() {
		return this.sysOrgCode;
	}

	public void setSysOrgCode(String sysOrgCode) {
		this.sysOrgCode = sysOrgCode;
	}

	@Column(name = "SYS_COMPANY_CODE", length = 50)
	public String getSysCompanyCode() {
		return this.sysCompanyCode;
	}

	public void setSysCompanyCode(String sysCompanyCode) {
		this.sysCompanyCode = sysCompanyCode;
	}

	@Column(name = "UPDATE_NAME", length = 50)
	public String getUpdateName() {
		return this.updateName;
	}

	public void setUpdateName(String updateName) {
		this.updateName = updateName;
	}

}