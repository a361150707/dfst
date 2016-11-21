package com.ennuova.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 数据库操作类
 * 
 * @author lin
 * @date 2015-09-08
 */

public class MySQLUtil{
	
	//@Autowired
	//private static SessionFactory sessionFactory;
	
	/**
	 * 获取Mysql数据库连�?
	 * 
	 * @return Connection
	 */
	public Connection getConn() {
		
		/*String url = "jdbc:oracle:thin:@localhost:1522:dataBase";
		String username = "sys as sysdba";
		String password = "sys";*/
		/*String url = "jdbc:oracle:thin:@192.168.1.138:1521:GAORCL";
		String username = "GJUser";
		String password = "000000";*/
		Connection conn = null;
		try {
			//加载MySQL驱动
			//Class.forName("oracle.jdbc.driver.OracleDriver");
//			conn = DriverManager.getConnection(url, username, password);
			
			//获取数据库连�?
//			ApplicationContext context = new ClassPathXmlApplicationContext("../springmvc-servlet.xml");      
//			DriverManagerDataSource ds = (DriverManagerDataSource)context.getBean("dataSource");      
//			conn = ds.getConnection();

//			SessionFactory sessionFactory = new AnnotationConfiguration().configure("applicationContext.xml").buildSessionFactory();
	//		conn = sessionFactory.getCurrentSession().connection();
 			
			conn = SingtonConnection.createMySingleton();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return conn;
	}
	

	/**
	 * 释放JDBC资源
	 * 
	 * @param conn 数据库连�?
	 * @param ps
	 * @param rs 记录�?
	 */
	private void releaseResources(Connection conn, PreparedStatement ps, ResultSet rs) {
		try {
			if (null != rs)
				rs.close();
			if (null != ps)
				ps.close();
			if (null != conn)
				conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 动�?获取索引
	 * 
	 * @param sql 查询table的SQL语句
	 * @return List<Map<String, Object>>
	 */
	public static List<Map<String, Object>> findAllData(String sql) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<Map<String, Object>> dataLst = new ArrayList<Map<String, Object>>();
		MySQLUtil mysqlUtil = new MySQLUtil();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ResultSetMetaData rsmd = null;
		
		try {
			String val = "";
			
			conn = mysqlUtil.getConn();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			rsmd = rs.getMetaData();
			while (rs.next()) {
				resultMap = new HashMap<String, Object>();
				for(int i = 1; i <= rsmd.getColumnCount(); i++){
					val = rsmd.getColumnName(i);
					resultMap.put(val, rs.getObject(val));	//把key跟value放到map�?
				}
				dataLst.add(resultMap);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			mysqlUtil.releaseResources(conn, ps, rs);
		}
		
		return dataLst;
	}
}