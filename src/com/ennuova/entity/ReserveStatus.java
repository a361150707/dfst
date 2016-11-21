package com.ennuova.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * ReserveStatus entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "RESERVE_STATUS")
@SequenceGenerator(name="SEQ_GEN", sequenceName="SEQ_RESERVE_STATUS")  
public class ReserveStatus implements java.io.Serializable {
	private static final long serialVersionUID = 6045630908333380462L;
	private Long id;
	/** 0表示预约保养 1表示预约维修 2表示预约续保*/
	private Integer reserveType;
	/**预约ID */
	private Long reserveId;
	
	/**0表示：待审核 
	1表示：已审核
	2表示：已完成
	3表示：审核未通过
	4表示：已取消
	5表示：已终止
	 */
	private Integer reserveStatus;
	private Date reserveDate;
	private String reserveStatusName;

	// Constructors

	/** default constructor */
	public ReserveStatus() {
	}
	@Column(name="RESERVE_STATUS_NAME",length = 50)
	public String getReserveStatusName() {
		return reserveStatusName;
	}

	public void setReserveStatusName(String reserveStatusName) {
		this.reserveStatusName = reserveStatusName;
	}

	/** minimal constructor */
	public ReserveStatus(Long id) {
		this.id = id;
	}

	/** full constructor */
	public ReserveStatus(Long id, Integer reserveType, Long reserveId,
			Integer reserveStatus, Date reserveDate) {
		this.id = id;
		this.reserveType = reserveType;
		this.reserveId = reserveId;
		this.reserveStatus = reserveStatus;
		this.reserveDate = reserveDate;
	}

	// Property accessors
	@Id
	@SequenceGenerator(name="sequenceGenerator",sequenceName="seq_reserve_status",allocationSize=1,initialValue=1)
	@GeneratedValue(generator="sequenceGenerator",strategy=GenerationType.SEQUENCE)
	@Column(name = "ID", unique = true, nullable = false, precision = 16, scale = 0)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "reserve_type", precision = 22, scale = 0)
	public Integer getReserveType() {
		return this.reserveType;
	}

	public void setReserveType(Integer reserveType) {
		this.reserveType = reserveType;
	}

	@Column(name = "reserve_id", precision = 16, scale = 0)
	public Long getReserveId() {
		return this.reserveId;
	}

	public void setReserveId(Long reserveId) {
		this.reserveId = reserveId;
	}

	@Column(name = "reserve_status", precision = 22, scale = 0)
	public Integer getReserveStatus() {
		return this.reserveStatus;
	}

	public void setReserveStatus(Integer reserveStatus) {
		this.reserveStatus = reserveStatus;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "reserve_date", length = 7)
	public Date getReserveDate() {
		return this.reserveDate;
	}

	public void setReserveDate(Date reserveDate) {
		this.reserveDate = reserveDate;
	}

}