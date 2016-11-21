package com.ennuova.vo;

import java.util.List;

import com.ennuova.entity.TSBaseUser;

public class ReserveMaintainPlanVO {
	/**服务顾问信息 */
	private TSBaseUser tsBaseUser;
	/**里程数  */
	private String f40;
	/** 保养方案名称 */
	private int maintainplayName;
	/** 保养方案ID */
	private Long maintainPlayId;
	/**项目信息 */
	private List<ReserveMaintainItemVO> reserveMaintainItemVOs;
	public TSBaseUser getTsBaseUser() {
		return tsBaseUser;
	}
	
	public void setTsBaseUser(TSBaseUser tsBaseUser) {
		this.tsBaseUser = tsBaseUser;
	}

	public String getF40() {
		return f40;
	}

	public void setF40(String f40) {
		this.f40 = f40;
	}


	public long getMaintainPlayId() {
		return maintainPlayId;
	}

	public void setMaintainPlayId(Long maintainPlayId) {
		this.maintainPlayId = maintainPlayId;
	}

	public int getMaintainplayName() {
		return maintainplayName;
	}

	public ReserveMaintainPlanVO() {
		super();
	}

	public ReserveMaintainPlanVO(TSBaseUser tsBaseUser, Long maintainPlayId, int maintainplayName,
			String f40, List<ReserveMaintainItemVO> reserveMaintainItemVOs) {
		this.tsBaseUser=tsBaseUser;
		this.maintainPlayId = maintainPlayId;
		this.maintainplayName = maintainplayName;
		this.reserveMaintainItemVOs = reserveMaintainItemVOs;
		this.f40 = f40;
	}

	public void setMaintainplayName(int maintainplayName) {
		this.maintainplayName = maintainplayName;
	}

	public List<ReserveMaintainItemVO> getReserveMaintainItemVOs() {
		return reserveMaintainItemVOs;
	}

	public void setReserveMaintainItemVOs(
			List<ReserveMaintainItemVO> reserveMaintainItemVOs) {
		this.reserveMaintainItemVOs = reserveMaintainItemVOs;
	}
	
}
