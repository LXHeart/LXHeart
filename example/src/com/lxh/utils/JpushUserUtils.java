package com.lxh.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jpush.api.JPushClient;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Message;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
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
	
	 public static PushPayload buildPushObject_all_alias_alert(String alias, String ALERT) {
	        return PushPayload.newBuilder()
	                .setPlatform(Platform.all())
	                .setAudience(Audience.alias(alias))
	                .setNotification(Notification.alert(ALERT))
	                .build();
	    }
	
	 /**
	  * send user-defined message(content) for which device ID is alias
	  * @param alias
	  * @param content
	  * @return
	  */
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
	public static PushPayload buildPushObject_all_all_alert(String alert) {
        return PushPayload.alertAll(alert);
    }
	
	
	/**
	 * send message(content) for all user 
	 * 
	 * @param content
	 * @return
	 */
	public static String sendSysMessage(String content){
		PushPayload payload =buildPushObject_all_all_alert(content);
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
	
	//send message(content) for which Device ID is alias
	public static String sendSysUserMessage(String alias,String content){
		PushPayload payload =buildPushObject_all_alias_alert(alias,content);
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
	
	public static void main(String[] args) {
//		sendSysUserMessage("6FF8AFD65E114211B11F10779BE891CC", "发送的内容！");//给单个用户发送
		sendSysMessage("this is a test");//给整个app用户发送
	}
}



