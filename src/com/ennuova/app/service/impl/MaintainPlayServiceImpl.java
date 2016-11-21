package com.ennuova.app.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ennuova.app.service.MaintainPlayService;
import com.ennuova.dao.MaintainPlayDao;
@Service("maintainPlayService")
public class MaintainPlayServiceImpl implements MaintainPlayService {
	@Resource
	private MaintainPlayDao maintainPlayDao;
}
