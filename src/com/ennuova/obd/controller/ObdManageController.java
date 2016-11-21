package com.ennuova.obd.controller;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.ennuova.entity.CnSbljinfo;
import com.ennuova.obd.model.Modulelist;
import com.ennuova.obd.model.Querymodule;
import com.ennuova.obd.service.ObdManageService;
import com.ennuova.obd.service.OdbLoginService;
import com.ennuova.obd.thread.ListenSocket;
import com.ennuova.obd.tools.MD5Util;
import com.ennuova.obd.util.LogUtil;
import com.ennuova.obd.util.MessageUtil;
import com.ennuova.obd.util.StringByteUtil;
import com.ennuova.util.UrlUtil;

@Controller("obdManageController")
@RequestMapping("/access")
public class ObdManageController {

	@Resource
	ObdManageService obdManageService;

	@Resource
	OdbLoginService odbLoginService;

	/**
	 * 日志上传
	 * 
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/log/upload")
	public String logUpload(
			@RequestParam(value = "logfile", required = false) MultipartFile logfile,
			HttpServletResponse response, HttpServletRequest request)
			throws IOException {

		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setContentType("text/json;charset=utf-8");
		PrintWriter out = response.getWriter();
		System.out.println("ObdManageController.logUpload()");

		Querymodule querymodule = new Querymodule();
		try {

			String providerString = request.getParameter("provider");
			String device_id = request.getParameter("device_id");
			String dev_typeString = request.getParameter("dev_type");
			String dev_ver = request.getParameter("dev_ver");
			String sn = request.getParameter("sn");
			
			System.out.println("设备序列号--》" + device_id);
			
			if (providerString == null || providerString.equals("")//
					|| device_id == null || device_id.equals("")//
					|| dev_typeString == null || dev_typeString.equals("")//
					|| dev_ver == null || dev_ver.equals("")//
					|| sn == null || sn.equals("")) {
				querymodule.setStatus(102);
				querymodule.setMessage("参数有误");
			} else {
				int provider = Integer.parseInt(providerString);
				int dev_type = Integer.parseInt(dev_typeString);

				String sk = "edafef2737a3f647e4e2d655f4f29a25";
				String prefix = "obd";
				String param_string = null;
				param_string = "provider=" + provider + "device_id="
						+ device_id + "dev_type=" + dev_type + "dev_ver="
						+ dev_ver;
				String newsn = MD5Util.MD5(MD5Util.MD5(prefix + param_string)
						+ sk);
				if (!newsn.equals(sn)) {
					querymodule.setStatus(100);
					querymodule.setMessage("校验失败");
				} else {

					// 日志存放路径
					String path = UrlUtil.getInstance().getLogpath() + "/" +device_id;
					System.out.println("文件原名称--》"+logfile.getOriginalFilename());
					
					String fileName = System.currentTimeMillis() + ".zip";
					
					System.out.println(path + "/" + fileName);
					File targetFile = new File(path, fileName);
					if (!targetFile.getParentFile().exists()) {
						targetFile.getParentFile().mkdirs();
					}
					// 保存
					try {
						logfile.transferTo(targetFile);
						// 成功
						querymodule.setStatus(0);
						querymodule.setMessage("成功");

					} catch (Exception e) {
						e.printStackTrace();
						querymodule.setStatus(900);
						querymodule.setMessage("服务器内部错误");
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			querymodule.setStatus(900);
			querymodule.setMessage("服务器内部错误");
		}
		System.out.println("返回值--》"
				+ JSONObject.fromObject(querymodule).toString());
		out.write(JSONObject.fromObject(querymodule).toString());
		out.flush();

		return null;
	}

	/**
	 * 发送日志消息
	 * 
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/log/sendMessage")
	private void sendMessage() {
		
	    //用户集合
	    List<Socket> socketList = ListenSocket.socketList;
	    
	    for(Socket socket:socketList){
	    	String ip = socket.getInetAddress().toString();
			int port = socket.getPort();
			
			// 根据IP和端口号查询登录信息得到密钥
			CnSbljinfo cnSbljinfo = odbLoginService
					.findCnSbljinfoByIpAndPort(ip, port);
			
			try {
				System.out.println("进入sendMessage函数");// 用于测试到哪步出错了，测试完发现只能打印
				OutputStream oi = socket.getOutputStream();
				System.out.println("在sendMessage函数中建立了输出流");// 用于测试到哪步出错了
				String regmsg = MessageUtil.sendMessage(cnSbljinfo);
				System.out.println("盒子" + socketList.indexOf(socket) + ":服务回复消息--->" + regmsg);
				
				oi.write(StringByteUtil.hexStringToBytes(regmsg));
			} catch (Exception e) {
				System.out.println("发送消息给" + ip + "报异常!!!!!!!!!!");
				// e.printStackTrace();
			}
	    }
	    
	}

}
