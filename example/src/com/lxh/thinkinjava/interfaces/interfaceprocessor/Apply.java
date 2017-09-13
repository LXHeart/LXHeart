package com.lxh.thinkinjava.interfaces.interfaceprocessor;

public class Apply {
	
	public static void process(Processor p, Object s){
		System.out.println("Apply.processor:using Processor " + p.name());
		System.out.println(p.process(s));
	}

}
