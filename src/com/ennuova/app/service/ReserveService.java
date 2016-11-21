package com.ennuova.app.service;

import com.ennuova.app.config.AppResult;

public interface ReserveService {

	AppResult updateReserve(Long reserveId, int type);

}
