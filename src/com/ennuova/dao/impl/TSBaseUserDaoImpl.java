package com.ennuova.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ennuova.base.impl.BaseDAOImpl;
import com.ennuova.dao.TSBaseUserDao;
import com.ennuova.entity.TSBaseUser;
import com.ennuova.util.DButil;
@Repository("tSBaseUserDao")
public class TSBaseUserDaoImpl extends BaseDAOImpl<TSBaseUser> implements TSBaseUserDao {

	/* 
	 * 根据车辆ID获取服务顾问信息
	 */
	@Override
	public TSBaseUser getTsBaseUser(long carId) {
		TSBaseUser tsBaseUser = new TSBaseUser();
		Connection conn = null;
		conn = getSessionFactory().getCurrentSession().connection(); 
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select * from t_s_base_user t where t.id=(select p.user_id from pub_carinfo p where p.id="+carId+")";
		try {
			ps=conn.prepareStatement(sql);
			rs=ps.executeQuery();
			while(rs.next()){
				tsBaseUser.setId(rs.getString("id"));
				tsBaseUser.setActivitisync(rs.getInt("ACTIVITISYNC"));
				tsBaseUser.setBrowser(rs.getString("browser"));
				tsBaseUser.setPassword(rs.getString("password"));
				tsBaseUser.setRealname(rs.getString("realname"));
				tsBaseUser.setSignature(rs.getString("signature"));
				tsBaseUser.setStatus(rs.getInt("status"));
				tsBaseUser.setUserkey(rs.getString("userkey"));
				tsBaseUser.setUsername(rs.getString("username"));
				tsBaseUser.setDepartid(rs.getString("departid"));
				tsBaseUser.setUserheadimg(rs.getString("userheadimg"));
				tsBaseUser.setMobilephone(rs.getString("mobilephone"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			DButil.closeDb(conn, ps, rs);
		}
		
		return tsBaseUser;
	}

	/**
	 *根据登陆的ID，获取顾问信息
	 * @author 陈晓珊
	 * @date 2016年10月24日
	 * @param loginId
	 * @return
	 */
	@Override
	public Map<String, Object> doDetail(String loginId) {
		String sql = "SELECT CUS.ID,baseUser.REALNAME,tsuser.SIGNATUREFILE,"
				+ "tsuser.EMAIL,tsuser.MOBILEPHONE,tsuser.OFFICEPHONE,"
				+ "tsuser.PRACTITIONERS_YEAR,R.ROLENAME FROM "
				+ "(SELECT ID,USER_ID FROM PUB_CUSTOMER WHERE ID = ?) cus "
				+ "LEFT JOIN T_S_BASE_USER baseUser ON cus.USER_ID = baseUser.ID "
				+ "LEFT JOIN T_S_USER tsuser ON tsuser.ID = baseUser.ID"
				+ " LEFT JOIN T_S_ROLE_USER ru ON ru.USERID = baseUser.ID "
				+ "LEFT JOIN T_S_ROLE r ON ru.ROLEID = r.ID";
		return findOneForJdbc(sql, loginId);
	}

}
