package com.ennuova.entity;

import java.util.List;

public class MessageListVO {
	private Long messageType;	//消息类别
	private String name;	//消息名
	private Long messageNum;	//此消息类别条数
	private String timeStr;	//转换后的最新消息字符串时间
	private String newMesssage;	//最新的消息内容
	private List<CnInfodetail> cnInfodetails;//消息列表
	private List<CnInfo> cnInfos;
	public Long getMessageType() {
		return messageType;
	}
	public void setMessageType(Long messageType) {
		this.messageType = messageType;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getMessageNum() {
		return messageNum;
	}
	public void setMessageNum(Long messageNum) {
		this.messageNum = messageNum;
	}
	public String getTimeStr() {
		return timeStr;
	}
	public void setTimeStr(String timeStr) {
		this.timeStr = timeStr;
	}
	public String getNewMesssage() {
		return newMesssage;
	}
	public void setNewMesssage(String newMesssage) {
		this.newMesssage = newMesssage;
	}
	public List<CnInfodetail> getCnInfodetails() {
		return cnInfodetails;
	}
	public void setCnInfodetails(List<CnInfodetail> cnInfodetails) {
		this.cnInfodetails = cnInfodetails;
	}
	public List<CnInfo> getCnInfos() {
		return cnInfos;
	}
	public void setCnInfos(List<CnInfo> cnInfos) {
		this.cnInfos = cnInfos;
	}
	
}
