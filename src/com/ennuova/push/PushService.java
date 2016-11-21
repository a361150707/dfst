/**
 * Project Name:VehicleRepairingAPP
 * File Name:PushServerlet.java
 * Package Name:com.vrapp.push
 * Date:2015-12-8下午9:44:25
 * Copyright (c) 2015, chenzhou1025@126.com All Rights Reserved.
 *
*/

    /**  
    * @Title: PushServerlet.java
    * @Package com.vrapp.push
    * @Description: TODO(用一句话描述该文件做什么)
    * @author asus
    * @date 2015-12-8
    * @version V1.0  
    */
    

package com.ennuova.push;

/**
 * ClassName:PushServerlet <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2015-12-8 下午9:44:25 <br/>
 * @author   蔡钟杰
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */

/**
 * @ClassName: PushServerlet
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author 蔡钟杰
 * @date 2015-12-8
 *
 */


import java.io.IOException;  
  
import javax.servlet.ServletException;  
import javax.servlet.http.HttpServlet;  
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;  
  

  
/** 
 * Servlet implementation class tuisong 
 */  
public class PushService extends HttpServlet {  
    private static final long serialVersionUID = 1L;  
         
    private static final String appKey ="14ebd23cd331f179b524c75b";  
    private static final String masterSecret = "b3070dbfcb037e9b98c40773";  
      
    public PushService() {  
        super();  
        // TODO Auto-generated constructor stub  
    }  
  
    /** 
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response) 
     */  
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {  
       // GJPush.testSendPush(appKey,masterSecret);  
        System.out.println("sucess");  
    }  
  
    /** 
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response) 
     */  
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {  
        // TODO Auto-generated method stub  
    }  
  
}  

