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

import org.hibernate.annotations.GenericGenerator;

/**
 * PubOdbbrand entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "PUB_ODBBRAND")
public class PubOdbbrand implements java.io.Serializable {

	// Fields

	private Long id;
	private PubBrand pubBrand;
	private Long parentId;
	private String carSeriesName;
	private String url;
	private Long carSeriesId;

	// Constructors

	/** default constructor */
	public PubOdbbrand() {
	}

	/** minimal constructor */
	public PubOdbbrand(Long id) {
		this.id = id;
	}

	/** full constructor */
	public PubOdbbrand(Long id, PubBrand pubBrand, Long parentId,
			String carSeriesName, String url, Long carSeriesId) {
		this.id = id;
		this.pubBrand = pubBrand;
		this.parentId = parentId;
		this.carSeriesName = carSeriesName;
		this.url = url;
		this.carSeriesId = carSeriesId;
	}

	// Property accessors
	@Id
	@SequenceGenerator(name="sequenceGenerator",sequenceName="seq_PUB_ODBBRAND",allocationSize=1,initialValue=1)
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

	@Column(name = "PARENTID", precision = 16, scale = 0)
	public Long getParentId() {
		return this.parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	@Column(name = "CARSERIESNAME", length = 100)
	public String getCarSeriesName() {
		return this.carSeriesName;
	}

	public void setCarSeriesName(String carSeriesName) {
		this.carSeriesName = carSeriesName;
	}

	@Column(name = "URL", length = 100)
	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Column(name = "CARSERIESID", precision = 16, scale = 0)
	public Long getCarSeriesId() {
		return this.carSeriesId;
	}

	public void setCarSeriesId(Long carSeriesId) {
		this.carSeriesId = carSeriesId;
	}

}