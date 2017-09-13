package com.lxh.thinkinjava.interfaces;
/**
 * 9.9接口与工厂
 * @author Administrator
 *
 */
interface Service {
	String method1();
	void method2();
}
interface ServiceFactory {
	Service getService();
}
class Implementation1 implements Service {
	Implementation1(){};
	public String method1(){return "Implementation1...method1";}
	public void method2(){System.out.println("Implementation1...method2");}
}
class Implementation1Factory implements ServiceFactory {
	public Service getService(){
		return new Implementation1();
	}
}
//class Implementation2 implements Service {
//	Implementation2(){};
//	public void method1(){System.out.println("Implementation2...method1");}
//	public void method2(){System.out.println("Implementation2...method2");}
//}
//class Implementation2Factory implements ServiceFactory {
//	public Service getService(){
//		return new Implementation2();
//	}
//}
/*
 * 传入serviceFactor的实现，实现重写service中的getService（）方法，并在方法内生成实例1或实例2的对象，实例1与实例2均为service的实现，
 * 可以向上转型为service，并调用实现重写的方法，从而可以更好的隐藏实现过程并可以生成需要的实例，根据传入不同的serviceFactory的实现，从而生
 * 产生不同的结果，这种可以称为策略模式
 * 
 */
public class Factories {
	public static String serviceConsumer(ServiceFactory fact){
		Service s = fact.getService();
//		s.method2();
		return s.method1();
	}
	
	public static void main(String[] args) {
		System.out.println(serviceConsumer(new Implementation1Factory()));
//		serviceConsumer(new Implementation2Factory());
	}
}
