package com.ennuova.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
* @Title: RescueItem.java 
* @Package com.ennuova.entity 
* @Description: 救援项目(描述) 
* @author felix
* @date 2016年4月21日
* @version V1.0
 */
@Entity
@Table(name = "RESCUE_ITEM")
public class RescueItem implements java.io.Serializable {

	// Fields

	/**   
	* @Title: RescueItem.java 
	* @Package com.ennuova.entity 
	* @Description: TODO(描述) 
	* @author felix
	* @date 2016年4月21日
	* @version V1.0   
	*/
	private static final long serialVersionUID = 1L;
	private Long rescueItemId;//救援项目ID
	private String rescueItemName;//名称
	private String rescueItemRemark;//备注
	
	
	/*private List<ReserveRescue> reserveRescues;
	
	private List<RescuePlay> rescuePlays;

	
	
	
	// Constructors
    
	@ManyToMany(mappedBy="rescueItemes",cascade=CascadeType.ALL)
	public List<ReserveRescue> getReserveRescues() {
		return reserveRescues;
	}

	public void setReserveRescues(List<ReserveRescue> reserveRescues) {
		this.reserveRescues = reserveRescues;
	}

	@ManyToMany(mappedBy="rescueItems",cascade=CascadeType.ALL)
	public List<RescuePlay> getRescuePlays() {
		return rescuePlays;
	}

	public void setRescuePlays(List<RescuePlay> rescuePlays) {
		this.rescuePlays = rescuePlays;
	}*/

	/** default constructor */
	public RescueItem() {
	}

	/** minimal constructor */
	public RescueItem(Long rescueItemId) {
		this.rescueItemId = rescueItemId;
	}

	/** full constructor */
	public RescueItem(Long rescueItemId, String rescueItemName,
			String rescueItemRemark) {
		this.rescueItemId = rescueItemId;
		this.rescueItemName = rescueItemName;
		this.rescueItemRemark = rescueItemRemark;
	}

	// Property accessors
	@Id
	@Column(name = "RESCUE_ITEM_ID", unique = true, nullable = false, precision = 16, scale = 0)
	public Long getRescueItemId() {
		return this.rescueItemId;
	}

	public void setRescueItemId(Long rescueItemId) {
		this.rescueItemId = rescueItemId;
	}

	@Column(name = "RESCUE_ITEM_NAME", length = 50)
	public String getRescueItemName() {
		return this.rescueItemName;
	}

	public void setRescueItemName(String rescueItemName) {
		this.rescueItemName = rescueItemName;
	}

	@Column(name = "RESCUE_ITEM_REMARK", length = 200)
	public String getRescueItemRemark() {
		return this.rescueItemRemark;
	}

	public void setRescueItemRemark(String rescueItemRemark) {
		this.rescueItemRemark = rescueItemRemark;
	}

}