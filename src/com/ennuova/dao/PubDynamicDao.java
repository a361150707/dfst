package com.ennuova.dao;

import java.util.List;
import java.util.Map;

import com.ennuova.base.BaseDAO;
import com.ennuova.entity.PubDynamic;

/**
 * 动态和话题dao
 * @author sududa
 * @date 2016年10月19日
 *
 */
public interface PubDynamicDao extends BaseDAO<PubDynamic> {

	/**
	 * 发布动态
	 *@author sududa
	 *@date 2016年10月19日
	 * @param pubDynamic
	 * @return
	 */
	PubDynamic doSendDynamic(PubDynamic pubDynamic);

	/**
	 * 删除动态
	 *@author sududa
	 *@date 2016年10月19日
	 * @param delfLag 
	 * @param id
	 * @param sendId
	 * @return
	 */
	Integer doDelete(String delfLag,String id, String sendId);

	/**
	 * 根据发送人的id查找动态列表
	 *@author sududa
	 *@date 2016年10月19日
	 * @param id
	 * @return
	 */
	List<Map<String, Object>> findBySendId(String id);

	/**
	 * 分页查询动态的时间列表
	 *@author sududa
	 *@date 2016年10月24日
	 * @param id
	 * @param page
	 * @param rows
	 * @return
	 */
	List<Map<String, Object>> findTimeListById(String id, Integer page, Integer rows);

	/**
	 * 根据id和发送动态的日期查询动态的列表
	 *@author sududa
	 *@date 2016年10月25日
	 * @param id
	 * @param sendDate
	 * @return
	 */
	List<Map<String, Object>> findBySendIdAndSendDate(String id, String sendDate);


	/**
	 * 根据登录的id查询自己,自己的好友,自己关注的,员工发送的动态列表,带分页
	 * @param page
	 * @param rows
	 * @param loginId
	 * @return
	 */
	List<Map<String, Object>> findByIdWithPage(Integer page, Integer rows, String loginId);
	
	
	/**
	 * 查询话题列表
	 *@author 陈晓珊
	 *@date 2016年10月27日
	 * @param title
	 * @param page
	 * @param rows
	 * @return
	 */
	List<Map<String, Object>> doTopicList(String title,Integer page, Integer rows);

	/**
	 * 根据话题id,查询话题详情列表
	 *@author 陈晓珊
	 *@date 2016年10月28日
	 * @param page
	 * @param rows
	 * @param loginId
	 * @return
	 */
	List<Map<String, Object>> doTopicDetilList(String loginId,String id, Integer page,
			Integer rows);

	/**
	 *根据动态ID，获取个人动态详情
	 *@author 陈晓珊
	 *@date 2016年11月1日
	 * @param params
	 * @param response
	 * @throws Exception
	 */
	Map<String, Object> doDyDetilList(String loginId,String id);

	
}
