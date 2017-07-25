package com.lxh.study.javaCallbackStudy;
/**
 * 这是小李
 * @author Administrator
 * @author LXHeart
 * @since 2017/07/18
 *
 */
public class Li {
  /**
   * 
   * @param callback
   * @param question
   */
  public void executeMessage(CallBack callback, String question){
	  
	  for(int i = 0; i < 10000; i++){
		  
	  }
	  
	  String result = "2";
	  
	  callback.solve(result);
  }

	
}
