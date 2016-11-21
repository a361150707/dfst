package com.ennuova.util;

import java.net.Socket;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;

import oracle.jdbc.OracleTypes;

public class JDBC {
	public void getOracle(long fxlh){
		 Connection conn = null;
		 ResultSet rs = null;
		 conn = DButil.getConn();
		PreparedStatement ps = null;
		//String sql = "insert into CN_INFODETAIL(id,r_msgid,r_recuser,b_state,message_type) values(SEQ_cn_infodetail.nextval,83505,"+id+",1,8)";
			//String sql = "updata PUB_GJUPPER set url ='http://Data.linkbba.com:8080/InnovPic/DOWNLOAD_16.54.BIN' where module_ver = '16.53'";
		String sql = "insert into pub_box (id,fxlh,fjhyf) values(SEQ_PUB_BOX.nextval,'"+fxlh+"',0)";
		//System.out.println(sql);
	/*	String sql = "insert into pub_gjupper (id,MODULE_NAME,MODULE_VER,URL,SBXLH,UPLOADTIME,CREATE_DATE,UPDATE_DATE,DEL_FLAG)" +
				" values(SEQ_PUB_GJUPPER.nextval,'DOWNLOAD.BIN','16.52','http://Data.linkbba.com:8080/InnovPic/DOWNLOAD_goloX_530.bin','"+fxlh+"'," +
				"sysdate,sysdate,sysdate,0)";
		System.out.println(sql);*/
		try {
			//ps = conn.prepareStatement(sql);
/*			int ss = ps.executeUpdate();*/
		/*	String sql = "insert into pub_gjupper (id,MODULE_NAME,MODULE_VER,URL,SBXLH,UPLOADTIME,CREATE_DATE,UPDATE_DATE,DEL_FLAG)" +
					" values(SEQ_PUB_GJUPPER.nextval,'golo_lc.apk','14.06','http://Data.linkbba.com:8080/InnovPic/golo_lc_8.22.apk','"+fxlh+"'," +
					"sysdate,sysdate,sysdate,0)";
			ps = conn.prepareStatement(sql);
			int ss = ps.executeUpdate();
			sql = "insert into pub_gjupper (id,MODULE_NAME,MODULE_VER,URL,SBXLH,UPLOADTIME,CREATE_DATE,UPDATE_DATE,DEL_FLAG)" +
					" values(SEQ_PUB_GJUPPER.nextval,'DOWNLOAD.BIN','16.53','http://Data.linkbba.com:8080/InnovPic/DOWNLOAD_goloX_V16.53.bin','"+fxlh+"'," +
					"sysdate,sysdate,sysdate,0)";
			ps = conn.prepareStatement(sql);
			ps.executeUpdate();
			sql = "insert into pub_gjupper (id,MODULE_NAME,MODULE_VER,URL,SBXLH,UPLOADTIME,CREATE_DATE,UPDATE_DATE,DEL_FLAG)" +
					" values(SEQ_PUB_GJUPPER.nextval,'rcuGuardService.apk','14.05','http://Data.linkbba.com:8080/InnovPic/golo_hangye_guardService.apk','"+fxlh+"'," +
					"sysdate,sysdate,sysdate,0)";
			ps = conn.prepareStatement(sql);*/
			//System.out.println(new Date().getTime());
			ps = conn.prepareStatement(sql);
			ps.execute();
			//System.out.println(new Date().getTime());
			
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally{
        	DButil.closeDb(conn, ps, rs);
        }
	}
	/*insert into pub_box (id,fxlh,fjhyf) values(SEQ_PUB_BOX.nextval,'9500',0)
	1468216740415
	1468216740473*/
	public void getFxlhList(){
		 Connection conn = null;
		 ResultSet rs = null;
		 conn = DButil.getConn();
		PreparedStatement ps = null;
		String sql = "select id from pub_customer";
		System.out.println(sql);
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()){
				Long id = rs.getLong("id");
				System.out.println(id);
				JDBC jdbc = new JDBC();
				//jdbc.getOracle(id);
			}
       } catch (SQLException e) {
           // TODO Auto-generated catch block
           e.printStackTrace();
       }finally{
       	DButil.closeDb(conn, ps, rs);
       }
	}
/*	public void getFxlhList(){
		 Connection conn = null;
		 ResultSet rs = null;
		 conn = DButil.getConn();
		PreparedStatement ps = null;
		String sql = "select * from recyclebin where type = 'TABLE' and to_date(droptime,'yyyy-mm-dd hh24:mi:ss') > to_date('2016-06-21:06:14:14','yyyy-mm-dd hh24:mi:ss') order by droptime desc";
		System.out.println(sql);
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()){
				String fxlh = rs.getString("object_name");
				System.out.println(fxlh);
				JDBC jdbc = new JDBC();
				jdbc.getOracle(fxlh);
			}
      } catch (SQLException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
      }finally{
      	DButil.closeDb(conn, ps, rs);
      }
	}*/
	
	public static void main(String[] args) {
		/*JDBC jdbc = new JDBC();
		for (long i = 973497501394l; i < 973497502000l; i++) {
			jdbc.getOracle(i);
		}*/
		try {
			Socket socket = new Socket("36.250.69.10", 7777);
			
			System.out.println(socket.getInetAddress());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/*public static void main(String[] args) {
		JDBC jdbc = new JDBC();
		jdbc.getFxlhList();
	}*/
	
}
