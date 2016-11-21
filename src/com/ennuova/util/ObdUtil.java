package com.ennuova.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ObdUtil {
	private static ObdUtil instance;
	private  String obdIp;
	private  String port;

	private ObdUtil() {
		try {
			Properties prop = new Properties();
			InputStream in = this.getClass()
					.getResourceAsStream("/config.properties");
			prop.load(in);
			this.setPort(prop.getProperty("port").trim());
			this.setObdIp(prop.getProperty("obdIp").trim());
		} catch (IOException e) {
			this.setObdIp("");
			this.setPort("");
		}
	}

	public static synchronized ObdUtil getInstance() {
		if (instance == null) {
			instance = new ObdUtil();
		}
		return instance;
	}



	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getObdIp() {
		return obdIp;
	}

	public void setObdIp(String obdIp) {
		this.obdIp = obdIp;
	}
	

}
