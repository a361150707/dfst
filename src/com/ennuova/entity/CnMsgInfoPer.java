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
 * CnMsgInfoPer entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "CN_MSG_INFO_PER")
public class CnMsgInfoPer implements java.io.Serializable {

	// Fields

	private Long id;
	private String content;
	private String recipient;
	private String sender;
	private Long msgInfoId;
	private String delFlag;
	private Date createDate;
	private Integer recipientType;
	private Integer senderType;
	private Integer isRead;
	private String headImg;
	private String nick;

	// Constructors

	/** default constructor */
	public CnMsgInfoPer() {
	}

	/** minimal constructor */
	public CnMsgInfoPer(Long id) {
		this.id = id;
	}

	/** full constructor */
	public CnMsgInfoPer(Long id, String content, String recipient,
			String sender, Long msgInfoId, String delFlag, Date createDate,
			Integer recipientType, Integer senderType, Integer isRead) {
		this.id = id;
		this.content = content;
		this.recipient = recipient;
		this.sender = sender;
		this.msgInfoId = msgInfoId;
		this.delFlag = delFlag;
		this.createDate = createDate;
		this.recipientType = recipientType;
		this.senderType = senderType;
		this.isRead = isRead;
	}

	@Id
	@SequenceGenerator(name="sequenceGenerator",sequenceName="SEQ_CN_MSG_INFO_PER",allocationSize=1,initialValue=1)
	@GeneratedValue(generator="sequenceGenerator",strategy=GenerationType.SEQUENCE)
	@Column(name = "ID", unique = true, nullable = false, precision = 16, scale = 0)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "CONTENT", length = 50)
	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "RECIPIENT", length = 32)
	public String getRecipient() {
		return this.recipient;
	}

	public void setRecipient(String recipient) {
		this.recipient = recipient;
	}

	@Column(name = "SENDER", length = 32)
	public String getSender() {
		return this.sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	@Column(name = "MSG_INFO_ID", precision = 16, scale = 0)
	public Long getMsgInfoId() {
		return this.msgInfoId;
	}

	public void setMsgInfoId(Long msgInfoId) {
		this.msgInfoId = msgInfoId;
	}

	@Column(name = "DEL_FLAG", length = 1)
	public String getDelFlag() {
		return this.delFlag;
	}

	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATE_DATE", length = 12)
	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@Column(name = "RECIPIENT_TYPE", precision = 6, scale = 0)
	public Integer getRecipientType() {
		return this.recipientType;
	}

	public void setRecipientType(Integer recipientType) {
		this.recipientType = recipientType;
	}

	@Column(name = "SENDER_TYPE", precision = 6, scale = 0)
	public Integer getSenderType() {
		return this.senderType;
	}

	public void setSenderType(Integer senderType) {
		this.senderType = senderType;
	}

	@Column(name = "IS_READ", precision = 6, scale = 0)
	public Integer getIsRead() {
		return this.isRead;
	}

	public void setIsRead(Integer isRead) {
		this.isRead = isRead;
	}
	@Column(name = "HEAD_IMG", length = 200)
	public String getHeadImg() {
		return headImg;
	}

	public void setHeadImg(String headImg) {
		this.headImg = headImg;
	}
	@Column(name = "NICK", length = 20)
	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

}