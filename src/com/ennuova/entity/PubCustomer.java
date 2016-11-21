package com.ennuova.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;


/**
 * PubCustomer entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "PUB_CUSTOMER")
public class PubCustomer implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private String fphoto;
	private String fnick;
	private String fusername;
	private String fpassword;
	private BigDecimal fsex;
	private String ftel;
	private String fzhiye;
	private BigDecimal fjiayear;
	private String faddr;
	private Date fcreatetime;
	private String sex;
	private Set<PubBdstore> pubBdstores = new HashSet<PubBdstore>(0);
	private Set<OwCusalarms> owCusalarmses = new HashSet<OwCusalarms>(0);
	private Set<OwJlpzsz> owJlpzszs = new HashSet<OwJlpzsz>(0);
	private Set<PubPurchasebox> pubPurchaseboxes = new HashSet<PubPurchasebox>(
			0);
	private Set<PubCarinfo> pubCarinfos = new HashSet<PubCarinfo>(0);
	
	private Long defCarId;
	private Long defBoxId;
	private String boxNum;
	private Integer mcount;
	private String alias;
	private String tags;
	private String carNum;//车牌号
	private String vname;//车型名称
	private String dataCompletion;//资料完成度
	private String defaultImg;//默认车辆图片
	private String userId;//员工id即管家
	private Long storeId;//门店Id
	private String cusInterest;//客户兴趣
	private Integer isSearchTel;//是否通过电话搜索到自己(1_允许,2_不允许)
	private String lineName;//车系名称
	
	
	@Transient
	public String getCarNum() {
		return carNum;
	}

	public void setCarNum(String carNum) {
		this.carNum = carNum;
	}

	@Transient
	public String getVname() {
		return vname;
	}

	public void setVname(String vname) {
		this.vname = vname;
	}

	@Transient
	public Long getDefBoxId() {
		return defBoxId;
	}

	public void setDefBoxId(Long defBoxId) {
		this.defBoxId = defBoxId;
	}

	@Transient
	public String getBoxNum() {
		return boxNum;
	}

	public void setBoxNum(String boxNum) {
		this.boxNum = boxNum;
	}

	@Transient
	public Integer getMcount() {
		return mcount;
	}

	public void setMcount(Integer mcount) {
		this.mcount = mcount;
	}

	@Transient
	public Long getDefCarId() {
		return defCarId;
	}
	
	public void setDefCarId(Long defCarId) {
		this.defCarId = defCarId;
	}
	

	// Constructors


	/** default constructor */
	public PubCustomer() {
	}

	@Transient
	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}
	

	/**
	 * @param id
	 */
	public PubCustomer(Long id) {
		this.id = id;
	}

	/** full constructor */
	public PubCustomer(String fphoto, String fnick, String fusername,
			String fpassword, BigDecimal fsex, String ftel, String fzhiye,
			BigDecimal fjiayear, String faddr, Date fcreatetime,
			Set<PubBdstore> pubBdstores, Set<OwCusalarms> owCusalarmses,
			Set<OwJlpzsz> owJlpzszs, Set<PubPurchasebox> pubPurchaseboxes,
			Set<PubCarinfo> pubCarinfos) {
		this.fphoto = fphoto;
		this.fnick = fnick;
		this.fusername = fusername;
		this.fpassword = fpassword;
		this.fsex = fsex;
		this.ftel = ftel;
		this.fzhiye = fzhiye;
		this.fjiayear = fjiayear;
		this.faddr = faddr;
		this.fcreatetime = fcreatetime;
		this.pubBdstores = pubBdstores;
		this.owCusalarmses = owCusalarmses;
		this.owJlpzszs = owJlpzszs;
		this.pubPurchaseboxes = pubPurchaseboxes;
		this.pubCarinfos = pubCarinfos;
	}

	// Property accessors
	@Id
	@Column(name = "ID", unique = true, nullable = false, precision = 16, scale = 0)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "FPHOTO", length = 256)
	public String getFphoto() {
		return this.fphoto;
	}

	public void setFphoto(String fphoto) {
		this.fphoto = fphoto;
	}

	@Column(name = "FNICK", length = 100)
	public String getFnick() {
		return this.fnick;
	}

	public void setFnick(String fnick) {
		this.fnick = fnick;
	}

	@Column(name = "FUSERNAME", length = 50)
	public String getFusername() {
		return this.fusername;
	}

	public void setFusername(String fusername) {
		this.fusername = fusername;
	}

	@Column(name = "FPASSWORD", length = 100)
	public String getFpassword() {
		return this.fpassword;
	}

	public void setFpassword(String fpassword) {
		this.fpassword = fpassword;
	}

	@Column(name = "FSEX", precision = 22, scale = 0)
	public BigDecimal getFsex() {
		return this.fsex;
	}

	public void setFsex(BigDecimal fsex) {
		this.fsex = fsex;
	}

	@Column(name = "FTEL", length = 100)
	public String getFtel() {
		return this.ftel;
	}

	public void setFtel(String ftel) {
		this.ftel = ftel;
	}

	@Column(name = "FZHIYE", length = 100)
	public String getFzhiye() {
		return this.fzhiye;
	}

	public void setFzhiye(String fzhiye) {
		this.fzhiye = fzhiye;
	}

	@Column(name = "FJIAYEAR", precision = 22, scale = 0)
	public BigDecimal getFjiayear() {
		return this.fjiayear;
	}

	public void setFjiayear(BigDecimal fjiayear) {
		this.fjiayear = fjiayear;
	}

	@Column(name = "FADDR", length = 100)
	public String getFaddr() {
		return this.faddr;
	}

	public void setFaddr(String faddr) {
		this.faddr = faddr;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "FCREATETIME", length = 7)
	public Date getFcreatetime() {
		return this.fcreatetime;
	}

	public void setFcreatetime(Date fcreatetime) {
		this.fcreatetime = fcreatetime;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "pubCustomer")
	public Set<PubBdstore> getPubBdstores() {
		return this.pubBdstores;
	}

	public void setPubBdstores(Set<PubBdstore> pubBdstores) {
		this.pubBdstores = pubBdstores;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "pubCustomer")
	public Set<OwCusalarms> getOwCusalarmses() {
		return this.owCusalarmses;
	}

	public void setOwCusalarmses(Set<OwCusalarms> owCusalarmses) {
		this.owCusalarmses = owCusalarmses;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "pubCustomer")
	public Set<OwJlpzsz> getOwJlpzszs() {
		return this.owJlpzszs;
	}

	public void setOwJlpzszs(Set<OwJlpzsz> owJlpzszs) {
		this.owJlpzszs = owJlpzszs;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "pubCustomer")
	public Set<PubPurchasebox> getPubPurchaseboxes() {
		return this.pubPurchaseboxes;
	}

	public void setPubPurchaseboxes(Set<PubPurchasebox> pubPurchaseboxes) {
		this.pubPurchaseboxes = pubPurchaseboxes;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "pubCustomer")
	public Set<PubCarinfo> getPubCarinfos() {
		return this.pubCarinfos;
	}

	public void setPubCarinfos(Set<PubCarinfo> pubCarinfos) {
		this.pubCarinfos = pubCarinfos;
	}
	@Column(name = "ALIAS", length = 64)
	public String getAlias() {
		return this.alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	@Column(name = "TAGS", length = 64)
	public String getTags() {
		return this.tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}
	@Transient
	public String getDataCompletion() {
		return dataCompletion;
	}
	
	public void setDataCompletion(String dataCompletion) {
		this.dataCompletion = dataCompletion;
	}
	@Transient
	public String getDefaultImg() {
		return defaultImg;
	}

	public void setDefaultImg(String defaultImg) {
		this.defaultImg = defaultImg;
	}

	@Column(name = "USER_ID", length = 32)
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	@Transient
	public Long getStoreId() {
		return storeId;
	}

	public void setStoreId(Long storeId) {
		this.storeId = storeId;
	}

	@Column(name = "CUS_INTEREST", length = 500)
	public String getCusInterest() {
		return cusInterest;
	}

	public void setCusInterest(String cusInterest) {
		this.cusInterest = cusInterest;
	}

	@Column(name = "IS_SEARCH_TEL")
	public Integer getIsSearchTel() {
		return isSearchTel;
	}

	public void setIsSearchTel(Integer isSearchTel) {
		this.isSearchTel = isSearchTel;
	}
	
	@Transient
	public String getLineName() {
		return lineName;
	}

	public void setLineName(String lineName) {
		this.lineName = lineName;
	}
	
	
	
	
}