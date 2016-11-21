package com.ennuova.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.SequenceGenerator;

/**   
 * @Title: Entity
 * @Description: 回复
 * @author onlineGenerator
 * @date 2016-07-26 16:14:02
 * @version V1.0   
 *
 */
@Entity
@Table(name = "pub_reply")
@SuppressWarnings("serial")
public class PubReplyEntity implements java.io.Serializable {
	/**回复主键*/
	private Long id;
	/**回复的来源id*/
	private Long fromId;
	/**回复人的id*/
	private Long toId;
	/**回复人的名称*/
	private java.lang.String toName;
	/**回复的内容*/
	private java.lang.String content;
	/**创建人*/
	private java.lang.String createBy;
	/**创建时间*/
	private java.util.Date createDate;
	/**更新人*/
	private java.lang.String updateBy;
	/**更新时间*/
	private java.util.Date updateDate;
	/**删除标识*/
	private java.lang.String delFlag;
	/**创建人名称*/
	private java.lang.String createName;
	/**所属机构*/
	private java.lang.String sysOrgCode;
	/**所属公司*/
	private java.lang.String sysCompanyCode;
	/**更新人名称*/
	private java.lang.String updateName;
	//创建时间
	private String dateStr;
	
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  回复主键
	 */
	@Id
	@SequenceGenerator(name="sequenceGenerator",sequenceName="SEQ_PUB_REPLY",allocationSize=1,initialValue=1)
	@GeneratedValue(generator="sequenceGenerator",strategy=GenerationType.SEQUENCE)
	@Column(name = "ID", unique = true, nullable = false, precision = 16, scale = 0)
	public Long getId(){
		return this.id;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  回复主键
	 */
	public void setId(Long id){
		this.id = id;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  回复的来源id
	 */
	@Column(name ="FROM_ID",nullable=true,length=16)
	public Long getFromId(){
		return this.fromId;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  回复的来源id
	 */
	public void setFromId(Long fromId){
		this.fromId = fromId;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  回复人的id
	 */
	@Column(name ="TO_ID",nullable=true,length=16)
	public Long getToId(){
		return this.toId;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  回复人的id
	 */
	public void setToId(Long toId){
		this.toId = toId;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  回复人的名称
	 */
	@Column(name ="TO_NAME",nullable=true,length=50)
	public java.lang.String getToName(){
		return this.toName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  回复人的名称
	 */
	public void setToName(java.lang.String toName){
		this.toName = toName;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  回复的内容
	 */
	@Column(name ="CONTENT",nullable=true,length=500)
	public java.lang.String getContent(){
		return this.content;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  回复的内容
	 */
	public void setContent(java.lang.String content){
		this.content = content;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  创建人
	 */
	@Column(name ="CREATE_BY",nullable=true,length=32)
	public java.lang.String getCreateBy(){
		return this.createBy;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  创建人
	 */
	public void setCreateBy(java.lang.String createBy){
		this.createBy = createBy;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  创建时间
	 */
	@Column(name ="CREATE_DATE",nullable=true)
	public java.util.Date getCreateDate(){
		return this.createDate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  创建时间
	 */
	public void setCreateDate(java.util.Date createDate){
		this.createDate = createDate;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  更新人
	 */
	@Column(name ="UPDATE_BY",nullable=true,length=32)
	public java.lang.String getUpdateBy(){
		return this.updateBy;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  更新人
	 */
	public void setUpdateBy(java.lang.String updateBy){
		this.updateBy = updateBy;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  更新时间
	 */
	@Column(name ="UPDATE_DATE",nullable=true)
	public java.util.Date getUpdateDate(){
		return this.updateDate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  更新时间
	 */
	public void setUpdateDate(java.util.Date updateDate){
		this.updateDate = updateDate;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  删除标识
	 */
	@Column(name ="DEL_FLAG",nullable=true,length=1)
	public java.lang.String getDelFlag(){
		return this.delFlag;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  删除标识
	 */
	public void setDelFlag(java.lang.String delFlag){
		this.delFlag = delFlag;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  创建人名称
	 */
	@Column(name ="CREATE_NAME",nullable=true,length=50)
	public java.lang.String getCreateName(){
		return this.createName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  创建人名称
	 */
	public void setCreateName(java.lang.String createName){
		this.createName = createName;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  所属机构
	 */
	@Column(name ="SYS_ORG_CODE",nullable=true,length=50)
	public java.lang.String getSysOrgCode(){
		return this.sysOrgCode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  所属机构
	 */
	public void setSysOrgCode(java.lang.String sysOrgCode){
		this.sysOrgCode = sysOrgCode;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  所属公司
	 */
	@Column(name ="SYS_COMPANY_CODE",nullable=true,length=50)
	public java.lang.String getSysCompanyCode(){
		return this.sysCompanyCode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  所属公司
	 */
	public void setSysCompanyCode(java.lang.String sysCompanyCode){
		this.sysCompanyCode = sysCompanyCode;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  更新人名称
	 */
	@Column(name ="UPDATE_NAME",nullable=true,length=50)
	public java.lang.String getUpdateName(){
		return this.updateName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  更新人名称
	 */
	public void setUpdateName(java.lang.String updateName){
		this.updateName = updateName;
	}
	@Transient
	public String getDateStr() {
		return dateStr;
	}

	public void setDateStr(String dateStr) {
		this.dateStr = dateStr;
	}
	
}
