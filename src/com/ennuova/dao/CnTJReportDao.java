package com.ennuova.dao;

import java.util.List;

import com.ennuova.base.DaoSupport;
import com.ennuova.entity.CnTjgzdetail;
import com.ennuova.entity.CnTjreport;

/**体检报告dao
 * @author 李智辉 
 * 2016-1-5下午1:12:59
 */
public interface CnTJReportDao extends DaoSupport<CnTjreport> {

	/**根据车辆Id和上一次的最小体检Id查询体检列表
	 * @param fclid
	 * @param id
	 * @return
	 */
	List<CnTjreport> queryTjReport(long fclid,long id);
	
	/**
	 * 根据体检报告id获取体检明细
	 * @author 伟灿
	 * @time 2016-1-5
	 * @param ftjid 体检报告id
	 * @return
	 */
	List<CnTjgzdetail> queryTjDetail(long ftjid);
	/**根据体检Id查询本次体检项数
	 * @param ftjid
	 * @return int体检项数
	 */
	int getTjNum(long ftjid);
}
