package com.lxh.thinkinjava.innerclasses.controller;

import java.util.ArrayList;
import java.util.List;
//10.8.2 内部类与控制框架
//The reusable framework for control system
public class Controller {
	//A class from java.util to hold Event objects;
	private List<Event> eventList = new ArrayList<Event>();
	public void addEvent(Event c){
		eventList.add(c);
	}
	public void run(){
		while(eventList.size()>0){
			//Make a copy so you're not modifying the list while you're selecting the elements in it;
			for(Event e : new ArrayList<Event>(eventList)){
				if(e.ready()){
					System.out.println(e);
					e.action();
					eventList.remove(e);
				}
			}
		}
	}

}
