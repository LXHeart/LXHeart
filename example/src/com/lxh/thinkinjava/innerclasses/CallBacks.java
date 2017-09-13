package com.lxh.thinkinjava.innerclasses;
//Using inner classes for callbacks
interface Incrementable{
	void increment();
}

//very simple to just implement the interface
class Callee1 implements Incrementable{
	private int i = 0;
	public void increment(){
		i++;
		System.out.println(i);
	}
}

class MyIncrement{
	public void increment(){System.out.println("Other operation");}
	static void f(MyIncrement mi){mi.increment();}
}

//If your class must implements increment() in some other way, you must use a inner class
class callee2 extends MyIncrement{
	private int i = 0;
	public void increment(){
		super.increment();
		i++;
		System.out.println(i);
	}
	/*
	 * 这里有两个概念：闭包与回调；
	 * 闭包：是一个可调用的对象，它记录了一些信息，这些信息来自于创建他的作用域；
	 * 回调：通过回调，对象能够携带一些信息，这些信息允许他在稍后的某个时刻调用初始的对象；
	 * 
	 * 这里内部类Closure可以适用于闭包这个概念，通过内部类Closure中的increment方法回调外围类callee2对象的increment方法
	 */
	private class Closure implements Incrementable{
		public void increment(){
			callee2.this.increment();
		}
	}
	Incrementable getCallbackReference(){//这里Closure类似于一个适配器，由于Caller中只能传入一个Incrementable类型的实例，内部类Closure是一个Incrementable类型，而且其中的方法increment调用的是关联外部实例的increment方法，这时就可以覆盖掉Incrementable接口中的increment方法
		return new Closure();
	}
}

class Caller{
	private Incrementable callbackReference;
	Caller(Incrementable cbh){
		callbackReference = cbh;
	}
	void go(){
		callbackReference.increment();
	}
}

public class CallBacks {
	public static void main(String[] args) {
		Callee1 c1 = new Callee1();
		callee2 c2 = new callee2();
//		MyIncrement.f(c2);
		Caller caller1 = new Caller(c1);
		Caller caller2 = new Caller(c2.getCallbackReference());  //c2.getCallbackReference()获取了一个Closure实例，Closure实现了Incrementable接口，并重写了increment方法，这个方法调用了外部类的increment方法，给人一种calee1重写了Incrementable接口中的increment方法
		caller1.go();
		caller1.go();
		System.out.println(".......................................");
		caller2.go();
		caller2.go();
	}

}
