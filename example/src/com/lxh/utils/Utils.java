package com.lxh.utils;

import it.sauronsoftware.jave.Encoder;
import it.sauronsoftware.jave.MultimediaInfo;

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
	/**
	 * 获取视频时长
	 * @param filePath 视频文件路径
	 * @return 返回一个String类型的秒数
	 */
	public static String getVideoTime(String filePath){
		File source = new File(filePath);
        Encoder encoder = new Encoder();
        try {
             MultimediaInfo m = encoder.getInfo(source);
             long ls = m.getDuration();
//             System.out.println("此视频时长为:"+ls/60000+"分"+(ls^000)/1000+"秒！");
             return String.valueOf( (ls/60000+(ls^000)/1000));
        } catch (Exception e) {
            e.printStackTrace();
           return "0";
        }
	}
	/**
	 * 获取视频时长
	 * @param source File类型
	 * @return 返回一个Strin类型的秒数
	 */
	public static String getVideoTime(File source){
		Encoder encoder = new Encoder();
        try {
             MultimediaInfo m = encoder.getInfo(source);
             long ls = m.getDuration();
//             System.out.println("此视频时长为:"+ls/60000+"分"+(ls^000)/1000+"秒！");
//             return ls/60000 + ":" + (ls^000)/1000;
             return String.valueOf( (ls/60000+(ls^000)/1000));
        } catch (Exception e) {
            e.printStackTrace();
           return "0";
        }
	}
	/**
	 * 根据传如的秒数获取一个标准的时间（00:00）
	 * @param totalSecond 秒数
	 * @return String类型的标准时间：分钟:秒
	 */
	public static String getStandardTime(Integer totalSecond){
		if(null == totalSecond){
			return "00:00";
		}
		Integer minute = totalSecond/60;
		Integer second = totalSecond - 60 * minute;
		StringBuffer sb = new StringBuffer();
		if(minute < 10){
			sb.append("0" + minute + ":");
		}else{
			sb.append(minute + ":");
		}
		if(second < 10){
			sb.append("0" + second);
		}
		else{
			sb.append(second);
		}
		return sb.toString();
	}
	
	public static void main(String[] args) {
		Integer a = null;
		System.out.println(getStandardTime(589));
	}
}
