package com.lxh.test;

import java.util.Iterator;
import java.util.List;

public class MyIterator<E> implements Iterator<E>{
	private int cursor;
	/**
	 * 传入的数据
	 */
	private transient Object[] data;
	/**
	 * 传入数据的长度
	 */
	private Integer size;
	
	/**
	 * 将List转为数组
	 * @param list
	 */
	public MyIterator(List<E> list){
		this.data = list.toArray();
		this.size = this.data.length;
	}
	
	@Override
	public boolean hasNext() {
		return cursor != size;
	}

	@SuppressWarnings("unchecked")
	@Override
	public E next() {
		cursor++;
		return (E) this.data[cursor-1];
	}

	@Override
	public void remove() {
		
	}
}
