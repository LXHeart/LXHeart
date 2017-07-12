package com.lxh.main;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;

import com.lxh.db.MYSQLControl;
import com.lxh.model.JdModel;
import com.lxh.util.URLFecter;
/*   
 * 
 */
public class JdongMain {
    //log4j����ʹ�ã�������뿴֮ǰд������
    static final Log logger = LogFactory.getLog(JdongMain.class);
    public static void main(String[] args) throws Exception {
        //��ʼ��һ��httpclient
        HttpClient client = new DefaultHttpClient();
        //����Ҫ��ȡ��һ����ַ��������Դ����ݿ��г�ȡ���ݣ�Ȼ������ѭ����������ȡһ��URL����
        String url="http://search.jd.com/Search?keyword=java&enc=utf-8&wq=java&pvid=75e6aab07f564ec490db7cd377798d34";
        //ץȡ������      http://search.jd.com/Search?keyword=java&enc=utf-8&qrst=1&rt=1&stop=1&vt=2&wq=java&page=3&s=61&click=0
        List<JdModel> bookdatas=URLFecter.URLParser(client, url);
        //ѭ�����ץȡ������
        for (JdModel jd:bookdatas) {
            logger.info("bookID:"+jd.getBookID()+"\t"+"bookPrice:"+jd.getBookPrice()+"\t"+"bookName:"+jd.getBookName());
        }
        //��ץȡ�����ݲ������ݿ�
        MYSQLControl.executeInsert(bookdatas);
    }
}
