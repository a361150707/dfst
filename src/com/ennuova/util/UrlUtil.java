package com.ennuova.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class UrlUtil {
	private static UrlUtil instance;
	private  String url;
	private  String imgurl;
	private String solrurl;
	private String logurl;
	private String logpath;
	private String imgsurl;

	private UrlUtil() {
		try {
			Properties prop = new Properties();
			InputStream in = this.getClass()
					.getResourceAsStream("/url.properties");
			prop.load(in);
			this.setImgurl(prop.getProperty("imgurl").trim());
			this.setUrl(prop.getProperty("url").trim());
			this.setSolrurl(prop.getProperty("solrurl").trim());
			this.setLogurl(prop.getProperty("logurl").trim());
			this.setLogpath(prop.getProperty("logpath").trim());
			this.setImgsurl(prop.getProperty("imgsurl").trim());
		} catch (IOException e) {
			this.setImgurl("");
			this.setUrl("");
			this.setSolrurl("");
			this.setLogurl("");
			this.setLogpath("");
			this.setImgsurl("");
		}
	}

	public static synchronized UrlUtil getInstance() {
		if (instance == null) {
			instance = new UrlUtil();
		}
		return instance;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getImgurl() {
		return imgurl;
	}

	public void setImgurl(String imgurl) {
		this.imgurl = imgurl;
	}

	public String getSolrurl() {
		return solrurl;
	}

	public void setSolrurl(String solrurl) {
		this.solrurl = solrurl;
	}

	public String getLogurl() {
		return logurl;
	}

	public void setLogurl(String logurl) {
		this.logurl = logurl;
	}

	public String getLogpath() {
		return logpath;
	}

	public void setLogpath(String logpath) {
		this.logpath = logpath;
	}

	public String getImgsurl() {
		return imgsurl;
	}

	public void setImgsurl(String imgsurl) {
		this.imgsurl = imgsurl;
	}

}
