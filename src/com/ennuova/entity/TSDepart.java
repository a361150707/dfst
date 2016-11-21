package com.ennuova.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * TSDepart entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "T_S_DEPART")
public class TSDepart implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Fields

	private String id;
	private String departname;
	private String description;
	private String parentdepartid;
	private String orgCode;
	private String orgType;

	// Constructors

	/** default constructor */
	public TSDepart() {
	}

	/** minimal constructor */
	public TSDepart(String id, String departname) {
		this.id = id;
		this.departname = departname;
	}

	/** full constructor */
	public TSDepart(String id, String departname, String description,
			String parentdepartid, String orgCode, String orgType) {
		this.id = id;
		this.departname = departname;
		this.description = description;
		this.parentdepartid = parentdepartid;
		this.orgCode = orgCode;
		this.orgType = orgType;
	}

	// Property accessors
	@Id
	@Column(name = "ID", unique = true, nullable = false, length = 32)
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "DEPARTNAME", nullable = false, length = 100)
	public String getDepartname() {
		return this.departname;
	}

	public void setDepartname(String departname) {
		this.departname = departname;
	}

	@Column(name = "DESCRIPTION")
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "PARENTDEPARTID", length = 32)
	public String getParentdepartid() {
		return this.parentdepartid;
	}

	public void setParentdepartid(String parentdepartid) {
		this.parentdepartid = parentdepartid;
	}

	@Column(name = "ORG_CODE", length = 64)
	public String getOrgCode() {
		return this.orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	@Column(name = "ORG_TYPE", length = 1)
	public String getOrgType() {
		return this.orgType;
	}

	public void setOrgType(String orgType) {
		this.orgType = orgType;
	}

}