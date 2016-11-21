package com.ennuova.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.SequenceGenerator;


/**动态的评论
 * @author 陈晓珊
 * 2016-11-2下午1:51:17
 */
@Entity
@Table(name = "DYNAMIC_COMMENT")
public class DynamicCommentEntity implements java.io.Serializable {
	//评论主键
	private Integer id;
	//评论的来源id
	private Integer fromId;
	//评论人的id
	private Integer toId;
	//评论人的名称
	private java.lang.String toName;
	//评论的内容
	private java.lang.String content;
	//创建时间
	private java.util.Date createDate;
	//删除标识
	private java.lang.String delFlag;
	//评论者头像
	private String to_img;
	
	@Id
	@SequenceGenerator(name="sequenceGenerator",sequenceName="SEQ_DYNAMIC_COMMENT",allocationSize=1,initialValue=1)
	@GeneratedValue(generator="sequenceGenerator",strategy=GenerationType.SEQUENCE)
	@Column(name ="ID",nullable=false,length=16,unique=true)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@Column(name ="FROM_ID",nullable=true,length=16)
	public Integer getFromId() {
		return fromId;
	}
	public void setFromId(Integer fromId) {
		this.fromId = fromId;
	}
	@Column(name ="TO_ID",nullable=true,length=16)
	public Integer getToId() {
		return toId;
	}
	public void setToId(Integer toId) {
		this.toId = toId;
	}
	@Column(name ="TO_NAME",nullable=true,length=50)
	public java.lang.String getToName() {
		return toName;
	}
	
	public void setToName(java.lang.String toName) {
		this.toName = toName;
	}
	@Column(name ="CONTENT",nullable=true,length=500)
	public java.lang.String getContent() {
		return content;
	}
	public void setContent(java.lang.String content) {
		this.content = content;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name ="CREATE_DATE",nullable=true)
	public java.util.Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(java.util.Date createDate) {
		this.createDate = createDate;
	}
	@Column(name ="DEL_FLAG",nullable=true,length=1)
	public java.lang.String getDelFlag() {
		return delFlag;
	}
	public void setDelFlag(java.lang.String delFlag) {
		this.delFlag = delFlag;
	}
	@Column(name ="TO_IMG",nullable=true,length=500)
	public String getTo_img() {
		return to_img;
	}
	public void setTo_img(String to_img) {
		this.to_img = to_img;
	}

	
}
