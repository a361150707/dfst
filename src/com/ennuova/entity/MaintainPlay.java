package com.ennuova.entity;

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * MaintainPlay entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "MAINTAIN_PLAY", schema = "INNOV_DEV")
public class MaintainPlay implements java.io.Serializable {

	// Fields

	private Long maintainPlayId;
	private String maintainPlayName;
	private int maintainPlayMilageNum;
	private String maintainPlayAliases;
	private Long maintainPlayOrder;
	private String maintainPlayRemark;
	@Transient
	private int km;

	// Constructors

	/** default constructor */
	public MaintainPlay() {
	}

	/** minimal constructor */
	public MaintainPlay(Long maintainPlayId) {
		this.maintainPlayId = maintainPlayId;
	}

	/** full constructor */
	public MaintainPlay(Long maintainPlayId, String maintainPlayName,
			int maintainPlayMilageNum, String maintainPlayAliases,
			Long maintainPlayOrder, String maintainPlayRemark) {
		this.maintainPlayId = maintainPlayId;
		this.maintainPlayName = maintainPlayName;
		this.maintainPlayMilageNum = maintainPlayMilageNum;
		this.maintainPlayAliases = maintainPlayAliases;
		this.maintainPlayOrder = maintainPlayOrder;
		this.maintainPlayRemark = maintainPlayRemark;
	}

	// Property accessors
	@Id
	@Column(name = "MAINTAIN_PLAY_ID", unique = true, nullable = false, precision = 16, scale = 0)
	public Long getMaintainPlayId() {
		return this.maintainPlayId;
	}

	public void setMaintainPlayId(Long maintainPlayId) {
		this.maintainPlayId = maintainPlayId;
	}

	@Column(name = "MAINTAIN_PLAY_NAME", length = 50)
	public String getMaintainPlayName() {
		return this.maintainPlayName;
	}

	public void setMaintainPlayName(String maintainPlayName) {
		this.maintainPlayName = maintainPlayName;
	}

	@Column(name = "MAINTAIN_PLAY_MILAGE_NUM", precision = 22, scale = 0)
	public int getMaintainPlayMilageNum() {
		return this.maintainPlayMilageNum;
	}

	public void setMaintainPlayMilageNum(int maintainPlayMilageNum) {
		this.maintainPlayMilageNum = maintainPlayMilageNum;
	}

	@Column(name = "MAINTAIN_PLAY_ALIASES", length = 50)
	public String getMaintainPlayAliases() {
		return this.maintainPlayAliases;
	}

	public void setMaintainPlayAliases(String maintainPlayAliases) {
		this.maintainPlayAliases = maintainPlayAliases;
	}

	@Column(name = "MAINTAIN_PLAY_ORDER", precision = 22, scale = 0)
	public Long getMaintainPlayOrder() {
		return this.maintainPlayOrder;
	}

	public void setMaintainPlayOrder(Long maintainPlayOrder) {
		this.maintainPlayOrder = maintainPlayOrder;
	}

	@Column(name = "MAINTAIN_PLAY_REMARK", length = 200)
	public String getMaintainPlayRemark() {
		return this.maintainPlayRemark;
	}

	public void setMaintainPlayRemark(String maintainPlayRemark) {
		this.maintainPlayRemark = maintainPlayRemark;
	}
	
	public int getKm() {
		return km;
	}

	public void setKm(int km) {
		this.km = km;
	}

}