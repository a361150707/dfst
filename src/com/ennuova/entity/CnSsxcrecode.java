package com.ennuova.entity;

import java.sql.Timestamp;

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
 * 实时行程信息
 */
@Entity
@Table(name = "CN_SSXCRECODE")
public class CnSsxcrecode implements java.io.Serializable {

	// Fields

	private Long id;
	private PubCarinfo pubCarinfo;
	private String fcph;
	private Timestamp fgpstime;
	private Double fgpsfx;
	private Double fgpsspe;
	private Double fgpsh;
	private Double fgpsjd;
	private Double fjd;
	private Double fwd;

	// Constructors

	/** default constructor */
	public CnSsxcrecode() {
	}

	/** minimal constructor */
	public CnSsxcrecode(Long id) {
		this.id = id;
	}

	/** full constructor */
	public CnSsxcrecode(Long id, PubCarinfo pubCarinfo, String fcph,
			Timestamp fgpstime, Double fgpsfx, Double fgpsspe, Double fgpsh,
			Double fgpsjd, Double fjd, Double fwd) {
		this.id = id;
		this.pubCarinfo = pubCarinfo;
		this.fcph = fcph;
		this.fgpstime = fgpstime;
		this.fgpsfx = fgpsfx;
		this.fgpsspe = fgpsspe;
		this.fgpsh = fgpsh;
		this.fgpsjd = fgpsjd;
		this.fjd = fjd;
		this.fwd = fwd;
	}

	// Property accessors
	@Id
	@SequenceGenerator(name="sequenceGenerator",sequenceName="seq_CN_SSXCRECODE",allocationSize=1,initialValue=1)
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
		return this.pubCarinfo;
	}

	public void setPubCarinfo(PubCarinfo pubCarinfo) {
		this.pubCarinfo = pubCarinfo;
	}

	@Column(name = "FCPH", length = 50)
	public String getFcph() {
		return this.fcph;
	}

	public void setFcph(String fcph) {
		this.fcph = fcph;
	}

	@Column(name = "FGPSTIME", length = 7)
	public Timestamp getFgpstime() {
		return this.fgpstime;
	}

	public void setFgpstime(Timestamp fgpstime) {
		this.fgpstime = fgpstime;
	}

	@Column(name = "FGPSFX")
	public Double getFgpsfx() {
		return this.fgpsfx;
	}

	public void setFgpsfx(Double fgpsfx) {
		this.fgpsfx = fgpsfx;
	}

	@Column(name = "FGPSSPE")
	public Double getFgpsspe() {
		return this.fgpsspe;
	}

	public void setFgpsspe(Double fgpsspe) {
		this.fgpsspe = fgpsspe;
	}

	@Column(name = "FGPSH")
	public Double getFgpsh() {
		return this.fgpsh;
	}

	public void setFgpsh(Double fgpsh) {
		this.fgpsh = fgpsh;
	}

	@Column(name = "FGPSJD")
	public Double getFgpsjd() {
		return this.fgpsjd;
	}

	public void setFgpsjd(Double fgpsjd) {
		this.fgpsjd = fgpsjd;
	}

	@Column(name = "FJD")
	public Double getFjd() {
		return this.fjd;
	}

	public void setFjd(Double fjd) {
		this.fjd = fjd;
	}

	@Column(name = "FWD")
	public Double getFwd() {
		return this.fwd;
	}

	public void setFwd(Double fwd) {
		this.fwd = fwd;
	}

	@Override
	public String toString() {
		return "CnSsxcrecode [id=" + id + ", pubCarinfo=" + pubCarinfo.getId()
				+ ", fcph=" + fcph + ", fgpstime=" + fgpstime + ", fgpsfx="
				+ fgpsfx + ", fgpsspe=" + fgpsspe + ", fgpsh=" + fgpsh
				+ ", fgpsjd=" + fgpsjd + ", fjd=" + fjd + ", fwd=" + fwd + "]";
	}
	

}