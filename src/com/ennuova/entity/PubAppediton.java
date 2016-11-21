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
 * PubAppediton entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "PUB_APPEDITON")
public class PubAppediton implements java.io.Serializable {

	// Fields

	private Long id;
	private String type;
	private String editon;
	private String content;
	private String link;
	private Date updatetime;

	// Constructors

	/** default constructor */
	public PubAppediton() {
	}

	/** minimal constructor */
	public PubAppediton(Long id) {
		this.id = id;
	}

	/** full constructor */
	public PubAppediton(Long id, String type, String editon, String content,
			String link, Date updatetime) {
		this.id = id;
		this.type = type;
		this.editon = editon;
		this.content = content;
		this.link = link;
		this.updatetime = updatetime;
	}

	// Property accessors
	@Id
	@SequenceGenerator(name="sequenceGenerator",sequenceName="seq_PUB_APPEDITON",allocationSize=1,initialValue=1)
	@GeneratedValue(generator="sequenceGenerator",strategy=GenerationType.SEQUENCE)
	@Column(name = "ID", unique = true, nullable = false, precision = 16, scale = 0)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "TYPE", length = 10)
	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Column(name = "EDITON", length = 10)
	public String getEditon() {
		return this.editon;
	}

	public void setEditon(String editon) {
		this.editon = editon;
	}

	@Column(name = "CONTENT", length = 1000)
	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "LINK", length = 100)
	public String getLink() {
		return this.link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "UPDATETIME", length = 7)
	public Date getUpdatetime() {
		return this.updatetime;
	}

	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}

}