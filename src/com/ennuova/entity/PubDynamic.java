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
 * 动态的表
 * @author sududa
 * @date 2016年10月19日
 *
 */
@Entity
@Table(name = "PUB_DYNAMIC")
public class PubDynamic implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id; //主键id
	private String sendId; //发送人的id
	private String sendType;//发送人的类型(1-车主,2-员工)
	private String title;//标题
	private String content;//内容
	private String dynamicType;//动态类型(1-动态,2-话题)
	private String imgPath;//图片路径(多张以;分隔开)
	private Date createtime;//创建时间
	private String delFlag;//删除标识
	private String showType;//动态显示的类型(1-公开,2-好友可见)
	private String dymanicPlateId;//话题的id
	private String state;//话题的状态(1-编辑,2-发布,3-关闭)
	private String shrinkDiagram;//缩列图
	private String[] arrImg;
	private String cusLocation;//客户位置
	
	@Id
	@SequenceGenerator(name="sequenceGenerator",sequenceName="SEQ_PUB_DYNAMIC",allocationSize=1,initialValue=1)
	@GeneratedValue(generator="sequenceGenerator",strategy=GenerationType.SEQUENCE)
	@Column(name ="ID",nullable=false,length=16,unique=true)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@Column(name ="SEND_ID",nullable=false,length=32)
	public String getSendId() {
		return sendId;
	}
	public void setSendId(String sendId) {
		this.sendId = sendId;
	}
	@Column(name ="SEND_TYPE",nullable=false,length=1)
	public String getSendType() {
		return sendType;
	}
	public void setSendType(String sendType) {
		this.sendType = sendType;
	}
	@Column(name ="TITLE",nullable=true,length=50)
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	@Column(name ="CONTENT",nullable=true,length=1024)
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	@Column(name ="DYNAMIC_TYPE",nullable=false,length=1)
	public String getDynamicType() {
		return dynamicType;
	}
	public void setDynamicType(String dynamicType) {
		this.dynamicType = dynamicType;
	}
	@Column(name ="IMG_PATH",nullable=true,length=500)
	public String getImgPath() {
		return imgPath;
	}
	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}
	@Column(name ="SHOW_TYPE",nullable=true,length=1)
	public String getShowType() {
		return showType;
	}
	public void setShowType(String showType) {
		this.showType = showType;
	}
	@Column(name ="DYNAMIC_PLATE_ID",nullable=true,length=16)
	public String getDymanicPlateId() {
		return dymanicPlateId;
	}
	public void setDymanicPlateId(String dymanicPlateId) {
		this.dymanicPlateId = dymanicPlateId;
	}
	@Column(name ="STATE",nullable=true)
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
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
	@Column(name ="SHRINK_DIAGRAM",nullable=true,length=200)
	public String getShrinkDiagram() {
		return shrinkDiagram;
	}
	public void setShrinkDiagram(String shrinkDiagram) {
		this.shrinkDiagram = shrinkDiagram;
	}
	
	@Transient
	public String[] getArrImg() {
		return arrImg;
	}
	public void setArrImg(String[] arrImg) {
		this.arrImg = arrImg;
	}
	@Column(name ="CUS_LOCATION",nullable=true,length=100)
	public String getCusLocation() {
		return cusLocation;
	}
	public void setCusLocation(String cusLocation) {
		this.cusLocation = cusLocation;
	}
	
	
	
	
	
	
}