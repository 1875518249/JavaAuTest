package com.autest.testng.MultiThead;

import org.testng.annotations.Test;

public class MultiTheadOnAnnotation {


    @Test(invocationCount = 10,threadPoolSize=3)//线程池 默认只有一个线程，用线程池大小可以设置为3个线程池
    public void  test(){
        System.out.println(1);   //尽量不要用有跨线程的变量进行关联，如果需要就需要加锁
        System.out.printf("Thead Id: %s%n",Thread.currentThread().getId());
    }
}
