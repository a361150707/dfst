package com.ennuova.app.config;


import org.hibernate.Query;

/**
 * @author jimmy(王志明)
 *2016年4月19日
 */
public enum SystemInfo implements ICodeEnum {
	//非法page请传整数
	PAGE_PARAM_ERROR(-1,"非法page请传整数"),
    ILLEGAL_PARAMETER(-1,"非法参数"),
    SELECT_NUM_OUT(-1,"每个用户每天只能查询5次,您已经超出查询次数了"),
    CARINFO_ERROR(-1,"车辆信息错误，请确认输入的信息正确"),
    QUERY_ERROR(-1,"查询失败"),
	QUERY_SUCCESS(1,"查询成功"),
	ADD_SUCCESS(1,"添加成功"),
	ADD_FAIL(-1,"添加失败"),
	FOLLOWTRIBE_ADD_SUCCESS(1,"关注成功"),
	FOLLOWTRIBE_ADD_FAIL(-1,"关注失败"),
	FOLLOWTRIBE_CANCEL_SUCCESS(1,"取消关注成功"),
	FOLLOWTRIBE_CANCEL_FAIL(-1,"取消关注失败"),
	UPDATE_SUCCESS(1,"更新成功"),
	UPDATE_FAIL(-1,"更新失败"),
	DEL_FAIL(-1,"删除失败"),
	DEL_SUCCESS(1,"删除成功"),
	QUERY_NOT_MORE(1,"没有更多"),
	QUERY_NOT_DATA(1,"无记录"),
	PASS_NOT_EQUALS(100001,"两次密码不同"),
	HANDLE_SUCCESS(1,"投诉建议处理成功"),
	HANDLE_ERROR(-1,"投诉建议处理失败"),
	IGNORE_SUCCESS(1,"投诉建议忽略成功"),
	IGNORE_ERROR(-1,"投诉建议忽略失败"),
	PLAY_SUCCESS(1,"操作成功"),
	PLAY_FAIL(-1,"操作失败,请稍后重试");
	private int code;
	private String msg;
	private SystemInfo(int code, String msg) {
		this.code = code;
		this.msg = msg;
	}
	
	@Override
	public int getCode() {
		return code;
	}

	@Override
	public String getMsg() {
		return msg;
	}

}
