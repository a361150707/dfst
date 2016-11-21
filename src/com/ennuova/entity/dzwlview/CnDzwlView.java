package com.ennuova.entity.dzwlview;

/**电子围栏推送Model
 * @author 李智辉 
 * 2015-12-17下午2:24:36
 */
public class CnDzwlView {
	private Long fclid; //车辆Id
	private String message; //推送消息
	private String alias;//别名
	
	public Long getFclid() {
		return fclid;
	}
	public void setFclid(Long fclid) {
		this.fclid = fclid;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getAlias() {
		return alias;
	}
	public void setAlias(String alias) {
		this.alias = alias;
	}
}
