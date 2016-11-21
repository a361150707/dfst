package com.ennuova.app.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ennuova.app.service.PubStoreService;
import com.ennuova.dao.PubStoreDao;
import com.ennuova.entity.PubStore;
import com.ennuova.util.UrlUtil;

@Service("pubStoreService")
public class PubStoreServiceImpl implements PubStoreService {

	@Resource
	private PubStoreDao pubStoreDao;

	/**
	 * 获取id对应的门店信息
	 */
	@Override
	public PubStore queryStoreById(long id) {
		PubStore ps=new PubStore();
		try {
			ps=this.pubStoreDao.getPubStorebyId(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ps;
	}

	@Override
	public boolean sava(PubStore ps) {
		try {
			this.pubStoreDao.save(ps);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public List<PubStore> querySomeStore(Long cid) {
		return pubStoreDao.querySomeStore(cid);
	}

	@Override
	public List<PubStore> queryMyStore(Long cusId) {
		// TODO Auto-generated method stub
		return pubStoreDao.queryMyStore(cusId);
	}

	@Override
	public List<PubStore> getPubStoreByAddress(String realTimeAddress) {
		List<PubStore> pubStores = this.pubStoreDao.getPubStoreByAddress(realTimeAddress);
		return pubStores;
	}

	@Override
	public List<Map<String, Object>> getPubStoreAll() {
		List<Map<String, Object>> list = pubStoreDao.getPubAllStore();
		String imgUrl = UrlUtil.getInstance().getImgurl();
		try {
			for (Map<String, Object> map : list) {
				String headImg = (String) map.get("FSYPICTURE");
				if(headImg!=""){
					map.put("FSYPICTURE", imgUrl+headImg);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	
	

}
