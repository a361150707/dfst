package com.ennuova.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * ReserveRescue entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "RESERVE_RESCUE")
public class ReserveRescue implements java.io.Serializable {

	/**   
	* @Title: ReserveRescue.java 
	* @Package com.ennuova.entity 
	* @Description: TODO(描述) 
	* @author felix
	* @date 2016年4月19日
	* @version V1.0   
	*/
	private static final long serialVersionUID = 1L;
	// Fields

	private Long reserveRescueId;//预约救援ID
	//private Long rescuePlayId;//救援方案ID
	private RescuePlay rescuePlay;//救援方案
	private String reserveState;//预约状态
	private Long customerId;//用户ID
	private Long carinfoId;//车辆ID
	private Double longitude;//经度
	private Double latitude;//纬度
	private String address;//地址信息
	private String reserveRescueContent;//内容
	private String reserveRescueRemark;//备注
	private String fcarnum;//车牌号
	private String storeId;//用户对应的门店id
	private String carName;//车型
	private Date createDate;//创建时间
	
	
	private List<RescueItem> rescueItems;
	
	// Constructors
	
	@ManyToMany(cascade=CascadeType.ALL)
    @JoinTable(
    		name="RESERVE_RESCUE_ITEM",
    		joinColumns=@JoinColumn(name="RESERVE_RESCUE_ID"),
    		inverseJoinColumns=@JoinColumn(name="RESCUE_ITEM_ID")
    )
	public List<RescueItem> getRescueItems() {
		return rescueItems;
	}
	
	public void setRescueItems(List<RescueItem> rescueItems) {
		this.rescueItems = rescueItems;
	}
	
	@Column(name="FCARNUM",length=20)
	public String getFcarnum() {
		return fcarnum;
	}

	public void setFcarnum(String fcarnum) {
		this.fcarnum = fcarnum;
	}

	/** default constructor */
	public ReserveRescue() {
	}

	/** minimal constructor */
	public ReserveRescue(Long reserveRescueId) {
		this.reserveRescueId = reserveRescueId;
	}

	/** full constructor */
	public ReserveRescue(Long reserveRescueId, Long rescuePlayId,
			String reserveState, Long customerId, Long carinfoId,
			Double longitude, Double latitude, String reserveRescueContent,
			String reserveRescueRemark, String fcarnum) {
		this.reserveRescueId = reserveRescueId;
		//this.rescuePlayId = rescuePlayId;
		this.reserveState = reserveState;
		this.customerId = customerId;
		this.carinfoId = carinfoId;
		this.longitude = longitude;
		this.latitude = latitude;
		this.reserveRescueContent = reserveRescueContent;
		this.reserveRescueRemark = reserveRescueRemark;
		this.fcarnum = fcarnum;
	}

	// Property accessors
	@Id
	@SequenceGenerator(name="sequenceGenerator",sequenceName="seq_RESERVE_RESCUE",allocationSize=1,initialValue=1)
	@GeneratedValue(generator="sequenceGenerator",strategy=GenerationType.SEQUENCE)
	@Column(name = "RESERVE_RESCUE_ID", unique = true, nullable = false, precision = 16, scale = 0)
	public Long getReserveRescueId() {
		return this.reserveRescueId;
	}

	public void setReserveRescueId(Long reserveRescueId) {
		this.reserveRescueId = reserveRescueId;
	}
	
	

	/*@Column(name = "RESCUE_PLAY_ID", precision = 16, scale = 0)
	public Long getRescuePlayId() {
		return this.rescuePlayId;
	}

	public void setRescuePlayId(Long rescuePlayId) {
		this.rescuePlayId = rescuePlayId;
	}*/

	@OneToOne
	@JoinColumn(name="RESCUE_PLAY_ID",referencedColumnName="RESCUE_PLAY_ID")
	public RescuePlay getRescuePlay() {
		return rescuePlay;
	}

	public void setRescuePlay(RescuePlay rescuePlay) {
		this.rescuePlay = rescuePlay;
	}

	@Column(name = "RESERVE_STATE", length = 50)
	public String getReserveState() {
		return this.reserveState;
	}

	public void setReserveState(String reserveState) {
		this.reserveState = reserveState;
	}

	@Column(name = "CUSTOMER_ID", precision = 16, scale = 0)
	public Long getCustomerId() {
		return this.customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	@Column(name = "CARINFO_ID", precision = 16, scale = 0)
	public Long getCarinfoId() {
		return this.carinfoId;
	}

	public void setCarinfoId(Long carinfoId) {
		this.carinfoId = carinfoId;
	}

	@Column(name = "LONGITUDE", precision = 126, scale = 0)
	public Double getLongitude() {
		return this.longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	@Column(name = "LATITUDE", precision = 126, scale = 0)
	public Double getLatitude() {
		return this.latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}
	@Column(name = "ADDRESS", length = 50)
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "RESERVE_RESCUE_CONTENT", length = 500)
	public String getReserveRescueContent() {
		return this.reserveRescueContent;
	}

	public void setReserveRescueContent(String reserveRescueContent) {
		this.reserveRescueContent = reserveRescueContent;
	}

	@Column(name = "RESERVE_RESCUE_REMARK", length = 500)
	public String getReserveRescueRemark() {
		return this.reserveRescueRemark;
	}

	public void setReserveRescueRemark(String reserveRescueRemark) {
		this.reserveRescueRemark = reserveRescueRemark;
	}
	@Column(name = "STORE_ID", length = 50)
	public String getStoreId() {
		return storeId;
	}

	public void setStoreId(String sroreId) {
		this.storeId = sroreId;
	}
	@Column(name = "CAR_NAME", length = 50)
	public String getCarName() {
		return carName;
	}

	public void setCarName(String carName) {
		this.carName = carName;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATE_DATE", length = 15)
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
}