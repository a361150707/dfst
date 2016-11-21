package com.ennuova.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ennuova.base.impl.DaoSupportImpl;
import com.ennuova.dao.ClickGoodDao;
import com.ennuova.entity.ClickGoodEntity;
import com.ennuova.util.DButil;
import com.ennuova.util.UrlUtil;
/**点赞dao实现
 * @author 李智辉
 * @time 2016-7-27
 */
@Repository
public class ClickGoodDaoImpl extends DaoSupportImpl<ClickGoodEntity> implements
		ClickGoodDao {

	@Override
	public List<Long> isClickGood(List<Long> idLists, Long userId,int type) {
		String isListStr = idLists.toString();
		String idsStr = isListStr.substring(1,isListStr.length()-1);
		List<Long> list = new ArrayList<Long>();
		
		Connection conn = null;
		PreparedStatement ps = null;
		conn = getSessionFactory().getCurrentSession().connection();
		ResultSet rs = null;
		
		String sql = "select FROM_ID from click_good where TO_ID = "+userId+" and GOOD_TYPE="+type+" and FROM_ID in("+idsStr+")";
		System.out.println(sql);
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()){
				Long id = rs.getLong("FROM_ID");
				list.add(id);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return list;
		}finally{
			DButil.closeDb(conn, ps, rs);
		}
		return list;
	}

	@Override
	public Long userIsClickGood(Long id, Long userId, int type) {
		Long rsNum = 0L;
		
		Connection conn = null;
		PreparedStatement ps = null;
		conn = getSessionFactory().getCurrentSession().connection();
		ResultSet rs = null;
		
		String sql = "select ID from click_good where TO_ID = "+userId+" and GOOD_TYPE="+type+" and FROM_ID = "+id;
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()){
				rsNum = rs.getLong("ID");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return rsNum;
		}finally{
			DButil.closeDb(conn, ps, rs);
		}
		return rsNum;
	}
	
	/**
	 * 根据动态ID，获取点赞人的头像
	 * @author 陈晓珊
	 * @date 2016年10月21日
	 * @param id
	 * @return
	 */
	@Override
	public List<Map<String, Object>> getClickGoodPhoto(String id,String type) {
		String imgUrl = UrlUtil.getInstance().getImgurl();
		String sql = "SELECT CUS.ID,?||CUS.FPHOTO as FPHOTO,"
				+ "to_char(GOOD.CREATE_DATE,'yyyy-mm-dd hh24:mi:ss') AS CREATEDATE "
				+ "FROM CLICK_GOOD GOOD LEFT JOIN PUB_CUSTOMER CUS "
				+ "ON GOOD. TO_ID = CUS.ID WHERE GOOD.FROM_ID = ? "
				+ "AND GOOD.GOOD_TYPE = ? ORDER BY GOOD.CREATE_DATE";
		return findForJdbcPage(sql,1, 6,imgUrl,id,type);
	}

	/**
	 * 根据动态ID,获取点赞的人数
	 * @author 陈晓珊
	 * @date 2016年10月26日
	 * @param id
	 * @return
	 */
	@Override
	public Map<String, Object> doClickGoodCount(String id,String type) {
		String sql = "SELECT COUNT(GOOD.ID)clickGoodCount "
				   + "FROM CLICK_GOOD GOOD WHERE GOOD.FROM_ID = ? and GOOD_TYPE = ?";
		return findOneForJdbc(sql, id,type);
	}

	
	/**
	 * 根据动态ID，获取点赞人的列表
	 * @author 陈晓珊
	 * @date 2016年10月21日
	 * @param id
	 * @return
	 */
	@Override
	public List<Map<String, Object>> doClickGoodList(String id,String type) {
		String imgUrl = UrlUtil.getInstance().getImgurl();
		String sql = "SELECT cus.ID,?||cus.FPHOTO as FPHOTO,"
				+ "nvl(cus.FNICK,'匿名') as FNICK,cus.FSEX,"
				+ "nvl(cus.CUS_INTEREST,'未设置兴趣标签') as cusInterest,"
				+ "nvl(line.FNAME,'未绑定车辆') as lineName,"
				+ "to_char(box.FJHTIME,'yyyy-mm-dd hh24:mi:ss') as jhTime,"
				+ "to_char(GOOD.CREATE_DATE,'yyyy-mm-dd hh24:mi:ss') AS CREATEDATE "
				+ "FROM CLICK_GOOD GOOD LEFT JOIN PUB_CUSTOMER CUS ON GOOD. TO_ID = CUS.ID "
				+ " LEFT JOIN PUB_CARINFO car ON cus.ID = car.FCUSTOMER "
				+ "AND car.FDEFAULT = ? LEFT JOIN PUB_LINE line ON car.FLINE = line.ID LEFT "
				+ "JOIN PUB_BOX box on car.ID = box.FCLID "
				+ "WHERE GOOD.FROM_ID = ? AND GOOD.GOOD_TYPE = ? "
				+ " ORDER BY GOOD.CREATE_DATE";
		return findForJdbc(sql,imgUrl,1, id,type);
	}

	/**
	 * 根据登陆ID，查找是否点赞过某条动态
	 * @author 陈晓珊
	 * @date 2016年10月26日
	 * @param id
	 * @return
	 */
	@Override
	public Map<String, Object> isClickGood(String loginId, String id,String type) {
		String sql = "SELECT ID FROM CLICK_GOOD WHERE FROM_ID = ? AND TO_ID = ? AND GOOD_TYPE = ?";
		return findOneForJdbc(sql,id,loginId,type);
	}

	/**
	 * 点赞
	 * @author 陈晓珊
	 * @date 2016年11月1日
	 * @param clickGood
	 * @return
	 */
	@Override
	public ClickGoodEntity doClickGood(ClickGoodEntity clickGood) {
		return save(clickGood);
	}

	/**
	 * 取消点赞
	 * @author 陈晓珊
	 * @date 2016年11月1日
	 * @param fromId
	 * @param toId
	 * @param goodType
	 * @return
	 */
	@Override
	public Integer cancelClickGood(String fromId, String toId, String goodType) {
		String sql = "DELETE FROM CLICK_GOOD good "
				+ "WHERE good.FROM_ID = ? "
				+ "AND good.TO_ID = ? "
				+ "AND good.GOOD_TYPE = ?";
		return executeSql(sql, fromId,toId,goodType);
	}
}
