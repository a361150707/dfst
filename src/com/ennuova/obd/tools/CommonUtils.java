/**
 * 项目名称：
 * 包名：  com.launch.mose.util 
 * 文件名：  CommonUtil.java  
 * 功能：  
 * 2014深圳市元征科技股份有限公司-版权所有
 * NO.    Date                      Author     Summary 
 * 001   2014年10月13日-上午10:42:18   韦子龙 
 *
 */
package com.ennuova.obd.tools;


/**
 * 
 * 类名称:CommonUtil 
 * 类描述:
 * 创建人: 韦子龙 
 * 修改人:
 * 修改人:  
 * 2014年10月13日 上午10:42:18
 * 修改备注:
 * @version 1.0.0
 * 
 */
public class CommonUtils {
	
	/**
	 * 如果str为null 或者length ==0 或 "null" 返回 ture
	 * @param str
	 * @return 
	 *boolean
	 * @exception 
	 * @since  1.0.0
	 */
	public static boolean isEmpty(String str){
		if (str == null || str.length() == 0 || str.trim().equals("") || str.trim().equalsIgnoreCase("null"))
			return true;
		else
			return false;
	}
	
}
