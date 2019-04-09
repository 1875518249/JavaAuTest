package com.autest.testng.suite;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

//所有测试套件的一个配置
//主要是套件运行之前，所需要运行的共有的方法，等
public class SuiteConfig {

    @BeforeSuite
    public void beforeSuite(){
        System.out.println("beforeSuite运行了！");
    }
    @AfterSuite
    public void afterSuite(){
        System.out.println("afterSuite运行啦.");
    }

    @BeforeTest
    public void beforeTest(){
        System.out.println(
                "beforetest运行了"
        );
    }
    @AfterTest
    public void afterTest(){
        System.out.println("afterTest运行啦");
    }
}
