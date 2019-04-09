package com.autest.code.cookies;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;

import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;

import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;

import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
/*        *
         * DefaultHttpClient 用 CloseableHttpClient

         HttpResponse 用 CloseableHttpResponse

         官方新api的样例

         Get方法：
         CloseableHttpClient httpclient = HttpClients.createDefault();
         HttpGet httpGet = new HttpGet("http://targethost/homepage");
         CloseableHttpResponse response1 = httpclient.execute(httpGet);
         // The underlying HTTP connection is still held by the response object
         // to allow the response content to be streamed directly from the network socket.
         // In order to ensure correct deallocation of system resources
         // the user MUST either fully consume the response content  or abort request
         // execution by calling CloseableHttpResponse#close().

         try {
         System.out.println(response1.getStatusLine());
         HttpEntity entity1 = response1.getEntity();
         // do something useful with the response body
         // and ensure it is fully consumed
         EntityUtils.consume(entity1);
         } finally {
         response1.close();
         }


         Post方法：

         HttpPost httpPost = new HttpPost("http://targethost/login");
         //拼接参数
         List <NameValuePair> nvps = new ArrayList <NameValuePair>();
         nvps.add(new BasicNameValuePair("username", "vip"));
         nvps.add(new BasicNameValuePair("password", "secret"));
         httpPost.setEntity(new UrlEncodedFormEntity(nvps));

         CloseableHttpResponse response2 = httpclient.execute(httpPost);

         try {
         System.out.println(response2.getStatusLine());
         HttpEntity entity2 = response2.getEntity();
         // do something useful with the response body
         // and ensure it is fully consumed
         //消耗掉response
         EntityUtils.consume(entity2);
         } finally {
         response2.close();
         }
         *//**/
public class MyCookiesForGet {

    private String url;
    private ResourceBundle bundle;
    private CookieStore cookieStore;

    @BeforeTest
    public void beforeGetTest() {
        //从resources中读取属性文件
        bundle = ResourceBundle.getBundle("application", Locale.CHINA);
        url = bundle.getString("test_url");
        System.out.println(url);
    }

    @Test
    public void testGetCookies() throws IOException {
        String result;
        String uri;

//        CloseableHttpClient httpClient = getSingleSSLConnection();


        uri = bundle.getString("getCookies.uri");
        HttpGet get = new HttpGet(this.url+uri);

        //
//      CloseableHttpClient client = HttpClients.createDefault();

       DefaultHttpClient client1 =new DefaultHttpClient();
        //HttpClientBuilder.create().build();
        HttpResponse response =client1.execute(get);

        result = EntityUtils.toString(response.getEntity(),"UTF-8");
        System.out.println(result);

        //新如何获取cookie，不用DefaultHttpClient
        cookieStore = client1.getCookieStore();
//        CookieStore cookieStore = new BasicCookieStore();
        List<Cookie> cookies= cookieStore.getCookies();
        for(Cookie cookie:cookies){
            String name = cookie.getName();
            String value = cookie.getValue();
            System.out.println("cookie name = "+name+"; cookie value = "+value);
        }
    }

    @Test(dependsOnMethods = {"testGetCookies"})
    public void getWithCookiesTest() throws IOException {

        String result;
        String uri = bundle.getString("test_getWithCookies");
        String testurl = url+uri;
        System.out.println(testurl);
        HttpGet get = new HttpGet(testurl);
        DefaultHttpClient client2 = new DefaultHttpClient();

        //设置cookie信息
        client2.setCookieStore(this.cookieStore);

        HttpResponse response = client2.execute(get);

        int statusCode = response.getStatusLine().getStatusCode();
        System.out.println(statusCode);
        if (statusCode == 200){
            result = EntityUtils.toString(response.getEntity(),"UTF-8");
            System.out.println(result);
        }else{
            System.out.println("获取get/with/cookies失败");
        }

    }
}
