package com.ennuova.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ennuova.base.impl.BaseDAOImpl;
import com.ennuova.dao.CarLocationDao;
import com.ennuova.dao.TestInsertDao;
import com.ennuova.entity.CarLocation;
import com.ennuova.entity.TestInsert;
import com.ennuova.util.StringUtil;
import com.ennuova.util.UrlUtil;

@Repository
public class TestInsertDaoImpl extends BaseDAOImpl<TestInsert> implements
		TestInsertDao {

	/**
	 * 测试批量插入数据库
	 */
	@Override
	public void testSaveBatch(List<TestInsert> testInserts,Integer rows) {
		saveWithBatch(testInserts,rows);
	}



}
