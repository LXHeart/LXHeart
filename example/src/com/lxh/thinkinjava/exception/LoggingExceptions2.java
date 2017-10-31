package com.lxh.thinkinjava.exception;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.logging.Logger;
/**
 * 异常与日志记录
 * 
 * 捕获和记录其他人编写的异常，因此我们必须在异常处理程序中生成日志消息（也就是在catch中）
 * @author Administrator
 *
 */
public class LoggingExceptions2 {
	private static Logger logger  = Logger.getLogger("LoggingException2");
	static void logException(Exception e){
		StringWriter trace = new StringWriter();
		e.printStackTrace(new PrintWriter(trace));
		logger.severe(trace.toString());
	}
	public static void main(String[] args) {
		try {
			throw new NullPointerException();
		} catch (NullPointerException e) {
//			e.printStackTrace();
			logException(e);
		}
	}

}
