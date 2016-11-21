package com.ennuova.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;
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

import org.hibernate.annotations.GenericGenerator;

/**
 * CnDzwl entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "CN_DZWL")
public class CnDzwl implements java.io.Serializable {

	// Fields

	private Long id;
	private PubCarinfo pubCarinfo;
	private String fname;
	private int fsrsc;
	private Date fcreatetime;
	private Double flng;
	private Double flat;
	private Double fradius;
	private int feffect;

	// Constructors

	/** default constructor */
	public CnDzwl() {
	}

	/** minimal constructor */
	public CnDzwl(Long id) {
		this.id = id;
	}

	// Property accessors
	@Id
	@SequenceGenerator(name="sequenceGenerator",sequenceName="seq_CN_DZWL",allocationSize=1,initialValue=1)
	@GeneratedValue(generator="sequenceGenerator",strategy=GenerationType.SEQUENCE)
	@Column(name = "ID", unique = true, nullable = false, precision = 16, scale = 0)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "FCLID")
	public PubCarinfo getPubCarinfo() {
		return pubCarinfo;
	}
	
	public void setPubCarinfo(PubCarinfo pubCarinfo) {
		this.pubCarinfo = pubCarinfo;
	}

	@Column(name = "FNAME", length = 50)
	public String getFname() {
		return this.fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	@Column(name = "FSRSC", precision = 22, scale = 0)
	public int getFsrsc() {
		return this.fsrsc;
	}

	public void setFsrsc(int fsrsc) {
		this.fsrsc = fsrsc;
	}

	@Column(name = "FCREATETIME", length = 7)
	public Date getFcreatetime() {
		return this.fcreatetime;
	}

	public void setFcreatetime(Date fcreatetime) {
		this.fcreatetime = fcreatetime;
	}

	@Column(name = "FLNG")
	public Double getFlng() {
		return this.flng;
	}

	public void setFlng(Double flng) {
		this.flng = flng;
	}

	@Column(name = "FLAT")
	public Double getFlat() {
		return this.flat;
	}

	public void setFlat(Double flat) {
		this.flat = flat;
	}

	@Column(name = "FRADIUS")
	public Double getFradius() {
		return this.fradius;
	}

	public void setFradius(Double fradius) {
		this.fradius = fradius;
	}

	@Column(name = "FEFFECT", precision = 22, scale = 0)
	public int getFeffect() {
		return this.feffect;
	}

	public void setFeffect(int feffect) {
		this.feffect = feffect;
	}

}