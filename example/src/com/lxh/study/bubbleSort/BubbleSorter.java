package com.lxh.study.bubbleSort;

import java.util.Arrays;
import java.util.Comparator;
/**
 * 冒泡排序
 * @author Administrator
 *
 */
public class BubbleSorter implements Sorter {

	@Override
	public <T extends Comparable<T>> void sort(T[] list) {
		boolean swapped = true;
        for (int i = 1, len = list.length; i < len && swapped; ++i) {
            swapped = false;
            for (int j = 0; j < len - i; ++j) {
                if (list[j].compareTo(list[j + 1]) > 0) {
                    T temp = list[j];
                    list[j] = list[j + 1];
                    list[j + 1] = temp;
                    swapped = true;
                }
            }
        }
	}

	@Override
	public <T> void sort(T[] list, Comparator<T> comp) {
		boolean swapped = true;
        for (int i = 1, len = list.length; i < len && swapped; ++i) {
            swapped = false;
            for (int j = 0; j < len - i; ++j) {
                if (comp.compare(list[j], list[j + 1]) > 0) {
                    T temp = list[j];
                    list[j] = list[j + 1];
                    list[j + 1] = temp;
                    swapped = true;
                }
            }
        }
	}

	public static void main(String[] args) {
		BubbleSorter a = new BubbleSorter();
		
		Integer[] arr = new Integer[]{5,8,5,4,1,3};
		Arrays.sort(arr);
//		a.sort(arr);
		for(Integer b:arr){
			System.out.println(b);
		}
	}
}
