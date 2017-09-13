package com.lxh.thinkinjava.innerclasses.controller;
//10.8.2 内部类与控制框架
//The common methods for any control event
public abstract class Event {
	private long eventTime;
	protected final long delayTime;
	public Event(long delayTime){
		this.delayTime = delayTime;
		start();
	}
	public void start(){//allows restarting  允许重新启动
		eventTime = System.nanoTime() + delayTime;
	}
	public boolean ready(){
		return System.nanoTime() >= eventTime;
	}
	public abstract void action();
	

}
