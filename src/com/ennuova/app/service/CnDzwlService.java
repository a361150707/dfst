package com.ennuova.app.service;

import java.util.List;

import com.ennuova.entity.CnDzwl;

/**电子围栏service
 * @author 李智辉 
 * 2015-12-15下午3:57:33
 */
public interface CnDzwlService {
	/**添加一条电子围栏信息
	 * @param cnDzwl
	 * @return boolean true-成功 flase-失败
	 */
	boolean addDzwl(CnDzwl cnDzwl);
	/**根据车辆Id和围栏id设置电子围栏
	 * @param fclid
	 * @param id
	 * @return boolean true-成功 flase-失败
	 */
	boolean setDzwl(Long fclid,Long id,int fsrcs);
	/**根据车辆Id获取车辆下的所有围栏信息
	 * @param fclid
	 * @return List<CnDzwl>
	 */
	List<CnDzwl> getCnDzwlList(Long fclid);
	/**根据主键Id删除围栏
	 * @param fid
	 * @return boolean true-成功 false-失败
	 */
	boolean deleteFence(Long fid);
	/**根据主键Id取消围栏
	 * @param fid
	 * @return boolean true-成功 false-失败
	 */
	boolean cancelFence(Long fid);
	/**
	 * 监听电子围栏消息
	 */
	void listenAndPushDzwl();
	/**
	 * 监听obd消息
	 */
	void listenAndPushOdbMessage();
}
