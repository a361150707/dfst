package com.ennuova.dao.impl;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.springframework.stereotype.Repository;

import com.ennuova.base.impl.DaoSupportImpl;
import com.ennuova.dao.CnExpressDao;
import com.ennuova.entity.CnExpress;
import com.ennuova.util.DButil;
/**老卡用户dao实现
 * @author zhihui
 *
 */
@Repository
public class CnExpressDaoImpl  extends DaoSupportImpl<CnExpress> implements CnExpressDao {

	@Override
	public CnExpress getCnExpress(long userId) {
		Connection conn = null;
		conn = getSessionFactory().getCurrentSession().connection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select id from Cn_Express where user_id = "+userId;
		CnExpress cnExpress = new CnExpress();
		try {
			ps=conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()){
				cnExpress.setId(rs.getLong("id"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			return cnExpress;
		}finally{
			DButil.closeDb(conn, ps, rs);	
		}
		return cnExpress;
	}
}
