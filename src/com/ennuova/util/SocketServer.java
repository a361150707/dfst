package com.ennuova.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import com.ennuova.obd.thread.ServerThread;
import com.ennuova.util.ObdUtil;

public class SocketServer extends Thread {

	// 服务端socket
	private ServerSocket serverScoket = null;
	// 盒子线程
	private ServerThread serverThread = null;

	public static List<Socket> socketList = new ArrayList<Socket>();

	/**
	 * 无参构造方法
	 */
	public SocketServer() {

		String port = "7870";
		try {
			if (null == serverScoket) {
				this.serverScoket = new ServerSocket(Integer.valueOf(port));
			}
		} catch (IOException e) {
			System.out.println("ListenSocket-serverScoket-->" + e.getMessage());
			e.printStackTrace();
		}
	}

	/**
	 * 监听的线程
	 */
	public void run() {
		while (!this.isInterrupted()) {
			while (true) {

				Socket client = new Socket();
				String ip = null;

				try {

					client = serverScoket.accept();
					socketList.add(client);

					// 获取 Socket IP
					ip = client.getInetAddress().getHostAddress();
					System.out.println(ip + "测试设备连接: "
							+ socketList.size());
					 BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));  
                     PrintWriter out = new PrintWriter(client.getOutputStream(), true);  
                     
                     String s1 = in.readLine();
                     System.out.println("Server Received:[" + s1 + "]");  //输出
                     out.println("Server Received:[" + s1 + "]");  //放回到客户端的
                     out.close();  
                     in.close();  

				} catch (IOException e) {
					System.out.println("ListenSocket-->" + e.getMessage());
					socketList.remove(client);
					System.out.println(ip + " 已下线 , 当前在线盒子为: "
							+ socketList.size() + " 台!");
				}
			}
		}
	}

	/**
	 * 关闭当前线程
	 */
	public void closeSocketServer() {
		try {
			if (null != serverScoket && !serverScoket.isClosed()) {
				serverScoket.close();
			}
			if (null != serverThread && !serverThread.isInterrupted()) {
				serverThread.interrupt();
			}
		} catch (IOException e) {
			// TODO Auto-generatedcatch block
			System.out.println("ListenSocket-closeSocketServer-->"
					+ e.getMessage());
			e.printStackTrace();
		}
	}
}