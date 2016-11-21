package com.ennuova.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;
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

import org.hibernate.annotations.GenericGenerator;

/**
 *故障码记录表
 */
@Entity
@Table(name = "CN_GZRECODE")
public class CnGzrecode implements java.io.Serializable {

	// Fields

	private Long id;
	private PubCarinfo pubCarinfo;
	private String fcph;
	private String fxtbs;
	private String fxtid;
	private Long fgzmsl;
	private Timestamp fhqtime;
	private String ftstamp;
	private Set<CnGzdetail> cnGzdetails = new HashSet<CnGzdetail>(0);

	// Constructors

	/** default constructor */
	public CnGzrecode() {
	}

	/** minimal constructor */
	public CnGzrecode(Long id) {
		this.id = id;
	}

	/** full constructor */
	public CnGzrecode(Long id, PubCarinfo pubCarinfo, String fcph,
			String fxtbs, String fxtid, Long fgzmsl, Timestamp fhqtime,
			String ftstamp, Set<CnGzdetail> cnGzdetails) {
		this.id = id;
		this.pubCarinfo = pubCarinfo;
		this.fcph = fcph;
		this.fxtbs = fxtbs;
		this.fxtid = fxtid;
		this.fgzmsl = fgzmsl;
		this.fhqtime = fhqtime;
		this.ftstamp = ftstamp;
		this.cnGzdetails = cnGzdetails;
	}

	// Property accessors
	@Id
	@SequenceGenerator(name="sequenceGenerator",sequenceName="seq_CN_GZRECODE",allocationSize=1,initialValue=1)
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

	@Column(name = "FXTBS", length = 50)
	public String getFxtbs() {
		return this.fxtbs;
	}

	public void setFxtbs(String fxtbs) {
		this.fxtbs = fxtbs;
	}

	@Column(name = "FXTID", precision = 16, scale = 0)
	public String getFxtid() {
		return this.fxtid;
	}

	public void setFxtid(String fxtid) {
		this.fxtid = fxtid;
	}

	@Column(name = "FGZMSL", precision = 22, scale = 0)
	public Long getFgzmsl() {
		return this.fgzmsl;
	}

	public void setFgzmsl(Long fgzmsl) {
		this.fgzmsl = fgzmsl;
	}

	@Column(name = "FHQTIME", length = 7)
	public Timestamp getFhqtime() {
		return this.fhqtime;
	}

	public void setFhqtime(Timestamp fhqtime) {
		this.fhqtime = fhqtime;
	}

	@Column(name = "FTSTAMP")
	public String getFtstamp() {
		return this.ftstamp;
	}

	public void setFtstamp(String ftstamp) {
		this.ftstamp = ftstamp;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "cnGzrecode")
	public Set<CnGzdetail> getCnGzdetails() {
		return this.cnGzdetails;
	}

	public void setCnGzdetails(Set<CnGzdetail> cnGzdetails) {
		this.cnGzdetails = cnGzdetails;
	}

	@Override
	public String toString() {
		return "CnGzrecode [id=" + id + ", pubCarinfo=" + pubCarinfo.getId()
				+ ", fcph=" + fcph + ", fxtbs=" + fxtbs + ", fxtid=" + fxtid
				+ ", fgzmsl=" + fgzmsl + ", fhqtime=" + fhqtime + ", ftstamp="
				+ ftstamp + "]";
	}

	
}