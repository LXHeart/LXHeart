package com.lxh.study.threadStudy;
/**
 * java多线程学习(出处：http://blog.csdn.net/evankaka/article/details/44153709)
 * 
 * 进程：每个进程都有独立的代码和数据空间（进程上下文），进程的切换会有较大的开销，一个进程包含1--n个线程。（进程是资源分配的最小单位）
 * 线程：同一类线程共享代码和数据空间，每个线程有独立的运行栈和程序计数器(PC)，线程切换开销小。（线程是CPU调度的最小单位）
 * 线程和进程一样分为五个阶段：创建、就绪、运行、阻塞、终止。
 * 多进程是指在操作系统能同时运行多个任务(程序)。
 * 多线程是指在同一程序中有多个顺序流在执行。
 * 
 * 在java中要想实现多线程，有两种手段，一种是继承Thread类，另一种是实现Runnable接口(其实准确来讲，应该有三种，还有一种是实现Callable接口，
 * 并于Futurn、线程池结合使用)
 * 
 * 本类介绍继承lang.Thread类
 * 
 * @author LXHeart
 * @since 2017/07/14
 * 
 *
 */
public class BaseAndExtendsThread extends Thread{
	
	private String name;
	
	public BaseAndExtendsThread(String name){
		this.name = name;
	}
	
	
	public void run(){
		for (int i = 0; i < 5; i++) {  
            System.out.println(name + "运行  :  " + i);  
            try {  
                sleep((int) Math.random() * 10);  
            } catch (InterruptedException e) {  
                e.printStackTrace();  
            }  
        }
	}
	
	public static void main(String[] args) {
		BaseAndExtendsThread b1 = new BaseAndExtendsThread("A");
		BaseAndExtendsThread b2 = new BaseAndExtendsThread("B");
		b1.start();
		b2.start();
		
		/*
		 * 说明：程序启动运行main的时候，java虚拟机启动一个线程，主线程main在main()调用时被创建。随着调用BaseAndExtendsThread的两个对象
		 * 的start方法，另外两个线程也启动了，这样，整个应用就在多线程下运行了。
		 * 
		 * 注意：start()方法的调用并不是立即执行多线程代码，而是使得该线程变为可运行状态(Runnable)，什么时候运行是由操作系统决定的。
		 * 从程序运行的结果可以发现，多线程程序是乱序执行。因此，只有乱序执行的代码才有必要设计为多线程。
		 * 
		 * Thread.sleep()方法调用目的是不让当前线程独自霸占该进程所获取的CPU资源，以留出一定时间给其他线程执行的机会。
		 * 
		 * 但是start()方法重复调用的话，会出现java.lang.IllegalThreadStateException异常。
		 * 
		 */
	}
	
	

}
