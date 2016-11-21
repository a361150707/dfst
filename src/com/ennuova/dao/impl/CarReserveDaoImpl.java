package com.ennuova.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ennuova.base.impl.BaseDAOImpl;
import com.ennuova.dao.CarReserveDao;
import com.ennuova.entity.CarReserveEntity;
import com.ennuova.util.StringUtil;
import com.ennuova.util.UrlUtil;

/**
 * 车辆预约dao 的实现类
 * @author sududa
 * @date 2016年9月13日
 *
 */
@Repository("carReserveDao")
public class CarReserveDaoImpl extends BaseDAOImpl<CarReserveEntity> implements
		CarReserveDao {

	/**
	 * 保存预约记录
	 *@author sududa
	 *@date 2016年9月13日
	 * @param carReserveEntity
	 * @return
	 */
	@Override
	public CarReserveEntity saveCarReserve(CarReserveEntity carReserveEntity) {
		return this.save(carReserveEntity);
	}

	@Override
	public Map<String, Object> findReserveStateByTypeAndOrder(Integer reserveType, int orderNum) {
		String sql = "select ID from RESERVE_STATE "
				   + " where STATUS_TYPE = ? and ORDER_NUM = ?";
		return findOneForJdbc(sql, reserveType,orderNum);
	}

	/**
	 * 根据客户id查找相关信息，用于预约的初始化
	 *@author sududa
	 *@date 2016年9月14日
	 * @param cusId
	 * @return
	 */
	@Override
	public Map<String, Object> findInitMap(String cusId) {
		String imgUrl = UrlUtil.getInstance().getImgurl();
		String sql = "select store.FNAME,?||store.FSYPICTURE as FSYPICTURE,"
				   + "?||store.FLOGO as FLOGO,store.FADDR,"
				   + "?||store.FBACKPC as FBACKPC"
				   + " from PUB_STORE store"
				   + " where store.ID = "
				   + " (select bdstore.FSTORE"
				   + " from PUB_BDSTORE bdstore"
				   + " where bdstore.FCUSTOMER = ? and bdstore.FDEFAULT = 1)";
		return findOneForJdbc(sql, imgUrl, imgUrl, imgUrl, cusId);
	}

	/**
	 * 根据预约id取消预约
	 *@author sududa
	 *@date 2016年9月14日
	 * @param reserveId
	 * @return
	 */
	@Override
	public Integer doCancleReserveByCusIdAndReserveId(String reserveId, Integer updateState) {
		String sql = "update CAR_RESERVE r set r.RESERVE_STATE = ?"
				   + " where r.ID = ?"
				   + " and r.RESERVE_STATE =?";//表示只有待审核的预约状态才可以取消
		return executeSql(sql, updateState,reserveId,0);
	}

	/**
	 * 根据客户id和预约类型查询客户的预约历史记录
	 *@author sududa
	 *@date 2016年9月18日
	 * @param cusId
	 * @param reserveType
	 * @param rows 
	 * @param page 
	 * @return
	 */
	@Override
	public List<Map<String, Object>> findListByCusIdAndReserveType(String cusId, String reserveType, Integer page, Integer rows) {
		String imgUrl = UrlUtil.getInstance().getImgurl();
		StringBuilder sql = new StringBuilder("select reserve.ID,"
				          + "reserve.FCAR_ID as fcarId,reserve.RESERVE_TYPE as reserveType,"
				          + "(to_char(reserve.RESERVE_DATE,'yyyy-mm-dd HH24:mi:ss')) as reserveDate,reserve.RESERVE_STATE as reserveState,"
				          + "reserve.IS_HOME isHome,reserve.HOME_ADDRESS as homeAddress,"
				          + "reserve.REMARKS,reserve.USER_ID as userId,reserve.CUS_NAME as cusName,"
				          + "reserve.CUS_TEL as custel,reserve.CAR_NUMBER as carNumber,reserve.CAR_MODEL as carModel,"
				          + "baseUser.REALNAME,"
				          + "tu.MOBILEPHONE,"
				          + "ro.ROLENAME,"
				          + "?||line.S_PICTURE as linePicture"
				          + " from CAR_RESERVE reserve"
				          + " left join T_S_BASE_USER baseUser"
				          + " on reserve.USER_ID = baseUser.ID"
				          + " left join T_S_USER tu"
				          + " on reserve.USER_ID = tu.ID"
				          + " left join T_S_ROLE_USER ru"
				          + " on reserve.USER_ID = ru.USERID"
				          + " left join T_S_ROLE ro"
				          + " on ru.ROLEID = ro.ID"
				          + " left join PUB_CARINFO car"
				          + " on reserve.FCAR_ID = car.ID"
				          + " left join PUB_LINE line"
				          + " on car.FLINE = line.ID"
				          + " where reserve.FCUS_ID = ? and reserve.USER_DEL_FLAG=0");
		if(StringUtil.isNotEmpty(reserveType)){
			sql.append(" and reserve.RESERVE_TYPE = ?");
			sql.append(" order by reserve.CREATE_DATE desc");
			return findForJdbcPage(sql.toString(), page, rows, imgUrl, cusId,reserveType);
		}else{
			sql.append(" order by reserve.CREATE_DATE desc");
			return findForJdbcPage(sql.toString(), page, rows, imgUrl, cusId);
		}
	}

	/**
	 * 根据客户id查找对应的管家的信息
	 *@author sududa
	 *@date 2016年9月18日
	 * @param cusId
	 * @return
	 */
	@Override
	public Map<String, Object> findUserByCusId(String userId) {
		String sql = "select bUser.REALNAME,"
				   + " eUser.MOBILEPHONE"
				   + " from T_S_BASE_USER bUser"
				   + " left join T_S_USER eUser"
				   + " on bUser.ID = eUser.ID"
				   + " where bUser.ID = ?";
		return findOneForJdbc(sql, userId);
	}

	/**
	 * 根据客户id查找其下的sa列表
	 *@author sududa
	 *@date 2016年9月18日
	 * @param cusId
	 * @return
	 */
	@Override
	public List<Map<String, Object>> findsaList(String cusId, String roleType, Integer page, Integer rows) {
		StringBuilder sql = new StringBuilder();
		/*sql.append("select bUser.ID,bUser.REALNAME,"
				   + " eUser.MOBILEPHONE,eUser.signaturefile,uRole.ROLENAME"
				   + " from T_S_BASE_USER bUser"
				   + " left join T_S_USER eUser"
				   + " on bUser.ID = eUser.ID"
				   + " left join T_S_ROLE_USER roleUser"
				   + " on bUser.ID = roleUser.USERID"
				   + " left join T_S_ROLE uRole"
				   + " on uRole.ID = roleUser.ROLEID"
				   + " where bUser.ID in"
				   + " (select uBdStore.FUSER"
				   + " from USER_BDSTORE uBdStore"
				   + " where uBdStore.FSTORE = "
				   + " (select bdstore.FSTORE"
				   + " from PUB_BDSTORE bdstore"
				   + " where bdstore.FCUSTOMER = ? and bdstore.FDEFAULT = 1)"
				   + " ) and bUser.staff_status=0");*/
		sql.append("select bUser.ID,bUser.REALNAME,"
				   + " eUser.MOBILEPHONE,eUser.signaturefile,uRole.ROLENAME"
				   + " from T_S_BASE_USER bUser"
				   + " left join T_S_USER eUser"
				   + " on bUser.ID = eUser.ID"
				   + " left join T_S_ROLE_USER roleUser"
				   + " on bUser.ID = roleUser.USERID"
				   + " left join T_S_ROLE uRole"
				   + " on uRole.ID = roleUser.ROLEID"
				   + " where eUser.FSTOREID ="
				   + " (select bdstore.FSTORE"
				   + " from PUB_BDSTORE bdstore"
				   + " where bdstore.FCUSTOMER = ? and bdstore.FDEFAULT = 1)"
				   + " and bUser.staff_status=0");
		if(StringUtil.isNotEmpty(roleType)){
			sql.append(" and roleUser.ROLEID = (select uRole.ID from T_S_ROLE uRole"
					 + " where uRole.ROLECODE = ?)");
			return findForJdbcParam(sql.toString(), page, rows, cusId,roleType);
		}else{
			sql.append(" and roleUser.ROLEID in (select uRole.ID from T_S_ROLE uRole"
					 + " where uRole.ROLECODE = ? or uRole.ROLECODE = ? or uRole.ROLECODE = ?)");
			return findForJdbcParam(sql.toString(), page, rows, cusId,"SA","CRM","SC");
		}
	}

	/**
	 * 根据预约id查找预约的订单详情记录
	 *@author sududa
	 *@date 2016年9月19日
	 * @param reserveId
	 * @return
	 */
	@Override
	public List<Map<String, Object>> findReserveOrderList(String reserveId, String reserveType) {
		String sql = "select sr.ID,sr.RESERVE_STATE as reserveState,(to_char(sr.CREATE_DATE,'yyyy-mm-dd HH24:mi:ss')) as createDate,"
				   + "sr.REMARKS,"
				   + "state.NAME,state.REMARK as stateRemark"
				   + " from RESERVE_STATUS_CHANGE_RECORD sr"
				   + " left join RESERVE_STATE state"
				   + " on sr.RESERVE_STATE = state.ID"
				   + " where sr.RESERVE_ID = ?"
				   + " and (state.STATUS_TYPE = ? or state.STATUS_TYPE =?) order by sr.create_date desc";
		return findForJdbc(sql, reserveId,reserveType,0);
	}

	/**
	 * 根据门店id查询服务顾问(SA),管家(CRM),销售顾问(SC)列表
	 *@author sududa
	 *@date 2016年9月23日
	 * @param params
	 * @param response
	 * @throws Exception
	 */
	@Override
	public List<Map<String, Object>> findUserListByStoreIdAndroleType(String storeId, String roleType, Integer page,
			Integer rows) {
		StringBuilder sql = new StringBuilder();
		/*sql.append("select bUser.ID,bUser.REALNAME,"
				   + " eUser.MOBILEPHONE,eUser.signaturefile,uRole.ROLENAME"
				   + " from T_S_BASE_USER bUser"
				   + " left join T_S_USER eUser"
				   + " on bUser.ID = eUser.ID"
				   + " left join T_S_ROLE_USER roleUser"
				   + " on bUser.ID = roleUser.USERID"
				   + " left join T_S_ROLE uRole"
				   + " on uRole.ID = roleUser.ROLEID"
				   + " where bUser.ID in"
				   + " (select uBdStore.FUSER"
				   + " from USER_BDSTORE uBdStore"
				   + " where uBdStore.FSTORE = ?"
				   + " )");*/
		sql.append("select bUser.ID,bUser.REALNAME,"
				   + " eUser.MOBILEPHONE,eUser.signaturefile,uRole.ROLENAME"
				   + " from T_S_BASE_USER bUser"
				   + " left join T_S_USER eUser"
				   + " on bUser.ID = eUser.ID"
				   + " left join T_S_ROLE_USER roleUser"
				   + " on bUser.ID = roleUser.USERID"
				   + " left join T_S_ROLE uRole"
				   + " on uRole.ID = roleUser.ROLEID"
				   + " where eUser.FSTOREID =?"
				   + "");
		if(StringUtil.isNotEmpty(roleType)){
			sql.append(" and roleUser.ROLEID = (select uRole.ID from T_S_ROLE uRole"
					+ " where uRole.ROLECODE = ?)");
			return findForJdbcParam(sql.toString(), page, rows, storeId,roleType);
		}else{
			sql.append("and roleUser.ROLEID = (select uRole.ID from T_S_ROLE uRole"
					+ " where uRole.ROLECODE = ? or uRole.ROLECODE = ? or uRole.ROLECODE = ?)");
			System.out.println(sql);
			return findForJdbcParam(sql.toString(), page, rows, storeId,"SA","CRM","SC");
		}
	}
	@Override
	public Integer doDelete(String reserveId, Integer delFlag) {
		String sql = "update CAR_RESERVE r set r.USER_DEL_FLAG = ?"
				   + " where r.ID = ?";
		return executeSql(sql, delFlag,reserveId);
	}

	/**
	 * 根据预约id和预约状态查找预约的记录
	 * @author sududa
	 * @date 2016年11月8日
	 */
	@Override
	public Map<String, Object> findByReserveIdAndState(String reserveId, Integer updateState) {
		String sql = "select r.FCUS_ID"
				   + " from CAR_RESERVE r"
				   + " where r.ID = ? and r.RESERVE_STATE = ?";
		return findOneForJdbc(sql, reserveId,updateState);
	}

}
