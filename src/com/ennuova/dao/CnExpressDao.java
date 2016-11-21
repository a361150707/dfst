package com.ennuova.dao;

import com.ennuova.base.DaoSupport;
import com.ennuova.entity.CnExpress;

/**老卡用户dao
 * @author lee
 * @time 2016-9-12 下午3:11:33
 */
public interface CnExpressDao  extends DaoSupport<CnExpress>{
	/**根据用户Id查询该用户是否已经填写了信息
	 * @param id
	 * @return CnExpress
	 */
	CnExpress getCnExpress(long userId);
}
