package com.lxh.study.ioStudy;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
/**
 * 文件复制
 * @author Administrator
 *
 */
public class Test5 {

	public static void main(String[] args) {
		File file = new File("D:\\a.mp4");
		File file1 = new File("D:\\b.mp4");
		FileInputStream fs = null;
		FileOutputStream fos = null;
		try {
			fs = new FileInputStream(file);
			fos = new FileOutputStream(file1);
			BufferedOutputStream bos = new BufferedOutputStream(fos);
			BufferedInputStream bis = new BufferedInputStream(fs);
			byte[] b = new byte[1024];
			int temp = 0;
			while((temp = bis.read(b)) != -1){
				bos.write(b, 0, temp);
				bos.flush();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				fs.close();
				fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	
}
