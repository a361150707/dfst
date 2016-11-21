package com.ennuova.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * PubOdbvehicle entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "PUB_ODBVEHICLE")
public class PubOdbvehicle implements java.io.Serializable {

	// Fields

	private Long id;
	private PubLine pubLine;
	private String cartype;
	private String carmodel;
	private String diagcarmodel;
	private String isabroad;
	private String lanid;
	private String detailid;
	private String detailname;
	private String vehiclemodelid;
	
	// Constructors

	/** default constructor */
	public PubOdbvehicle() {
	}

	/** minimal constructor */
	public PubOdbvehicle(Long id) {
		this.id = id;
	}

	// Property accessors
	@Id
	@SequenceGenerator(name="sequenceGenerator",sequenceName="seq_PUB_ODBVEHICLE",allocationSize=1,initialValue=1)
	@GeneratedValue(generator="sequenceGenerator",strategy=GenerationType.SEQUENCE)
	@Column(name = "ID", unique = true, nullable = false, precision = 16, scale = 0)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "VEHICLEMODEL")
	public PubLine getPubLine() {
		return this.pubLine;
	}

	public void setPubLine(PubLine pubLine) {
		this.pubLine = pubLine;
	}
	@Column(name = "CARTYPE", length = 50)
	public String getCartype() {
		return this.cartype;
	}

	public void setCartype(String cartype) {
		this.cartype = cartype;
	}

	@Column(name = "CARMODEL", length = 50)
	public String getCarmodel() {
		return this.carmodel;
	}

	public void setCarmodel(String carmodel) {
		this.carmodel = carmodel;
	}

	@Column(name = "DIAGCARMODEL", length = 50)
	public String getDiagcarmodel() {
		return this.diagcarmodel;
	}

	public void setDiagcarmodel(String diagcarmodel) {
		this.diagcarmodel = diagcarmodel;
	}

	@Column(name = "ISABROAD", length = 50)
	public String getIsabroad() {
		return this.isabroad;
	}

	public void setIsabroad(String isabroad) {
		this.isabroad = isabroad;
	}

	@Column(name = "LANID", length = 50)
	public String getLanid() {
		return this.lanid;
	}

	public void setLanid(String lanid) {
		this.lanid = lanid;
	}

	@Column(name = "DETAILID", length = 50)
	public String getDetailid() {
		return this.detailid;
	}

	public void setDetailid(String detailid) {
		this.detailid = detailid;
	}

	@Column(name = "DETAILNAME", length = 50)
	public String getDetailname() {
		return this.detailname;
	}

	public void setDetailname(String detailname) {
		this.detailname = detailname;
	}

	@Column(name = "VEHICLEMODELID", length = 50)
	public String getVehiclemodelid() {
		return this.vehiclemodelid;
	}

	public void setVehiclemodelid(String vehiclemodelid) {
		this.vehiclemodelid = vehiclemodelid;
	}

}