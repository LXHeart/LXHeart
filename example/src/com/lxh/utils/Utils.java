package com.lxh.utils;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;


public class Utils {
	/**
	 * 获取当前时间 格式为"2017-03-25 09:16:68"
	 * @return String 
	 */
	public static String getCurrentTime(){
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//获取的时间格式
		String date = df.format(new Date());// new Date()获取当前时间
		return date;
	}
	/**
	 * 获取UUID
	 * @return
	 */
	public static String getUUID(){
		return UUID.randomUUID().toString().replace("-", "");
	}
	/**
	 * 获取百分比；例如传入类型为double的"0.38654"，将会返回String类型的"38.65%"
	 * @param num 传入double类型的num
	 * @return String
	 * @since 2017/07/14
	 * @author LXHeart
	 */
	public static String getPercent(double num){
		DecimalFormat df = new DecimalFormat("0.00%");
		return df.format(num);
	}
	
	public static void main(String[] args) {
		System.out.println(getPercent(0.38654));
	}

}
