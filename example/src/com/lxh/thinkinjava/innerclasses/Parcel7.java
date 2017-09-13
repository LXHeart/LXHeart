package com.lxh.thinkinjava.innerclasses;
//Returning an instance of an anonymous inner class
public class Parcel7 {
	public Contents contents(){
		return new Contents(){//创建一个继承自Contents的匿名类的对象
			private int i = 11;
			public int value(){return i;}
		};
	}
	public static void main(String[] args) {
		Parcel7 p = new Parcel7();
		Contents c = p.contents();
		System.out.println(c.value());
	}
}
