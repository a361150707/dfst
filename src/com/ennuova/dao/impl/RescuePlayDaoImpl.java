package com.ennuova.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ennuova.base.impl.DaoSupportImpl;
import com.ennuova.dao.RescuePlayDao;
import com.ennuova.entity.RescueItem;
import com.ennuova.entity.RescuePlay;

@Repository("rescuePlayDao")
public class RescuePlayDaoImpl extends DaoSupportImpl<RescuePlay> implements RescuePlayDao{

	@Override
	public List<RescuePlay> queryRescuePlay() {
		List<RescuePlay> playList = this.findAll();
		if(playList.size()>0){
			for (RescuePlay rescuePlay : playList) {
				if(rescuePlay.getRescueItems().size()>0){
					for (RescueItem rescueItem : rescuePlay.getRescueItems()) {
						
					}
				}
			}
		}
		return playList;
	}
	/**
	 * 查看救援的历史详情
	 * @author sududa
	 * @date 2016年7月19日
	 * @param id
	 * @return
	 */
	@Override
	public Map<String, Object> findDetail(String id) {
		String sql = "select r.RESERVE_RESCUE_ID as id,r.RESCUE_PLAY_ID as rescuePlayId,"
				   + "r.RESERVE_STATE as reserveState,"
				   + "r.CARINFO_ID as carinfoId,"
				   + "r.ADDRESS as reserveAddress,r.LONGITUDE,r.LATITUDE,"
				   + "r.FCARNUM as carNum,"
				   + "(to_char(r.CREATE_DATE,'yyyy-mm-dd HH24:mi:ss')) as createDate,"
				   + "(to_char(r.CREATE_DATE,'day')) as week,"
				   + "bu.REALNAME,bu.USERKEY,bu.ID,"
				   + "u.MOBILEPHONE,u.OFFICEPHONE,"
				   + "car.FLINE,car.FVEHICLEMODEL,car.FCARNUM,"
				   + "line.FNAME as lineName,line.S_PICTURE as lineImage,"
				   + "model.FNAME as modelName,"
				   + "cus.FNICK as customerName,cus.FTEL as customerTel"
				   + " from RESERVE_RESCUE r"
				   + " left join T_S_BASE_USER bu"
				   + " on r.USER_ID = bu.ID"
				   + " left join T_S_USER u"
				   + " on r.USER_ID = u.ID"
				   + " left join PUB_CARINFO car"
				   + " on r.CARINFO_ID = car.ID"
				   + " left join PUB_LINE line"
				   + " on car.FLINE = line.ID"
				   + " left join PUB_VEHICLEMODEL model"
				   + " on car.FVEHICLEMODEL = model.ID"
				   + " left join PUB_CUSTOMER cus"
				   + " on r.CUSTOMER_ID = cus.ID"
			       + " where r.RESERVE_RESCUE_ID = ?";
		return findOneForJdbc(sql, id);
	}

}
