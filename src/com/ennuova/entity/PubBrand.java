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
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * 品牌表
 */
@Entity
@Table(name = "PUB_BRAND")
public class PubBrand implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private String fcj;
	private String fcodeabb;
	private String fnumber;
	private String fname;
	private Long fobdsbm;
	private List<PubLine> pubLines = new ArrayList<PubLine>(0);
	private Set<PubVehiclemodel> pubVehiclemodels = new HashSet<PubVehiclemodel>(
			0);
	private Set<PubCarinfo> pubCarinfos = new HashSet<PubCarinfo>(0);

	// Constructors

	/** default constructor */
	public PubBrand() {
	}

	/** minimal constructor */
	public PubBrand(Long id) {
		this.id = id;
	}

	/** full constructor */
	public PubBrand(Long id, String fcj, String fcodeabb, String fnumber,
			String fname, Long fobdsbm, List<PubLine> pubLines,
			Set<PubVehiclemodel> pubVehiclemodels, Set<PubCarinfo> pubCarinfos) {
		this.id = id;
		this.fcj = fcj;
		this.fcodeabb = fcodeabb;
		this.fnumber = fnumber;
		this.fname = fname;
		this.fobdsbm = fobdsbm;
		this.pubLines = pubLines;
		this.pubVehiclemodels = pubVehiclemodels;
		this.pubCarinfos = pubCarinfos;
	}
	
	/** full constructor */
	public PubBrand(Long id, String fcj, String fcodeabb, String fnumber,
			String fname, Long fobdsbm) {
		this.id = id;
		this.fcj = fcj;
		this.fcodeabb = fcodeabb;
		this.fnumber = fnumber;
		this.fname = fname;
		this.fobdsbm = fobdsbm;
	}

	// Property accessors
	@Id
	@SequenceGenerator(name="sequenceGenerator",sequenceName="seq_PUB_BRAND",allocationSize=1,initialValue=1)
	@GeneratedValue(generator="sequenceGenerator",strategy=GenerationType.SEQUENCE)
	@Column(name = "ID", unique = true, nullable = false, precision = 16, scale = 0)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "FCJ", length = 50)
	public String getFcj() {
		return this.fcj;
	}

	public void setFcj(String fcj) {
		this.fcj = fcj;
	}

	@Column(name = "FCODEABB", length = 30)
	public String getFcodeabb() {
		return this.fcodeabb;
	}

	public void setFcodeabb(String fcodeabb) {
		this.fcodeabb = fcodeabb;
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

	@Column(name = "FOBDSBM", precision = 16, scale = 0)
	public Long getFobdsbm() {
		return this.fobdsbm;
	}

	public void setFobdsbm(Long fobdsbm) {
		this.fobdsbm = fobdsbm;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "pubBrand")
	public List<PubLine> getPubLines() {
		return this.pubLines;
	}

	public void setPubLines(List<PubLine> pubLines) {
		this.pubLines = pubLines;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "pubBrand")
	public Set<PubVehiclemodel> getPubVehiclemodels() {
		return this.pubVehiclemodels;
	}

	public void setPubVehiclemodels(Set<PubVehiclemodel> pubVehiclemodels) {
		this.pubVehiclemodels = pubVehiclemodels;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "pubBrand")
	public Set<PubCarinfo> getPubCarinfos() {
		return this.pubCarinfos;
	}

	public void setPubCarinfos(Set<PubCarinfo> pubCarinfos) {
		this.pubCarinfos = pubCarinfos;
	}

}