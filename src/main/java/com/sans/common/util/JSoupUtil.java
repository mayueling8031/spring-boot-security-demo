package com.sans.common.util;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

public class JSoupUtil {
    /**
     * Jspup工具类
     * url:采集的URL
     * domian: 采集的域名
     *
     * @author java00123.com
     */
    public static Document getDocument(String url, String domain) {
        int error_count = 0;
        Document doc = null;
        while (true) {
            if (error_count > 10) {
                break;
            }
            try {
                doc = Jsoup
                        .connect(url)
                        .timeout(6000)
                        .header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8")
                        .header("Accept-Encoding", "gzip,deflate,sdch")
                        .header("Connection", "keep-alive")
                        .header("referer", domain)
                        .header("cookie", "data")
                        .followRedirects(true)
                        .userAgent("Mozilla/5.0 Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/42.0.2311.152 Safari/537.36")
                        .get();
            } catch (Exception e) {
                error_count++;
            }
            if (doc != null) {
                break;
            }
        }
        return doc;
    }

    public static Document parseHtml(String html) {
        return Jsoup.parse(html);
    }

    /**
     * 获取网站图片url地址集合
     *
     * @param url
     * @return
     */
    public static List<String> getUrl(String url) {
        List<String> urlList = new ArrayList<String>();
        Document document = getDocument(url, url);
        Elements elements = document.select("a");
        for (Element element : elements) {
            String string = element.attr("href");
            if (!string.equals(url)) {
                if (string.substring(0, 22).equals(url)) {
                    String str = string.substring(22);
                    Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
                    boolean flag = pattern.matcher(str).matches();
                    if (flag) {
                        urlList.add(string);
                    }
                }
            }
        }
        return urlList;
    }


    public static void main(String[] args) {
        List<String> urlList = new ArrayList<String>();
        Document document = getDocument("https://www.mzitu.com/japan/", "https://www.mzitu.com/japan/");
        Elements elements = document.select("a");
        for (Element element : elements) {
            String string = element.attr("href");
            if (!string.equals("https://www.mzitu.com/")) {
                if (string.substring(0, 22).equals("https://www.mzitu.com/")) {
                    String str = string.substring(22);
                    Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
                    boolean flag = pattern.matcher(str).matches();
                    if (flag) {
                        urlList.add(string);
                    }
                }
            }
        }
        for (int k = 0; k < urlList.size(); k++) {
            Document documentUrl = getDocument(urlList.get(k), "https://www.mzitu.com/japan/");
            //找到表示总共多少页的 <a> 标签
            Elements el = documentUrl.select(".pagenavi a");
            Integer span = Integer.valueOf(el.eq(el.size() - 2).select("span").text());
            System.out.println(span);
            for (Integer i = 1; i <= span; i++) {
                String url02 = urlList.get(k) + "/" + i;//具体每张图对应的页面
                Document document02 = JSoupUtil.getDocument(url02, urlList.get(k));
                //找到这个图片列表对应的位置
                String src = null;
                Elements elements02 = document02.select(".main-image p a img");
                for (Element element02 : elements02) {
                    //每一个a标签就对应一张图,然后拿到里面的href属性的值
                    src = element02.attr("src");
                    System.out.println(src);
                }
                //使用这个地址下载图片
                //1.使用Java代码模拟出一个客户端
                CloseableHttpClient httpClient = HttpClients.createDefault();
                //2.创建一个get请求
                HttpGet httpGet = new HttpGet(src);
                //3.使用客户端执行请求,获取响应
                CloseableHttpResponse httpResponse;
                try {
                    httpResponse = httpClient.execute(httpGet);
                    //4.获取响应体
                    HttpEntity entity = httpResponse.getEntity();
                    //5.获取响应体的内容
                    InputStream is = entity.getContent();
                    //创建一个字节输出流，将图片输出到硬盘中"D/aa"目录
                    //解析src获取图片的后缀名
                    //int i1 = src.lastIndexOf(".");//得到的是最后一个 . 的索引,然后用substring来根据索引切割
                    String sub = src.substring(src.lastIndexOf("."));
                    //创建一个随时间毫秒值变化的的文件名
                    Date date = new Date();
                    SimpleDateFormat sdf = new SimpleDateFormat("YYYYMMddHHmmssSSS");
                    String imgName = sdf.format(date) + sub;
                    FileOutputStream out = new FileOutputStream("D:/img/" + imgName);//具体放在哪个地方可以由你自己确定,但是记得要这个文件夹一定要存在,否则会报错
                    //将输入流中的内容拷贝到输出流
                    IOUtils.copy(is, out);
                    //关流
                    out.close();
                    is.close();
                    System.out.println("下载ing.......");

                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println("本次下载完成了,快去打开吧...");
            }
        }
    }
}
