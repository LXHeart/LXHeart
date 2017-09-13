package com.lxh.thinkinjava.innerclasses;
//An anonymous inner class that performs
public class Parcel9 {
	//Argument must be final to use inside anonymous inner class参数必须在内部匿名类内部使用
	public Destination destination(final String dest){
		return new Destination(){
			private String label = dest;
			public String readLabel(){return label;}
		};
	}
	//如果定义一个匿名内部类，并且希望它使用一个在其外部定义的对象，那么编译器会要求其参数引用是final的
	public static void main(String[] args) {
		Parcel9 p = new Parcel9();
		Destination d = p.destination("LXHeart");
	}
	
}
