package com.lxh.thinkinjava.interfaces.newClass;

public class NewClass {
	public String name(){
		return getClass().getSimpleName() + "************";
	}
	public Object process(Object s){
		return s;
	}
}
