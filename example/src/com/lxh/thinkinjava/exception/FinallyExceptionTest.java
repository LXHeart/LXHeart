package com.lxh.thinkinjava.exception;
/**
 * try catch finally 中最好不要使用return、continue和break跳转关键字；
 * @author Administrator
 *
 */
public class FinallyExceptionTest {
	
	@SuppressWarnings("finally")
	public static String f(){
		try {
            return "1";
       } catch (Exception e) {
            return "2";
       } finally {
            return "3";
       }
	}
	
	@SuppressWarnings("finally")
	public static void a() {
        try {
             throw new Exception();
        } catch (Exception e) {
			e.printStackTrace();
		} finally {
             return;
        }
   }
	
	public static void main(String[] args) {
		a();
		
		
	}

}
