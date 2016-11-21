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
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * PubUser entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "PUB_USER")
public class PubUser implements java.io.Serializable {

	// Fields

	private Long id;
	private String userid;
	private String password;
	private String name;
	private Date lastlogin;
	private BigDecimal logins;
	private String photo;
	private Long orgid;
	private Set<OwNewsct> owNewscts = new HashSet<OwNewsct>(0);
	private Set<OwNewcar> owNewcars = new HashSet<OwNewcar>(0);
	private Set<OwDiscountaction> owDiscountactions = new HashSet<OwDiscountaction>(
			0);

	// Constructors

	/** default constructor */
	public PubUser() {
	}

	/** full constructor */
	public PubUser(String userid, String password, String name, Date lastlogin,
			BigDecimal logins, String photo, Long orgid,
			Set<OwNewsct> owNewscts, Set<OwNewcar> owNewcars,
			Set<OwDiscountaction> owDiscountactions) {
		this.userid = userid;
		this.password = password;
		this.name = name;
		this.lastlogin = lastlogin;
		this.logins = logins;
		this.photo = photo;
		this.orgid = orgid;
		this.owNewscts = owNewscts;
		this.owNewcars = owNewcars;
		this.owDiscountactions = owDiscountactions;
	}

	// Property accessors
	@Id
	@SequenceGenerator(name="sequenceGenerator",sequenceName="seq_PUB_USER",allocationSize=1,initialValue=1)
	@GeneratedValue(generator="sequenceGenerator",strategy=GenerationType.SEQUENCE)
	@Column(name = "ID", unique = true, nullable = false, precision = 16, scale = 0)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "USERID", length = 50)
	public String getUserid() {
		return this.userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	@Column(name = "PASSWORD", length = 50)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "NAME", length = 30)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "LASTLOGIN", length = 7)
	public Date getLastlogin() {
		return this.lastlogin;
	}

	public void setLastlogin(Date lastlogin) {
		this.lastlogin = lastlogin;
	}

	@Column(name = "LOGINS", precision = 22, scale = 0)
	public BigDecimal getLogins() {
		return this.logins;
	}

	public void setLogins(BigDecimal logins) {
		this.logins = logins;
	}

	@Column(name = "PHOTO", length = 256)
	public String getPhoto() {
		return this.photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	@Column(name = "ORGID", precision = 16, scale = 0)
	public Long getOrgid() {
		return this.orgid;
	}

	public void setOrgid(Long orgid) {
		this.orgid = orgid;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "pubUser")
	public Set<OwNewsct> getOwNewscts() {
		return this.owNewscts;
	}

	public void setOwNewscts(Set<OwNewsct> owNewscts) {
		this.owNewscts = owNewscts;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "pubUser")
	public Set<OwNewcar> getOwNewcars() {
		return this.owNewcars;
	}

	public void setOwNewcars(Set<OwNewcar> owNewcars) {
		this.owNewcars = owNewcars;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "pubUser")
	public Set<OwDiscountaction> getOwDiscountactions() {
		return this.owDiscountactions;
	}

	public void setOwDiscountactions(Set<OwDiscountaction> owDiscountactions) {
		this.owDiscountactions = owDiscountactions;
	}

}