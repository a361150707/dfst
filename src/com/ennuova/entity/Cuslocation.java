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
 * 客户位置的实体
 * @author sududa
 * @date 2016年10月18日
 *
 */
@Entity
@Table(name = "CUS_LOCATION")
public class Cuslocation implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id; //主键id
	private Integer cusId; //当前登录人的id
	private float lng;//经度
	private float lat;//纬度
	private String locationType;//获取位置的类型(1-手机,2-车)
	private Date createtime;//创建时间
	private String delFlag;//删除标识
	
	@Id
	@SequenceGenerator(name="sequenceGenerator",sequenceName="SEQ_CUS_LOCATION",allocationSize=1,initialValue=1)
	@GeneratedValue(generator="sequenceGenerator",strategy=GenerationType.SEQUENCE)
	@Column(name ="ID",nullable=false,length=16,unique=true)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@Column(name ="CUS_ID",nullable=false,length=16)
	public Integer getCusId() {
		return cusId;
	}
	public void setCusId(Integer cusId) {
		this.cusId = cusId;
	}
	@Column(name ="LNG",nullable=false)
	public float getLng() {
		return lng;
	}
	public void setLng(float lng) {
		this.lng = lng;
	}
	@Column(name ="LAT",nullable=false)
	public float getLat() {
		return lat;
	}
	public void setLat(float lat) {
		this.lat = lat;
	}
	@Column(name ="LOCATION_TYPE",nullable=false,length=1)
	public String getLocationType() {
		return locationType;
	}
	public void setLocationType(String locationType) {
		this.locationType = locationType;
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