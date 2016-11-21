package com.ennuova.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * 数据流表
 */
@Entity
@Table(name = "PUB_DATALMB")
public class PubDatalmb implements java.io.Serializable {

	// Fields

	private Long id;
	private String fcode;
	private String fid;
	private String fname;
	private String fdw;
	private Long fbit;
	private String fsf;

	// Constructors

	/** default constructor */
	public PubDatalmb() {
	}

	/** minimal constructor */
	public PubDatalmb(Long id) {
		this.id = id;
	}

	/** full constructor */
	public PubDatalmb(Long id, String fcode, String fid, String fname,
			String fdw, Long fbit, String fsf) {
		this.id = id;
		this.fcode = fcode;
		this.fid = fid;
		this.fname = fname;
		this.fdw = fdw;
		this.fbit = fbit;
		this.fsf = fsf;
	}

	// Property accessors
	@Id
	@SequenceGenerator(name="sequenceGenerator",sequenceName="seq_PUB_DATALMB",allocationSize=1,initialValue=1)
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

	@Column(name = "FID", length = 50)
	public String getFid() {
		return this.fid;
	}

	public void setFid(String fid) {
		this.fid = fid;
	}

	@Column(name = "FNAME", length = 50)
	public String getFname() {
		return this.fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	@Column(name = "FDW", length = 50)
	public String getFdw() {
		return this.fdw;
	}

	public void setFdw(String fdw) {
		this.fdw = fdw;
	}

	@Column(name = "FBIT", precision = 16, scale = 0)
	public Long getFbit() {
		return this.fbit;
	}

	public void setFbit(Long fbit) {
		this.fbit = fbit;
	}

	@Column(name = "FSF", length = 50)
	public String getFsf() {
		return this.fsf;
	}

	public void setFsf(String fsf) {
		this.fsf = fsf;
	}

}