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

/**
 * SIM卡信息
 */
@Entity
@Table(name = "CN_SIMINFO")
public class CnSiminfo implements java.io.Serializable {

	// Fields

	private Long id;
	private PubBox pubBox;
	private String fsimkh;
	private BigDecimal fyxq;
	private Timestamp fjhtime;
	private String fzlc;
	private String fsylc;
	private String fbdlc;
	private String fqglc;
	private String fysylc;
	private Set<CnCzrecode> cnCzrecodes = new HashSet<CnCzrecode>(0);

	// Constructors

	/** default constructor */
	public CnSiminfo() {
	}

	/** minimal constructor */
	public CnSiminfo(Long id) {
		this.id = id;
	}

	/** full constructor */
	public CnSiminfo(Long id, PubBox pubBox, String fsimkh, BigDecimal fyxq,
			Timestamp fjhtime, String fzlc, String fsylc, String fbdlc,
			String fqglc, String fysylc, Set<CnCzrecode> cnCzrecodes) {
		this.id = id;
		this.pubBox = pubBox;
		this.fsimkh = fsimkh;
		this.fyxq = fyxq;
		this.fjhtime = fjhtime;
		this.fzlc = fzlc;
		this.fsylc = fsylc;
		this.fbdlc = fbdlc;
		this.fqglc = fqglc;
		this.fysylc = fysylc;
		this.cnCzrecodes = cnCzrecodes;
	}

	// Property accessors
	@Id
	@SequenceGenerator(name="sequenceGenerator",sequenceName="seq_CN_SIMINFO",allocationSize=1,initialValue=1)
	@GeneratedValue(generator="sequenceGenerator",strategy=GenerationType.SEQUENCE)
	@Column(name = "ID", unique = true, nullable = false, precision = 16, scale = 0)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "FBOXID")
	public PubBox getPubBox() {
		return this.pubBox;
	}

	public void setPubBox(PubBox pubBox) {
		this.pubBox = pubBox;
	}

	@Column(name = "FSIMKH", length = 50)
	public String getFsimkh() {
		return this.fsimkh;
	}

	public void setFsimkh(String fsimkh) {
		this.fsimkh = fsimkh;
	}

	@Column(name = "FYXQ", precision = 22, scale = 0)
	public BigDecimal getFyxq() {
		return this.fyxq;
	}

	public void setFyxq(BigDecimal fyxq) {
		this.fyxq = fyxq;
	}

	@Column(name = "FJHTIME", length = 7)
	public Timestamp getFjhtime() {
		return this.fjhtime;
	}

	public void setFjhtime(Timestamp fjhtime) {
		this.fjhtime = fjhtime;
	}

	@Column(name = "FZLC")
	public String getFzlc() {
		return this.fzlc;
	}

	public void setFzlc(String fzlc) {
		this.fzlc = fzlc;
	}

	@Column(name = "FSYLC")
	public String getFsylc() {
		return this.fsylc;
	}

	public void setFsylc(String fsylc) {
		this.fsylc = fsylc;
	}

	@Column(name = "FBDLC")
	public String getFbdlc() {
		return this.fbdlc;
	}

	public void setFbdlc(String fbdlc) {
		this.fbdlc = fbdlc;
	}

	@Column(name = "FQGLC")
	public String getFqglc() {
		return this.fqglc;
	}

	public void setFqglc(String fqglc) {
		this.fqglc = fqglc;
	}

	@Column(name = "FYSYLC")
	public String getFysylc() {
		return this.fysylc;
	}

	public void setFysylc(String fysylc) {
		this.fysylc = fysylc;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "cnSiminfo")
	public Set<CnCzrecode> getCnCzrecodes() {
		return this.cnCzrecodes;
	}

	public void setCnCzrecodes(Set<CnCzrecode> cnCzrecodes) {
		this.cnCzrecodes = cnCzrecodes;
	}

}