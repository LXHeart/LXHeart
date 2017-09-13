package com.lxh.thinkinjava.holding;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

//Adding group of elements to Collection objects
public class AddingGroups {
	public static void main(String[] args) {
		long t1 = System.nanoTime();
		Collection<Integer> collection = new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4, 5));
		long t2 = System.nanoTime();
		System.out.println(t2 - t1);
		Integer[] moreInts = {6, 7, 8, 9, 10};
		collection.addAll(Arrays.asList(moreInts));
		long t3 = System.nanoTime();
		System.out.println(t3 - t2);
		//Runs significantly faster, but you can't construct a Collection this way;
		Collections.addAll(collection, 11, 12, 13, 14, 15);
		Collections.addAll(collection, moreInts);
		//Produces a list "backed by" an array
		List<Integer> list = Arrays.asList(16, 17, 18, 19, 20);//可以使用Arrays.asList输出来当作List，但是底层表示的是数组，不能调整尺寸
		list.set(1, 99);//OK~~modify an element
		//list.add(21);
		//Runtime error because the underlying array cannot be resized
		System.out.println(list);
		
	}
}
