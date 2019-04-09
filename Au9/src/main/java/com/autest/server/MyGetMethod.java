package com.autest.server;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@RestController
@Api(value = "/",description = "这是我的全部的get方法")
public class MyGetMethod {


    @RequestMapping(value = "/getCookies",method = RequestMethod.GET)
    @ApiOperation(value = "说明这个方法是干嘛的；这个是获取到cookies",httpMethod = "GET")
    public String getCookie(HttpServletResponse reponse){

        //HttpServletRequest 装请求信息的类
        //HttpServletResponse 装响应信息的类
        Cookie cookie =new Cookie("login","true");
        reponse.addCookie(cookie);
        return "You get cookies success!";
    }
    //要求客户端携带cookies访问
    @RequestMapping(value = "/get/with/cookies",method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "携带cookies信息get访问",httpMethod = "GET")
    public String getWithCookies(HttpServletRequest request){
    Cookie[] cookies = request.getCookies();
    if(Objects.isNull(cookies)){
        return "你必须携带cookies信息来";
    }
    for(Cookie cookie:cookies){
        if(cookie.getName().equals("login") && cookie.getValue().equals("true")){
            return "携带cookies信息get访问success！";
        }
    }
    return "你必须携带cookies信息来1";
    }

    /**
     * 开发一个需要携带参数才能访问的get请求
     * 第一种方式  url：key=value&key=value
     * 模拟获取商品列表
     * @RequestParam
     */
     @ApiOperation(value = "携带参数才能访问get请求；第一种方式  url：key=value&key=value",httpMethod ="GET" )
    @RequestMapping(value = "/get/list",method = RequestMethod.GET)
    public Map<String,Integer> getList(@RequestParam Integer start,
                                       @RequestParam Integer end){
            Map<String, Integer> myList = new HashMap<>();
            myList.put("鞋", 400);
            myList.put("卫衣", 119);
            myList.put("裤子", 88);

            return myList;

    }
    /**
     * 第二种需要携带参数访问的get请求
     * 区别 url:ip:port/get/list/10/20
     * 访问路径有区别
     */
    @ApiOperation(value = "第二种需要携带参数访问的get请求;区别 url:ip:port/get/list/10/20",httpMethod = "GET")
    @RequestMapping(value = "/get/list/{start}/{end}",method = RequestMethod.GET)
    public Map myGetList(@PathVariable Integer start,@PathVariable Integer end){

        Map<String, Integer> myList = new HashMap<>();
        myList.put("鞋", 501);
        myList.put("卫衣", 121);
        myList.put("裤子", 99);

        return myList;
    }


}
