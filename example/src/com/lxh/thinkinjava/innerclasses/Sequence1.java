package com.lxh.thinkinjava.innerclasses;

import java.util.Iterator;

//Holds a sequence of Objects
//迭代器模式的简单实现

public class Sequence1 {//序列
	private Object[] items;
	private int next = 0;
	public Sequence1(int size){
		items = new Object[size];
	}
	public void add(Object x){
		if(next < items.length){
			items[next++] = x;
		}
	}
	/*
	 * Sequence使用的是实现的是自己的接口Selector，并实现迭代器模式；这里实现了JDK给出的Iterator接口，重写其中的方法来实现迭代
	 */
	private class SequenceSelector implements Iterator{//这就是一个迭代器模式
		private int i = 0;
		public boolean hasNext(){return i == items.length;}//
		public Object next(){
			Object flag = items[i];
			remove();
			return flag;
		}
		@SuppressWarnings("unused")
		public Sequence1 getSequence(){
			return Sequence1.this;
		}
		@Override
		public void remove() {
			if(i < items.length) 
				i++;
		}
	}
	public Iterator selector(){
		return new SequenceSelector();
	}
	public static void main(String[] args) {
		Sequence1 sequence = new Sequence1(10);//初始化类型为Object的items数组初始大小为10
		for(int i = 0; i<10; i++)
			sequence.add(Integer.toString(i));//通过add()方法给items数组中放置值
		Iterator selector = sequence.selector();//创建SequenceSelector对象，这个对象继承于Selector
		while(!selector.hasNext()){
			System.out.println(selector.next() + " ");
		}
	}
}
