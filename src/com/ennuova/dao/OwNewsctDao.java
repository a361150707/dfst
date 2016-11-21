package com.ennuova.dao;

import java.util.List;
import java.util.Map;

import com.ennuova.base.DaoSupport;
import com.ennuova.entity.OwNewsct;

public interface OwNewsctDao extends DaoSupport<OwNewsct>{

    public List<OwNewsct> queryPageByState(long fstate,String fcompany,long ftype,int startIndex,int stopIndex);
	
	public OwNewsct getOwNewsById(long id);
	
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
	 * 查找新闻的详情
	 * @author sududa
	 * @date 2016-07-29
	 * @param newsId
	 * @return
	 */
	public Map<String, Object> findDetailById(String newsId);

	/**
	 * 根据关键字查找相关的新闻
	 * @param keyword
	 */
	public void findListRelation(String keyword);
	
	/**
	 * 查看所有的发布的，没有删除的新闻
	 * @author sududa
	 * @date 2016-07-29
	 * @return
	 */
	public List<Map<String, Object>> findAllNews();
	
	/**根据主键ID更新新闻评论或者点赞或者分享数量，每调用一次增加1
	 * @param id
	 * @param type 更新类型 0-更新新闻评论数 1-更新新闻点赞数 2-更新新闻分享数 3-更新新闻浏览次数
	 * @return Integer
	 */
	Integer updateCommentOrGoodOrShareCount(int type,Long id,int count);
	/**根据车系ID或者车型ID查询相关的新闻数量
	 * @param type 1-车系ID,2车型Id
	 * @param typeId
	 * @return	资讯数量
	 */
	Long getNewCountByKey(int type,Long typeId);
	/**根据车系ID查询车辆详解数量
	 * @param lineId
	 * @return Long新闻详解数量
	 */
	Long getNewCountByLid(Long lineId);
	
	/**
	 * @param type 类型
	 * @param typeId 车系或者车型Id
	 * @param isDetatil 是否是车系详解
	 * @return List<OwNewsct>
	 */
	List<OwNewsct> getNewsByLineIdOrVId(int type,Long typeId,int isDetatil,int startIndex,int stopIndex);
	/**获取置顶新闻
	 * @return List<OwNewsct>
	 */
	List<OwNewsct> getTopInfo();
}
