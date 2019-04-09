package com.autest.code.cookies;


import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class MyCookiesForPost {
    private String url;
    private ResourceBundle bundle;
    private CookieStore cookieStore;

    @BeforeTest
    public void beforeGetTest() {
        //从resources中读取属性文件
        bundle = ResourceBundle.getBundle("application", Locale.CHINA);
        url = bundle.getString("test_url");
//        System.out.println(url);
    }

    @Test
    public void testGetCookies() throws IOException {
        String result;
        String uri;

//        CloseableHttpClient httpClient = getSingleSSLConnection();


        uri = bundle.getString("getCookies.uri");
        HttpGet get = new HttpGet(this.url + uri);

        //
//      CloseableHttpClient client = HttpClients.createDefault();

        DefaultHttpClient client1 = new DefaultHttpClient();
        //HttpClientBuilder.create().build();
        HttpResponse response = client1.execute(get);

        result = EntityUtils.toString(response.getEntity(), "UTF-8");
        System.out.println(result);

        //新如何获取cookie，不用DefaultHttpClient
        cookieStore = client1.getCookieStore();
        //        CookieStore cookieStore = new BasicCookieStore();
        List<Cookie> cookies = cookieStore.getCookies();
        for (Cookie cookie : cookies) {
            String name = cookie.getName();
            String value = cookie.getValue();
            System.out.println("cookie name = " + name + "; cookie value = " + value);
        }
    }
    @Test(dependsOnMethods = {"testGetCookies"})
    public void postWithCookiesTest() throws IOException {
        //拼接请求地址
        String uri = bundle.getString("test_postWithCookies");
        String test_url = url+uri;

        //声明一个Client对象，用来进行方法的执行
        DefaultHttpClient client2 =new DefaultHttpClient();
        //声明一个方法，这里就是post方法
        HttpPost post = new HttpPost(test_url);
        //添加post参数
        JSONObject param = new JSONObject();
        param.put("name","Yuefei");
        param.put("age","18");
        //设置请求头信息，添加header（统一方式添加setHeader）
        post.setHeader("Content-Type","application/json");
        //将参数信息添加到方法中，添加请求参数
        StringEntity entity = new StringEntity(param.toString(),"utf-8");
        post.setEntity(entity);
        //声明一个变量接收响应的结果
        String result ;
        //设置cookies信息
        client2.setCookieStore(this.cookieStore);
        //执行post方法
        HttpResponse response = client2.execute(post);
        //获取响应结果
        result = EntityUtils.toString(response.getEntity());
        System.out.println("response: "+result);


        //处理结果，判断结果是否符合预期
        //将响应结果字符串转化成json对象
        JSONObject resultjson = new JSONObject(result);

        //获取到结果值
        String success = (String) resultjson.get("Yuefei"); //直接get的是json对象格式需要强转String类型
        String code =(String) resultjson.get("code");//json中每个key和value都是字符串才行
        //具体判断返回结果
        Assert.assertEquals("Success",success);
        Assert.assertEquals("1",code);

        //返回结果中value值带有整数等类型如何处理？
        //返回结果中带有（动态数组）列表又该如何定位处理？
    }
}

