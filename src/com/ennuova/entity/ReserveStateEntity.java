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
 * @Description: 预约状态表
 * @author onlineGenerator
 * @date 2016-09-13 14:01:19
 * @version V1.0   
 *
 */
@Entity
@Table(name = "reserve_state", schema = "")
@SequenceGenerator(name="SEQ_GEN", sequenceName="SEQ_RESERVE_STATE")  
@SuppressWarnings("serial")
public class ReserveStateEntity implements java.io.Serializable {
	/**预约状态ID*/
	private java.lang.Integer id;
	/**预约状态名称*/
	private java.lang.String name;
	/**预约状态类型*/
	private java.lang.Integer statusType;
	
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  预约状态ID
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="SEQ_GEN")  
	@Column(name ="ID",nullable=false,length=20)
	public java.lang.Integer getId(){
		return this.id;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  预约状态ID
	 */
	public void setId(java.lang.Integer id){
		this.id = id;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  预约状态名称
	 */
	@Column(name ="NAME",nullable=true,length=30)
	public java.lang.String getName(){
		return this.name;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  预约状态名称
	 */
	public void setName(java.lang.String name){
		this.name = name;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  预约状态类型
	 */
	@Column(name ="STATUS_TYPE",nullable=true,length=6)
	public java.lang.Integer getStatusType(){
		return this.statusType;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  预约状态类型
	 */
	public void setStatusType(java.lang.Integer statusType){
		this.statusType = statusType;
	}
}
