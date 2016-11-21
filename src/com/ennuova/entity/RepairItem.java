package com.ennuova.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * RepairItem entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "REPAIR_ITEM", schema = "INNOV_DEV")
public class RepairItem implements java.io.Serializable {

	// Fields

	private Long repairItemId;
	private String repairItemName;
	private String repairItemRemark;

	// Constructors

	/** default constructor */
	public RepairItem() {
	}

	/** minimal constructor */
	public RepairItem(Long repairItemId) {
		this.repairItemId = repairItemId;
	}

	/** full constructor */
	public RepairItem(Long repairItemId, String repairItemName,
			String repairItemRemark) {
		this.repairItemId = repairItemId;
		this.repairItemName = repairItemName;
		this.repairItemRemark = repairItemRemark;
	}

	// Property accessors
	@Id
	@Column(name = "REPAIR_ITEM_ID", unique = true, nullable = false, precision = 16, scale = 0)
	public Long getRepairItemId() {
		return this.repairItemId;
	}

	public void setRepairItemId(Long repairItemId) {
		this.repairItemId = repairItemId;
	}

	@Column(name = "REPAIR_ITEM_NAME", length = 50)
	public String getRepairItemName() {
		return this.repairItemName;
	}

	public void setRepairItemName(String repairItemName) {
		this.repairItemName = repairItemName;
	}

	@Column(name = "REPAIR_ITEM_REMARK", length = 200)
	public String getRepairItemRemark() {
		return this.repairItemRemark;
	}

	public void setRepairItemRemark(String repairItemRemark) {
		this.repairItemRemark = repairItemRemark;
	}

}