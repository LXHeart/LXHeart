package com.lxh.thinkinjava.innerclasses;

public class DotNew {
	public class Inner{}
	public static void main(String[] args) {
		DotNew dn = new DotNew();
		DotNew.Inner dni = dn.new Inner();//创建内部类的对象
	}
}
//在拥有外部类对象之前是不可能创建内部类对象的。这是因为内部类对象会暗暗地连接到创建它的外部类对象上。但是，如果你创建的是嵌套类（静态内部类），
//那么他就不需要对外部类对象的引用。
