package com.ennuova.entity;

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * PubDictionary entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "PUB_DICTIONARY")
public class PubDictionary implements java.io.Serializable {

	// Fields

	private Long id;
	private String ftype;
	private BigDecimal fstate;
	private String fdescription;

	// Constructors

	/** default constructor */
	public PubDictionary() {
	}

	/** full constructor */
	public PubDictionary(String ftype, BigDecimal fstate, String fdescription) {
		this.ftype = ftype;
		this.fstate = fstate;
		this.fdescription = fdescription;
	}

	// Property accessors
	@Id
	@SequenceGenerator(name="sequenceGenerator",sequenceName="seq_PUB_DICTIONARY",allocationSize=1,initialValue=1)
	@GeneratedValue(generator="sequenceGenerator",strategy=GenerationType.SEQUENCE)
	@Column(name = "ID", unique = true, nullable = false, precision = 16, scale = 0)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "FTYPE", length = 30)
	public String getFtype() {
		return this.ftype;
	}

	public void setFtype(String ftype) {
		this.ftype = ftype;
	}

	@Column(name = "FSTATE", precision = 22, scale = 0)
	public BigDecimal getFstate() {
		return this.fstate;
	}

	public void setFstate(BigDecimal fstate) {
		this.fstate = fstate;
	}

	@Column(name = "FDESCRIPTION", length = 50)
	public String getFdescription() {
		return this.fdescription;
	}

	public void setFdescription(String fdescription) {
		this.fdescription = fdescription;
	}

}