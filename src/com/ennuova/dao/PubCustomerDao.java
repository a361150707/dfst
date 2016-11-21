package com.ennuova.dao;



import java.util.List;
import java.util.Map;

import com.ennuova.base.DaoSupport;
import com.ennuova.entity.PubCustomer;

public interface PubCustomerDao extends DaoSupport<PubCustomer>{

	//此方法用来验证该手机号是否已经注册
	public boolean ifTelEg(String tel);
	public void addCustomer(PubCustomer customer);
	public long getNewID();
	public long login(String ftel,String fpassword);
	public List<PubCustomer> queryAll();
	public long queryCustomerStore(long cusId);
	public PubCustomer getCustomerById(long id);
	String modifyUrl(long cusId,String filename);
	boolean perfectInfo(Long id,String nick,String address,int driving,int sex,String staffId);
	int changeMyinfo(long cusId,String label,String content);
	
	//判断原密码是否正确
	int validatePwd(long id,String pwd);
	
	int changePwd(long id,String pwd);
	
	void addAlarm(long cusId);
	
	/**
	 * 忘记密码修改
	 * @param tel
	 * @param pwd
	 * @return
	 */
	int forgetPwd(String tel,String pwd);
	/**根据用户Id获取管家信息
	 * @param userId
	 * @return	List<Map<String, Object>>
	 */
	List<Map<String, Object>> getHousekeeperById(Long userId);
	
	/**为用户分配管家id
	 * @param userId
	 * @param staffId
	 * @return 
	 */
	boolean updateManageId(Long userId,String staffId);
	/**
	 * 根据主键id修改关注的数量-1
	 *@author sududa
	 *@date 2016年10月25日
	 * @param loginId
	 * @param followCount
	 * @return
	 */
	public Integer updateFollowCount(String loginId, int followCount);
	/**
	 * 根据主键id修改粉丝的数量-1
	 *@author sududa
	 *@date 2016年10月25日
	 * @param contactsId
	 * @param fansCount
	 * @return
	 */
	public Integer updateFansCount(String contactsId, int fansCount);
	/**
	 * 修改客户的关注数加1
	 *@author sududa
	 *@date 2016年10月25日
	 * @param loginId
	 * @param i
	 * @return
	 */
	public Integer updateFollowCountPlus(String loginId, int i);
	/**
	 * 修改客户的粉丝数加1
	 *@author sududa
	 *@date 2016年10月25日
	 * @param contactsId
	 * @param i
	 * @return
	 */
	public Integer updateFansCountPlus(String contactsId, int i);
	
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
			Integer rows, String queryText);
	
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
