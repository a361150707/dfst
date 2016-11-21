package com.ennuova.app.service.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ennuova.app.service.CnInfoService;
import com.ennuova.dao.CnExpressDao;
import com.ennuova.dao.CnInfoDao;
import com.ennuova.entity.CnExpress;
import com.ennuova.entity.CnInfo;
import com.ennuova.entity.CnInfodetail;
import com.ennuova.entity.MessageListVO;
import com.ennuova.entity.PubBox;
import com.ennuova.util.StringUtil;

@Transactional
@Service("cnInfoService")
public class CnInfoServiceImpl implements CnInfoService{
	
	@Resource
	private CnInfoDao cnInfoDao;
	@Resource
	private CnExpressDao cnExpressDao;
	@Override
	public List<CnInfo> getCusMsg(long cusId){
		return cnInfoDao.getCusMsg(cusId);
	}

	@Override
	public List<CnInfo> getDetailMsg(String fsubject, long cusId) {
		cnInfoDao.updateReadMsg(fsubject, cusId);
		return cnInfoDao.getDetailMsg(fsubject, cusId);
	}


	@Override
	public int deleteSubjectMsg(String fsubject, long cusId) {
		return cnInfoDao.deleteSubjectMsg(fsubject, cusId);
	}

	@Override
	public int deleteMsg(List<Long> ids) {
		return cnInfoDao.deleteMsg(ids);
	}

	
	
	/**
	 * 添加盒子提醒
	 * @param pubBox
	 * @param string
	 */
	public void addMsg(PubBox pubBox, String fcode) {
		cnInfoDao.addMsg(pubBox,fcode);
	}
	
	
	/**
	 * 获取个人消息中心(系统咨询给取一条)
	 * @param detail
	 * @return
	 * @author 伟灿
	 */
	@Override
	public List<CnInfo> UserMsgCore(long cusId) {
		List<CnInfo> infoList =  new ArrayList<CnInfo>();
		CnInfo info = new CnInfo();
		info.setCusId(cusId);
		info.setFmodule("系统");
		List<CnInfo> sysDetail = cnInfoDao.queryUserMsg(info,0,1);
		if(sysDetail.size()>0){
			infoList.add(sysDetail.get(0));
		}
		info.setFmodule("资讯");
		List<CnInfo> zixDetail = cnInfoDao.queryUserMsg(info,0,1);
		if(zixDetail.size()>0){
			infoList.add(zixDetail.get(0));
		}
		return infoList;
	}

	/**
	 * 获取不同类别的消息详情(分页)
	 * @param info
	 * @param page
	 * @param size
	 * @return
	 * @author 伟灿
	 */
	@Override
	public List<CnInfo> getModuleMsg(CnInfo info, int page, int size) {
		int startIndex = page*size;//0 10 20 30
		int stopIndex = page*size+size;//10 20 30 40
		cnInfoDao.updateReadMsg(info.getFmodule(), info.getCusId());
		List<CnInfo> infoList = this.cnInfoDao.queryUserMsg(info, startIndex, stopIndex);
		return infoList;
	}
	public static void main(String[] args) {
		String d = "dsfsf,";
		System.out.println(d.substring(0,d.length()-1));
	}
	@Override
	public List<MessageListVO> getMessageList(long cusId) {
		List<MessageListVO> messageListVOs = new ArrayList<MessageListVO>();
		List<CnInfodetail> cnInfodetails = cnInfoDao.getMessageListNum(cusId);
		Map<Long, Long> map = new HashMap<Long, Long>();
		Map<Long, CnInfo> map2 = new HashMap<Long, CnInfo>();
		DateFormat dateFormatDate = new SimpleDateFormat("yyyy-MM-dd");
		for (CnInfodetail cnInfodetail : cnInfodetails) {
			Long lineNun = map.get(cnInfodetail.getMessageType());
			if(lineNun==null)
				lineNun =0L;
			map.put(cnInfodetail.getMessageType(), lineNun+1);
			CnInfo cnInfo = map2.get(cnInfodetail.getMessageType());
			if(cnInfo==null){
				cnInfo = new CnInfo();
				cnInfo = cnInfoDao.getById(cnInfodetail.getCnInfo().getId());
				map2.put(cnInfodetail.getMessageType(), cnInfo);	
			}
		}
		// 1-故障 2-防盗 3-驾驶 4-安全 5-车务 6-资讯 7-意见反馈 8-i宝团队
		for(Long key : map.keySet()){
			List<CnInfodetail> cnInfodetails2 = new ArrayList<CnInfodetail>();
			cnInfodetails2 = cnInfoDao.getCnInfodetails(cusId, key, 9999999999999L);
			StringBuffer buffer = new StringBuffer("");
			for (CnInfodetail cnInfodetail : cnInfodetails2) {
				buffer.append(cnInfodetail.getCnInfo().getId()).append(",");
			}
			String idList = buffer.toString();
			if(buffer.toString().length()>0){
				idList = buffer.toString().substring(0,buffer.toString().length()-1);
			}
			List<CnInfo> cnInfos = cnInfoDao.getCnInfosByIdList(idList);
			MessageListVO messageListVO = new MessageListVO();
			messageListVO.setCnInfodetails(cnInfodetails2);
			messageListVO.setCnInfos(cnInfos);
			messageListVO.setMessageType(key);
			messageListVO.setMessageNum(map.get(key));
			if(key==1){
				messageListVO.setName("故障消息");
			}else if (key==2) {
				messageListVO.setName("防盗消息");
			}else if (key==3) {
				messageListVO.setName("驾驶消息");
			}else if (key==4) {
				messageListVO.setName("安全消息");
			}else if (key==5) {
				messageListVO.setName("车务消息");
			}else if (key==6) {
				messageListVO.setName("资讯消息");
			}else if (key==7) {
				messageListVO.setName("意见消息");
			}else if (key==8) {
				messageListVO.setName("i宝团队");
			}
			messageListVO.setNewMesssage(map2.get(key).getFcontent());
			String messageDate = dateFormatDate.format(map2.get(key).getFsendtime());
			messageListVO.setTimeStr(messageDate);
			if(messageListVO.getName()!=null){
				messageListVOs.add(messageListVO);
			}
		}
		return messageListVOs;
	}

	@Override
	public boolean updateBadge(Long cudId, int messageType) {
		boolean success = false;
		try {
			int lineNum = cnInfoDao.updateBrage(messageType, cudId);
			if(lineNum>0)
				success = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return success;
	}

	@Override
	public List<CnInfodetail> getMoreCninfo(Long cusId, Long maxId, Long messageType) {
		List<CnInfodetail> CnInfodetails = new ArrayList<CnInfodetail>();
		try {
			CnInfodetails = cnInfoDao.getCnInfodetails(cusId, messageType, maxId);
			StringBuffer buffer = new StringBuffer("");
			for (CnInfodetail cnInfodetail : CnInfodetails) {
				buffer.append(cnInfodetail.getCnInfo().getId()).append(",");
			}
			String idList = buffer.toString();
			if(StringUtil.isNotEmpty(idList) && idList.length()>0){
				idList = buffer.toString().substring(0,buffer.toString().length()-1);
				List<CnInfo> cnInfos = cnInfoDao.getCnInfosByIdList(idList);
				for (int i = 0; i < CnInfodetails.size(); i++) {
					CnInfodetail cnInfodetail = CnInfodetails.get(i);
					if(i<cnInfos.size()){
						cnInfodetail.setCnInfo(cnInfos.get(i));
						CnInfodetails.set(i, cnInfodetail);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return CnInfodetails;
	}

	@Override
	public boolean alertMessage(Long userId) {
		boolean success  = cnInfoDao.alertMessage(userId);
		if(success){
			CnExpress cnExpress = cnExpressDao.getCnExpress(userId);
			if(cnExpress.getId()!=null){
				success = false;
			}
		}
		return success;
	}

	@Override
	public boolean saveUserSimSendInfo(CnExpress cnExpress) {
		boolean success = false;
		try {
			cnExpress = cnExpressDao.save(cnExpress);
			if(cnExpress.getId()!=null){
				success =  true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
		return success;
	}

}
