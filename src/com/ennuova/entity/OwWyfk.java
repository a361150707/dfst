package com.ennuova.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * OwWyfk entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "OW_WYFK")
public class OwWyfk implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private String ffkr;
	private String ftel;
	private String fknr;
	private String fway;

	// Constructors

	/** default constructor */
	public OwWyfk() {
	}

	/** full constructor */
	public OwWyfk(String ffkr, String ftel, String fknr, String fway) {
		this.ffkr = ffkr;
		this.ftel = ftel;
		this.fknr = fknr;
		this.fway = fway;
	}

	// Property accessors
	@Id
	@SequenceGenerator(name="sequenceGenerator",sequenceName="seq_OW_WYFK",allocationSize=1,initialValue=1)
	@GeneratedValue(generator="sequenceGenerator",strategy=GenerationType.SEQUENCE)
	@Column(name = "ID", unique = true, nullable = false, precision = 16, scale = 0)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "FFKR", length = 50)
	public String getFfkr() {
		return this.ffkr;
	}

	public void setFfkr(String ffkr) {
		this.ffkr = ffkr;
	}

	@Column(name = "FTEL", length = 50)
	public String getFtel() {
		return this.ftel;
	}

	public void setFtel(String ftel) {
		this.ftel = ftel;
	}

	@Column(name = "FKNR", length = 100)
	public String getFknr() {
		return this.fknr;
	}

	public void setFknr(String fknr) {
		this.fknr = fknr;
	}

	@Column(name = "FWAY", length = 30)
	public String getFway() {
		return this.fway;
	}

	public void setFway(String fway) {
		this.fway = fway;
	}

}