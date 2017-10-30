package com.lxh.thinkinjava.exception;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.logging.Logger;
/**
 * 异常与记录日志
 * @author Administrator
 *
 */
class LoggingException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Logger logger  = Logger.getLogger("LoggingException");
	public LoggingException(){
		StringWriter trace = new StringWriter();
		printStackTrace(new PrintWriter(trace));
		logger.severe(trace.toString());
	}
}

public final class LoggingExceptions {
	public static void main(String[] args) {
		try {
			throw new LoggingException();
		} catch (LoggingException e) {
//			e.printStackTrace();
//			System.err.println("Caught:" + e);
		}
		try {
			throw new LoggingException();
		} catch (LoggingException e) {
//			e.printStackTrace();
//			System.err.println("Caught:" + e);
		}
	}

}
