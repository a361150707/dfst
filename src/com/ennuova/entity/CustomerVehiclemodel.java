package com.ennuova.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * CustomerVehiclemodel entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "CUSTOMER_VEHICLEMODEL")
public class CustomerVehiclemodel implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long customerVehiclemodelId;//id
	private Long customerId;//用户id
	//private Long vehiclemodelId;//关注车型id
	private PubVehiclemodel model;//关联车型
	private Date createDate;//创建时间

	// Constructors

	/** default constructor */
	public CustomerVehiclemodel() {
	}

	/** minimal constructor */
	public CustomerVehiclemodel(Long customerVehiclemodelId) {
		this.customerVehiclemodelId = customerVehiclemodelId;
	}

	/** full constructor */
	public CustomerVehiclemodel(Long customerVehiclemodelId, Long customerId,
			Long vehiclemodelId, Date createDate) {
		this.customerVehiclemodelId = customerVehiclemodelId;
		this.customerId = customerId;
		//this.vehiclemodelId = vehiclemodelId;
		this.createDate = createDate;
	}

	// Property accessors
	@Id
	@SequenceGenerator(name="sequenceGenerator",sequenceName="seq_CUSTOMER_VEHICLEMODEL",allocationSize=1,initialValue=1)
	@GeneratedValue(generator="sequenceGenerator",strategy=GenerationType.SEQUENCE)
	@Column(name = "CUSTOMER_VEHICLEMODEL_ID", unique = true, nullable = false, precision = 16, scale = 0)
	public Long getCustomerVehiclemodelId() {
		return this.customerVehiclemodelId;
	}

	public void setCustomerVehiclemodelId(Long customerVehiclemodelId) {
		this.customerVehiclemodelId = customerVehiclemodelId;
	}

	@Column(name = "CUSTOMER_ID", precision = 16, scale = 0)
	public Long getCustomerId() {
		return this.customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	
	/*@Column(name = "VEHICLEMODEL_ID", precision = 16, scale = 0)
	public Long getVehiclemodelId() {
		return this.vehiclemodelId;
	}

	public void setVehiclemodelId(Long vehiclemodelId) {
		this.vehiclemodelId = vehiclemodelId;
	}*/
	
	@OneToOne  
    @JoinColumn(name="VEHICLEMODEL_ID",referencedColumnName="id",unique=true)
	public PubVehiclemodel getModel() {
		return model;
	}

	public void setModel(PubVehiclemodel model) {
		this.model = model;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATE_DATE", length = 12)
	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

}