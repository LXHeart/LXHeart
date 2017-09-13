package com.lxh.study.javaCallbackStudy;
/**
 * 测试类
 * @author Administrator
 *
 */
/*
 * 有一天小王遇到一个很难的问题，问题是“1 + 1 = ?”，就打电话问小李，小李一下子也不知道，就跟小王说，等我办完手上的事情，就去想想答案，小王也不会傻傻的拿
 * 着电话去等小李的答案吧，于是小王就对小李说，我还要去逛街，你知道了答案就打我电话告诉我，于是挂了电话，自己办自己的事情，过了一个小时，小李打了小王的电话，
 * 告诉他答案是2
 */
public class Test {

	public static void main(String[] args){
		
		Li li = new Li();
		Wang wang = new Wang(li);
		wang.askQuestion("1+1=?");
		/*
		 * 调用了wang.askQuestion方法，这个方法调用了li.executeMessage方法，li.executeMessage方法又调用了wang.solve方法
		 */
	}
	
}
