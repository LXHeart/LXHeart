package com.lxh.baidu.speech;

import java.util.HashMap;

import org.json.JSONObject;

import com.baidu.aip.ocr.AipOcr;

public class BaiduSpeechClient {
	

	    //设置APPID/AK/SK
	    public static final String APP_ID = "9980852";
	    public static final String API_KEY = "4MngA3nFbuV6ScjUxKWNuaPn";
	    public static final String SECRET_KEY = "G9PVbpfPLtdqkeFko4tM0FzYyQpg5wLo";

	    public static void main(String[] args) {
	        // 初始化一个OcrClient
	        AipOcr client = new AipOcr(APP_ID, API_KEY, SECRET_KEY);

	        // 可选：设置网络连接参数
	        client.setConnectionTimeoutInMillis(2000);
	        client.setSocketTimeoutInMillis(60000);

	        // 参数为本地图片路径
	        String imagePath = "D://qqqq.jpg";
	        JSONObject response = client.basicGeneral(imagePath, new HashMap<String, String>());
	        System.out.println(response.toString());

	        
	    }

}
