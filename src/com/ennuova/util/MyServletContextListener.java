package com.ennuova.util;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.ennuova.app.service.PubCustomerService;
import com.ennuova.dzwl.timer.CnDzwlListen;
import com.ennuova.obd.thread.ListenSocket;
import com.ennuova.obd.thread.ServerThread;
import com.ennuova.obd.util.ApplicationContextUtil;

/**
 * 监听器
 * @author janson
 */
public class MyServletContextListener implements ServletContextListener {
	
	private ListenSocket socketThread;
	private CnDzwlListen cnDzwlListen;
	private SocketServer socketServer;
	
	public void contextInitialized(ServletContextEvent sce) {
		
		System.out.println("项目启动！");
		
		// 获取容器与相关的Service对象
		ApplicationContext ac = WebApplicationContextUtils
				.getWebApplicationContext(sce.getServletContext());
		
		ApplicationContextUtil.ac=ac;
		//服务器启动的时候不用注释掉了
		/*if (null == socketServer) {
			System.out.println("开启线程socketThread");
			socketServer = new SocketServer();
			socketServer.start();
		}*/
		
		if (null == socketThread) {
			System.out.println("开启线程socketThread");
			socketThread = new ListenSocket();
			socketThread.start();
		}
		
		if(null==cnDzwlListen){
			System.out.println("开启线程cnDzwlListen");
			cnDzwlListen=new CnDzwlListen();
			cnDzwlListen.start();
		}
	
		
	}

	public void contextDestroyed(ServletContextEvent sce) {
		System.out.println("项目关闭！");
		if (null != socketThread && !socketThread.isInterrupted()) {
			System.out.println("关闭线程socketThread");
			// 关闭线程
			socketThread.closeSocketServer();
			
			// 中断线程
			socketThread.interrupt();
		}
		
		
		if(null!=cnDzwlListen&&!cnDzwlListen.isInterrupted()){
			System.out.println("关闭线程cnDzwlListen");
			// 中断线程
			cnDzwlListen.interrupt();
		}
	}


}
