package com.ennuova.entity;

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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

/**
 * 行程记录表
 */
@Entity
@Table(name = "CN_XCRECODE")
public class CnXcrecode implements java.io.Serializable {

	// Fields

	private Long id;
	private PubCarinfo pubCarinfo;
	private String fcph;
	private Timestamp fbegintime;
	private Timestamp fendtime;
	private Double fbeginjd;
	private Timestamp fcreatetime;
	private Timestamp fbeginsjc;
	private Timestamp fendsjc;
	private String fjyw;
	private Double fbeginwd;
	private Double fendjd;
	private Double fendwd;
	private Double fbcxslc;
	private Double fbcxsyh;
	private Long fbeginflag;
	private Long fendflag;
	private String fxcxh;

	// Constructors

	/** default constructor */
	public CnXcrecode() {
	}

	/** minimal constructor */
	public CnXcrecode(Long id) {
		this.id = id;
	}

	/** full constructor */
	public CnXcrecode(Long id, PubCarinfo pubCarinfo, String fcph,
			Timestamp fbegintime, Timestamp fendtime, Double fbeginjd,
			Timestamp fcreatetime, Timestamp fbeginsjc, Timestamp fendsjc,
			String fjyw, Double fbeginwd, Double fendjd, Double fendwd,
			Double fbcxslc, Double fbcxsyh, Long fbeginflag,
			Long fendflag, String fxcxh) {
		this.id = id;
		this.pubCarinfo = pubCarinfo;
		this.fcph = fcph;
		this.fbegintime = fbegintime;
		this.fendtime = fendtime;
		this.fbeginjd = fbeginjd;
		this.fcreatetime = fcreatetime;
		this.fbeginsjc = fbeginsjc;
		this.fendsjc = fendsjc;
		this.fjyw = fjyw;
		this.fbeginwd = fbeginwd;
		this.fendjd = fendjd;
		this.fendwd = fendwd;
		this.fbcxslc = fbcxslc;
		this.fbcxsyh = fbcxsyh;
		this.fbeginflag = fbeginflag;
		this.fendflag = fendflag;
		this.fxcxh = fxcxh;
	}

	// Property accessors
	@Id
	@SequenceGenerator(name="sequenceGenerator",sequenceName="seq_CN_XCRECODE",allocationSize=1,initialValue=1)
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
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "FBEGINTIME", length = 7)
	public Date getFbegintime() {
		return this.fbegintime;
	}

	public void setFbegintime(Timestamp fbegintime) {
		this.fbegintime = fbegintime;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "FENDTIME", length = 7)
	public Date getFendtime() {
		return this.fendtime;
	}

	public void setFendtime(Timestamp fendtime) {
		this.fendtime = fendtime;
	}

	@Column(name = "FBEGINJD")
	public Double getFbeginjd() {
		return this.fbeginjd;
	}

	public void setFbeginjd(Double fbeginjd) {
		this.fbeginjd = fbeginjd;
	}

	@Column(name = "FCREATETIME", length = 7)
	public Timestamp getFcreatetime() {
		return this.fcreatetime;
	}

	public void setFcreatetime(Timestamp fcreatetime) {
		this.fcreatetime = fcreatetime;
	}

	@Column(name = "FBEGINSJC")
	public Timestamp getFbeginsjc() {
		return this.fbeginsjc;
	}

	public void setFbeginsjc(Timestamp fbeginsjc) {
		this.fbeginsjc = fbeginsjc;
	}

	@Column(name = "FENDSJC")
	public Timestamp getFendsjc() {
		return this.fendsjc;
	}

	public void setFendsjc(Timestamp fendsjc) {
		this.fendsjc = fendsjc;
	}

	@Column(name = "FJYW", length = 100)
	public String getFjyw() {
		return this.fjyw;
	}

	public void setFjyw(String fjyw) {
		this.fjyw = fjyw;
	}

	@Column(name = "FBEGINWD")
	public Double getFbeginwd() {
		return this.fbeginwd;
	}

	public void setFbeginwd(Double fbeginwd) {
		this.fbeginwd = fbeginwd;
	}

	@Column(name = "FENDJD")
	public Double getFendjd() {
		return this.fendjd;
	}

	public void setFendjd(Double fendjd) {
		this.fendjd = fendjd;
	}

	@Column(name = "FENDWD")
	public Double getFendwd() {
		return this.fendwd;
	}

	public void setFendwd(Double fendwd) {
		this.fendwd = fendwd;
	}

	@Column(name = "FBCXSLC")
	public Double getFbcxslc() {
		return this.fbcxslc;
	}

	public void setFbcxslc(Double fbcxslc) {
		this.fbcxslc = fbcxslc;
	}

	@Column(name = "FBCXSYH")
	public Double getFbcxsyh() {
		return this.fbcxsyh;
	}

	public void setFbcxsyh(Double fbcxsyh) {
		this.fbcxsyh = fbcxsyh;
	}

	@Column(name = "FBEGINFLAG", precision = 16, scale = 0)
	public Long getFbeginflag() {
		return this.fbeginflag;
	}

	public void setFbeginflag(Long fbeginflag) {
		this.fbeginflag = fbeginflag;
	}

	@Column(name = "FENDFLAG", precision = 16, scale = 0)
	public Long getFendflag() {
		return this.fendflag;
	}

	public void setFendflag(Long fendflag) {
		this.fendflag = fendflag;
	}

	@Column(name = "FXCXH", length = 100)
	public String getFxcxh() {
		return this.fxcxh;
	}

	public void setFxcxh(String fxcxh) {
		this.fxcxh = fxcxh;
	}

	@Override
	public String toString() {
		return "CnXcrecode [id=" + id + ", pubCarinfo=" + pubCarinfo.getId()
				+ ", fcph=" + fcph + ", fbegintime=" + fbegintime
				+ ", fendtime=" + fendtime + ", fbeginjd=" + fbeginjd
				+ ", fcreatetime=" + fcreatetime + ", fbeginsjc=" + fbeginsjc
				+ ", fendsjc=" + fendsjc + ", fjyw=" + fjyw + ", fbeginwd="
				+ fbeginwd + ", fendjd=" + fendjd + ", fendwd=" + fendwd
				+ ", fbcxslc=" + fbcxslc + ", fbcxsyh=" + fbcxsyh
				+ ", fbeginflag=" + fbeginflag + ", fendflag=" + fendflag
				+ ", fxcxh=" + fxcxh + "]";
	}
	
	
	

}