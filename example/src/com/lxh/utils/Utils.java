package com.lxh.utils;

import java.io.File;
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
	 * 删除文件
	 * @param path 需要删除的文件的路径
	 * @return boolean 成功返回true，失败返回false
	 */
	public static boolean delFile(String path){
		boolean flag = false;
		File file = new File(path);
		//判断是否是文件与是否存在
		if (file.isFile() && file.exists()) {
			file.delete();
			flag = true;
		}
		return flag;
	}
	/**
	 * 将小数转换成百分制
	 * @param num type：double
	 * @return String
	 */
	public static String numToPercent(double num){
		DecimalFormat df = new DecimalFormat("0.00%");
		return df.format(num);
	}
	
	public static void main(String[] args) {
		System.out.println(numToPercent(0.36548));
	}

}
