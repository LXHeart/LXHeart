package com.lxh.thinkinjava.interfaces.newClass;

public class Change extends NewClass{
	
	
	public Object process(Object s) {
		char[] c = s.toString().toCharArray();
		String result = "";
		for(int i = c.length-1; i>=0; i--){
			result += c[i];
		}
		return result;
	}
}
