package com.ennuova.app.service;

import java.util.List;
import java.util.Map;

import com.ennuova.entity.CommonImages;

public interface CommonImagesService {

	List<CommonImages> queryImages(int tp, long tpId);
	/**获取车系/车型图片 数量(描述) tp为鉴别字符（1来源车系2来源车型）,tpid指车系/车型id
	 * @param type
	 * @param id
	 * @return 图片数量
	 */
	List<Map<String, Object>> getImgNumByLine(int type,Long id);
}
