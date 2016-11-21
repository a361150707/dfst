package com.ennuova.entity;

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

import org.hibernate.annotations.GenericGenerator;

/**
 * 报警设置
 */
@Entity
@Table(name = "PUB_ALARMSETTING")
public class PubAlarmsetting implements java.io.Serializable {

	// Fields

	private Long id;
	private String fcode;
	private String fname;
	private String ftype;
	private String fminfz;
	private String fmaxfz;
	private String fdiscribe;
	private String ftxnr;
	
	private Long bisuse;
	
	private Set<OwCusalarms> owCusalarmses = new HashSet<OwCusalarms>(0);
	private Set<CnBjrecode> cnBjrecodes = new HashSet<CnBjrecode>(0);

	// Constructors

	/** default constructor */
	public PubAlarmsetting() {
	}

	/** minimal constructor */
	public PubAlarmsetting(Long id) {
		this.id = id;
	}

	/** full constructor */
	public PubAlarmsetting(Long id, String fcode, String fname, String ftype,
			String fminfz, String fmaxfz, String fdiscribe, String ftxnr,
			Set<OwCusalarms> owCusalarmses, Set<CnBjrecode> cnBjrecodes) {
		this.id = id;
		this.fcode = fcode;
		this.fname = fname;
		this.ftype = ftype;
		this.fminfz = fminfz;
		this.fmaxfz = fmaxfz;
		this.fdiscribe = fdiscribe;
		this.ftxnr = ftxnr;
		this.owCusalarmses = owCusalarmses;
		this.cnBjrecodes = cnBjrecodes;
	}

	// Property accessors
	@Id
	@SequenceGenerator(name="sequenceGenerator",sequenceName="seq_PUB_ALARMSETTING",allocationSize=1,initialValue=1)
	@GeneratedValue(generator="sequenceGenerator",strategy=GenerationType.SEQUENCE)
	@Column(name = "ID", unique = true, nullable = false, precision = 16, scale = 0)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "FCODE", length = 50)
	public String getFcode() {
		return this.fcode;
	}

	public void setFcode(String fcode) {
		this.fcode = fcode;
	}

	@Column(name = "FNAME", length = 50)
	public String getFname() {
		return this.fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	@Column(name = "FTYPE", length = 30)
	public String getFtype() {
		return this.ftype;
	}

	public void setFtype(String ftype) {
		this.ftype = ftype;
	}

	@Column(name = "FMINFZ")
	public String getFminfz() {
		return this.fminfz;
	}

	public void setFminfz(String fminfz) {
		this.fminfz = fminfz;
	}

	@Column(name = "FMAXFZ")
	public String getFmaxfz() {
		return this.fmaxfz;
	}

	public void setFmaxfz(String fmaxfz) {
		this.fmaxfz = fmaxfz;
	}

	@Column(name = "FDISCRIBE", length = 256)
	public String getFdiscribe() {
		return this.fdiscribe;
	}

	public void setFdiscribe(String fdiscribe) {
		this.fdiscribe = fdiscribe;
	}

	@Column(name = "FTXNR", length = 1000)
	public String getFtxnr() {
		return this.ftxnr;
	}

	public void setFtxnr(String ftxnr) {
		this.ftxnr = ftxnr;
	}
	
	@Column(name = "B_ISUSE", precision = 16, scale = 0)
	public Long getBisuse() {
		return this.bisuse;
	}

	public void setBisuse(Long bisuse) {
		this.bisuse = bisuse;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "pubAlarmsetting")
	public Set<OwCusalarms> getOwCusalarmses() {
		return this.owCusalarmses;
	}

	public void setOwCusalarmses(Set<OwCusalarms> owCusalarmses) {
		this.owCusalarmses = owCusalarmses;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "pubAlarmsetting")
	public Set<CnBjrecode> getCnBjrecodes() {
		return this.cnBjrecodes;
	}

	public void setCnBjrecodes(Set<CnBjrecode> cnBjrecodes) {
		this.cnBjrecodes = cnBjrecodes;
	}

}