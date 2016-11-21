package com.ennuova.dao;

import java.util.List;
import java.util.Map;

import com.ennuova.base.DaoSupport;
import com.ennuova.entity.CnDiagsoft;
import com.ennuova.entity.PubBox;
import com.ennuova.entity.PubCarinfo;

public interface PubBoxDao extends DaoSupport<PubBox>{

	PubBox findByFxlh(String fxlh);
	public List<PubBox> queryMyBox(long cusId);
	long checkBox(String fxlh);
	List<PubCarinfo> queryNoBindBoxCar(long cusId);
	boolean addBindBox(long boxid,long fclid,String pwd);
	int jiebangBox(long boxId);
	int setDefBox(long cusId,long clId);
	//根据盒子信息查询车型对账表
	CnDiagsoft getCnDiagsoft(PubBox pubBox);
	/**根据车辆查询盒子信息
	 * @param fclid
	 * @return PubBox
	 */
	PubBox getPubBoxByFclid(Long fclid);
	/**
	 * 通过客户的id查看盒子的信息
	 * @author sududa
	 * @date 2016-10-28
	 * @param loginId
	 * @return
	 */
	Map<String, Object> findByCusId(String loginId);
}
