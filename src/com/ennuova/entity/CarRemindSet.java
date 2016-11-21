package com.ennuova.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**   
* @Title: CarRemindSet.java 
* @Package com.ennuova.entity 
* @Description: 车务提醒设置(描述) 
* @author felix
* @date 2016年4月19日
* @version V1.0   
*/
@Entity
@Table(name = "CAR_REMIND_SET")
public class CarRemindSet implements java.io.Serializable {

	// Fields

	
	private static final long serialVersionUID = 1L;
	private Long carRemindSetId;//id
	private Long customerId;//用户id
	private Long carinfoId;//车辆id
	private float maintainMileage;//保养里程（公里）
	private Integer maintainDate;//保养时间
	private Integer insuranceDate;//保险到期
	private Integer inspectorDate;//年检到期
	private Integer warrantyDate;//保修到期
	private String delFlag;//删除标致
	// Constructors

	/** default constructor */
	public CarRemindSet() {
	}

	/** minimal constructor */
	public CarRemindSet(Long carRemindSetId) {
		this.carRemindSetId = carRemindSetId;
	}

	/** full constructor */
	public CarRemindSet(Long carRemindSetId, Long customerId, Long carinfoId,
			Integer maintainMileage, Integer maintainDate,
			Integer insuranceDate, Integer inspectorDate,
			Integer warrantyDate, String delFlag) {
		this.carRemindSetId = carRemindSetId;
		this.customerId = customerId;
		this.carinfoId = carinfoId;
		this.maintainMileage = maintainMileage;
		this.maintainDate = maintainDate;
		this.insuranceDate = insuranceDate;
		this.inspectorDate = inspectorDate;
		this.warrantyDate = warrantyDate;
		this.delFlag = delFlag;
	}

	// Property accessors
	@Id
	@SequenceGenerator(name="sequenceGenerator",sequenceName="seq_CAR_REMIND_SET",allocationSize=1,initialValue=1)
	@GeneratedValue(generator="sequenceGenerator",strategy=GenerationType.SEQUENCE)
	@Column(name = "CAR_REMIND_SET_ID", unique = true, nullable = false, precision = 16, scale = 0)
	public Long getCarRemindSetId() {
		return this.carRemindSetId;
	}

	public void setCarRemindSetId(Long carRemindSetId) {
		this.carRemindSetId = carRemindSetId;
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

	@Column(name = "MAINTAIN_MILEAGE", precision = 22, scale = 0)
	public Float getMaintainMileage() {
		return this.maintainMileage;
	}

	public void setMaintainMileage(Float maintainMileage) {
		this.maintainMileage = maintainMileage;
	}

	@Column(name = "MAINTAIN_DATE", precision = 22, scale = 0)
	public Integer getMaintainDate() {
		return this.maintainDate;
	}

	public void setMaintainDate(Integer maintainDate) {
		this.maintainDate = maintainDate;
	}

	@Column(name = "INSURANCE_DATE", precision = 22, scale = 0)
	public Integer getInsuranceDate() {
		return this.insuranceDate;
	}

	public void setInsuranceDate(Integer insuranceDate) {
		this.insuranceDate = insuranceDate;
	}

	@Column(name = "INSPECTOR_DATE", precision = 22, scale = 0)
	public Integer getInspectorDate() {
		return this.inspectorDate;
	}

	public void setInspectorDate(Integer inspectorDate) {
		this.inspectorDate = inspectorDate;
	}

	@Column(name = "WARRANTY_DATE", precision = 22, scale = 0)
	public Integer getWarrantyDate() {
		return this.warrantyDate;
	}

	public void setWarrantyDate(Integer warrantyDate) {
		this.warrantyDate = warrantyDate;
	}

	@Column(name = "DEL_FLAG", length = 1)
	public String getDelFlag() {
		return this.delFlag;
	}

	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}


}