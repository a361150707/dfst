package com.ennuova.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * PubStore entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "PUB_STORE")
public class PubStore implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private String fname;
	private Long fprovince;
	private Long fcity;
	private Long farea;
	private String fcode;
	private String fsypicture;
	private String flogo;
	private String fbackpc;
	private String faddr;
	private String ftel;
	private String fweibo;
	private String fweixin;
	private String ftext;
	private Long fdefault;
	
	private Long fcompany;
	private Double flng;
	private Double flat;
	// Constructors

	
	private Long bid;
	
	
	
	@Transient
	public Long getBid() {
		return bid;
	}

	public void setBid(Long bid) {
		this.bid = bid;
	}

	/** default constructor */
	public PubStore() {
	}

	@Transient
	public Long getFcompany() {
		return fcompany;
	}

	public void setFcompany(Long fcompany) {
		this.fcompany = fcompany;
	}

	public Long getFdefault() {
		return fdefault;
	}

	public void setFdefault(Long fdefault) {
		this.fdefault = fdefault;
	}

	/** full constructor */
	public PubStore(String fname,
			Long fprovince, Long fcity, Long farea, String fcode,
			String fsypicture, String flogo, String fbackpc, String faddr,
			String ftel, String fweibo, String fweixin, String ftext) {
		this.fname = fname;
		this.fprovince = fprovince;
		this.fcity = fcity;
		this.farea = farea;
		this.fcode = fcode;
		this.fsypicture = fsypicture;
		this.flogo = flogo;
		this.fbackpc = fbackpc;
		this.faddr = faddr;
		this.ftel = ftel;
		this.fweibo = fweibo;
		this.fweixin = fweixin;
		this.ftext = ftext;
	}

	// Property accessors
	@Id
	@SequenceGenerator(name="sequenceGenerator",sequenceName="seq_PUB_STORE",allocationSize=1,initialValue=1)
	@GeneratedValue(generator="sequenceGenerator",strategy=GenerationType.SEQUENCE)
	@Column(name = "ID", unique = true, nullable = false, precision = 16, scale = 0)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	@Column(name = "FNAME", length = 100)
	public String getFname() {
		return this.fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	@Column(name = "FPROVINCE", precision = 16, scale = 0)
	public Long getFprovince() {
		return this.fprovince;
	}

	public void setFprovince(Long fprovince) {
		this.fprovince = fprovince;
	}

	@Column(name = "FCITY", precision = 16, scale = 0)
	public Long getFcity() {
		return this.fcity;
	}

	public void setFcity(Long fcity) {
		this.fcity = fcity;
	}

	@Column(name = "FAREA", precision = 16, scale = 0)
	public Long getFarea() {
		return this.farea;
	}

	public void setFarea(Long farea) {
		this.farea = farea;
	}

	@Column(name = "FCODE", length = 256)
	public String getFcode() {
		return this.fcode;
	}

	public void setFcode(String fcode) {
		this.fcode = fcode;
	}

	@Column(name = "FSYPICTURE", length = 256)
	public String getFsypicture() {
		return this.fsypicture;
	}

	public void setFsypicture(String fsypicture) {
		this.fsypicture = fsypicture;
	}

	@Column(name = "FLOGO", length = 256)
	public String getFlogo() {
		return this.flogo;
	}

	public void setFlogo(String flogo) {
		this.flogo = flogo;
	}

	@Column(name = "FBACKPC", length = 256)
	public String getFbackpc() {
		return this.fbackpc;
	}

	public void setFbackpc(String fbackpc) {
		this.fbackpc = fbackpc;
	}

	@Column(name = "FADDR", length = 100)
	public String getFaddr() {
		return this.faddr;
	}

	public void setFaddr(String faddr) {
		this.faddr = faddr;
	}

	@Column(name = "FTEL", length = 100)
	public String getFtel() {
		return this.ftel;
	}

	public void setFtel(String ftel) {
		this.ftel = ftel;
	}

	@Column(name = "FWEIBO", length = 100)
	public String getFweibo() {
		return this.fweibo;
	}

	public void setFweibo(String fweibo) {
		this.fweibo = fweibo;
	}

	@Column(name = "FWEIXIN", length = 100)
	public String getFweixin() {
		return this.fweixin;
	}

	public void setFweixin(String fweixin) {
		this.fweixin = fweixin;
	}

	@Column(name = "FTEXT")
	public String getFtext() {
		return this.ftext;
	}

	public void setFtext(String ftext) {
		this.ftext = ftext;
	}
	@Column(name = "FLNG", precision = 126, scale = 0)
	public Double getFlng() {
		return this.flng;
	}

	public void setFlng(Double flng) {
		this.flng = flng;
	}

	@Column(name = "FLAT", precision = 126, scale = 0)
	public Double getFlat() {
		return this.flat;
	}

	public void setFlat(Double flat) {
		this.flat = flat;
	}
}