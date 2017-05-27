package com.lxh.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class MD5 {

	
	 /**
     * MD5���ܣ�����byte[]����
     * 
     * @param data
     * @return
     * @throws NoSuchAlgorithmException
     */
    private static byte[] encryptMD5(byte[] data)
            throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("MD5");
        digest.update(data);
        return digest.digest();
    }
    /**
     * ��MD5�������ת��String����
     * 
     * @param data
     * @return
     * @throws NoSuchAlgorithmException
     */
    private static String encryptMD5toString(byte[] data)
            throws NoSuchAlgorithmException {
        String str = "";
        String str16;
        for (int i = 0; i < data.length; i++) {
            str16 = Integer.toHexString(0xFF & data[i]);
            if (str16.length() == 1) {
                str = str + "0" + str16;
            } else {
                str = str + str16;
            }
        }
        return str;
    }
    
    
    /**
     * ��ȡMD5ֵ
     * @param str ��Ҫת��Ϊmd5���ַ�
     * @return ����md5ֵ
     */
    public static String getMD5(String str){
    	 byte[] bytes = null;
         String str1 = null;
         try {
			bytes = encryptMD5(str.getBytes());
			str1 = encryptMD5toString(bytes);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
    	return str1;
    }
    
    public static void main(String[] avg) throws NoSuchAlgorithmException {
       /* String data = "XSoftlab1";
        System.out.println("����ǰ:" + data);
        byte[] bytes = null;
        String str = null;
        bytes = Test.encryptMD5(data.getBytes());
        
        str = Test.encryptMD5toString(bytes);
         
        System.out.println("���ܺ�:" + str);*/
    	
    	System.out.println(MD5.getMD5("58"));
    }
	
	
}
