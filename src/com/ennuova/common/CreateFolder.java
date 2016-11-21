package com.ennuova.common;

import java.io.File;

/**
 * 判断文件夹是否存在,不存在则自动创建
 * 
 * @author lin
 */
public class CreateFolder {
	
	/**
	 * 判断文件夹是否存在,不存在则自动创建
	 * @param path 为文件夹路径
	 */
	public void createFld(String path){
		File filePath = new File(path);
		if(!filePath.exists()){	// 判断文件夹是否存在,文件夹不存在，则创建文件夹
			filePath.mkdir();	// 自动创建
		}
	}
}