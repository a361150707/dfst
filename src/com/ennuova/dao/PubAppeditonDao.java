package com.ennuova.dao;


import com.ennuova.base.DaoSupport;
import com.ennuova.entity.PubAppediton;

/**版本更新dao
 * @author zhihui
 *
 */
public interface PubAppeditonDao extends DaoSupport<PubAppediton>{
	/**根据APP类型获取该APP版本号和该APP下载地址	
	 * @param appType
	 * @return  Map<String, Object>
	 */
	PubAppediton getEditonByAppType(String appType);
}
