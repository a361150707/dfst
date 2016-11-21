package com.ennuova.app.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ennuova.app.service.PubContactsService;
import com.ennuova.dao.CarReserveDao;
import com.ennuova.dao.PubContactsDao;
import com.ennuova.dao.PubCustomerDao;
import com.ennuova.dao.ReserveStateChangeRecordDao;
import com.ennuova.dao.StaffChangeRecordDao;
import com.ennuova.entity.PubContacts;
import com.ennuova.util.StringUtil;
import com.ennuova.util.UrlUtil;

@Service("pubContactsService")
public class PubContactsServiceImpl implements PubContactsService {
	
	/**
	 * 联系人的dao
	 */
	@Resource
	private PubContactsDao pubContactsDao;
	
	/**
	 * 客户的dao
	 */
	@Resource
	private PubCustomerDao pubCustomerDao;

	/**
	 * 关注,取消关注,拉黑
	 *@author sududa
	 *@date 2016年10月18日
	 * @param pubContacts
	 * @return
	 */
	@Override
	public Integer doFollowAndUnFollow(PubContacts pubContacts) {
		String relation = pubContacts.getRelation();//关系
		String loginId = pubContacts.getLoginId();//当前登录人的id
		String loginType = pubContacts.getLoginType();//当前登录人的类型(1-车主,2-员工)
		String contactsId = pubContacts.getContactsId();//联系人的id
		String contactsType = pubContacts.getContactsType();//联系人的类型(1-车主,2-员工)
		if(loginId.equals(contactsId) && loginType.equals(contactsType)){
			return  0;
		}
		if(StringUtil.isNotEmpty(relation)){
			if("3".equals(relation)){//表示取消关注的操作
				Map<String, Object> pubContactsMap = pubContactsDao.findByLoginIdTypeAndContacsIdType(loginId,loginType,contactsId,contactsType);
				if(StringUtil.isNotEmpty(pubContactsMap)){//说明还没有执行取消关注的操作
					Integer result = pubContactsDao.deleteByLoginIdTypeAndContacsIdType(loginId,loginType,contactsId,contactsType);
					if(result > 0){
						pubCustomerDao.updateFollowCount(loginId,1);
						pubCustomerDao.updateFansCount(contactsId,1);
					}
					return result;
				}
				return 1;
			}else if("1".equals(relation)){//表示关注
				Map<String, Object> pubContactsMap = pubContactsDao.findByLoginIdTypeAndContacsIdType(loginId,loginType,contactsId,contactsType);
				if(StringUtil.isNotEmpty(pubContactsMap)){//表示数据库已经有记录了
					if("1".equals(pubContactsMap.get("RELATION"))){//表示不能重复操作已关注的数据
						return 1;
					}else if("2".equals(pubContactsMap.get("RELATION"))){//表示原来已经拉黑了,再重新关注的
						Integer result = pubContactsDao.updateByLoginIdTypeAndContacsIdType(relation,loginId,loginType,contactsId,contactsType);
						if(result > 0){
							pubCustomerDao.updateFollowCountPlus(loginId,1);
							pubCustomerDao.updateFansCountPlus(contactsId,1);
						}
						return result;
					}else{//表示出错了
						return 0;
					}
//					Integer result = pubContactsDao.updateByLoginIdTypeAndContacsIdType(relation,loginId,loginType,contactsId,contactsType);
//					return result;
				}else{//表示还没有关注可以录入
					PubContacts resultEntity = pubContactsDao.save(pubContacts);
					if(StringUtil.isNotEmpty(resultEntity)){//表示关注成功
						pubCustomerDao.updateFollowCountPlus(loginId,1);
						pubCustomerDao.updateFansCountPlus(contactsId,1);
						return 1;
					}
				}
			}else{//表示拉黑
				Map<String, Object> pubContactsMap = pubContactsDao.findByLoginIdTypeAndContacsIdType(loginId,loginType,contactsId,contactsType);
				if(StringUtil.isNotEmpty(pubContactsMap)){//表示原来就有记录了，不能重新插入
					if("2".equals(pubContactsMap.get("RELATION"))){//表示不能重复操作已拉黑的数据
						return 1;//返回拉黑成功
					}
					if("1".equals(pubContactsMap.get("RELATION"))){//表示原来已经关注了,直接拉黑
						Integer result = pubContactsDao.updateByLoginIdTypeAndContacsIdType(relation,loginId,loginType,contactsId,contactsType);
						if(result > 0){
							pubCustomerDao.updateFollowCount(loginId,1);
							pubCustomerDao.updateFansCount(contactsId,1);
						}
						return result;
					}
				}else{//表示没有插入，执行插入的操作
					PubContacts resultEntity = pubContactsDao.save(pubContacts);
					if(StringUtil.isNotEmpty(resultEntity)){
						return 1;
					}
				}
			}
		}
		return 0;
	}

	/**
	 * 关注的人列表
	 * @author 陈晓珊
	 * @date 2016年10月21日
	 * @param loginId
	 * @return
	 */
	@Override
	public List<Map<String, Object>> doFollowList(String loginId) {
		List<Map<String, Object>> followList = pubContactsDao.doFollowList(loginId);
		for(Map<String, Object> map : followList){
			//我关注的人
			map.put("ISFOLLOW", "1");
		}
		return followList;
	}

	/**
	 * 粉丝列表
	 * @author 陈晓珊
	 * @date 2016年10月21日
	 * @param loginId
	 * @return
	 */
	@Override
	public List<Map<String, Object>> doFansList(String loginId) {
		List<Map<String, Object>> fansList = pubContactsDao.doFansList(loginId);
		List<Map<String, Object>>  followList = pubContactsDao.queryFollowList(loginId);
		//粉丝中是否有我关注的人
		for(Map<String, Object> map : fansList){
			String cusID =  map.get("ID")+"";
			map.put("ISFOLLOW", "");
			for(int j = 0;j < followList.size();j++){
				String contactsID = followList.get(j).get("CONTACTS_ID")+"";
				if(contactsID.equals(cusID)){
					map.put("ISFOLLOW", "1");
					break;
				}
			}
		}
		return fansList;
	}

	/**
	 * 黑名单列表
	 * @author 陈晓珊
	 * @date 2016年10月21日
	 * @param loginId
	 * @return
	 */
	@Override
	public List<Map<String, Object>> doBlackList(String loginId) {
		return pubContactsDao.doBlackList(loginId);
	}

	/**
	 * 联系人列表列表
	 * @author 陈晓珊
	 * @date 2016年10月21日
	 * @param loginId
	 * @return
	 */
	@Override
	public List<Map<String, Object>> doContactsList(String loginId) {
		List<Map<String, Object>> contactsList = pubContactsDao.doContactsList(loginId);
		if(StringUtil.isNotEmpty(contactsList) && contactsList.size()>0){
			for(Map<String, Object> map : contactsList){
				map.put("ISFOLLOW", "1");
			}
		}
		return contactsList;
	}

	/**
	 * 车友数
	 * @author 陈晓珊
	 * @date 2016年10月21日
	 * @param loginId
	 * @return
	 */
	@Override
	public Map<String, Object> doRidersNum(String loginId) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		Map<String, Object> followMap = pubContactsDao.doFollowNum(loginId);
		Map<String, Object> fanswMap = pubContactsDao.doFansNum(loginId);
		Map<String, Object> blacklistMap = pubContactsDao.doBlacklistNum(loginId);
		Map<String, Object> contactstMap = pubContactsDao.doContactsNum(loginId);
		resultMap.put("FOLLOWCOUNT",followMap.get("followNum") );
		resultMap.put("FANSCOUNT", fanswMap.get("fansNum"));
		resultMap.put("BLACKLISTCOUNT", blacklistMap.get("blacklistNum"));
		resultMap.put("CONTACTSCOUNT", contactstMap.get("contactsNum"));
		return resultMap;
	}
	
	/**
	 * 联系人详情
	 * @author 陈晓珊
	 * @date 2016年10月21日
	 * @param id
	 * @return
	 */
	@Override
	public Map<String, Object> doContactDetail(String id) {
		Map<String, Object> resultMap = pubContactsDao.doContactDetail(id);
		if(StringUtil.isNotEmpty(resultMap)){
			resultMap.put("ISFOLLOW", "1");
		}
		return resultMap;
	}

	/**
	 * 关注的人ID列表
	 * @author 陈晓珊
	 * @date 2016年10月31日
	 * @param loginId
	 * @return
	 */
	@Override
	public List<Map<String, Object>> queryFollowList(String loginId) {
		return pubContactsDao.queryFollowList(loginId);
	}
	


}
