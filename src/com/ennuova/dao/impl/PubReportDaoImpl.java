package com.ennuova.dao.impl;

import java.io.Serializable;
import java.nio.Buffer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ennuova.base.impl.DaoSupportImpl;
import com.ennuova.dao.CarInfoDao;
import com.ennuova.dao.PubReportDao;
import com.ennuova.entity.PubBrand;
import com.ennuova.entity.PubCarinfo;
import com.ennuova.entity.PubCustomer;
import com.ennuova.entity.PubLine;
import com.ennuova.entity.PubReport;
import com.ennuova.entity.PubVehiclemodel;
import com.ennuova.util.DateUtils;
import com.ennuova.util.StringUtil;
import com.ennuova.util.UrlUtil;

@Repository("pubReportDao")
public class PubReportDaoImpl extends DaoSupportImpl<PubReport> implements PubReportDao{
	
	
}
