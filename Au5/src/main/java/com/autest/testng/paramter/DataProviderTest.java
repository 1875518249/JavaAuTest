package com.autest.testng.paramter;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.lang.reflect.Method;

public class DataProviderTest {

    //通过dataprovider参数名字进行直接传递
    @Test(dataProvider = "data1")
    public void dataProTest(String name,int age){
        System.out.println("name = "+name+";age = "+age);
    }
    @DataProvider(name = "data1")
    public Object[][] dataProvide(){
        Object[][] o = new Object[][]{
                {"张三",23},
                {"李四",24}
        };
        return o;
    }

    //另外，dataprovider参数名一样如何让其多个不同的方法可以使用不同的参数？通过方法名传入

    @Test(dataProvider = "methodTest")
    public void test1(String tname,int tage){
        System.out.println("Test1!!!!1 name = "+tname+";age = " +tage);
    }
    @Test(dataProvider = "methodTest")
    public void test2(String tname,int tage){
        System.out.println("Test222222 name = "+tname+";age = " +tage);
    }
    @DataProvider(name = "methodTest")
    public Object[][] methodDataTest(Method method){ //通过方法名传递，这个Method必须是reflect里的。
        Object[][] re = null;      //给初值为null，后面不同的方法给不同的值
        if(method.getName().equals("test1")){
            re =new Object[][]{
                {"ZhangSan",33},
                {"LISi",34}
            };
        }else if(method.getName().equals("test2")){
            re = new Object[][]{
                    {"Wangwu",55},
                    {"ZheLiu",66}
            };
        }
        return re;
    }
}
