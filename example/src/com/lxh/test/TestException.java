package com.lxh.test;

public class TestException {
	
	public static void main(String[] args) {
		
		Integer a = 0;
		if(a == 0){
			throw new ArithmeticException("a == 0");
		}else{
			Integer b = 10/a;
			System.out.println(b);
		}
		System.out.println("123");
	}

}
