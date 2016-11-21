package com.ennuova.dao;

import java.util.List;
import java.util.Map;

import com.ennuova.base.DaoSupport;
import com.ennuova.entity.CommonImages;
import com.ennuova.entity.CommonVideos;

/**
 * @author 李智辉
 * @time 2016-5-24
 */
public interface CommonVideosDao extends DaoSupport<CommonVideos>{
	/**根据车系id查询视频
	 * @param tp
	 * @param tpId
	 * @param page页码 
	 * @return List<CommonVideos>
	 */
	List<Map<String, Object>> queryVideos(int tp,Long tpId,int page);
	/**根据车系Id获取视频数量 
	 * * @param tp 1-车系 2-车型
	 * @param tpId
	 * @return Long视频数量
	 */
	Long getVideoCountByLid(int tp,Long tpId);
	/**根据主键id更新视频点赞数
	 * @param id
	 */
	int updateVideoGoodCount(Long id,int count);
}
