package com.lxh.study.ioStudy;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * 按字符写入：字符输出流
 * 
 * FileWrite的常用构造有一下几种。
 * 	FileWrite(String fileName):根据文件名创建FileWrite对象；
 * 	FileWrite(String fileName, boolean append):根据文件名创建FileWrite对象，append参数用来指定是否在原文件之后追加内容；
 * 	FileWrite(File file):根据File对象创建FileWrite对象；
 * 	FileWrite(File file, boolean apperd):根据File对象创建FileWrite对象，append参数用来指定是否在原文件之后追加内容；
 * 
 * FileWrite的常用方法包括一下几种；
 * void write(int c)：向文件中写入正整数c代表的单个字符；
 * void write(char[] cbuf)：向文件中写入字符数组cbuf；
 * void write(char[] cbuf, int off, int len)：向文件中写入字符数组从偏移位置off开始的len个字符；
 * void write(String str)：向文件中写入字符串str，注意此方法不会在写入完毕之后自动换行；
 * void write(String str, int off, int len)：向文件中写入字符串str的位置从off开始、长度为len的一部分子字符串；
 * Write append(char c)：向文件中追加单个字符c；
 * Write append(CharSequence cqs)：向文件中追加cqs代表的一个字符序列。CharSequence是从JDK1.4版本开始引入的一个接口，代表字符值的一个可读序列，此接口对许多不同种类的字符序列提供统一的只读访问；
 * Write append(CharSequence cqs, int start, int end)：向文件中追加csq字符序列的从位置start开始、end结束的一部分字符。
 * void flush()：刷新字符输出缓冲区；
 * void close()：关闭字符输出流；
 * 
 * 和BufferReader相对应，启用缓冲区的BufferWrite也拥有以下两种形式的构造方法；、
 * BufferWrite(Write out):根据out代表的Write对象创建BufferWrite实例，缓冲区大小采用默认值；
 * BufferWrite(Write out, int sz):根据out代表的Write对象创建BufferWrite实例，缓冲区大小采用指定的sz值；
 * 
 * 		我们知道，BufferReader类的readLine()方法能一次从输入流中读入一行，但对于BufferWriter类，却没有一次写一行的方法。若要向输出流中
 * 一次写一行，可用PrintWriter类(PrintWrite类也是Writer的直接子类)将原来的流改造成新的打印流，PrintWriter类有一个方法pringln(String)，
 * 能一次输出一行，即在待输出的字符串后自动补"\r\n";
 * @author Administrator
 *
 */
public class Test4 {
	/** 
     * @author linbingwen 
     * @since  2015年9月5日  
     * @param args     
     */  
    public static void main(String[] args) {  
        String path = "D:" + File.separator + "test4.txt";  
        String str= "Evankaka林炳文Evankaka林炳文Evankaka林炳文\r\n";  
        writeFile(path,str);  
        writeFile(path,str);  
        writeFile(path,str);  
    }  
      
    /** 
     * 利用字符流写入文件 
     * @author linbingwen 
     * @since  2015年9月5日  
     * @param path 
     * @param content 
     */  
    public static void writeFile(String path,String content){  
        //由于IO操作会抛出异常，因此在try语句块的外部定义FileWriter的引用  
        FileWriter w = null;  
        try {  
            //以path为路径创建一个新的FileWriter对象  
            //如果需要追加数据，而不是覆盖，则使用FileWriter（path，true）构造方法  
            //w = new FileWriter(path,true);        
            w = new FileWriter(path,true);             
            //将字符串写入到流中，\r\n表示换行  
            w.write(content);  
            //如果想马上看到写入效果，则需要调用w.flush()方法  
            w.flush();  
        } catch (IOException e) {  
            e.printStackTrace();  
        } finally {  
            //如果前面发生异常，那么是无法产生w对象的  
            //因此要做出判断，以免发生空指针异常  
            if(w != null) {  
                try {  
                    //关闭流资源，需要再次捕捉异常  
                    w.close();  
                } catch (IOException e) {  
                    e.printStackTrace();  
                }  
            }  
        }  
    }  
	

}
