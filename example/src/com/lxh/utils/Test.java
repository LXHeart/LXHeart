package com.lxh.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Test {

	public static void main(String[] args){
//		GetThumbnailImage.thumbnailImageT(new File("C:\\Users\\Administrator.SC-201706091102\\Pictures\\Saved Pictures\\1496985225332.jpg"), 200, 200, "thumb_", true);
		
		File file = new File("C:\\Users\\Administrator.SC-201706091102\\Pictures\\Saved Pictures\\1496985225332.jpg");
		
		try {
			FileReader fr = new FileReader(file);
			FileWriter fw = new FileWriter("C:\\Users\\Administrator.SC-201706091102\\Pictures\\Saved Pictures\\11496985225332.jpg");
			byte[] b = new byte[1024];
			int length = 0;
			long filesize = file.getsize
			while((length = fr.read())>0){
				
				fw.write(b, 0, length);
			}
			
			
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
