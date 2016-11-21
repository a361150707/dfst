package com.ennuova.entity;

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
import javax.persistence.Transient;

/**
 * PubBox entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "PUB_BOX")
public class PubBox implements java.io.Serializable {

	// Fields

	/**
	 * @Description: TODO
	 * @author felix
	 * @date 2015-12-17
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private PubCarinfo pubCarinfo;
	private String fxlh;
	private Long fjhyf;
	private Date fjhtime;
	private String fedition;
	private Date fbdate;
	private String fyjedition;
	private Long gps;
	private Long wifi;
	private Long blue;
	private String fpassword;
	
	private String fcarnum;//车牌号
	private Long carid;//车辆id
	private Long cardefault;//是否默认
	private Long cusid;//用户id
	private String mname;//车型名称
	
	
	

	// Constructors

	@Transient
	public String getFcarnum() {
		return fcarnum;
	}

	public void setFcarnum(String fcarnum) {
		this.fcarnum = fcarnum;
	}

	@Transient
	public Long getCarid() {
		return carid;
	}

	public void setCarid(Long carid) {
		this.carid = carid;
	}

	@Transient
	public Long getCardefault() {
		return cardefault;
	}

	public void setCardefault(Long cardefault) {
		this.cardefault = cardefault;
	}

	@Transient
	public Long getCusid() {
		return cusid;
	}

	public void setCusid(Long cusid) {
		this.cusid = cusid;
	}

	@Transient
	public String getMname() {
		return mname;
	}

	public void setMname(String mname) {
		this.mname = mname;
	}

	/** default constructor */
	public PubBox() {
	}

	/** minimal constructor */
	public PubBox(Long id) {
		this.id = id;
	}

	/** full constructor */
	public PubBox(Long id, PubCarinfo pubCarinfo, String fxlh,
			Long fjhyf, Date fjhtime, String fedition, Date fbdate,
			String fyjedition, Long gps, Long wifi,
			Long blue, String fpassword) {
		this.id = id;
		this.pubCarinfo = pubCarinfo;
		this.fxlh = fxlh;
		this.fjhyf = fjhyf;
		this.fjhtime = fjhtime;
		this.fedition = fedition;
		this.fbdate = fbdate;
		this.fyjedition = fyjedition;
		this.gps = gps;
		this.wifi = wifi;
		this.blue = blue;
		this.fpassword = fpassword;
	}

	// Property accessors
	@Id
	@SequenceGenerator(name="sequenceGenerator",sequenceName="seq_PUB_BOX",allocationSize=1,initialValue=1)
	@GeneratedValue(generator="sequenceGenerator",strategy=GenerationType.SEQUENCE)
	@Column(name = "ID", unique = true, nullable = false, precision = 16, scale = 0)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "FCLID")
	public PubCarinfo getPubCarinfo() {
		return this.pubCarinfo;
	}

	public void setPubCarinfo(PubCarinfo pubCarinfo) {
		this.pubCarinfo = pubCarinfo;
	}

	@Column(name = "FXLH", length = 50)
	public String getFxlh() {
		return this.fxlh;
	}

	public void setFxlh(String fxlh) {
		this.fxlh = fxlh;
	}

	@Column(name = "FJHYF", precision = 22, scale = 0)
	public Long getFjhyf() {
		return this.fjhyf;
	}

	public void setFjhyf(Long fjhyf) {
		this.fjhyf = fjhyf;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "FJHTIME", length = 12)
	public Date getFjhtime() {
		return this.fjhtime;
	}

	public void setFjhtime(Date fjhtime) {
		this.fjhtime = fjhtime;
	}

	@Column(name = "FEDITION", length = 50)
	public String getFedition() {
		return this.fedition;
	}

	public void setFedition(String fedition) {
		this.fedition = fedition;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "FBDATE", length = 12)
	public Date getFbdate() {
		return this.fbdate;
	}

	public void setFbdate(Date fbdate) {
		this.fbdate = fbdate;
	}

	@Column(name = "FYJEDITION", length = 50)
	public String getFyjedition() {
		return this.fyjedition;
	}

	public void setFyjedition(String fyjedition) {
		this.fyjedition = fyjedition;
	}

	@Column(name = "GPS", precision = 22, scale = 0)
	public Long getGps() {
		return this.gps;
	}

	public void setGps(Long gps) {
		this.gps = gps;
	}

	@Column(name = "WIFI", precision = 22, scale = 0)
	public Long getWifi() {
		return this.wifi;
	}

	public void setWifi(Long wifi) {
		this.wifi = wifi;
	}

	@Column(name = "BLUE", precision = 22, scale = 0)
	public Long getBlue() {
		return this.blue;
	}

	public void setBlue(Long blue) {
		this.blue = blue;
	}

	@Column(name = "FPASSWORD", length = 500)
	public String getFpassword() {
		return this.fpassword;
	}

	public void setFpassword(String fpassword) {
		this.fpassword = fpassword;
	}

}