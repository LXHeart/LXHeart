package com.lxh.thinkinjava.innerclasses;

//10.7语义理解：普通内部类的字段与方法，只能放在类的外部层次上，所以普通的内部类不能有static数据和static字段，也不能包含嵌套类；
public class Test {
	/*
	 *   ordinary inner class不能定义 static数据和static field,这个static的定义有关，static定义的数据可以直接使用已经加载的类的类名
	 * 进行直接调用，而不需要创建类的实例，但是要使用ordinary inner class就必须创建outer class实例，这违背了static的定义；
	 *   但是在普通内部类中可以使用static final修饰的基本数据类型与字符串，这应该与final的特性有关；但是在使用static final修饰的引用类型就
	 * 会报错，这是在MyEclipse中出现的问题；
	 */
	class Child{
		static final String str = "123";
//		 static final Date str = new Date();这样写eclipse会报错
	}
	
	public Child getChild(){
		return new Child();
	}
	
	public static void main(String[] args) {
//		Test t = new Test();
//		Test.Child c = t.getChild();
//		System.out.println(c.str);
//		Child c = new Child();
//		System.out.println(c.str);
	}

}
