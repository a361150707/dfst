package com.ennuova.app.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ennuova.app.service.CarReserveService;
import com.ennuova.dao.CarReserveDao;
import com.ennuova.dao.ReserveStateChangeRecordDao;
import com.ennuova.dao.StaffChangeRecordDao;
import com.ennuova.entity.CarReserveEntity;
import com.ennuova.entity.ReserveStatusChangeRecordEntity;
import com.ennuova.entity.StaffChangeRecordEntity;
import com.ennuova.util.DateUtil;
import com.ennuova.util.StringUtil;
import com.ennuova.util.UrlUtil;

/**
 * 车辆预约的实现类
 * @author sududa
 * @date 2016年9月13日
 *
 */
@Service("carReserveService")
public class CarReserveServiceImpl implements CarReserveService {
	/**
	 * 预约记录的dao
	 */
	@Resource
	private CarReserveDao carReserveDao;
	
	/**
	 * 状态变更记录的dao
	 */
	@Resource
	private ReserveStateChangeRecordDao reserveStateChangeRecordDao;
	
	/**
	 * 员工变更记录的dao
	 */
	@Resource
	private StaffChangeRecordDao staffChangeRecordDao;

	/**
	 * 保存车辆预约的信息,预约变更记录，员工变更记录
	 *@author sududa
	 *@date 2016年9月13日
	 * @param carReserveEntity
	 * @return
	 */
	@Override
	public Integer saveReserveWithStateAndStaff(CarReserveEntity carReserveEntity) {
		Date createDate = DateUtil.getCrruentTimestamp();
		/*Map<String, Object> reserveStateMap = carReserveDao.findReserveStateByTypeAndOrder(carReserveEntity.getReserveType(),1);
		if(StringUtil.isNotEmpty(reserveStateMap)){
			String getId = reserveStateMap.get("ID")+"";
			if(StringUtil.isNotEmpty(getId)){
				carReserveEntity.setReserveState(Integer.valueOf(getId));
			}
		}*/
		carReserveEntity.setUserDelFlag(0);
		carReserveEntity.setReserveState(0);//设置预约状态为待审核
		carReserveEntity.setCreateDate(createDate);
		CarReserveEntity reserveEntity = carReserveDao.saveCarReserve(carReserveEntity);
		if(StringUtil.isNotEmpty(reserveEntity)){
			StaffChangeRecordEntity staffChangeRecordEntity = new StaffChangeRecordEntity();
			staffChangeRecordEntity.setReserveId(reserveEntity.getId());
			staffChangeRecordEntity.setFuserId(carReserveEntity.getUserId());
			staffChangeRecordEntity.setCreateDate(createDate);
			staffChangeRecordEntity.setReserveState(carReserveEntity.getReserveState());
			staffChangeRecordEntity.setRemarks(carReserveEntity.getRemarks());
			StaffChangeRecordEntity staffChangeResultEntity = staffChangeRecordDao.saveStaffChangeRecoed(staffChangeRecordEntity);
			if(StringUtil.isNotEmpty(staffChangeResultEntity)){
				ReserveStatusChangeRecordEntity reserveStatusChangeRecordEntity = new ReserveStatusChangeRecordEntity();
				reserveStatusChangeRecordEntity.setReserveId(reserveEntity.getId());
				reserveStatusChangeRecordEntity.setReserveState(carReserveEntity.getReserveState());
				reserveStatusChangeRecordEntity.setCreateDate(createDate);
				reserveStatusChangeRecordEntity.setRemarks(carReserveEntity.getRemarks());
				ReserveStatusChangeRecordEntity resultStateChangeEntity = reserveStateChangeRecordDao.save(reserveStatusChangeRecordEntity);
				if(StringUtil.isNotEmpty(resultStateChangeEntity)){
					return reserveEntity.getId();
				}
			}
		}
		return 0;
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
		return carReserveDao.findInitMap(cusId);
	}

	/**
	 * 根据客户id和预约的id取消预约记录
	 *@author sududa
	 *@date 2016年9月14日
	 * @param cusId
	 * @param reserveId
	 * @param remarks 
	 * @param updateState 
	 * @return
	 */
	@Override
	public Integer doCancleReserve(String cusId, String reserveId, String remarks, Integer updateState) {
		Map<String, Object> resultMap = carReserveDao.findByReserveIdAndState(reserveId,updateState);
		if(StringUtil.isNotEmpty(resultMap)){//表示已经取消了
			return 1;
		}else{//表示还没有取消的操作
			Integer result = carReserveDao.doCancleReserveByCusIdAndReserveId(reserveId,updateState);
			if(result > 0){
				ReserveStatusChangeRecordEntity reserveStatusChangeRecordEntity = new ReserveStatusChangeRecordEntity();
				reserveStatusChangeRecordEntity.setReserveId(Integer.valueOf(reserveId));
				reserveStatusChangeRecordEntity.setReserveState(updateState);
				Date createDate = DateUtil.getCrruentTimestamp();
				reserveStatusChangeRecordEntity.setCreateDate(createDate);
				reserveStatusChangeRecordEntity.setRemarks(remarks);
				ReserveStatusChangeRecordEntity resultEntity = reserveStateChangeRecordDao.save(reserveStatusChangeRecordEntity);
				if(StringUtil.isNotEmpty(resultEntity)){
					return 1;
				}
			}
		}
		return 0;
	}

	/**
	 * 根据客户id和预约类型查询客户的预约历史记录
	 *@author sududa
	 *@date 2016年9月18日
	 * @param cusId
	 * @param reserveType
	 * @return
	 */
	@Override
	public List<Map<String, Object>> findListByCusIdAndReserveType(String cusId, String reserveType, Integer page, Integer rows) {
		return carReserveDao.findListByCusIdAndReserveType(cusId,reserveType,page,rows);
	}

	/**
	 * 根据管家id查找对应的管家的信息
	 *@author sududa
	 *@date 2016年9月18日
	 * @param cusId
	 * @return
	 */
	@Override
	public Map<String, Object> findUserByCusId(String userId) {
		return carReserveDao.findUserByCusId(userId);
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
		List<Map<String, Object>> list = carReserveDao.findsaList(cusId,roleType,page,rows);
		String imgUrl = UrlUtil.getInstance().getImgurl();
		try {
			for (Map<String, Object> map : list) {
				String headImg = (String) map.get("SIGNATUREFILE");
				if(headImg!=""){
					map.put("SIGNATUREFILE", imgUrl+headImg);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
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
		return carReserveDao.findReserveOrderList(reserveId,reserveType);
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
		List<Map<String, Object>> list = carReserveDao.findUserListByStoreIdAndroleType(storeId,roleType,page,rows);
		String imgUrl = UrlUtil.getInstance().getImgurl();
		try {
			for (Map<String, Object> map : list) {
				String headImg = (String) map.get("SIGNATUREFILE");
				if(headImg!=""){
					map.put("SIGNATUREFILE", imgUrl+headImg);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public Integer doDelete(String reserveId, Integer delFlag) {
		// TODO Auto-generated method stub
		return carReserveDao.doDelete(reserveId, delFlag);
	}

}
