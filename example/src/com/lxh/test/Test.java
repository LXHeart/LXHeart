package com.lxh.test;

import java.util.ArrayList;
import java.util.List;

public class Test {
	
	public static void main(String[] args) {
		List<Integer> a = new ArrayList<Integer>();
		for(int i = 0; i<10; i++){
			a.add(i);
		}
		MyIterator<Integer> c = new MyIterator<Integer>(a);
		while(c.hasNext()){
			System.out.println(c.next());
		}
	}

}
