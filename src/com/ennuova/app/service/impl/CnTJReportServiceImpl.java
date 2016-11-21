package com.ennuova.app.service.impl;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import oracle.sql.DATE;

import org.springframework.stereotype.Service;

import com.ennuova.app.service.CnTJReportService;
import com.ennuova.dao.CnGzmDao;
import com.ennuova.dao.CnTJReportDao;
import com.ennuova.dao.CnTjdetailDao;
import com.ennuova.dao.CnTjgzdetailDao;
import com.ennuova.entity.CnGzm;
import com.ennuova.entity.CnTjdetail;
import com.ennuova.entity.CnTjgzdetail;
import com.ennuova.entity.CnTjreport;
import com.ennuova.entity.CnTjreportVO;
import com.ennuova.entity.PubCarinfo;

/**体检记录
 * @author 李智辉 
 * 2016-1-5下午1:48:32
 */
@Service
public class CnTJReportServiceImpl implements CnTJReportService {
	@Resource
	private CnTJReportDao cnTJReportDao;
	@Resource
	private CnTjdetailDao cnTjdetailDao;
	@Resource 
	private CnTjgzdetailDao cnTjgzdetailDao;
	@Resource
	private CnGzmDao cnGzmDao;
	@Override
	public boolean addCnTJReport(String json,Long fclid,String fcph) {
		
		CnTjreport cnTjreport = new CnTjreport();
		boolean success = false;
		try {
			JSONObject jsonObject = JSONObject.fromObject(json);
			JSONArray jsonArrayDatas = jsonObject.getJSONArray("datas");
			cnTjreport.setFcph(fcph);
			Timestamp timestamp = new Timestamp(new Date().getTime());
			cnTjreport.setFtjtime(timestamp);
			cnTjreport.setFtstamp(new Date().getTime());
			cnTjreport.setFzgzs(jsonObject.getLong("fSize"));
			PubCarinfo pubCarinfo = new PubCarinfo();
			pubCarinfo.setId(fclid);
			cnTjreport.setPubCarinfo(pubCarinfo);
			cnTJReportDao.save(cnTjreport);
			CnTjreport cnTjreport1 = new CnTjreport();
			cnTjreport1.setId(cnTjreport.getId());
			if(jsonArrayDatas.size()!=0){
				for (int i = 0; i < jsonArrayDatas.size(); i++) {
					JSONObject jsonObject2 = jsonArrayDatas.getJSONObject(i);
					CnTjdetail cnTjdetail = new CnTjdetail();
					cnTjdetail.setCnTjreport(cnTjreport1);
					cnTjdetail.setFcode(jsonObject2.getString("id"));
					cnTjdetail.setFjcz(jsonObject2.getString("value"));
					cnTjdetail.setFname(jsonObject2.getString("name"));
					cnTjdetail.setFunit(jsonObject2.getString("unit"));
					cnTjdetailDao.save(cnTjdetail);
				}
			}
			JSONArray jsonArrayFaults = jsonObject.getJSONArray("faults");
			if(jsonArrayFaults.size()!=0){
				for (int i = 0; i < jsonArrayFaults.size(); i++) {
					JSONObject jsonObject3 = jsonArrayFaults.getJSONObject(i);
					JSONArray jsonArraySysFaults  = jsonObject3.getJSONArray("sysFaults");
					if(jsonArraySysFaults.size()!=0){
						for (int j = 0; j < jsonArraySysFaults.size(); j++) {
							JSONObject jsonObject4 = jsonArraySysFaults.getJSONObject(j);
							CnTjgzdetail cnTjgzdetail = new CnTjgzdetail();
							cnTjgzdetail.setGzxt(jsonObject3.getString("system"));
							cnTjgzdetail.setCnTjreport(cnTjreport1);
							cnTjgzdetail.setCode(jsonObject4.getString("code"));
							cnTjgzdetail.setGzid(jsonObject4.getString("id"));
							cnTjgzdetail.setState(jsonObject4.getString("state"));
							cnTjgzdetail.setGznr(jsonObject4.getString("content"));
							cnTjgzdetailDao.save(cnTjgzdetail);
						}
					}
				}
			}
			
			success = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return success;
	}
	@Override
	public List<CnTjreportVO> queryTjReport(long fclid,long id) {
		List<CnTjreport> cnTjreports = cnTJReportDao.queryTjReport(fclid,id);
		List<CnTjreportVO> cnTjreportvos = new ArrayList<CnTjreportVO>();
		Map<String, String> map = new HashMap<String, String>();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		for (CnTjreport cnTjreport : cnTjreports) {
			String timeStr = dateFormat.format(cnTjreport.getFtjtime());
			map.put(timeStr, timeStr);
			CnTjreportVO cnTjreportVO = new CnTjreportVO();
			cnTjreportVO.setId(cnTjreport.getId());
			cnTjreportVO.setFcph(cnTjreport.getFcph());
			cnTjreportVO.setFtjtime(cnTjreport.getFtjtime());
			cnTjreportVO.setFtstamp(cnTjreport.getFtstamp());
			cnTjreportVO.setFzgzs(cnTjreport.getFzgzs());
			cnTjreportVO.setPubCarinfo(cnTjreport.getPubCarinfo());
			cnTjreportvos.add(cnTjreportVO);
		}
		for(String mapStr : map.keySet()){
			for (CnTjreportVO cnTjreportVO : cnTjreportvos) {
				String timeStr = dateFormat.format(cnTjreportVO.getFtjtime());
				if(mapStr.equals(timeStr)){
					cnTjreportVO.setTimeStr(mapStr);
					break;
				}
			}
		}
		return cnTjreportvos;
	}
	@Override
	public List<CnTjgzdetail> queryTjDetail(long ftjid) {
		return cnTJReportDao.queryTjDetail(ftjid);
	}
	@Override
	public CnGzm getCnGzm(String faultCode) {
		CnGzm cnGzm = new CnGzm();
		try {
			cnGzm = this.cnGzmDao.findByFno(faultCode);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cnGzm;
	}
	@Override
	public int getTJNum(long ftjid) {
		int num = 0;
		try {
			num = cnTJReportDao.getTjNum(ftjid);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return num;
	}
}
