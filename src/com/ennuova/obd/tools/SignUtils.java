package com.ennuova.obd.tools;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ennuova.obd.util.GoloApplication;

/**
 * 计算签名
 * 
 * @author yangbo
 * 
 */
public class SignUtils {

	/**
	 * 除去数组中的空值
	 * 
	 * @param params
	 *            签名参数组
	 * @return 去掉空值与签名参数后的新签名参数组
	 */
	public static Map<String, String> paraFilter(Map<String, String> params) {

		Map<String, String> result = new HashMap<String, String>();

		if (params == null || params.size() <= 0) {
			return result;
		}

		for (String key : params.keySet()) {
			String value = params.get(key);
			// if (value == null || value.equals("")
			// || key.equalsIgnoreCase("sign")) {
			// continue;
			// }
			if (value == null || value.equals("")) {
				continue;
			}
			result.put(key, value);
		}

		return result;
	}

	/**
	 * 把数组所有元素排序，并按照“参数=参数值”的模式用“&”字符拼接成字符串
	 * 
	 * @param params
	 *            需要排序并参与字符拼接的参数组
	 * @return 拼接后字符串
	 */
	public static String createLinkString(Map<String, String> params) {
		Map<String, String> filterParams = paraFilter(params);
		List<String> keys = new ArrayList<String>(filterParams.keySet());
		// 排序
		Collections.sort(keys);
		String prestr = "";
		for (int i = 0; i < keys.size(); i++) {
			String key = keys.get(i);
			String value = filterParams.get(key);
			if (i == keys.size() - 1) {// 拼接时，不包括最后一个&字符
				prestr = prestr + key + "=" + value;
			} else {
				prestr = prestr + key + "=" + value + "&";
			}
		}
		return prestr;
	}

	/**
	 * 把数组所有元素排序，并按照“参数=参数值”的模式用“&”字符拼接成字符串
	 * 对参数进行编码
	 * @param params
	 *            需要排序并参与字符拼接的参数组
	 * @return 拼接后字符串
	 */
	public static String createLinkStringValueUtf8(Map<String, String> params) {
		Map<String, String> filterParams = paraFilter(params);
		List<String> keys = new ArrayList<String>(filterParams.keySet());
		// 排序
		Collections.sort(keys);
		String prestr = "";
		for (int i = 0; i < keys.size(); i++) {
			String key = keys.get(i);
			String value = filterParams.get(key);
			if (i == keys.size() - 1) {// 拼接时，不包括最后一个&字符
				prestr = prestr + key + "=" + getUTF8XMLString(value);
			} else {
				prestr = prestr + key + "=" + getUTF8XMLString(value) + "&";
			}
		}
		return prestr;
	}

	/**
	 * 获得签名
	 * 
	 * @param token
	 * @param params
	 * @return
	 */
	public static String getSign(String token, Map<String, String> params) {
		String createLinkString = createLinkString(params);
		String strLink = createLinkString + token;
		String result = MD5Util.MD5(strLink);
		return result;
	}

	/**
	 * 
	 * @param params
	 *            必须包含action
	 * @return
	 */
	public static String getSign(Map<String, String> params) {
		Map<String, String> signMap = new HashMap<String, String>();
		if (!StringUtils.isEmpty(GoloApplication.app_id)) {
			signMap.put("app_id", GoloApplication.app_id);
		}
		if (!StringUtils.isEmpty(GoloApplication.develop_id)) {
			signMap.put("develop_id", GoloApplication.develop_id);
		}
		signMap.put("time", Long.toString(System.currentTimeMillis() / 1000));
		if (!StringUtils.isEmpty(GoloApplication.access_id)) {
			signMap.put("access_id", GoloApplication.access_id);
		}
		if (!StringUtils.isEmpty(GoloApplication.access_token)) {
			signMap.put("access_token", GoloApplication.access_token);
		}
		// 指定接口的参数
		if (params != null) {
			signMap.putAll(params);
		}
		// 对参数（必传的参数+ 指定接口的参数）进行签名
		String createLinkString = createLinkString(signMap);
		String strLink = createLinkString + GoloApplication.delevop_key;
		String result = MD5Util.MD5(strLink);
		return createLinkString + "&sign=" + result;
	}

	/**
	 * 
	 * @param params
	 *            必须包含action
	 * @return
	 */
	public static String getSignBySaveCarInfo(Map<String, String> params) {
		Map<String, String> signMap = new HashMap<String, String>();
		Map<String, String> newMap = new HashMap<String, String>();
		if (!StringUtils.isEmpty(GoloApplication.app_id)) {
			signMap.put("app_id", GoloApplication.app_id);
		}
		if (!StringUtils.isEmpty(GoloApplication.develop_id)) {
			signMap.put("develop_id", GoloApplication.develop_id);
		}
		signMap.put("time", Long.toString(System.currentTimeMillis() / 1000));
		if (!StringUtils.isEmpty(GoloApplication.access_id)) {
			signMap.put("access_id", GoloApplication.access_id);
		}
		if (!StringUtils.isEmpty(GoloApplication.access_token)) {
			signMap.put("access_token", GoloApplication.access_token);
		}
		// 指定接口的参数
		if (params != null) {
			signMap.putAll(params);
		}
		if (signMap != null) {
			newMap.putAll(signMap);
		}

		// 对参数（必传的参数+ 指定接口的参数）进行签名
		String createLinkString = createLinkString(signMap);
		String strLink = createLinkString + GoloApplication.delevop_key;
		String result = MD5Util.MD5(strLink);

		// 对参数进行utf-8编码
		String createLinkStringutf8 = createLinkStringValueUtf8(newMap);

		return createLinkStringutf8 + "&sign=" + result;
	}

	/**
	 * Get XML String of utf-8
	 * 
	 * @return XML-Formed string
	 */
	public static String getUTF8XMLString(String xml) {
		String xmlUTF8 = "";
		try {
			xmlUTF8 = URLEncoder.encode(xml, "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return xmlUTF8;
	}

}
