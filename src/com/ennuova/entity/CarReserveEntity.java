package com.ennuova.entity;

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
 * @Title: Entity
 * @Description: 预约记录表
 * @author onlineGenerator
 * @date 2016-09-13 14:17:23
 * @version V1.0   
 *
 */
@Entity
@Table(name = "car_reserve", schema = "")
@SequenceGenerator(name="SEQ_GEN", sequenceName="SEQ_CAR_RESERVE")  
@SuppressWarnings("serial")
public class CarReserveEntity implements java.io.Serializable {
	/**预约记录ID*/
	private java.lang.Integer id;
	/**客户id*/
	private java.lang.Integer fcusId;
	/**车辆id*/
	private java.lang.Integer fcarId;
	/**预约类型*/
	private java.lang.Integer reserveType;
	/**预约时间*/
	private java.util.Date reserveDate;
	/**预约状态*/
	private java.lang.Integer reserveState;
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
	/**更新人名称*/
	private java.lang.String updateName;
	/**所属机构*/
	private java.lang.String sysOrgCode;
	/**所属公司*/
	private java.lang.String sysCompanyCode;
	/**是否上门取车*/
	private java.lang.String isHome;
	/**取车地址*/
	private java.lang.String homeAddress;
	/**备注*/
	private java.lang.String remarks;
	/**当前顾问ID*/
	private java.lang.String userId;
	private String cusName;
	private String cusTel;
	private String carNumber;
	private String carModel;
	private int userDelFlag;
	
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  预约记录ID
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="SEQ_GEN")  
	@Column(name ="ID",nullable=false,length=20)
	public java.lang.Integer getId(){
		return this.id;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  预约记录ID
	 */
	public void setId(java.lang.Integer id){
		this.id = id;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  客户id
	 */
	@Column(name ="FCUS_ID",nullable=true,length=16)
	public java.lang.Integer getFcusId(){
		return this.fcusId;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  客户id
	 */
	public void setFcusId(java.lang.Integer fcusId){
		this.fcusId = fcusId;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  车辆id
	 */
	@Column(name ="FCAR_ID",nullable=true,length=16)
	public java.lang.Integer getFcarId(){
		return this.fcarId;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  车辆id
	 */
	public void setFcarId(java.lang.Integer fcarId){
		this.fcarId = fcarId;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  预约类型
	 */
	@Column(name ="RESERVE_TYPE",nullable=true,length=6)
	public java.lang.Integer getReserveType(){
		return this.reserveType;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  预约类型
	 */
	public void setReserveType(java.lang.Integer reserveType){
		this.reserveType = reserveType;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  预约时间
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name ="RESERVE_DATE",nullable=true)
	public java.util.Date getReserveDate(){
		return this.reserveDate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  预约时间
	 */
	public void setReserveDate(java.util.Date reserveDate){
		this.reserveDate = reserveDate;
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
	 *@return: java.lang.String  是否上门取车
	 */
	@Column(name ="IS_HOME",nullable=true,length=1)
	public java.lang.String getIsHome(){
		return this.isHome;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  是否上门取车
	 */
	public void setIsHome(java.lang.String isHome){
		this.isHome = isHome;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  取车地址
	 */
	@Column(name ="HOME_ADDRESS",nullable=true,length=200)
	public java.lang.String getHomeAddress(){
		return this.homeAddress;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  取车地址
	 */
	public void setHomeAddress(java.lang.String homeAddress){
		this.homeAddress = homeAddress;
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
	 *@return: java.lang.String  当前顾问ID
	 */
	@Column(name ="USER_ID",nullable=true,length=32)
	public java.lang.String getUserId(){
		return this.userId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  当前顾问ID
	 */
	public void setUserId(java.lang.String userId){
		this.userId = userId;
	}

	@Column(name ="CUS_NAME",nullable=true,length=50)
	public String getCusName() {
		return cusName;
	}

	public void setCusName(String cusName) {
		this.cusName = cusName;
	}

	@Column(name ="CUS_TEL",nullable=true,length=50)
	public String getCusTel() {
		return cusTel;
	}

	public void setCusTel(String cusTel) {
		this.cusTel = cusTel;
	}

	@Column(name ="CAR_NUMBER",nullable=true,length=50)
	public String getCarNumber() {
		return carNumber;
	}

	public void setCarNumber(String carNumber) {
		this.carNumber = carNumber;
	}

	@Column(name ="CAR_MODEL",nullable=true,length=50)
	public String getCarModel() {
		return carModel;
	}

	public void setCarModel(String carModel) {
		this.carModel = carModel;
	}
	@Column(name ="USER_DEL_FLAG",length=2)
	public int getUserDelFlag() {
		return userDelFlag;
	}

	public void setUserDelFlag(int userDelFlag) {
		this.userDelFlag = userDelFlag;
	}
	
	
}
