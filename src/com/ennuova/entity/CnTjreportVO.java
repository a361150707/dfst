package com.ennuova.entity;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

/**
 * CnTjreport entity. @author MyEclipse Persistence Tools
 */
public class CnTjreportVO implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private PubCarinfo pubCarinfo;
	private String fcph;
	private Timestamp ftjtime;
	private Long fzgzs;
	private Long ftstamp;
	private String timeStr;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public PubCarinfo getPubCarinfo() {
		return pubCarinfo;
	}
	public void setPubCarinfo(PubCarinfo pubCarinfo) {
		this.pubCarinfo = pubCarinfo;
	}
	public String getFcph() {
		return fcph;
	}
	public void setFcph(String fcph) {
		this.fcph = fcph;
	}
	public Timestamp getFtjtime() {
		return ftjtime;
	}
	public void setFtjtime(Timestamp ftjtime) {
		this.ftjtime = ftjtime;
	}
	public Long getFzgzs() {
		return fzgzs;
	}
	public void setFzgzs(Long fzgzs) {
		this.fzgzs = fzgzs;
	}
	public Long getFtstamp() {
		return ftstamp;
	}
	public void setFtstamp(Long ftstamp) {
		this.ftstamp = ftstamp;
	}
	public String getTimeStr() {
		return timeStr;
	}
	public void setTimeStr(String timeStr) {
		this.timeStr = timeStr;
	}
	
}