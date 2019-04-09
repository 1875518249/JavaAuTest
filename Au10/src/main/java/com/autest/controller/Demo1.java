package com.autest.controller;


import com.autest.model.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Log4j
@RestController
@Api(value ="v1",description = "这是我的第一个测试demo")
@RequestMapping("v1")
public class Demo1 {

    //首先获取一个执行sql语句的对象
    @Autowired //起动机加载，demo启动起来这个对象就被加载赋值
    private SqlSessionTemplate template;

    @RequestMapping(value = "/getUserCounts",method = RequestMethod.GET)
    @ApiOperation(value = "可以获取用户数",httpMethod = "GET")
    public int getUserCounts(){

        //selectone 返回的只有一个结果或null；否则就报错;TooManyResultsException
        //查询数据库中值为null，没有返回值也会报500错，要避免null;java.lang.NullPointerException: null
        return template.selectOne("getUserCounts"); //重点，


    }
        //boot工程格式如下
        //  /swagger-ui.html
        //
        //非boot工程加个自己项目名
        //   /xxx/swagger-ui.html
    @RequestMapping(value = "/getUser",method = RequestMethod.GET)
    @ApiOperation(value = "获取用户信息",httpMethod = "GET")
    public List<Object> getUser(){
        return template.selectList("getUser");

    }

    @RequestMapping(value = "/addLoginUser",method = RequestMethod.POST)
    @ApiOperation(value = "添加用户",httpMethod = "POST")
    public int addUser(@RequestBody User user){
        return template.insert("addUser",user);

    }



}
