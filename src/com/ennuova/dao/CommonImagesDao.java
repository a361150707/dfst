package com.ennuova.dao;

import java.util.List;
import java.util.Map;

import com.ennuova.base.DaoSupport;
import com.ennuova.entity.CommonImages;

public interface CommonImagesDao extends DaoSupport<CommonImages>{

	/**
	 * 
	* @Title: CommonImagesDao.java 
	* @Package com.ennuova.dao 
	* @Description: 获取车系/车型图片(描述) tp为鉴别字符（1来源车系2来源车型）,tpid指车系/车型id
	* @author felix
	* @date 2016年4月15日
	* @version V1.0
	 */
	List<CommonImages> queryImages(int tp,Long tpId);
	/**获取车系/车型图片 数量(描述) tp为鉴别字符（1来源车系2来源车型）,tpid指车系/车型id
	 * @param type
	 * @param id
	 * @return 图片数量
	 */
	List<Map<String, Object>> getImgNumByLine(int type,Long id);
	/**获取车系/车型图片 数量(描述) tp为鉴别字符（1来源车系2来源车型）,tpid指车系/车型id
	 * @param type
	 * @param id
	 * @return 图片数量
	 */
	Long getImgNum(int type,Long id);
}
