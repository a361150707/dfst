package com.ennuova.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.ennuova.base.DaoSupport;
import com.ennuova.entity.PubCarinfo;
import com.ennuova.entity.PubLine;

@SuppressWarnings("rawtypes")
public interface CarInfoDao extends DaoSupport<PubCarinfo>{

	public PubCarinfo queryCarInfo(long myCarId);
	public List queryCarBrand();
	public List<PubLine> queryBrandModel(String brandId);
	public List<PubCarinfo> queryAllCarInfo(long userId);
	public String addCarInfo(String userId, String brandId, String chexiId,
			String chexingId,String carnum,String carCode) ;
	public int updateCarInfo(String carId, String label, String content);//修改爱车信息
	public String modifyCarInfo(String carId, String brandId, String chexiId,
			String chexingId) ;
	public List getModel(long fline);
	//public List queryCarList(long cusId);
	
	PubCarinfo queryCarinfo(long cusId);
	/**根据用户Id查询默认车辆信息
	 * @param userId
	 * @return PubCarinfo
	 */
	PubCarinfo getDefaultCarId(Long userId);
	/**根据车辆id获取车辆以及盒子相关信息
	 * @param fclid
	 * @return Map<String, Object>
	 */
	PubCarinfo getCarBoxInfo(Long fclid);
	/**根据车系Id获取ODB车系id
	 * @param lineId
	 * @return Long ODB车系id
	 */
	Long getOdbLine(Long lineId);
	/**根据主键Id查询ODB车型信息
	 * @param id
	 * @returnMap<String, Object>
	 */
	Map<String, Object> getOdbCX(Long id);
	/**根据车辆Id查询车辆激活信息
	 * @param fclid
	 * @return Map<String, Object>
	 */
	Map<String, Object> getCarJHInfo(Long fclid);
	/**根据车辆Id更新车架号
	 * @param fclid
	 * @return boolean
	 */
	boolean updateCarCode(Long fclid,String fCarCode);
	/**根据车系id查询车系信息
	 * @param lineId
	 * @return PubLine
	 */
	PubLine getPubLine(long lineId);
	/**
	 * 根据车辆id查询车辆的信息
	 * @author sududa
	 * @date 2016年7月13日
	 * @param carId
	 * @return
	 */
	public Map<String, Object> findMyCarByCarId(String carId);
	
	/**
	 * 根据车辆id获取该车辆的车型名称
	 *@author sududa
	 *@date 2016年10月8日
	 * @param carinfoId
	 * @return
	 */
	public Map<String, Object> findModelById(Serializable carinfoId);
	/**
	 * 查询附近的车列表
	 * @author sududa
	 * @date 2016年10月28日
	 */
	List<Map<String, Object>> doList(Integer page, Integer rows, String defCarId);
	
}
