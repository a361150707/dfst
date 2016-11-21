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

import org.hibernate.annotations.GenericGenerator;

/**
 * PubBdstore entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "PUB_BDSTORE")
public class PubBdstore implements java.io.Serializable {

	// Fields

	/**
	 * @Description: TODO
	 * @author felix
	 * @date 2015-12-14
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private PubCustomer pubCustomer;
	private Long fstore;
	private BigDecimal fdefault;

	// Constructors

	/** default constructor */
	public PubBdstore() {
	}

	/** full constructor */
	public PubBdstore(PubCustomer pubCustomer, Long fstore, BigDecimal fdefault) {
		this.pubCustomer = pubCustomer;
		this.fstore = fstore;
		this.fdefault = fdefault;
	}

	// Property accessors
	@Id
	@SequenceGenerator(name="sequenceGenerator",sequenceName="seq_PUB_BDSTORE",allocationSize=1,initialValue=1)
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

	@Column(name = "FSTORE", precision = 16, scale = 0)
	public Long getFstore() {
		return this.fstore;
	}

	public void setFstore(Long fstore) {
		this.fstore = fstore;
	}

	@Column(name = "FDEFAULT", precision = 22, scale = 0)
	public BigDecimal getFdefault() {
		return this.fdefault;
	}

	public void setFdefault(BigDecimal fdefault) {
		this.fdefault = fdefault;
	}

}