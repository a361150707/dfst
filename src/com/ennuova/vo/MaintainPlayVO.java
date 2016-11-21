package com.ennuova.vo;

import java.math.BigDecimal;

public class MaintainPlayVO{
	private BigDecimal maintainplayid;
	private BigDecimal maintainplaymilagenum;
	private BigDecimal km;
	private BigDecimal rownum_;
	public BigDecimal getMaintainplayid() {
		return maintainplayid;
	}
	public BigDecimal getMaintainplaymilagenum() {
		return maintainplaymilagenum;
	}
	public void setMaintainplaymilagenum(BigDecimal maintainplaymilagenum) {
		this.maintainplaymilagenum = maintainplaymilagenum;
	}
	public BigDecimal getKm() {
		return km;
	}
	public BigDecimal getRownum_() {
		return rownum_;
	}
	public void setRownum_(BigDecimal rownum_) {
		this.rownum_ = rownum_;
	}
	public void setKm(BigDecimal km) {
		this.km = km;
	}
	public void setMaintainplayid(BigDecimal maintainplayid) {
		this.maintainplayid = maintainplayid;
	}

}