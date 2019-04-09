package com.autest.testng.paramter;

import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class ParamterTest {
//   @Parameters({"nm","ag"})
    @Test(parameters = {"name","age"})
    public void paramTest1(String name, int age){  //参数化需要接收参数

        System.out.println("name = "+name+"  age = "+age);
    }
}
