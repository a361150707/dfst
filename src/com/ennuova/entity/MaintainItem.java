package com.ennuova.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * MaintainItem entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "MAINTAIN_ITEM", schema = "INNOV_DEV")
public class MaintainItem implements java.io.Serializable {

	// Fields

	private Long maintainItemId;
	private String maintainItemName;
	private String maintainItemRemark;

	// Constructors

	/** default constructor */
	public MaintainItem() {
	}

	/** minimal constructor */
	public MaintainItem(Long maintainItemId) {
		this.maintainItemId = maintainItemId;
	}

	/** full constructor */
	public MaintainItem(Long maintainItemId, String maintainItemName,
			String maintainItemRemark) {
		this.maintainItemId = maintainItemId;
		this.maintainItemName = maintainItemName;
		this.maintainItemRemark = maintainItemRemark;
	}

	// Property accessors
	@Id
	@Column(name = "MAINTAIN_ITEM_ID", unique = true, nullable = false, precision = 16, scale = 0)
	public Long getMaintainItemId() {
		return this.maintainItemId;
	}

	public void setMaintainItemId(Long maintainItemId) {
		this.maintainItemId = maintainItemId;
	}

	@Column(name = "MAINTAIN_ITEM_NAME", length = 50)
	public String getMaintainItemName() {
		return this.maintainItemName;
	}

	public void setMaintainItemName(String maintainItemName) {
		this.maintainItemName = maintainItemName;
	}

	@Column(name = "MAINTAIN_ITEM_REMARK", length = 200)
	public String getMaintainItemRemark() {
		return this.maintainItemRemark;
	}

	public void setMaintainItemRemark(String maintainItemRemark) {
		this.maintainItemRemark = maintainItemRemark;
	}

}