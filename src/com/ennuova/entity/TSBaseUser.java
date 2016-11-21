package com.ennuova.entity;

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * TSBaseUser entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "T_S_BASE_USER")
public class TSBaseUser implements java.io.Serializable {

	// Fields

	private String id;
	private int activitisync;//是否同步工作流引擎
	private String browser;// 用户使用浏览器类型
	private String password;//用户密码
	private String realname;// 真实姓名
	private String signature;// 签名文件
	private int status;// 状态1：在线,2：离线,0：禁用
	private String userkey;// 用户验证唯一标示
	private String username;// 用户名
	private String departid;
	private String userheadimg;//用户头像
	private String mobilephone;//联系电话

	// Constructors

	/** default constructor */
	public TSBaseUser() {
	}

	/** minimal constructor */
	public TSBaseUser(String id, String username) {
		this.id = id;
		this.username = username;
	}

	/** full constructor */
	public TSBaseUser(String id, int activitisync, String browser,
			String password, String realname, String signature,
			int status, String userkey, String username,
			String departid, String userheadimg, String mobilephone) {
		this.id = id;
		this.activitisync = activitisync;
		this.browser = browser;
		this.password = password;
		this.realname = realname;
		this.signature = signature;
		this.status = status;
		this.userkey = userkey;
		this.username = username;
		this.departid = departid;
		this.userheadimg = userheadimg;
		this.mobilephone = mobilephone;
	}

	// Property accessors
	@Id
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
	@Column(name ="ID",unique = true,nullable=false,length=32)
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "ACTIVITISYNC", precision = 22, scale = 0)
	public int getActivitisync() {
		return this.activitisync;
	}

	public void setActivitisync(int activitisync) {
		this.activitisync = activitisync;
	}

	@Column(name = "BROWSER", length = 20)
	public String getBrowser() {
		return this.browser;
	}

	public void setBrowser(String browser) {
		this.browser = browser;
	}

	@Column(name = "PASSWORD", length = 100)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "REALNAME", length = 50)
	public String getRealname() {
		return this.realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	@Column(name = "SIGNATURE")
	public String getSignature() {
		return this.signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	@Column(name = "STATUS", precision = 22, scale = 0)
	public int getStatus() {
		return this.status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	@Column(name = "USERKEY", length = 200)
	public String getUserkey() {
		return this.userkey;
	}

	public void setUserkey(String userkey) {
		this.userkey = userkey;
	}

	@Column(name = "USERNAME", nullable = false, length = 10)
	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Column(name = "DEPARTID", length = 32)
	public String getDepartid() {
		return this.departid;
	}

	public void setDepartid(String departid) {
		this.departid = departid;
	}

	@Column(name = "USERHEADIMG", length = 250)
	public String getUserheadimg() {
		return this.userheadimg;
	}

	public void setUserheadimg(String userheadimg) {
		this.userheadimg = userheadimg;
	}

	@Column(name = "MOBILEPHONE", length = 20)
	public String getMobilephone() {
		return this.mobilephone;
	}

	public void setMobilephone(String mobilephone) {
		this.mobilephone = mobilephone;
	}

}