package com.ennuova.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**   
 * @Title: Entity
 * @Description: 预约状态变更记录表
 * @author onlineGenerator
 * @date 2016-09-13 14:15:22
 * @version V1.0   
 *
 */
@Entity
@Table(name = "reserve_status_change_record")
public class ReserveStatusChangeRecordEntity implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	/**预约状态变更记录ID*/
	private java.lang.Integer id;
	/**预约ID*/
	private java.lang.Integer reserveId;
	/**预约状态*/
	private java.lang.Integer reserveState;
	/**备注*/
	private java.lang.String remarks;
	/**删除标识*/
	private java.lang.String delFlag;
	/**创建人*/
	private java.lang.String createBy;
	/**创建时间*/
	private java.util.Date createDate;
	
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  预约状态变更记录ID
	 */
	@Id
	@SequenceGenerator(name="sequenceGenerator", sequenceName="SEQ_RESERVE_STATUS_RECORD")  
	@GeneratedValue(generator="sequenceGenerator",strategy=GenerationType.SEQUENCE)  
	@Column(name ="ID",nullable=false,length=20,unique=true)
	public java.lang.Integer getId(){
		return this.id;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  预约状态变更记录ID
	 */
	public void setId(java.lang.Integer id){
		this.id = id;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  预约ID
	 */
	@Column(name ="RESERVE_ID",nullable=true,length=16)
	public java.lang.Integer getReserveId(){
		return this.reserveId;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  预约ID
	 */
	public void setReserveId(java.lang.Integer reserveId){
		this.reserveId = reserveId;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  预约状态
	 */
	@Column(name ="RESERVE_STATE",nullable=true,length=6)
	public java.lang.Integer getReserveState(){
		return this.reserveState;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  预约状态
	 */
	public void setReserveState(java.lang.Integer reserveState){
		this.reserveState = reserveState;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  备注
	 */
	@Column(name ="REMARKS",nullable=true,length=255)
	public java.lang.String getRemarks(){
		return this.remarks;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  备注
	 */
	public void setRemarks(java.lang.String remarks){
		this.remarks = remarks;
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
}
