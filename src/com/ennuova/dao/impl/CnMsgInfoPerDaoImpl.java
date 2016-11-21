package com.ennuova.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.solr.common.util.Hash;
import org.springframework.stereotype.Repository;

import com.ennuova.base.impl.DaoSupportImpl;
import com.ennuova.dao.CnMsgInfoPerDao;
import com.ennuova.entity.CnInfo;
import com.ennuova.entity.CnInfodetail;
import com.ennuova.entity.CnMsgInfoPer;
import com.ennuova.util.DButil;

/**消息Dao实现
 * @author lee
 * @time 2016-9-25 下午9:05:19
 */
@Repository
public class CnMsgInfoPerDaoImpl extends DaoSupportImpl<CnMsgInfoPer> implements CnMsgInfoPerDao {

	@Override
	public List<Map<String, Object>> getMsgList(String receiveId, int rType) {
		String sql = "select * from" +
				" (select id,CONTENT,nick,head_img,sender,create_date,sender_type,row_number() over(partition by SENDER order by id desc) rn" +
				" from CN_MSG_INFO_PER where RECIPIENT = ? and RECIPIENT_TYPE=? and IS_READ = 0 and DEL_FLAG = 0)" +
				" where rn=1 order by id desc";
		return findForJdbc(sql, receiveId,rType);
	}

	@Override
	public List<Map<String, Object>> getMsgDetailList(String receiveId,
			int rType, String sender, Long startIndex, Long stopIndex) {
		String sql = "select * from (select id,headImg" +
				",nick,createDate" +
				",content,jumpId,msgType,RESERVETYPE,RESERVESTATE,rownum as rn from" +
				" (select p.ID as id,p.HEAD_IMG as headImg" +
				",p.NICK as nick,(to_char(p.CREATE_DATE,'yyyy-mm-dd HH24:mi:ss')) as createDate" +
				",m.CONTENT content,m.JUMP_ID as jumpId,m.MSG_TYPE as msgType,m.RESERVE_TYPE as RESERVETYPE,m.RESERVE_STATE as RESERVESTATE" +
				" from CN_MSG_INFO_PER p left join CN_MSG_INFO m" +
				" on p.MSG_INFO_ID = m.id" +
				" where RECIPIENT = ?" +
				" and SENDER = ?" +
				" and RECIPIENT_TYPE = ?" +
				" and DEL_FLAG = 0 order by createDate desc))" +
				"where rn>? and rn<=?";
//			String sql = "select * from" +
//					" (select p.ID as id,p.HEAD_IMG as headImg" +
//					",p.NICK as nick,p.CREATE_DATE as createDate" +
//					",m.CONTENT content,m.JUMP_ID as jumpId,m.MSG_TYPE as msgType" +
//					",rownum as rn from CN_MSG_INFO_PER p left join CN_MSG_INFO m" +
//					" on p.MSG_INFO_ID = m.id" +
//					" where RECIPIENT = ?" +
//					" and SENDER = ?" +
//					" and RECIPIENT_TYPE = ?" +
//					" and DEL_FLAG = 0)" +
//					" where rn>? and rn<=?";
		return findForJdbc(sql, receiveId,sender,rType,startIndex,stopIndex);
	}

	@Override
	public int setReader(String receiveId, String sender) {
		String sql = "update CN_MSG_INFO_PER set IS_READ = 1 where RECIPIENT = ? and SENDER = ? and IS_READ = 0";
		return executeSql(sql, receiveId,sender);
	}

	@Override
	public Map<String, Long> getBage(String receiveId) {
		Map<String, Long> map = new HashMap<String, Long>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		conn = getSessionFactory().getCurrentSession().connection();
		String sql = "select count(1) as bage,sender from CN_MSG_INFO_PER where recipient='"+receiveId+"' and is_read=0 and DEL_FLAG = 0 group by sender ";
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				map.put(rs.getString("sender"), rs.getLong("bage"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			DButil.closeDb(conn, ps, rs);
		}
		
		return map;
	}
	
	@Override
	public int deleteMsgByStaffIdAndCusId(String receiveId, String sender) {
		String sql = "update CN_MSG_INFO_PER set DEL_FLAG = 1 where recipient = ? and sender = ? and DEL_FLAG = 0";
		return executeSql(sql, receiveId,sender);
	}
	

}
