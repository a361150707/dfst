package com.ennuova.app.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ennuova.app.service.TestService;
import com.ennuova.dao.TestDao;
import com.ennuova.entity.Test;

@Service("testService")
public class TestServiceImpl implements TestService{

	@Resource
	private TestDao testDao;
	
	
	@Override
	public List<Test> query() {
		return this.testDao.query();
	}

	
}
