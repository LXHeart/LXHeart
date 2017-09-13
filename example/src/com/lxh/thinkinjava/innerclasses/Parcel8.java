package com.lxh.thinkinjava.innerclasses;
//Calling the base-class constructor
public class Parcel8 {
	public Wrapping wrapping(int x){//传递适合的参数给基类的构造器
		return new Wrapping(x){//被其导出的类当作公共“接口”来使用，到处的类就是该匿名内部类
			public int value(){
				return super.value() * 47;
			}
		};//semicolon required;分号表示表达式的结束，只不过这个表达式正巧包含了匿名内部类罢了。
	} 
	public static void main(String[] args) {
		Parcel8 p = new Parcel8();
		Wrapping w = p.wrapping(10);
	}

}
