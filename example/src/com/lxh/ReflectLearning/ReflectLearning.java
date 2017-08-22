package com.lxh.ReflectLearning;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ReflectLearning {
	
	public static void main(String[] args) throws Exception{
		Dog dog = new Dog();
		System.out.println(dog.getClass());
		System.out.println(dog.getClass().getName());
		
		Class<?> dogClass = dog.getClass();
		Class<?> dogClass1 = Dog.class;
		Class<?> dogClass2 = Class.forName("com.lxh.test.Dog");
		
		Method[] method1 = dogClass.getMethods();
		System.out.println(".................通过getMethods()获取方法开始...............");
		for(Method m : method1){
			System.out.println(m);
		}
		System.out.println(".................通过getMethods()获取方法结束...............");
		Method[] method2 = dogClass.getDeclaredMethods();
		System.out.println(".................通过getDeclaredMethods()获取方法开始...............");
		for(Method m : method2){
			System.out.println(m);
		}
		System.out.println(".................通过getDeclaredMethods()获取方法结束...............");
		Constructor<?>[] constructors = dogClass.getConstructors();
		System.out.println("===========列出所有构造函数开始=================");
		for(Constructor<?> c: constructors){
			System.out.println(c);
		}
		System.out.println("===========列出所有构造函数结束=================");
		
		System.out.println("==========通过newInstance()来生成对象，一定存在默认构造函数==========");
		Dog dog1 = (Dog)dogClass.newInstance();
		dog1.setName("狗狗1号");
		dog1.setAge(7);
		System.out.println(dog1);
		
		System.out.println("===========通过newInstance(参数)方法一来生成对象================");
		Dog dog2 = (Dog)constructors[1].newInstance("狗狗二号");
		dog2.setAge(10);
		System.out.println(dog2);
		
		System.out.println("===========通过newInstance(参数)方法二来生成对象================");
		Constructor<?> con1 = dogClass.getConstructor(new Class[]{String.class, int.class});
		Dog dog3 = (Dog)con1.newInstance(new Object[]{"狗狗三号", 14});
		System.out.println(dog3);
		
		Method[] methods = dogClass.getDeclaredMethods();
		for(Method m:methods){
			System.out.println("函数名：" + m.getName() + "\t函数类型：" + 
					m.getModifiers() + "\t函数返回：" + m.getReturnType() );
		}
		
		//调用私有方法
		Method method3 = dogClass.getDeclaredMethod("sleep", int.class);
		Dog dog4 = (Dog)dogClass.getConstructor(new Class[]{String.class}).newInstance(new Object[]{"狗狗1号"});
		method3.setAccessible(true);//私有方法一定要加这句
		method3.invoke(dog4, 13);
		
		//调用私有静态方法
		Method method4 = dogClass.getDeclaredMethod("play");
		method4.setAccessible(true);//私有方法一定要加这局
		method4.invoke(dogClass.newInstance());
		
		//调用公共方法
		Method method5 = dogClass.getDeclaredMethod("est", String.class);//这里也可以调用getDeclaredMethod()
		Dog dog5 = new Dog("狗狗3号", 45);
		method5.invoke(dog5, "苹果！");
		
		//属性的获取与更改
		Dog dog6 = new Dog("狗狗1号", 12);
		System.out.println(dog6);
		
		Class<?> dogClass3 = dog6.getClass();
		Field field1 = dogClass3.getDeclaredField("name");//注意，getField只能取public的字段
		field1.setAccessible(true);
		System.out.println("狗狗原名：" + field1.get(dog6));
		field1.set(dog6, "狗狗2号");
		System.out.println(dog6);
		
		
		
		
		
		
		
	}

}
