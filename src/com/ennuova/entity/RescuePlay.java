package com.ennuova.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 * 
* @Title: RescuePlay.java 
* @Package com.ennuova.entity 
* @Description: 救援方案(描述) 
* @author felix
* @date 2016年4月21日
* @version V1.0
 */
@Entity
@Table(name = "RESCUE_PLAY")
public class RescuePlay implements java.io.Serializable {

	// Fields

	/**   
	* @Title: RescuePlay.java 
	* @Package com.ennuova.entity 
	* @Description: TODO(描述) 
	* @author felix
	* @date 2016年4月21日
	* @version V1.0   
	*/
	private static final long serialVersionUID = 1L;
	private Long rescuePlayId;//救援方案ID
	private String rescuePlayName;//名称
	private String rescuePlayAliases;//别名
	private Integer rescuePlayOrder;//排序
	private String rescuePlayRemark;//备注
	
	private List<RescueItem> rescueItems;//对象的项集合

	
	
	// Constructors

	@ManyToMany(cascade=CascadeType.ALL)
    @JoinTable(
            name="RESCUE_PLAY_ITEM",
            joinColumns=@JoinColumn(name="RESCUE_PLAY_ID"),
            inverseJoinColumns=@JoinColumn(name="RESCUE_ITEM_ID")
    )
	public List<RescueItem> getRescueItems() {
		return rescueItems;
	}

	public void setRescueItems(List<RescueItem> rescueItems) {
		this.rescueItems = rescueItems;
	}

	/** default constructor */
	public RescuePlay() {
	}

	/** minimal constructor */
	public RescuePlay(Long rescuePlayId) {
		this.rescuePlayId = rescuePlayId;
	}

	/** full constructor */
	public RescuePlay(Long rescuePlayId, String rescuePlayName,
			String rescuePlayAliases, Integer rescuePlayOrder,
			String rescuePlayRemark,List<RescueItem> rescueItems) {
		this.rescuePlayId = rescuePlayId;
		this.rescuePlayName = rescuePlayName;
		this.rescuePlayAliases = rescuePlayAliases;
		this.rescuePlayOrder = rescuePlayOrder;
		this.rescuePlayRemark = rescuePlayRemark;
		this.rescueItems = rescueItems;
	}

	// Property accessors
	@Id
	@Column(name = "RESCUE_PLAY_ID", unique = true, nullable = false, precision = 16, scale = 0)
	public Long getRescuePlayId() {
		return this.rescuePlayId;
	}

	public void setRescuePlayId(Long rescuePlayId) {
		this.rescuePlayId = rescuePlayId;
	}

	@Column(name = "RESCUE_PLAY_NAME", length = 50)
	public String getRescuePlayName() {
		return this.rescuePlayName;
	}

	public void setRescuePlayName(String rescuePlayName) {
		this.rescuePlayName = rescuePlayName;
	}

	@Column(name = "RESCUE_PLAY_ALIASES", length = 50)
	public String getRescuePlayAliases() {
		return this.rescuePlayAliases;
	}

	public void setRescuePlayAliases(String rescuePlayAliases) {
		this.rescuePlayAliases = rescuePlayAliases;
	}

	@Column(name = "RESCUE_PLAY_ORDER", precision = 22, scale = 0)
	public Integer getRescuePlayOrder() {
		return this.rescuePlayOrder;
	}

	public void setRescuePlayOrder(Integer rescuePlayOrder) {
		this.rescuePlayOrder = rescuePlayOrder;
	}

	@Column(name = "RESCUE_PLAY_REMARK", length = 200)
	public String getRescuePlayRemark() {
		return this.rescuePlayRemark;
	}

	public void setRescuePlayRemark(String rescuePlayRemark) {
		this.rescuePlayRemark = rescuePlayRemark;
	}

}