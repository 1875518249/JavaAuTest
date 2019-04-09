package com.autest.testng;

import org.testng.annotations.Test;

public class EspectException {
    /**
     * 什么时候用到异常测试？
       在我们期望结果是为某一个异常的时候
       比如：我们传入了某些不合法的参数，程序抛出了异常
       也就是说我的预期结果就是这个异常
     */

    //演示一个测试结果失败的异常测试
    @Test(expectedExceptions = RuntimeException.class)
    public void runTimeExceptionFailed(){
        System.out.println("这是一个失败的异常测试");
    }

    //这是一个成功的异常测试
    @Test(expectedExceptions = RuntimeException.class)
    public void runTimeExceptionSuccess(){
        System.out.println("这是一个成功的异常测试");
        throw new RuntimeException();//注意运行时这里抛出异常，后面代码运行不到了，打印必须放在前面否则报红线
    }
}
