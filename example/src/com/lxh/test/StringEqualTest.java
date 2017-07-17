package com.lxh.test;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
/**
 * 文件复制
 * @author Administrator
 *
 */
public class StringEqualTest {
		 public static void main(String[] args) {
		        String s1 = "Programming";
		        String s2 = new String("Programming");
		        String s3 = "Program";
		        String s4 = "ming";
		        String s5 = "Program" + "ming";
		        String s6 = s3 + s4;
//		        System.out.println(s1 == s2);
//		        System.out.println(s1 == s5);
//		        System.out.println(s1 == s6);
//		        System.out.println(s1 == s6.intern());
//		        System.out.println(s2 == s2.intern());
		        
		        assert(1>2);
		    }
		 
		 
		 
		 public void a(String a, int b){
			 
			 
		 }
		 public void a(int a, String b){
			 
		 }
	
	
}
