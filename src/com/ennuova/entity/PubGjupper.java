package com.ennuova.entity;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * obd固件版本号
 */
@Entity
@Table(name = "PUB_GJUPPER")
public class PubGjupper implements java.io.Serializable {

	// Fields
	private Long id;
	private String moduleName;
	private String moduleVer;
	private String url;
	private String sbxlh;
	private String filePath;
	private Timestamp uploadtime;
	private String createBy;
	private String createDate;
	private String updateBy;
	private String updateDate;
	private String remarks;
	private String delFlag;

	// Constructors

	/** default constructor */
	public PubGjupper() {
	}

	/** minimal constructor */
	public PubGjupper(Long id) {
		this.id = id;
	}

	/** full constructor */
	public PubGjupper(Long id, String moduleName, String moduleVer, String url,
			String sbxlh, String filePath, Timestamp uploadtime,
			String createBy, String createDate, String updateBy,
			String updateDate, String remarks, String delFlag) {
		this.id = id;
		this.moduleName = moduleName;
		this.moduleVer = moduleVer;
		this.url = url;
		this.sbxlh = sbxlh;
		this.filePath = filePath;
		this.uploadtime = uploadtime;
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

	@Column(name = "MODULE_NAME", length = 50)
	public String getModuleName() {
		return this.moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	@Column(name = "MODULE_VER", length = 50)
	public String getModuleVer() {
		return this.moduleVer;
	}

	public void setModuleVer(String moduleVer) {
		this.moduleVer = moduleVer;
	}

	@Column(name = "URL", length = 100)
	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Column(name = "SBXLH", length = 50)
	public String getSbxlh() {
		return this.sbxlh;
	}

	public void setSbxlh(String sbxlh) {
		this.sbxlh = sbxlh;
	}

	@Column(name = "FILE_PATH", length = 100)
	public String getFilePath() {
		return this.filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	@Column(name = "UPLOADTIME", length = 7)
	public Timestamp getUploadtime() {
		return this.uploadtime;
	}

	public void setUploadtime(Timestamp uploadtime) {
		this.uploadtime = uploadtime;
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