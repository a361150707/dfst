package com.ennuova.entity;

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.google.common.hash.HashCode;

/**
 * CommonVideosId entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "Common_Videos")
public class CommonVideos implements java.io.Serializable {

	// Fields

	private Long id;
	private String fpath;
	private int ftype;
	private Long ftypeid;
	private String fthumbpath;

	// Constructors

	/** default constructor */
	public CommonVideos() {
	}

	/** minimal constructor */
	public CommonVideos(Long id) {
		this.id = id;
	}

	/** full constructor */
	public CommonVideos(Long id, String fpath, int ftype,
			Long ftypeid, String fthumbpath) {
		this.id = id;
		this.fpath = fpath;
		this.ftype = ftype;
		this.ftypeid = ftypeid;
		this.fthumbpath = fthumbpath;
	}

	// Property accessors

	@Id
	@SequenceGenerator(name="sequenceGenerator",sequenceName="SEQ_COMMON_VIDEOS",allocationSize=1,initialValue=1)
	@GeneratedValue(generator="sequenceGenerator",strategy=GenerationType.SEQUENCE)
	@Column(name = "ID", unique = true, nullable = false, precision = 16, scale = 0)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "FPATH", length = 64)
	public String getFpath() {
		return this.fpath;
	}

	public void setFpath(String fpath) {
		this.fpath = fpath;
	}

	@Column(name = "FTYPE", precision = 22, scale = 0)
	public int getFtype() {
		return this.ftype;
	}

	public void setFtype(int ftype) {
		this.ftype = ftype;
	}

	@Column(name = "FTYPEID", precision = 16, scale = 0)
	public Long getFtypeid() {
		return this.ftypeid;
	}

	public void setFtypeid(Long ftypeid) {
		this.ftypeid = ftypeid;
	}

	@Column(name = "FTHUMBPATH", length = 512)
	public String getFthumbpath() {
		return this.fthumbpath;
	}

	public void setFthumbpath(String fthumbpath) {
		this.fthumbpath = fthumbpath;
	}
}