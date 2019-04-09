package com.autest.testng.groups;


import org.testng.annotations.Test;

@Test(groups = "teacher")
public class GroupsOnClass3 {

    public void teach1(){
        System.out.println("GroupsOnClass3333中的teach1运行11");
    }

    public void teach2(){
        System.out.println("GroupsOnClass3333中的teach2运行22");
    }

}
