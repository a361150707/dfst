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
 * PubRange entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "PUB_RANGE")
public class PubRange implements java.io.Serializable {

	// Fields

	private Long id;
	private BigDecimal frangeMax;
	private BigDecimal frangeMin;
	private String fdescribe;

	// Constructors

	/** default constructor */
	public PubRange() {
	}

	/** full constructor */
	public PubRange(BigDecimal frangeMax, BigDecimal frangeMin, String fdescribe) {
		this.frangeMax = frangeMax;
		this.frangeMin = frangeMin;
		this.fdescribe = fdescribe;
	}

	// Property accessors
	@Id
	@SequenceGenerator(name="sequenceGenerator",sequenceName="seq_PUB_RANGE",allocationSize=1,initialValue=1)
	@GeneratedValue(generator="sequenceGenerator",strategy=GenerationType.SEQUENCE)
	@Column(name = "ID", unique = true, nullable = false, precision = 16, scale = 0)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "FRANGE_MAX", precision = 22, scale = 0)
	public BigDecimal getFrangeMax() {
		return this.frangeMax;
	}

	public void setFrangeMax(BigDecimal frangeMax) {
		this.frangeMax = frangeMax;
	}

	@Column(name = "FRANGE_MIN", precision = 22, scale = 0)
	public BigDecimal getFrangeMin() {
		return this.frangeMin;
	}

	public void setFrangeMin(BigDecimal frangeMin) {
		this.frangeMin = frangeMin;
	}

	@Column(name = "FDESCRIBE", length = 50)
	public String getFdescribe() {
		return this.fdescribe;
	}

	public void setFdescribe(String fdescribe) {
		this.fdescribe = fdescribe;
	}

}