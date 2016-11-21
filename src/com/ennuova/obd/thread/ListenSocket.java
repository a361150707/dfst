package com.ennuova.obd.thread;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import com.ennuova.util.ObdUtil;

public class ListenSocket extends Thread {

	// 服务端socket
	private ServerSocket serverScoket = null;
	// 盒子线程
	private ServerThread serverThread = null;

	public static List<Socket> socketList = new ArrayList<Socket>();

	/**
	 * 无参构造方法
	 */
	public ListenSocket() {

		String port = ObdUtil.getInstance().getPort();
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
					System.out.println(ip + " 用户上线了 , 当前在线盒子为: "
							+ socketList.size() + " 台!");

					System.out.println("开启线程ServerThread");
					serverThread = new ServerThread(client);
					serverThread.start();

					System.out.println("接收了 第" + socketList.indexOf(client)
							+ "个请求");

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