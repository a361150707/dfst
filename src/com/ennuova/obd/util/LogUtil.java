package com.ennuova.obd.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Scanner;

public class LogUtil {

	public static String getCurrentDate() {
		SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sm.format(new Date());
	}

	public static String strRight(String value) {
		return value.substring(value.length() - 2, value.length());
	}

	public static void appendLog(String newLog) {
		Scanner sc = null;
		PrintWriter pw = null;
		Calendar c = new GregorianCalendar();
		File log = new File("D:\\log\\loginfo" + String.valueOf(c.get(c.YEAR))
				+ strRight("00" + String.valueOf(c.get(c.MONTH) + 1))
				+ strRight("00" + String.valueOf(c.get(c.DAY_OF_MONTH)))
				+ ".log");
		try {
			/*if (!log.exists())// 如果文件不存在,则新建.
			{
				File parentDir = new File(log.getParent());
				if (!parentDir.exists())// 如果所在目录不存在,则新建.
				{
					parentDir.mkdirs();
				}
				log.createNewFile();
			}
			sc = new Scanner(log);
			StringBuilder sb = new StringBuilder();
			while (sc.hasNextLine())// 先读出旧文件内容,并暂存sb中;
			{
				sb.append(sc.nextLine());
				sb.append("\r\n");// 换行符作为间隔,扫描器读不出来,因此要自己添加.
			}
			sc.close();

			pw = new PrintWriter(new FileWriter(log), true);

			pw.println(sb.toString());// ,写入旧文件内容.

			pw.println(newLog + "  [" + getCurrentDate() + "]");// 写入新日志.

			pw.close();*/
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public static void Log(String newLog) {
		Scanner sc = null;
		PrintWriter pw = null;
		Calendar c = new GregorianCalendar();
		File log = new File("D:\\log\\loginfo" + String.valueOf(c.get(c.YEAR))
				+ strRight("00" + String.valueOf(c.get(c.MONTH) + 1))
				+ strRight("00" + String.valueOf(c.get(c.DAY_OF_MONTH)))
				+ ".log");
		try {
			if (!log.exists())// 如果文件不存在,则新建.
			{
				File parentDir = new File(log.getParent());
				if (!parentDir.exists())// 如果所在目录不存在,则新建.
				{
					parentDir.mkdirs();
				}
				log.createNewFile();
			}
			sc = new Scanner(log);
			StringBuilder sb = new StringBuilder();
			while (sc.hasNextLine())// 先读出旧文件内容,并暂存sb中;
			{
				sb.append(sc.nextLine());
				sb.append("\r\n");// 换行符作为间隔,扫描器读不出来,因此要自己添加.
			}
			sc.close();

			pw = new PrintWriter(new FileWriter(log), true);

			pw.println(sb.toString());// ,写入旧文件内容.

			pw.println(newLog + "  [" + getCurrentDate() + "]");// 写入新日志.

			pw.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
