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
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 故障码表
 */
@Entity
@Table(name = "CN_GZM")
public class CnGzm implements java.io.Serializable {

	// Fields

	private Long id;
	private String fno;
	private long flevel;
	private String fdescribe;
	private String fgzmsug;
	private String fhelp;
	private String fsuggest;
	private String fgzmhelp;
	//private Set<CnGzdetail> cnGzdetails = new HashSet<CnGzdetail>(0);

	// Constructors

	/** default constructor */
	public CnGzm() {
	}

	/** minimal constructor */
	public CnGzm(Long id) {
		this.id = id;
	}

	/** full constructor */
	public CnGzm(Long id, String fno, long flevel, String fdescribe,
			String fgzmsug, String fhelp, String fsuggest, String fgzmhelp,
			Set<CnGzdetail> cnGzdetails) {
		this.id = id;
		this.fno = fno;
		this.flevel = flevel;
		this.fdescribe = fdescribe;
		this.fgzmsug = fgzmsug;
		this.fhelp = fhelp;
		this.fsuggest = fsuggest;
		this.fgzmhelp = fgzmhelp;
	}

	// Property accessors
	@Id
	@SequenceGenerator(name="sequenceGenerator",sequenceName="seq_CN_GZM",allocationSize=1,initialValue=1)
	@GeneratedValue(generator="sequenceGenerator",strategy=GenerationType.SEQUENCE)
	@Column(name = "ID", unique = true, nullable = false, precision = 16, scale = 0)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "FNO", length = 50)
	public String getFno() {
		return this.fno;
	}

	public void setFno(String fno) {
		this.fno = fno;
	}

	@Column(name = "FLEVEL", precision = 22, scale = 0)
	public long getFlevel() {
		return this.flevel;
	}

	public void setFlevel(long flevel) {
		this.flevel = flevel;
	}

	@Column(name = "FDESCRIBE", length = 3000)
	public String getFdescribe() {
		return this.fdescribe;
	}

	public void setFdescribe(String fdescribe) {
		this.fdescribe = fdescribe;
	}

	@Column(name = "FGZMSUG", length = 3000)
	public String getFgzmsug() {
		return this.fgzmsug;
	}

	public void setFgzmsug(String fgzmsug) {
		this.fgzmsug = fgzmsug;
	}

	@Column(name = "FHELP", length = 3000)
	public String getFhelp() {
		return this.fhelp;
	}

	public void setFhelp(String fhelp) {
		this.fhelp = fhelp;
	}

	@Column(name = "FSUGGEST", length = 3000)
	public String getFsuggest() {
		return this.fsuggest;
	}

	public void setFsuggest(String fsuggest) {
		this.fsuggest = fsuggest;
	}

	@Column(name = "FGZMHELP", length = 3000)
	public String getFgzmhelp() {
		return this.fgzmhelp;
	}

	public void setFgzmhelp(String fgzmhelp) {
		this.fgzmhelp = fgzmhelp;
	}

}