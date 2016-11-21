package com.ennuova.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


/**
 * 设备登录信息
 */
@Entity
@Table(name = "CN_SBLJINFO")
public class CnSbljinfo implements java.io.Serializable {

	// Fields

	/**   
	* @Title: CnSbljinfo.java 
	* @Package com.ennuova.entity 
	* @Description: TODO(描述) 
	* @author felix
	* @date 2016年4月27日
	* @version V1.0   
	*/
	private static final long serialVersionUID = 1L;
	private Long id;
	private String fsbxlh;
	private String fak;
	private String fmy;
	private String fsbimei;
	private String fsim;
	private String fyjedition;
	private String fedition;
	private Timestamp fasksjc;
	private String fjyw;
	private String fvehiclemodel;
	private Timestamp fxysjc;
	private String floginip;
	private Long floginport;
	private Long fclid;
	private String fgy;
	private String fxysy;
	
	private String protocolno;
	private String extfield;
	private String messageid;
	private String dataid;
	private String framelength;
	private String frameid;
	private String paritybit;
	private String timestamp;

	// Constructors

	/** default constructor */
	public CnSbljinfo() {
	}

	/** minimal constructor */
	public CnSbljinfo(Long id) {
		this.id = id;
	}

	/** full constructor */
	public CnSbljinfo(Long id, String fsbxlh, String fak, String fmy,
			String fsbimei, String fsim, String fyjedition, String fedition,
			Timestamp fasksjc, String fjyw, String fvehiclemodel, Timestamp fxysjc,
			String floginip, Long floginport, Long fclid) {
		this.id = id;
		this.fsbxlh = fsbxlh;
		this.fak = fak;
		this.fmy = fmy;
		this.fsbimei = fsbimei;
		this.fsim = fsim;
		this.fyjedition = fyjedition;
		this.fedition = fedition;
		this.fasksjc = fasksjc;
		this.fjyw = fjyw;
		this.fvehiclemodel = fvehiclemodel;
		this.fxysjc = fxysjc;
		this.floginip = floginip;
		this.floginport = floginport;
		this.fclid = fclid;
	}

	// Property accessors
	@Id
	@SequenceGenerator(name="sequenceGenerator",sequenceName="seq_CN_SBLJINFO",allocationSize=1,initialValue=1)
	@GeneratedValue(generator="sequenceGenerator",strategy=GenerationType.SEQUENCE)
	@Column(name = "ID", unique = true, nullable = false, precision = 16, scale = 0)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "FSBXLH", length = 1000)
	public String getFsbxlh() {
		return this.fsbxlh;
	}

	public void setFsbxlh(String fsbxlh) {
		this.fsbxlh = fsbxlh;
	}

	@Column(name = "FAK", length = 1000)
	public String getFak() {
		return this.fak;
	}

	public void setFak(String fak) {
		this.fak = fak;
	}

	@Column(name = "FMY", length = 1000)
	public String getFmy() {
		return this.fmy;
	}

	public void setFmy(String fmy) {
		this.fmy = fmy;
	}

	@Column(name = "FSBIMEI", length = 1000)
	public String getFsbimei() {
		return this.fsbimei;
	}

	public void setFsbimei(String fsbimei) {
		this.fsbimei = fsbimei;
	}

	@Column(name = "FSIM", length = 100)
	public String getFsim() {
		return this.fsim;
	}

	public void setFsim(String fsim) {
		this.fsim = fsim;
	}

	@Column(name = "FYJEDITION", length = 50)
	public String getFyjedition() {
		return this.fyjedition;
	}

	public void setFyjedition(String fyjedition) {
		this.fyjedition = fyjedition;
	}

	@Column(name = "FEDITION", length = 50)
	public String getFedition() {
		return this.fedition;
	}

	public void setFedition(String fedition) {
		this.fedition = fedition;
	}

	@Column(name = "FASKSJC")
	public Timestamp getFasksjc() {
		return this.fasksjc;
	}

	public void setFasksjc(Timestamp fasksjc) {
		this.fasksjc = fasksjc;
	}

	@Column(name = "FJYW", length = 100)
	public String getFjyw() {
		return this.fjyw;
	}

	public void setFjyw(String fjyw) {
		this.fjyw = fjyw;
	}

	@Column(name = "FVEHICLEMODEL", length = 100)
	public String getFvehiclemodel() {
		return this.fvehiclemodel;
	}

	public void setFvehiclemodel(String fvehiclemodel) {
		this.fvehiclemodel = fvehiclemodel;
	}

	@Column(name = "FXYSJC")
	public Timestamp getFxysjc() {
		return this.fxysjc;
	}

	public void setFxysjc(Timestamp fxysjc) {
		this.fxysjc = fxysjc;
	}

	@Column(name = "FLOGINIP", length = 1000)
	public String getFloginip() {
		return this.floginip;
	}

	public void setFloginip(String floginip) {
		this.floginip = floginip;
	}

	@Column(name = "FLOGINPORT", precision = 16, scale = 0)
	public Long getFloginport() {
		return this.floginport;
	}

	public void setFloginport(Long floginport) {
		this.floginport = floginport;
	}

	@Column(name = "FCLID", precision = 16, scale = 0)
	public Long getFclid() {
		return this.fclid;
	}

	public void setFclid(Long fclid) {
		this.fclid = fclid;
	}

	@Column(name = "FGY", length = 1000)
	public String getFgy() {
		return this.fgy;
	}

	public void setFgy(String fgy) {
		this.fgy = fgy;
	}

	@Column(name = "FXYSY", length = 1000)
	public String getFxysy() {
		return this.fxysy;
	}

	public void setFxysy(String fxysy) {
		this.fxysy = fxysy;
	}
	
	@Column(name = "PROTOCOLNO", length = 1000)
	public String getProtocolno() {
		return this.protocolno;
	}

	public void setProtocolno(String protocolno) {
		this.protocolno = protocolno;
	}

	@Column(name = "EXTFIELD", length = 1000)
	public String getExtfield() {
		return this.extfield;
	}

	public void setExtfield(String extfield) {
		this.extfield = extfield;
	}

	@Column(name = "MESSAGEID", length = 1000)
	public String getMessageid() {
		return this.messageid;
	}

	public void setMessageid(String messageid) {
		this.messageid = messageid;
	}

	@Column(name = "DATAID", length = 1000)
	public String getDataid() {
		return this.dataid;
	}

	public void setDataid(String dataid) {
		this.dataid = dataid;
	}

	@Column(name = "FRAMELENGTH", length = 1000)
	public String getFramelength() {
		return this.framelength;
	}

	public void setFramelength(String framelength) {
		this.framelength = framelength;
	}

	@Column(name = "FRAMEID", length = 1000)
	public String getFrameid() {
		return this.frameid;
	}

	public void setFrameid(String frameid) {
		this.frameid = frameid;
	}
	@Column(name = "PARITYBIT", length = 1000)
	public String getParitybit() {
		return this.paritybit;
	}

	public void setParitybit(String paritybit) {
		this.paritybit = paritybit;
	}

	@Column(name = "TIMESTAMP", length = 1000)
	public String getTimestamp() {
		return this.timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}


}