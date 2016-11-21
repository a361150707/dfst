package com.ennuova.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.lang.String;
import java.lang.Double;
import java.lang.Integer;
import java.math.BigDecimal;
import javax.xml.soap.Text;
import java.sql.Blob;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.SequenceGenerator;

/**   
 * @Title: Entity
 * @Description: 点赞
 * @author onlineGenerator
 * @date 2016-07-26 16:17:01
 * @version V1.0   
 *
 */
@Entity
@Table(name = "click_good", schema = "")
@SequenceGenerator(name="SEQ_GEN", sequenceName="SEQ_CLICK_GOOD")  
@SuppressWarnings("serial")
public class ClickGoodEntity implements java.io.Serializable {
	/**点赞主键*/
	private Long id;
	/**点赞的来源id*/
	private Long fromId;
	/**点赞人的id*/
	private Long toId;
	/**点赞的类型*/
	private java.lang.String goodType;
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
	
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  点赞主键
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="SEQ_GEN")  
	@Column(name ="ID",nullable=false,length=20)
	public Long getId(){
		return this.id;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  点赞主键
	 */
	public void setId(Long id){
		this.id = id;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  点赞的来源id
	 */
	@Column(name ="FROM_ID",nullable=true,length=16)
	public Long getFromId(){
		return this.fromId;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  点赞的来源id
	 */
	public void setFromId(Long fromId){
		this.fromId = fromId;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  点赞人的id
	 */
	@Column(name ="TO_ID",nullable=true,length=16)
	public Long getToId(){
		return this.toId;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  点赞人的id
	 */
	public void setToId(Long toId){
		this.toId = toId;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  点赞的类型
	 */
	@Column(name ="GOOD_TYPE",nullable=true,length=5)
	public java.lang.String getGoodType(){
		return this.goodType;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  点赞的类型
	 */
	public void setGoodType(java.lang.String goodType){
		this.goodType = goodType;
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
}
