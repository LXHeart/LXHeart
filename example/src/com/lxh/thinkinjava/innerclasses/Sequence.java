package com.lxh.thinkinjava.innerclasses;

import java.util.Iterator;

//Holds a sequence of Objects
//迭代器模式的简单实现
interface Selector{
	boolean end();//判断是否结束
	Object current();//获取当前对象
	void next();//获取下一个对象
}

public class Sequence {//序列
	private Object[] items;
	private int next = 0;
	public Sequence(int size){
		items = new Object[size];
	}
	public void add(Object x){
		if(next < items.length){
			items[next++] = x;
		}
	}
	private class SequenceSelector implements Selector{//这就是一个迭代器模式
		private int i = 0;
		public boolean end(){return i == items.length;}//
		public Object current(){return items[i];}
		public void next(){if(i < items.length) i++;}
		@SuppressWarnings("unused")
		public Sequence getSequence(){
			return Sequence.this;
		}
	}
	public Selector selector(){
		return new SequenceSelector();
	}
	public static void main(String[] args) {
		Sequence sequence = new Sequence(12);//初始化类型为Object的items数组初始大小为10
		for(int i = 0; i<10; i++)
			sequence.add(Integer.toString(i));//通过add()方法给items数组中放置值
		Selector selector = sequence.selector();//创建SequenceSelector对象，这个对象继承于Selector
		while(!selector.end()){
			System.out.println(selector.current() + " ");
			selector.next();
		}
	}
	
	
}
