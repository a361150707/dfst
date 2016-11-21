package com.ennuova.app.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ennuova.app.service.PubCustomerService;
import com.ennuova.dao.CarInfoDao;
import com.ennuova.dao.CnInfoDao;
import com.ennuova.dao.PubBdstoreDao;
import com.ennuova.dao.PubContactsDao;
import com.ennuova.dao.PubCustomerDao;
import com.ennuova.dao.PubLineDao;
import com.ennuova.entity.PubBdstore;
import com.ennuova.entity.PubCarinfo;
import com.ennuova.entity.PubCustomer;
import com.ennuova.entity.PubLine;
import com.ennuova.util.ConvertTools;
import com.ennuova.util.StringUtil;
import com.ennuova.util.UrlUtil;

@Transactional
@Service("pubCustomerService")
public class PubCustomerServiceImpl implements PubCustomerService{

	@Resource
	private PubCustomerDao pubCustomerDao;
	
	@Resource
	private CnInfoDao cnInfoDao;
	
	@Resource
	private CarInfoDao carInfoDao;
	
	@Resource
	private PubLineDao pubLineDao;
	
	@Resource
	private PubBdstoreDao pubBdstoreDao;
	
	/**
	 * 联系人的dao
	 */
	@Resource
	private PubContactsDao pubContactsDao;

	@Override
	public boolean ifTelEg(String tel) {
		return this.pubCustomerDao.ifTelEg(tel);
	}

	PubCustomer u;
	@Override
	public long reg(String tel, String pwd) {
		// TODO Auto-generated method stub
		//用来测试打印输出
		System.out.println("RegService");
		System.out.println(tel);
		//System.out.println(flxr);
		System.out.println(pwd);	
		//把用户注册信息存入到客户信息表中
		//根据ID规则获取ID
		long key = pubCustomerDao.getNewID();
		//设置实体BEAN值
		u= new PubCustomer();
		u.setId(key);
		System.out.println("khId:"+key);
		u.setFpassword(pwd);
		u.setFtel(tel);
		u.setFusername(tel);
		u.setFcreatetime(new Date());
		BigDecimal sex = BigDecimal.valueOf(1);
		u.setFsex(sex);
		u.setAlias("zhg"+key);
		System.out.println("key:"+key);
		pubCustomerDao.addCustomer(u);
		return key;
	}
	
	public void addAlarm(long key){
		pubCustomerDao.addAlarm(key);
	}
	
	@Override
	public long validate(String mobile, String fpassword) {
		return pubCustomerDao.login(mobile, fpassword);
	}
	
	@Override
	public List<PubCustomer> queryAll() {
		return pubCustomerDao.queryAll();
	}
	
	@Override
	public long queryCustomerStore(long cusId) {
		return pubCustomerDao.queryCustomerStore(cusId);
	}
	
	@Override
	public PubCustomer getCustomerById(long cusId) {
		PubCustomer customer = pubCustomerDao.getCustomerById(cusId);
		PubCarinfo carInfo = carInfoDao.getDefaultCarId(cusId);
		customer.setStoreId(pubCustomerDao.queryCustomerStore(cusId));
		if(StringUtil.isNotEmpty(carInfo)){
			customer.setDefBoxId(carInfo.getBoxId());
			customer.setDefCarId(carInfo.getId());
			if(StringUtil.isNotEmpty(carInfo.getLid())){
				PubLine pubLine = carInfoDao.getPubLine(carInfo.getLid());
				if(StringUtil.isNotEmpty(pubLine)){
					customer.setDefaultImg(pubLine.getsPicture());
					customer.setBoxNum(carInfo.getFxlh());
					customer.setCarNum(carInfo.getFcarnum());
					customer.setVname(carInfo.getVname());
					customer.setLineName(carInfo.getLineName());
				}
			}
		}
	    int count = 2;
	    if(customer.getSex()!=null) count++;
	    if(customer.getBoxNum()!=null) count++;
	    if(customer.getFphoto()!=null) count++;
	    if(customer.getFnick()!=null) count++;
	    if(customer.getFaddr()!=null) count++;
	    int ratioF = count*100/7;
	    customer.setDataCompletion(ratioF+"%");
		int i = cnInfoDao.getNoReadMeg(cusId);
		customer.setMcount(i);
		return customer;
	}

	@Override
	public String modifyUrl(long cusId, String filename) {
		return pubCustomerDao.modifyUrl(cusId, filename);
	}

	@Override
	public int changeMyinfo(long cusId, String label, String content) {
		return pubCustomerDao.changeMyinfo(cusId, label, content);
	}

	/**
	 * 修改密码
	 */
	@Override
	public int changePwd(long id, String oldPwd, String newPwd) {
		int result = 0;
		try {
			//判断旧密码是否正确
			int temp = pubCustomerDao.validatePwd(id, oldPwd);
			System.out.println("temp:"+temp);
			if(temp > 0){//密码存在
				result=1;
				pubCustomerDao.changePwd(id, newPwd);
			}else{
				result=0;
			}
		} catch (Exception e) {
			result = -1;
			e.printStackTrace();
		}
		System.out.println("result:"+result);
		return result;
	}

	/**
	 * 忘记密码修改
	 * @param tel
	 * @param pwd
	 * @return
	 */
	@Override
	public int forgetPwd(String tel,String pwd) {
		return pubCustomerDao.forgetPwd(tel, pwd);
	}

	@Override
	public List<Map<String, Object>> getHousekeeperById(Long userId) {
		List<Map<String, Object>> list = pubCustomerDao.getHousekeeperById(userId);
		String imgUrl = UrlUtil.getInstance().getImgurl();
		try {
			for (Map<String, Object> map : list) {
				String headImg = (String) map.get("HEADIMG");
				if(headImg!=""){
					map.put("HEADIMG", imgUrl+headImg);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public boolean perfectInfo(Long id, String nick, String address, int driving, int sex, String staffId) {
		// TODO Auto-generated method stub
		return pubCustomerDao.perfectInfo(id, nick, address, driving, sex, staffId);
	}

	@Override
	public boolean updateManageId(Long userId, String staffId,Long storeId) {
		boolean success = false;
		try {
			success = pubCustomerDao.updateManageId(userId, staffId);
			long id = pubBdstoreDao.getIsExistId(storeId, userId);
			if(id==-1){//之前未绑定
				BigDecimal def = BigDecimal.valueOf(1);
				PubBdstore bdStore = new PubBdstore();
				bdStore.setFdefault(def);
				bdStore.setFstore(storeId);
				PubCustomer cus=new PubCustomer();
				cus.setId(userId);
				bdStore.setPubCustomer(cus);
				pubBdstoreDao.save(bdStore);
			}else{//已经绑定门店（直接修改门店）
				pubBdstoreDao.updateDefault(userId);
				pubBdstoreDao.updateCusDefault(id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return success;
	}

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
	@Override
	public List<Map<String, Object>> doQueryCustomer(Integer page,
			Integer rows,String loginId, String queryText) {
		List<Map<String, Object>> resultList = pubCustomerDao.doQueryCustomer(page,rows,queryText);
		List<Map<String, Object>>  followList = pubContactsDao.queryFollowList(loginId);
		String imgUrl = UrlUtil.getInstance().getImgurl();
		//客户中是否有我关注的人
		for(Map<String, Object> map : resultList){
			String cusID =  map.get("ID")+"";
			map.put("ISFOLLOW", "");
			for(int j = 0;j < followList.size();j++){
				String contactsID = followList.get(j).get("CONTACTS_ID")+"";
				if(contactsID.equals(cusID)){
					map.put("ISFOLLOW", "1");
					break;
				}
			}
		}
		return resultList;
	}

	/**
	 * 是否能通过手机号查找客户
	 * @author 陈晓珊
	 * @date 2016年11月4日
	 * @param loginId
	 * @return
	 */
	@Override
	public Integer doIsSearchTel(String loginId,String isSearchTel) {
		return pubCustomerDao.doIsSearchTel(loginId,isSearchTel);
	}

	/**
	 * 修改兴趣标签
	 * @param loginId
	 * @param interest
	 * @return
	 */
	@Override
	public Integer doEditInterest(String loginId, String interest) {
		return pubCustomerDao.doEditInterest(loginId,interest);
	}

	/**
	 * 查找客户的基本信息(包括客户信息、车辆信息、车系信息、门店id)
	 * @author sududa
	 * @date 2016年11月14日
	 */
	@Override
	public Map<String, Object> queryCusInfos(String cusId) {
		return pubCustomerDao.queryCusInfos(cusId);
	}

}
