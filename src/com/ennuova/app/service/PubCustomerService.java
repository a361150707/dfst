package com.ennuova.app.service;

import java.util.List;
import java.util.Map;

import com.ennuova.entity.PubCustomer;


public interface PubCustomerService {

	public boolean ifTelEg(String tel);
	public long reg(String tel,String pwd);
	public long validate(String mobile,String fpassword);
	public List<PubCustomer> queryAll();
	public long queryCustomerStore(long cusId);
	public PubCustomer getCustomerById(long cusId);
	String modifyUrl(long cusId,String filename);
	int changeMyinfo(long cusId,String label,String content);
	
	int changePwd(long id,String oldPwd,String newPwd); 
	
	/**
	 * 忘记密码修改
	 * @param tel
	 * @param pwd
	 * @return
	 */
	int forgetPwd(String tel,String pwd);
	public void addAlarm(long key);
	/**根据用户Id获取管家信息
	 * @param userId
	 * @return List<Map<String, Object>>
	 */
	List<Map<String, Object>> getHousekeeperById(Long userId);
	/**完善信息
	 * @param id
	 * @param nick
	 * @param address
	 * @param headImg
	 * @param driving
	 * @param sex
	 * @param staffId
	 * @return
	 */
	boolean perfectInfo(Long id, String nick, String address, int driving, int sex, String staffId);
	/**
	 * @param userId
	 * @param staffId
	 * @param storeId
	 * @return
	 */
	boolean updateManageId(Long userId,String staffId,Long storeId);
	
	/**
	 * 查询客户
	 * @author 陈晓珊
	 * @date 2016年11月2日
	 * @param page
	 * @param rows
	 * @param telephone
	 * @param interest
	 * @param nickname
	 * @return
	 */
	public List<Map<String, Object>> doQueryCustomer(Integer page,
			Integer rows,String loginId, String queryText);
	
	/**
	 * 是否能通过手机号查找客户
	 * @author 陈晓珊
	 * @date 2016年11月4日
	 * @param loginId
	 * @return
	 */
	public Integer doIsSearchTel(String loginId,String isSearchTel);
	
	/**
	 * 修改兴趣标签
	 * @param loginId
	 * @param interest
	 * @return
	 */
	public Integer doEditInterest(String loginId, String interest);
	
	/**
	 * 查找客户的基本信息(包括客户信息、车辆信息、车系信息、门店id)
	 * @author sududa
	 * @date 2016年11月14日
	 */
	public Map<String, Object> queryCusInfos(String cusId);
}
