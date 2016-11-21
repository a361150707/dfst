package com.ennuova.dao;

import java.util.List;
import java.util.Map;

import com.ennuova.base.DaoSupport;
import com.ennuova.entity.CnMsgInfoPer;

/**消息Dao
 * @author lee
 * @time 2016-9-25 下午9:00:16
 */
public interface CnMsgInfoPerDao extends DaoSupport<CnMsgInfoPer>{
	/**根据接收人Id和接收人类型获取消息列表
	 * @param receiveId
	 * @param rType
	 * @param startIndex
	 * @param stopIndex
	 * @return
	 */
	List<Map<String, Object>> getMsgList(String receiveId,int rType);
	/**根据接收人Id和接收人类型,发送人Id获取消息详情列表
	 * @param receiveId
	 * @param rType
	 * @param sender
	 * @param startIndex
	 * @param stopIndex
	 * @return List<Map<String, Object>>
	 */
	List<Map<String, Object>> getMsgDetailList(String receiveId,int rType,String sender,Long startIndex,Long stopIndex);
	/**根据就收人Id和发送人Id设置已读未读
	 * @param receiveId
	 * @param sender
	 * @return int设置数量
	 */
	int setReader(String receiveId,String sender);
	/**根据接收人Id和接收人类型获取消息列表角标
	 * @param receiveId
	 * @return Map<String, Long>
	 */
	Map<String, Long> getBage(String receiveId);
	
	/**根据发送人和接收人删除消息
	 * @param receiveId
	 * @param sender
	 * @return int 删除数量
	 */
	int deleteMsgByStaffIdAndCusId(String receiveId,String sender);
}
