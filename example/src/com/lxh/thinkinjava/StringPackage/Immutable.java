package com.lxh.thinkinjava.StringPackage;
/**
 * 不可变String
 * 当调用upcase()方法时
 * @author Administrator
 *
 */
public class Immutable {
	public static String upcase(String s){
		System.out.println(s);
		return s.toUpperCase();
	}
	public static void main(String[] args) {
		String q = "howdy";
		System.out.println(q.hashCode());
		String qq = upcase(q);
		System.out.println(qq.hashCode());
		System.out.println(q.hashCode());
	}

}
