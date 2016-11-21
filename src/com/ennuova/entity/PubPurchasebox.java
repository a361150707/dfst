package com.ennuova.entity;

import java.math.BigDecimal;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * PubPurchasebox entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "PUB_PURCHASEBOX")
public class PubPurchasebox implements java.io.Serializable {

	// Fields

	private Long id;
	private PubCustomer pubCustomer;
	private PubBox pubBox;
	private Date fbuydate;
	private BigDecimal fmoney;
	private Long fcompany;
	private String ftel;

	// Constructors

	/** default constructor */
	public PubPurchasebox() {
	}

	/** full constructor */
	public PubPurchasebox(PubCustomer pubCustomer, PubBox pubBox,
			Date fbuydate, BigDecimal fmoney, Long fcompany, String ftel) {
		this.pubCustomer = pubCustomer;
		this.pubBox = pubBox;
		this.fbuydate = fbuydate;
		this.fmoney = fmoney;
		this.fcompany = fcompany;
		this.ftel = ftel;
	}

	// Property accessors
	@Id
	@SequenceGenerator(name="sequenceGenerator",sequenceName="seq_PUB_PURCHASEBOX",allocationSize=1,initialValue=1)
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
	@JoinColumn(name = "FBOXCODE")
	public PubBox getPubBox() {
		return this.pubBox;
	}

	public void setPubBox(PubBox pubBox) {
		this.pubBox = pubBox;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "FBUYDATE", length = 7)
	public Date getFbuydate() {
		return this.fbuydate;
	}

	public void setFbuydate(Date fbuydate) {
		this.fbuydate = fbuydate;
	}

	@Column(name = "FMONEY", precision = 22, scale = 0)
	public BigDecimal getFmoney() {
		return this.fmoney;
	}

	public void setFmoney(BigDecimal fmoney) {
		this.fmoney = fmoney;
	}

	@Column(name = "FCOMPANY", precision = 16, scale = 0)
	public Long getFcompany() {
		return this.fcompany;
	}

	public void setFcompany(Long fcompany) {
		this.fcompany = fcompany;
	}

	@Column(name = "FTEL", length = 50)
	public String getFtel() {
		return this.ftel;
	}

	public void setFtel(String ftel) {
		this.ftel = ftel;
	}

}