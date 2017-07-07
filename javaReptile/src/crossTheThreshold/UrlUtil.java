package crossTheThreshold;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/*
 * URLConnection的使用
 * 本文以科学网为例讲解URLConnection的使用。如下图所示，为我们要爬取的第一个页面。即第一层。 
 * 下图为我们要爬取的第二个页面，也是我们真正想要爬取的页面。即用户id及用户名。即第二层。
 * 
 * 在爬取第二层的入口地址时，我们发现第一层获取的url中含有中文字符，所以要对其进行转码，获取可供请求的url。 
 * 下面提供本人自己写了一个，针对此网站的url转码方法，这个方法不对其他页面适用，如有其他页面也存在该问题，可在此基础上进行修改。
 */
public class UrlUtil {
    private final static String ENCODE = "GBK"; 
    public static String getURLEncoderString(String str) {
        String result = "";
        if (null == str) {
            return "";
        }
        try {
            result = java.net.URLEncoder.encode(str, ENCODE);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static void main(String[] args) throws UnsupportedEncodingException {
        String str = "blog.php?mod=member&type=管理科学和管理思想史&realmmedium=管理科学与工程&realm=管理综合&catid=565";
        UrlTrans(str);

    }
    public static String UrlTrans(String str) {
        //中文正则匹配，将中文进行转码，其他字符不变
        Pattern p = Pattern.compile("([\u4e00-\u9fa5]+)");    
        Matcher m = p.matcher( str );    
        String mv = null;  
        List<String> list=new ArrayList<String>();
        while (m.find()) {    
            mv = m.group(0);    
            list.add(getURLEncoderString(mv));    
        } 
        //找出id，即565
        String regEx="[^0-9]";   
        Pattern p1 = Pattern.compile(regEx);   
        Matcher m1 = p1.matcher(str);   
        String url=" http://blog.sciencenet.cn/blog.php?mod=member&type="+list.get(0)+"&realmmedium="+list.get(1)+"&realm="+list.get(2)+"&catid="+m1.replaceAll("").trim();
        System.out.println(url);
        return url;
    }
}