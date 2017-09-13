package com.lxh.thinkinjava.innerclasses;
//Expanded version of Parcel7.jaca
public class Parcel7b {
	class MyContents implements Contents{
		private int i = 11;
		public int value(){return i;}
	}
	public Contents contents(){return new MyContents();}//Parcel7.java是这个类的简化形式
	public static void main(String[] args) {
		Parcel7b p = new Parcel7b();
		Contents c = p.contents();
	}
}
