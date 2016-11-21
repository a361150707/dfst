package com.ennuova.app.service;

import java.util.List;

import com.ennuova.entity.CnExpress;
import com.ennuova.entity.CnInfo;
import com.ennuova.entity.CnInfodetail;
import com.ennuova.entity.MessageListVO;
import com.ennuova.entity.PubBox;

public interface CnInfoService {

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
	 * 删除模块消息
	 * @param fsubject
	 * @param cusId
	 * @return
	 */
	int deleteSubjectMsg(String fsubject,long cusId);
	
	/**
	 * 删除(可批量)具体消息
	 * @param detailId
	 * @return
	 */
	int deleteMsg(List<Long> ids);

	/**
	 * 添加盒子提醒
	 * @param pubBox
	 * @param string
	 */
	void addMsg(PubBox pubBox, String string);
	
	/**
	 * 获取个人消息中心(系统咨询给取一条)
	 * @param detail
	 * @return
	 * @author 伟灿
	 */
	public List<CnInfo> UserMsgCore(long cusId);
	/**
	 * 获取个人消息中心
	 * @param detail
	 * @return
	 * @author zhihui
	 */
	public List<MessageListVO> getMessageList(long cusId);
	
	/**
	 * 获取不同类别的消息详情(分页)
	 * @param info
	 * @param page
	 * @param size
	 * @return
	 * @author 伟灿
	 */
	List<CnInfo> getModuleMsg(CnInfo info,int page,int size);
	/**更新角标数量
	 * @param cudId
	 * @param messageType
	 * @return boolean true-成功 false-失败
	 */
	boolean updateBadge(Long cudId,int messageType);
	/**获取不同类别的消息详情(分页)
	 * @param info
	 * @param page
	 * @param size
	 * @return List<CnInfodetail>
	 */
	List<CnInfodetail> getMoreCninfo(Long cusId,Long maxId,Long messageType);
	/**根据用户Id查询该用户是否属于老卡用户
	 * @param userId
	 * @return success true-属于老卡用户 false-不属于
	 */
	boolean alertMessage(Long userId);
	/**保存用户sim卡寄送地址信息
	 * @param cnExpress
	 * @return boolean true-保存成功 false-保存失败
	 */
	boolean saveUserSimSendInfo(CnExpress cnExpress);
}
