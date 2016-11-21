package com.ennuova.entity;

import java.sql.Timestamp;
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
 * CnInfo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "CN_INFO")
public class CnInfo implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;//	ID
	private String fmodule;//	消息模块
	private String fsubject;//	主题
	private Date fsendtime;//	发送时间
	private String createBy;//	创建人
	private Timestamp createDate;//	创建时间
	private String updateBy;//	更新人
	private Timestamp updateDate;//	更新时间
	private String remarks;//	备注
	private String delFlag;//	删除标志
	private String SLink;//	链接地址
	private Long BWay;//	发送方式
	private Long RCorp;//	创建公司
	private String fcontent;//	内容
	private int messageType;//消息类型 1-故障 2-防盗 3-驾驶 4-安全 5-车务 6-资讯 7-意见反馈 8-i宝团队
	
	private Long clid;//车辆信息

	private Long cusId;//客户id
	private Long detailId;//明细id
	
	private Long status;//选中状态
	
	
	private Timestamp readTime;//阅读时间
	
	private Long notReadNum;//未读数量
	
	private Integer reserveType;
	private Integer reserveState;
	
	


	// Constructors

	@Transient
	public Long getNotReadNum() {
		return notReadNum;
	}
	
	public void setNotReadNum(Long notReadNum) {
		this.notReadNum = notReadNum;
	}
	
	
	@Transient
	public Timestamp getReadTime() {
		return readTime;
	}


	public void setReadTime(Timestamp readTime) {
		this.readTime = readTime;
	}

	@Transient
	public Long getStatus() {
		return status;
	}

	public void setStatus(Long status) {
		this.status = status;
	}

	@Transient
	public Long getDetailId() {
		return detailId;
	}

	public void setDetailId(Long detailId) {
		this.detailId = detailId;
	}

	@Transient
	public Long getCusId() {
		return cusId;
	}

	public void setCusId(Long cusId) {
		this.cusId = cusId;
	}

	/** default constructor */
	public CnInfo() {
	}

	/** minimal constructor */
	public CnInfo(Long id) {
		this.id = id;
	}

	/** full constructor */
	public CnInfo(Long id, String fmodule, String fsubject, Date fsendtime,
			String createBy, Timestamp createDate, String updateBy,
			Timestamp updateDate, String remarks, String delFlag, String SLink,
			Long BWay, Long RCorp, String fcontent) {
		this.id = id;
		this.fmodule = fmodule;
		this.fsubject = fsubject;
		this.fsendtime = fsendtime;
		this.createBy = createBy;
		this.createDate = createDate;
		this.updateBy = updateBy;
		this.updateDate = updateDate;
		this.remarks = remarks;
		this.delFlag = delFlag;
		this.SLink = SLink;
		this.BWay = BWay;
		this.RCorp = RCorp;
		this.fcontent = fcontent;
	}

	// Property accessors
	@Id
	@SequenceGenerator(name="sequenceGenerator",sequenceName="seq_CN_INFO",allocationSize=1,initialValue=1)
	@GeneratedValue(generator="sequenceGenerator",strategy=GenerationType.SEQUENCE)
	@Column(name = "ID", unique = true, nullable = false, precision = 16, scale = 0)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "FMODULE", length = 30)
	public String getFmodule() {
		return this.fmodule;
	}

	public void setFmodule(String fmodule) {
		this.fmodule = fmodule;
	}

	@Column(name = "FSUBJECT", length = 100)
	public String getFsubject() {
		return this.fsubject;
	}

	public void setFsubject(String fsubject) {
		this.fsubject = fsubject;
	}

	@Column(name = "FSENDTIME", length = 7)
	public Date getFsendtime() {
		return this.fsendtime;
	}

	public void setFsendtime(Date fsendtime) {
		this.fsendtime = fsendtime;
	}

	@Column(name = "CREATE_BY", length = 64)
	public String getCreateBy() {
		return this.createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	@Column(name = "CREATE_DATE", length = 11)
	public Timestamp getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}

	@Column(name = "UPDATE_BY", length = 64)
	public String getUpdateBy() {
		return this.updateBy;
	}

	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}

	@Column(name = "UPDATE_DATE", length = 11)
	public Timestamp getUpdateDate() {
		return this.updateDate;
	}

	public void setUpdateDate(Timestamp updateDate) {
		this.updateDate = updateDate;
	}

	@Column(name = "REMARKS")
	public String getRemarks() {
		return this.remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	@Column(name = "DEL_FLAG", length = 1)
	public String getDelFlag() {
		return this.delFlag;
	}

	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}

	@Column(name = "S_LINK", length = 500)
	public String getSLink() {
		return this.SLink;
	}

	public void setSLink(String SLink) {
		this.SLink = SLink;
	}

	@Column(name = "B_WAY", precision = 16, scale = 0)
	public Long getBWay() {
		return this.BWay;
	}

	public void setBWay(Long BWay) {
		this.BWay = BWay;
	}

	@Column(name = "R_CORP", precision = 16, scale = 0)
	public Long getRCorp() {
		return this.RCorp;
	}

	public void setRCorp(Long RCorp) {
		this.RCorp = RCorp;
	}

	@Column(name = "FCONTENT", length = 2000)
	public String getFcontent() {
		return this.fcontent;
	}

	public void setFcontent(String fcontent) {
		this.fcontent = fcontent;
	}
	@Column(name = "CLID", precision = 16, scale = 0)
	public Long getClid() {
		return this.clid;
	}

	public void setClid(Long clid) {
		this.clid = clid;
	}
	@Column(name = "MESSAGE_TYPE", precision = 2, scale = 0)
	public int getMessageType() {
		return messageType;
	}

	public void setMessageType(int messageType) {
		this.messageType = messageType;
	}

	@Column(name = "RESERVE_TYPE", length = 6)
	public Integer getReserveType() {
		return reserveType;
	}

	public void setReserveType(Integer reserveType) {
		this.reserveType = reserveType;
	}

	@Column(name = "RESERVE_STATE", length = 6)
	public Integer getReserveState() {
		return reserveState;
	}

	public void setReserveState(Integer reserveState) {
		this.reserveState = reserveState;
	}
	
	
}