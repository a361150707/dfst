package com.ennuova.app.service;

import java.util.List;

import com.ennuova.entity.CnGzm;
import com.ennuova.entity.CnTjdetail;
import com.ennuova.entity.CnTjgzdetail;
import com.ennuova.entity.CnTjreport;
import com.ennuova.entity.CnTjreportVO;

/**体检报告service
 * @author 李智辉 
 * 2016-1-5下午1:21:25
 */
public interface CnTJReportService {
	/**添加一条体检记录
	 * @param cnTjreport fclid
	 * @return boolean true-成功 false-失败
	 */
	boolean addCnTJReport(String json,Long fclid,String fcph);
	

	/**根据车辆id上一次体检Id查询体检列表一次10条
	 * @param fclid
	 * @param Id
	 * @return List<CnTjreportVO>
	 */
	List<CnTjreportVO> queryTjReport(long fclid,long Id);
	
	/**
	 * 根据体检报告id获取体检明细
	 * @author 伟灿
	 * @time 2016-1-5
	 * @param ftjid 体检报告id
	 * @return
	 */
	List<CnTjgzdetail> queryTjDetail(long ftjid);
	/**根据故障码获取故障信息
	 * @param faultCode
	 * @return CnGzm
	 */
	CnGzm getCnGzm(String faultCode);
	/**根据体检Id查询本次体检项数
	 * @param ftjid
	 * @return int
	 */
	int getTJNum(long ftjid);
}
