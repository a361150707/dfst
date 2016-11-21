package com.ennuova.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
 * OwNewcar entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "OW_NEWCAR")
public class OwNewcar implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private PubUser pubUser;
	private String ftitle;
	private String fnrPic;
	private Date fbtime;
	private BigDecimal fviewcount;
	private Date flastview;
	private String fnrText;
	private Long fcompany;
	private BigDecimal fzhiding;
	private BigDecimal fstate;
	private List<OwNewcarindex> owNewcarindexes = new ArrayList<OwNewcarindex>(0);
	private String uname;//用户名

	// Constructors

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	/** default constructor */
	public OwNewcar() {
	}

	/** full constructor */
	public OwNewcar(PubUser pubUser, String ftitle, String fnrPic, Date fbtime,
			BigDecimal fviewcount, Date flastview, String fnrText,
			Long fcompany, BigDecimal fzhiding, BigDecimal fstate,
			List<OwNewcarindex> owNewcarindexes) {
		this.pubUser = pubUser;
		this.ftitle = ftitle;
		this.fnrPic = fnrPic;
		this.fbtime = fbtime;
		this.fviewcount = fviewcount;
		this.flastview = flastview;
		this.fnrText = fnrText;
		this.fcompany = fcompany;
		this.fzhiding = fzhiding;
		this.fstate = fstate;
		this.owNewcarindexes = owNewcarindexes;
	}

	// Property accessors
	@Id
	@SequenceGenerator(name="sequenceGenerator",sequenceName="seq_OW_NEWCAR",allocationSize=1,initialValue=1)
	@GeneratedValue(generator="sequenceGenerator",strategy=GenerationType.SEQUENCE)
	@Column(name = "ID", unique = true, nullable = false, precision = 16, scale = 0)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "FBUSER")
	public PubUser getPubUser() {
		return this.pubUser;
	}

	public void setPubUser(PubUser pubUser) {
		this.pubUser = pubUser;
	}

	@Column(name = "FTITLE", length = 50)
	public String getFtitle() {
		return this.ftitle;
	}

	public void setFtitle(String ftitle) {
		this.ftitle = ftitle;
	}

	@Column(name = "FNR_PIC", length = 256)
	public String getFnrPic() {
		return this.fnrPic;
	}

	public void setFnrPic(String fnrPic) {
		this.fnrPic = fnrPic;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "FBTIME", length = 12)
	public Date getFbtime() {
		return this.fbtime;
	}

	public void setFbtime(Date fbtime) {
		this.fbtime = fbtime;
	}

	@Column(name = "FVIEWCOUNT", precision = 22, scale = 0)
	public BigDecimal getFviewcount() {
		return this.fviewcount;
	}

	public void setFviewcount(BigDecimal fviewcount) {
		this.fviewcount = fviewcount;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "FLASTVIEW", length = 12)
	public Date getFlastview() {
		return this.flastview;
	}

	public void setFlastview(Date flastview) {
		this.flastview = flastview;
	}

	@Column(name = "FNR_TEXT")
	public String getFnrText() {
		return this.fnrText;
	}

	public void setFnrText(String fnrText) {
		this.fnrText = fnrText;
	}

	@Column(name = "FCOMPANY", precision = 16, scale = 0)
	public Long getFcompany() {
		return this.fcompany;
	}

	public void setFcompany(Long fcompany) {
		this.fcompany = fcompany;
	}

	@Column(name = "FZHIDING", precision = 22, scale = 0)
	public BigDecimal getFzhiding() {
		return this.fzhiding;
	}

	public void setFzhiding(BigDecimal fzhiding) {
		this.fzhiding = fzhiding;
	}

	@Column(name = "FSTATE", precision = 22, scale = 0)
	public BigDecimal getFstate() {
		return this.fstate;
	}

	public void setFstate(BigDecimal fstate) {
		this.fstate = fstate;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "owNewcar")
	public List<OwNewcarindex> getOwNewcarindexes() {
		return this.owNewcarindexes;
	}

	public void setOwNewcarindexes(List<OwNewcarindex> owNewcarindexes) {
		this.owNewcarindexes = owNewcarindexes;
	}

}