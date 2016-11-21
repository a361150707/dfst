package com.ennuova.app.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ennuova.app.service.CnDzwlService;
import com.ennuova.dao.CnDzwlDao;
import com.ennuova.entity.CnDzwl;
import com.ennuova.entity.dzwlview.CnDzwlView;
import com.ennuova.push.InnovPush;
import com.ennuova.util.Constant;

/**电子围栏相关服务
 * @author 李智辉 
 * 2015-12-15下午4:03:20
 */
@Service("cnDzwlService")
public class CnDzwlServiceImpl implements CnDzwlService {
	
	@Resource
	private CnDzwlDao cnDzwlDao;
	
	
	@Override
	public boolean addDzwl(CnDzwl cnDzwl) {
		boolean success = false;
		try {
			int lineNum = this.cnDzwlDao.saveFence(cnDzwl);
			if (lineNum>0) {
				success = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return success;
	}

	@Override
	public boolean setDzwl(Long fclid, Long id,int fsrcs) {
		boolean success = false;
		try {
			int lineNum = this.cnDzwlDao.setFence(fclid, id, fsrcs);
			if (lineNum>0) {
				success = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return success;
	}

	@Override
	public List<CnDzwl> getCnDzwlList(Long fclid) {
		List<CnDzwl> cnDzwls = new ArrayList<CnDzwl>();
		CnDzwl cnDzwl = new CnDzwl();
		List<CnDzwl> cnDzwlList = new ArrayList<CnDzwl>();
		try {
			cnDzwls = this.cnDzwlDao.getCnDzwlList(fclid);
			for (CnDzwl cnDzwlItem : cnDzwls) {
				if (cnDzwlItem.getFeffect()==1) {
					cnDzwl = cnDzwlItem;
					cnDzwls.remove(cnDzwlItem);
					break;
				}
			}
			if (cnDzwl.getFeffect()==1) {
				cnDzwlList.add(cnDzwl);
			}
			
			for (CnDzwl cnDzwl1 : cnDzwls) {
				cnDzwlList.add(cnDzwl1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cnDzwlList;
	}

	@Override
	public boolean deleteFence(Long fid) {
		boolean success = false;
		try {
			this.cnDzwlDao.delete(fid);
			success = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return success;
	}

	@Override
	public boolean cancelFence(Long fid) {
		boolean success = false;
		try {
			success = this.cnDzwlDao.cancelFence(fid);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return success;
	}
 
	/**
	 * 电子围栏
	 */
	public void listenAndPushDzwl() {
		try {
			List<CnDzwlView> cnDzwlViews = this.cnDzwlDao.fenceListen();
			for (CnDzwlView cnDzwlView : cnDzwlViews) {
				InnovPush.testSendPush(cnDzwlView.getAlias(), cnDzwlView.getMessage(), 3,Constant.MASRER_CZ_SECRET,Constant.APP_CZ_KEY);
				System.out.println("车辆ID--》"+cnDzwlView.getFclid());
				System.out.println("消息--》"+cnDzwlView.getMessage());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 报警提醒
	 */
	public void listenAndPushOdbMessage() {
		try {
			List<CnDzwlView> cnDzwlViews = this.cnDzwlDao.falarmListen();
			for (CnDzwlView cnDzwlView : cnDzwlViews) {
				InnovPush.testSendPush(cnDzwlView.getAlias(), cnDzwlView.getMessage(), 3,Constant.MASRER_CZ_SECRET,Constant.APP_CZ_KEY);
				System.out.println("车辆ID--》"+cnDzwlView.getFclid());
				System.out.println("消息--》"+cnDzwlView.getMessage());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		
	}
}
