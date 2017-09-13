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
  public void executeMessage(Wang callback, String question){//这里传入的callback是CallBack接口的实现类Wang
	  
	  for(int i = 0; i < 1000000000; i++){
		  
	  }
	  
	  String result = "2";
	  callback.solve(result);
  }

	
}
