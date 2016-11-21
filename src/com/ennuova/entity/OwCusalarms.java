package com.ennuova.entity;

import java.math.BigDecimal;

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
import javax.persistence.Transient;

/**
 * OwCusalarms entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "OW_CUSALARMS")
public class OwCusalarms implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private PubCustomer pubCustomer;
	private PubAlarmsetting pubAlarmsetting;
	
	
	private Long fswitch;
	
	private Long aId;
	private Long cusId;
	private String fname;
	private String ftype;
	private String fdiscribe;
	private boolean swVal;
	

	
	
	
	// Constructors

	@Transient
	public boolean isSwVal() {
		return swVal;
	}
	
	public void setSwVal(boolean swVal) {
		this.swVal = swVal;
	}
	
	@Transient
	public Long getaId() {
		return aId;
	}

	public void setaId(Long aId) {
		this.aId = aId;
	}

	@Transient
	public Long getCusId() {
		return cusId;
	}

	public void setCusId(Long cusId) {
		this.cusId = cusId;
	}

	@Transient
	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}
	
	@Transient
	public String getFtype() {
		return ftype;
	}

	public void setFtype(String ftype) {
		this.ftype = ftype;
	}

	@Transient
	public String getFdiscribe() {
		return fdiscribe;
	}

	
	public void setFdiscribe(String fdiscribe) {
		this.fdiscribe = fdiscribe;
	}

	/** default constructor */
	public OwCusalarms() {
	}

	/** full constructor */
	public OwCusalarms(PubCustomer pubCustomer, PubAlarmsetting pubAlarmsetting) {
		this.pubCustomer = pubCustomer;
		this.pubAlarmsetting = pubAlarmsetting;
	}

	// Property accessors
	@Id
	@SequenceGenerator(name="sequenceGenerator",sequenceName="seq_OW_CUSALARMS",allocationSize=1,initialValue=1)
	@GeneratedValue(generator="sequenceGenerator",strategy=GenerationType.SEQUENCE)
	@Column(name = "ID", unique = true, nullable = false, precision = 16, scale = 0)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "FCUSTOMER")
	public PubCustomer getPubCustomer() {
		return this.pubCustomer;
	}

	public void setPubCustomer(PubCustomer pubCustomer) {
		this.pubCustomer = pubCustomer;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "FALARM")
	public PubAlarmsetting getPubAlarmsetting() {
		return this.pubAlarmsetting;
	}

	public void setPubAlarmsetting(PubAlarmsetting pubAlarmsetting) {
		this.pubAlarmsetting = pubAlarmsetting;
	}
	

	@Column(name = "FSWITCH", precision = 22, scale = 0)
	public Long getFswitch() {
		return this.fswitch;
	}

	public void setFswitch(Long fswitch) {
		this.fswitch = fswitch;
	}

}