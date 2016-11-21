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

/**动态的评论回复
 * @author 陈晓珊
 * 2016-10-20下午4:06:17
 */
@Entity
@Table(name = "DYNAMIC_REPLY")
public class DynamicReply implements java.io.Serializable{
	
	//主键id
	private Integer id;
	//动态的id
	private Integer dynamicId;
	//回复人的id
	private Integer replyName;
	//回复的内容
	private String replyContent;
	//回复人的昵称
	private String replyNick;
	//创建时间
	private Date createDate;
	//删除标识
	private String delFlag;
	//客户id
	private Integer cusId;
	
	@Id
	@SequenceGenerator(name="sequenceGenerator",sequenceName="SEQ_PUB_DYNAMICREPLY",allocationSize=1,initialValue=1)
	@GeneratedValue(generator="sequenceGenerator",strategy=GenerationType.SEQUENCE)
	@Column(name ="ID",nullable=false,length=16,unique=true)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@Column(name ="DYNAMIC_ID",nullable=true,length=16)
	public Integer getDynamicId() {
		return dynamicId;
	}
	public void setDynamicId(Integer dynamicId) {
		this.dynamicId = dynamicId;
	}
	@Column(name ="REPLY_NAME",nullable=true,length=16)
	public Integer getReplyName() {
		return replyName;
	}
	public void setReplyName(Integer replyName) {
		this.replyName = replyName;
	}
	@Column(name ="REPLY_CONTENT",nullable=true,length=500)
	public String getReplyContent() {
		return replyContent;
	}
	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}
	@Column(name ="REPLY_NICK",nullable=true,length=50)
	public String getReplyNick() {
		return replyNick;
	}
	public void setReplyNick(String replyNick) {
		this.replyNick = replyNick;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name ="CREATE_DATE",nullable=true)
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	@Column(name ="DEL_FLAG",nullable=true,length=1)
	public String getDelFlag() {
		return delFlag;
	}
	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}
	
	@Column(name ="CUS_ID",nullable=true,length=16)
	public Integer getCusId() {
		return cusId;
	}
	public void setCusId(Integer cusId) {
		this.cusId = cusId;
	}



}
