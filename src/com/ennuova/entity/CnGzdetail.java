package com.ennuova.entity;

import java.math.BigDecimal;
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

import org.hibernate.annotations.GenericGenerator;

/**
 * 故障明细表
 */
@Entity
@Table(name = "CN_GZDETAIL")
public class CnGzdetail implements java.io.Serializable {

	// Fields

	private Long id;
	private CnGzrecode cnGzrecode;
	private CnGzm cnGzm;
	private String fzt;
	private String faultcode;

	// Constructors

	/** default constructor */
	public CnGzdetail() {
	}

	/** minimal constructor */
	public CnGzdetail(Long id) {
		this.id = id;
	}

	/** full constructor */
	public CnGzdetail(Long id, CnGzrecode cnGzrecode, CnGzm cnGzm,
			String fzt, String fgzm) {
		this.id = id;
		this.cnGzrecode = cnGzrecode;
		this.cnGzm = cnGzm;
		this.fzt = fzt;
	}

	// Property accessors
	@Id
	@SequenceGenerator(name="sequenceGenerator",sequenceName="seq_CN_GZDETAIL",allocationSize=1,initialValue=1)
	@GeneratedValue(generator="sequenceGenerator",strategy=GenerationType.SEQUENCE)
	@Column(name = "ID", unique = true, nullable = false, precision = 16, scale = 0)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "FGZJL")
	public CnGzrecode getCnGzrecode() {
		return this.cnGzrecode;
	}

	public void setCnGzrecode(CnGzrecode cnGzrecode) {
		this.cnGzrecode = cnGzrecode;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "FGZMID")
	public CnGzm getCnGzm() {
		return this.cnGzm;
	}

	public void setCnGzm(CnGzm cnGzm) {
		this.cnGzm = cnGzm;
	}

	@Column(name = "FZT")
	public String getFzt() {
		return this.fzt;
	}

	public void setFzt(String fzt) {
		this.fzt = fzt;
	}
	
	@Column(name = "FAULTCODE")
	public String getFaultcode() {
		return faultcode;
	}

	public void setFaultcode(String faultcode) {
		this.faultcode = faultcode;
	}

	@Override
	public String toString() {
		return "CnGzdetail [id=" + id + ", cnGzrecode=" + cnGzrecode.getId()
				+ ", cnGzm=" + cnGzm.getId() + ", fzt=" + fzt + ", faultcode="
				+ faultcode + "]";
	}



}