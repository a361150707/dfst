package com.ennuova.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ennuova.base.impl.BaseDAOImpl;
import com.ennuova.dao.PubDynamicDao;
import com.ennuova.entity.PubDynamic;
import com.ennuova.util.StringUtil;
import com.ennuova.util.UrlUtil;

@Repository
public class PubDynamicDaoImpl extends BaseDAOImpl<PubDynamic> implements
		PubDynamicDao {

	/**
	 * 发布动态
	 *@author sududa
	 *@date 2016年10月19日
	 * @param pubDynamic
	 * @return
	 */
	@Override
	public PubDynamic doSendDynamic(PubDynamic pubDynamic) {
		return save(pubDynamic);
	}

	/**
	 * 删除动态
	 *@author sududa
	 *@date 2016年10月19日
	 * @param delfLag 
	 * @param id
	 * @param sendId
	 * @return
	 */
	@Override
	public Integer doDelete(String delfLag,String id, String sendId) {
		String sql = "update PUB_DYNAMIC d set d.DEL_FLAG = ?"
				   + " where d.ID = ? and d.SEND_ID = ?";
		return executeSql(sql, delfLag,id,sendId);
	}

	/**
	 * 根据发送人的id查找动态列表
	 *@author sududa
	 *@date 2016年10月19日
	 * @param id
	 * @return
	 */
	@Override
	public List<Map<String, Object>> findBySendId(String id) {
		String sql = "select dy.ID,dy.TITLE,dy.CONTENT,dy.IMG_PATH as imgPath,"
				   + "dy.SHRINK_DIAGRAM as shrinkDiagram,"
				   + "to_char(dy.CREATE_DATE,'yyyy-mm-dd hh24:mi:ss') as createDate"
				   + " from PUB_DYNAMIC dy"
				   + " where dy.SEND_ID = ?"
				   + " order by dy.CREATE_DATE desc";
		return findForJdbc(sql, id);
	}

	/**
	 * 分页查询动态的时间列表
	 *@author sududa
	 *@date 2016年10月24日
	 * @param id
	 * @param page
	 * @param rows
	 * @return
	 */
	@Override
	public List<Map<String, Object>> findTimeListById(String id, Integer page, Integer rows) {
		String sql = "select distinct"
				   + " to_char(dy.CREATE_DATE,'yyyy-mm-dd') as sendDate"
				   + " from PUB_DYNAMIC dy"
				   + " where dy.SEND_ID = ?"
				   + " order by sendDate desc";
		return findForJdbcPage(sql, page, rows, id);
	}


	/**
	 * 根据id和发送动态的日期查询动态的列表
	 *@author sududa
	 *@date 2016年10月25日
	 * @param id
	 * @param sendDate
	 * @return
	 */
	@Override
	public List<Map<String, Object>> findBySendIdAndSendDate(String id, String sendDate) {
		String sql = "select dy.ID,dy.TITLE,dy.CONTENT,dy.IMG_PATH as imgPath,"
				   + "dy.SHRINK_DIAGRAM as shrinkDiagram,"
				   + "to_char(dy.CREATE_DATE,'yyyy-mm-dd hh24:mi:ss') as createDate,"
				   + "dy.DYNAMIC_PLATE_ID as topicId,"
				   + "dy.CUS_LOCATION AS location"
				   + " from PUB_DYNAMIC dy"
				   + " where dy.SEND_ID = ?"
				   + " and to_char(dy.CREATE_DATE,'yyyy-mm-dd') = ?"
				   + " order by dy.CREATE_DATE desc";
		return findForJdbc(sql, id,sendDate);
	}

	/**
	 * 根据登录的id查询自己,自己的好友,自己关注的,员工发送的动态列表,带分页
	 * @param page
	 * @param rows
	 * @param loginId
	 * @return
	 */
	@Override
	public List<Map<String, Object>> findByIdWithPage(Integer page, Integer rows, String loginId) {
		String imgUrl = UrlUtil.getInstance().getImgurl();
		String sql = "select dy.ID,dy.SEND_ID as sendId,"
				   + "dy.SEND_TYPE as sendType,dy.TITLE,"
				   + "dy.CONTENT,dy.IMG_PATH as imgPath,"
				   + "dy.SHRINK_DIAGRAM as shrinkDiagram,"
				   + "dy.DYNAMIC_PLATE_ID as topicId,"
				   + "to_char(dy.CREATE_DATE,'yyyy-mm-dd hh24:mi:ss') as createDate,"
				   + "dy.CUS_LOCATION AS location,"
				   + " nvl(cus.FNICK,'匿名') as FNICK,"
				   + "?||cus.FPHOTO as FPHOTO,cus.FSEX,"
				   + "nvl(click.clickGoodCount,0) as clickGoodCount,"
				   + "myClick.FROM_ID as isClickGood,"
				   + "contacts.CONTACTS_ID as ISFOLLOW"
				   + " from PUB_DYNAMIC dy"
				   + " LEFT JOIN PUB_CUSTOMER cus"
				   + " ON dy.SEND_ID = to_char(cus.id)"
				   + " left join"
				   + " ("
				   + " select cg.FROM_ID as fromId,COUNT(cg.ID) as clickGoodCount"
				   + " FROM CLICK_GOOD cg"
				   + " WHERE GOOD_TYPE = ?"
				   + " group by FROM_ID"
				   + " ) click"
				   + " on dy.ID = click.fromId"
				   + " left join"//加载登录人是否点赞了此动态
				   + "("
				   + " select cg.FROM_ID FROM CLICK_GOOD cg"
				   + " where GOOD_TYPE = ? and cg.TO_ID = ?"
				   + ") myClick"
				   + " on dy.ID = myClick.FROM_ID "
				   + " left join"//登录人是否关注此人
				   + " ("
				   + " select con.CONTACTS_ID FROM PUB_CONTACTS con "
				   + " where con.LOGIN_ID = ? AND relation = ?"
				   + " ) contacts"
				   + " on dy.SEND_ID = contacts.CONTACTS_ID"
				   + " where"
				   + " (dy.SEND_ID in"
				   + " (select con.CONTACTS_ID from PUB_CONTACTS con"
				   + " where con.LOGIN_ID = ?) and dy.SHOW_TYPE = ?"
				   + " )"
				   + " or"
				   + "(dy.SEND_ID in"
				   + " (select con.CONTACTS_ID from PUB_CONTACTS con"
				   + " where con.CONTACTS_ID in"
				   + " (select con.LOGIN_ID from PUB_CONTACTS con"
				   + " where con.CONTACTS_ID = ?"
				   + " ) and  con.LOGIN_ID = ?"
				   + " ) and dy.SHOW_TYPE = ?"
				   + ")"
				   + " or"
				   + " (dy.SEND_ID in "
				   + " (select dy.SEND_ID from PUB_DYNAMIC dy"
				   + " where dy.SEND_TYPE = ? and dy.DYNAMIC_TYPE = ?"
				   + " and nvl(dy.DEL_FLAG,0)= ?"
				   + " )"
				   + ")"
				   + " or"
				   + " (dy.SEND_ID = ? and nvl(dy.DEL_FLAG,0) = ?)"
				   + " order by dy.CREATE_DATE desc";
/*		String sql = "select dy.ID,dy.SEND_ID as sendId,"
				   + "dy.SEND_TYPE as sendType,dy.TITLE,"
				   + "dy.CONTENT,dy.IMG_PATH as imgPath,"
				   + "dy.SHRINK_DIAGRAM as shrinkDiagram,"
				   + "dy.DYNAMIC_PLATE_ID as topicId,"
				   + "to_char(dy.CREATE_DATE,'yyyy-mm-dd hh24:mi:ss') as createDate,"
				   + "dy.CUS_LOCATION AS location,"
				   + " nvl(cus.FNICK,'匿名') as FNICK,"
				   + "?||cus.FPHOTO as FPHOTO,cus.FSEX,"
				   + "nvl(click.clickGoodCount,0) as clickGoodCount,"
				   + "myClick.TO_ID as isClickGood,"
				   + "contacts.CONTACTS_ID as ISFOLLOW"
				   + " from PUB_DYNAMIC dy"
				   + " LEFT JOIN PUB_CUSTOMER cus"
				   + " ON dy.SEND_ID = to_char(cus.id)"
				   + " left join"
				   + " ("
				   + " select cg.FROM_ID as fromId,COUNT(cg.ID) as clickGoodCount"
				   + " FROM CLICK_GOOD cg"
				   + " WHERE GOOD_TYPE = ?"
				   + " group by FROM_ID"
				   + " ) click"
				   + " on dy.ID = click.fromId"
				   + " left join"//加载登录人是否点赞了此动态
				   + "("
				   + " select cg.TO_ID FROM CLICK_GOOD cg"
				   + " where GOOD_TYPE = ? and cg.FROM_ID = ?"
				   + ") myClick"
				   + " on dy.ID = myClick.TO_ID"
				   + " left join"//登录人是否关注此人
				   + " ("
				   + " select con.CONTACTS_ID FROM PUB_CONTACTS con "
				   + " where con.LOGIN_ID = ? AND relation = ?"
				   + " ) contacts"
				   + " on dy.SEND_ID = contacts.CONTACTS_ID"
				   + " where"
				   + " (dy.SEND_ID in"
				   + " (select con.CONTACTS_ID from PUB_CONTACTS con"
				   + " where con.LOGIN_ID = ?) and dy.SHOW_TYPE = ?"
				   + " )"
				   + " or"
				   + "(dy.SEND_ID in"
				   + " (select con.CONTACTS_ID from PUB_CONTACTS con"
				   + " where con.CONTACTS_ID in"
				   + " (select con.LOGIN_ID from PUB_CONTACTS con"
				   + " where con.CONTACTS_ID = ?"
				   + " ) and  con.LOGIN_ID = ?"
				   + " ) and dy.SHOW_TYPE = ?"
				   + ")"
				   + " or"
				   + " (dy.SEND_ID in "
				   + " (select dy.SEND_ID from PUB_DYNAMIC dy"
				   + " where dy.SEND_TYPE = ? and dy.DYNAMIC_TYPE = ?"
				   + " and nvl(dy.DEL_FLAG,0)= ?"
				   + " )"
				   + ")"
				   + " or"
				   + " (dy.SEND_ID = ? and nvl(dy.DEL_FLAG,0) = ?)"
				   + " order by dy.CREATE_DATE desc";*/
				return findForJdbcPage(sql, page, rows,
						imgUrl, 
						3, 
						3, loginId, 
						loginId,1,
						loginId, 1,
						loginId, loginId, 2, 
						2, 1, 0, 
						loginId, 0);

	}


	/**
	 * 查询话题列表
	 *@author 陈晓珊
	 *@date 2016年10月27日
	 * @param title
	 * @param page
	 * @param rows
	 * @return
	 */
	@Override
	public List<Map<String, Object>> doTopicList(String title,Integer page, Integer rows) {
		StringBuilder sql = new StringBuilder(""+"SELECT D1.ID,D1.TITLE,"
				+ "D1.CUS_LOCATION AS location,"
				+ "D1.CONTENT,D1.IMG_PATH AS IMGPATH,"
				+ "to_char(D1.CREATE_DATE,'yyyy-mm-dd hh24:mi:ss') AS CREATEDATE,"
				+ "nvl(D1.READ_COUNT,0) AS READCOUNT,"
				+ "nvl(D2.JOINCOUNT,0) AS JOINCOUNT,nvl(USR.REALNAME,'匿名') AS REALNAME "
				+ "FROM PUB_DYNAMIC D1 LEFT JOIN "
				+ "(SELECT COUNT(DYNAMIC_PLATE_ID)JOINCOUNT,DYNAMIC_PLATE_ID "
				+ "FROM PUB_DYNAMIC GROUP BY DYNAMIC_PLATE_ID)D2 ON D1.ID = D2.DYNAMIC_PLATE_ID "
				+ "LEFT JOIN T_S_BASE_USER USR ON D1.SEND_ID = USR.ID WHERE D1.DYNAMIC_TYPE = ? "
				+ "AND nvl(D1.DEL_FLAG,0) = ? AND D1.STATE = ?");
		if(StringUtil.isNotEmpty(title)){
			sql.append("AND D1.TITLE LIKE '%"+title+"%'");
		}
		sql.append("ORDER BY D1.CREATE_DATE DESC");
	/*	String sql = "SELECT D1.ID,D1.TITLE,D1.CONTENT,D1.IMG_PATH AS IMGPATH,"
				+ "to_char(D1.CREATE_DATE,'yyyy-mm-dd hh24:mi:ss') AS CREATEDATE,"
				+ "nvl(D1.READ_COUNT,0) AS READCOUNT,"
				+ "nvl(D2.JOINCOUNT,0) AS JOINCOUNT,USR.REALNAME FROM PUB_DYNAMIC D1 LEFT JOIN "
				+ "(SELECT COUNT(DYNAMIC_PLATE_ID)JOINCOUNT,DYNAMIC_PLATE_ID "
				+ "FROM PUB_DYNAMIC GROUP BY DYNAMIC_PLATE_ID)D2 ON D1.ID = D2.DYNAMIC_PLATE_ID "
				+ "LEFT JOIN T_S_BASE_USER USR ON D1.SEND_ID = USR.ID WHERE D1.DYNAMIC_TYPE = ? "
				+ "AND nvl(D1.DEL_FLAG,0) = ? AND D1.STATE = ? ORDER BY D1.CREATE_DATE DESC";*/
		return findForJdbcPage(sql.toString(), page, rows,2,0,2);
	}

	/**
	 * 根据话题id,查询话题详情列表
	 *@author 陈晓珊
	 *@date 2016年10月28日
	 * @param page
	 * @param rows
	 * @param loginId
	 * @return
	 */
	@Override
	public List<Map<String, Object>> doTopicDetilList(String loginId,String id, Integer page,
			Integer rows) {
		String imgUrl = UrlUtil.getInstance().getImgurl();
		String sql = " SELECT dy.ID,dy.SEND_ID AS sendId,dy.SEND_TYPE AS sendType,"
				+"dy.CONTENT,dy.IMG_PATH AS imgPath,"
				+"dy.DYNAMIC_PLATE_ID as topicId,dy.TITLE,"
				+"to_char(dy.CREATE_DATE,'yyyy-mm-dd hh24:mi:ss') as createDate,"
				+"dy.CUS_LOCATION AS location,"
				+"nvl(cus.FNICK,'匿名') as FNICK,?||cus.FPHOTO as FPHOTO,cus.FSEX ,"
				+"GOOD.ID AS isClickGood ,nvl(goodCount.clickGoodCount,0) AS clickGoodCount,"
				+ "con.CONTACTS_ID AS ISFOLLOW "
				+"FROM PUB_DYNAMIC dy LEFT JOIN PUB_CUSTOMER cus "
				+"ON  dy.SEND_ID = to_char(cus.id) LEFT JOIN"//登陆人是否点赞了此动态
				+ " CLICK_GOOD good "
				+"ON dy.ID = GOOD.FROM_ID AND TO_ID = ? AND GOOD.GOOD_TYPE = ? "
				+"LEFT JOIN "//此动态点赞人数
				+ "(SELECT COUNT(ID)clickGoodCount,FROM_ID FROM CLICK_GOOD "
				+"WHERE GOOD_TYPE = ? GROUP BY FROM_ID) goodCount "
				+"ON goodCount.FROM_ID = dy.ID "
				+"LEFT JOIN "//登录人是否关注此人
				+ "(SELECT CONTACTS_ID FROM PUB_CONTACTS "
				+"WHERE relation = ? AND LOGIN_ID = ?) con "
				+"ON con.CONTACTS_ID = dy.SEND_ID "
				+"WHERE dy.DYNAMIC_PLATE_ID = ? "
				+"AND dy.DYNAMIC_TYPE = ? AND nvl(dy.DEL_FLAG,0) = ? "
				+"ORDER BY createDate DESC ";
		/*String sql = " SELECT dy.ID,dy.SEND_ID AS sendId,dy.SEND_TYPE AS sendType,"
				+ "dy.CONTENT,dy.IMG_PATH AS imgPath,"
				+ "dy.DYNAMIC_PLATE_ID as topicId,dy.TITLE,"
				+ "to_char(dy.CREATE_DATE,'yyyy-mm-dd hh24:mi:ss') as createDate,"
				+ "dy.CUS_LOCATION AS location,"
				+ "nvl(cus.FNICK,'匿名') as FNICK,?||cus.FPHOTO as FPHOTO,cus.FSEX "
				+ "FROM PUB_DYNAMIC dy LEFT JOIN PUB_CUSTOMER cus "
				+ "ON  dy.SEND_ID = to_char(cus.id)  "
				+ "WHERE dy.DYNAMIC_PLATE_ID = ? "
				+ "AND dy.DYNAMIC_TYPE = ? AND nvl(dy.DEL_FLAG,0) = ? ORDER BY createDate DESC";*/
		return findForJdbcPage(sql, page, rows,imgUrl,loginId,3,3,1,loginId,id,1,0);
	}

	/**
	 *根据动态ID，获取个人动态详情
	 *@author 陈晓珊
	 *@date 2016年11月1日
	 * @param params
	 * @param response
	 * @throws Exception
	 */
	@Override
	public Map<String, Object> doDyDetilList(String loginId,String id) {

		String imgUrl = UrlUtil.getInstance().getImgurl();
		String sql = "SELECT dy.ID,dy.SEND_ID AS sendId,dy.SEND_TYPE AS sendType,"
				+ "dy.CONTENT,dy.IMG_PATH AS imgPath,"
				+ "dy.DYNAMIC_PLATE_ID as topicId,dy.TITLE,"
				+ "to_char(dy.CREATE_DATE,'yyyy-mm-dd hh24:mi:ss') as createDate,"
				+ "nvl(cus.FNICK,'匿名') as FNICK,?||cus.FPHOTO as FPHOTO,cus.FSEX,"
				+ "GOOD.ID AS isClickGood ,nvl(goodCount.clickGoodCount,0) AS clickGoodCount,"
				+ "con.CONTACTS_ID AS ISFOLLOW,"
				+ "dy.CUS_LOCATION AS location "
				+ "FROM PUB_DYNAMIC dy LEFT JOIN PUB_CUSTOMER cus "
				+ "ON  dy.SEND_ID = to_char(cus.id)LEFT JOIN "//登陆人是否点赞了此动态
				+ "CLICK_GOOD good "
				+ "ON dy.ID = GOOD.FROM_ID AND TO_ID = ? AND GOOD.GOOD_TYPE = ? "
				+ "LEFT JOIN "//此动态点赞人数
				+ "(SELECT COUNT(ID)clickGoodCount,FROM_ID FROM CLICK_GOOD "
				+ "WHERE GOOD_TYPE = ? GROUP BY FROM_ID) goodCount "
				+ "ON goodCount.FROM_ID = dy.ID "
				+ "LEFT JOIN "//登录人是否关注此人
				+ "(SELECT CONTACTS_ID FROM PUB_CONTACTS "
				+ "WHERE relation = ? AND LOGIN_ID = ?) con "
				+ "ON con.CONTACTS_ID = dy.SEND_ID "
				+ "WHERE dy.ID = ? "
				+ "AND nvl(dy.DEL_FLAG,0) = ? ORDER BY createDate DESC ";
		/*String sql = "SELECT dy.ID,dy.SEND_ID AS sendId,dy.SEND_TYPE AS sendType,"
				+ "dy.CONTENT,dy.IMG_PATH AS imgPath,"
				+ "dy.DYNAMIC_PLATE_ID as topicId,dy.TITLE,"
				+ "to_char(dy.CREATE_DATE,'yyyy-mm-dd hh24:mi:ss') as createDate,"
				+ "nvl(cus.FNICK,'匿名') as FNICK,?||cus.FPHOTO as FPHOTO,cus.FSEX,"
				+ "dy.CUS_LOCATION AS location "
				+ "FROM PUB_DYNAMIC dy LEFT JOIN PUB_CUSTOMER cus "
				+ "ON  dy.SEND_ID = to_char(cus.id)"
				+ "WHERE dy.ID = ? "
				+ "AND nvl(dy.DEL_FLAG,0) = ? ORDER BY createDate DESC";*/
		return findOneForJdbc(sql,imgUrl,loginId,3,3,1,loginId,id,0);
	}

}
