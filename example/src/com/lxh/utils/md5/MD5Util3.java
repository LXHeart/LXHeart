/**  
* @Title MD5Util.java
* @Package com.zd.comm.util
* @Copyright 西安正典软件科技有限公司 http://www.xazdsoft.com
* @author Administrator
* @date 2014-5-19 下午1:41:34 
* @History
*     Version  Date      Author     Content
*     -------- --------- ---------- ------------------------
*     1.0.0    2014-5-19   Administrator    最初版本  
*
*/

package com.lxh.utils.md5;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @ClassName: MD5Util加密工具类
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author ZSH
 * @date 2014-5-19 下午1:41:34
 *  
 *
 */

public class MD5Util3 {
	/** 
	* @Title: md5 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param str
	* @return
	* @author ZSH
	* @date 2014-5-19 下午1:42:25
	*/
	public static String md5(String str) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(str.getBytes());
			byte[] byteDigest = md.digest();
			int i;
			StringBuffer buf = new StringBuffer("");
			for (int offset = 0; offset < byteDigest.length; offset++) {
				i = byteDigest[offset];
				if (i < 0)
					i += 256;
				if (i < 16)
					buf.append("0");
				buf.append(Integer.toHexString(i));
			}
			// 32位加密
			return buf.toString();
			// 16位的加密
			// return buf.toString().substring(8, 24);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static void main(String[] args) {
		String a = MD5Util3.md5("321");
		System.out.println(a);
	}
}
