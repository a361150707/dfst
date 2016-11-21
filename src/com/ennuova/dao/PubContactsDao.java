package com.ennuova.dao;

import java.util.List;
import java.util.Map;

import com.ennuova.base.BaseDAO;
import com.ennuova.entity.PubContacts;

/**
 * 联系人dao接口
 * @author sududa
 * @date 2016年10月17日
 *
 */
public interface PubContactsDao extends BaseDAO<PubContacts> {

	

	/**
	 * 根据当前登录的id,类型,联系人的id,类型查找联系人
	 *@author sududa
	 *@date 2016年10月18日
	 * @param loginId
	 * @param loginType
	 * @param contactsId
	 * @param contactsType
	 * @return 如果查找到就说明已经关注或者拉黑了，以防重复插入和拉黑
	 */
	Map<String, Object> findByLoginIdTypeAndContacsIdType(String loginId, String loginType, String contactsId,
			String contactsType);

	/**
	 * 修改关注和拉黑的状态
	 *@author sududa
	 *@date 2016年10月18日
	 * @param relation 
	 * @param loginId
	 * @param loginType
	 * @param contactsId
	 * @param contactsType
	 * @return 修改成功返回 > 0
	 */
	Integer updateByLoginIdTypeAndContacsIdType(String relation, String loginId, String loginType, String contactsId,
			String contactsType);

	/**
	 * 根据当前登录的id,类型,联系人的id,类型删除联系人
	 * @author sududa
	 * @date 2016年10月18日
	 * @param loginId
	 * @param loginType
	 * @param contactsId
	 * @param contactsType
	 * @return 删除
	 */
	Integer deleteByLoginIdTypeAndContacsIdType(String loginId, String loginType, String contactsId,
			String contactsType);

	/**
	 * 更加登录的id和登录的类型查找登录人关注的列表
	 *@author sududa
	 *@date 2016年10月21日
	 * @param loginId 登录人的id
	 * @param loginType 登录人的类型
	 * @return 该登录人的关注列表
	 */
	List<Map<String, Object>> findFollowByLoginIdAndType(String loginId, String loginType);

	/**
	 * 根据当前登录的id，关注的人列表
	 * @author 陈晓珊
	 * @date 2016年10月21日
	 * @param loginId
	 * @return
	 */
	List<Map<String, Object>> doFollowList(String loginId);

	/**
	 * 根据当前登录的id，粉丝列表
	 * @author 陈晓珊
	 * @date 2016年10月21日
	 * @param loginId
	 * @return
	 */
	List<Map<String, Object>> doFansList(String loginId);

	/**
	 * 根据当前登录的id，黑名单列表
	 * @author 陈晓珊
	 * @date 2016年10月21日
	 * @param loginId
	 * @return
	 */
	List<Map<String, Object>> doBlackList(String loginId);

	/**
	 * 根据当前登录的id，联系人列表
	 * @author 陈晓珊
	 * @date 2016年10月21日
	 * @param loginId
	 * @return
	 */
	List<Map<String, Object>> doContactsList(String loginId);

	/**
	 * 根据当前登录的id,统计关注的人的数目
	 * @author 陈晓珊
	 * @date 2016年10月21日
	 * @param loginId
	 * @return
	 */
	Map<String, Object> doFollowNum(String loginId);

	/**
	 * 根据当前登录的id,统计粉丝的数目
	 * @author 陈晓珊
	 * @date 2016年10月21日
	 * @param loginId
	 * @return
	 */
	Map<String, Object> doFansNum(String loginId);

	/**
	 * 根据当前登录的id,统计黑名单的人数
	 * @author 陈晓珊
	 * @date 2016年10月21日
	 * @param loginId
	 * @return
	 */
	Map<String, Object> doBlacklistNum(String loginId);

	/**
	 * 根据当前登录的id,统计联系人的人数
	 * @author 陈晓珊
	 * @date 2016年10月21日
	 * @param loginId
	 * @return
	 */
	Map<String, Object> doContactsNum(String loginId);

	/**
	 * 根据当前登录的id,查找我关注的人的ID
	 * @author 陈晓珊
	 * @date 2016年10月21日
	 * @param loginId
	 * @return
	 */
	List<Map<String, Object>> queryFollowList(String loginId);

	/**
	 * 根据登录人id,登录人类型,附近人的id,附近人的类型,两者之间的关系查找是否有关注或拉黑
	 *@author sududa
	 *@date 2016年10月25日
	 * @param loginId
	 * @param loginType
	 * @param contactsId
	 * @param contactsType
	 * @param relation
	 * @return
	 */
	Map<String, Object> findByLoginIdTypeAndContacsIdTypeRelation(String loginId, String loginType, String contactsId,
			String contactsType, String relation);

	/**
	 * 根据联系人ID，获取联系人详情
	 * @author 陈晓珊
	 * @date 2016年10月25日
	 * @param id
	 * @return
	 */
	Map<String, Object> doContactDetail(String id);

	
	
	
}
