package com.autest.testng;

import org.testng.annotations.Test;

public class DependTest {

    @Test
    public void test1(){
        System.out.println("test1 run1111");
        throw new RuntimeException();
    }

    //如果test2依赖于test1，就应该是test1执行后才执行test2也就是执行test2会自动先去执行test1，
    // 如果test1执行失败了test2就不执行直接忽略
    @Test(dependsOnMethods = {"test1"})
    public void test2(){
        System.out.println("test2 run222");
    }
}
