package com.autest.testng.groups;

import org.testng.annotations.Test;

@Test(groups = "stu")
public class GroupsOnClass2 {
    public void stu1(){
        System.out.println("GroupsOnClass2222中的stu1运行11111");
    }

    public void stu2(){
        System.out.println("GroupsOnClass222中的stu2运行22222");
    }

}
