package com.ennuova.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * PubArea entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "PUB_AREA")
public class PubArea implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private Long fid;
	private String fname;
	private BigDecimal fgrade;
	private String fdncode;

	// Constructors

	/** default constructor */
	public PubArea() {
	}

	/** full constructor */
	public PubArea(Long fid, String fname, BigDecimal fgrade, String fdncode) {
		this.fid = fid;
		this.fname = fname;
		this.fgrade = fgrade;
		this.fdncode = fdncode;
	}

	// Property accessors
	@Id
	@SequenceGenerator(name="sequenceGenerator",sequenceName="seq_PUB_AREA",allocationSize=1,initialValue=1)
	@GeneratedValue(generator="sequenceGenerator",strategy=GenerationType.SEQUENCE)
	@Column(name = "ID", unique = true, nullable = false, precision = 16, scale = 0)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "FID", precision = 16, scale = 0)
	public Long getFid() {
		return this.fid;
	}

	public void setFid(Long fid) {
		this.fid = fid;
	}

	@Column(name = "FNAME", length = 50)
	public String getFname() {
		return this.fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	@Column(name = "FGRADE", precision = 22, scale = 0)
	public BigDecimal getFgrade() {
		return this.fgrade;
	}

	public void setFgrade(BigDecimal fgrade) {
		this.fgrade = fgrade;
	}

	@Column(name = "FDNCODE", length = 50)
	public String getFdncode() {
		return this.fdncode;
	}

	public void setFdncode(String fdncode) {
		this.fdncode = fdncode;
	}

}