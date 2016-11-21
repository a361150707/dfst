package com.ennuova.dao;

import java.util.List;
import java.util.Map;

import com.ennuova.base.BaseDAO;
import com.ennuova.entity.CarLocation;
import com.ennuova.entity.Cuslocation;
import com.ennuova.entity.TestInsert;

public interface TestInsertDao extends BaseDAO<TestInsert> {


	/**
	 * 测试批量插入
	 * @author sududa
	 * @date 2016年11月17日
	 */
	void testSaveBatch(List<TestInsert> testInserts,Integer rows);

	
}
