package com.ennuova.app.service.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ennuova.app.service.CnMsgInfoPerService;
import com.ennuova.dao.CnMsgInfoDao;
import com.ennuova.dao.CnMsgInfoPerDao;
import com.ennuova.entity.CnMsgInfo;
import com.ennuova.entity.CnMsgInfoPer;
import com.ennuova.push.InnovPush;
import com.ennuova.util.Constant;
import com.ennuova.util.UrlUtil;

/**消息服务实现
 * @author lee
 * @time 2016-9-25 下午9:16:48
 */
@Service
public class CnMsgInfoPerServiceImpl implements CnMsgInfoPerService{
	@Resource
	private CnMsgInfoDao cnMsgInfoDao;
	@Resource
	private CnMsgInfoPerDao cnMsgInfoPerDao;
	@Override
	public boolean deleteMsg(Long id) {
		
		return false;
	}
	@Override
	public List<Map<String, Object>> getMsgList(String receiveId,int rType) {
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		String imgUrl = UrlUtil.getInstance().getImgurl();
		DateFormat dateFormatDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			list = cnMsgInfoPerDao.getMsgList(receiveId, rType);
			Map<String, Long> mapBage = cnMsgInfoPerDao.getBage(receiveId);
			for (Map<String, Object> map : list) {
				map.put("CORNER", mapBage.get(map.get("SENDER")));
				Date date = (Date) map.get("CREATE_DATE");
				map.put("CREATE_DATE", dateFormatDate.format(date));
				/*String headImg = (String) map.get("HEAD_IMG");
				if(headImg!=""){
					map.put("HEAD_IMG", imgUrl+headImg);
				}*/
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	@Override
	public List<Map<String, Object>> getMsgDetailList(String receiveId,
			int rType, String sender,Long pageNum,Long pagesize) {
		Long startIndex = pageNum*pagesize;
		Long stopIndex = pageNum*pagesize+pagesize;
		//DateFormat dateFormatDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		try {
			list = cnMsgInfoPerDao.getMsgDetailList(receiveId, rType, sender, startIndex, stopIndex);
//			for (Map<String, Object> map : list) {
//				Date date = (Date) map.get("createDate");
//				map.put("createDate", dateFormatDate.format(date));
//			}
			cnMsgInfoPerDao.setReader(receiveId, sender);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return list;
	}
	@Override
	public CnMsgInfoPer savaMsg(String receiveId, int rType, String sender, int sType,
			String content, int msgType, Long jumpId, String headImg,
			String nick,Integer reserveType,Integer reserveState) {
		CnMsgInfoPer cnMsgInfoPer = new CnMsgInfoPer();
		CnMsgInfo cnMsgInfo = new CnMsgInfo();
		cnMsgInfo.setContent(content);
		cnMsgInfo.setCreateDate(new Date());
		cnMsgInfo.setJumpId(jumpId);
		cnMsgInfo.setMsgType(msgType);
		cnMsgInfo.setReserveType(reserveType);
		cnMsgInfo.setReserveState(reserveState);
		cnMsgInfo = cnMsgInfoDao.save(cnMsgInfo);
		if(cnMsgInfo.getId()!=null){
			if(content.length()>40){
				content = content.substring(0, 38)+"…";
			}
			cnMsgInfoPer.setContent(content);
			cnMsgInfoPer.setDelFlag("0");
			cnMsgInfoPer.setCreateDate(new Date());
			cnMsgInfoPer.setHeadImg(headImg);
			cnMsgInfoPer.setIsRead(0);
			cnMsgInfoPer.setMsgInfoId(cnMsgInfo.getId());
			cnMsgInfoPer.setNick(nick);
			cnMsgInfoPer.setRecipient(receiveId);
			cnMsgInfoPer.setRecipientType(rType);
			cnMsgInfoPer.setSender(sender);
			cnMsgInfoPer.setSenderType(sType);
			cnMsgInfoPer = cnMsgInfoPerDao.save(cnMsgInfoPer);
			if(cnMsgInfoPer.getId()>0){	//推送消息
				if(rType==0){
					InnovPush.testSendPush("zhg"+receiveId, content, 3, Constant.MASRER_CZ_SECRET, Constant.APP_CZ_KEY);
				}else {
					InnovPush.testSendPush(receiveId, content, 3, Constant.MASRER_STAFF_SECRET, Constant.APP_STAFF_KEY);
				}
			}
		}
		return cnMsgInfoPer;
	}
	
	@Override
	public int deleteMsgByStaffIdAndCusId(String receiveId, String sender) {
		// TODO Auto-generated method stub
		return cnMsgInfoPerDao.deleteMsgByStaffIdAndCusId(receiveId, sender);
	}
	
}
