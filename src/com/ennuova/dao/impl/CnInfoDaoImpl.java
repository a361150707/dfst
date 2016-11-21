package com.ennuova.dao.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.springframework.stereotype.Repository;

import com.ennuova.base.impl.DaoSupportImpl;
import com.ennuova.dao.CnInfoDao;
import com.ennuova.entity.CnInfo;
import com.ennuova.entity.CnInfodetail;
import com.ennuova.entity.PubBox;
import com.ennuova.push.InnovPush;
import com.ennuova.util.Constant;
import com.ennuova.util.DButil;

@Repository("cnInfoDao")
public class CnInfoDaoImpl extends DaoSupportImpl<CnInfo> implements CnInfoDao {

	/**
	 * 获取消息列表
	 */
	@Override
	public List<CnInfo> getCusMsg(long cusId) {
		Connection conn = null;
		PreparedStatement ps = null;
		conn = getSessionFactory().getCurrentSession().connection();
		String sql = "SELECT DISTINCT INFO.FMODULE,FSUBJECT,min(INFO.FCONTENT) FCONTENT,INFO.S_LINK,DETAIL.R_RECUSER,SUM(CASE WHEN DETAIL.D_READTIME IS NULL THEN 1 ELSE 0 END) NOTREADNUM FROM CN_INFO INFO LEFT JOIN CN_INFODETAIL DETAIL ON INFO.ID=DETAIL.R_MSGID WHERE DETAIL.R_RECUSER = '"
				+ cusId
				+ "' AND DETAIL.B_STATE=1 group by FMODULE,FSUBJECT,S_LINK,R_RECUSER ORDER BY NOTREADNUM DESC";
		System.out.println(sql);
		List<CnInfo> infoList = new ArrayList<CnInfo>();
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				CnInfo info = new CnInfo();
				info.setFmodule(rs.getString("FMODULE"));
				info.setFsubject(rs.getString("FSUBJECT"));
				info.setFcontent(rs.getString("FCONTENT"));
				info.setSLink(rs.getString("S_LINK"));
				info.setCusId(rs.getLong("R_RECUSER"));
				info.setNotReadNum(rs.getLong("NOTREADNUM"));
				infoList.add(info);
			}
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			DButil.closeDb(conn, ps, rs);
		}
		return infoList;
	}

	/**
	 * 获取模块消息详情
	 * 
	 * @param fsubject
	 * @param cusId
	 * @return
	 */
	@Override
	public List<CnInfo> getDetailMsg(String fsubject, long cusId) {
		Connection conn = null;
		PreparedStatement ps = null;
		conn = getSessionFactory().getCurrentSession().connection();
		String sql = "SELECT INFO.FMODULE,INFO.FSUBJECT,INFO.FCONTENT,INFO.ID,INFO.S_LINK,INFO.FSENDTIME,DETAIL.ID DETAILID,DETAIL.R_RECUSER FROM CN_INFO INFO LEFT JOIN CN_INFODETAIL DETAIL ON INFO.ID=DETAIL.R_MSGID WHERE INFO.FSUBJECT='"
				+ fsubject
				+ "' AND DETAIL.R_RECUSER = '"
				+ cusId
				+ "' AND DETAIL.B_STATE=1 ";
		System.out.println(sql);
		List<CnInfo> infoList = new ArrayList<CnInfo>();
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				CnInfo info = new CnInfo();
				info.setId(rs.getLong("DETAILID"));
				info.setFmodule(rs.getString("FMODULE"));
				info.setFsubject(rs.getString("FSUBJECT"));
				info.setFcontent(rs.getString("FCONTENT"));
				info.setSLink(rs.getString("S_LINK"));
				info.setCusId(rs.getLong("R_RECUSER"));
				info.setFsendtime(rs.getTimestamp("FSENDTIME"));
				info.setDetailId(rs.getLong("DETAILID"));
				info.setStatus(0l);
				infoList.add(info);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			DButil.closeDb(conn, ps, rs);
		}
		return infoList;
	}

	/**
	 * 更新模块消息已读
	 * 
	 * @param fsubject
	 * @param cusId
	 * @return
	 */
	@Override
	public int updateReadMsg(String fmodule, long cusId) {
		Connection conn = null;
		PreparedStatement ps = null;
		conn = getSessionFactory().getCurrentSession().connection();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String date = sdf.format(new Date());
		String sql = "UPDATE CN_INFODETAIL SET D_READTIME = to_date('"
				+ date
				+ "','YYYY-MM-DD HH24:MI:SS') WHERE R_MSGID IN (SELECT INFO.ID FROM CN_INFO INFO LEFT JOIN CN_INFODETAIL DETAIL ON INFO.ID=DETAIL.R_MSGID WHERE INFO.FMODULE ='"+fmodule+"' AND DETAIL.R_RECUSER="+cusId+" AND DETAIL.B_STATE=1)";
		System.out.println("sql:"+sql);
		int result = 0;
		try {
			ps = conn.prepareStatement(sql);
			result = ps.executeUpdate();
		} catch (Exception e) {
			result = 0;
			e.printStackTrace();
		}finally{
			DButil.closeDb(conn, ps, null);
		}
		return result;
	}

	/**
	 * 删除模块消息
	 * 
	 * @param fsubject
	 * @param cusId
	 * @return
	 */
	@Override
	public int deleteSubjectMsg(String fsubject, long cusId) {
		Connection conn = null;
		PreparedStatement ps = null;
		conn = getSessionFactory().getCurrentSession().connection();
		String sql = "UPDATE CN_INFODETAIL SET B_STATE = 0 WHERE R_MSGID IN (SELECT INFO.ID FROM CN_INFO INFO WHERE INFO.FSUBJECT='"
				+ fsubject + "') AND R_RECUSER='" + cusId + "'";
		int result = 0;
		try {
			ps = conn.prepareStatement(sql);
			result = ps.executeUpdate();
		} catch (Exception e) {
			result = 0;
			e.printStackTrace();
		}finally{
			DButil.closeDb(conn, ps, null);
		}
		return result;
	}

	/**
	 * 删除具体消息
	 * 
	 * @param detailId
	 * @return
	 */
	@Override
	public int deleteMsg(List<Long> ids) {
		Connection conn = null;
		PreparedStatement ps = null;
		conn = getSessionFactory().getCurrentSession().connection();
		int result = 0;
		StringBuffer sql = new StringBuffer(" UPDATE CN_INFODETAIL SET B_STATE = 0 WHERE ID in (");
		if(ids!=null && ids.size()>0){
			for (int i = 0; i < ids.size(); i++) {
				if(i==ids.size()-1){
					sql.append("'"+ids.get(i)+"'");
				}else{					
					sql.append("'"+ids.get(i)+"',");
				}
			}
			sql.append(")");
			System.out.println(sql.toString());
			try {
				 ps = conn.prepareStatement(sql.toString());
				 result = ps.executeUpdate();
				 conn.close();
			 } catch (Exception e) {
				 result = 0;
				 e.printStackTrace();
			 }
		}
		return result;
	}

	@Override
	public int getNoReadMeg(long cusId) {
		int noRead = 0;
		Connection conn = null;
		ResultSet rs = null;
		conn = getSessionFactory().getCurrentSession().connection();
		PreparedStatement ps = null;
		/*// 创建存储过程的对象
		CallableStatement c = null;
		try {
			System.out.println(cusId);
			c = conn.prepareCall("call SP_OW_INFORETURN(?,?)");
			// 给存储过程的参数设置值
			c.setLong(1, cusId); // 将第一个参数的值设置成100
			c.registerOutParameter(2, java.sql.Types.NUMERIC);
			// 执行存储过程
			c.execute();
			noRead = c.getInt(2);
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}*/
		String sql = "select count(1) as num from cn_infodetail where R_RECUSER = "+cusId+" and D_READTIME is null and MESSAGE_TYPE is not null and DEL_FLAG = 0";
		try {
			ps=conn.prepareStatement(sql);
			rs=ps.executeQuery();
			while(rs.next()){
				noRead = rs.getInt("num");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			DButil.closeDb(conn, ps, rs);
			
		}
		return noRead;
	}

	/**
	 * 添加obd消息
	 * 
	 * @param pubBox
	 * @param fcode
	 */
	@Override
	public void addMsg(PubBox pubBox, String fcode) {

		CallableStatement statement;
		try {
			
			System.out.println(pubBox.getFxlh()+"-->推送obd消息");
			statement = getSessionFactory().getCurrentSession().connection()
					.prepareCall("{call sp_ow_bjtx(?, ? ,? , ?,?)}");
			statement.setInt(1, pubBox.getId().intValue());
			statement.setString(2, fcode);
			statement.registerOutParameter(3, Types.INTEGER);
			statement.registerOutParameter(4, Types.VARCHAR);
			statement.registerOutParameter(5, Types.VARCHAR);
			statement.execute();

			int tx = statement.getInt(3);
			String message = statement.getString(4);
			String alies = statement.getString(5);
			if (tx == 1&&("FDXX001".equals(fcode)||"CLGZ001".equals(fcode)))
				InnovPush.testSendPush(alies, message, 3,Constant.MASRER_CZ_SECRET,Constant.APP_CZ_KEY);

		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * 获取个人中心消息
	 * @param detail
	 * @return
	 * @author 伟灿
	 */
	@Override
	public List<CnInfo> queryUserMsg(CnInfo detail,int startIndex,int stopIndex) {
		Connection conn = null;
		PreparedStatement ps = null;
		conn = getSessionFactory().getCurrentSession().connection();
		StringBuffer sql = new StringBuffer("SELECT * FROM (SELECT A.*, ROWNUM RN FROM "
				    +" ( SELECT INFO.ID,INFO.FMODULE,FSUBJECT,INFO.FCONTENT FCONTENT,INFO.FSENDTIME, "
				    +" INFO.S_LINK,DETAIL.R_RECUSER,DETAIL.ID DETAILID,DETAIL.D_READTIME READTIME FROM CN_INFO INFO LEFT JOIN CN_INFODETAIL DETAIL "
                    +" ON INFO.ID=DETAIL.R_MSGID WHERE 1=1 ");
		if(detail != null){
			if(detail.getCusId() != null){
				sql.append(" AND DETAIL.R_RECUSER = '"+detail.getCusId()+"' ");
			}
			if(detail.getFmodule() != null && !detail.getFmodule().equals("")){
				sql.append(" AND INFO.FMODULE LIKE '%"+detail.getFmodule()+"%' ");
			}
		}
		sql.append(" AND DETAIL.B_STATE=1 ORDER BY INFO.FSENDTIME DESC ) A WHERE ROWNUM<="+stopIndex+" ) WHERE RN>"+startIndex+" "); 
		System.out.println(sql);
		ResultSet rs = null;
		List<CnInfo> infoList = new ArrayList<CnInfo>();
		long notRead = 0;
		try {
			ps = conn.prepareStatement(sql.toString());
			rs = ps.executeQuery();
			while (rs.next()) {
				CnInfo info = new CnInfo();
				info.setId(rs.getLong("ID"));
				info.setFsendtime(rs.getTimestamp("FSENDTIME"));
				info.setFmodule(rs.getString("FMODULE"));
				info.setFsubject(rs.getString("FSUBJECT"));
				info.setFcontent(rs.getString("FCONTENT"));
				info.setSLink(rs.getString("S_LINK"));
				info.setCusId(rs.getLong("R_RECUSER"));
				info.setDetailId(rs.getLong("DETAILID"));
				if(rs.getTimestamp("READTIME") == null){
					notRead++;
				}
				infoList.add(info);
			}
			if(infoList.size()>0){
				infoList.get(0).setNotReadNum(notRead);
			}
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return infoList;
	}

	@Override
	public List<CnInfodetail> getMessageListNum(Long cusId) {
		List<CnInfodetail> cnInfodetails = new ArrayList<CnInfodetail>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		conn = getSessionFactory().getCurrentSession().connection();
		String sql = "select R_MSGID,MESSAGE_TYPE from CN_INFODETAIL where R_RECUSER="+cusId+" and D_READTIME is null and MESSAGE_TYPE is not null order by id desc";
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				CnInfodetail cnInfodetail = new CnInfodetail();
				CnInfo cnInfo = new CnInfo();
				cnInfo.setId(rs.getLong("R_MSGID"));
				cnInfodetail.setCnInfo(cnInfo);
				cnInfodetail.setMessageType(rs.getLong("MESSAGE_TYPE"));
				cnInfodetails.add(cnInfodetail);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			DButil.closeDb(conn, ps, rs);
		}
		return cnInfodetails;
	}

	@Override
	public List<CnInfodetail> getCnInfodetails(Long cusId,Long messageType,Long maxId) {
		List<CnInfodetail> cnInfodetails = new ArrayList<CnInfodetail>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		conn = getSessionFactory().getCurrentSession().connection();
		String sql = "select * from (select id,R_MSGID,D_READTIME from CN_INFODETAIL where id<"+maxId+" and R_RECUSER="+cusId+" and MESSAGE_TYPE = "+messageType+" order by id desc) where rownum<=6";
		System.out.println(sql);
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				CnInfodetail cnInfodetail = new CnInfodetail();
				cnInfodetail.setId(rs.getLong("id"));
				CnInfo cnInfo = new CnInfo();
				cnInfo.setId(rs.getLong("R_MSGID"));
				cnInfodetail.setCnInfo(cnInfo);
				cnInfodetail.setDReadtime(rs.getTimestamp("D_READTIME"));
				cnInfodetails.add(cnInfodetail);
			}
		} catch (Exception e) { 
			e.printStackTrace();
		}finally{
			DButil.closeDb(conn, ps, rs);
		}
		return cnInfodetails;
	}

	@Override
	public List<CnInfo> getCnInfosByIdList(String idList) {
		List<CnInfo> cnInfos = new ArrayList<CnInfo>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		conn = getSessionFactory().getCurrentSession().connection();
		String sql = "select ID,FCONTENT,FSENDTIME from cn_info where id in ("+idList+") order by id desc";
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				CnInfo cnInfo = new CnInfo();
				cnInfo.setId(rs.getLong("ID"));
				cnInfo.setFcontent(rs.getString("FCONTENT"));
				cnInfo.setFsendtime(rs.getTimestamp("FSENDTIME"));
				cnInfos.add(cnInfo);
			}
		} catch (Exception e) { 
			e.printStackTrace();
		}finally{
			DButil.closeDb(conn, ps, rs);
		}
		return cnInfos;
	}

	@Override
	public int updateBrage(int messageType, Long cudId) {
		int lineNum = 0;
		Connection conn = null;
		PreparedStatement ps = null;
		conn = getSessionFactory().getCurrentSession().connection();
		String sql = "update CN_INFODETAIL set d_readtime = sysdate where r_recuser = "+cudId+" and message_type="+messageType;
		try {
			ps = conn.prepareStatement(sql);
			lineNum  = ps.executeUpdate();
			
		} catch (Exception e) { 
			e.printStackTrace();
		}finally{
			DButil.closeDb(conn, ps, null);
		}
		return lineNum;
	}

	@Override
	public boolean addMsg(String messageContent, int type, long userId,int messageType) {
		//消息类型 1-故障 2-防盗 3-驾驶 4-安全 5-车务 6-资讯 7-意见反馈 8-预约信息 9-系统消息

		String fsubject = "";
		if(messageType==6){fsubject = "资讯";}else if(messageType==7){fsubject = "意见反馈";}else if(messageType==8){fsubject = "预约信息";}else if(messageType==9){fsubject = "系统信息";}
		if(type==1){
			String sql = "insert into CN_INFO (id,fmodule,fsubject,fsendtime,fcontent,message_type) values(seq_cn_info.nextval,'系统','"+fsubject+"',sysdate,'"+messageContent+"',"+messageType+")";
		}
		return false;
	}
	@Override
	public boolean alertMessage(Long userId) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		conn = getSessionFactory().getCurrentSession().connection();
		
		boolean success = false;
		
		String sql = "select sbinfo.fsim as sim from pub_carinfo car inner join pub_box box on car.id=box.fclid left join cn_sbljinfo sbinfo on box.fxlh = sbinfo.fsbxlh  where car.fdefault=1 and car.fcustomer="+userId;
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()){
				String sbInfo = rs.getString("sim");
				if(sbInfo.indexOf("898606150100230")>-1){
					success = true;
				}
			}
		} catch (Exception e) { 
			e.printStackTrace();
			return false;
		}finally{
			DButil.closeDb(conn, ps, rs);
		}
		return success;
	}

}
