package com.ennuova.obd.thread;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.security.PublicKey;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ennuova.entity.CnDiagsoft;
import com.ennuova.entity.CnSbljinfo;
import com.ennuova.entity.PubBox;
import com.ennuova.obd.message.DataMessage;
import com.ennuova.obd.message.LoginMessage;
import com.ennuova.obd.message.LoginResMessage;
import com.ennuova.obd.service.OdbLoginService;
import com.ennuova.obd.util.ApplicationContextUtil;
import com.ennuova.obd.util.MessageUtil;
import com.ennuova.obd.util.StringByteUtil;
import com.ennuova.obd.util.aecrsa.AESCodec;
import com.ennuova.obd.util.aecrsa.RSAUtil;

public class ServerThread extends Thread {
	
	Logger logger=LoggerFactory.getLogger("mylogger1");
	
	private Socket client;
	DataInputStream in;
	DataOutputStream out;
	private OdbLoginService odbLoginService;
    //用户集合
    private List<Socket> socketList = ListenSocket.socketList;
	
	public ServerThread(Socket client) {
		this.client = client;
		// 获取OdbLoginService
		this.odbLoginService = (OdbLoginService) ApplicationContextUtil.ac
				.getBean("odbLoginService");
	}

	public void run() {
		int i = 0;
		String ip = null;
		int port;
		try {
			
			System.out.println("-----监听线程----start--------");
			client.setKeepAlive(true);  
			client.setSoTimeout(30*60* 1000);
			
			ip = client.getInetAddress().toString();
			port = client.getPort();
			
			in = new DataInputStream(client.getInputStream());
			out = new DataOutputStream(client.getOutputStream());
		
             
			while (true) {
				
				String serialNumber="";
				
				i++;
				//System.out.println("与盒子" + socketList.indexOf(client) + "：第" + i + "次通讯；目前在线盒子（"+socketList.size()+"）个!");
				logger.warn("IP和port :{} 消息：{}",ip + ":" + port,"与盒子" + socketList.indexOf(client) + "：第" + i + "次通讯；目前在线盒子（"+socketList.size()+"）个!");
				
				byte[] buffer = new byte[2048]; // 缓冲区的大小
				System.out.println("读取数据开始！");
				in.read(buffer);
				System.out.println("读取数据结束！");

				String regmsg = "";

				String msg = StringByteUtil.bytesToHexString(buffer);

				String framelength = msg.substring(10, 14);

				int length = Integer.parseInt(framelength, 16) * 2;

				length = length + 10;

				msg = msg.substring(0, length);

				//System.out.println("客户端发送消息-->" + msg);

				String serviceId = msg.substring(32, 36);

				//System.out.println("业务代码--》" + serviceId);

				if (serviceId.equals("0002")) {
					
					//LogUtil.appendLog("客户端发送登录消息-->" + msg);
					logger.warn("业务ID:{} 盒子发送消息:{}",serviceId,msg);
					
					// 登录
					LoginMessage loginMessage = MessageUtil
							.msgToLoginMessage(msg);
					
					serialNumber=StringByteUtil.AscIItoString(loginMessage.getSerialNumber());

					logger.warn("盒子序列号:{} 盒子登录请求消息:{}",serialNumber,loginMessage.toString());
					//System.out.println(loginMessage.toString());

					// 公钥字符串
					String data = loginMessage.getKey();

					byte[] key = AESCodec.initSecretKey();

					//System.out.println("密钥: string:"+ StringByteUtil.bytesToHexString(key));
					
					logger.warn("盒子序列号:{} 密钥:{}",serialNumber,StringByteUtil.bytesToHexString(key));
					logger.warn("盒子序列号:{} 加密前数据:{}",serialNumber,data);
					//System.out.println("加密前数据: string:" + data);

					PublicKey publicKey = RSAUtil.getPublicKey(data);

					byte[] encryptData1 = RSAUtil.encrypt(publicKey, key);
				
					String AES = StringByteUtil.bytesToHexString(encryptData1);
					
					//System.out.println("对密钥加密加密后数据: "+ AES);
					logger.warn("盒子序列号:{} 对密钥加密加密后数据:{}",serialNumber,AES);

					AESCodec.key = key;
					String keyString = StringByteUtil.bytesToHexString(key);

					//System.out.println("令牌--》" + AES);
					logger.warn("盒子序列号:{} 令牌:{}",serialNumber,keyString);

					// 回复类
					LoginResMessage loginResMessage = MessageUtil
							.LoginMessagetoRes(loginMessage, AES);

					boolean res = odbLoginService.login(loginMessage,
							loginResMessage, keyString, ip, port);
					if (!res) {
						//System.out.println("Odb登录失败！");
						logger.warn("盒子序列号:{} msseage:{}",serialNumber,"Odb登录失败！");
					} else {

						String model = "00000000";

						// 查询车型ID
						CnDiagsoft cnDiagsoft = odbLoginService
								.getOdbModelBySerialNumber(loginMessage
										.getSerialNumber());

						if (cnDiagsoft != null && cnDiagsoft.getConfigId() != null) {
							String modelId = Long.toHexString(cnDiagsoft.getConfigId());
							//System.out.println("数据库车型识别码-》" + modelId);
							logger.warn("盒子序列号:{} 数据库车型16进制识别码-》{}",serialNumber,modelId);
							String pattern = "00000000";
							modelId = pattern.substring(0, pattern.length()
									- modelId.length())
									+ modelId;
							model = modelId;
						}
						// 设置车型ID
						loginResMessage.setModel(model);
						logger.warn("盒子序列号:{} 响应车型--》{}",serialNumber,model);
						//System.out.println("响应车型--》" + model);
						
						// 查询盒子
						PubBox pubBox = odbLoginService
								.getPubBoxBySerialNumber(loginMessage
										.getSerialNumber());
						
						//是否注册
						if(pubBox!=null){
							loginResMessage.setRet("01");
							if(pubBox.getFjhyf()!=null){
								//设置是否激活
								loginResMessage.setActivation("0"+pubBox.getFjhyf());
							}else{
								//设置是否激活
								loginResMessage.setActivation("00");
							}
						}else{
							loginResMessage.setRet("00");
						}
						logger.warn("盒子序列号:{} 登录响应注册、激活--》{}",serialNumber,loginResMessage.getRet()+"、"+loginResMessage.getActivation());
					}

					logger.warn("盒子序列号:{} 登录响应数据--》{}",serialNumber,loginResMessage.toString());
					//System.out.println(loginResMessage.toString());
					regmsg = MessageUtil.RedMessagetoString(loginResMessage);
				} else {

					// 根据IP和端口号查询登录信息得到密钥
					CnSbljinfo cnSbljinfo = odbLoginService
							.findCnSbljinfoByIpAndPort(ip, port);
					
					serialNumber=cnSbljinfo.getFsbxlh();
					String key = cnSbljinfo.getFmy();

					//System.out.println("客户端发送加密前-->" + msg);
					logger.warn("盒子序列号:{} 客户端发送加密前-->{}",serialNumber,msg);
					String begin = msg.substring(0, 32);
					String data = msg.substring(32, msg.length() - 12);
					String end = msg.substring(msg.length() - 12, msg.length());

					msg = begin
							+ StringByteUtil.bytesToHexString(RSAUtil.decrypt(
									StringByteUtil.hexStringToBytes(data),
									StringByteUtil.hexStringToBytes(key)))
							+ end;
					StringBuffer stringBuffer = new StringBuffer(msg);
					// 总长度
					Long rslength = stringBuffer.length() - 10L;
					rslength = rslength / 2;
					String flength = Integer.toHexString(rslength.intValue());
					String pattern = "0000";
					flength = pattern.substring(0,
							pattern.length() - flength.length())
							+ flength;
					//System.out.println("贞长-》" + flength);
					stringBuffer.replace(10, 14, flength);
					msg = stringBuffer.toString();
					//System.out.println("对密钥加密解密后数据: " + msg);
					
					logger.warn("盒子序列号:{} 对密钥加密解密后数据-->{}",serialNumber,msg);
					serviceId = msg.substring(32, 36);
					//System.out.println("解密后业务代码--》" + serviceId);
					logger.warn("盒子序列号:{} 解密后业务代码--》{}",serialNumber,serviceId);
					// 发送数据包
					DataMessage dataMessage = MessageUtil.msgToDataMessage(msg);

					if (serviceId.equals("0009")) {
						
						//LogUtil.appendLog("客户" + socketList.indexOf(client) + ":客户端发送业务消息-->" + msg);
						logger.warn("盒子序列号:{} 盒子发送业务消息---》{}",serialNumber,msg);
						
						List<DataMessage> dataMessageList = MessageUtil
								.dataMessageToDataMessageList(dataMessage);
						//解析业务打包数据
						boolean res = odbLoginService.packageDataTransmission(dataMessageList,
								cnSbljinfo);
						
						regmsg = MessageUtil.RedDataMessageString(dataMessage,
								cnSbljinfo);
						
					} else if (serviceId.equals("0007")) {
						
						//LogUtil.appendLog("客户" + socketList.indexOf(client) + ":客户端发送报警消息-->" + msg);
						logger.warn("盒子序列号:{} 盒子发送报警消息---》{}",serialNumber,msg);
						
						//报警
						boolean res = odbLoginService.police(dataMessage,
								cnSbljinfo);
						
						regmsg = MessageUtil.RedDataMessageString(dataMessage,
								cnSbljinfo);
					} else if (serviceId.equals("0005")) {
						
						//LogUtil.appendLog("客户" + socketList.indexOf(client) + ":客户端发送故障消息-->" + msg);
						logger.warn("盒子序列号:{} 盒子发送故障消息---》{}",serialNumber,msg);
						
						//故障码
						boolean res = odbLoginService.faultCodes(dataMessage,
								cnSbljinfo);
						
						regmsg = MessageUtil.RedDataMessageString(dataMessage,
								cnSbljinfo);
					} else if (serviceId.equals("0008")) {
						
						//LogUtil.appendLog("客户" + socketList.indexOf(client) + ":客户端发送行程消息-->" + msg);
						logger.warn("盒子序列号:{} 客户端发送行程消息---》{}",serialNumber,msg);
						
						//行程记录
						boolean res = odbLoginService.travelRecord(dataMessage,
								cnSbljinfo);
						regmsg = MessageUtil.RedDataMessageString(dataMessage,
								cnSbljinfo);
					}else if (serviceId.equals("0006")) {
						
						logger.warn("盒子序列号:{} 客户端发送日志消息---》{}",serialNumber,msg);
						
						regmsg = MessageUtil.RedDataMessageString(dataMessage,
								cnSbljinfo);
						regmsg="";
						
					}else if (serviceId.equals("0001")){
						
						//LogUtil.appendLog("客户" + socketList.indexOf(client) + ":客户端发送其他消息-->" + msg);
						logger.warn("盒子序列号:{} 客户端发送心跳消息---》{}",serialNumber,msg);
						
						regmsg = MessageUtil.RedDataMessageString(dataMessage,
								cnSbljinfo);
					}
				}
				
				if(!regmsg.equals("")){
					
					logger.warn("盒子序列号:{} 服务回复消息---》{}",serialNumber,regmsg);
					//System.out.println("服务回复消息--->" + regmsg);
					//LogUtil.appendLog("客户" + socketList.indexOf(client) + ":服务回复消息--->" + regmsg);
					out.write(StringByteUtil.hexStringToBytes(regmsg));
					//System.out.println("发送响应消息成功！");
				}else{
					logger.warn("盒子序列号:{} {}",serialNumber,"服务不回复消息！");
				}
				// out.flush();
	
			}
		} catch (Exception e) {
			//System.out.println("ServerThread-->"+e.getMessage());
			//System.out.println("与客户(" + socketList.indexOf(client) + ")通信结束！第" + i + "次通讯异常！");
		     
			logger.warn("ip:{} ServerThread-->{}",ip,e.getMessage());
		 	logger.warn("ip:{} {}",ip,"与客户(" + socketList.indexOf(client) + ")通信结束！第" + i + "次通讯异常！"); 
		     
			 //用户下线
            socketList.remove(client);
            
        	logger.warn("ip:{} {}",ip," 已下线 , 当前在线盒子为: " + socketList.size() + " 台!"); 
            //System.out.println(ip + " 已下线 , 当前在线盒子为: " + socketList.size() + " 台!");
 
		} finally {
			try {
				
				if (out != null) {
					out.flush();
					out.close();
				}
				if (client != null) {
					if (!client.isClosed()) {
						client.close();
					}
				}
				if(!this.isInterrupted()){
					this.interrupt();
				}
			} catch (Exception e) {
				System.err.println(e.getMessage());
				System.err.println("关闭socket失败!");
				
			}
		}

	}
}