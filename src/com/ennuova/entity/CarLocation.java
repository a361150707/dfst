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
 * 车辆位置实体
 * @author sududa
 * @date 2016年11月4日
 *
 */
@Entity
@Table(name = "CAR_LOCATION")
public class CarLocation implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id; //主键id
	private Integer cusId; //当前登录人的id
	private float lng;//经度
	private float lat;//纬度
	private String cusPhoto;//客户头像
	private Date createtime;//创建时间
	private String delFlag;//删除标识
	private String cusInterest;//客户兴趣标签
	private String cusNick;//客户昵称
	private String lineName;//车系名称
	private String totalMileage;//总里程
	private String todayMileage;//当天的总里程
	private String todayOil;//当天的总油耗
	private String averageOil;//百公里平均油耗
	private String defCarId;//默认的车id
	private Integer sex;//性别
	
	@Id
	@SequenceGenerator(name="sequenceGenerator",sequenceName="SEQ_CAR_LOCATION",allocationSize=1,initialValue=1)
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
	
	@Column(name ="CUS_PHOTO",nullable=true,length=256)
	public String getCusPhoto() {
		return cusPhoto;
	}
	public void setCusPhoto(String cusPhoto) {
		this.cusPhoto = cusPhoto;
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
	
	@Column(name ="CUS_INTEREST",nullable=true,length=500)
	public String getCusInterest() {
		return cusInterest;
	}
	public void setCusInterest(String cusInterest) {
		this.cusInterest = cusInterest;
	}
	
	@Column(name ="CUS_NICK",nullable=true,length=100)
	public String getCusNick() {
		return cusNick;
	}
	public void setCusNick(String cusNick) {
		this.cusNick = cusNick;
	}
	
	@Column(name ="LINE_NAME",nullable=true,length=50)
	public String getLineName() {
		return lineName;
	}
	public void setLineName(String lineName) {
		this.lineName = lineName;
	}
	
	@Column(name ="TOTAL_MILEAGE",nullable=true,length=100)
	public String getTotalMileage() {
		return totalMileage;
	}
	public void setTotalMileage(String totalMileage) {
		this.totalMileage = totalMileage;
	}
	
	@Column(name ="TODAY_MILEAGE",nullable=true,length=100)
	public String getTodayMileage() {
		return todayMileage;
	}
	public void setTodayMileage(String todayMileage) {
		this.todayMileage = todayMileage;
	}
	
	@Column(name ="TODAY_OIL",nullable=true,length=50)
	public String getTodayOil() {
		return todayOil;
	}
	public void setTodayOil(String todayOil) {
		this.todayOil = todayOil;
	}
	
	@Column(name ="AVERAGE_OIL",nullable=true,length=50)
	public String getAverageOil() {
		return averageOil;
	}
	public void setAverageOil(String averageOil) {
		this.averageOil = averageOil;
	}
	
	@Transient
	public String getDefCarId() {
		return defCarId;
	}
	public void setDefCarId(String defCarId) {
		this.defCarId = defCarId;
	}
	@Column(name ="SEX",nullable=true)
	public Integer getSex() {
		return sex;
	}
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	
	
	
	
	
	
}