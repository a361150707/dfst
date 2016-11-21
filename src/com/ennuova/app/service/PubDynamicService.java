package com.ennuova.app.service;

import java.util.List;
import java.util.Map;

import com.ennuova.entity.ClickGoodEntity;
import com.ennuova.entity.PubDynamic;

/**
 * 动态和话题的服务接口
 * @author sududa
 * @date 2016年10月19日
 *
 */
public interface PubDynamicService {

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
	Integer doDelete(String delfLag, String id, String sendId);

	/**
	 * 附近的人详情，即查询该人的动态及有车绑定盒子的里程数和油耗
	 *@author sududa
	 *@date 2016年10月25日
	 * @param loginId 登录人的id
	 * @param id 附近人的列表的主键id
	 * @param page 当前页
	 * @param rows 每页显示的条数
	 * @return
	 */
	Map<String, Object> doNearbyDetail(String loginId, String id, Integer page, Integer rows);


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
	 * @author 陈晓珊
	 * @date 2016年10月27日
	 * @param title
	 * @param page
	 * @param rows
	 * @return
	 */
	List<Map<String, Object>> doTopicList(String title,Integer page, Integer rows);

	/**
	 * 查询个人的动态列表
	 * @param page
	 * @param rows
	 * @param loginId
	 * @return
	 */
	List<Map<String, Object>> doPersonalList(Integer page, Integer rows, String loginId);
	
	/**
	 * 查询话题详情列表
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
	 * 查询个人的盒子激活时间,累计里程，累计油耗
	 * @param loginId
	 * @return
	 */
	Map<String, Object> doPersonalOne(String loginId);

	/**
	 * 个人动态详情
	 * @author 陈晓珊
	 * @date 2016年11月1日
	 * @param loginId
	 * @param id
	 * @return
	 */
	Map<String, Object> doDyDetilList(String loginId,String id);

	/**
	 * 点赞
	 * @author 陈晓珊
	 * @date 2016年11月1日
	 * @param clickGood
	 * @return
	 */
	ClickGoodEntity doClickGood(ClickGoodEntity clickGood);

	/**
	 * 取消点赞
	 * @author 陈晓珊
	 * @date 2016年11月1日
	 * @param fromId
	 * @param toId
	 * @param goodType
	 * @return
	 */
	Integer cancelClickGood(String fromId, String toId, String goodType);

	/**
	 * 根据登陆ID，查找是否点赞过某条动态
	 * @author 陈晓珊
	 * @date 2016年10月26日
	 * @param id
	 * @return
	 */
	Map<String, Object> doIsClickGood(String toId, String fromId,
			String goodType);


}
