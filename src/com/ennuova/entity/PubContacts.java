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
import javax.persistence.Transient;

/**
 * 联系人实体
 * @author sududa
 * @date 2016年10月17日
 *
 */
@Entity
@Table(name = "PUB_CONTACTS")
public class PubContacts implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id; //主键id
	private String loginId; //当前登录人的id
	private String loginType;//当前登录人的类型(1-车主,2-员工)
	private String contactsId;//联系人的id
	private String contactsType;//联系人的类型(1-车主,2-员工)
	private String relation;//与联系人的关系(1-关注,2-拉黑)
	private Date createtime;//创建时间
	private String delFlag;//删除标识
	
	@Id
	@SequenceGenerator(name="sequenceGenerator",sequenceName="SEQ_PUB_CONTACTS",allocationSize=1,initialValue=1)
	@GeneratedValue(generator="sequenceGenerator",strategy=GenerationType.SEQUENCE)
	@Column(name ="ID",nullable=false,length=16,unique=true)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@Column(name ="LOGIN_ID",nullable=false,length=32)
	public String getLoginId() {
		return loginId;
	}
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
	@Column(name ="LOGIN_TYPE",nullable=false,length=1)
	public String getLoginType() {
		return loginType;
	}
	public void setLoginType(String loginType) {
		this.loginType = loginType;
	}
	@Column(name ="CONTACTS_ID",nullable=false,length=32)
	public String getContactsId() {
		return contactsId;
	}
	public void setContactsId(String contactsId) {
		this.contactsId = contactsId;
	}
	@Column(name ="CONTACTS_TYPE",nullable=false,length=1)
	public String getContactsType() {
		return contactsType;
	}
	public void setContactsType(String contactsType) {
		this.contactsType = contactsType;
	}
	@Column(name ="RELATION",nullable=false,length=1)
	public String getRelation() {
		return relation;
	}
	public void setRelation(String relation) {
		this.relation = relation;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name ="CREATE_DATE",nullable=false)
	public Date getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	@Column(name ="DEL_FLAG",nullable=true,length=1)
	public String getDelFlag() {
		return delFlag;
	}
	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}
	
	
	
	
}