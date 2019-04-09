package com.autest.testng;

import org.testng.annotations.Test;

public class TimeOutTest {

    @Test(timeOut = 3000,description = "测试超时成功")//单位为毫秒值
    public void testSuccess()throws InterruptedException{
        Thread.sleep(2000);
    }

    @Test(timeOut = 2000,description = "测试超时失败")
    public void testFail()throws InterruptedException{
        Thread.sleep(3500);
    }
}
