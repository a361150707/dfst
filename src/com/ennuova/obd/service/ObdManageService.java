package com.ennuova.obd.service;

import java.util.List;

import com.ennuova.obd.model.Modulelist;

/**
 * obd版本查询和日志上传
 * @author dofastlwj
 */
public interface ObdManageService {

	List<Modulelist> getModulelist(String device_id);

}
