package crossTheThreshold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
/*
 * URLConnection的使用
 *URLConnection是Java自带的请求url的工具。下面将以爬取科学网的用户为例，讲解其使用。下面程序可复制下来，直接运行，并求注释已经很清晰明了。
*/
public class UseURLConnection {

    public static void main(String[] args) throws Exception {
        //使用URLConnection请求url，并返回html字符，这里使请求第一层数据，获取第二层请求的url
        String html = getRawHtml("http://blog.sciencenet.cn/blog.php?mod=member&type=%B9%DC%C0%ED%D7%DB%BA%CF");
        //使用Jsoup方式进行解析html
        Document document=Jsoup.parse(html);
        Elements elements=document.select("div[class=box_line]").get(0).select("li").select("a");
        for (Element ele: elements) {
            //第二层请求，为了爬取用户信息
            String html1 = getRawHtml(UrlUtil.UrlTrans(ele.attr("href")));
            //使用Jsoup方式进行解析html1
            Document document1=Jsoup.parse(html1);
            Elements elements2=document1.select("div[id=con_box]").select("p[class=potfont]").select("a");
            for (Element ele1: elements2) {
                //匹配字符串中的数字，获取id
                String idtest=ele1.attr("href");
                String regEx="[^0-9]";   
                Pattern p1 = Pattern.compile(regEx);   
                Matcher m1 = p1.matcher(idtest);  
                String id=m1.replaceAll("").trim();
                //获取用户名
                String name=ele1.text();
                System.out.println(id+"=="+name);
            }
        }
    }
    //URLConnection方法
    public static String  getRawHtml(String personalUrl) throws InterruptedException,IOException {
        //使用URLConnection请求数据
        URL url = new URL(personalUrl);
        URLConnection conn = url.openConnection();
        InputStream in=null;
        try {
            conn.setConnectTimeout(3000);
            in = conn.getInputStream();
        } catch (Exception e) {
        }
        //将获取的数据转化为String
        String html = convertStreamToString(in);
        return html;
    }
    //这个方法是将InputStream转化为String
    public static String convertStreamToString(InputStream is) throws IOException {
        if (is == null)
            return "";
        BufferedReader reader = new BufferedReader(new InputStreamReader(is,"gbk"));
        StringBuilder sb = new StringBuilder();
        String line = null;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        reader.close();
        return sb.toString();

    }
}
