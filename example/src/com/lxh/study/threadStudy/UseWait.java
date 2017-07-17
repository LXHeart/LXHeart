package com.lxh.study.threadStudy;

/**
 * 建立三个线程，A线程打印10次A，B线程打印10次B，C线程打印10次C	，要求线程同时运行，交替打印10此ABC。
 * @author Administrator
 *
 */
public class UseWait implements Runnable{
	
	private String name;
	private Object prev;
	private Object self;
	
	private UseWait(String name, Object prev, Object self){
		this.name = name;
		this.prev = prev;
		this.self = self;
	}

	@Override
	public void run() {
		int count = 10;
		while(count>0){
			synchronized(prev){
				synchronized(self){
					System.out.println(name);
					count--;
					self.notify();
				}
				try {
					prev.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		} 
		
	}
	
	
	public static void main(String[] args) throws Exception{
		Object a = new Object();
		Object b = new Object();
		Object c = new Object();
		
		UseWait pa = new UseWait("A", c, a);
		UseWait pb = new UseWait("B", a, b);
		UseWait pc = new UseWait("C", b, c);
		
		new Thread(pa).start();  
        Thread.sleep(100);  //确保按顺序A、B、C执行  
        new Thread(pb).start();  
        Thread.sleep(100);    
        new Thread(pc).start();     
        Thread.sleep(100);
		
	}

}
