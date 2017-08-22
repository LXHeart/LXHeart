package com.lxh.ReflectLearning;

public class Dog implements Animal {
	private String name;
	private int age;
	public Dog(){}
	public Dog(String name, int age){
		this.name = name;
		this.age = age;
	}
	public Dog(String name){
		this.name = name;
		this.age = 10;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public void sleep(int x){
		System.out.println(name + "睡觉" + x + "分钟");
	}
	@Override
	public String est(String obj) {
		System.out.println(name + "吃" + obj);
		return null;
	}
	@Override
	public int run(int obj) {
		System.out.println("跑，速度：" + obj);
		return 0;
	}
	@Override
	public String toString() {
		return "狗名：" + name + ",狗的年纪：" + age;
	}
	public static void play(){
		System.out.println("狗狗自己玩！");
	}
}
