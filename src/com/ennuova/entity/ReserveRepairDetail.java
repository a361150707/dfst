package com.ennuova.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * ReserveRepairDetail entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "RESERVE_REPAIR_DETAIL")
public class ReserveRepairDetail implements java.io.Serializable {

	// Fields

	private Long reserveRepairDetailId;
	private Long reserveRepairId;
	private Long repairItemId;

	// Constructors

	/** default constructor */
	public ReserveRepairDetail() {
	}

	/** minimal constructor */
	public ReserveRepairDetail(Long reserveRepairDetailId) {
		this.reserveRepairDetailId = reserveRepairDetailId;
	}

	/** full constructor */
	public ReserveRepairDetail(Long reserveRepairDetailId,
			Long reserveRepairId, Long repairItemId) {
		this.reserveRepairDetailId = reserveRepairDetailId;
		this.reserveRepairId = reserveRepairId;
		this.repairItemId = repairItemId;
	}

	// Property accessors
	@Id
	@Column(name = "RESERVE_REPAIR_DETAIL_ID", unique = true, nullable = false, precision = 16, scale = 0)
	public Long getReserveRepairDetailId() {
		return this.reserveRepairDetailId;
	}

	public void setReserveRepairDetailId(Long reserveRepairDetailId) {
		this.reserveRepairDetailId = reserveRepairDetailId;
	}

	@Column(name = "RESERVE_REPAIR_ID", precision = 16, scale = 0)
	public Long getReserveRepairId() {
		return this.reserveRepairId;
	}

	public void setReserveRepairId(Long reserveRepairId) {
		this.reserveRepairId = reserveRepairId;
	}

	@Column(name = "REPAIR_ITEM_ID", precision = 16, scale = 0)
	public Long getRepairItemId() {
		return this.repairItemId;
	}

	public void setRepairItemId(Long repairItemId) {
		this.repairItemId = repairItemId;
	}

}