package com.lxh.thinkinjava.holding;

import java.util.ArrayList;

//Simple container example (produces compiler warning)(ThrowsException)
class Apple{
	private static long counter;
	private final long id = counter++;
	public long id(){return id;}
}

class Orange{}

public class ApplesAndOrangesWithoutGenerics {
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		@SuppressWarnings("rawtypes")
		ArrayList apples = new ArrayList<>();
		for(int i = 0; i<3; i++)
			apples.add(new Apple());
		//Not prevented from adding an Orange to apples
		apples.add(new Orange());
		for(int i=0; i<apples.size(); i++){
			((Apple)apples.get(i)).id();
			//Orange is detected only at run time
		}
	}

}
