package com.ennuova.tools.sms;

import java.net.URLEncoder;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ennuova.dao.PubMesInterDao;
import com.ennuova.entity.PubMesInter;
import com.ennuova.obd.util.ApplicationContextUtil;
import com.ennuova.util.StongnetUtil;

public class sendMessage {
	
	@SuppressWarnings("unused")
	private static Logger log = LoggerFactory.getLogger(sendMessage.class);

	//ApplicationContext ac = new ClassPathXmlApplicationContext(new String[]{"applicationContext.xml","hib-config.xml"});
	//BeanFactory beanFactory=new ClassPathXmlApplicationContext("applicationContext.xml");
	
	public static void main(String[] args) {
		sendMessage sendMessage=new sendMessage();
		sendMessage.sendSms("18850223150", "验证码123456");
		//sendMessage.sendSms("18850223150", "123456","SCHZSJYZ");
	}

	public String sendSms(String phone, String content) {

		String userid = "404";
		String username = "ynw"; // 短信宝帐户名
		String pass = "ynwxm2015"; // 短信宝帐户密码，md5加密后的字符串
		String result = ""; 
		try {
			content = URLEncoder.encode(content, "UTF-8");
			httpSend send = new httpSend(
					"http://222.76.210.200:8805/sms.aspx?action=send&userid="
							+ userid + "&account=" + username + "&password="
							+ pass + "&mobile=" + phone + "&content=" + content);
			result = send.send();
		} catch (Exception e) {
			result = "短信系统出错！";
			e.printStackTrace();
		}

		return result;
	}

	public String sendSms(String phone, String content, String code) {
		
		// 获取短信接口对象Dao
		PubMesInterDao pubMesInterDao =	(PubMesInterDao) ApplicationContextUtil.ac.getBean("pubMesInterDao");
		// 获取短信接口对象
		PubMesInter pubMesInter = pubMesInterDao.queryFirstMesInterface();
		
		// 获取模板
		/*VipMesstemplate vipMesstemplate = vipMessinterfaceDao
				.getVipMesstemplateByCode(code);*/

		String result = "";

		if (pubMesInter != null) {

			String userid = pubMesInter.getId().toString();
			String username = pubMesInter.getFuser(); // 短信宝帐户名
			String pass = pubMesInter.getFpassword(); // 短信宝帐户密码，md5加密后的字符串

			try {
				content = URLEncoder.encode(content, "UTF-8");
				httpSend send = new httpSend(
						"http://222.76.210.200:8805/sms.aspx?action=send&userid="
								+ userid + "&account=" + username
								+ "&password=" + pass + "&mobile=" + phone
								+ "&content=" + content);
				result = send.send();
			} catch (Exception e) {
				result = "短信系统出错！";
				e.printStackTrace();
			}
		} else {
			result = "账号密码还没配置！";
		}

		return result;
	}
	
	/**
	 * 华兴短信接口
	 * @param code 验证码
	 * @param tel  手机号
	 * @author 伟灿
	 * @return 结果消息
	 */
	public String sendCode(String code,String tel){
		String result = "";
		try {
			//以下为所需的参数，测试时请修改,中文请先转为16进制再发送
			String strReg = StongnetUtil.getInstance().getStrReg();//注册号（由华兴软通提供）
			String strPwd = StongnetUtil.getInstance().getStrPwd();//密码（由华兴软通提供）
			String strSourceAdd = "";//子通道号，可为空（预留参数一般为空）
			String strPhone = tel;//手机号码，多个手机号用半角逗号分开，最多1000个
			String strContent = httpSend.paraTo16("您的i宝验证码是"+code+"【i宝】"); //短信内容
			String strSmsUrl = "http://www.stongnet.com/sdkhttp/sendsms.aspx";
			String strSmsParam = "reg=" + strReg + "&pwd=" + strPwd + "&sourceadd=" + strSourceAdd + "&phone=" + strPhone + "&content=" + strContent;;
			String strRes  = new String();
			//发送短信
			strRes = httpSend.postSend(strSmsUrl, strSmsParam);
			result = strRes;
		} catch (Exception e) {
			result = "短信系统出错！";
			e.printStackTrace();
		}
		System.out.println("return result:"+result);
		return result;
	}

}