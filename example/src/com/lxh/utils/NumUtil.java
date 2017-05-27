package com.lxh.utils;

import java.text.DecimalFormat;
/**
 * 数字工具类
 * @author Wei
 *
 */
public class NumUtil {

	/**
	 * 将小数转换成百分制
	 * @param num type：double double类型的数据
	 * @return String
	 */
	public static String numToPercent(double num){
		DecimalFormat df = new DecimalFormat("0.000%");
		return df.format(num);
	}
}
