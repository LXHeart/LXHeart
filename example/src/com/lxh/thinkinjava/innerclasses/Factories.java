package com.lxh.thinkinjava.innerclasses;

interface Service{
	void method1();
	void method2();
}

interface ServiceFactor{
	Service getService();
}

class Implementation1 implements Service{
	private Implementation1(){}
	public void method1(){System.out.println("Implementation1 method1");}
	public void method2(){System.out.println("Implementation1 method2");}
	public static ServiceFactor factor = new ServiceFactor() {//以ServiceFactor为基类创建一个
		public Service getService() {
			return new Implementation1();
		}
	};
}
//class Implementation2 implements Service{
//	private Implementation2(){}
//	public void method1(){System.out.println("Implementation2 method1");}
//	public void method2(){System.out.println("Implementation2 method2");}
//	public static ServiceFactor factor = new ServiceFactor() {
//		public Service getService() {
//			return new Implementation2();
//		}
//	};
//}

/**
 * 调用一个方法来创建一个类，并使用其中的方法
 * @author Administrator
 *
 */

public class Factories {
	public static void serviceConsumer(ServiceFactor fact){
		Service s = fact.getService();
		s.method1();
		s.method2();
	}
	/*
	 * 目的：使用Implementation1中的method1与method2方法
	 */
	public static void main(String[] args) {
		serviceConsumer(Implementation1.factor);//之前是直接new了一个Implementation1的对象
//		serviceConsumer(Implementation2.factor);
		
	}
	
}
