package com.ennuova.app.service;

import java.util.List;


import com.ennuova.entity.PubArea;

public interface PubAreaService {

	
	
	//fid为上级id，fgrade为等级（1：省级，2：市级，3：区（县）级）
	public List<PubArea> queryPubArea(long fid,long fgrade);
	
	/**
	 * 根据名字获取城市列表
	 * @Description: TODO
	 * @param @param cname
	 * @param @return   
	 * @return List<PubArea>  
	 * @author felix
	 * @date 2015-12-9
	 */
	public List<PubArea> queryCityByName(String cname);
}
