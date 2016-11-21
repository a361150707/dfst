package com.ennuova.app.config;

import java.util.List;
/**
 * @author jimmy(王志明)
 *2016年4月19日
 */
public class AppObjectResult extends AppResult {
	private Object data;
	public AppObjectResult(int code, String msg) {
		super(code, msg);
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public AppObjectResult(int code, String msg, Object data) {
		super(code, msg);
		this.data = data;
	}
	public AppObjectResult() {
	}
}
