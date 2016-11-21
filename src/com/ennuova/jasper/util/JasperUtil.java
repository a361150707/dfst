package com.ennuova.jasper.util;

import java.io.InputStream;
import java.util.Properties;


public class JasperUtil {

	private static JasperUtil instance;
	private String licenseKey;
	private String user;
	private String password;
	
	
	public JasperUtil(){
		try {
			Properties prop = new Properties();
			InputStream in = this.getClass().getResourceAsStream("/jasper.properties");
			prop.load(in);
			this.setLicenseKey(prop.getProperty("licenseKey").trim());
			this.setUser(prop.getProperty("user").trim());
			this.setPassword(prop.getProperty("password").trim());
		} catch (Exception e) {
			this.setLicenseKey("");
			this.setUser("");
			this.setPassword("");
		}
	}
	public static JasperUtil getInstance() {
		if(instance == null){
			instance = new JasperUtil();
		}
		return instance;
	}

	public String getLicenseKey() {
		return licenseKey;
	}
	public void setLicenseKey(String licenseKey) {
		this.licenseKey = licenseKey;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
