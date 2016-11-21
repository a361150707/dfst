package com.ennuova.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DButil {
	private static String url="jdbc:oracle:thin:@Data.linkbba.com:1521:orcl";
	private static String user="innov";
	private static String password="innov_1234";
	public static Connection getConn(){
		Connection conn=null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn=DriverManager.getConnection(url, user, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
		 
	}
	public static void closeDb(Connection conn,Statement stat,ResultSet rs){
		try {
			if (rs !=null) {
				rs.close();
			}
			if (stat !=null) {
				stat.close();
			}
			if (conn !=null) {
				conn.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		
	}
}
