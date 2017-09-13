package com.lxh.thinkinjava.holding;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;

//Containers print themselves automatically
public class PrintingContainers {
	static Collection<String> fill(Collection<String> collection){
		collection.add("rat");
		collection.add("cat");
		collection.add("dog");
		collection.add("dog");
		return collection;
	}
	static Map<String, String> fill(Map<String, String> map){
		map.put("rat", "Fuzzy");
		map.put("cat", "Rags");
		map.put("dog", "Bosco");
		map.put("dog", "Stop");
		return map;
	}
	public static void main(String[] args) {
		/*
		 * 这里展示了java容器类库中的两种主要类型，他们区别在于容器中每个“槽”保存的元素个数。Collection在每个槽中只能保存一个元素：
		 * List，它以特定的顺序保存一组元素；
		 * Set，元素不能重复；
		 * Queue，只允许在容器的一“端”插入对象，并从另一“端”移除对象。
		 * Map在每个槽内保存了两个对象，即键和与之相关联的值。
		 */
		System.out.println(fill(new ArrayList<String>()));
		System.out.println(fill(new LinkedList<String>()));
		//ArrayList与LinkedList按照被插入的顺序保存元素。两者的不同之处仅在于执行某些类型的的操作是的性能不同；
		//大致区别：
		//1.ArrayList是实现了基于动态数组的数据结构，LinkedList基于链表的数据结构。
		//2.对于随机访问get和set，ArrayList绝对优于LinkedList，因为LinkedList要移动指针。
		//3.对于新增和删除操作add和remove，LinkedList比较占优势，因为ArrayList要移动数据。
		System.out.println(fill(new HashSet<String>()));
		System.out.println(fill(new TreeSet<String>()));
		System.out.println(fill(new LinkedHashSet<String>()));
		//HashSet、TreeSet、LinkedHashSet都是Set类型，输出显示在Set中，每个相同的项只保存一次，但是输出也显示了不同Set实现存储元素的方式也不同。
		//HashSet使用很复杂的方式存储元素，这种技术是最快的获取元素的方式，因此存储的顺序看起来并没有实际意义（通常你只会关心某事物是否是某个Set的成员而不会关心它在Set出现的顺序）
		//如果存储顺序很重要，那么可以使用TreeSet，它按照比较结果的升序保存对象；或者使用LinkedHashSet，它按照被添加的顺序保存对象。
		System.out.println(fill(new HashMap<String, String>()));
		System.out.println(fill(new TreeMap<String, String>()));
		System.out.println(fill(new LinkedHashMap<String, String>()));
		//区别：
		//1.HashMap提供了最快的查找技术，没有按照任何明显的顺序来保存其元素。
		//2.TreeMap按照比较结果的升序保存键，
		//3.LinkedHashMap按照插入顺序保存键，同时还保留了HashMap的查询速度。
	}

}
