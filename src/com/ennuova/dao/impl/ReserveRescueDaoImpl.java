package com.ennuova.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ennuova.base.impl.BaseDAOImpl;
import com.ennuova.dao.ReserveRescueDao;
import com.ennuova.entity.RescueItem;
import com.ennuova.entity.ReserveRescue;
@Repository("reserveRescueDao")
public class ReserveRescueDaoImpl extends BaseDAOImpl<ReserveRescue> implements
		ReserveRescueDao {

	@Override
	public List<ReserveRescue> queryReserveRescue(long cusId) {
		List<ReserveRescue> reserveRescues = getList("customerId", cusId);
		if(reserveRescues.size()>0){
			for (ReserveRescue reserveRescue : reserveRescues) {
				reserveRescue.getRescuePlay().setRescueItems(null);
				/*if(reserveRescue.getRescueItemes().size()>0){
					for (RescueItem item : reserveRescue.getRescueItemes()) {
						item.setRescuePlays(null);
						item.setReserveRescues(null);
					}
				}*/
			}
		}
		return reserveRescues;
	}

	@Override
	public ReserveRescue getReserveRescue(long reserveRescueId) {
		ReserveRescue reserveRescue = getById(reserveRescueId);
		if(reserveRescue != null){
			reserveRescue.getRescuePlay().setRescueItems(null);
			/*if(reserveRescue.getRescueItemes().size()>0){
				for (RescueItem item : reserveRescue.getRescueItemes()) {
					item.setRescuePlays(null);
					item.setReserveRescues(null);
				}
			}*/
		}
		return reserveRescue;
	}

}
