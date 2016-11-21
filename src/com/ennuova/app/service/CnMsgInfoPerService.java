package com.ennuova.app.service;

import java.util.List;
import java.util.Map;

import com.ennuova.entity.CnMsgInfoPer;

/**消息服务
 * @author lee
 * @time 2016-9-25 下午9:12:02
 */
public interface CnMsgInfoPerService {
	/**根据消息主键ID删除单条消息
	 * @param id
	 * @return 
	 */
	boolean deleteMsg(Long id);
	/**根据接收人Id和接收人类型获取消息列表
	 * @param receiveId
	 * @param rType
	 * @param pageNum
	 * @param pagesize
	 * @return
	 */
	List<Map<String, Object>> getMsgList(String receiveId,int rType);
	/**根据接收人ID和发送人ID接收人类型获取消息列表
	 * @param receiveId
	 * @param rType
	 * @param sender
	 * @param startIndex
	 * @param stopIndex
	 * @return
	 */
	List<Map<String, Object>> getMsgDetailList(String receiveId,
			int rType, String sender,Long pageNum,Long pagesize);
	/**保存消息
	 * @param receiveId 接收人Id
	 * @param rType	接收人类型(0-车主,1-员工)
	 * @param SENDER 发送人Id
	 * @param sType 发送人类型(0-车主,1-员工)
	 * @param content 内容
	 * @param msgType 消息类型 （1-文本 2-预约 3-咨询）
	 * @param jumpId 跳转Id 如果消息为文本此参数可为空
	 * @param headImg 头像快照
	 * @param nick 昵称
	 * @return resrveType 预约类型
	 * @return reserveState 预约状态
	 * @return CnMsgInfoPer
	 */
	CnMsgInfoPer savaMsg(String receiveId,int rType,String SENDER,int sType,String content,int msgType,Long jumpId,String headImg,String nick,Integer resrveType,Integer reserveState);
	
	/**根据接收人和发送人删除消息
	 * @param receiveId
	 * @param sender
	 * @return int删除数量
	 */
	int deleteMsgByStaffIdAndCusId(String receiveId, String sender);
}
