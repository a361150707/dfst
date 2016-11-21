package com.ennuova.app.config;

/**
 * @author jimmy(王志明)
 *2016年4月19日
 */
public class AppResult {
	private int code;
	private String msg;
	public AppResult(){
	}
	/**
	 * @param code
	 * @param msg
	 */
	public AppResult(int code, String msg) {
		super();
		this.code = code;
		this.msg = msg;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public void setResult(int code,String msg){
		setCode(code);
		setMsg(msg);
	}
}
