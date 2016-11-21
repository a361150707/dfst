package com.ennuova.app.service;

import java.util.List;

import com.ennuova.entity.OwNewsindex;

public interface OwNewsindexService {

	public List<OwNewsindex> queryNewsindex(long fcompany,long ftype);
}
