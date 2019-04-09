package com.autest.testng.groups;

import org.testng.annotations.Test;
//类也可以写test标签，不仅是方法，而且也可以分组
@Test(groups = "stu")
public class GroupsOnClass1 {

    public void stu1(){
        System.out.println("GroupsOnClass1中的stu1运行11");
    }

    public void stu2(){
        System.out.println("GroupsOnClass1中的stu2运行22");
    }
}
