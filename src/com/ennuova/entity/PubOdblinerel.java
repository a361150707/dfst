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
 * Obd车型与系统车系对照表
 */
@Entity
@Table(name = "PUB_ODBLINEREL")
public class PubOdblinerel implements java.io.Serializable {

	// Fields

	private Long id;
	private PubOdbvehicle pubOdbvehicle;
	private PubLine pubLine;

	// Constructors

	/** default constructor */
	public PubOdblinerel() {
	}

	/** minimal constructor */
	public PubOdblinerel(Long id) {
		this.id = id;
	}

	/** full constructor */
	public PubOdblinerel(Long id, PubOdbvehicle pubOdbvehicle, PubLine pubLine) {
		this.id = id;
		this.pubOdbvehicle = pubOdbvehicle;
		this.pubLine = pubLine;
	}

	// Property accessors
	@Id
	@SequenceGenerator(name="sequenceGenerator",sequenceName="seq_PUB_ODBLINEREL",allocationSize=1,initialValue=1)
	@GeneratedValue(generator="sequenceGenerator",strategy=GenerationType.SEQUENCE)
	@Column(name = "ID", unique = true, nullable = false, precision = 16, scale = 0)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "OBDLINEID")
	public PubOdbvehicle getPubOdbvehicle() {
		return this.pubOdbvehicle;
	}

	public void setPubOdbvehicle(PubOdbvehicle pubOdbvehicle) {
		this.pubOdbvehicle = pubOdbvehicle;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "LINEID")
	public PubLine getPubLine() {
		return this.pubLine;
	}

	public void setPubLine(PubLine pubLine) {
		this.pubLine = pubLine;
	}

}