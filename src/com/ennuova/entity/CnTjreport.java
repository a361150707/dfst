package com.ennuova.entity;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

/**
 * CnTjreport entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "CN_TJREPORT")
public class CnTjreport implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private PubCarinfo pubCarinfo;
	private String fcph;
	private Timestamp ftjtime;
	private Long fzgzs;
	private Long ftstamp;
	// Constructors

	/** default constructor */
	public CnTjreport() {
	}

	/** minimal constructor */
	public CnTjreport(Long id) {
		this.id = id;
	}



	// Property accessors
	@Id
	@SequenceGenerator(name="sequenceGenerator",sequenceName="seq_CN_TJREPORT",allocationSize=1,initialValue=1)
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

	@Column(name = "FTJTIME", length = 15)
	public Timestamp getFtjtime() {
		return this.ftjtime;
	}

	public void setFtjtime(Timestamp ftjtime) {
		this.ftjtime = ftjtime;
	}

	@Column(name = "FZGZS", precision = 16, scale = 0)
	public Long getFzgzs() {
		return this.fzgzs;
	}

	public void setFzgzs(Long fzgzs) {
		this.fzgzs = fzgzs;
	}

	@Column(name = "FTSTAMP", precision = 16, scale = 0)
	public Long getFtstamp() {
		return this.ftstamp;
	}

	public void setFtstamp(Long ftstamp) {
		this.ftstamp = ftstamp;
	}	
}