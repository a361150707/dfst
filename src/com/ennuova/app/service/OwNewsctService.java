package com.ennuova.app.service;

import java.util.List;
import java.util.Map;

import com.ennuova.entity.OwNewsct;

public interface OwNewsctService {

	public List<OwNewsct> queryPageByState(long fstate,String fcompany,long ftype,int page,int size);
	public OwNewsct getNewsById(long id);
	public int growthFviewCount(long id,String tableName);
	
	/**
	 * @author sududa
	 * @date 2016-07-29
	 * @param type
	 * @param keyword
	 * @return
	 */
	public List<Map<String, Object>> findListRelation(String type, String keyword);
	/**
	 * @author sududa
	 * @date 2016-07-29
	 * @param newsId
	 * @param userId
	 * @return
	 */
	public Map<String, Object> findDetailAndRelation(String newsId,Long userId);

	/**对新闻或者评论点赞
	 * @param id
	 * @param type 0-点赞新闻，1-点赞评论
	 * @return Integer
	 */
	boolean clickGood(int type,Long fromId,Long userId);
	
	/**根据车系或者车型Id获取车辆的相关资讯数量等信息
	 * @param type
	 * @param typeId
	 * @return Map<String, Object> 
	 */
	Map<String, Object> getCarOtherInfo(int type,Long typeId);
	/**根据车系ID或者车型ID或者详解查询相关的新闻列表（分页）
	 * @param type
	 * @param typeId
	 * @param page
	 * @param size
	 * @param isDetatil
	 * @param startIndex
	 * @param stopIndex
	 * @return 新闻列表
	 */
	List<OwNewsct> getNewsByLineIdOrVId(int type,Long typeId,int page,int size,int isDetatil);
	/**获取置顶新闻
	 * @return List<OwNewsct>
	 */
	List<OwNewsct> getTopInfo();
}
