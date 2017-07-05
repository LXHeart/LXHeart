package com.lxh.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jpush.api.JPushClient;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Message;
import cn.jpush.api.push.model.Options;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.audience.AudienceTarget;
import cn.jpush.api.push.model.notification.IosNotification;
import cn.jpush.api.push.model.notification.Notification;

/**
 * 极光推送工具类
 * 
 * @author tanzhibo
 * 
 */
public class JpushUserUtils {

	private static Logger LOG = LoggerFactory.getLogger(JpushUserUtils.class);
	
	private static String appKey = "32264309f9316ad66ffc7fa2";
	private static String masterSecret = "6f140a1597a8a284db7b5eb7";
	private static  JPushClient jpushClient = new JPushClient(masterSecret, appKey, 3);

	public static PushPayload buildPushObject_ios_audienceMore_messageWithExtras_MI(String alias,String content) {
        return PushPayload.newBuilder()
                .setPlatform(Platform.android_ios())
                .setAudience(Audience.alias(alias))
                .setMessage(Message.newBuilder()
                        .setMsgContent(content)
                        .addExtra("from", "JPush")
                        .build())
                .build();
    }
	/**
	 * Determines if there is a capital letter in the string 
	 * @param str
	 * @return Return true with uppercase letters,otherwise return false 
	 */
	public static Boolean isUppercase(String str){
		char[] charArray = str.toCharArray();
		for(char c : charArray){
			if(Character.isUpperCase(c)){
				return true;
			}
		}
		return false;
	}
	
	//发送通知
	 public static PushPayload buildPushObject_all_alias_alert(String alias, String ALERT, Map<String, String> map) {
		 if(isUppercase(alias)){
			 return PushPayload.newBuilder()
		                .setPlatform(Platform.all())
		                .setAudience(Audience.alias(alias))
		                .setNotification(Notification.newBuilder()
		                		.addPlatformNotification(IosNotification.newBuilder()
		                				.setAlert(ALERT)
		                				.setBadge(0)
		                				.addExtras(map)
		                				.build()
		                				)
		                				.build())
		                .build();
		 }else{
			 return PushPayload.newBuilder()
		                .setPlatform(Platform.all())
		                .setAudience(Audience.alias(alias))
		                .setNotification(Notification.android(ALERT, map.get("title"), map))
		                .build();
		 }
	    }
	
	public static String sendMessage(String alias,String content){
		PushPayload payload = buildPushObject_ios_audienceMore_messageWithExtras_MI(alias, content);
		try {
			PushResult result = jpushClient.sendPush(payload);
			LOG.info("Got result - " + result);
		} catch (APIConnectionException e) {
			LOG.error("Connection error, should retry later", e);
		} catch (APIRequestException e) {
			LOG.error("Should review the error, and fix the request", e);
			LOG.info("HTTP Status: " + e.getStatus());
			LOG.info("Error Code: " + e.getErrorCode());
			LOG.info("Error Message: " + e.getErrorMessage());
			return String.valueOf(e.getErrorCode());
		}
		return "0";
	}
	
	/**
	 * 构建推送对象：所有平台，所有设备，内容为 ALERT 的通知。
	 * @return
	 */
	public static List<PushPayload> buildPushObject_all_all_alert(String alert, Map<String, String> map) {
		List<PushPayload> list = new ArrayList<PushPayload>();
		//ios平台
		PushPayload a= PushPayload.newBuilder()
        .setPlatform(Platform.all())
        .setAudience(Audience.all())
        .setNotification(Notification.newBuilder()
        		.addPlatformNotification(IosNotification.newBuilder()
        				.setAlert(alert)
        				.setBadge(0)
        				.addExtras(map)
        				.build()
        				)
        				.build())
        .build();
		//安卓平台
		PushPayload b = PushPayload.newBuilder()
        .setPlatform(Platform.all())
        .setAudience(Audience.all())
        .setNotification(Notification.android(alert, map.get("title"), map))
        .build();
		list.add(a);
		list.add(b);
        return list;
    }
	
	//向ios平台推送消息
	 public static PushPayload buildPushObject_ios_tagAnd_alertWithExtrasAndMessage() {
	        return PushPayload.newBuilder()
	                .setPlatform(Platform.ios())
	                .setNotification(Notification.newBuilder()
	                        .addPlatformNotification(IosNotification.newBuilder()
	                                .setAlert("liyichen")
	                                .setBadge(5)
	                                .setSound("happy")
	                                .addExtra("from", "JPush")
	                                .build())
	                        .build())
	                 .setOptions(Options.newBuilder()
	                         .setApnsProduction(true)
	                         .build())
	                 .build();
	    }
	
	public static String sendSysMessage(PushPayload payload){
		try {
			PushResult result = jpushClient.sendPush(payload);
			LOG.info("Got result - " + result);
		} catch (APIConnectionException e) {
			LOG.error("Connection error, should retry later", e);
		} catch (APIRequestException e) {
			LOG.error("Should review the error, and fix the request", e);
			LOG.info("HTTP Status: " + e.getStatus());
			LOG.info("Error Code: " + e.getErrorCode());
			LOG.info("Error Message: " + e.getErrorMessage());
			return String.valueOf(e.getErrorCode());
		}
		return "0";
	}
	//向所有用户推送信息
	public static String sendAllMessage(String content, Map<String, String> map){
		List<PushPayload> list = buildPushObject_all_all_alert(content, map);
		for (PushPayload p : list) {
			sendSysMessage(p);
		}
		return null;
	}
	
	public static String sendSysUserMessage(String alias,String content, Map<String, String> map){
		PushPayload payload =buildPushObject_all_alias_alert(alias,content, map);
		try {
			PushResult result = jpushClient.sendPush(payload);
			LOG.info("Got result - " + result);
		} catch (APIConnectionException e) {
			LOG.error("Connection error, should retry later", e);
		} catch (APIRequestException e) {
			LOG.error("Should review the error, and fix the request", e);
			LOG.info("HTTP Status: " + e.getStatus());
			LOG.info("Error Code: " + e.getErrorCode());
			LOG.info("Error Message: " + e.getErrorMessage());
			return String.valueOf(e.getErrorCode());
		}
		return "0";
	}
	
	public static PushPayload buildPushObject_ios_tagAnd_alertWithExtrasAndMessage(String alias, String ALERT, Map<String, String> map) {
        return PushPayload.newBuilder()
                .setPlatform(Platform.all())
                .setNotification(Notification.newBuilder()
                        .addPlatformNotification(IosNotification.newBuilder()
                                .setAlert(ALERT)
                                .setBadge(5)
                                .setSound("happy")
                                .addExtras(map)
                                .build())
                        .build())
                 .build();
    }
	
	
	
	
	
	
	public static void main(String[] args) {
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("headImage", "");
		map.put("time", "2017-07-28 22:18");
		map.put("title", "系统消息");
		
		sendSysUserMessage("6FF8AFD65E114211B11F10779BE891CC", "开发明尼苏达狂风巨浪四大美女佛i接撒多方联手大美女克里夫死了都快疯了快圣诞节阿飞空间啦三季度弗兰克四大美女发来看你哦撒旦法计算的蒙牛方面索尼东方会计师独立法撒旦法客流集散地法雷克萨的蒙牛发来看马上打开了福美来斯达康奶粉款手机啊到你发来看撒旦解放", map);//给单个用户发送
//		sendSysUserMessage("670ed0f593a481cc", "nihaoa", map);//给单个用户发送
//		sendAllMessage("this is a test", map);//给整个app用户发送
	}
}



