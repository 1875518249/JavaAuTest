package com.autest.testng;

import org.testng.annotations.Test;

public class IgnoreTest {

    @Test
    public void ignore1(){
        System.out.println("ignore1 运行！");
    }

    @Test(enabled = false)
    public void ignore2(){
        System.out.println("ignore2运行啦");
    }
}
