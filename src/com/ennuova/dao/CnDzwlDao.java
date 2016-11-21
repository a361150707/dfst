package com.ennuova.dao;

import java.util.List;
import com.ennuova.base.DaoSupport;
import com.ennuova.entity.CnDzwl;
import com.ennuova.entity.dzwlview.CnDzwlView;

/**电子围栏dao
 * @author 李智辉 
 * 2015-12-15下午2:36:11
 */
public interface CnDzwlDao extends DaoSupport<CnDzwl>{
	/**添加一条电子围栏信息
	 * @param cnDzwl
	 * @return int d插入记录数
	 */
	int saveFence(CnDzwl cnDzwl);
	/**根据车辆Id和主键设置电子围栏,同时将该车辆id下的出当前id的其他围栏设置为未生效状态
	 * @param fclid
	 * @param Id
	 * @return int 更新记录数
	 */
	int setFence(Long fclid,Long Id,int fsrcs);
	/**根据车辆Id获取围栏列表
	 * @param fclid
	 * @return List<CnDzwl>
	 */
	List<CnDzwl> getCnDzwlList(Long fclid);
	/**根据主键Id获取电子围栏信息
	 * @param id
	 * @return CnDzwl
	 */
	CnDzwl getCnDzwl(Long id);
	/**根据围栏Id取消围栏
	 * @param id
	 * @return boolean true false
	 */
	boolean cancelFence(Long id);
	/**围栏监听
	 * @return CnDzwlView
	 */
	List<CnDzwlView> fenceListen();
	/**报警提醒
	 * @return CnDzwlView
	 */
	List<CnDzwlView> falarmListen();
}
