package com.lxh.test;

import java.util.HashMap;
import java.util.Map;

public class Test {
	
	public Map<Object, Integer> getRepeat(Object[] arr){
		Map<Object, Integer> map = new HashMap<Object, Integer>();
		for(int i = 0; i < arr.length; i++){
			Integer flag = 1;
			for(int j = i+1; j<arr.length; j++){
				if(arr[i].equals(arr[j])){
					flag++;
				}
			}
			if(flag>1){
				map.put(arr[i], flag);
			}
		}
		return map;
	}
	
	public static void main(String[] args) {
		Test t = new Test();
		Object[] c = {'c', 'a', 'g', 'c', 'b', 'c', 'a', 'a',};
		System.out.println(t.getRepeat(c));
	}

}
