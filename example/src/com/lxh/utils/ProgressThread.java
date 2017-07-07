package com.lxh.utils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public class ProgressThread extends Thread {
	private static final Log log = LogFactory.getLog(ProgressThread.class);
	public static long speed = 0;
	@Override
	public void run() {
		while(true){
			log.info("..................tempSize:"+FtpUtil17_a.tempSize + "..............totalSize : "+FtpUtil17_a.totalSize);
			long a = FtpUtil17_a.tempSize*100/FtpUtil17_a.totalSize;
			speed = a;
			if(a == 0){
				a = 1;
			}
			if(a==100){
				ProgressThread.speed = 0L;
				FtpUtil17_a.tempSize = 0L;
				FtpUtil17_a.totalSize = 1L;
				break;
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
}
