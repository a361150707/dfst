package com.ennuova.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * ReserveMaintainDetail entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "RESERVE_MAINTAIN_DETAIL")
public class ReserveMaintainDetail implements java.io.Serializable {

	// Fields

	private Long reserveMaintainDetailId;
	private Long reserveMaintainId;
	private Long maintainItemId;

	// Constructors

	/** default constructor */
	public ReserveMaintainDetail() {
	}

	/** minimal constructor */
	public ReserveMaintainDetail(Long reserveMaintainDetailId) {
		this.reserveMaintainDetailId = reserveMaintainDetailId;
	}

	/** full constructor */
	public ReserveMaintainDetail(Long reserveMaintainDetailId,
			Long reserveMaintainId, Long maintainItemId) {
		this.reserveMaintainDetailId = reserveMaintainDetailId;
		this.reserveMaintainId = reserveMaintainId;
		this.maintainItemId = maintainItemId;
	}

	// Property accessors
	@Id
	@Column(name = "RESERVE_MAINTAIN_DETAIL_ID", unique = true, nullable = false, precision = 16, scale = 0)
	public Long getReserveMaintainDetailId() {
		return this.reserveMaintainDetailId;
	}

	public void setReserveMaintainDetailId(Long reserveMaintainDetailId) {
		this.reserveMaintainDetailId = reserveMaintainDetailId;
	}

	@Column(name = "RESERVE_MAINTAIN_ID", precision = 16, scale = 0)
	public Long getReserveMaintainId() {
		return this.reserveMaintainId;
	}

	public void setReserveMaintainId(Long reserveMaintainId) {
		this.reserveMaintainId = reserveMaintainId;
	}

	@Column(name = "MAINTAIN_ITEM_ID", precision = 16, scale = 0)
	public Long getMaintainItemId() {
		return this.maintainItemId;
	}

	public void setMaintainItemId(Long maintainItemId) {
		this.maintainItemId = maintainItemId;
	}

}