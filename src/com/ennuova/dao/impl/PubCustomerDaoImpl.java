package com.ennuova.dao.impl;


import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ennuova.base.impl.DaoSupportImpl;
import com.ennuova.dao.PubCustomerDao;
import com.ennuova.entity.PubCustomer;
import com.ennuova.util.DButil;
import com.ennuova.util.StringUtil;
import com.ennuova.util.UrlUtil;

@Repository("pubCustomerDao")
public class PubCustomerDaoImpl extends DaoSupportImpl<PubCustomer> implements	PubCustomerDao {

	//此方法用来验证该手机号是否已经注册
	@Override
	public boolean ifTelEg(String name){
		System.out.println("开始测试该手机号是否已经注册");
		String strTel = "select * from Pub_customer where ftel='"+name+"'";
		// TODO Auto-generated method stub
		//this.
		Connection conn = null;
		conn = getSessionFactory().getCurrentSession().connection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(strTel);
			rs = ps.executeQuery();
			if (rs.next()) {
				return true;
				}
			else{
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DButil.closeDb(conn, ps, rs);
		}
		return false;
	}

	/**
	 * 添加用户
	 */
	@Override
	public void addCustomer(PubCustomer customer) {
		try {
			this.save(customer);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	public long getNewID() {
		// TODO Auto-generated method stub
		Connection conn = null;
		conn = getSessionFactory().getCurrentSession().connection();
		CallableStatement stmt = null;
		Long newId=0l;
		try{
			 stmt = conn.prepareCall("call PNEXTID(?,?)");
			 stmt.setString(1, "Pub_customer");
	         //stmt.registerOutParameter(1, java.sql.Types.VARCHAR);
	         stmt.registerOutParameter(2, java.sql.Types.NUMERIC);			         
	         stmt.execute();
	         newId = stmt.getLong(2);
		}catch (Exception e){
			e.printStackTrace();
			return -1;
		}finally{
			DButil.closeDb(conn, stmt, null);
		}
		return newId;
	}

	@Override
	public long login(String tel, String fPassword) {
		// TODO Auto-generated method stub
		PubCustomer customer=(PubCustomer) getSessionFactory().getCurrentSession().createQuery(//
				"from PubCustomer where ftel=? and fpassword=?")
				.setParameter(0, tel)
				.setParameter(1, fPassword)
				.setFirstResult(0)
				.setMaxResults(1)
				.uniqueResult();
		if(customer==null)
			return -1;
		else
			return customer.getId();
	}

	@Override
	public List<PubCustomer> queryAll() {
		return this.findAll();
	}

	@Override
	public long queryCustomerStore(long cusId) {
		Connection conn = null;
		conn = getSessionFactory().getCurrentSession().connection();
		PreparedStatement ps = null;
		ResultSet rs=null;
		Long storeId=-1l;
		String sql=" SELECT FSTORE FROM PUB_BDSTORE WHERE FCUSTOMER='"+cusId+"' AND FDEFAULT='1'";
		try {
			ps=conn.prepareStatement(sql);
			rs=ps.executeQuery();
			while (rs.next()) {
				storeId=rs.getLong("FSTORE");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			DButil.closeDb(conn, ps, rs);
		}
		return storeId;
	}

	@Override
	public PubCustomer getCustomerById(long id) {
		Connection conn=null;
		conn = getSessionFactory().getCurrentSession().connection();
		PreparedStatement ps = null;
		ResultSet rs=null;
		String imgUrl =  UrlUtil.getInstance().getImgurl();
		String sql="SELECT cus.ID,'"+imgUrl+"'||cus.FPHOTO as FPHOTO,cus.FTEL,cus.FNICK,cus.FUSERNAME,"
				 + "cus.FSEX,cus.FJIAYEAR,cus.FADDR,cus.USER_ID,cus.IS_SEARCH_TEL,"
				 + "nvl(cus.CUS_INTEREST,'未设置兴趣标签') as cusInterest"
				 + " FROM PUB_CUSTOMER cus WHERE ID='"+id+"' ";
		PubCustomer customer = new PubCustomer();
		try {
			ps=conn.prepareStatement(sql);
			rs=ps.executeQuery();
			if(rs.next()){
				customer.setId(rs.getLong("ID"));
				/*if(rs.getString("FPHOTO")!=null&&rs.getString("FPHOTO")!=""){
					customer.setFphoto(imgUrl+rs.getString("FPHOTO"));
				}*/
				customer.setFphoto(rs.getString("FPHOTO"));
				customer.setFtel(rs.getString("FTEL"));
				customer.setFusername(rs.getString("FUSERNAME"));
				customer.setFnick(rs.getString("FNICK"));
				customer.setFsex(rs.getBigDecimal("FSEX"));
				customer.setFjiayear(rs.getBigDecimal("FJIAYEAR"));
				customer.setFaddr(rs.getString("FADDR"));
				customer.setUserId(rs.getString("USER_ID"));
				customer.setIsSearchTel(rs.getInt("IS_SEARCH_TEL"));
				customer.setCusInterest(rs.getString("cusInterest"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			DButil.closeDb(conn, ps, rs);
		}
		return customer;	
	}
	
	@Override
    /**
     * 修改上传图片的在数据库中的url
     */
    public String modifyUrl(long cusId,String filename) {
        Connection conn = null;
        conn = getSessionFactory().getCurrentSession().connection();
        PreparedStatement ps = null;
        String photo = "PubCustomer/" + filename;
        // 修改图片名称（一般第一次更改的时候会变化）
        String sql = "UPDATE PUB_CUSTOMER SET FPHOTO='" + photo + "' WHERE ID='" + cusId + "'";
        try {
            ps = conn.prepareStatement(sql);
            System.out.println(sql);
            ps.executeUpdate();
            conn.close();
            System.out.println("我的头像----修改成功");
            System.out.println("photo---------------" + photo);
            return photo;
        } catch (SQLException e) {
            System.out.println("我的头像----修改失败");
            e.printStackTrace();
        }finally{
        	DButil.closeDb(conn, ps, null);
        }

        return photo;
    }

	@Override
	public int changeMyinfo(long cusId, String label, String content) {
		Connection conn = null;
		conn = getSessionFactory().getCurrentSession().connection();
		PreparedStatement ps1 = null;
		String sql = "";
		String sql1 = "";
		int result = 0;
		if (label.equals("name")) {

			sql1 = "UPDATE PUB_CUSTOMER SET FUSERNAME='" + content + "' WHERE ID='"+ cusId + "'";
			try {
				ps1 = conn.prepareStatement(sql1);
				result = ps1.executeUpdate();
				System.out.println("我的姓名---修改成功");
				conn.close();
			} catch (SQLException e) {
				System.out.println("我的姓名---修改失败");
				e.printStackTrace();
			}
		} else if (label.equals("sex")) {
			if (content.equals("女")) {
				sql = "UPDATE PUB_CUSTOMER SET FSEX='2' WHERE ID='" + cusId + "'";
				try {
					ps1 = conn.prepareStatement(sql);
					result = ps1.executeUpdate();
					System.out.println("我的性别---修改成功");
					conn.close();
				} catch (SQLException e) {
					System.out.println("我的性别---修改失败");
					e.printStackTrace();
				}
			} else {
				sql = "UPDATE PUB_CUSTOMER SET FSEX='1' WHERE ID='" + cusId + "'";
				try {
					ps1 = conn.prepareStatement(sql);
					result = ps1.executeUpdate();
					System.out.println("我的性别---修改成功");
					conn.close();
				} catch (SQLException e) {
					System.out.println("我的性别---修改失败");
					e.printStackTrace();
				}
			}
		} else if (label.equals("addr")) {// 修改地区
			sql = "UPDATE PUB_CUSTOMER SET FADDR='"+content+"' WHERE ID='" + cusId + "'";
			try {
				System.out.println(sql);
				ps1 = conn.prepareStatement(sql);
				result = ps1.executeUpdate();
				System.out.println("我的地区---修改成功");
				conn.close();
			} catch (SQLException e) {
				System.out.println("我的地区---修改失败");
				e.printStackTrace();
			}
			
		}else if (label.equals("nick")) {
			sql = "UPDATE PUB_CUSTOMER SET fnick='"+content+"' WHERE ID='" + cusId + "'";
			try {
				System.out.println(sql);
				ps1 = conn.prepareStatement(sql);
				result = ps1.executeUpdate();
				System.out.println("我的昵称---修改成功");
				conn.close();
			} catch (SQLException e) {
				System.out.println("我的昵称---修改失败");
				e.printStackTrace();
			}
		}else if (label.equals("age")) {
			sql = "UPDATE PUB_CUSTOMER SET FJIAYEAR='"+content+"' WHERE ID='" + cusId + "'";
			try {
				System.out.println(sql);
				ps1 = conn.prepareStatement(sql);
				result = ps1.executeUpdate();
				System.out.println("我的年龄---修改成功");
				conn.close();
			} catch (SQLException e) {
				System.out.println("我的年龄---修改失败");
				e.printStackTrace();
			}
		}
		return  result;
	}

	/**
	 * 确认原密码是否正确
	 */
	@Override
	public int validatePwd(long id, String pwd) {
		Connection conn = null;
		PreparedStatement ps = null;
		conn = getSessionFactory().getCurrentSession().connection();
		ResultSet rs = null;
		int result = 0;
		String sql = " SELECT COUNT(*) AS ISHAVE FROM PUB_CUSTOMER WHERE ID='"+id+"' AND FPASSWORD='"+pwd+"' ";
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			if(rs.next()){
				result = rs.getInt("ISHAVE");
			}
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 修改密码
	 */
	@Override
	public int changePwd(long id, String pwd) {
		Connection conn = null;
		PreparedStatement ps = null;
		conn = getSessionFactory().getCurrentSession().connection();
		String sql = " UPDATE PUB_CUSTOMER SET FPASSWORD='"+pwd+"' WHERE ID='"+id+"' ";
		int result = 0;
		try {
			ps = conn.prepareStatement(sql);
			result = ps.executeUpdate();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			DButil.closeDb(conn, ps, null);
		}
		return result;
	}

	@Override
	public void addAlarm(long cusId) {
		Connection conn = null;
		conn = getSessionFactory().getCurrentSession().connection();
		//创建存储过程的对象  
		CallableStatement c = null;
		try {
			c=conn.prepareCall("{call ADD_ALARM(?)}");  
			//给存储过程的参数设置值  
			c.setLong(1, cusId); //将第一个参数的值设置成100  
			//执行存储过程
			c.execute();  
			conn.close();  			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 忘记密码修改
	 * @param tel
	 * @param pwd
	 * @return
	 */
	@Override
	public int forgetPwd(String tel, String pwd) {
		Connection conn = null;
		PreparedStatement ps = null;
		conn = getSessionFactory().getCurrentSession().connection();
		String sql = " UPDATE PUB_CUSTOMER SET FPASSWORD='"+pwd+"' WHERE FTEL ='"+tel+"' ";
		int result = 0;
		try {
			ps = conn.prepareStatement(sql);
			result = ps.executeUpdate();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			DButil.closeDb(conn, ps, null);
		}
		return result;
	}

	@Override
	public List<Map<String, Object>> getHousekeeperById(Long userId) {
		String sql = "select c.user_id as userId,t.mobilephone as phone,t.signaturefile as headimg,s.realname as name from pub_customer c left join t_s_user t on c.user_id=t.id left join t_s_base_user s on t.id=s.id where c.id=?";
		return findForJdbc(sql, userId);
	}

	@Override
	public boolean perfectInfo(Long id, String nick, String address, int driving, int sex, String staffId) {
		boolean success = false;
		Connection conn = null;
		PreparedStatement ps = null;
		conn = getSessionFactory().getCurrentSession().connection();
		String sql = " UPDATE PUB_CUSTOMER set fusername='"+nick+"',FNICK='"+nick+"',FADDR='"+address+"'," +
				"FJIAYEAR="+driving+",FSEX="+sex+",USER_ID='"+staffId+"' WHERE id ="+id;
		int result = 0;
		try {
			ps = conn.prepareStatement(sql);
			result = ps.executeUpdate();
			if(result>0){
				success = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return success;
		}finally{
			DButil.closeDb(conn, ps, null);
		}
		return success;
	}

	@Override
	public boolean updateManageId(Long userId, String staffId) {
		boolean success = false;
		Connection conn = null;
		PreparedStatement ps = null;
		conn = getSessionFactory().getCurrentSession().connection();
		String sql = " UPDATE PUB_CUSTOMER set USER_ID='"+staffId+"' WHERE id ="+userId;
		int result = 0;
		try {
			ps = conn.prepareStatement(sql);
			result = ps.executeUpdate();
			if(result>0){
				success = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return success;
		}finally{
			DButil.closeDb(conn, ps, null);
		}
		return success;
	}

	/**
	 * 根据主键id修改关注的数量-1
	 *@author sududa
	 *@date 2016年10月25日
	 * @param loginId
	 * @param followCount
	 * @return
	 */
	@Override
	public Integer updateFollowCount(String loginId, int followCount) {
		String sql = "update PUB_CUSTOMER cus"
				   + " set cus.FOLLOW_COUNT = cus.FOLLOW_COUNT - 1"
				   + " where cus.ID = ? and cus.FOLLOW_COUNT > ? and cus.FOLLOW_COUNT is not null";
		return executeSql(sql, loginId,0);
	}

	/**
	 * 根据主键id修改粉丝的数量-1
	 *@author sududa
	 *@date 2016年10月25日
	 * @param contactsId
	 * @param fansCount
	 * @return
	 */
	@Override
	public Integer updateFansCount(String contactsId, int fansCount) {
		String sql = "update PUB_CUSTOMER cus"
				   + " set cus.FANS_COUNT = cus.FANS_COUNT - 1"
				   + " where cus.ID = ? and cus.FANS_COUNT > ? and cus.FANS_COUNT is not null";
		return executeSql(sql, contactsId,0);
	}

	/**
	 * 修改客户的关注数加1
	 *@author sududa
	 *@date 2016年10月25日
	 * @param loginId
	 * @param i
	 * @return
	 */
	@Override
	public Integer updateFollowCountPlus(String loginId, int followCount) {
		String sql = "update PUB_CUSTOMER cus"
				   + " set cus.FOLLOW_COUNT = nvl(cus.FOLLOW_COUNT,0) + 1"
				   + " where cus.ID = ?";
		return executeSql(sql, loginId);
	}

	/**
	 * 修改客户的粉丝数加1
	 *@author sududa
	 *@date 2016年10月25日
	 * @param contactsId
	 * @param fansCount
	 * @return
	 */
	@Override
	public Integer updateFansCountPlus(String contactsId, int fansCount) {
		String sql = "update PUB_CUSTOMER cus"
				   + " set cus.FANS_COUNT = nvl(cus.FANS_COUNT,0) + 1"
				   + " where cus.ID = ?";
		return executeSql(sql, contactsId);
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
			Integer rows, String queryText) {
		String imgUrl = UrlUtil.getInstance().getImgurl();
		StringBuilder sql = new StringBuilder(""+"SELECT cus.ID,?||cus.FPHOTO as FPHOTO,"
				+ "nvl(cus.FNICK,'匿名') as FNICK ,"
				+ "cus.FSEX,nvl(cus.CUS_INTEREST,'未设置兴趣标签') as cusInterest,"
				+ "nvl(line.FNAME,'未绑定车辆') as lineName,"
				+ "to_char(box.FJHTIME,'yyyy-mm-dd hh24:mi:ss') as jhTime "
				+ "FROM PUB_CUSTOMER cus LEFT JOIN PUB_CARINFO car "
				+ "ON cus.ID = car.FCUSTOMER AND car.FDEFAULT = ? "
				+ "LEFT JOIN PUB_LINE line ON car.FLINE = line.ID "
				+ "LEFT JOIN PUB_BOX box on car.ID = box.FCLID"
				+ " WHERE nvl(cus.DEL_FLAG,0) = ?");
		
		if(StringUtil.isNotEmpty(queryText)){
			sql.append("AND ((cus.FTEL = '"+queryText+"' AND cus.IS_SEARCH_TEL = '1') "
					+ "or cus.CUS_INTEREST LIKE '%"+queryText+"%' "
					+ "or cus.FNICK  = '"+queryText+"')");
		}
		return findForJdbcPage(sql.toString(), page, rows,imgUrl,1,0);
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
		String sql = "UPDATE PUB_CUSTOMER cus "
				+ "SET cus.IS_SEARCH_TEL = ?  WHERE cus.ID = ?";
		return executeSql(sql, isSearchTel,loginId);
	}

	/**
	 * 修改兴趣标签
	 * @param loginId
	 * @param interest
	 * @return
	 */
	@Override
	public Integer doEditInterest(String loginId, String interest) {
		String sql = "UPDATE PUB_CUSTOMER cus "
				+ "SET cus.CUS_INTEREST = ?  WHERE cus.ID = ? ";
		return executeSql(sql, interest,loginId);
	}

	/**
	 * 查找客户的基本信息(包括客户信息、车辆信息、车系信息、门店id)
	 * @author sududa
	 * @date 2016年11月14日
	 */
	@Override
	public Map<String, Object> queryCusInfos(String cusId) {
		String imgUrl = UrlUtil.getInstance().getImgurl();
		String sql = "select ?||cus.FPHOTO as FPHOTO,cus.FNICK,cus.FUSERNAME,cus.FPASSWORD,"
				   + "cus.FSEX,cus.FTEL,cus.FZHIYE,cus.FJIAYEAR,cus.FADDR,"
				   + "cus.ALIAS,cus.TAGS,cus.FWEIXIN,cus.FSALEADVISER,cus.FPROVINCE,"
				   + "cus.FCITY,cus.FAREA,cus.FTYPE,cus.USER_ID as userId,"
				   + "cus.CUS_INTEREST as cusInterest,cus.FOLLOW_COUNT as cusFollowCount,"
				   + "cus.FANS_COUNT as fansCount,cus.IS_SEARCH_TEL as isSearchTel,"
				   + "car.FBRAND,car.FLINE,car.FVEHICLEMODEL,car.FCARCODE,"
				   + "car.FCARNUM,car.FENGINENUM,"
				   + "to_char(car.FINSEXPIRE,'yyyy-mm-dd hh24:mi:ss') as FINSEXPIRE,"
				   + "to_char(car.FGUAEXPIRE,'yyyy-mm-dd hh24:mi:ss') as FGUAEXPIRE,"
				   + "to_char(car.FYEARINSEXPIRE,'yyyy-mm-dd hh24:mi:ss') as FYEARINSEXPIRE,"
				   + "to_char(car.FINSCOMPANY,'yyyy-mm-dd hh24:mi:ss') as FINSCOMPANY,"
				   + "car.MAINTAIN_MILEAGE as maintainMileage,"
				   + "to_char(car.MAINTIAN_TIME,'yyyy-mm-dd hh24:mi:ss') as maintainTime,"
				   + "line.FNUMBER,line.FNAME as lineName,?||line.S_PICTURE as linePicture,"
				   + "line.S_CONTENT as lineContent,line.MAILBOX_CAPACITY as lineMailboxCapacity,"
				   + "bdstore.FSTORE as storeId"
				   + " from PUB_CUSTOMER cus"
				   + " left join PUB_CARINFO car"
				   + " on cus.ID = car.FCUSTOMER and car.FDEFAULT = 1"
				   + " left join PUB_LINE line"
				   + " on car.FLINE = line.ID"
				   + " left join PUB_BDSTORE bdstore"
				   + " on cus.ID = bdstore.FSTORE"
				   + " where cus.ID = ?";
		return findOneForJdbc(sql, imgUrl, imgUrl, cusId);
	}

}
