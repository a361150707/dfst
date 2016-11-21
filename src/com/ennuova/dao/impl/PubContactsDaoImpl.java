package com.ennuova.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ennuova.base.impl.BaseDAOImpl;
import com.ennuova.dao.PubContactsDao;
import com.ennuova.entity.PubContacts;
import com.ennuova.util.UrlUtil;

@Repository("pubContactsDao")
public class PubContactsDaoImpl extends BaseDAOImpl<PubContacts> implements
		PubContactsDao {

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
	@Override
	public Map<String, Object> findByLoginIdTypeAndContacsIdType(String loginId, String loginType, String contactsId,
			String contactsType) {
		String sql = "select con.ID,con.RELATION from PUB_CONTACTS con"
				   + " where con.LOGIN_ID = ? and con.LOGIN_TYPE = ?"
				   + " and con.CONTACTS_ID = ? and con.CONTACTS_TYPE = ?";
		return findOneForJdbc(sql, loginId,loginType,contactsId,contactsType);
	}

	/**
	 * 修改关注和拉黑,取消关注的状态
	 *@author sududa
	 *@date 2016年10月18日
	 * @param relation 
	 * @param loginId
	 * @param loginType
	 * @param contactsId
	 * @param contactsType
	 * @return 修改成功返回 > 0
	 */
	@Override
	public Integer updateByLoginIdTypeAndContacsIdType(String relation, String loginId, String loginType, String contactsId,
			String contactsType) {
		String sql = "update PUB_CONTACTS con set con.RELATION = ?"
				   + " where con.LOGIN_ID = ? and con.LOGIN_TYPE = ?"
				   + " and con.CONTACTS_ID = ? and con.CONTACTS_TYPE = ?";
		return executeSql(sql, relation,loginId,loginType,contactsId,contactsType);
	}

	/**
	 * 根据当前登录的id,类型,联系人的id,类型删除联系人
	 *@author sududa
	 *@date 2016年10月18日
	 * @param loginId
	 * @param loginType
	 * @param contactsId
	 * @param contactsType
	 * @return 删除
	 */
	@Override
	public Integer deleteByLoginIdTypeAndContacsIdType(String loginId, String loginType, String contactsId,
			String contactsType) {
		String sql = "delete from PUB_CONTACTS con"
				   + " where con.LOGIN_ID = ? and con.LOGIN_TYPE = ?"
				   + " and con.CONTACTS_ID = ? and con.CONTACTS_TYPE = ?";
		return executeSql(sql, loginId,loginType,contactsId,contactsType);
	}

	/**
	 * 更加登录的id和登录的类型查找登录人关注的列表
	 *@author sududa
	 *@date 2016年10月21日
	 * @param loginId 登录人的id
	 * @param loginType 登录人的类型
	 * @return 该登录人的关注列表
	 */
	@Override
	public List<Map<String, Object>> findFollowByLoginIdAndType(String loginId, String loginType) {
		String sql = "select con.CONTACTS_ID as followId"
				   + " from PUB_CONTACTS con"
				   + " where con.LOGIN_ID = ? and con.LOGIN_TYPE = ?"
				   + " and con.RELATION = ?";
		return findForJdbc(sql, loginId,loginType,1);
	}
	/**
	 * 根据登陆人的ID,查找关注的人
	 * @author 陈晓珊
	 * @date 2016年10月21日
	 * @param loginId
	 */
	@Override
	public List<Map<String, Object>> doFollowList(String loginId) {
		String imgUrl = UrlUtil.getInstance().getImgurl();
		String sql = "SELECT cus.ID,?||cus.FPHOTO as FPHOTO,"
				+ "nvl(cus.FNICK,'匿名') as FNICK,cus.FSEX,"
				+ "nvl(cus.CUS_INTEREST,'未设置兴趣标签') as cusInterest,"
				+ "nvl(line.FNAME,'未绑定车辆') as lineName,"
				+ "to_char(box.FJHTIME,'yyyy-mm-dd hh24:mi:ss') as jhTime FROM "
				+ "PUB_CUSTOMER cus LEFT JOIN PUB_CARINFO car ON cus.ID = car.FCUSTOMER "
				+ "AND car.FDEFAULT = ? LEFT JOIN PUB_LINE line ON car.FLINE = line.ID "
				+ "LEFT JOIN PUB_BOX box on car.ID = box.FCLID "
				+ "WHERE to_char(cus.ID) IN (SELECT CONTACTS_ID FROM PUB_CONTACTS "
				+ "WHERE LOGIN_ID = ? AND relation = '1')";	
		return findForJdbc(sql,imgUrl,1, loginId);
	}
	/**
	 * 根据登陆人的ID,查找粉丝
	 * @author 陈晓珊
	 * @date 2016年10月21日
	 * @param loginId
	 */
	@Override
	public List<Map<String, Object>> doFansList(String loginId) {
		String imgUrl = UrlUtil.getInstance().getImgurl();
		String sql ="SELECT cus.ID,?||cus.FPHOTO as FPHOTO,"
				+ "nvl(cus.FNICK,'匿名') as FNICK,cus.FSEX,"
				+ "nvl(cus.CUS_INTEREST,'未设置兴趣标签') as cusInterest,"
				+ "nvl(line.FNAME,'未绑定车辆') as lineName,"
				+ "to_char(box.FJHTIME,'yyyy-mm-dd hh24:mi:ss') as jhTime FROM "
				+ "PUB_CUSTOMER cus LEFT JOIN PUB_CARINFO car ON cus.ID = car.FCUSTOMER "
				+ "AND car.FDEFAULT = ? LEFT JOIN PUB_LINE line ON car.FLINE = line.ID "
				+ "LEFT JOIN PUB_BOX box on car.ID = box.FCLID  WHERE to_char(cus.ID) IN "
				+ "(SELECT LOGIN_ID FROM PUB_CONTACTS WHERE CONTACTS_ID = ? AND relation = '1')";
		return findForJdbc(sql, imgUrl,1,loginId);
	}

	/**
	 * 根据登陆人的ID,查找黑名单列表
	 * @author 陈晓珊
	 * @date 2016年10月21日
	 * @param loginId
	 * @return
	 */
	@Override
	public List<Map<String, Object>> doBlackList(String loginId) {
		String imgUrl = UrlUtil.getInstance().getImgurl();
		String sql = " SELECT cus.ID,?||cus.FPHOTO as FPHOTO,"
				+ "nvl(cus.FNICK,'匿名') as FNICK,cus.FSEX,"
				+ "nvl(line.FNAME,'未绑定车辆') as lineName ,"
				+ "to_char(box.FJHTIME,'yyyy-mm-dd hh24:mi:ss') as jhTime FROM "
				+ "PUB_CUSTOMER cus LEFT JOIN PUB_CARINFO car ON cus.ID = car.FCUSTOMER "
				+ "AND car.FDEFAULT = ? LEFT JOIN PUB_LINE line ON car.FLINE = line.ID "
				+ "LEFT JOIN PUB_BOX box on car.ID = box.FCLID WHERE to_char(cus.ID) IN"
				+ "(SELECT CONTACTS_ID FROM PUB_CONTACTS WHERE LOGIN_ID = ? AND relation = '2')";
		return findForJdbc(sql, imgUrl,1, loginId);
	}

	/**
	 * 根据当前登录的id，联系人列表
	 * @author 陈晓珊
	 * @date 2016年10月21日
	 * @param loginId
	 * @return
	 */
	@Override
	public List<Map<String, Object>> doContactsList(String loginId) {
		String imgUrl = UrlUtil.getInstance().getImgurl();
		/*String sql = "SELECT cus.ID,cus.FPHOTO,cus.FNICK,cus.CUS_INTEREST as cusInterest,cus.FSEX,"
				+ "line.FNAME as lineName"
				+ " FROM PUB_CUSTOMER cus"
				+ " left join PUB_CARINFO car"
				+ " on cus.ID = car.FCUSTOMER"
				+ " left join PUB_LINE line"
				+ " on car.FLINE = line.ID"
				+ " WHERE cus.ID IN"
				+ " (SELECT con.LOGIN_ID FROM PUB_CONTACTS con WHERE con.LOGIN_ID in"
				+ " (SELECT cons.CONTACTS_ID FROM PUB_CONTACTS cons WHERE cons.LOGIN_ID = ? AND relation = '1') "
				+ " AND con.CONTACTS_ID = ? AND con.relation = ?)";
		return findForJdbc(sql, loginId,loginId,1);*/
		String sql = "SELECT cus.ID,?||cus.FPHOTO as FPHOTO,"
				+ "nvl(cus.FNICK,'匿名') as FNICK,cus.FSEX,"
				+ "nvl(cus.CUS_INTEREST,'未设置兴趣标签') as cusInterest,"
				+ "nvl(line.FNAME,'未绑定车辆') as lineName,"
				+ "to_char(box.FJHTIME,'yyyy-mm-dd hh24:mi:ss') as jhTime"
				+ " FROM PUB_CUSTOMER cus"
				+ " LEFT JOIN PUB_CARINFO car"
				+ " ON cus.ID = car.FCUSTOMER AND car.FDEFAULT = ?"
				+ " LEFT JOIN PUB_LINE line"
				+ " ON car.FLINE = line.ID"
				+ " LEFT JOIN PUB_BOX box"
				+ " on car.ID = box.FCLID"
				+ " where cus.ID in"
				+ " (SELECT to_number(LOGIN_ID) FROM PUB_CONTACTS"
				+ " WHERE LOGIN_ID IN"
				+ " (SELECT CONTACTS_ID FROM PUB_CONTACTS WHERE LOGIN_ID = ?"
				+ " AND relation = '1')"
				+ " AND CONTACTS_ID = ? AND relation = '1')";
//				+ " WHERE to_char(cus.ID) IN"
//				+ " (SELECT LOGIN_ID FROM PUB_CONTACTS WHERE LOGIN_ID IN"
//				+ " (SELECT CONTACTS_ID FROM PUB_CONTACTS WHERE LOGIN_ID = ?"
//				+ " AND relation = '1') AND CONTACTS_ID = ? AND relation = '1')";
		return findForJdbc(sql,imgUrl,1, loginId,loginId);
//		return findForJdbc(sql,imgUrl,1);
	}

	/**
	 * 根据当前登录的id，统计关注个数
	 * @author 陈晓珊
	 * @date 2016年10月21日
	 * @param loginId
	 * @return
	 */
	@Override
	public Map<String, Object> doFollowNum(String loginId) {
		String sql = "SELECT COUNT(*)followCount FROM "
				+ "PUB_CONTACTS WHERE LOGIN_ID = ? AND relation = '1'";
		return findOneForJdbc(sql, loginId);
	}

	/**
	 * 根据当前登录的id,统计粉丝的数目
	 * @author 陈晓珊
	 * @date 2016年10月21日
	 * @param loginId
	 * @return
	 */
	@Override
	public Map<String, Object> doFansNum(String loginId) {
		String sql = "SELECT COUNT(*)fansCount FROM "
				+ "PUB_CONTACTS WHERE CONTACTS_ID = ? AND relation = '1'";
		return findOneForJdbc(sql, loginId);
	}


	/**
	 * 根据当前登录的id,统计黑名单的人数
	 * @author 陈晓珊
	 * @date 2016年10月21日
	 * @param loginId
	 * @return
	 */
	@Override
	public Map<String, Object> doBlacklistNum(String loginId) {
		String sql = "SELECT COUNT(*)blacklistCount FROM "
				+ "PUB_CONTACTS WHERE LOGIN_ID = ? AND relation = '2'";
		return findOneForJdbc(sql, loginId);
	}

	/**
	 * 根据当前登录的id,统计联系人的人数
	 * @author 陈晓珊
	 * @date 2016年10月21日
	 * @param loginId
	 * @return
	 */
	@Override
	public Map<String, Object> doContactsNum(String loginId) {
		String sql = "SELECT COUNT(*)contactsNum FROM PUB_CONTACTS WHERE LOGIN_ID IN"
				+ "(SELECT CONTACTS_ID FROM PUB_CONTACTS WHERE LOGIN_ID = ? "
				+ "AND relation = '1') AND CONTACTS_ID = ? AND relation = '1'";
		return findOneForJdbc(sql, loginId,loginId);
	}

	/**
	 * 根据当前登录的id,查找我关注的人的ID
	 * @author 陈晓珊
	 * @date 2016年10月21日
	 * @param loginId
	 * @return
	 */
	@Override
	public List<Map<String, Object>> queryFollowList(String loginId) {
		String sql ="SELECT CONTACTS_ID FROM PUB_CONTACTS WHERE relation = '1' AND LOGIN_ID = ? ";
		return findForJdbc(sql, loginId);
	}

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
	@Override
	public Map<String, Object> findByLoginIdTypeAndContacsIdTypeRelation(String loginId, String loginType,
			String contactsId, String contactsType, String relation) {
		String sql = "select con.ID from PUB_CONTACTS con"
				   + " where con.LOGIN_ID = ? and con.LOGIN_TYPE = ?"
				   + " and con.CONTACTS_ID = ? and con.CONTACTS_TYPE = ?"
				   + " and con.RELATION = ?";
		return findOneForJdbc(sql, loginId,loginType,contactsId,contactsType,relation);
	}

	/**
	 * 根据联系人ID，获取联系人详情
	 * @author 陈晓珊
	 * @date 2016年10月25日
	 * @param id
	 * @return
	 */
	@Override
	public Map<String, Object> doContactDetail(String id) {
		String imgUrl = UrlUtil.getInstance().getImgurl();
		String sql = "SELECT cus.ID,?||cus.FPHOTO as FPHOTO,"
				+ "nvl(cus.FNICK,'匿名') as FNICK,cus.FSEX,"
				+ "nvl(cus.CUS_INTEREST,'未设置兴趣标签') as cusInterest,"
				+ "nvl(CUS.FOLLOW_COUNT,0) as FOLLOWCOUNT,"
				+ "nvl(CUS.FANS_COUNT,0) as FANSCOUNT,"
				+ "nvl(line.FNAME,'未绑定车辆') as lineName "
				+ "FROM PUB_CUSTOMER cus LEFT JOIN PUB_CARINFO car "
				+ "ON cus.ID = car.FCUSTOMER AND car.FDEFAULT = '1' LEFT JOIN PUB_LINE line "
				+ "ON car.FLINE = line.ID WHERE cus.ID = ?";
		return findOneForJdbc(sql,imgUrl, id);
	}

}
