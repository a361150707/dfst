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

/**
 * OwJlpzsz entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "OW_JLPZSZ")
public class OwJlpzsz implements java.io.Serializable {

	// Fields

	private Long id;
	private PubCustomer pubCustomer;
	private String flinker;
	private String ftel;

	// Constructors

	/** default constructor */
	public OwJlpzsz() {
	}

	/** full constructor */
	public OwJlpzsz(PubCustomer pubCustomer, String flinker, String ftel) {
		this.pubCustomer = pubCustomer;
		this.flinker = flinker;
		this.ftel = ftel;
	}

	// Property accessors
	@Id
	@SequenceGenerator(name="sequenceGenerator",sequenceName="seq_OW_JLPZSZ",allocationSize=1,initialValue=1)
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

	@Column(name = "FLINKER", length = 50)
	public String getFlinker() {
		return this.flinker;
	}

	public void setFlinker(String flinker) {
		this.flinker = flinker;
	}

	@Column(name = "FTEL", length = 50)
	public String getFtel() {
		return this.ftel;
	}

	public void setFtel(String ftel) {
		this.ftel = ftel;
	}

}