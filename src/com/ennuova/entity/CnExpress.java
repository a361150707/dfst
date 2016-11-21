package com.ennuova.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * CnExpress entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "CN_EXPRESS")
public class CnExpress implements java.io.Serializable {

	// Fields

	private Long id;
	private Long userId;
	private String userName;
	private String userAddress;
	private String userMobile;
	private String isSend;
	private String expressNumber;
	private String expressName;

	// Constructors

	/** default constructor */
	public CnExpress() {
	}

	/** minimal constructor */
	public CnExpress(Long id) {
		this.id = id;
	}

	/** full constructor */
	public CnExpress(Long id, Long userId, String userName, String userAddress,
			String userMobile, String isSend, String expressNumber,
			String expressName) {
		this.id = id;
		this.userId = userId;
		this.userName = userName;
		this.userAddress = userAddress;
		this.userMobile = userMobile;
		this.isSend = isSend;
		this.expressNumber = expressNumber;
		this.expressName = expressName;
	}

	// Property accessors
	@Id
	@SequenceGenerator(name="sequenceGenerator",sequenceName="seq_CN_EXPRESS",allocationSize=1,initialValue=1)
	@GeneratedValue(generator="sequenceGenerator",strategy=GenerationType.SEQUENCE)
	@Column(name = "ID", unique = true, nullable = false, precision = 16, scale = 0)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "USER_ID", precision = 16, scale = 0)
	public Long getUserId() {
		return this.userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	@Column(name = "USER_NAME", length = 50)
	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Column(name = "USER_ADDRESS", length = 200)
	public String getUserAddress() {
		return this.userAddress;
	}

	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}

	@Column(name = "USER_MOBILE", length = 15)
	public String getUserMobile() {
		return this.userMobile;
	}

	public void setUserMobile(String userMobile) {
		this.userMobile = userMobile;
	}

	@Column(name = "IS_SEND", length = 2)
	public String getIsSend() {
		return this.isSend;
	}

	public void setIsSend(String isSend) {
		this.isSend = isSend;
	}

	@Column(name = "EXPRESS_NUMBER", length = 100)
	public String getExpressNumber() {
		return this.expressNumber;
	}

	public void setExpressNumber(String expressNumber) {
		this.expressNumber = expressNumber;
	}

	@Column(name = "EXPRESS_NAME", length = 20)
	public String getExpressName() {
		return this.expressName;
	}

	public void setExpressName(String expressName) {
		this.expressName = expressName;
	}

}