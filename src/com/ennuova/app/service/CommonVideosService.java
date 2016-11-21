package com.ennuova.app.service;

import java.util.List;
import java.util.Map;

import com.ennuova.entity.CommonVideos;

public interface CommonVideosService {
	/**获取车系视频
	 * @param tp
	 * @param tpId
	 * @return
	 */
	CommonVideos queryVideos(int tp, long tpId);
	/**分页获取车辆视频列表
	 * @param tp
	 * @param tpId
	 * @param page
	 * @param userId
	 * @return List<CommonVideos>
	 */
	List<Map<String, Object>> queryVideoList(int tp, long tpId,int page,Long userId);
}
