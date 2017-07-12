package com.lxh.study.ioStudy;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 字符流读取操作
 * 
 * 		java采用16位Unicode来表示字符串和字符，对应的数据流就称为字符流。Reader和Writer为字符流设计。FileReader是InputStreamReader
 * 的子类，而InputStreamReader是Reader的子类；FileWrite是OutPutStreamWrite的子类，而OutPutStreamWrite则是Writer的子类。
 * 字符流和字节流的区别在于，字符流操作的对象是字符及字符数组，而字节流操作的对象则是字节及字节数组。
 * 
 * 字符输入流：
 * FileReader常用构造包括一下几种；
 * FileReader(String fileName)：根据文件名创建FileReader对象；
 * FileReader(File file): 根据File对象创建FileReader对象；
 * 
 * 
 * FileReader常用方法：
 * int read(): 读取单个字符。返回字符的整数值，如果已经到达文件尾，则返回-1；
 * int read(char[] cbuf): 将字符读入cbuf字符数组。返回读取到的字符数，如果已经到达文件尾，则返回-1；
 * int read(char[] cbuf, int off, int len): 将读取到的字符放到cbuf字符数组从off标识的偏移位置开始，最多读取len个字符；
 * 
 * 与字节流不同BufferReader是Reader的直接子类，这一点和BufferInputStream是InputStream的二级子类有所不同。通过BufferReader.readLine()
 * 方法可以实现读取文本行、返回字符串，因为我们平时读取的文本文件大多是断行的，而且该方法能直接返回字符串，因此BufferReader使用比FileReader更为广泛；
 * 
 * BufferReader有以下两种构造方法；
 * BufferReader(Reader in): 根据in代表的Reader对象创建BufferReader对象，缓冲区大小采用默认值；
 * BufferReader(Reader in, in sz):根据in代表的Reader对象创建BufferReader实例，缓冲区大小采用定值sz值；
 * 
 * BufferReader.readLine()方法遇到一下字符或者字符串认为当前行结束：'\n'(换行符)，'\r'(回车符)，'\r\n'(回车换行)，返回值为该行内容的字符串，
 * 不包含任何行终止符，如果已到达流末尾，则返回null；
 * 
 * 
 * @author Administrator
 *
 */
public class Test3 {
	
	public static void main(String[] args) {
		String path = "D:" + File.separator + "test3.txt";  
        readFile1(path);  
        readFile2(path);  
        readFile3(path,"utf-8");  
	}
	
	
	/**
	 * 字符流读取文件方法一
	 * @author lxh
	 * @since 2017/07/12
	 * @param path
	 */
	public static void readFile1(String path){
		FileReader r = null;
		try {
			r = new FileReader(path);
			//读入到字符数组的优化
			//由于有时候文件太大，无法确定需要定义数组的大小
			//因此一般定义数组长度为1024，采用循环的方式读入
			char[] buf = new char[1024];
			int temp = 0;
			System.out.println("=================字符流读取文件方法=====================");
			while((temp = r.read(buf)) != -1){
				System.out.println(new String(buf, 0, temp));
			}
			System.out.println();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(r != null){
				try {
					r.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
		}
	}
	
	
	/**
	 * 字符流读取方法二
	 * @author lxheart
	 * @since 2017/07/12
	 * @param path
	 * @return
	 */
	public static String readFile2(String path){
		File file = new File(path);
		StringBuffer sb = new StringBuffer();
		if(file.isFile()){
			BufferedReader bufferReader = null;
			FileReader fileReader = null;
			try {
				fileReader = new FileReader(file);
				bufferReader = new BufferedReader(fileReader);
				String line = bufferReader.readLine();
				System.out.println("============字符流读取方法二=====================");
				while(line != null){
					System.out.println(line);
					sb.append(line + "\r\n");
					line = bufferReader.readLine();
				}
				
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					fileReader.close();
					bufferReader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return sb.toString();
	}
	
	
	
	/**
	 * 字符流读取文件：可以指定文件编码格式
	 * @author lxh
	 * @since 2017/07/12
	 * @param path
	 * @param charset
	 * @return 
	 */
	public static String readFile3(String path, String charset){
		File file = new File(path);  
        StringBuffer sb = new StringBuffer();  
        if (file.isFile()) {  
            BufferedReader bufferedReader = null;  
            InputStreamReader inputStreamReader = null;  
            try {  
                inputStreamReader = new InputStreamReader(new FileInputStream(file), charset);  
                bufferedReader = new BufferedReader(inputStreamReader);  
                String line = bufferedReader.readLine();  
                System.out.println("========================== 字符流读取文件方法三==========================");  
                while (line != null) {  
                    System.out.println(line);  
                    sb.append(line + "\r\n");  
                    line = bufferedReader.readLine();  
                }  
            } catch (FileNotFoundException e) {  
                e.printStackTrace();  
            } catch (IOException e) {  
                e.printStackTrace();  
            } finally {  
                try {  
                    inputStreamReader.close();  
                    bufferedReader.close();  
                } catch (IOException e) {  
                    e.printStackTrace();  
                }  
            }  
  
        }  
        return sb.toString(); 
	}

}
