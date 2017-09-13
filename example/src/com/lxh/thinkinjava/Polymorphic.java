package com.lxh.thinkinjava;

enum Node{
	Middle_C, C_SHARP, B_FLAT
}


class Instrument{
	public void play(Node n){
		System.out.println("Instrument.play()");
	}
}


class Wind extends Instrument{
	public void play(Node n){
		System.out.println("Wind.play()+" + n);
	}
}


public class Polymorphic{
	public static void tune(Instrument i){
		i.play(Node.Middle_C);
	}
	public static void main(String[] args){
		Wind flute = new Wind();
		
		tune(flute);
	}
}
