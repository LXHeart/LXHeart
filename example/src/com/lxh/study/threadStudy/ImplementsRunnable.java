package com.lxh.study.threadStudy;
/**
 * 实现java.lang.Runnable接口
 * @author LXHeart
 * @since 2017/07/14
 *
 */
public class ImplementsRunnable implements Runnable{
	private String name;
	
	public ImplementsRunnable(String name){
		this.name = name;
	}

	@Override
	public void run() {
		for (int i = 0; i < 5; i++) {  
            System.out.println(name + "运行  :  " + i);  
            try {  
                Thread.sleep((int) Math.random() * 10);  
            } catch (InterruptedException e) {  
                e.printStackTrace();  
            }  
        } 
	}
	
	
	public static void main(String[] args) {
		ImplementsRunnable a1 = new ImplementsRunnable("A");
		ImplementsRunnable a2 = new ImplementsRunnable("B");
		
		Thread t1 = new Thread(a1);
		Thread t2 = new Thread(a2);
		
		t1.start();
		t2.start();
		/*
		 * 说明：ImplementsRunnable类通过实现Runnable接口，使得该类有了多线程的特征。run()方法是多线程程序的一个约定。所有的多线程代码都在
		 * run()方法里面。Thread类实际上也是实现了Runnable接口的类。
		 * 在启动多线程的时候，需要先通过Thread类的构造方法Thread(Runnable target)构造出对象，然后调用Thread对象的start()方法来运行多线程代码；
		 * 实际上所有的多线程代码都是通过运行Thread类的start()方法来运行的。因此，不管是扩展Thread类还是实现Runnable接口来实现多线程，最终
		 * 还是通过Thread的对象的API来控制线程的，熟悉Thread类的API是进行多线程变成的基础。
		 * 
		 * 
		 * Thread和Runnable的区别：
		 * 如果一个类继承Thread，则不适合资源共享。但如果实现了Runnable接口的话，则很容易的实现资源共享。
		 * 实现Runnable接口比继承Thread类所具有的优势：
		 * 1）适合相同的程序代码的线程去处理同一个资源；
		 * 2）可以避免java中单继承的限制；
		 * 3）增加程序的健壮性，代码可以被多个线程共享，代码和数据独立；
		 * 4）线程池只能放入实现Runnable或callable类线程，不能直接放入继承Thread的类；
		 * 
		 * 提醒：main方法其实也是一个线程。在java中所有的线程都是同时启动的，至于什么时候，那个先执行，完全看谁先得到CPU的资源。
		 * 在java中，每次程序运行至少启动两个线程。一个是main线程，一个是垃圾手机线程。因为每当使用java命令执行一个类的时候，实际上都会启动一个
		 * JVM，每一个JVM实际就是在操作系统中启动了一个进程。
		 * 
		 * Thread
		 *   |
		 *   ↓
		 * 初始状态                               阻塞状态                                          结束
		 * 		  ↘                 ⑤     ↙                      ↖    ⑥                  ↗
		 *        ②↘	  ↙		  ③               ↖              ↗     ⑦
		 * 			可运行              ↔↔ ↔↔ ↔↔    运行中
		 * 				  ↖	             ④                   ↙         ↘ 
		 *  			①   ↖			         ↙⑨		   ↘ ⑧
		 * 					↖		    ↙			        ↘
		 * 					    锁池状态        ←   ← ←  ←   等待队列
		 * 									⑩
		 * 
		 * Thread→初始状态：new Thread;
		 * ②:t.start()
		 * ③:用完片时间Thread.yield()
		 * ④:OS调度选中
		 * ⑤：用户输入结束、sleep结束、join的线程结束
		 * ⑥：等待用户输入Thread.sleep()、t2.join()
		 * ⑦:run()结束、main()结束
		 * ⑧：o.wait
		 * ⑨:synchronized
		 * ⑩:o.notify()、o.notifyAll()、wait时间到
		 * ①：拿到对象的锁标记
		 * 
		 * 1.新建状态(new)：新创建了一个线程对象；
		 * 2.就绪状态：(Runnable):线程对象创建后，其他线程调用了该对象的start()方法。该状态的线程位于可运行线程池中，变的可运行，等待获取CPU的使用权；
		 * 3.运行状态(Running):就绪状态的线程获取了cpu，执行程序代码；
		 * 4.阻塞状态(Blocked):阻塞状态是线程因为某种原因放弃CPU使用权，暂时停止运行。直到线程进入就绪状态，才有机会转到运行状态。阻塞情况分为以下三种：
		 * 	4.1：等待阻塞：运行的线程执行wait()方法，JVM会把该线程放入等待池中。(wait会释放持有的锁)；
		 * 	4.2：同步阻塞：运行的线程在获取对象的同步锁时，若该同步锁被别的线程占用，则JVM会把该线程放入锁池中；
		 * 	4.3：其他阻塞：运行的线程执行sleep()或join()方法，或发出了I/O请求时，JVM会把该线程置为阻塞状态。当sleep()状态超时、join()等待线程终止
		 * 				或超时、或者I/O处理完毕时，线程重新转入就绪状态。(注意：sleep()是不会释放持有的锁)
		 * 5.死亡状态(Dead):线程执行完了或者因异常退出了run()方法，该线程结束生命周期。
		 * 
		 * 
		 * 线程调度：
		 * 1.线程调度优先级：java线程有优先级，优先级高的线程会获得较多的运行机会。
		 * java线程的优先级用整数表示，取值范围是1~10，Thread类有一下三个静态常量：
		 * static int MAX_PRIORITY:线程可以具有最高优先级，取值为10；
		 * static int MIN_PRIORITY:线程可以具有最低优先级，取值为1；
		 * static int NORM_PRIORITY:分配给线程的最低优先级，取值为5；
		 * Thread类的setPriority()和getPriority()方法分别用来设置和获取线程的优先级。
		 * 每个线程都有默认的优先级；主线程的默认优先级为NORM_PRIORITY。
		 * 线程的优先级有继承关系，比如A线程中创建了B线程，那么B将和A具有相同的优先级。
		 * JVM提供了十个线程优先级，但与常见的操作系统都不能很好的映射。如果希望程序能移植到各个操作系统中，应该仅仅使用Thread类有的三个静态常量作为优先级，这样能保证同样的优先级采用了同样的调度方式。
		 * 
		 * 2.线程睡眠：Thread.sleep(long millis)方法，使线程转到阻塞状态。millis参数设定睡眠时间，以毫秒为单位。当睡眠结束后，就转为就绪(Runnable)状态。sleep()平台移植性好。
		 * 
		 * 3.线程等待：Object类中的wait()方法，导致当前的线程等待，直到其他线程调用此对象的notify()方法或notifyAll()唤醒方法。这两个唤醒方法也是Object类中的方法，行为等价于调用wait(0)一样。
		 * 
		 * 4.线程让步：Thread.yield()方法，暂停当前正在执行的线程对象，把执行机会让给相同或者更高优先级的线程。
		 * 
		 * 5.线程加入：join()方法，等待其他线程终止。在当前线程中调用另一个线程的join()方法，则当前线程转入阻塞状态，直到另一个进程运行结束，当前线程再由阻塞转为就绪状态。
		 * 
		 * 6.线程唤醒：Object类中的notify()方法，唤醒在此对象监视器上等待的单个线程。如果所有线程都在此对象上等待，则会选择唤醒其中一个线程。选择是任意的，并在对实现做出决定时发生。
		 * 线程通过调用其中一个wait方法，在对象的监视器上等待。直到当前的线程放弃此对象上的锁定，才能继续执行被唤醒的线程。被唤醒的线程将以常规方式与在该对象
		 * 上主动同步的其他所有线程进行竞争；例如，唤醒的线程在作为锁定此对象的下一个线程方面没有可靠的特权或劣势。类似的方法还有notifyAll()，唤醒在此对象监视器上
		 * 等待的所有线程。注意：Thread类中suspend()方法和resume()两个方法在JDK1.5中已经废除，因为有死锁倾向。
		 * 
		 * 
		 * 常用函数说明：
		 * 1.sleep(long millis):在指定的毫秒数内让当前正在执行的线程休眠(暂停执行)。
		 * 
		 * 2.join():等待调用join()方法的线程死亡；可以有参数，参数为long类型，等待线程死亡的最大时间；0意味着永远等待；
		 * join()是Thread类的一个方法，启动线程后直接调用，即join()的作用是：等待该线程终止。这里需要解释的就是该线程是指的主线程等待子线程的终止。也就是在子线程调用了join()方法后面的代码，只有等到子线程结束了才能执行。
		 * 为什么用join()方法：在很多情况，主线程生成并启动了子线程，如果子线程里要进行大量耗时的运算主线程往往将于子线程之前结束，但是主线程处理完其他事务后，需要用到子线程的处理结果，也就是主线程需要等待子线程执行完成之后再结束，这个时候就要用到join()方法。
		 * 
		 * 3.yield():暂停当前正在执行的线程对象，并执行其他线程。
		 * yield()应该做的是让当前运行线程回到可运行状态，以允许具有相同优先级的其他线程获得运行机会。因此，使用yiled()的目的是让相同优先级的线程之间能适当的轮转执行。但是，实际中无法保证yield()达到目的，因为让步的线程还有可能被线程调度程序再次选中。
		 * 结论：yield()从未导致线程转到等待/睡眠/阻塞状态。在大多数情况下，yield()将导致线程从运行状态转到可运行状态，但有可能没有效果。
		 * 
		 * sleep()和yield()的区别：sleep()使当前线程进入停滞状态，所以执行sleep()的线程在指定的时间内肯定不会被执行；yield()只是使当前线程重新回到可执行状态，所以执行yield()的线程有可能在进入到可执行状态后马上又被执行；
		 * sleep()方法使当前运行中的线程睡眠一段时间，进入不可运行状态，这段时间的长短是由程序设定的，yield方法使当前线程让出CPU使用权，但让出
		 * 的时间是不可设定的。实际上，yield()方法对应了如下操作：先检测当前是否有相同优先级的线程处于同课运行状态，如有，则把CPU的占有权交给此线程，
		 * 否则，继续运行原来的线程。所以yield()方法称为“退让”，它把运行机会让给了同等优先级的其他线程。
		 * 另外，sleep方法允许较低优先级的线程获得运行机会，但yield()方法执行时，当前线程仍处于可运行状态，所以，不可能让较低优先级的线程在此时获得CPU占有权。
		 * 在一个运行系统中，如果较高优先级的线程没有调用sleep方法，又没有受到I\O阻塞，那么，较低优先级线程只能等待所有较高优先级的线程运行结束，才有机会运行。
		 * 
		 * 4.setPriority():更改线程的有线级别：MIN_PRIORITY = 1, NORM_PRIORITY = 5, MAX_PRIORITY = 10
		 * 
		 * 5.interrupt():不要以为他是中断某个线程！它只是线程发出的一个中断信号，让线程在无线等待时(如死锁时)能抛出，从而结束线程，但是如果你吃掉了这个异常，那么这个线程还是不会中断的！
		 * 
		 * 6.wait():Obj.wait()与Obj.notify()必须要与synchronized(Obj)一起使用，也就是wait与notify是针对已经获取了Obj锁进行操作，
		 * 从语法角度来说就是Obj.wait()、Obj.notify()必须在synchronized(Obj){...}语句块中使用。从功能上来说wait就是说线程在获取锁对象
		 * 后，主动释放锁对象，同时本线程休眠。直到有其他线程调用对象的notify唤醒该线程，才能继续获取对象锁，并继续执行。相应的notify()就是对对象
		 * 锁的唤醒操作。但有一点需要注意的是notify()调用后，并不是马上就释放对象锁，而是在相应的synchronized(){}语句块执行结束，自动释放锁后，
		 * jvm会在wait()对象锁的线程中随机选取一线程，赋予其对象锁，唤醒线程，继续执行。这样就提供了在线程间同步、唤醒的操作。Thread.sleep()
		 * 与Object.wait()二者都可以暂停当前线程，释放CPU的控制权，主要的区别在于Object.wait()在释放CPU同时，释放了对象锁的控制。
		 * 单单在概念上理解清除了还不够需要在实际例子中进行测试才能更好的理解。对Object.wait()、Object.notify()的应用最经典的例子，应该是
		 * 三线程打印ABC的问题，见UseWait类；
		 * 
		 * wait和sleep区别：
		 * 1.它们都是在多线程的环境下，都可以在程序的调用处阻塞指定的毫秒数，并返回。
		 * 2.wati()和sleep()都可以通过interrupt()方法打断线程的暂停状态，从而使线程立刻抛出InterruptedException.
		 * 		如果线程A希望立即结束线程B，则可以对线程B对应的Thread实例调用interrupt方法。如果此刻线程B正在wait/sleep/join，则线程B会立即抛出InterruptedException，在catch(){}中直接return即可安全的结束线程。
		 * 		需要注意的是InterruptedException是线程自己从内部抛出的，并不是interrupt()方法抛出的。对于某一线程调用interrupt()时，如果该线程正在执行普通的代码，那么该线程根本就
		 * 不会抛出InterruptedException。但是，一旦该线程进入到wait()、sleep()、join()后，就会立刻抛出InterruptedException。
		 * 
		 * 不同点：
		 * 1.Thread类的方法：sleep()、yield()等；Object的方法：wait()、notify()等；
		 * 2.每个对象都有一个锁来控制同步访问。Synchronized关键字可以和对象的锁交互，来实现线程的同步。sleep方法没有释放锁，而wait方法释放了锁，使得其他线程可以使用同步控制块或方法。
		 * 3.wait，notify和notifyAll只能在同步控制块或同步控制方法中使用，而sleep可以在任何地方使用；所以sleep()和wait()方法最大的区别是：sleep睡眠时，保持对象锁，仍然占有该锁；而wait睡眠时，释放对象锁。
		 * 但是wait和sleep都可以通过interrupt()方法打断线程的暂停状态，从而使线程立刻抛出InterruptedException（但不建议使用该方法）
		 * 
		 * sleep()方法：
		 * sleep()使当前线程进入停滞状态（阻塞当前线程），让出CPU的使用权、目的是不让当前线程独自霸占该进程所获得的CPU资源，以留一定时间给其他线程执行的机会；sleep()是Thread类的Static
		 * 方法；因此它不能改变对象的锁，所以当在一个Synchronized块中调用sleep()方法时，线程虽然休眠了，但是对象的锁并没有被释放，其他线程无法访问这个对象（即睡着也持有对象锁）。在sleep
		 * 休眠时间期满后，该线程不一定会立即执行，这是因为其他线程可能正在运行而且没有被调度而放弃执行，除非此线程具有更高的优先级。
		 * 
		 * wait()方法：
		 * wait()方法是Object类里面的方法；当一个线程执行到wait()方法时，他就进入到一个和该对象相关的等待池中，同时失去（释放）了对象的机锁(暂时失去机锁，wait(long timeout)超时
		 * 时间到后还需返还对象锁)；其它线程可以访问；wait()使用notify或者notifyAll或者指定睡眠时间来唤醒当前等待池中的线程。wait()必须放在Synchronized block中，否则会在
		 * program runtime时扔出java.lang.IllegalmonitorStateException异常。
		 * 
		 * 
		 * 
		 * 常见名词解释：
		 * 主线程：JVM调用程序main()所产生的线程。
		 * 当前线程：一般指Thread.currentThread()来获取的进程；
		 * 后台线程：指为其他线程服务的线程，也称为守护线程。JVM的垃圾回收线程就是一个后台线程。用户线程和后台线程的区别在于是否等待主线程依赖主线程结束而结束。
		 * 前台线程：是指接受后台线程服务的线程，其实前台后台线程是联系在一起的，就像傀儡和幕后操纵者一样的关系。傀儡是前台线程，幕后操纵者为后台线程。由前台线程创建的线程默认也是前台线程。可以通过isDeamon()和setDeamon()方法来判断和设置一个线程是否为后台线程。
		 * 
		 * 线程类的一些常用方法：
		 * sleep():强迫一个线程睡眠N毫秒；
		 * isAlive():判断一个线程是否存活；
		 * join():等待线程终止；
		 * activeCount():程序中活跃的线程数；
		 * enumerate():枚举程序中的线程；
		 * currentThread():得到当前线程；
		 * isDeamon():线程是否为守护线程；
		 * setDeamon():设置一个线程为守护线程(用户线程和守护线程的主要区别在于，是否等待主线程依赖于主线程结束而结束)
		 * setName():为线程设置一个名称；
		 * wait():强迫一个线程等待；
		 * notify():通知一个线程继续运行；
		 * setPriority():设置一个线程的优先级；
		 * 
		 * 
		 * 
		 * 
		 * 
		 */								
	}

}
