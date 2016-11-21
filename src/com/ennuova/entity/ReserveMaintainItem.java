package com.ennuova.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * ReserveMaintainItem entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "RESERVE_MAINTAIN_ITEM")
@SequenceGenerator(name="SEQ_GEN", sequenceName="SEQ_RESERVE_MAINTAIN_ITEM")  
public class ReserveMaintainItem implements java.io.Serializable {

	private static final long serialVersionUID = -5793451592303405978L;
	private Long reserveMaintainItemId;
	private Long maintainItemId;
	private ReserveMaintain reserveMaintain;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "RESERVE_MAINTAIN_ID")
	public ReserveMaintain getReserveMaintain() {
		return reserveMaintain;
	}

	public void setReserveMaintain(ReserveMaintain reserveMaintain) {
		this.reserveMaintain = reserveMaintain;
	}

	/** default constructor */
	public ReserveMaintainItem() {
	}

	/** minimal constructor */
	public ReserveMaintainItem(Long reserveMaintainItemId) {
		this.reserveMaintainItemId = reserveMaintainItemId;
	}

	/** full constructor */
	public ReserveMaintainItem(Long reserveMaintainItemId,
			 Long maintainItemId) {
		this.reserveMaintainItemId = reserveMaintainItemId;
		this.maintainItemId = maintainItemId;
	}

	// Property accessors
	@Id
	@SequenceGenerator(name="sequenceGenerator",sequenceName="seq_reserve_maintain_item",allocationSize=1,initialValue=1)
	@GeneratedValue(generator="sequenceGenerator",strategy=GenerationType.SEQUENCE)
	@Column(name = "RESERVE_MAINTAIN_ITEM_ID", unique = true, nullable = false, precision = 16, scale = 0)
	public Long getReserveMaintainItemId() {
		return this.reserveMaintainItemId;
	}

	public void setReserveMaintainItemId(Long reserveMaintainItemId) {
		this.reserveMaintainItemId = reserveMaintainItemId;
	}

	@Column(name = "MAINTAIN_ITEM_ID", precision = 16, scale = 0)
	public Long getMaintainItemId() {
		return this.maintainItemId;
	}

	public void setMaintainItemId(Long maintainItemId) {
		this.maintainItemId = maintainItemId;
	}

}