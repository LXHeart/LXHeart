package com.lxh.thinkinjava.innerclasses;

/*
 * 接口内部的类
 */
public interface ClassInInterface {
	void howdy();
	class Test implements ClassInInterface{
		public void howdy(){
			System.out.println("howdy()");
		}
		public static void main(String[] args) {
			new Test().howdy();
		}
	}
}
//如何使用接口内部的类：之前使用内部类有两种方法，一种是在外部类中创建用于生成内部类实例的方法，另一种是使用.new来创建
//Outerclass.InnerClass instanceName = OuterClass.new InnerClass();
//而使用接口中的类创建实例需要使用Interface.InnerClass instanceName = Interface.InnerClass();
//但是在这里如何使用内部类中的main函数我还是不知道
