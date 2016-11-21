package com.ennuova.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ennuova.base.impl.BaseDAOImpl;
import com.ennuova.dao.CarLocationDao;
import com.ennuova.entity.CarLocation;
import com.ennuova.util.StringUtil;
import com.ennuova.util.UrlUtil;

@Repository
public class CarLocationDaoImpl extends BaseDAOImpl<CarLocation> implements
		CarLocationDao {

	/**
	 * 查询除了自己的车以外的附近的车
	 * @author sududa
	 * @param rows 
	 * @param page 
	 * @param cusId 
	 * @date 2016年11月4日
	 */
	@Override
	public List<Map<String, Object>> findListBynotSelf(Integer page, Integer rows,String cusId) {
		String imgUrl = UrlUtil.getInstance().getImgurl();
		String sql = "select cl.ID,cl.CUS_ID as cusId,cl.LNG,"
				   + "cl.LAT,?||cl.CUS_PHOTO as cusPhoto,"
				   + "nvl(cl.CUS_INTEREST,'未设置兴趣标签') as cusInrerest,"
				   + "nvl(cl.CUS_NICK,'匿名') as cusNick,"
				   + "cl.LINE_NAME as lineName,nvl(cl.TOTAL_MILEAGE,'--') as totalMileage,"
				   + "nvl(cl.TODAY_MILEAGE,'--') as todayMileage,"
				   + "nvl(cl.TODAY_OIL,'--') as todayOil,cl.SEX"
				   + "nvl(cl.AVERAGE_OIL,'--') as averageOil"
				   + " from CAR_LOCATION cl"
				   + " where 1=1 and cl.CUS_ID != ?";
		return findForJdbcPage(sql, page, rows, imgUrl, cusId);
	}

	/**
	 * 根据客户的id查询该客户是否有存入了车的位置
	 * @author sududa
	 * @param cusId
	 * @date 2016年11月4日
	 */
	@Override
	public Map<String, Object> findByCusId(String cusId) {
		String sql = "select cl.ID,"
				   + "nvl(cl.TOTAL_MILEAGE,'--') as totalMileage,"
				   + "nvl(cl.TODAY_MILEAGE,'--') as todayMileage,"
				   + "nvl(cl.TODAY_OIL,'--') as todayOil,"
				   + "nvl(cl.AVERAGE_OIL,'--') as averageOil"
				   + " from CAR_LOCATION cl"
				   + " where cl.CUS_ID = ?";
		return findOneForJdbc(sql, cusId);
	}

	/**
	 * 根据客户的id清除客户默认车辆的位置信息
	 * @author sududa
	 * @date 2016年11月7日
	 */
	@Override
	public Integer doDelByCusId(String cusId) {
		String sql = "delete from CAR_LOCATION cl"
				   + " where cl.CUS_ID = ?";
		return executeSql(sql, cusId);
	}

	/**
	 * 查询附近的人列表
	 * @author sududa
	 * @date 2016年11月8日
	 */
	@Override
	public List<Map<String, Object>> findListBynotSelfWithParams(Integer page, Integer rows, CarLocation carLocation) {
		String imgUrl = UrlUtil.getInstance().getImgurl();
		StringBuilder sql = new StringBuilder();
		sql.append("select cl.ID,cl.CUS_ID as cusId,cl.LNG,"
				   + "cl.LAT,?||cl.CUS_PHOTO as cusPhoto,"
				   + "nvl(cl.CUS_INTEREST,'未设置兴趣标签') as cusInrerest,"
				   + "nvl(cl.CUS_NICK,'匿名') as cusNick,"
				   + "cl.LINE_NAME as lineName,nvl(cl.TOTAL_MILEAGE,'--') as totalMileage,"
				   + "nvl(cl.TODAY_MILEAGE,'--') as todayMileage,"
				   + "nvl(cl.TODAY_OIL,'--') as todayOil,cl.SEX,"
				   + "nvl(cl.AVERAGE_OIL,'--') as averageOil"
				   + " from CAR_LOCATION cl"
				   + " where 1=1 and cl.CUS_ID != ?");
		if(StringUtil.isNotEmpty(carLocation.getLineName())){
			sql.append(" and cl.LINE_NAME like '%"+carLocation.getLineName()+"%'");
		}
		if(StringUtil.isNotEmpty(carLocation.getSex())){
			sql.append(" and cl.SEX = '"+carLocation.getSex()+"'");
		}
		return findForJdbcPage(sql.toString(), page, rows, imgUrl, carLocation.getCusId());
	}



}
