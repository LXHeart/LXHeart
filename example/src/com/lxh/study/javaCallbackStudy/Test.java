package com.lxh.study.javaCallbackStudy;
/**
 * 测试类
 * @author Administrator
 *
 */
public class Test {

	public static void main(String[] args){
		
		Li li = new Li();
		Wang wang = new Wang(li);
		
		
		wang.askQuestion("1+1=?");
	}
	
}
