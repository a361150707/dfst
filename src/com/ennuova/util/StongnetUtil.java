package com.ennuova.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class StongnetUtil {

	private static StongnetUtil instance;
	
	private String strReg;
	private String strPwd;
	
	
	private StongnetUtil(){
		try {
			Properties prop = new Properties();
			InputStream in = this.getClass()
					.getResourceAsStream("/stongnet.properties");
			prop.load(in);
			this.setStrReg(prop.getProperty("strReg"));
			this.setStrPwd(prop.getProperty("strPwd"));
		} catch (IOException e) {
			this.setStrReg("");
			this.setStrPwd("");
		}
	}
	
	public static synchronized StongnetUtil getInstance(){
		if(instance == null){
			instance = new StongnetUtil();
		}
		return instance;
	}

	public String getStrReg() {
		return strReg;
	}

	public void setStrReg(String strReg) {
		this.strReg = strReg;
	}

	public String getStrPwd() {
		return strPwd;
	}

	public void setStrPwd(String strPwd) {
		this.strPwd = strPwd;
	}
	
	
}
