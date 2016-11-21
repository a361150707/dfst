package com.ennuova.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;


/**
 * PubCarinfo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "PUB_CARINFO")
public class PubCarinfo implements java.io.Serializable {

	// Fields

	/**
	 * @Description: TODO
	 * @author felix
	 * @date 2015-12-17
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private PubBrand pubBrand;
	private PubVehiclemodel pubVehiclemodel;
	private PubCustomer pubCustomer;
	private PubLine pubLine;
	private String fcarcode;
	private String fcarnum;
	private String fenginenum;
	private Long fdefault;
	private Set<CnTjreport> cnTjreports = new HashSet<CnTjreport>(0);
	private Set<CnBjrecode> cnBjrecodes = new HashSet<CnBjrecode>(0);
	private Set<CnSsclxx> cnSsclxxes = new HashSet<CnSsclxx>(0);
	private Set<CnXcrecode> cnXcrecodes = new HashSet<CnXcrecode>(0);
	private Set<CnSsxcrecode> cnSsxcrecodes = new HashSet<CnSsxcrecode>(0);
	private Set<CnDzwl> cnDzwls = new HashSet<CnDzwl>(0);
	private Set<CnGzrecode> cnGzrecodes = new HashSet<CnGzrecode>(0);
	private Set<PubBox> pubBoxes = new HashSet<PubBox>(0);
	
	
	private Long cusId;//客户id
	private Long bid;//品牌id
	private Long lid;//车系id
	private Long vid;//车型id
	private String blogo;//品牌logo
	private String bname;//品牌名称
	private String lname;//车系名称
	private String vname;//车型名称
	
	private Long boxId;//盒子id
	private String fxlh;//盒子序列号
	
	
	private Date finsexpire;//保险到期
    private Date fguaexpire;//保修到期
    private Date fyearinsexpire;//年检到期
    private Long finscompany;//保险公司
    private String userId;//专属顾问
    private Date maintianTime;//保养时间
	
	private Integer bxianDay;//保险到期天数
	private Integer bxiuDay;//保修到期天数
	private Integer njianDay;//年检到期天数
	private Integer byangDay;//保养时间天数
	private float byangMileage;//保养里程数
	private String linePicture;//车系图片
	private String lineName;//车系名称
	

	
	@Transient
	public Integer getBxianDay() {
		return bxianDay;
	}

	public void setBxianDay(Integer bxianDay) {
		this.bxianDay = bxianDay;
	}

	@Transient
	public Integer getBxiuDay() {
		return bxiuDay;
	}

	public void setBxiuDay(Integer bxiuDay) {
		this.bxiuDay = bxiuDay;
	}

	@Transient
	public Integer getNjianDay() {
		return njianDay;
	}

	public void setNjianDay(Integer njianDay) {
		this.njianDay = njianDay;
	}

	@Transient
	public Integer getByangDay() {
		return byangDay;
	}

	public void setByangDay(Integer byangDay) {
		this.byangDay = byangDay;
	}

	
	
	@Transient
	public float getByangMileage() {
		return byangMileage;
	}

	public void setByangMileage(float byangMileage) {
		this.byangMileage = byangMileage;
	}

	// Constructors
	@Transient
	public String getFxlh() {
		return fxlh;
	}

	public void setFxlh(String fxlh) {
		this.fxlh = fxlh;
	}

	@Transient
	public Long getBoxId() {
		return boxId;
	}

	public void setBoxId(Long boxId) {
		this.boxId = boxId;
	}

	@Transient
	public String getBlogo() {
		return blogo;
	}
	
	public void setBlogo(String blogo) {
		this.blogo = blogo;
	}
	
	@Transient
	public Long getCusId() {
		return cusId;
	}


	public void setCusId(Long cusId) {
		this.cusId = cusId;
	}

	@Transient
	public Long getBid() {
		return bid;
	}

	public void setBid(Long bid) {
		this.bid = bid;
	}

	@Transient
	public Long getLid() {
		return lid;
	}

	public void setLid(Long lid) {
		this.lid = lid;
	}

	@Transient
	public Long getVid() {
		return vid;
	}

	public void setVid(Long vid) {
		this.vid = vid;
	}

	@Transient
	public String getBname() {
		return bname;
	}

	public void setBname(String bname) {
		this.bname = bname;
	}

	@Transient
	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	@Transient
	public String getVname() {
		return vname;
	}

	public void setVname(String vname) {
		this.vname = vname;
	}


	// Constructors

	/** default constructor */
	public PubCarinfo() {
	}

	/** minimal constructor */
	public PubCarinfo(Long id) {
		this.id = id;
	}

	/** full constructor */
	public PubCarinfo(Long id, PubBrand pubBrand,
			PubVehiclemodel pubVehiclemodel, PubCustomer pubCustomer,
			PubLine pubLine, String fcarcode, String fcarnum,
			String fenginenum, Long fdefault, Set<CnTjreport> cnTjreports,
			Set<CnBjrecode> cnBjrecodes, Set<CnSsclxx> cnSsclxxes,
			Set<CnXcrecode> cnXcrecodes, Set<CnSsxcrecode> cnSsxcrecodes,
			Set<CnDzwl> cnDzwls, Set<CnGzrecode> cnGzrecodes,
			Set<PubBox> pubBoxes) {
		this.id = id;
		this.pubBrand = pubBrand;
		this.pubVehiclemodel = pubVehiclemodel;
		this.pubCustomer = pubCustomer;
		this.pubLine = pubLine;
		this.fcarcode = fcarcode;
		this.fcarnum = fcarnum;
		this.fenginenum = fenginenum;
		this.fdefault = fdefault;
		this.cnTjreports = cnTjreports;
		this.cnBjrecodes = cnBjrecodes;
		this.cnSsclxxes = cnSsclxxes;
		this.cnXcrecodes = cnXcrecodes;
		this.cnSsxcrecodes = cnSsxcrecodes;
		this.cnDzwls = cnDzwls;
		this.cnGzrecodes = cnGzrecodes;
		this.pubBoxes = pubBoxes;
	}

	// Property accessors
	@Id
	@SequenceGenerator(name="sequenceGenerator",sequenceName="seq_PUB_CARINFO",allocationSize=1,initialValue=1)
	@GeneratedValue(generator="sequenceGenerator",strategy=GenerationType.SEQUENCE)
	@Column(name = "ID", unique = true, nullable = false, precision = 16, scale = 0)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "FBRAND")
	public PubBrand getPubBrand() {
		return this.pubBrand;
	}

	public void setPubBrand(PubBrand pubBrand) {
		this.pubBrand = pubBrand;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "FVEHICLEMODEL")
	public PubVehiclemodel getPubVehiclemodel() {
		return this.pubVehiclemodel;
	}

	public void setPubVehiclemodel(PubVehiclemodel pubVehiclemodel) {
		this.pubVehiclemodel = pubVehiclemodel;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "FCUSTOMER")
	public PubCustomer getPubCustomer() {
		return this.pubCustomer;
	}

	public void setPubCustomer(PubCustomer pubCustomer) {
		this.pubCustomer = pubCustomer;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "FLINE")
	public PubLine getPubLine() {
		return this.pubLine;
	}

	public void setPubLine(PubLine pubLine) {
		this.pubLine = pubLine;
	}

	@Column(name = "FCARCODE", length = 100)
	public String getFcarcode() {
		return this.fcarcode;
	}

	public void setFcarcode(String fcarcode) {
		this.fcarcode = fcarcode;
	}

	@Column(name = "FCARNUM", length = 100)
	public String getFcarnum() {
		return this.fcarnum;
	}

	public void setFcarnum(String fcarnum) {
		this.fcarnum = fcarnum;
	}

	@Column(name = "FENGINENUM", length = 100)
	public String getFenginenum() {
		return this.fenginenum;
	}

	public void setFenginenum(String fenginenum) {
		this.fenginenum = fenginenum;
	}

	@Column(name = "FDEFAULT", precision = 16, scale = 0)
	public Long getFdefault() {
		return this.fdefault;
	}

	public void setFdefault(Long fdefault) {
		this.fdefault = fdefault;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "pubCarinfo")
	public Set<CnTjreport> getCnTjreports() {
		return this.cnTjreports;
	}

	public void setCnTjreports(Set<CnTjreport> cnTjreports) {
		this.cnTjreports = cnTjreports;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "pubCarinfo")
	public Set<CnBjrecode> getCnBjrecodes() {
		return this.cnBjrecodes;
	}

	public void setCnBjrecodes(Set<CnBjrecode> cnBjrecodes) {
		this.cnBjrecodes = cnBjrecodes;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "pubCarinfo")
	public Set<CnSsclxx> getCnSsclxxes() {
		return this.cnSsclxxes;
	}

	public void setCnSsclxxes(Set<CnSsclxx> cnSsclxxes) {
		this.cnSsclxxes = cnSsclxxes;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "pubCarinfo")
	public Set<CnXcrecode> getCnXcrecodes() {
		return this.cnXcrecodes;
	}

	public void setCnXcrecodes(Set<CnXcrecode> cnXcrecodes) {
		this.cnXcrecodes = cnXcrecodes;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "pubCarinfo")
	public Set<CnSsxcrecode> getCnSsxcrecodes() {
		return this.cnSsxcrecodes;
	}

	public void setCnSsxcrecodes(Set<CnSsxcrecode> cnSsxcrecodes) {
		this.cnSsxcrecodes = cnSsxcrecodes;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "pubCarinfo")
	public Set<CnDzwl> getCnDzwls() {
		return this.cnDzwls;
	}

	public void setCnDzwls(Set<CnDzwl> cnDzwls) {
		this.cnDzwls = cnDzwls;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "pubCarinfo")
	public Set<CnGzrecode> getCnGzrecodes() {
		return this.cnGzrecodes;
	}

	public void setCnGzrecodes(Set<CnGzrecode> cnGzrecodes) {
		this.cnGzrecodes = cnGzrecodes;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "pubCarinfo")
	public Set<PubBox> getPubBoxes() {
		return this.pubBoxes;
	}

	public void setPubBoxes(Set<PubBox> pubBoxes) {
		this.pubBoxes = pubBoxes;
	}


    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="FINSEXPIRE", length=12)
    public Date getFinsexpire() {
        return this.finsexpire;
    }
    
    public void setFinsexpire(Date finsexpire) {
        this.finsexpire = finsexpire;
    }
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="FGUAEXPIRE", length=12)
    public Date getFguaexpire() {
        return this.fguaexpire;
    }
    
    public void setFguaexpire(Date fguaexpire) {
        this.fguaexpire = fguaexpire;
    }
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="FYEARINSEXPIRE", length=12)
    public Date getFyearinsexpire() {
        return this.fyearinsexpire;
    }
    
    public void setFyearinsexpire(Date fyearinsexpire) {
        this.fyearinsexpire = fyearinsexpire;
    }
    
    @Column(name="FINSCOMPANY", precision=16, scale=0)
    public Long getFinscompany() {
        return this.finscompany;
    }
    
    public void setFinscompany(Long finscompany) {
        this.finscompany = finscompany;
    }
    
    @Column(name="USER_ID", length=32)
    public String getUserId() {
        return this.userId;
    }
    
    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Transient
	public String getLinePicture() {
		return linePicture;
	}

	public void setLinePicture(String linePicture) {
		this.linePicture = linePicture;
	}
	@Transient
	public Date getMaintianTime() {
		return maintianTime;
	}

	public void setMaintianTime(Date maintianTime) {
		this.maintianTime = maintianTime;
	}

	@Transient
	public String getLineName() {
		return lineName;
	}

	public void setLineName(String lineName) {
		this.lineName = lineName;
	}
	
	
    
    

}