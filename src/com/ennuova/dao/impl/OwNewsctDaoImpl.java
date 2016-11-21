package com.ennuova.dao.impl;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ennuova.base.impl.DaoSupportImpl;
import com.ennuova.dao.OwNewsctDao;
import com.ennuova.entity.OwNewsct;
import com.ennuova.util.StringUtil;
import com.ennuova.util.DButil;
import com.ennuova.util.UrlUtil;

@Repository("owNewsctDao")
public class OwNewsctDaoImpl extends DaoSupportImpl<OwNewsct> implements
		OwNewsctDao {
	@Override
	public List<OwNewsct> queryPageByState(long fstate,String fcompanyId,long ftype,int startIndex,int stopIndex) {
		BigDecimal a=BigDecimal.valueOf(fstate);
		Connection conn = null;
		conn=getSessionFactory().getCurrentSession().connection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		StringBuffer sql = null;
		List<OwNewsct> newsList=new ArrayList<OwNewsct>();
		if(startIndex==0){
			sql=new StringBuffer("SELECT ID,FBTIME,FNR_PIC,FTITLE,FTYPE,FNR_TEXT FROM OW_NEWSCT WHERE FSTATE='"+a+"' AND FCOMPANY='"+fcompanyId+"' AND fzhiding=1 AND 1=1 ");
			if(ftype==0){
				sql.append(" AND FTYPE != '2' ");
			}else{
				sql.append(" AND FTYPE = '"+ftype+"' ");
			}
			//System.out.println(sql);
			String urlPath=UrlUtil.getInstance().getImgurl();
			try {
				ps=conn.prepareStatement(sql.toString());
				rs=ps.executeQuery();
				while(rs.next()){	
					OwNewsct newsct=new OwNewsct();
					newsct.setId(rs.getLong("ID"));
					newsct.setFbtime(rs.getTimestamp("FBTIME"));
					newsct.setFnrPic(urlPath+rs.getString("FNR_PIC"));
					newsct.setFtitle(rs.getString("FTITLE"));
					newsct.setFtype(rs.getLong("FTYPE"));
					newsct.setFnrText(rs.getString("FNR_TEXT"));
					if(newsct.getFtype().equals(1l)){
						newsct.setTpNm("新闻中心");
					}else if(newsct.getFtype().equals(2l)){
						newsct.setTpNm("优惠促销");
					}else{
						newsct.setTpNm("新车上市");
					}
					newsList.add(newsct);
				}
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				DButil.closeDb(conn, ps, rs);
			}
			//System.out.println("size1:"+newsList.size());
		}
		sql=new StringBuffer(" SELECT * FROM (SELECT A.*,ROWNUM RN FROM (SELECT ID,FBTIME,FNR_PIC,FTITLE,FTYPE,FNR_TEXT FROM OW_NEWSCT WHERE FSTATE='"+a+"' AND FCOMPANY='"+fcompanyId+"' AND fzhiding!=1 AND 1=1 ");
		if(ftype==0){
			sql.append(" AND FTYPE != '2' ");
		}else{
			sql.append(" AND FTYPE = '"+ftype+"' ");
		}
		sql.append(" ORDER BY create_date DESC) A) where RN>"+startIndex+" and RN<="+stopIndex+"");
		//System.out.println(sql);
		String urlPath=UrlUtil.getInstance().getImgurl();
		try {
			ps=conn.prepareStatement(sql.toString());
			rs=ps.executeQuery();
			while(rs.next()){	
				OwNewsct newsct=new OwNewsct();
				newsct.setId(rs.getLong("ID"));
				newsct.setFbtime(rs.getTimestamp("FBTIME"));
				newsct.setFnrPic(urlPath+rs.getString("FNR_PIC"));
				newsct.setFtitle(rs.getString("FTITLE"));
				newsct.setFtype(rs.getLong("FTYPE"));
				newsct.setFnrText(rs.getString("FNR_TEXT"));
				if(newsct.getFtype().equals(1l)){
					newsct.setTpNm("新闻中心");
				}else if(newsct.getFtype().equals(2l)){
					newsct.setTpNm("优惠促销");
				}else{
					newsct.setTpNm("新车上市");
				}
				newsList.add(newsct);
			}
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			DButil.closeDb(conn, ps, rs);
		}
		//System.out.println("size:"+newsList.size());
		return newsList;
		/*getSessionFactory().getCurrentSession().createQuery(
				" from OwNewcar where fstate=? and fcompany=? order by fzhiding desc")
				.setParameter(0, a)
				.setParameter(1, fcompanyId)
				.list();*/
	}

	@Override
	public OwNewsct getOwNewsById(long id) {
		OwNewsct newsct=new OwNewsct();
		Connection conn=null;
		conn=getSessionFactory().getCurrentSession().connection();
		PreparedStatement ps=null;
		ResultSet rs=null;
		String sql=" SELECT NEWS.FTITLE,NEWS.FBTIME,NEWS.FVIEWCOUNT,NEWS.FNR_TEXT,U.DEPARTNAME FROM OW_NEWSCT NEWS LEFT JOIN T_S_DEPART U ON NEWS.SYS_ORG_CODE=U.ORG_CODE WHERE NEWS.ID='"+id+"'";
		//System.out.println(sql);
		try {
			ps=conn.prepareStatement(sql);
		    rs=ps.executeQuery();
		    if(rs.next()){
		    	newsct.setFtitle(rs.getString("FTITLE"));
		    	newsct.setFnrText(rs.getString("FNR_TEXT"));
		    	newsct.setFviewcount(rs.getBigDecimal("FVIEWCOUNT"));
		    	newsct.setUname(rs.getString("DEPARTNAME"));
		    	newsct.setFbtime(rs.getTimestamp("FBTIME"));
		    }
		    conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return newsct;
	}

	//根性浏览次数，tableName为要更新的模块
		@Override
		public int growthFviewCount(long id,String tableName) {
			Connection conn=null;
			conn=getSessionFactory().getCurrentSession().connection();
			PreparedStatement ps=null;
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			String date=sdf.format(new Date());
			//System.out.println(date);
			String sql=" UPDATE "+tableName+" SET FVIEWCOUNT=FVIEWCOUNT+1,FLASTVIEW=to_date('"+date+"','YYYY-MM-DD HH24:MI:SS') WHERE ID='"+id+"' ";
			int result=0;
			try {
				ps=conn.prepareStatement(sql);
				result=ps.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return result;
		}

		/**
		 * @author sududa
		 * @date 2016-07-29
		 * @param type
		 * @param keyword
		 * @return
		 */
		@Override
		public List<Map<String, Object>> findListRelation(String type, String keyword) {
			/*StringBuilder sql = new StringBuilder("select news.ID,news.FTITLE,news.FBTIME,news.FVIEWCOUNT,news.UNAME,"
					+ "news.GOOD_COUNT,news.SHARE_COUNT,news.COMMENT_COUNT,news.KEY_WORD"
				    + " from OW_NEWSCT news");
			if(StringUtil.isNotEmpty(type) && StringUtil.isNotEmpty(keyword)){
				sql.append(" where news.FSTATE = ? and nvl(news.DEL_FLAG,0) = ?"
						   + " and  and ");
			}else{
				
			}
			return findForJdbc(sql, objs);*/
			return null;
		}

		/**
		 * 查找新闻的详情
		 * @author sududa
		 * @date 2016-07-29
		 * @param newsId
		 * @return
		 */
		@Override
		public Map<String, Object> findDetailById(String newsId) {
			String sql = "SELECT NEWS.ID,NEWS.FTITLE,NEWS.FBTIME,NEWS.FVIEWCOUNT,NEWS.FNR_TEXT,NEWS.KEY_WORD as keyWord,NEWS.FTYPE,NEWS.GOOD_COUNT,NEWS.SHARE_COUNT,NEWS.COMMENT_COUNT,U.DEPARTNAME FROM OW_NEWSCT NEWS LEFT JOIN T_S_DEPART U ON NEWS.SYS_ORG_CODE=U.ORG_CODE WHERE NEWS.ID= ?";
			return findOneForJdbc(sql, newsId);
		}

		@Override
		public void findListRelation(String keyword) {
			
		}

		/**
		 * 查看所有的发布的，没有删除的新闻
		 * @author sududa
		 * @date 2016-07-29
		 * @return
		 */
		@Override
		public List<Map<String, Object>> findAllNews() {
			String sql = "select news.ID,news.FTITLE,news.FBTIME,news.FVIEWCOUNT,news.UNAME,"
					+ "news.GOOD_COUNT as goodCount,news.SHARE_COUNT as shareCount,"
					+ "news.COMMENT_COUNT as commentCount,news.KEY_WORD as keyWord"
				    + " from OW_NEWSCT news"
				    + " where news.FSTATE = ? and nvl(news.DEL_FLAG,0) = ?";
			return findForJdbc(sql, 2,0);
		}

		@Override
		public Integer updateCommentOrGoodOrShareCount(int type, Long id,int count) {
			String sql = "";
			switch (type) {
			case 0:
				sql = "update ow_newsct set COMMENT_COUNT = COMMENT_COUNT+"+count+" where id = ?";
				break;
			case 1:
				sql = "update ow_newsct set GOOD_COUNT = GOOD_COUNT+"+count+" where id = ?";
				break;
			case 2:
				sql = "update ow_newsct set SHARE_COUNT = SHARE_COUNT+"+count+" where id = ?";
				break;
			case 3:
				sql = "update ow_newsct set FVIEWCOUNT = FVIEWCOUNT+"+count+" where id = ?";
				break;
			}
			return executeSql(sql, id);
		}
		@Override
		public Long getNewCountByLid(Long lineId) {
			Connection conn = null;
			PreparedStatement ps = null;
			conn = getSessionFactory().getCurrentSession().connection();
			ResultSet rs = null;
			String sql = "select count(id) vnum from ow_newsct where FIS_LINE_DETAIL = 1 and FSTATE=2 and FLINEID = "+lineId;
			Long newsNum = 0l;
			try {
				ps = conn.prepareStatement(sql);
				rs = ps.executeQuery();
				if(rs.next()){
					newsNum = rs.getLong("vnum");
				}
			} catch (Exception e) {
				e.printStackTrace();
				return newsNum;
			}finally{
				DButil.closeDb(conn, ps, rs);
			}
			return newsNum;
		}

		@Override
		public Long getNewCountByKey(int type, Long typeId) {
			Connection conn = null;
			PreparedStatement ps = null;
			conn = getSessionFactory().getCurrentSession().connection();
			ResultSet rs = null;
			String sql = "select count(id) vnum from ow_newsct where FIS_LINE_DETAIL=0  and FSTATE=2 and  ";
			if(type==1){
				sql = sql+" FLINEID = "+typeId;
			}else{
				sql = sql+" FMODELID = "+typeId;
			}
			Long newsNum = 0l;
			try {
				ps = conn.prepareStatement(sql);
				rs = ps.executeQuery();
				if(rs.next()){
					newsNum = rs.getLong("vnum");
				}
			} catch (Exception e) {
				e.printStackTrace();
				return newsNum;
			}finally{
				DButil.closeDb(conn, ps, rs);
			}
			return newsNum;
		}

		@Override
		public List<OwNewsct> getNewsByLineIdOrVId(int type, Long typeId,
				int isDetatil,int startIndex,int stopIndex) {
			Connection conn = null;
			conn=getSessionFactory().getCurrentSession().connection();
			PreparedStatement ps = null;
			ResultSet rs = null;
			List<OwNewsct> newsList=new ArrayList<OwNewsct>();
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
			String sql = " SELECT * FROM (SELECT A.*,ROWNUM RN FROM (SELECT ID,FBTIME,FNR_PIC,FTITLE,FTYPE,FNR_TEXT,GOOD_COUNT,SHARE_COUNT,COMMENT_COUNT,KEY_WORD FROM OW_NEWSCT where FSTATE = 2 and ";
			if(isDetatil==1){
				sql = sql + "FIS_LINE_DETAIL = 1 and FLINEID = " + typeId + " ORDER BY FBTIME DESC) A) where RN>"+startIndex+" and RN<="+stopIndex;
			}else{
				if(type==1){
					sql = sql + "FIS_LINE_DETAIL = 0 and FLINEID = " + typeId + " ORDER BY FBTIME DESC) A) where RN>"+startIndex+" and RN<="+stopIndex;
				}else{
					sql = sql + "FIS_LINE_DETAIL = 0 and FMODELID = " + typeId + " ORDER BY FBTIME DESC) A) where RN>"+startIndex+" and RN<="+stopIndex;

				}
			}
			String urlPath=UrlUtil.getInstance().getImgurl();
			try {
				ps=conn.prepareStatement(sql);
				rs=ps.executeQuery();
				while(rs.next()){	
					OwNewsct newsct=new OwNewsct();
					newsct.setId(rs.getLong("ID"));
					newsct.setFbtime(rs.getTimestamp("FBTIME"));
					if(newsct.getFbtime()!=null){
						newsct.setTimeStr(dateFormat.format(newsct.getFbtime()));
					}
					newsct.setFnrPic(urlPath+rs.getString("FNR_PIC"));
					newsct.setFtitle(rs.getString("FTITLE"));
					newsct.setFtype(rs.getLong("FTYPE"));
					newsct.setFnrText(rs.getString("FNR_TEXT"));
					newsct.setGoodCount(rs.getLong("GOOD_COUNT"));
					newsct.setShareCout(rs.getLong("SHARE_COUNT"));
					newsct.setCommentCount(rs.getLong("COMMENT_COUNT"));
					newsct.setKeyWord(rs.getString("KEY_WORD"));
					if(newsct.getFtype().equals(1l)){
						newsct.setTpNm("新闻中心");
					}else if(newsct.getFtype().equals(2l)){
						newsct.setTpNm("优惠促销");
					}else if(newsct.getFtype().equals(3l)){
						newsct.setTpNm("新车上市");
					}else{
						newsct.setTpNm("金融方案");
					}
					newsList.add(newsct);
				}
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
				return newsList;
			}finally{
				DButil.closeDb(conn, ps, rs);
			}
			return newsList;
		}

		@Override
		public List<OwNewsct> getTopInfo() {
			Connection conn = null;
			conn=getSessionFactory().getCurrentSession().connection();
			PreparedStatement ps = null;
			ResultSet rs = null;
			StringBuffer sql = null;
			List<OwNewsct> newsList=new ArrayList<OwNewsct>();
			DateFormat dateFormat = new SimpleDateFormat("MM-dd");
			sql=new StringBuffer("SELECT ID,FBTIME,FNR_PIC,FTITLE,FTYPE,FNR_TEXT,GOOD_COUNT,SHARE_COUNT,COMMENT_COUNT,KEY_WORD FROM OW_NEWSCT WHERE DEL_FLAG=0 and FSTATE='"+2+"' AND fzhiding=1 AND 1=1 ");
			String urlPath=UrlUtil.getInstance().getImgurl();
			try {
				ps=conn.prepareStatement(sql.toString());
				rs=ps.executeQuery();
				while(rs.next()){	
					OwNewsct newsct=new OwNewsct();
					newsct.setId(rs.getLong("ID"));
					newsct.setFbtime(rs.getTimestamp("FBTIME"));
					if(newsct.getFbtime()!=null){
						newsct.setTimeStr(dateFormat.format(newsct.getFbtime()));
					}
					
					newsct.setFnrPic(urlPath+rs.getString("FNR_PIC"));
					newsct.setFtitle(rs.getString("FTITLE"));
					newsct.setFtype(rs.getLong("FTYPE"));
					newsct.setFnrText(rs.getString("FNR_TEXT"));
					newsct.setGoodCount(rs.getLong("GOOD_COUNT"));
					newsct.setShareCout(rs.getLong("SHARE_COUNT"));
					newsct.setCommentCount(rs.getLong("COMMENT_COUNT"));
					newsct.setKeyWord(rs.getString("KEY_WORD"));
					if(newsct.getFtype().equals(1l)){
						newsct.setTpNm("新闻中心");
					}else if(newsct.getFtype().equals(2l)){
						newsct.setTpNm("优惠促销");
					}else if(newsct.getFtype().equals(3l)){
						newsct.setTpNm("新车上市");
					}else{
						newsct.setTpNm("金融方案");
					}
					newsList.add(newsct);
				}
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				DButil.closeDb(conn, ps, rs);
			}
			return newsList;
		}
	
}
