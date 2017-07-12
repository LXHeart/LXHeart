package com.lxh.model;
/**
 * 用来封装对象，我要抓取一本书的数据包括，书籍的id，书名及价格。ecliplse中生成set、get方法的快捷键是shift+alt+s然后选择生成setter、getter
 * @author Administrator
 *
 */
public class JdModel {
    private String bookID;
    private String bookName;
    private String bookPrice;
    public String getBookID() {
        return bookID;
    }
    public void setBookID(String bookID) {
        this.bookID = bookID;
    }
    public String getBookName() {
        return bookName;
    }
    public void setBookName(String bookName) {
        this.bookName = bookName;
    }
    public String getBookPrice() {
        return bookPrice;
    }
    public void setBookPrice(String bookPrice) {
        this.bookPrice = bookPrice;
    }
}