package com.ennuova.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * CnInfodemo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "CN_INFODEMO")
public class CnInfodemo implements java.io.Serializable {

	// Fields

	private Long id;
	private String fcode;
	private String fname;
	private String fsubject;
	private String fcontent;
	private String createBy;
	private String createDate;
	private String updateBy;
	private String updateDate;
	private String remarks;
	private String delFlag;
	

	// Constructors

	/** default constructor */
	public CnInfodemo() {
	}

	/** minimal constructor */
	public CnInfodemo(Long id) {
		this.id = id;
	}

	/** full constructor */
	public CnInfodemo(Long id, String fcode, String fname, String fsubject,
			String fcontent, String createBy, String createDate,
			String updateBy, String updateDate, String remarks, String delFlag) {
		this.id = id;
		this.fcode = fcode;
		this.fname = fname;
		this.fsubject = fsubject;
		this.fcontent = fcontent;
		this.createBy = createBy;
		this.createDate = createDate;
		this.updateBy = updateBy;
		this.updateDate = updateDate;
		this.remarks = remarks;
		this.delFlag = delFlag;
	}

	// Property accessors
	@Id
	@Column(name = "ID", unique = true, nullable = false, precision = 16, scale = 0)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "FCODE", length = 30)
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

	@Column(name = "FSUBJECT", length = 100)
	public String getFsubject() {
		return this.fsubject;
	}

	public void setFsubject(String fsubject) {
		this.fsubject = fsubject;
	}

	@Column(name = "FCONTENT")
	public String getFcontent() {
		return this.fcontent;
	}

	public void setFcontent(String fcontent) {
		this.fcontent = fcontent;
	}

	@Column(name = "CREATE_BY", length = 64)
	public String getCreateBy() {
		return this.createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	@Column(name = "CREATE_DATE")
	public String getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	@Column(name = "UPDATE_BY", length = 64)
	public String getUpdateBy() {
		return this.updateBy;
	}

	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}

	@Column(name = "UPDATE_DATE")
	public String getUpdateDate() {
		return this.updateDate;
	}

	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}

	@Column(name = "REMARKS")
	public String getRemarks() {
		return this.remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	@Column(name = "DEL_FLAG", length = 1)
	public String getDelFlag() {
		return this.delFlag;
	}

	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}
}