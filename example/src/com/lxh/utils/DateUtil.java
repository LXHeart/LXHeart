package com.lxh.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.crypto.Data;

/**
 * 时间工具类
 * @author Wei
 *
 */
public class DateUtil {
	/*
	 * 时间格式为：yyyy-MM-dd HH:mm:ss
	 */
	private static final String DATE_FORMAT1 = "yyyy-MM-dd HH:mm:ss";
	private static final String DATE_FORMAT2 = "yyyy-M-d H:m:s";
	private static final String DATE_FORMAT3 = "yyyy-MM-dd HH:mm:ss:SSS";
	
	/**
	 * 将字符串类型的日期转换成date类型的日期
	 * @param str 格式为yyyy-MM-dd HH:mm:ss的时间类型字符串
	 * @return date类型的时间
	 * @throws ParseException
	 */
	public static Date strToDate(String str){
		DateFormat fmtDateTime = new SimpleDateFormat(DATE_FORMAT1);
		Date date = null;
		try {
			date = fmtDateTime.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
	/**
	 * 获取当前时间
	 * @return 格式为yyyy-MM-dd HH:mm:ss:SSS的字符串
	 */
	public static String getCurrentTime(){
		DateFormat fmtDateTime = new SimpleDateFormat(DATE_FORMAT3);
		return fmtDateTime.format(new Date());
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public static void main(String[] args) {
	}
}
