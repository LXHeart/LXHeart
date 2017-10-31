package com.lxh.thinkinjava.exception;

class SimpleException extends Exception {}

public class InheritingExceptions{
	public void f()throws SimpleException{
		System.out.println("Throw SimpleException from f()");
		throw new SimpleException();
	}
	public static void main(String[] args) throws Exception {
		InheritingExceptions sed = new InheritingExceptions();
		try {
			sed.f();
		} catch (Exception e) {
			System.out.println("Caught it!");
			throw e;
		}
		System.out.println("123");
	}
}
