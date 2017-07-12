package com.lxh.study.ioStudy;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
/**
 * 按字节流读文件：
 * 	InputStream：此抽象类是表述字节输入流的所有超类。需要定义InputStream的子类的应用程序必须时钟提供返回下一个输入字节的方法。
 * 	int available()：返回此输入流方法的下一个调用可以不受阻塞地从此输入流读取（或跳过）的字节数；
 * 	void close()：关闭此输入流并释放与该流关联的所有系统资源；
 * 	void mark(int readlimit)：在此输入流中标记当前的位置
 * 	boolean markSupported()：测试此输入流是否支持mark和reset方法；
 * 	abstract int read()：从输入流读取下一个数据字节；
 * 	int read(byte[] b)：从输入流中读取一定数量的字节并将其存储在缓冲区数组b中；
 * 	int read(byte[] b, int off, int len)：将输入流中最多len个数据字节读入字节数组；
 * 	void reset()：将此流重新定位到对此输入流最后调用mark方法时的位置；
 * 	long skip(long n)：跳过和放弃此输入流中的n个数据字节；
 * 
 * 注：对于中文字符，会出现乱码，中文字符要用字符流读取；
 * 
 * @author Administrator
 *
 */
public class Test1{
	/*
	 * 设置读取文件的路径
	 */
	private static String PATH = "D:" + File.separator + "test1.txt";  

	/** 
     * @author linbingwen 
     * @since 2015年9月5日 
     * @param args 
     * @throws IOException 
     */  
    public static void main(String[] args) {  
    	
    	//File.separator：Windows环境下为"/"，在mac环境下为"\"，提高了代码的兼容性
        String path = "D:" + File.separator + "test1.txt";  
        readFile1(path);  
        readFile2(path);  
//      Test1 t = new Test1();
//      t.start();
    	
  
    }  
    
    
      
    /** 
     * 字节流读取文件：单个字符读取 
     * @author linbingwen 
     * @since  2015年9月5日  
     * @param path 
     */  
    public static void readFile1(String path) {  
        FileInputStream is = null;  
        try {  
            is = new FileInputStream(path);  
            System.out.println("===============================单个字符读取begin===============================");  
            int ch = 0;  
            while ((ch = is.read()) != -1) {  
                System.out.print((char) ch);  
            }  
            System.out.println();  
            System.out.println("===============================单个字符读取end===============================");        
        } catch (IOException e) {  
            e.printStackTrace();  
        } finally {  
            // 关闭输入流  
            if (is != null) {  
                try {  
                    is.close();  
                } catch (IOException e) {  
                    e.printStackTrace();  
                }  
            }  
        }  
    }  
    /** 
     * 字节流读取文件：数组循环读取 
     * @author linbingwen 
     * @since  2015年9月5日  
     * @param path 
     */  
    public static void readFile2(String path) {  
        FileInputStream is = null;  
        try {  
            // 创建文件输入流对象  
            is = new FileInputStream(path);  
            // 设定读取的字节数  
            int n = 512;  
            byte buffer[] = new byte[n];  
            // 读取输入流  
            System.out.println("===============================数组循环读取begin===============================");  
            while ((is.read(buffer, 0, n) != -1) && (n > 0)) {  
                System.out.print(new String(buffer));  
            }  
            System.out.println();  
            System.out.println("===============================数组循环读取end===============================");  
        } catch (IOException ioe) {  
            System.out.println(ioe);  
        } catch (Exception e) {  
            System.out.println(e);  
        } finally {  
            // 关闭输入流  
            if (is != null) {  
                try {  
                    is.close();  
                } catch (IOException e) {  
                    e.printStackTrace();  
                }  
            }  
        }  
    }  
	
	
	
}
