package com.lxh.study.javaCallbackStudy;
/**
 * 这是小王
 * @author LXHeart
 * 实现了一个回调接口CallBack
 *
 */
public class Wang implements CallBack{
	
	private Li li;
	
	public Wang (Li li){
		this.li = li;
	}
	
	public void askQuestion(final String question){
		new Thread(new Runnable(){

			@Override
			public void run() {
			  li.executeMessage(Wang.this, question);
			}
			
		}).start();
		
		play();
	}
	
	public void play(){
		System.out.println("i'm going to play!");
	}

	@Override
	public void solve(String result) {
      System.out.println("Xiao Li told Xiao Wang that the answer was " + result);		
	}

}
