package com.ennuova.util;

import java.sql.Connection;
import java.sql.SQLException;

import org.springframework.jdbc.datasource.DriverManagerDataSource;


public class SingtonConnection {
	private static SingtonConnection theObject;
	private static DriverManagerDataSource ds;

    /**
     * private constructor
     */
    private SingtonConnection() {
    	ds = new DriverManagerDataSource(); 
    	ds.setDriverClassName("oracle.jdbc.driver.OracleDriver"); 
    	ds.setUrl("jdbc:oracle:thin:@192.168.1.138:1521:GAORCL"); 
    	ds.setUsername("GJWX");
    	ds.setPassword("GJWX");
    }

    /**
     * �?��静�?字段是否为null，若为null则创建实例，否则直接返回实例
     *
     * @return the singleton object
     */
    public static Connection createMySingleton() {
        if (theObject == null)
            theObject = new SingtonConnection();

        try {
			return ds.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
		return null;
    }
}