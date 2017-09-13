package com.lxh.thinkinjava.innerclasses;

public class DotThis {
	void f(){System.out.println("DotThis.f()");}
	public class Inner{
		public DotThis outer(){
			return DotThis.this;//对外部类对象的引用
		}
	}
	public Inner inner(){
		return new Inner();
	}
	public static void main(String[] args) {
		DotThis dt = new DotThis();
		DotThis.Inner dtf = dt.inner();
		dtf.outer().f();
	}
}
