
package com.ennuova.push;
import org.slf4j.Logger;  
import org.slf4j.LoggerFactory;  

import com.ennuova.util.Constant;
  
import cn.jpush.api.JPushClient;  
import cn.jpush.api.common.resp.APIConnectionException;  
import cn.jpush.api.common.resp.APIRequestException;  
import cn.jpush.api.push.PushResult;  
import cn.jpush.api.push.model.Options;
import cn.jpush.api.push.model.Platform;  
import cn.jpush.api.push.model.PushPayload;  
import cn.jpush.api.push.model.audience.Audience;  
import cn.jpush.api.push.model.notification.AndroidNotification;  
import cn.jpush.api.push.model.notification.IosNotification;  
import cn.jpush.api.push.model.notification.Notification;  

public class InnovPush {
	
	
	
	
   protected static final Logger LOG = LoggerFactory.getLogger(PushService.class);  
   public static final String TAG = "tag_api";  
   public  static JPushClient jpushClient=null;  
     
  
   /**单推  
  	 * @param alias 别名
  	 * @param notice 推送的消息内容
  	 * @param maxRetryTimes 推送失败的最大重推次数
  	 */
  	public static void testSendPush(String alias ,String notice,int maxRetryTimes,String MASRER_SECRET,String APP_KEY) {  
  	        jpushClient = new JPushClient(MASRER_SECRET, APP_KEY, maxRetryTimes); 
  	       PushPayload payload=buildPushObject_all_alias_alert(alias,notice); 
  	       try {   
  	           PushResult result = jpushClient.sendPush(payload);  
  	             
  	           LOG.info("Got result - " + result);  
  	             
  	       } catch (APIConnectionException e) {  
  	           LOG.error("Connection error. Should retry later. ", e);  
  	             
  	       } catch (APIRequestException e) {  
  	           LOG.error("Error response from JPush server. Should review and fix it. ", e);  
  	           LOG.info("HTTP Status: " + e.getStatus());  
  	           LOG.info("Error Code: " + e.getErrorCode());  
  	           LOG.info("Error Message: " + e.getErrorMessage());  
  	           LOG.info("Msg ID: " + e.getMsgId());  
  	       }  
  	   }
   

 /**根据别名进行单推
 * @param alias
 * @param notice
 * @return PushPayload
 */
 public static PushPayload buildPushObject_all_alias_alert(String alias,String notice) {
     return PushPayload.newBuilder()
             .setPlatform(Platform.all())
             .setAudience(Audience.alias(alias))
             .setNotification(Notification.newBuilder()
             .addPlatformNotification(IosNotification.newBuilder()  
                     .setAlert(notice) 
                     .setSound("happy")
                     .addExtra("from", "JPush")  
                     .build())  
             .addPlatformNotification(AndroidNotification.newBuilder()
            		 .setAlert(notice)
        				.addExtra("from", "JPush").build())
             .build())
             .setOptions(Options.newBuilder()
            		 .setApnsProduction(false)
            		 .build())
             .build();
 }
    public static void main(String[] args) {
		testSendPush("zhg32", "来，叫爸比", 3,Constant.MASRER_CZ_SECRET,Constant.APP_CZ_KEY);
	}
   public static PushPayload buildPushObject_android_and_ios() {  
       return PushPayload.newBuilder()  
               .setPlatform(Platform.android_ios())  
               .setAudience(Audience.tag("tag1"))  
               .setNotification(Notification.newBuilder()  
                       .setAlert("alert content")  
                       .addPlatformNotification(AndroidNotification.newBuilder()  
                               .setTitle("Android Title").build())  
                       .addPlatformNotification(IosNotification.newBuilder()  
                               .incrBadge(1)  
                               .addExtra("extra_key", "extra_value").build())  
                       .build())  
               .build();  
   }  
   
}


