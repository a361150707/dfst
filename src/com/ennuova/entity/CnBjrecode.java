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
 * 报警记录
 */
@Entity
@Table(name = "CN_BJRECODE")
public class CnBjrecode implements java.io.Serializable {

	// Fields

	private Long id;
	private PubAlarmsetting pubAlarmsetting;
	private PubCarinfo pubCarinfo;
	private String fcph;
	private String fxxid;
	private String fxxnr;
	private Timestamp fcreatetime;
	private Long ftstamp;

	// Constructors

	/** default constructor */
	public CnBjrecode() {
	}

	/** minimal constructor */
	public CnBjrecode(Long id) {
		this.id = id;
	}

	/** full constructor */
	public CnBjrecode(Long id, PubAlarmsetting pubAlarmsetting,
			PubCarinfo pubCarinfo, String fcph, String fxxid, String fxxnr,
			Timestamp fcreatetime, Long ftstamp) {
		this.id = id;
		this.pubAlarmsetting = pubAlarmsetting;
		this.pubCarinfo = pubCarinfo;
		this.fcph = fcph;
		this.fxxid = fxxid;
		this.fxxnr = fxxnr;
		this.fcreatetime = fcreatetime;
		this.ftstamp = ftstamp;
	}

	// Property accessors
	@Id
	@SequenceGenerator(name="sequenceGenerator",sequenceName="seq_CN_BJRECODE",allocationSize=1,initialValue=1 )
	@GeneratedValue(generator="sequenceGenerator",strategy=GenerationType.SEQUENCE)
	@Column(name = "ID", unique = true, nullable = false, precision = 16, scale = 0)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "FXXNAME")
	public PubAlarmsetting getPubAlarmsetting() {
		return this.pubAlarmsetting;
	}

	public void setPubAlarmsetting(PubAlarmsetting pubAlarmsetting) {
		this.pubAlarmsetting = pubAlarmsetting;
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

	@Column(name = "FXXID", length = 50)
	public String getFxxid() {
		return this.fxxid;
	}

	public void setFxxid(String fxxid) {
		this.fxxid = fxxid;
	}

	@Column(name = "FXXNR", length = 500)
	public String getFxxnr() {
		return this.fxxnr;
	}

	public void setFxxnr(String fxxnr) {
		this.fxxnr = fxxnr;
	}

	@Column(name = "FCREATETIME", length = 7)
	public Timestamp getFcreatetime() {
		return this.fcreatetime;
	}

	public void setFcreatetime(Timestamp fcreatetime) {
		this.fcreatetime = fcreatetime;
	}

	@Column(name = "FTSTAMP")
	public Long getFtstamp() {
		return this.ftstamp;
	}

	public void setFtstamp(Long ftstamp) {
		this.ftstamp = ftstamp;
	}

	@Override
	public String toString() {
		return "CnBjrecode [id=" + id + ", pubCarinfo=" + pubCarinfo.getId()
				+ ", fcph=" + fcph + ", fxxid=" + fxxid + ", fxxnr=" + fxxnr
				+ ", fcreatetime=" + fcreatetime + ", ftstamp=" + ftstamp + "]";
	}
	
	
}