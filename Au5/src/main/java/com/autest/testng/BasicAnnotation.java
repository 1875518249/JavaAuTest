package com.autest.testng;

//添加git追踪左侧文件就变成绿色；没追踪就是红色


import org.testng.annotations.*;

public class BasicAnnotation {
    //最基本的注解，把方法标记为测试的一部分(测试方法)
    @Test
    public void testCase1(){
        System.out.println("这是测试用例1");
        System.out.printf("Thead Id: %s%n",Thread.currentThread().getId());

    }

    @Test
    public void testCase2(){
        System.out.println("这是测试用例2");
        System.out.printf("Thead Id: %s%n",Thread.currentThread().getId());

    }
    @BeforeMethod
    public void beforeMethod(){
        System.out.println("BeforeMethod在测试方法运行之前运行标签");
    }

    @AfterMethod
    public void afterMethod(){
        System.out.println("AfterMethod在测试方法运行之后运行标签");
    }

    @BeforeClass
    public void beforeClass(){
        System.out.println("beforeClass是在类运行之前运行的（标签）方法");
    }

    @AfterClass
    public void afterClass(){
        System.out.println("afterClass是在类运行之后运行的(标签)方法");
    }
    @BeforeSuite
    public void beforeSuite(){
        System.out.println("beforeSuite测试套件");
    }
    @AfterSuite
    public void afterSuite(){
        System.out.println("afterSuite测试套件");
    }
}
