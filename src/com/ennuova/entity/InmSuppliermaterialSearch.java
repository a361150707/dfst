package com.ennuova.entity;

import org.apache.solr.client.solrj.beans.Field;



/**
 * 配件中心搜索结果
 */
public class InmSuppliermaterialSearch {

	@Field
	private String id;
	@Field
	private String fname;
	@Field
	private String fphoto;
	@Field
	private String frefprice;
	@Field
	private String sname;
	
	
	

	public void setId(String id) {
		this.id = id;
	}
	public String getId() {
		return id;
	}

	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getFphoto() {
		return fphoto;
	}
	public void setFphoto(String fphoto) {
		this.fphoto = fphoto;
	}
	
	public String getFrefprice() {
		return frefprice;
	}
	public void setFrefprice(String frefprice) {
		this.frefprice = frefprice;
	}
	public String getSname() {
		return sname;
	}
	public void setSname(String sname) {
		this.sname = sname;
	}
	@Override
	public String toString() {
		return "InmSuppliermaterialSearch [id=" + id + ", fname=" + fname
				+ ", fphoto=" + fphoto + ", frefprice=" + frefprice
				+ ", sname=" + sname + "]";
	}
	
	
	
}
