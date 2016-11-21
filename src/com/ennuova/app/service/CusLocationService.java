package com.ennuova.app.service;

import java.util.List;
import java.util.Map;

import com.ennuova.entity.Cuslocation;

/**
 * 客户位置的服务接口
 * @author sududa
 * @date 2016年10月18日
 *
 */
public interface CusLocationService {

	/**
	 * 获取客户的位置
	 *@author sududa
	 *@date 2016年10月18日
	 * @param cuslocation
	 * @return
	 */
	Integer doGetLocation(Cuslocation cuslocation);

	/**
	 * 根据客户id清除位置信息
	 *@author sududa
	 *@date 2016年10月19日
	 * @param cusId
	 * @return
	 */
	Integer doCleanLocation(String cusId);

	/**
	 * 附近的人列表
	 *@author sududa
	 *@date 2016年10月19日
	 * @param page 当前页(默认值为1)
	 * @param rows 每页显示的条数(默认值为10)
	 * @param interest 兴趣
	 * @param authentication 认证的车主
	 * @param loginId 当前登录人的id
	 * @param lat 自己的经度
	 * @param lng 自己的纬度
	 * @param sex 性别
	 * @return
	 */
	List<Map<String, Object>> doList(Integer page, Integer rows, String interest, String authentication, String loginId, String lng, String lat, String sex);



}
