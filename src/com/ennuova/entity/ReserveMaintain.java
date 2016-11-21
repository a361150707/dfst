package com.ennuova.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 * ReserveMaintain entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "RESERVE_MAINTAIN")
@SequenceGenerator(name="SEQ_GEN", sequenceName="SEQ_RESERVE_MAINTAIN")  
public class ReserveMaintain implements java.io.Serializable {
	private static final long serialVersionUID = 8519511078584275367L;
	private Long reserveMaintainId;
	private Long maintainPlayId;
	private String reserveName;
	private String reserveTel;
	private String reserveState;
	private Date reserveDate;
	private String reserveDateString;
	private Long customerId;
	private Long carinfoId;
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
	@Column(name = "USER_ID", length = 50)
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	private Set<ReserveMaintainItem> reserveMaintainItems=new HashSet<ReserveMaintainItem>();

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "reserveMaintain")
	public Set<ReserveMaintainItem> getReserveMaintainItems() {
		return reserveMaintainItems;
	}

	public void setReserveMaintainItems(
			Set<ReserveMaintainItem> reserveMaintainItems) {
		this.reserveMaintainItems = reserveMaintainItems;
	}

	/** default constructor */
	public ReserveMaintain() {
	}

	/** minimal constructor */
	public ReserveMaintain(Long reserveMaintainId) {
		this.reserveMaintainId = reserveMaintainId;
	}

	/** full constructor */
	public ReserveMaintain(Long reserveMaintainId, Long maintainPlayId,
			String reserveName, String reserveTel, String reserveState,
			Date reserveDate, Long customerId, Long carinfoId,
			Integer reserveMaintainMilage, String reserveAddress,
			String reserveContent, String reserveEmark,
			String reserveMaintainHomeCar, String createBy, Date createDate,
			String updateBy, Date updateDate, String delFlag,
			String createName, String sysOrgCode, String sysCompanyCode,
			String updateName,String userId) {
		this.reserveMaintainId = reserveMaintainId;
		this.maintainPlayId = maintainPlayId;
		this.reserveName = reserveName;
		this.reserveTel = reserveTel;
		this.reserveState = reserveState;
		this.reserveDate = reserveDate;
		this.customerId = customerId;
		this.carinfoId = carinfoId;
		this.reserveMaintainMilage = reserveMaintainMilage;
		this.reserveAddress = reserveAddress;
		this.reserveContent = reserveContent;
		this.reserveEmark = reserveEmark;
		this.reserveMaintainHomeCar = reserveMaintainHomeCar;
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
	@SequenceGenerator(name="sequenceGenerator",sequenceName="seq_reserve_maintain",allocationSize=1,initialValue=1)
	@GeneratedValue(generator="sequenceGenerator",strategy=GenerationType.SEQUENCE)
	@Column(name = "RESERVE_MAINTAIN_ID", unique = true, nullable = false, precision = 16, scale = 0)
	public Long getReserveMaintainId() {
		return this.reserveMaintainId;
	}

	public void setReserveMaintainId(Long reserveMaintainId) {
		this.reserveMaintainId = reserveMaintainId;
	}

	@Column(name = "MAINTAIN_PLAY_ID", precision = 16, scale = 0)
	public Long getMaintainPlayId() {
		return this.maintainPlayId;
	}

	public void setMaintainPlayId(Long maintainPlayId) {
		this.maintainPlayId = maintainPlayId;
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

	@Column(name = "RESERVE_MAINTAIN_MILAGE")
	public Integer getReserveMaintainMilage() {
		return this.reserveMaintainMilage;
	}

	public void setReserveMaintainMilage(Integer reserveMaintainMilage) {
		this.reserveMaintainMilage = reserveMaintainMilage;
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

	@Column(name = "RESERVE_MAINTAIN_HOME_CAR", length = 1)
	public String getReserveMaintainHomeCar() {
		return this.reserveMaintainHomeCar;
	}

	public void setReserveMaintainHomeCar(String reserveMaintainHomeCar) {
		this.reserveMaintainHomeCar = reserveMaintainHomeCar;
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
	@Transient
	public String getReserveDateString() {
		return reserveDateString;
	}

	public void setReserveDateString(String reserveDateString) {
		this.reserveDateString = reserveDateString;
	}

}