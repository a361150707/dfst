package com.ennuova.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * CnInfodetail entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "CN_INFODETAIL")
public class CnInfodetail implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	//private Long RMsgid;//消息ID
	private Long RRecuser;//接收人
	private Date DReadtime;//阅读时间
	private Long BState;//状态
	private CnInfo cnInfo;
	private Long messageType;
	// Constructors

	@OneToOne
	@JoinColumn(name="RMsgid",referencedColumnName="id",unique=true)
	public CnInfo getCnInfo() {
		return cnInfo;
	}

	public void setCnInfo(CnInfo cnInfo) {
		this.cnInfo = cnInfo;
	}

	/** default constructor */
	public CnInfodetail() {
	}

	/** minimal constructor */
	public CnInfodetail(Long id) {
		this.id = id;
	}

	/** full constructor */
	public CnInfodetail(Long id, CnInfo cnInfo, Long RRecuser, Date DReadtime,
			Long BState) {
		this.id = id;
		//this.RMsgid = RMsgid;
		this.cnInfo = cnInfo;
		this.RRecuser = RRecuser;
		this.DReadtime = DReadtime;
		this.BState = BState;
	}

	// Property accessors
	@Id
	@SequenceGenerator(name="sequenceGenerator",sequenceName="seq_CN_INFODETAIL",allocationSize=1,initialValue=1)
	@GeneratedValue(generator="sequenceGenerator",strategy=GenerationType.SEQUENCE)
	@Column(name = "ID", unique = true, nullable = false, precision = 16, scale = 0)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	/*@Column(name = "R_MSGID", precision = 16, scale = 0)
	public Long getRMsgid() {
		return this.RMsgid;
	}

	public void setRMsgid(Long RMsgid) {
		this.RMsgid = RMsgid;
	}*/

	@Column(name = "R_RECUSER", precision = 16, scale = 0)
	public Long getRRecuser() {
		return this.RRecuser;
	}

	public void setRRecuser(Long RRecuser) {
		this.RRecuser = RRecuser;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "D_READTIME", length = 7)
	public Date getDReadtime() {
		return this.DReadtime;
	}

	public void setDReadtime(Date DReadtime) {
		this.DReadtime = DReadtime;
	}

	@Column(name = "B_STATE", precision = 16, scale = 0)
	public Long getBState() {
		return this.BState;
	}

	public void setBState(Long BState) {
		this.BState = BState;
	}
	@Column(name = "MESSAGE_TYPE", precision = 2, scale = 0)
	public Long getMessageType() {
		return messageType;
	}

	public void setMessageType(Long messageType) {
		this.messageType = messageType;
	}
}