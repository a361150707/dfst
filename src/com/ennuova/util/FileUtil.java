package com.ennuova.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author jimmy(王志明)
 *2016年4月26日
 */
public class FileUtil {
	
	// 手机端上传文件
	public static String uploadFile(MultipartFile upload, String savePath){
		File file = new File(savePath);
		if (!file.exists()) {
			file.mkdirs();
		}
		String fileName = UUID.randomUUID().toString() + upload.getOriginalFilename().substring(upload.getOriginalFilename().lastIndexOf("."), upload.getOriginalFilename().length());
		FileOutputStream fos = null;
		BufferedOutputStream bos = null;
		BufferedInputStream bis = null;
		try {
			fos = new FileOutputStream(savePath + "/" + fileName);
			bos = new BufferedOutputStream(fos);
			bis = new BufferedInputStream(upload.getInputStream());
			byte[] buffer = new byte[4096];
			int len = 0;
			while ((len = bis.read(buffer)) > 0) {
				bos.write(buffer, 0, len);
			}
			return fileName;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			try {
				if (bos != null) {
					bos.flush();
					bos.close();
				}
				if (fos != null) {
					fos.close();
				}
				if (bis != null) {
					bis.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
	}
	
	// Web端上传文件
	public static String uploadFile(File upload, String savePath, String uploadName) {
		File file = new File(savePath);
		if (!file.exists()) {
			file.mkdirs();
		}
		uploadName = UUID.randomUUID().toString() + uploadName.substring(uploadName.lastIndexOf("."), uploadName.length());
		FileOutputStream fos = null;
		BufferedOutputStream bos = null;
		FileInputStream fis = null;
		BufferedInputStream bis = null;
		try {
			fos = new FileOutputStream(savePath + "/" + uploadName);
			bos = new BufferedOutputStream(fos);
			fis = new FileInputStream(upload);
			bis = new BufferedInputStream(fis);
			byte[] buffer = new byte[4096];
			int len = 0;
			while ((len = bis.read(buffer)) > 0) {
				bos.write(buffer, 0, len);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			try {
				if (bos != null) {
					bos.flush();
					bos.close();
				}
				if (fos != null) {
					fos.close();
				}
				if (bis != null) {
					bis.close();
				}
				if (fis != null) {
					fis.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return uploadName;
	}
	
	public static byte[] getBytes(File file){  
		FileInputStream fis = null;
		BufferedInputStream bis = null;
		ByteArrayOutputStream bos = null;
        try {  
            fis = new FileInputStream(file);  
            bis = new BufferedInputStream(fis);
            bos = new ByteArrayOutputStream(1000);  
            byte[] buffer = new byte[1000];
            int count;
			while ((count = bis.read(buffer)) != -1) {
				bos.write(buffer, 0, count);
			}
            return bos.toByteArray();  
        } catch (Exception e) {  
            e.printStackTrace();  
            return null;
        } finally {
        	try {
				if (bos != null) {
					bos.flush();
					bos.close();
				}
				if (bis != null){
					bis.close();
				}
				if (fis != null) {
					fis.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
        }
    }  
	
	public static void displayFile(String filePath, HttpServletResponse response) {
		OutputStream outputStream = null;
		try {
			outputStream = response.getOutputStream();
			File file = new File(filePath);
			byte[] bytes = getBytes(file);
			response.setContentLength(bytes.length);
			outputStream.write(bytes, 0, bytes.length);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (outputStream != null) {
					outputStream.flush();
					outputStream.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	//删除指定文件
	public static void delFile(String datapath){
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		String realPath = request.getSession().getServletContext().getRealPath(datapath);
		File file =new File(realPath);
		//指定文件存在删除
		if (file.exists()) {
			file.delete();
		}
	}
	//删除指定文件夹下所有文件
	public static boolean delAllFile(String datapath) {
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		String realPath = request.getSession().getServletContext().getRealPath(datapath);
		boolean flag = false;
		File file = new File(realPath);
		if (!file.exists()) {
		return flag;
		}
		if (!file.isDirectory()) {
		return flag;
		}
		String[] tempList = file.list();
		File temp = null;
		for (int i = 0; i < tempList.length; i++) {
		if (realPath.endsWith(File.separator)) {
		temp = new File(realPath + tempList[i]);
		} else {
		temp = new File(realPath + File.separator + tempList[i]);
		}
		if (temp.isFile()) {
		temp.delete();
		}
		if (temp.isDirectory()) {
		delAllFile(realPath + "/" + tempList[i]);//先删除文件夹里面的文件
		flag = true;
		}
		}
		return flag;
		}
	// 下载服务端文件
	public static void downloadFile(String filePath, String fileName, HttpServletResponse response) {
		File file = new File(filePath);
		FileInputStream fis = null;
		BufferedInputStream bis = null;
		OutputStream os = null;
		BufferedOutputStream bos = null;
		try {
			fis = new FileInputStream(file);
			bis = new BufferedInputStream(fis);
			os = response.getOutputStream();
			bos = new BufferedOutputStream(os);
			response.setHeader("Content-disposition", "attachment;filename="+ new String(fileName.getBytes("GBK"), "ISO-8859-1"));
			byte[] buffer = new byte[4096];
			int count;
			while ((count = bis.read(buffer)) != -1) {
				bos.write(buffer, 0, count);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (bos != null) {
					bos.flush();
					bos.close();
				}
				if (os != null) {
					os.close();
				}
				if (bis != null){
					bis.close();
				}
				if (fis != null) {
					fis.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
}
