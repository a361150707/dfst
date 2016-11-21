/**
 * Copyright 2005 Jasper Systems, Inc. All rights reserved.
 * <p/>
 * 本软件代码是保密和专有信息的
 * （"机密信息"）
 * 。任何未经授权的
 * 审查、使用、复制、披露或披露机密
 * 资料被严格禁止。
 */
package com.ennuova.jasper;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.UnsupportedCallbackException;
import javax.xml.soap.*;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import org.xml.sax.InputSource;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.sax.SAXSource;
import javax.xml.parsers.DocumentBuilderFactory;

import com.ennuova.jasper.util.JasperUtil;
import com.sun.xml.wss.ProcessingContext;
import com.sun.xml.wss.XWSSProcessor;
import com.sun.xml.wss.XWSSProcessorFactory;
import com.sun.xml.wss.XWSSecurityException;
import com.sun.xml.wss.impl.callback.PasswordCallback;
import com.sun.xml.wss.impl.callback.UsernameCallback;

import javax.xml.transform.Source;  
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;  
import javax.xml.transform.TransformerFactory;  
import javax.xml.transform.stream.StreamResult;  
/**
 * @author Sunil Sheshadri
 * @version $Id:
 *          //depot/jasper_release/module/ProvisionApp/web/secure/apidoc/java
 *          /com/jasperwireless/ws/client/sample/GetTerminalDetailsClient.java#3
 *          $
 */
public class GetTerminalDetailsClient implements ApiClientConstant {
    private SOAPConnectionFactory connectionFactory;
    private MessageFactory messageFactory;
    private URL url;
    private String licenseKey;

    private XWSSProcessorFactory processorFactory;

    /**
     * 构造函数的初始化和SOAP连接，messagefactory processorfactory
     *
     * @param url
     * @throws SOAPException
     * @throws MalformedURLException
     * @throws XWSSecurityException
     */
    public GetTerminalDetailsClient(String url, String licenseKey)
            throws SOAPException, MalformedURLException, XWSSecurityException {
        connectionFactory = SOAPConnectionFactory.newInstance();
        messageFactory = MessageFactory.newInstance();
        processorFactory = XWSSProcessorFactory.newInstance();
        this.url = new URL(url);
        this.licenseKey = licenseKey;
        //TODO load 证书
        System.setProperty("javax.net.ssl.trustStore", "jssecacerts");
    }

    /**
     * 此方法创建一个终端请求并返回的SOAP消息。 ICCID值传递到该方法
     *
     * @return SOAPMessage
     * @throws SOAPException
     */
    private SOAPMessage createTerminalRequest(String iccid)
            throws SOAPException {
        SOAPMessage message = messageFactory.createMessage();
        message.getMimeHeaders().addHeader("SOAPAction", "http://api.jasperwireless.com/ws/service/terminal/GetTerminalDetails");
//        message.getMimeHeaders().addHeader("SOAPAction", "https://api.10646.cn/ws/service/terminal/GetTerminalDetails");
        SOAPEnvelope envelope = message.getSOAPPart().getEnvelope();
        Name terminalRequestName = envelope.createName(
                "GetTerminalDetailsRequest", PREFIX, NAMESPACE_URI);
        SOAPBodyElement terminalRequestElement = message.getSOAPBody()
                .addBodyElement(terminalRequestName);
        Name msgId = envelope.createName("messageId", PREFIX, NAMESPACE_URI);
        SOAPElement msgElement = terminalRequestElement.addChildElement(msgId);
        msgElement.setValue("TCE-100-ABC-34084");
        Name version = envelope.createName("version", PREFIX, NAMESPACE_URI);
        SOAPElement versionElement = terminalRequestElement
                .addChildElement(version);
        versionElement.setValue("1.0");
        Name license = envelope.createName("licenseKey", PREFIX, NAMESPACE_URI);
        SOAPElement licenseElement = terminalRequestElement
                .addChildElement(license);
        licenseElement.setValue(licenseKey);
        Name iccids = envelope.createName("iccids", PREFIX, NAMESPACE_URI);
        SOAPElement iccidsElement = terminalRequestElement
                .addChildElement(iccids);
        Name iccidName = envelope.createName("iccid", PREFIX, NAMESPACE_URI);
        SOAPElement iccidElement = iccidsElement.addChildElement(iccidName);
        iccidElement.setValue(iccid);
        return message;
    }

    public void callWebService(String username, String password, String iccid)
            throws SOAPException, IOException, XWSSecurityException, Exception {
        SOAPMessage request = createTerminalRequest(iccid);
        request = secureMessage(request, username, password);
        System.out.println("Request: ");
        request.writeTo(System.out);
        System.out.println("");
        //System.out.println("test:"+request.getProperty("ns2:accountId"));
        System.out.println("");
        SOAPConnection connection = connectionFactory.createConnection();
        SOAPMessage response = connection.call(request, url);
        System.out.println("Response: ");
        TransformerFactory transformerFactory = TransformerFactory.newInstance();  
        Transformer transformer = transformerFactory.newTransformer();  
        // Extract the content of the reply======================提取消息内容  
        Source sourceContent = response.getSOAPPart().getContent();  
        // Set the output for the transformation  
        StreamResult result = new StreamResult(System.out);  
        transformer.transform(sourceContent, result); 
        // Close the connection 关闭连接 ==============  
        System.out.println("");  
        connection.close();  
        /* 
         * 模拟客户端A，异常处理测试 
         */  
        SOAPBody ycBody = response.getSOAPBody();  
        Node ycResp = ycBody.getLastChild();  
        System.out.println("returnValue:"+ycBody.getTextContent()); 
        System.out.println("---:");
        
//        response.writeTo(System.out);
        
        ByteArrayOutputStream oStream = new ByteArrayOutputStream();
        response.writeTo(oStream);
        System.out.println("111");
        String soapRes = new String(oStream.toByteArray());
        System.out.println("output:===" + soapRes);
        String s = soapRes.substring(soapRes.indexOf("<ns2:monthToDateUsage>") + "<ns2:monthToDateUsage>".length(), soapRes.indexOf("</ns2:monthToDateUsage>"));
		System.out.println("s:"+s);
//        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream();
        		
        
        System.out.println("");
        if (!response.getSOAPBody().hasFault()) {
        	System.out.println("--1");
            writeTerminalResponse(response);
            System.out.println("--2");
        } else {
            SOAPFault fault = response.getSOAPBody().getFault();
            System.err.println("故障信息");
            System.err.println("SOAP Fault Code :" + fault.getFaultCode());
            System.err.println("SOAP Fault String :" + fault.getFaultString());
        }
    }

    /**
     * 得到终端响应。
     *
     * @param message
     * @throws SOAPException
     */
    private void writeTerminalResponse(SOAPMessage message)
            throws SOAPException {
        SOAPEnvelope envelope = message.getSOAPPart().getEnvelope();
        Name terminalResponseName = envelope.createName(
                "GetTerminalDetailsResponse", PREFIX, NAMESPACE_URI);
        SOAPBodyElement terminalResponseElement = (SOAPBodyElement) message
                .getSOAPBody().getChildElements(terminalResponseName).next();
        String terminalValue = terminalResponseElement.getTextContent();
        Name terminals = envelope
                .createName("terminals", PREFIX, NAMESPACE_URI);
        Name terminal = envelope.createName("terminal", PREFIX, NAMESPACE_URI);
        SOAPBodyElement terminalsElement = (SOAPBodyElement) terminalResponseElement
                .getChildElements(terminals).next();
        SOAPBodyElement terminalElement = (SOAPBodyElement) terminalsElement
                .getChildElements(terminal).next();
        NodeList list = terminalElement.getChildNodes();
        Node n = null;
        for (int i = 0; i < list.getLength(); i++) {
            n = list.item(i);
            if ("status".equalsIgnoreCase(n.getLocalName()))
                break;
        }

        System.out.println("设备状态 = " + n.getTextContent());
        System.out.println("终端响应 [" + terminalValue + "]");

    }

    /**
     * 此方法用于添加安全性。这xwss：使用UsernameToken
     *配置，并期望通过用户名和密码。
     * securitypolicy.xml文件应该在类路径。
     *
     * @param message
     * @param username
     * @param password
     * @return
     * @throws IOException
     * @throws XWSSecurityException
     */
    private SOAPMessage secureMessage(SOAPMessage message,
                                      final String username, final String password) throws IOException,
            XWSSecurityException {
        CallbackHandler callbackHandler = new CallbackHandler() {
            public void handle(Callback[] callbacks)
                    throws UnsupportedCallbackException {
                for (int i = 0; i < callbacks.length; i++) {
                    if (callbacks[i] instanceof UsernameCallback) {
                        UsernameCallback callback = (UsernameCallback) callbacks[i];
                        callback.setUsername(username);
                    } else if (callbacks[i] instanceof PasswordCallback) {
                        PasswordCallback callback = (PasswordCallback) callbacks[i];
                        callback.setPassword(password);
                    } else {
                        throw new UnsupportedCallbackException(callbacks[i]);
                    }
                }
            }
        };
        InputStream policyStream = null;
        XWSSProcessor processor = null;
        //D:\\apache-tomcat-6.0.29\\webapps\\autopet\\WEB-INF\\classes\\test\\
        try {
            System.out.println(System.getProperty("SecurityPolicy.xml"));
            policyStream = getClass().getResourceAsStream("SecurityPolicy.xml");
            processor = processorFactory
                    .createProcessorForSecurityConfiguration(policyStream,
                            callbackHandler);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (policyStream != null) {
                policyStream.close();
            }
        }
        ProcessingContext context = processor.createProcessingContext(message);
        return processor.secureOutboundMessage(context);
    }

    /**
     * 主程序。用法：terminalclient <用户名> <密码>
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        // Apitest URL. See "Get WSDL Files" in the API documentation for
        // Production URL.
    	String lincenseKey = JasperUtil.getInstance().getLicenseKey();
    	String user = JasperUtil.getInstance().getUser();
    	String password = JasperUtil.getInstance().getPassword();
        String[] a = {lincenseKey, user, password, "89860615010023079665"};
        String url = "https://api.10646.cn/ws/service/terminal";
        if (a.length != 4) {
            System.out.println("usage: GetTerminalDetailsClient <license-key> <username> <password> <iccid>");
            System.exit(-1);
        }
        GetTerminalDetailsClient terminalClient = new GetTerminalDetailsClient(
                url, a[0]);
        terminalClient.callWebService(a[1], a[2], a[3]);
    }
  
 
    public void soap2string(Source source) throws Exception {
		if (source != null) {
			Node root = null;
			if (source instanceof DOMSource) {
				root = ((DOMSource) source).getNode();
			} else if (source instanceof SAXSource) {
				InputSource insource = ((SAXSource) source).getInputSource();
				DocumentBuilderFactory dbf = DocumentBuilderFactory
						.newInstance();
				dbf.setNamespaceAware(true);
				org.w3c.dom.Document doc = dbf.newDocumentBuilder().parse(insource);
				root = (Node) doc.getDocumentElement();
			}
			Transformer transformer = TransformerFactory.newInstance()
					.newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.transform(new DOMSource(root), new StreamResult(
					System.out));
		}
	}
}
