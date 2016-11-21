package com.ennuova.entity;

import java.math.BigDecimal;
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

/**
 * OwDiscountaction entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "OW_DISCOUNTACTION")
public class OwDiscountaction implements java.io.Serializable {

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
	private Long fcompany;
	private BigDecimal fzhiding;
	private BigDecimal fstate;
	private BigDecimal fviewcount;
	private Date flastview;
	private String fnrText;
	private Set<OwDiscountindex> owDiscountindexes = new HashSet<OwDiscountindex>(
			0);

	private String uname;
	
	
	// Constructors

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	/** default constructor */
	public OwDiscountaction() {
	}

	/** full constructor */
	public OwDiscountaction(PubUser pubUser, String ftitle, String fnrPic,
			Date fbtime, Long fcompany, BigDecimal fzhiding, BigDecimal fstate,
			BigDecimal fviewcount, Date flastview, String fnrText,
			Set<OwDiscountindex> owDiscountindexes) {
		this.pubUser = pubUser;
		this.ftitle = ftitle;
		this.fnrPic = fnrPic;
		this.fbtime = fbtime;
		this.fcompany = fcompany;
		this.fzhiding = fzhiding;
		this.fstate = fstate;
		this.fviewcount = fviewcount;
		this.flastview = flastview;
		this.fnrText = fnrText;
		this.owDiscountindexes = owDiscountindexes;
	}

	// Property accessors
	@Id
	@SequenceGenerator(name="sequenceGenerator",sequenceName="seq_OW_DISCOUNTACTION",allocationSize=1,initialValue=1)
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

	@Temporal(TemporalType.DATE)
	@Column(name = "FBTIME", length = 7)
	public Date getFbtime() {
		return this.fbtime;
	}

	public void setFbtime(Date fbtime) {
		this.fbtime = fbtime;
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

	@Column(name = "FVIEWCOUNT", precision = 22, scale = 0)
	public BigDecimal getFviewcount() {
		return this.fviewcount;
	}

	public void setFviewcount(BigDecimal fviewcount) {
		this.fviewcount = fviewcount;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "FLASTVIEW", length = 7)
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

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "owDiscountaction")
	public Set<OwDiscountindex> getOwDiscountindexes() {
		return this.owDiscountindexes;
	}

	public void setOwDiscountindexes(Set<OwDiscountindex> owDiscountindexes) {
		this.owDiscountindexes = owDiscountindexes;
	}

}