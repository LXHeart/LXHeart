package com.lxh.study.ioStudy;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * 按字节流写文件
 * 
 * 	OutputStream：此抽象类是表示输出字节流的所有类的超类。输出流接受输出字节并将这些字节发送到某个接收器。需要定义OutputStream子类的应用程序必须始终提供至少一中可写入一个输出字节的方法；
 * 	void close()：关闭此输出流并释放与此流有关的所有系统资源；
 * 	void flush()：刷新此输出流并强制写出所有缓冲的输出字节；
 * 	void write(byte[] b)：将b.length个字节从指定的字节数组写入此输出流；
 * 	void write(byte[] b, int off, int len)：将指定字节数组中从偏移量off开始的len个字节写入此输出流；
 * 	abstract void write(int b)：将指定的字节写入此输出流；
 * 
 * 进行I/O操作时可能会产生I/O例外，属于非运行时例外，应该在程序中处理。
 * 如：FileNotFoundException,EOFException,IOException等等；
 * @author Administrator
 *
 */
public class Test2 {
	
	 /** 
     * @author linbingwen 
     * @since 2015年9月5日 
     * @param args 
     */  
    public static void main(String[] args) {  
        String input = "D:" + File.separator + "hello.jpg";  
        String output = "D:" + File.separator + "hello1.jpg";  
        writeFile(input,output);  
  
    }  
  
    /** 
     * 文件复制操作,可以是图片、文字 
     *  
     * @author linbingwen 
     * @since 2015年9月5日 
     * @param input 
     * @param output 
     */  
    public static void writeFile(String input, String output) {  
        FileInputStream fis = null;  
        FileOutputStream fos = null;  
        byte[] buffer = new byte[100];  
        int temp = 0;  
        try {  
            fis = new FileInputStream(input);  
            fos = new FileOutputStream(output);  
            while (true) {  
                temp = fis.read(buffer, 0, buffer.length);  
                if (temp == -1) {  
                    break;  
                }  
                fos.write(buffer, 0, temp);  
            }  
        } catch (Exception e) {  
            System.out.println(e);  
        } finally {  
            try {  
                fis.close();  
                fos.close();  
            } catch (Exception e2) {  
                System.out.println(e2);  
            }  
        }  
  
    }  
}
