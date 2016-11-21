package com.ennuova.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.ennuova.base.impl.DaoSupportImpl;
import com.ennuova.dao.TestDao;
import com.ennuova.entity.Test;

@Repository("testDao")
public class TestDaoImpl extends DaoSupportImpl<Test> implements TestDao {

	@Override
	public List<Test> query() {
		List<Test> tlist=new ArrayList<Test>();
		for(int i=0;i<3;i++){
			Test t=new Test();
			t.setId(i+"");
			t.setName("name"+i);
			tlist.add(t);
		}
		return tlist;
	}

	
	
}
