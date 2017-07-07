package crossTheThreshold;

import java.io.IOException;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.HttpVersion;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicHttpResponse;
import org.apache.http.util.EntityUtils;

//网络爬虫之httpclient的使用
public class HttpClientTest {
    public static void main(String[] args) throws IOException {
        HttpClient client = HttpClientBuilder.create().build();//new DefaultHttpClient();   //初始化httpclient
        String personalUrl="http://www.autohome.com.cn/beijing/";     //指定一个种子url
        HttpGet postMethod = new HttpGet(personalUrl);   //post方法请求
        System.out.println("postMethod====="+ postMethod+"=====");
        HttpGet getMethod = new HttpGet(personalUrl);       //  get方法请求
        System.out.println("Method======"+getMethod+"=====");
        HttpResponse response = new BasicHttpResponse(HttpVersion.HTTP_1_1,
                HttpStatus.SC_OK, "OK");                        //初始化response        
        response = client.execute(getMethod);                   //执行响应
        System.out.println("response========"+response+"=====");
        int StatusCode = response.getStatusLine().getStatusCode(); //获取响应状态码
        System.out.println("StatusCode===="+StatusCode+"=====");
        System.out.println(response);
        if(StatusCode == 200){                          //状态码200表示响应成功
            //获取实体内容
            String entity = EntityUtils.toString (response.getEntity(),"utf-8");
            //输出实体内容
            System.out.println(entity);
            EntityUtils.consume(response.getEntity());       //消耗实体
        }else {
            //关闭HttpEntity的流实体
            EntityUtils.consume(response.getEntity());        //消耗实体
        }
    }

}
