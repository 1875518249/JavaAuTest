package com.autest.code;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.testng.annotations.Test;

import java.io.IOException;

public class MyHttpClient {

    @Test
    public void test1() throws IOException {

        //用来存放我们的结果
        String result;
        //创建一个get方法实例
        HttpGet get = new HttpGet("http://www.baidu.com");

        //client实例用来执行get方法的
        HttpClient client = new DefaultHttpClient();
        HttpResponse response = client.execute(get);//执行请求方法
        //将响应全部内容转换成文本
       result = EntityUtils.toString(response.getEntity(),"utf-8");
        System.out.println(result);
    }

}
