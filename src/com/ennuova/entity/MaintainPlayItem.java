package com.ennuova.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * MaintainPlayItem entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "MAINTAIN_PLAY_ITEM", schema = "INNOV_DEV")
public class MaintainPlayItem implements java.io.Serializable {

	// Fields

	private Long maintainPlayItemId;
	private Long maintainPlayId;
	private Long maintainItemId;

	// Constructors

	/** default constructor */
	public MaintainPlayItem() {
	}

	/** minimal constructor */
	public MaintainPlayItem(Long maintainPlayItemId) {
		this.maintainPlayItemId = maintainPlayItemId;
	}

	/** full constructor */
	public MaintainPlayItem(Long maintainPlayItemId, Long maintainPlayId,
			Long maintainItemId) {
		this.maintainPlayItemId = maintainPlayItemId;
		this.maintainPlayId = maintainPlayId;
		this.maintainItemId = maintainItemId;
	}

	// Property accessors
	@Id
	@Column(name = "MAINTAIN_PLAY_ITEM_ID", unique = true, nullable = false, precision = 16, scale = 0)
	public Long getMaintainPlayItemId() {
		return this.maintainPlayItemId;
	}

	public void setMaintainPlayItemId(Long maintainPlayItemId) {
		this.maintainPlayItemId = maintainPlayItemId;
	}

	@Column(name = "MAINTAIN_PLAY_ID", precision = 16, scale = 0)
	public Long getMaintainPlayId() {
		return this.maintainPlayId;
	}

	public void setMaintainPlayId(Long maintainPlayId) {
		this.maintainPlayId = maintainPlayId;
	}

	@Column(name = "MAINTAIN_ITEM_ID", precision = 16, scale = 0)
	public Long getMaintainItemId() {
		return this.maintainItemId;
	}

	public void setMaintainItemId(Long maintainItemId) {
		this.maintainItemId = maintainItemId;
	}

}