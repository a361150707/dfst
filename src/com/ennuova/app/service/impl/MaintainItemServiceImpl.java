package com.ennuova.app.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ennuova.app.service.MaintainItemService;
import com.ennuova.dao.MaintainItemDao;
@Service("maintainItemService")
public class MaintainItemServiceImpl implements MaintainItemService {
	@Resource
	private MaintainItemDao maintainItemDao;
}
