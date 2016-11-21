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

/**
 * OwDiscountindex entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "OW_DISCOUNTINDEX")
public class OwDiscountindex implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private OwDiscountaction owDiscountaction;
	private String fpicture;
	private BigDecimal fsort;
	private BigDecimal fstate;
	
	private Long discountId;
	

	// Constructors

	public Long getDiscountId() {
		return discountId;
	}

	public void setDiscountId(Long discountId) {
		this.discountId = discountId;
	}

	/** default constructor */
	public OwDiscountindex() {
	}

	/** full constructor */
	public OwDiscountindex(OwDiscountaction owDiscountaction, String fpicture,
			BigDecimal fsort, BigDecimal fstate) {
		this.owDiscountaction = owDiscountaction;
		this.fpicture = fpicture;
		this.fsort = fsort;
		this.fstate = fstate;
	}

	// Property accessors
	@Id
	@SequenceGenerator(name="sequenceGenerator",sequenceName="seq_OW_DISCOUNTINDEX",allocationSize=1,initialValue=1)
	@GeneratedValue(generator="sequenceGenerator",strategy=GenerationType.SEQUENCE)
	@Column(name = "ID", unique = true, nullable = false, precision = 16, scale = 0)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "FREL_DISCOUNT")
	public OwDiscountaction getOwDiscountaction() {
		return this.owDiscountaction;
	}

	public void setOwDiscountaction(OwDiscountaction owDiscountaction) {
		this.owDiscountaction = owDiscountaction;
	}

	@Column(name = "FPICTURE", length = 256)
	public String getFpicture() {
		return this.fpicture;
	}

	public void setFpicture(String fpicture) {
		this.fpicture = fpicture;
	}

	@Column(name = "FSORT", precision = 22, scale = 0)
	public BigDecimal getFsort() {
		return this.fsort;
	}

	public void setFsort(BigDecimal fsort) {
		this.fsort = fsort;
	}

	@Column(name = "FSTATE", precision = 22, scale = 0)
	public BigDecimal getFstate() {
		return this.fstate;
	}

	public void setFstate(BigDecimal fstate) {
		this.fstate = fstate;
	}

}