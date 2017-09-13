package com.lxh.thinkinjava.interfaces.classprocessor;

import java.util.Arrays;
//《think in java》9.3

class Processor{
	public String name(){
		return getClass().getSimpleName();
	}
	Object process(Object input){return input;}
}

class Upcase extends Processor{
	String process(Object input){
		return ((String)input).toUpperCase();
	}
}

class Downcase extends Processor{
	String process(Object input){
		return ((String)input).toLowerCase();
	}
}

class Splitter extends Processor{
	String process(Object input){
		return Arrays.toString(((String)input).split(" "));
	}
}
/**
 * 创建一个能够根据所传递的参数对象的不同而具有不同行为的方法，被称为策略模式。这类方法包含所要执行的算法中固定不变的部分，而“策略”包含
 * 变换的部分。策略就是传递进去的参数对象，它包含要执行的代码。这里Processor对象就是一个策略，在main()中可以看到三种不同类型的策略
 * 应用到了String类型的s对象上。
 */
public class Apply {
	public static void process(Processor p, Object s){
		System.out.println("User Processor " + p.name());
		System.out.println(p.process(s));
	}
	public static String s = "Disagreement with beliefs is by definition incorrect";
	
	public static void main(String[] args) {
		process(new Upcase(), s);
		process(new Downcase(), s);
		process(new Splitter(), s);
	}
}
