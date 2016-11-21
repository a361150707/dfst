package com.ennuova.util;

import java.util.List;
import java.util.Map;

/**
 * 公共服务的工具类
 * @author sududa
 * @date 2016年10月31日
 *
 */
public class CommonServicesUtil {

	/**
	 * 处理客户的头像的工具
	 * @author sududa
	 * @date 2016年10月31日
	 */
     public static void changePhoto(List<Map<String, Object>> resultList,String changePar) {
 		String imgUrl = UrlUtil.getInstance().getImgurl();
 		for (Map<String, Object> map : resultList) {
 			String photo = map.get(changePar)+"";
 			map.put(changePar, imgUrl+photo);
 		}
 	}
     
	    
}
