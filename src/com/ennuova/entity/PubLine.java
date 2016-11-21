	package com.ennuova.entity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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
import javax.persistence.Transient;

/**
 * PubLine entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "PUB_LINE")
public class PubLine implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private PubBrand pubBrand;
	private String fnumber;
	private String fname;
	private String sPicture;
	private String sContent;
	private String mailboxCapacity; //邮箱容量
	private Set<PubVehiclemodel> pubVehiclemodels = new HashSet<PubVehiclemodel>(
			0);
	private Set<PubCarinfo> pubCarinfos = new HashSet<PubCarinfo>(0);

	
	private List<PubVehiclemodel> cxing =new ArrayList<PubVehiclemodel>();
	// Constructors

	/** default constructor */
	public PubLine() {
	}

	/** full constructor */
	public PubLine(PubBrand pubBrand, String fnumber, String fname,
			Set<PubVehiclemodel> pubVehiclemodels, Set<PubCarinfo> pubCarinfos) {
		this.pubBrand = pubBrand;
		this.fnumber = fnumber;
		this.fname = fname;
		this.pubVehiclemodels = pubVehiclemodels;
		this.pubCarinfos = pubCarinfos;
	}

	@Transient
	public List<PubVehiclemodel> getCxing() {
		return cxing;
	}

	public void setCxing(List<PubVehiclemodel> cxing) {
		this.cxing = cxing;
	}

	// Property accessors
	@Id
	@SequenceGenerator(name="sequenceGenerator",sequenceName="seq_PUB_LINE",allocationSize=1,initialValue=1)
	@GeneratedValue(generator="sequenceGenerator",strategy=GenerationType.SEQUENCE)
	@Column(name = "ID", unique = true, nullable = false, precision = 16, scale = 0)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "FBRAND")
	public PubBrand getPubBrand() {
		return this.pubBrand;
	}

	public void setPubBrand(PubBrand pubBrand) {
		this.pubBrand = pubBrand;
	}

	@Column(name = "FNUMBER", length = 30)
	public String getFnumber() {
		return this.fnumber;
	}

	public void setFnumber(String fnumber) {
		this.fnumber = fnumber;
	}

	@Column(name = "FNAME", length = 50)
	public String getFname() {
		return this.fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}
	@Column(name = "S_PICTURE", length = 300)	
	public String getsPicture() {
		return sPicture;
	}

	public void setsPicture(String sPicture) {
		this.sPicture = sPicture;
	}
	
	@Column(name = "MAILBOX_CAPACITY", length = 10)
	public String getMailboxCapacity() {
		return mailboxCapacity;
	}

	public void setMailboxCapacity(String mailboxCapacity) {
		this.mailboxCapacity = mailboxCapacity;
	}

	@Column(name = "S_CONTENT", length = 300)
	public String getsContent() {
		return sContent;
	}

	public void setsContent(String sContent) {
		this.sContent = sContent;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "pubLine")
	public Set<PubVehiclemodel> getPubVehiclemodels() {
		return this.pubVehiclemodels;
	}

	public void setPubVehiclemodels(Set<PubVehiclemodel> pubVehiclemodels) {
		this.pubVehiclemodels = pubVehiclemodels;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "pubLine")
	public Set<PubCarinfo> getPubCarinfos() {
		return this.pubCarinfos;
	}

	public void setPubCarinfos(Set<PubCarinfo> pubCarinfos) {
		this.pubCarinfos = pubCarinfos;
	}

}