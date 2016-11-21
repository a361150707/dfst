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
 * @Description: 员工变更记录表
 * @author onlineGenerator
 * @date 2016-09-13 14:15:52
 * @version V1.0   
 *
 */
@Entity
@Table(name = "staff_change_record", schema = "")
@SequenceGenerator(name="SEQ_GEN", sequenceName="SEQ_STAFF_CHANGE_RECORD")  
@SuppressWarnings("serial")
public class StaffChangeRecordEntity implements java.io.Serializable {
	/**员工变更记录ID*/
	private java.lang.Integer id;
	/**预约ID*/
	private java.lang.Integer reserveId;
	/**员工ID*/
	private java.lang.String fuserId;
	/**创建时间*/
	private java.util.Date createDate;
	/**备注*/
	private java.lang.String remarks;
	private Integer reserveState;//预约状态
	
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  员工变更记录ID
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="SEQ_GEN")  
	@Column(name ="ID",nullable=false,length=20)
	public java.lang.Integer getId(){
		return this.id;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  员工变更记录ID
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
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  员工ID
	 */
	@Column(name ="FUSER_ID",nullable=true,length=32)
	public java.lang.String getFuserId(){
		return this.fuserId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  员工ID
	 */
	public void setFuserId(java.lang.String fuserId){
		this.fuserId = fuserId;
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
	 * 方法: 取得 Integer 预约状态
	 *@author sududa
	 *@date 2016年9月22日
	 * @return Integer 预约状态
	 */
	@Column(name ="RESERVE_STATE",nullable=true,length=6)
	public Integer getReserveState() {
		return reserveState;
	}

	/**
	 *方法: 设置  Integer 预约状态
	 *@author sududa
	 *@date 2016年9月22日
	 * @param reserveState Integer 预约状态
	 */
	public void setReserveState(Integer reserveState) {
		this.reserveState = reserveState;
	}
	
	
}
