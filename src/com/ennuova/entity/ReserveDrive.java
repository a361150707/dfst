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

/**
 * ReserveDrive entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "RESERVE_DRIVE")
public class ReserveDrive implements java.io.Serializable {

	// Fields

	/**   
	* @Title: ReserveDrive.java 
	* @Package com.ennuova.entity 
	* @Description: TODO(描述) 
	* @author felix
	* @date 2016年4月15日
	* @version V1.0   
	*/
	private static final long serialVersionUID = 1L;
	private Long reserveDriveId;//预约试驾id
	private String reserveName;//预约人名称
	private String reserveTel;//预约人手机
	private String reserveSex;//预约人性别
	private String reserveState;//预约状态
	private Date reserveDate;//预约时间
	private String reserveContent;//内容
	private String reserveRemark;//备注
	private Long brandId;//品牌ID
	private Long lineId;//车系ID
	private Long vehiclemodelId;//车型ID
	private String brandName;//品牌名称
	private String lineName;//车系名称
	private String vehiclemodelName;//车型名称
	private Long provinceId;//省份ID
	private Long cityId;//城市ID
	private String provinceName;//省份名称
	private String cityName;//城市名称
	private Long storeId;//门店ID
	private String storeName;//门店名称
	private String delFlag;//删除标识
	// Constructors

	/** default constructor */
	public ReserveDrive() {
	}

	/** minimal constructor */
	public ReserveDrive(Long reserveDriveId) {
		this.reserveDriveId = reserveDriveId;
	}

	/** full constructor */
	public ReserveDrive(Long reserveDriveId, String reserveName,
			String reserveTel, String reserveSex, String reserveState,
			Date reserveDate, String reserveContent, String reserveRemark,
			Long brandId, Long lineId, Long vehiclemodelId, String brandName,
			String lineName, String vehiclemodelName, Long provinceId,
			Long cityId, String provinceName, String cityName, Long storeId,
			String storeName, String delFlag) {
		this.reserveDriveId = reserveDriveId;
		this.reserveName = reserveName;
		this.reserveTel = reserveTel;
		this.reserveSex = reserveSex;
		this.reserveState = reserveState;
		this.reserveDate = reserveDate;
		this.reserveContent = reserveContent;
		this.reserveRemark = reserveRemark;
		this.brandId = brandId;
		this.lineId = lineId;
		this.vehiclemodelId = vehiclemodelId;
		this.brandName = brandName;
		this.lineName = lineName;
		this.vehiclemodelName = vehiclemodelName;
		this.provinceId = provinceId;
		this.cityId = cityId;
		this.provinceName = provinceName;
		this.cityName = cityName;
		this.storeId = storeId;
		this.storeName = storeName;
		this.delFlag = delFlag;
	}

	// Property accessors
	@Id
	@SequenceGenerator(name="sequenceGenerator",sequenceName="seq_RESERVE_DRIVE",allocationSize=1,initialValue=1)
	@GeneratedValue(generator="sequenceGenerator",strategy=GenerationType.SEQUENCE)
	@Column(name = "RESERVE_DRIVE_ID", unique = true, nullable = false, precision = 16, scale = 0)
	public Long getReserveDriveId() {
		return this.reserveDriveId;
	}

	public void setReserveDriveId(Long reserveDriveId) {
		this.reserveDriveId = reserveDriveId;
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

	@Column(name = "RESERVE_SEX", length = 50)
	public String getReserveSex() {
		return this.reserveSex;
	}

	public void setReserveSex(String reserveSex) {
		this.reserveSex = reserveSex;
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

	@Column(name = "RESERVE_CONTENT", length = 500)
	public String getReserveContent() {
		return this.reserveContent;
	}

	public void setReserveContent(String reserveContent) {
		this.reserveContent = reserveContent;
	}

	@Column(name = "RESERVE_REMARK", length = 500)
	public String getReserveRemark() {
		return this.reserveRemark;
	}

	public void setReserveRemark(String reserveRemark) {
		this.reserveRemark = reserveRemark;
	}

	@Column(name = "BRAND_ID", precision = 16, scale = 0)
	public Long getBrandId() {
		return this.brandId;
	}

	public void setBrandId(Long brandId) {
		this.brandId = brandId;
	}

	@Column(name = "LINE_ID", precision = 16, scale = 0)
	public Long getLineId() {
		return this.lineId;
	}

	public void setLineId(Long lineId) {
		this.lineId = lineId;
	}

	@Column(name = "VEHICLEMODEL_ID", precision = 16, scale = 0)
	public Long getVehiclemodelId() {
		return this.vehiclemodelId;
	}

	public void setVehiclemodelId(Long vehiclemodelId) {
		this.vehiclemodelId = vehiclemodelId;
	}

	@Column(name = "BRAND_NAME", length = 50)
	public String getBrandName() {
		return this.brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	@Column(name = "LINE_NAME", length = 50)
	public String getLineName() {
		return this.lineName;
	}

	public void setLineName(String lineName) {
		this.lineName = lineName;
	}

	@Column(name = "VEHICLEMODEL_NAME", length = 50)
	public String getVehiclemodelName() {
		return this.vehiclemodelName;
	}

	public void setVehiclemodelName(String vehiclemodelName) {
		this.vehiclemodelName = vehiclemodelName;
	}

	@Column(name = "PROVINCE_ID", precision = 16, scale = 0)
	public Long getProvinceId() {
		return this.provinceId;
	}

	public void setProvinceId(Long provinceId) {
		this.provinceId = provinceId;
	}

	@Column(name = "CITY_ID", precision = 16, scale = 0)
	public Long getCityId() {
		return this.cityId;
	}

	public void setCityId(Long cityId) {
		this.cityId = cityId;
	}

	@Column(name = "PROVINCE_NAME", length = 50)
	public String getProvinceName() {
		return this.provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

	@Column(name = "CITY_NAME", length = 50)
	public String getCityName() {
		return this.cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	@Column(name = "STORE_ID", precision = 16, scale = 0)
	public Long getStoreId() {
		return this.storeId;
	}

	public void setStoreId(Long storeId) {
		this.storeId = storeId;
	}

	@Column(name = "STORE_NAME", length = 50)
	public String getStoreName() {
		return this.storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}


	@Column(name = "DEL_FLAG", length = 1)
	public String getDelFlag() {
		return this.delFlag;
	}

	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}

}