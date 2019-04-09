package com.tester.demo;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

public class ReportTest {

    @Test
    public void test1(){
        Assert.assertEquals(1,1);
    }
    @Test
    public void test2(){
        Assert.assertEquals(1,2);
    }

    @Test
    public void test3(){
        Assert.assertEquals("abc","abc");
    }

    @Test
    public void logDemo(){
        Reporter.log("我自己写的日志");
        throw new RuntimeException("我自己写的异常");
    }

}
