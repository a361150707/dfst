package com.ennuova.entity;

import java.math.BigDecimal;
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
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * PubVehiclemodel entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "PUB_VEHICLEMODEL")
public class PubVehiclemodel implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;//ID
	private PubBrand pubBrand;//品牌
	private PubLine pubLine;//车系
	private String imgStr;//图片路径
	private String fnumber;//车型编码
	private String fname;//车型名称
	private String fyearkuan;//年款
	private BigDecimal fissale;//是否在售
	private Double funifiedprice;//厂商指导价
	private String fmodeldesc;//车型说明
	private String fengine;//发动机
	private String fgearbox;//变速箱
	private String fareas;//车身规格(长*宽*高(mm))
	private String fstructure;//车身结构
	private String fmaxspeed;//最高车速(km/h)
	private String fexpedite;//官方0-100km/h加速(s)
	private String fwarranty;//整车质保
	private String fpl;//排量
	private String remarks;//备注
	private String delFlag;//删除标识
	private Long borrowNum;//浏览数       BORROW_NUM
	private Long attentionNum;//关注数  ATTENTION_NUM
	private Set<PubCarinfo> pubCarinfos = new HashSet<PubCarinfo>(0);
	

	//---不在数据库---//
	private Long xid;
	private String lname;
	private Long attentId;
	private int isFollow;
	private String lineName;
	private long lineId;
	@Transient
	public String getLineName() {
		return lineName;
	}

	public void setLineName(String lineName) {
		this.lineName = lineName;
	}
	@Transient
	public long getLineId() {
		return lineId;
	}

	public void setLineId(long lineId) {
		this.lineId = lineId;
	}

	// Constructors
	@Transient
	public String getImgStr() {
		return imgStr;
	}

	public void setImgStr(String imgStr) {
		this.imgStr = imgStr;
	}
	@Transient
	public Long getAttentId() {
		return attentId;
	}
	
	public void setAttentId(Long attentId) {
		this.attentId = attentId;
	}
	
	@Transient
	public Long getXid() {
		return xid;
	}


	public void setXid(Long xid) {
		this.xid = xid;
	}

	@Transient
	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}
	@Transient
	public int getIsFollow() {
		return isFollow;
	}

	public void setIsFollow(int isFollow) {
		this.isFollow = isFollow;
	}

	/** default constructor */
	public PubVehiclemodel() {
	}

	

	/** full constructor */
	public PubVehiclemodel(PubBrand pubBrand, PubLine pubLine, String fnumber,
			String fname, String fyearkuan, BigDecimal fissale,
			Double funifiedprice, String fmodeldesc, String fengine,
			String fgearbox, String fareas, String fstructure,
			String fmaxspeed, String fexpedite, String fwarranty,String fpl,
			String remarks,String delFlag,Set<PubCarinfo> pubCarinfos,Long attentionNum,Long borrowNum) {
		this.pubBrand = pubBrand;
		this.pubLine = pubLine;
		this.fnumber = fnumber;
		this.fname = fname;
		this.fyearkuan = fyearkuan;
		this.fissale = fissale;
		this.funifiedprice = funifiedprice;
		this.fmodeldesc = fmodeldesc;
		this.fengine = fengine;
		this.fgearbox = fgearbox;
		this.fareas = fareas;
		this.fstructure = fstructure;
		this.fmaxspeed = fmaxspeed;
		this.fexpedite = fexpedite;
		this.fwarranty = fwarranty;
		this.fpl = fpl;
		this.pubCarinfos = pubCarinfos;
		this.delFlag = delFlag;
		this.remarks = remarks;
		this.attentionNum = attentionNum;
		this.borrowNum = borrowNum;
	}

	// Property accessors
	@Id
	@SequenceGenerator(name="sequenceGenerator",sequenceName="seq_PUB_VEHICLEMODEL",allocationSize=1,initialValue=1)
	@GeneratedValue(generator="sequenceGenerator",strategy=GenerationType.SEQUENCE)
	@Column(name = "ID", unique = true, nullable = false, precision = 16, scale = 0)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "FBRADN")
	public PubBrand getPubBrand() {
		return this.pubBrand;
	}

	public void setPubBrand(PubBrand pubBrand) {
		this.pubBrand = pubBrand;
	}
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "FLINE")
	public PubLine getPubLine() {
		return this.pubLine;
	}

	public void setPubLine(PubLine pubLine) {
		this.pubLine = pubLine;
	}

	@Column(name = "FNUMBER", length = 50)
	public String getFnumber() {
		return this.fnumber;
	}

	public void setFnumber(String fnumber) {
		this.fnumber = fnumber;
	}

	@Column(name = "FNAME", length = 50)
	public String getFname() {
		return this.fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	@Column(name = "FYEARKUAN", length = 30)
	public String getFyearkuan() {
		return this.fyearkuan;
	}

	public void setFyearkuan(String fyearkuan) {
		this.fyearkuan = fyearkuan;
	}

	@Column(name = "FISSALE", precision = 22, scale = 0)
	public BigDecimal getFissale() {
		return this.fissale;
	}

	public void setFissale(BigDecimal fissale) {
		this.fissale = fissale;
	}

	@Column(name = "FUNIFIEDPRICE", precision = 2, scale = 0)
	public Double getFunifiedprice() {
		return this.funifiedprice;
	}

	public void setFunifiedprice(Double funifiedprice) {
		this.funifiedprice = funifiedprice;
	}

	@Column(name = "FMODELDESC", length = 100)
	public String getFmodeldesc() {
		return this.fmodeldesc;
	}

	public void setFmodeldesc(String fmodeldesc) {
		this.fmodeldesc = fmodeldesc;
	}

	@Column(name = "FENGINE", length = 60)
	public String getFengine() {
		return this.fengine;
	}

	public void setFengine(String fengine) {
		this.fengine = fengine;
	}

	@Column(name = "FGEARBOX", length = 50)
	public String getFgearbox() {
		return this.fgearbox;
	}

	public void setFgearbox(String fgearbox) {
		this.fgearbox = fgearbox;
	}

	@Column(name = "FAREAS", length = 30)
	public String getFareas() {
		return this.fareas;
	}

	public void setFareas(String fareas) {
		this.fareas = fareas;
	}

	@Column(name = "FSTRUCTURE", length = 30)
	public String getFstructure() {
		return this.fstructure;
	}

	public void setFstructure(String fstructure) {
		this.fstructure = fstructure;
	}

	@Column(name = "FMAXSPEED", length = 30)
	public String getFmaxspeed() {
		return this.fmaxspeed;
	}

	public void setFmaxspeed(String fmaxspeed) {
		this.fmaxspeed = fmaxspeed;
	}

	@Column(name = "FEXPEDITE", length = 30)
	public String getFexpedite() {
		return this.fexpedite;
	}

	public void setFexpedite(String fexpedite) {
		this.fexpedite = fexpedite;
	}

	@Column(name = "FWARRANTY", length = 30)
	public String getFwarranty() {
		return this.fwarranty;
	}

	public void setFwarranty(String fwarranty) {
		this.fwarranty = fwarranty;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "pubVehiclemodel")
	public Set<PubCarinfo> getPubCarinfos() {
		return this.pubCarinfos;
	}

	public void setPubCarinfos(Set<PubCarinfo> pubCarinfos) {
		this.pubCarinfos = pubCarinfos;
	}
	@Column(name = "FPL", length = 50)
	public String getFpl() {
		return this.fpl;
	}

	public void setFpl(String fpl) {
		this.fpl = fpl;
	}

	@Column(name = "REMARKS", length = 200)
	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	@Column(name = "DEL_FLAG", length = 5)
	public String getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}

	@Column(name="BORROW_NUM",length = 38)
	public Long getBorrowNum() {
		return borrowNum;
	}

	public void setBorrowNum(Long borrowNum) {
		this.borrowNum = borrowNum;
	}

	@Column(name="ATTENTION_NUM",length=38)
	public Long getAttentionNum() {
		return attentionNum;
	}

	public void setAttentionNum(Long attentionNum) {
		this.attentionNum = attentionNum;
	}
	
	
}