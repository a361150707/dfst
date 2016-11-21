package com.ennuova.entity;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * ODb登录车型对应表
 */
@Entity
@Table(name = "CN_DIAGSOFT")
public class CnDiagsoft implements java.io.Serializable {

	// Fields

	private Long id;
	private Long detailId;
	private Long autoId;
	private String softName;
	private String carSeriesName;
	private Long createUserId;
	private Timestamp createTime;
	private Long lanId;
	private Long configId;
	private Long parentId;
	private Long isHot;
	private Long areaId;
	private Long checkStatus;
	private Long checkUserId;
	private Timestamp checkTime;
	private Long isAbroad;

	// Constructors

	/** default constructor */
	public CnDiagsoft() {
	}

	/** minimal constructor */
	public CnDiagsoft(Long id) {
		this.id = id;
	}

	/** full constructor */
	public CnDiagsoft(Long id, Long detailId, Long autoId, String softName,
			String carSeriesName, Long createUserId, Timestamp createTime,
			Long lanId, Long configId, Long parentId, Long isHot, Long areaId,
			Long checkStatus, Long checkUserId, Timestamp checkTime,
			Long isAbroad) {
		this.id = id;
		this.detailId = detailId;
		this.autoId = autoId;
		this.softName = softName;
		this.carSeriesName = carSeriesName;
		this.createUserId = createUserId;
		this.createTime = createTime;
		this.lanId = lanId;
		this.configId = configId;
		this.parentId = parentId;
		this.isHot = isHot;
		this.areaId = areaId;
		this.checkStatus = checkStatus;
		this.checkUserId = checkUserId;
		this.checkTime = checkTime;
		this.isAbroad = isAbroad;
	}

	// Property accessors
	@Id
	@SequenceGenerator(name="sequenceGenerator",sequenceName="seq_CN_DIAGSOFT",allocationSize=1,initialValue=1)
	@GeneratedValue(generator="sequenceGenerator",strategy=GenerationType.SEQUENCE)
	@Column(name = "ID", unique = true, nullable = false, precision = 16, scale = 0)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "DETAIL_ID", precision = 16, scale = 0)
	public Long getDetailId() {
		return this.detailId;
	}

	public void setDetailId(Long detailId) {
		this.detailId = detailId;
	}

	@Column(name = "AUTO_ID", precision = 16, scale = 0)
	public Long getAutoId() {
		return this.autoId;
	}

	public void setAutoId(Long autoId) {
		this.autoId = autoId;
	}

	@Column(name = "SOFT_NAME", length = 50)
	public String getSoftName() {
		return this.softName;
	}

	public void setSoftName(String softName) {
		this.softName = softName;
	}

	@Column(name = "CAR_SERIES_NAME", length = 50)
	public String getCarSeriesName() {
		return this.carSeriesName;
	}

	public void setCarSeriesName(String carSeriesName) {
		this.carSeriesName = carSeriesName;
	}

	@Column(name = "CREATE_USER_ID", precision = 16, scale = 0)
	public Long getCreateUserId() {
		return this.createUserId;
	}

	public void setCreateUserId(Long createUserId) {
		this.createUserId = createUserId;
	}

	@Column(name = "CREATE_TIME", length = 7)
	public Timestamp getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	@Column(name = "LAN_ID", precision = 16, scale = 0)
	public Long getLanId() {
		return this.lanId;
	}

	public void setLanId(Long lanId) {
		this.lanId = lanId;
	}

	@Column(name = "CONFIG_ID", precision = 16, scale = 0)
	public Long getConfigId() {
		return this.configId;
	}

	public void setConfigId(Long configId) {
		this.configId = configId;
	}

	@Column(name = "PARENT_ID", precision = 16, scale = 0)
	public Long getParentId() {
		return this.parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	@Column(name = "IS_HOT", precision = 16, scale = 0)
	public Long getIsHot() {
		return this.isHot;
	}

	public void setIsHot(Long isHot) {
		this.isHot = isHot;
	}

	@Column(name = "AREA_ID", precision = 16, scale = 0)
	public Long getAreaId() {
		return this.areaId;
	}

	public void setAreaId(Long areaId) {
		this.areaId = areaId;
	}

	@Column(name = "CHECK_STATUS", precision = 16, scale = 0)
	public Long getCheckStatus() {
		return this.checkStatus;
	}

	public void setCheckStatus(Long checkStatus) {
		this.checkStatus = checkStatus;
	}

	@Column(name = "CHECK_USER_ID", precision = 16, scale = 0)
	public Long getCheckUserId() {
		return this.checkUserId;
	}

	public void setCheckUserId(Long checkUserId) {
		this.checkUserId = checkUserId;
	}

	@Column(name = "CHECK_TIME", length = 7)
	public Timestamp getCheckTime() {
		return this.checkTime;
	}

	public void setCheckTime(Timestamp checkTime) {
		this.checkTime = checkTime;
	}

	@Column(name = "IS_ABROAD", precision = 16, scale = 0)
	public Long getIsAbroad() {
		return this.isAbroad;
	}

	public void setIsAbroad(Long isAbroad) {
		this.isAbroad = isAbroad;
	}

}