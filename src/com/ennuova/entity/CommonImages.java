package com.ennuova.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;


/**
 * 
* @Title: CommonImages.java 
* @Package com.ennuova.entity 
* @Description: 图片附件(描述) 
* @author felix
* @date 2016年4月15日
* @version V1.0
 */
@Entity
@Table(name = "COMMON_IMAGES")
public class CommonImages implements java.io.Serializable {

	// Fields

	/**   
	* @Title: CommonImages.java 
	* @Package com.ennuova.entity 
	* @Description: TODO(描述) 
	* @author felix
	* @date 2016年4月14日
	* @version V1.0   
	*/
	private static final long serialVersionUID = 1L;
	private Long id;//id
	private String fpath;//图片路径(原图)
	private Integer ftype;//鉴别字符（1来源车系2来源车型）
	private String delFlag;//删除标识
	private Long ftypeid;//所属类型ID

	//*****不在数据库******//
	private String thpath;//缩略图路径

	@Transient
	public String getThpath() {
		return thpath;
	}
	
	public void setThpath(String thpath) {
		this.thpath = thpath;
	}
	
	
	// Constructors


	/** default constructor */
	public CommonImages() {
	}

	/** minimal constructor */
	public CommonImages(Long id) {
		this.id = id;
	}

	/** full constructor */
	public CommonImages(Long id, String fpath, Integer ftype,
			String delFlag,Long ftypeid) {
		this.id = id;
		this.fpath = fpath;
		this.ftype = ftype;
		this.delFlag = delFlag;
		this.ftypeid = ftypeid;
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

	@Column(name = "FPATH", length = 64)
	public String getFpath() {
		return this.fpath;
	}

	public void setFpath(String fpath) {
		this.fpath = fpath;
	}

	@Column(name = "FTYPE", precision = 22, scale = 0)
	public Integer getFtype() {
		return this.ftype;
	}

	public void setFtype(Integer ftype) {
		this.ftype = ftype;
	}

	/*@Column(name = "CREATE_BY", length = 64)
	public String getCreateBy() {
		return this.createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "CREATE_DATE", length = 7)
	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@Column(name = "UPDATE_BY", length = 64)
	public String getUpdateBy() {
		return this.updateBy;
	}

	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "UPDATE_DATE", length = 7)
	public Date getUpdateDate() {
		return this.updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}*/

	@Column(name = "DEL_FLAG", length = 1)
	public String getDelFlag() {
		return this.delFlag;
	}

	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}

	/*@Column(name = "CREATE_NAME", length = 50)
	public String getCreateName() {
		return this.createName;
	}

	public void setCreateName(String createName) {
		this.createName = createName;
	}

	@Column(name = "SYS_ORG_CODE", length = 50)
	public String getSysOrgCode() {
		return this.sysOrgCode;
	}

	public void setSysOrgCode(String sysOrgCode) {
		this.sysOrgCode = sysOrgCode;
	}

	@Column(name = "SYS_COMPANY_CODE", length = 50)
	public String getSysCompanyCode() {
		return this.sysCompanyCode;
	}

	public void setSysCompanyCode(String sysCompanyCode) {
		this.sysCompanyCode = sysCompanyCode;
	}*/

	@Column(name = "FTYPEID", precision = 16, scale = 0)
	public Long getFtypeid() {
		return this.ftypeid;
	}

	public void setFtypeid(Long ftypeid) {
		this.ftypeid = ftypeid;
	}

}