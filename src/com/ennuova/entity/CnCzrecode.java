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
 * 充值记录
 */
@Entity
@Table(name = "CN_CZRECODE")
public class CnCzrecode implements java.io.Serializable {

	// Fields

	private Long id;
	private CnSiminfo cnSiminfo;
	private String fczje;
	private Timestamp fcztime;
	private String fczzlc;
	private String fczbdlc;
	private String fczqglc;

	// Constructors

	/** default constructor */
	public CnCzrecode() {
	}

	/** minimal constructor */
	public CnCzrecode(Long id) {
		this.id = id;
	}

	/** full constructor */
	public CnCzrecode(Long id, CnSiminfo cnSiminfo, String fczje,
			Timestamp fcztime, String fczzlc, String fczbdlc, String fczqglc) {
		this.id = id;
		this.cnSiminfo = cnSiminfo;
		this.fczje = fczje;
		this.fcztime = fcztime;
		this.fczzlc = fczzlc;
		this.fczbdlc = fczbdlc;
		this.fczqglc = fczqglc;
	}

	// Property accessors
	@Id
	@SequenceGenerator(name="sequenceGenerator",sequenceName="seq_CN_CZRECODE",allocationSize=1,initialValue=1)
	@GeneratedValue(generator="sequenceGenerator",strategy=GenerationType.SEQUENCE)
	@Column(name = "ID", unique = true, nullable = false, precision = 16, scale = 0)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "FSIM")
	public CnSiminfo getCnSiminfo() {
		return this.cnSiminfo;
	}

	public void setCnSiminfo(CnSiminfo cnSiminfo) {
		this.cnSiminfo = cnSiminfo;
	}

	@Column(name = "FCZJE")
	public String getFczje() {
		return this.fczje;
	}

	public void setFczje(String fczje) {
		this.fczje = fczje;
	}

	@Column(name = "FCZTIME", length = 7)
	public Timestamp getFcztime() {
		return this.fcztime;
	}

	public void setFcztime(Timestamp fcztime) {
		this.fcztime = fcztime;
	}

	@Column(name = "FCZZLC")
	public String getFczzlc() {
		return this.fczzlc;
	}

	public void setFczzlc(String fczzlc) {
		this.fczzlc = fczzlc;
	}

	@Column(name = "FCZBDLC")
	public String getFczbdlc() {
		return this.fczbdlc;
	}

	public void setFczbdlc(String fczbdlc) {
		this.fczbdlc = fczbdlc;
	}

	@Column(name = "FCZQGLC")
	public String getFczqglc() {
		return this.fczqglc;
	}

	public void setFczqglc(String fczqglc) {
		this.fczqglc = fczqglc;
	}

}