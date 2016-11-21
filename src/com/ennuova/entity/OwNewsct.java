package com.ennuova.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
 * OwNewsct entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "OW_NEWSCT")
public class OwNewsct implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private PubUser pubUser;
	private String ftitle;
	private String fnrPic;
	private Date fbtime;
	private String fcompany;
	private BigDecimal fzhiding;
	private BigDecimal fstate;
	private BigDecimal fviewcount;//浏览次数
	private Date flastview;
	private String fnrText;
	private Long ftype;//新闻资讯类别（1 代表新闻，2代表折扣，3代表新车）
	private List<OwNewsindex> owNewsindexes = new ArrayList<OwNewsindex>(0);
	
	private String uname;//来源用户名
	
	private String tpNm;//类型名称
	private Long goodCount;//点赞数
	private Long shareCout;//分享数
	private Long commentCount;//评论次数
	private String keyWord;//关键词，用逗号分隔
	private String timeStr;//转化后的时间
	private Long lineId;	//车系ID
	private int lineDetail;//是否是车系详解0-否 1-是
	// Constructors

	@Transient
	public String getTpNm() {
		return tpNm;
	}

	public void setTpNm(String tpNm) {
		this.tpNm = tpNm;
	}

	@Column(name = "FTYPE", precision = 16, scale = 0)
	public String getUname() {
		return uname;
	}

	public Long getFtype() {
		return ftype;
	}

	public void setFtype(Long ftype) {
		this.ftype = ftype;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	/** default constructor */
	public OwNewsct() {
	}

	/** full constructor */
	public OwNewsct(PubUser pubUser, String ftitle, String fnrPic, Date fbtime,
			String fcompany, BigDecimal fzhiding, BigDecimal fstate,
			BigDecimal fviewcount, Date flastview, String fnrText,Long ftype,
			List<OwNewsindex> owNewsindexes) {
		this.pubUser = pubUser;
		this.ftitle = ftitle;
		this.fnrPic = fnrPic;
		this.fbtime = fbtime;
		this.fcompany = fcompany;
		this.fzhiding = fzhiding;
		this.fstate = fstate;
		this.fviewcount = fviewcount;
		this.flastview = flastview;
		this.fnrText = fnrText;
		this.ftype = ftype;
		this.owNewsindexes = owNewsindexes;
	}

	// Property accessors
	@Id
	@SequenceGenerator(name="sequenceGenerator",sequenceName="seq_OW_NEWSCT",allocationSize=1,initialValue=1)
	@GeneratedValue(generator="sequenceGenerator",strategy=GenerationType.SEQUENCE)
	@Column(name = "ID", unique = true, nullable = false, precision = 16, scale = 0)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "FBUSER")
	public PubUser getPubUser() {
		return this.pubUser;
	}

	public void setPubUser(PubUser pubUser) {
		this.pubUser = pubUser;
	}

	@Column(name = "FTITLE", length = 50)
	public String getFtitle() {
		return this.ftitle;
	}

	public void setFtitle(String ftitle) {
		this.ftitle = ftitle;
	}

	@Column(name = "FNR_PIC", length = 256)
	public String getFnrPic() {
		return this.fnrPic;
	}

	public void setFnrPic(String fnrPic) {
		this.fnrPic = fnrPic;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "FBTIME", length = 7)
	public Date getFbtime() {
		return this.fbtime;
	}

	public void setFbtime(Date fbtime) {
		this.fbtime = fbtime;
	}

	@Column(name = "FCOMPANY", precision = 16, scale = 0)
	public String getFcompany() {
		return this.fcompany;
	}

	public void setFcompany(String fcompany) {
		this.fcompany = fcompany;
	}

	@Column(name = "FZHIDING", precision = 22, scale = 0)
	public BigDecimal getFzhiding() {
		return this.fzhiding;
	}

	public void setFzhiding(BigDecimal fzhiding) {
		this.fzhiding = fzhiding;
	}

	@Column(name = "FSTATE", precision = 22, scale = 0)
	public BigDecimal getFstate() {
		return this.fstate;
	}

	public void setFstate(BigDecimal fstate) {
		this.fstate = fstate;
	}

	@Column(name = "FVIEWCOUNT", precision = 22, scale = 0)
	public BigDecimal getFviewcount() {
		return this.fviewcount;
	}

	public void setFviewcount(BigDecimal fviewcount) {
		this.fviewcount = fviewcount;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "FLASTVIEW", length = 7)
	public Date getFlastview() {
		return this.flastview;
	}

	public void setFlastview(Date flastview) {
		this.flastview = flastview;
	}

	@Column(name = "FNR_TEXT")
	public String getFnrText() {
		return this.fnrText;
	}

	public void setFnrText(String fnrText) {
		this.fnrText = fnrText;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "owNewsct")
	public List<OwNewsindex> getOwNewsindexes() {
		return this.owNewsindexes;
	}

	public void setOwNewsindexes(List<OwNewsindex> owNewsindexes) {
		this.owNewsindexes = owNewsindexes;
	}
	@Column(name = "GOOD_COUNT", length = 16)
	public Long getGoodCount() {
		return goodCount;
	}

	public void setGoodCount(Long goodCount) {
		this.goodCount = goodCount;
	}
	@Column(name = "SHARE_COUNT", length = 16)
	public Long getShareCout() {
		return shareCout;
	}

	public void setShareCout(Long shareCout) {
		this.shareCout = shareCout;
	}
	@Column(name = "COMMENT_COUNT", length = 16)
	public Long getCommentCount() {
		return commentCount;
	}

	public void setCommentCount(Long commentCount) {
		this.commentCount = commentCount;
	}
	@Column(name = "KEY_WORD")
	public String getKeyWord() {
		return keyWord;
	}

	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}
	@Transient
	public String getTimeStr() {
		return timeStr;
	}

	public void setTimeStr(String timeStr) {
		this.timeStr = timeStr;
	}
	@Column(name = "FLINEID",length=16)
	public Long getLineId() {
		return lineId;
	}

	public void setLineId(Long lineId) {
		this.lineId = lineId;
	}
	@Column(name = "FIS_LINE_DETAIL")
	public int getLineDetail() {
		return lineDetail;
	}

	public void setLineDetail(int lineDetail) {
		this.lineDetail = lineDetail;
	}

}