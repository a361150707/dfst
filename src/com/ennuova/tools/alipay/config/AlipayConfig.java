package com.ennuova.tools.alipay.config;


/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *版本：3.3
 *日期：2012-08-10
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
	
 *提示：如何获取安全校验码和合作身份者ID
 *1.用您的签约支付宝账号登录支付宝网站(www.alipay.com)
 *2.点击“商家服务”(https://b.alipay.com/order/myOrder.htm)
 *3.点击“查询合作者身份(PID)”、“查询安全校验码(Key)”

 *安全校验码查看时，输入支付密码后，页面呈灰色的现象，怎么办？
 *解决方法：
 *1、检查浏览器配置，不让浏览器做弹框屏蔽设置
 *2、更换浏览器或电脑，重新登录查询。
 */

public class AlipayConfig {
	
	//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
	
	// 合作身份者ID，以2088开头由16位纯数字组成的字符串
    public static String partner = "2088121188854800";
	// 合作身份者ID，以2088开头由16位纯数字组成的字符串
	public static String seller_id = "garageman@qq.com";
	// 商户的私钥
	public static String private_key = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAMp/M54IseoOdAK4"+
"NtmDgNMt1KuzVfdjd/KK3vALFCOTjTWwMMDzsAlcB9O+Ex6oaXj/EW6aYp6/UJPb"+
"HgqQtolIaisnYpyLpVflU/jZWCWNzSHFlMKXtAvCKuNRTOw6PZeSVNzrnuH3dWs0"+
"d6P7CyjUxwFfTd+IZAqaRRQMURT3AgMBAAECgYALA+Jd7WEoN0MORtENAf9G/Y49"+
"DNNXUrzan71C14dv6/3Zds0gIeFokO4IEaXenBCouU9Mce99bTDH6jp3HqofMsfd"+
"pA/8TxYWVjr8nUbrW2xcdSibw1+fJi8Qi/8HqZjAitVzOYndga2yvidiY/V1n8GM"+
"HBAKneZzAmcUnValIQJBAPjYHcW7eOWxiO3PKA/+WCO32rdd6pBSQ5Du0d4s58i9"+
"W7wtPvNk3p420PYMLhyqffZ5AsO1ivZ2stxx6jk3DWcCQQDQUeV17g9kk9dGIG/3"+
"/Q0Vp9lLLPCWweWl6MnsXpZ1WU2aaPWT5xH1ajt3Wvod3iXvGIGdqqorvODDoowZ"+
"9XHxAkB+IPymNqnGsr/W2cNSp/aA1DW175Ju6GN62LHjTRuGsgQHU1+u+pej1eEA"+
"dZgBy6DhBuER1bjDx9JMziiMK41NAkEAj6TI+mZqysl+mWZojrE4woP4u3ODehC9"+
"T4eTJJ+cU9J+Rki1fYTJx1W3J3feG5ZAxfs59QPnTz1wJrYAsS2joQJAPhmiuENx"+
"wH65xLH90X/EvYnLwOOHWikLgabSv108JrdP8fJaXEMzO7zLnU6Tbn25mg/ZfQjc"+
"z33BfO3E4VBP2A==";
	
	// 支付宝的公钥，无需修改该值
	public static String ali_public_key  = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCnxj/9qwVfgoUh/y2W89L6BkRAFljhNhgPdyPuBV64bfQNN1PjbCzkIM6qRdKBoLPXmKKMiFYnkd6rAoprih3/PrQEB/VsW8OoM8fxn67UDYuyBTqA23MML9q1+ilIZwBC2AQ2UBVOrFXfFl75p6/B5KsiNG9zpgmLCUYuLkxpLQIDAQAB";

	//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑
	

	// 调试用，创建TXT日志文件夹路径
	public static String log_path = "D:\\";

	// 字符编码格式 目前支持 gbk 或 utf-8
	public static String input_charset = "utf-8";
	
	// 签名方式 不需修改
	public static String sign_type = "RSA";

}
