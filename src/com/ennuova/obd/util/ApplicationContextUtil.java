package com.ennuova.obd.util;

import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

import org.springframework.context.ApplicationContext;

/**
 * ac静态变量存储
 * @author janson
 */
public class ApplicationContextUtil {

	public static ApplicationContext ac;
	//socket列表
	public static Map<String,Socket> socketMap=new HashMap<String, Socket>();
}
