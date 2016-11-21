package com.ennuova.app.service;

import java.util.List;

import com.ennuova.entity.PubBox;
import com.ennuova.entity.PubCarinfo;

public interface MyBoxService {

	public List<PubBox> queryMyBox(long cusId);
	long checkBox(String fxhl);
	List<PubCarinfo> queryNoBindBoxCar(long cusId);
	boolean addBindBox(String fxlh,long boxid,long fclid,String pwd); 
	
	int jiebangBox(long boxId);
	int setDefBox(long cusId,long clId);
	String objFindBy(String seriaNo);
}
