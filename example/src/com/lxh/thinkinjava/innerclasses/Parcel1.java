package com.lxh.thinkinjava.innerclasses;
//Creating inner class
public class Parcel1 {//包裹
	class Contents{
		private int i = 11;
		public int value(){return i;}
	}
	class Destination{//目的
		private String label;//标签
		Destination(String whereTo){
			label = whereTo;
		}
		String readLabel(){return label;}
	}
	public void ship(String dest){
		Contents c = new Contents();
		Destination d = new Destination(dest);
		System.out.println(d.readLabel());
	}
	public static void main(String[] args) {
		Parcel1 p = new Parcel1();
		p.ship("Tasmania");
	}
	
}

