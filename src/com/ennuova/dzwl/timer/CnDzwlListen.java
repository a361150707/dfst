package com.ennuova.dzwl.timer;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.ennuova.app.service.CnDzwlService;
import com.ennuova.obd.util.ApplicationContextUtil;

/**
 * 电子围栏线程
 * 
 * @author 李智辉 2015-12-17下午2:35:23
 */
public class CnDzwlListen extends Thread {

	private CnDzwlService cnDzwlService;

	public CnDzwlListen() {
		this.cnDzwlService = (CnDzwlService) ApplicationContextUtil.ac
				.getBean("cnDzwlService");
	}
	public void run() {
		while (true) {
			try {

				Date nowTime = new Date(System.currentTimeMillis());
				SimpleDateFormat formatter = new SimpleDateFormat(
						"yyyy年MM月dd日 HH时mm分ss秒");
				String retStrFormatNowDate = formatter.format(nowTime);
				System.out.println("启动消息监听----> " + retStrFormatNowDate);

				//监听电子围栏
				cnDzwlService.listenAndPushDzwl();
				//监听obd消息
				cnDzwlService.listenAndPushOdbMessage();
				
				Thread.sleep(5*60*1000);
			} catch (InterruptedException e) {
				System.out.println("CnDzwlListen-->"+e.getMessage());
				this.interrupt();
				e.printStackTrace();
			}

		}
	}

	public static void main(String[] args) {
		CnDzwlListen cnDzwlListen = new CnDzwlListen();
		cnDzwlListen.start();
	}
}
