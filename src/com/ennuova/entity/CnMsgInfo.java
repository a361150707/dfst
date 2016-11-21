package com.ennuova.entity;

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
 * CnMsgInfo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "CN_MSG_INFO")
public class CnMsgInfo implements java.io.Serializable {

	// Fields

	private Long id;
	private String content;
	private Long jumpId;
	private Integer msgType;
	private Date createDate;
	
	private Integer reserveType;
	private Integer reserveState;

	// Constructors

	/** default constructor */
	public CnMsgInfo() {
	}

	/** minimal constructor */
	public CnMsgInfo(Long id) {
		this.id = id;
	}

	/** full constructor */
	public CnMsgInfo(Long id, String content, Long jumpId, Integer msgType,
			Date createDate) {
		this.id = id;
		this.content = content;
		this.jumpId = jumpId;
		this.msgType = msgType;
		this.createDate = createDate;
	}

	@Id
	@SequenceGenerator(name="sequenceGenerator",sequenceName="SEQ_CN_MSG_INFO",allocationSize=1,initialValue=1)
	@GeneratedValue(generator="sequenceGenerator",strategy=GenerationType.SEQUENCE)
	@Column(name = "ID", unique = true, nullable = false, precision = 16, scale = 0)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "CONTENT", length = 500)
	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "JUMP_ID", precision = 16, scale = 0)
	public Long getJumpId() {
		return this.jumpId;
	}

	public void setJumpId(Long jumpId) {
		this.jumpId = jumpId;
	}

	@Column(name = "MSG_TYPE", precision = 6, scale = 0)
	public Integer getMsgType() {
		return this.msgType;
	}

	public void setMsgType(Integer msgType) {
		this.msgType = msgType;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATE_DATE", length = 12)
	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	@Column(name = "RESERVE_TYPE", length = 6)
	public Integer getReserveType() {
		return reserveType;
	}

	public void setReserveType(Integer reserveType) {
		this.reserveType = reserveType;
	}

	@Column(name = "RESERVE_STATE", length = 6)
	public Integer getReserveState() {
		return reserveState;
	}

	public void setReserveState(Integer reserveState) {
		this.reserveState = reserveState;
	}

}