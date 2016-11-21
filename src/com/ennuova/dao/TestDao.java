package com.ennuova.dao;

import java.util.List;

import com.ennuova.base.DaoSupport;
import com.ennuova.entity.Test;

public interface TestDao  extends DaoSupport<Test>{

	public List<Test> query();
}
