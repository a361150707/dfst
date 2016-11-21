package com.ennuova.app.service;

import java.util.List;
import java.util.Map;

import com.ennuova.entity.PubContacts;

/**
 * 联系人的服务接口
 * @author sududa
 * @date 2016年10月17日
 *
 */
public interface PubContactsService {
	
	

	/**
	 * 关注,取消关注,拉黑
	 *@author sududa
	 *@date 2016年10月18日
	 * @param pubContacts
	 * @return
	 */
	Integer doFollowAndUnFollow(PubContacts pubContacts);

	/**
	 * 关注的人列表
	 * @author 陈晓珊
	 * @date 2016年10月21日
	 * @param loginId
	 * @return
	 */
	List<Map<String, Object>> doFollowList(String loginId);

	/**
	 * 粉丝列表
	 * @author 陈晓珊
	 * @date 2016年10月21日
	 * @param loginId
	 * @return
	 */
	List<Map<String, Object>> doFansList(String loginId);

	/**
	 * 黑名单列表
	 * @author 陈晓珊
	 * @date 2016年10月21日
	 * @param loginId
	 * @return
	 */
	List<Map<String, Object>> doBlackList(String loginId);

	/**
	 * 联系人列表列表
	 * @author 陈晓珊
	 * @date 2016年10月21日
	 * @param loginId
	 * @return
	 */
	List<Map<String, Object>> doContactsList(String loginId);

	/**
	 * 车友数
	 * @author 陈晓珊
	 * @date 2016年10月21日
	 * @param loginId
	 * @return
	 */
	Map<String, Object> doRidersNum(String loginId);

	/**
	 * 联系人详情
	 * @author 陈晓珊
	 * @date 2016年10月21日
	 * @param id
	 * @return
	 */
	Map<String, Object> doContactDetail(String id);

	/**
	 * 关注的人ID列表
	 * @author 陈晓珊
	 * @date 2016年10月31日
	 * @param loginId
	 * @return
	 */
	List<Map<String, Object>> queryFollowList(String loginId);



}
