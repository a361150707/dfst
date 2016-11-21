package com.ennuova.util;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class ReturnObject implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5540386714175326096L;
	
	private int returnCode=0;
	private String msg;
	private Map<String, Object> args;
	private Object object;
	
	public ReturnObject(){
		super();
	}
	public int getReturnCode() {
		return returnCode;
	}
	
	public void setReturnCode(int returnCode) {
		this.returnCode = returnCode;
	}
	
	public String getMsg() {
		return msg;
	}
	
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	public Map<String, Object> getArgs() {
		if (args==null){
			args = new HashMap<String, Object>();
		}
		return args;
	}
	
	public Object getObject() {
		return object;
	}
	
	public void setObject(Object object) {
		this.object = object;
	}
	
	
}
