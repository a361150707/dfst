package com.ennuova.dao;


import java.util.List;
import java.util.Map;

import com.ennuova.base.DaoSupport;
import com.ennuova.entity.CnInfo;
import com.ennuova.entity.CnInfodetail;
import com.ennuova.entity.PubBox;

public interface CnInfoDao extends DaoSupport<CnInfo>{


	/**
	 * 获取用户消息中心
	 * @param cusId
	 * @return
	 */
	List<CnInfo> getCusMsg(long cusId);
	
	/**
	 * 获取模块消息详情
	 * @param fsubject
	 * @param cusId
	 * @return
	 */
	List<CnInfo> getDetailMsg(String fsubject,long cusId);
	
	/**
	 * 更新模块消息已读
	 * @param fsubject
	 * @param cusId
	 * @return
	 */
	int updateReadMsg(String fsubject,long cusId);
	
	/**
	 * 删除模块消息
	 * @param fsubject
	 * @param cusId
	 * @return
	 */
	int deleteSubjectMsg(String fsubject,long cusId);
	
	/**
	 * 删除具体消息
	 * @param detailId
	 * @return
	 */
	int deleteMsg(List<Long> ids);
	
	/**
	 * 获取用户是否存在未读消息
	 * @param cusId
	 * @return
	 */
	int getNoReadMeg(long cusId);

	/**
	 * 添加obd消息
	 * @param pubBox
	 * @param fcode
	 */
	void addMsg(PubBox pubBox, String fcode);

	/**
	 * 获取个人中心消息
	 * @param detail
	 * @return
	 * @author 伟灿
	 */
	List<CnInfo> queryUserMsg(CnInfo info,int startIndex,int stopIndex);
	/**根据用户Id获取个人信息列表
	 * @param cusId
	 * @return List<CnInfo>
	 */
	List<CnInfodetail> getMessageListNum(Long cusId);
	/**消息分页
	 * @param cusId
	 * @param messageType
	 * @param maxId
	 * @return @return List<CnInfodetail>
	 */
	List<CnInfodetail> getCnInfodetails(Long cusId,Long messageType,Long maxId);
	/**根据id列表获取消息列表
	 * @param idList
	 * @return List<CnInfo>
	 */
	List<CnInfo> getCnInfosByIdList(String idList);
	/**将消息标记为已读
	 * @param messageType
	 * @param cudId
	 * @return int更新数量
	 */
	int updateBrage(int messageType,Long cudId);
	/**添加消息
	 * @param messageContent 消息内容
	 * @param type	消息推送类型 1-单推 2-全推
	 * @param userId	用户ID，当进行单推时传用户ID，当群推时传0;
	 * @param messageType	消息类型 1-故障 2-防盗 3-驾驶 4-安全 5-车务 6-资讯 7-意见反馈 8-预约信息 9-系统消息
	 * @return boolean true 成功 false 失败
	 */
	boolean addMsg(String messageContent,int type,long userId,int messageType);
	/**根据用户Id查询该用户是否属于老卡用户
	 * @param userId
	 * @return boolean true-该用户属于老批次卡的用户 false-否
	 */
	boolean alertMessage(Long userId);
}
