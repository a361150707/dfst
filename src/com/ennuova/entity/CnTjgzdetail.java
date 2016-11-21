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

import org.hibernate.annotations.GenericGenerator;

/**
 * CnTjgzdetail entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "CN_TJGZDETAIL")
public class CnTjgzdetail implements java.io.Serializable {

	// Fields

	private Long id;
	private CnTjreport cnTjreport;
	private String gzid;
	private String gznr;
	private String state;
	private String gzxt;
	private String clearstate;
	private String code;

	// Constructors

	/** default constructor */
	public CnTjgzdetail() {
	}

	/** minimal constructor */
	public CnTjgzdetail(Long id) {
		this.id = id;
	}

	/** full constructor */
	public CnTjgzdetail(Long id, CnTjreport cnTjreport, String gzid,
			String gznr, String state, String gzxt, String clearstate,
			String code) {
		this.id = id;
		this.cnTjreport = cnTjreport;
		this.gzid = gzid;
		this.gznr = gznr;
		this.state = state;
		this.gzxt = gzxt;
		this.clearstate = clearstate;
		this.code = code;
	}

	// Property accessors
	@Id
	@SequenceGenerator(name="sequenceGenerator",sequenceName="seq_CN_TJGZDETAIL",allocationSize=1,initialValue=1)
	@GeneratedValue(generator="sequenceGenerator",strategy=GenerationType.SEQUENCE)
	@Column(name = "ID", unique = true, nullable = false, precision = 16, scale = 0)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "TJID")
	public CnTjreport getCnTjreport() {
		return this.cnTjreport;
	}

	public void setCnTjreport(CnTjreport cnTjreport) {
		this.cnTjreport = cnTjreport;
	}

	@Column(name = "GZID", length = 50)
	public String getGzid() {
		return this.gzid;
	}

	public void setGzid(String gzid) {
		this.gzid = gzid;
	}

	@Column(name = "GZNR", length = 3000)
	public String getGznr() {
		return this.gznr;
	}

	public void setGznr(String gznr) {
		this.gznr = gznr;
	}

	@Column(name = "STATE", length = 50)
	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Column(name = "GZXT", length = 50)
	public String getGzxt() {
		return this.gzxt;
	}

	public void setGzxt(String gzxt) {
		this.gzxt = gzxt;
	}

	@Column(name = "CLEARSTATE", length = 50)
	public String getClearstate() {
		return this.clearstate;
	}

	public void setClearstate(String clearstate) {
		this.clearstate = clearstate;
	}

	@Column(name = "CODE", length = 50)
	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}