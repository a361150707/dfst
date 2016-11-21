package com.ennuova.app.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.ennuova.entity.PubCarinfo;
import com.ennuova.entity.PubLine;
import com.ennuova.entity.PubReport;
import com.ennuova.entity.PubVehiclemodel;

public interface PubReportService {

	/**
	 * 举报
	 * @param pubReport
	 * @return
	 */
	Integer doReport(PubReport pubReport);
	
}
