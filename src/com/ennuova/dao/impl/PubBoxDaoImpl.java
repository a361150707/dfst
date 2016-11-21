package com.ennuova.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ennuova.base.impl.DaoSupportImpl;
import com.ennuova.dao.PubBoxDao;
import com.ennuova.entity.CnDiagsoft;
import com.ennuova.entity.PubBox;
import com.ennuova.entity.PubCarinfo;
import com.ennuova.util.DButil;
import com.ennuova.util.UrlUtil;
import com.ennuova.entity.PubOdblinerel;
import com.ennuova.entity.PubOdbvehicle;

/**
 * 盒子信息dao
 * 
 * @author janson
 */
@Repository("pubBoxDao")
public class PubBoxDaoImpl extends DaoSupportImpl<PubBox> implements PubBoxDao {

	/**
	 * 根据盒子序列号查询盒子信息
	 */
	public PubBox findByFxlh(String fxlh) {
		return (PubBox) getSessionFactory().getCurrentSession().createQuery(//
				"from PubBox where fxlh=?").setParameter(0, fxlh)
				.setFirstResult(0)//
				.setMaxResults(1)//
				.uniqueResult();
	}

	/**
	 * 获取我的盒子信息列表
	 */
	@Override
	public List<PubBox> queryMyBox(long cusId) {
		/*
		 * Connection conn = null; PreparedStatement ps = null;
		 */
		Connection conn = null;
		conn = getSessionFactory().getCurrentSession().connection();
		PreparedStatement ps = null;
		String sql = " SELECT BOX.ID,BOX.FXLH,CAR.FCARNUM,CAR.ID CARID,"
				+ " CAR.FDEFAULT cardefault,CUS.ID CUSID,M.FNAME MNAME FROM Pub_Box BOX "
				+ " LEFT JOIN Pub_Carinfo CAR ON CAR.ID=BOX.FCLID LEFT JOIN "
				+ " Pub_Customer CUS ON CUS.ID=CAR.FCUSTOMER LEFT JOIN Pub_Vehiclemodel M "
				+ " ON M.ID=CAR.FVEHICLEMODEL WHERE CAR.FCUSTOMER='" + cusId
				+ "' ORDER BY CAR.FDEFAULT DESC";
		System.out.println("queryMyBox:" + sql.toString());
		ResultSet rs = null;
		List<PubBox> boxList = new ArrayList<PubBox>();
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				PubBox box = new PubBox();
				box.setId(rs.getLong("ID"));
				box.setFxlh(rs.getString("FXLH"));
				box.setFcarnum(rs.getString("FCARNUM"));
				box.setCarid(rs.getLong("CARID"));
				box.setCardefault(rs.getLong("CARDEFAULT"));
				box.setCusid(rs.getLong("CUSID"));
				box.setMname(rs.getString("MNAME"));
				boxList.add(box);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			DButil.closeDb(conn, ps, rs);
		}
		return boxList;/*
						 * getSessionFactory().getCurrentSession().createSQLQuery
						 * ("SELECT BOX.ID,BOX.FXLH,CAR.FCARNUM,CAR.ID CARID," +
						 * " CAR.FDEFAULT cardefault,CUS.ID CUSID,M.FNAME MNAME FROM Pub_Box BOX "
						 * +
						 * " LEFT JOIN Pub_Carinfo CAR ON CAR.ID=BOX.FCLID LEFT JOIN "
						 * +
						 * " Pub_Customer CUS ON CUS.ID=CAR.FCUSTOMER LEFT JOIN Pub_Vehiclemodel M "
						 * +
						 * " ON M.ID=CAR.FVEHICLEMODEL WHERE CAR.FCUSTOMER=? ")
						 * .addEntity(PubBox.class) .setParameter(0,
						 * cusId).list();
						 */
	}

	/**
	 * 返回提示消息:（-1）--（-3）各表示以下三点意思
	 *  -1序列号不存在时，提示：无效的系列号，请重新输入！
	 *  -2密码错误，提示：密码输入错误，请重新输入！
	 *  -3序列号已绑定车辆时，提示：当前输入的系列号已被其他车辆绑定，请重新输入！
	 */
	@Override
	public long checkBox(String fxhl) {
		Connection conn = null;
		PreparedStatement ps = null;
		long result = -1;
		conn = getSessionFactory().getCurrentSession().connection();
		ResultSet rs = null;
		try {
			String sql = " SELECT ID FROM PUB_BOX WHERE FXLH='"+fxhl+"' ";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			if(rs.next()){
				/*sql = " SELECT ID FROM PUB_BOX WHERE FXLH='" + fxhl
						+ "' AND FPASSWORD='" + fpassword + "' ";
				System.out.println(sql);
				ps = conn.prepareStatement(sql);
				rs = ps.executeQuery();
				if (rs.next()) {*/
					sql = " SELECT ID FROM PUB_BOX WHERE FXLH = '" + fxhl+"' AND FCLID IS NULL";
					ps = conn.prepareStatement(sql);
					rs = ps.executeQuery();
					if(rs.next()){
						result =  rs.getLong("ID");
					}else{
						result = -3;
					}
				/*}else{
					result = -2;
				}*/
			}else{
				result = -1;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			DButil.closeDb(conn, ps, rs);
		}
		System.out.println("boxState:"+result);
		return result;
	}

	/**
	 * 获取未绑定盒子的车辆信息
	 */
	@Override
	public List<PubCarinfo> queryNoBindBoxCar(long cusId) {
		Connection conn = null;
		conn = getSessionFactory().getCurrentSession().connection();
		PreparedStatement ps = null;
		String sql ="SELECT CAR.FCARNUM,CAR.ID,CAR.FCARCODE,BRAND.FNAME BNAME,BRAND.FLOGO,LINE.FNAME LNAME,V.FNAME VNAME FROM PUB_CARINFO CAR LEFT JOIN PUB_BRAND BRAND ON BRAND.ID=CAR.FBRAND LEFT JOIN PUB_LINE LINE ON LINE.ID=CAR.FLINE LEFT JOIN PUB_VEHICLEMODEL V ON V.ID=CAR.FVEHICLEMODEL  WHERE CAR.ID NOT IN(SELECT FCLID FROM PUB_BOX WHERE FCLID IS NOT NULL) AND CAR.FCUSTOMER='"+cusId+"' ORDER BY CAR.ID DESC";

		System.out.println(sql.toString());
		ResultSet rs = null;
		List<PubCarinfo> carList = new ArrayList<PubCarinfo>();
		String imgUrl =  UrlUtil.getInstance().getImgurl();
		try {
			ps = conn.prepareStatement(sql.toString());
			rs = ps.executeQuery();
			while (rs.next()) {
				PubCarinfo car = new PubCarinfo();
				car.setFcarnum(rs.getString("FCARNUM"));
				car.setId(rs.getLong("ID"));
				car.setBname(rs.getString("BNAME"));
				car.setLname(rs.getString("LNAME"));
				car.setFcarcode(rs.getString("FCARCODE"));
				car.setBlogo(imgUrl+rs.getString("FLOGO"));
				car.setVname(rs.getString("VNAME"));
				carList.add(car);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			DButil.closeDb(conn, ps, rs);
		}
		return carList;
	}

	@Override
	public boolean addBindBox(long boxid, long fclid,String pwd) {
		Connection conn = null;
		conn = getSessionFactory().getCurrentSession().connection();
		PreparedStatement ps = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String date = sdf.format(new Date());	
		String sql = "UPDATE PUB_BOX SET FCLID='" + fclid + "',FPASSWORD='"+pwd+"',FJHTIME=to_date('"+date+"','YYYY-MM-DD HH24:MI:SS') WHERE ID='"+ boxid + "'";
		System.out.println(sql.toString());
		boolean bool = false;
		try {
			ps = conn.prepareStatement(sql.toString());
			int i = ps.executeUpdate();
			if (i > 0) {
				bool = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			DButil.closeDb(conn, ps, null);
		}
		return bool;
	}

	/**
	 * 解绑（删除）盒子
	 */
	@Override
	public int jiebangBox(long boxId) {
		Connection conn = null;
		PreparedStatement ps = null;
		conn = getSessionFactory().getCurrentSession().connection();
		String sql = " UPDATE PUB_BOX SET FCLID=NULL WHERE ID='" + boxId + "' ";
		int result = 0;
		try {
			ps = conn.prepareStatement(sql);
			result = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			DButil.closeDb(conn, ps, null);
		}
		return result;
	}

	/**
	 * 修改默认盒子
	 */
	@Override
	public int setDefBox(long cusId, long clId) {
		Connection conn = null;
		PreparedStatement ps = null;
		conn = getSessionFactory().getCurrentSession().connection();
		String sql = " UPDATE PUB_CARINFO SET FDEFAULT=0 WHERE FCUSTOMER='"
				+ cusId + "' ";
		System.out.println(sql);
		String upDefsql = " UPDATE PUB_CARINFO SET FDEFAULT = 1 WHERE ID = '"
				+ clId + "' ";
		int result = 0;
		try {
			ps = conn.prepareStatement(sql);
			result = ps.executeUpdate();
			ps = conn.prepareStatement(upDefsql);
			result += ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			DButil.closeDb(conn, ps, null);
		}
		return result;
	}

	/**
	 * 根据盒子信息系查询车型对照表
	 */
	public CnDiagsoft getCnDiagsoft(PubBox pubBox) {

		if (pubBox.getPubCarinfo() != null&&pubBox.getPubCarinfo().getPubLine()!=null) {
			// 根据盒子信息查询关联表
			PubOdblinerel pubOdblinerel = (PubOdblinerel) getSessionFactory()
					.getCurrentSession().createQuery(//
							"from PubOdblinerel where pubLine=?")
					.setParameter(0, pubBox.getPubCarinfo().getPubLine())
					.uniqueResult();
			// 查询Obd车型表
			if (pubOdblinerel != null
					&& pubOdblinerel.getPubOdbvehicle() != null) {
				PubOdbvehicle pubOdbvehicle = (PubOdbvehicle) getSessionFactory()
						.getCurrentSession()
						.createQuery(//
								"from PubOdbvehicle where id=?")
						.setParameter(0,
								pubOdblinerel.getPubOdbvehicle().getId())
						.uniqueResult();
				if (pubOdbvehicle != null&&pubOdbvehicle.getDetailid()!=null) {
					CnDiagsoft cnDiagsoft = (CnDiagsoft) getSessionFactory()
							.getCurrentSession().createQuery(//
									"from CnDiagsoft where detailId=?")
							.setParameter(0, Long.parseLong(pubOdbvehicle.getDetailid()))
							.uniqueResult();
					return cnDiagsoft;
				} else {
					return null;
				}
			} else {
				return null;
			}
		} else {
			return null;
		}
	}

	@Override
	public PubBox getPubBoxByFclid(Long fclid) {
		Connection conn = null;
		PreparedStatement ps = null;
		conn = getSessionFactory().getCurrentSession().connection();
		ResultSet rs = null;
		String sql = "select * from PUB_BOX where FCLID = " + fclid;
		PubBox pubBox = new PubBox();
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				pubBox.setId(rs.getLong("id"));
				pubBox.setFxlh(rs.getString("FXLH"));
				pubBox.setFjhyf(rs.getLong("FJHYF"));
				pubBox.setFjhtime(rs.getTimestamp("FJHTIME"));
				pubBox.setFedition(rs.getString("FEDITION"));
				pubBox.setFbdate(rs.getTimestamp("FBDATE"));
				pubBox.setFyjedition(rs.getString("FYJEDITION"));
				pubBox.setGps(rs.getLong("GPS"));
				pubBox.setWifi(rs.getLong("WIFI"));
				pubBox.setBlue(rs.getLong("BLUE"));
				pubBox.setFpassword(rs.getString("FPASSWORD"));
				pubBox.setCarid(fclid);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DButil.closeDb(conn, ps, rs);
		}
		return pubBox;
	}

	/**
	 * 通过客户的id查看盒子的信息
	 * @author sududa
	 * @date 2016-10-28
	 * @param loginId
	 * @return
	 */
	@Override
	public Map<String, Object> findByCusId(String loginId) {
		String sql = "select box.ID,box.FJHTIME,box.FJHYF"
				   + " from PUB_BOX box"
				   + " where box.FCLID = "
				   + " (select car.ID from PUB_CARINFO car"
				   + " where car.FCUSTOMER = ? and car.FDEFAULT =?"
				   + ")";
		return findOneForJdbc(sql, loginId, 1);
	}

}
