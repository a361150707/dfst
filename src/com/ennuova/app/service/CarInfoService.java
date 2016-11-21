package com.ennuova.app.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.ennuova.entity.CarLocation;
import com.ennuova.entity.PubCarinfo;
import com.ennuova.entity.PubLine;
import com.ennuova.entity.PubVehiclemodel;
import com.ennuova.entity.TestInsert;

public interface CarInfoService {

	public PubCarinfo getCarInfo(long carId);
	public List getCarBrand();
	public List getCarInfo(String brandId);
	public List<PubCarinfo> getAllCarInfoByUser(Long userId);
	public String addCarInfo(String userId,String brandId,String chexiId,String chexingId,String carnum,String carCode);
	public int updateCarInfo(String carId, String label, String content);
	public String modifyCarInfo(String carId,String brandId,String chexiId,String chexingId);
	public List getModel(long fline);
	public Map<String,Object> getWeatherInfo(String cityName);
	//public List queryCarList(long khID);
	
	PubCarinfo queryCarinfo(long cusId);
	
	/**根据用户Id获取PubCarinfo
	 * @param userId
	 * @return PubCarinfo
	 */
	PubCarinfo getDefaultCarId(Long userId);
	/**激活盒子参数准备service
	 * @param fclid
	 * @return Map<String, Object>
	 */
	Map<String, Object> getCarBoxInfo(Long fclid);
	/**根据车辆Id获取激活信息
	 * @param fclid
	 * @return Map<String, Object>
	 */
	Map<String, Object> getCarJHInfo(Long fclid);
	/**更新车架号
	 * @param fclid
	 * @param carCode
	 * @return boolean
	 */
	boolean updateCarCode(Long fclid,String carCode);
	
	/**
	 * 删除用户车辆
	 * @param id
	 * @return
	 */
	boolean delCusCar(long id);
	/**根据车系id获取车系信息
	 * @param lineId
	 * @return PubLine
	 */
	PubLine getPubLine(long lineId);
	
	/**
	 * 
	* @Title: CarInfoService.java 
	* @Package com.ennuova.app.service 
	* @Description: 获取售后首页车辆信息(描述) 
	* @author felix
	* @date 2016年4月20日
	* @version V1.0
	 */
	PubCarinfo afterSaleCar(long cusId);
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
	 * @param defCarId 
	 * @date 2016年10月28日
	 */
	public List<Map<String, Object>> doList(Integer page, Integer rows, CarLocation carLocation, String defCarId);
	
	/**
	 * 根据客户的id清除客户默认车辆的位置信息
	 * @author sududa
	 * @date 2016年11月7日
	 */
	public Integer doCleanCarLocation(String cusId);
	/**
	 * 根据客以及默认车辆查询自己当天的车辆信息
	 * @author sududa
	 * @param cusId 
	 * @param defCarId 
	 * @date 2016年11月10日
	 */
	public Map<String, Object> QueryMyCarLocation(String cusId, String defCarId);
	/**
	 * 测试多条一起插入
	 * @author sududa
	 * @date 2016年11月18日
	 * @param testInserts
	 * @param rows
	 * @return void
	 */
	void testSaveBatch(List<TestInsert> testInserts, Integer rows);
	/**
	 * 测试单条插入
	 * @author sududa
	 * @date 2016年11月18日
	 * @param testInsert
	 * @return void
	 */
	public void saveOne(TestInsert testInsert);
	
}
