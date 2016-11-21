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
 * 举报的实体
 * @author sududa
 * @date 2016年10月19日
 *
 */
@Entity
@Table(name = "PUB_REPORT")
public class PubReport implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id; //主键id
	private String loginId; //当前登录人的id
	private String loginType;//当前登录人的类型(1-车主,2-员工)
	private String reportId;//被举报人的id
	private String reportType;//被举报人的类型(1-车主,2-员工)
	private String content;//举报的内容
	private Date createtime;//创建时间
	private String delFlag;//删除标识
	
	@Id
	@SequenceGenerator(name="sequenceGenerator",sequenceName="SEQ_PUB_REPORT",allocationSize=1,initialValue=1)
	@GeneratedValue(generator="sequenceGenerator",strategy=GenerationType.SEQUENCE)
	@Column(name ="ID",nullable=false,length=16,unique=true)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@Column(name ="LOGIN_ID",nullable=false,length=1)
	public String getLoginId() {
		return loginId;
	}
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
	@Column(name ="LOGIN_TYPE",nullable=false,length=1)
	public String getLoginType() {
		return loginType;
	}
	public void setLoginType(String loginType) {
		this.loginType = loginType;
	}
	@Column(name ="REPORT_ID",nullable=false,length=32)
	public String getReportId() {
		return reportId;
	}
	public void setReportId(String reportId) {
		this.reportId = reportId;
	}
	@Column(name ="REPORT_TYPE",nullable=false,length=1)
	public String getReportType() {
		return reportType;
	}
	public void setReportType(String reportType) {
		this.reportType = reportType;
	}
	@Column(name ="CONTENT",nullable=false,length=500)
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
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