package com.lxh.thinkinjava.exception;
/**
 * 栈轨迹
 * printStackTrance()方法所提供的信息可以通过getStackTrace()方法来直接访问，这个方法将返回一个由战轨迹中的元素所构成的数组，其中每一个元素都表示
 * 栈中的一帧。元素0是栈顶元素。
 * @author Administrator
 *
 */
public class WhoCalled {
	static void f(){
		try {
			throw new Exception();
		} catch (Exception e) {
			for(StackTraceElement ste:e.getStackTrace())
				System.out.println(ste);
		}
	}
	
	static void g(){f();}
	static void h(){g();}
	public static void main(String[] args) {
		f();
		System.out.println(".............................");
		g();
		System.out.println(".............................");
		g();
	}
}
