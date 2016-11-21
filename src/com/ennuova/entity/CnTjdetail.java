package com.ennuova.entity;

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

import org.hibernate.annotations.GenericGenerator;

/**
 * CnTjdetail entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "CN_TJDETAIL")
public class CnTjdetail implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Fields

	private Long id;
	private CnTjreport cnTjreport;
	private String fcode;
	private String fname;
	private String funit;
	private String fjcz;
	
	// Constructors

	/** default constructor */
	public CnTjdetail() {
	}

	/** minimal constructor */
	public CnTjdetail(Long id) {
		this.id = id;
	}

	/** full constructor */
	public CnTjdetail(Long id, CnTjreport cnTjreport, String fcode,
			String fname, String funit, String fjcz) {
		this.id = id;
		this.cnTjreport = cnTjreport;
		this.fcode = fcode;
		this.fname = fname;
		this.funit = funit;
		this.fjcz = fjcz;
	}

	// Property accessors
	@Id
	@SequenceGenerator(name="sequenceGenerator",sequenceName="seq_CN_TJDETAIL",allocationSize=1,initialValue=1)
	@GeneratedValue(generator="sequenceGenerator",strategy=GenerationType.SEQUENCE)
	@Column(name = "ID", unique = true, nullable = false, precision = 16, scale = 0)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "FTJID")
	public CnTjreport getCnTjreport() {
		return this.cnTjreport;
	}

	public void setCnTjreport(CnTjreport cnTjreport) {
		this.cnTjreport = cnTjreport;
	}

	@Column(name = "FCODE", length = 50)
	public String getFcode() {
		return this.fcode;
	}

	public void setFcode(String fcode) {
		this.fcode = fcode;
	}

	@Column(name = "FNAME", length = 100)
	public String getFname() {
		return this.fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	@Column(name = "FUNIT", length = 50)
	public String getFunit() {
		return this.funit;
	}

	public void setFunit(String funit) {
		this.funit = funit;
	}

	@Column(name = "FJCZ", length = 50)
	public String getFjcz() {
		return this.fjcz;
	}

	public void setFjcz(String fjcz) {
		this.fjcz = fjcz;
	}

}