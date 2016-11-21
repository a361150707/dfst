package com.ennuova.app.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ennuova.app.service.PubReportService;
import com.ennuova.dao.PubReportDao;
import com.ennuova.entity.PubContacts;
import com.ennuova.entity.PubReport;
import com.ennuova.util.StringUtil;

@Service("pubReportService")
public class PubReportServiceImpl implements PubReportService{
	
	/**
	 * 举报的dao
	 */
	@Resource
	private PubReportDao pubReportDao;


	@Override
	public Integer doReport(PubReport pubReport) {
		PubReport resultEntity = pubReportDao.save(pubReport);
		if(StringUtil.isNotEmpty(resultEntity)){//表示插入成功
			return 1;
		}
		return 0;
	}
	
}
