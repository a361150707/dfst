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
 * PubVehiclemodelBorrow entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "PUB_VEHICLEMODEL_BORROW")
public class PubVehiclemodelBorrow implements java.io.Serializable {

	// Fields

	/**   
	* @Title: PubVehiclemodelBorrow.java 
	* @Package com.ennuova.entity 
	* @Description: TODO(描述) 
	* @author felix
	* @date 2016年4月15日
	* @version V1.0   
	*/
	private static final long serialVersionUID = 1L;
	private Long vehiclemodelBorrowId;
	private Long vehiclemodelId;
	private Date createDate;
	private String createIp;
	private String borrowInfo;

	// Constructors

	/** default constructor */
	public PubVehiclemodelBorrow() {
	}

	/** minimal constructor */
	public PubVehiclemodelBorrow(Long vehiclemodelBorrowId) {
		this.vehiclemodelBorrowId = vehiclemodelBorrowId;
	}

	/** full constructor */
	public PubVehiclemodelBorrow(Long vehiclemodelBorrowId,
			Long vehiclemodelId, Date createDate, String createIp,
			String borrowInfo) {
		this.vehiclemodelBorrowId = vehiclemodelBorrowId;
		this.vehiclemodelId = vehiclemodelId;
		this.createDate = createDate;
		this.createIp = createIp;
		this.borrowInfo = borrowInfo;
	}

	// Property accessors
	@Id
	@SequenceGenerator(name="sequenceGenerator",sequenceName="seq_PUB_VEHICLEMODEL_BORROW",allocationSize=1,initialValue=1)
	@GeneratedValue(generator="sequenceGenerator",strategy=GenerationType.SEQUENCE)
	@Column(name = "VEHICLEMODEL_BORROW_ID", unique = true, nullable = false, precision = 16, scale = 0)
	public Long getVehiclemodelBorrowId() {
		return this.vehiclemodelBorrowId;
	}

	public void setVehiclemodelBorrowId(Long vehiclemodelBorrowId) {
		this.vehiclemodelBorrowId = vehiclemodelBorrowId;
	}

	@Column(name = "VEHICLEMODEL_ID", precision = 16, scale = 0)
	public Long getVehiclemodelId() {
		return this.vehiclemodelId;
	}

	public void setVehiclemodelId(Long vehiclemodelId) {
		this.vehiclemodelId = vehiclemodelId;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "CREATE_DATE", length = 7)
	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@Column(name = "CREATE_IP", length = 20)
	public String getCreateIp() {
		return this.createIp;
	}

	public void setCreateIp(String createIp) {
		this.createIp = createIp;
	}

	@Column(name = "BORROW_INFO", length = 200)
	public String getBorrowInfo() {
		return this.borrowInfo;
	}

	public void setBorrowInfo(String borrowInfo) {
		this.borrowInfo = borrowInfo;
	}

}