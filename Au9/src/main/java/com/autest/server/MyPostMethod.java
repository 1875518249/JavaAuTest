package com.autest.server;

import com.autest.bean.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@Api(value = "/v1",description = "这是我的全部post请求")
@RequestMapping("/v1") //这个起到的效果是下面所有方法前面加上/v1，可以版本分开
public class MyPostMethod {

    //属性变量装cookies信息
    private static Cookie cookie;

    //用户登录成功获取cookies，然后再访问其他接口获取列表
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ApiOperation(value = "登录接口，成功后获取cookies信息",httpMethod = "POST")
    public String login(HttpServletResponse response,
                        @RequestParam(value = "username",required = true) String userName,
                        @RequestParam(value = "passWord",required = true) String passWord){

        if(userName.equals("liusan") && passWord.equals("123456")){
            cookie = new Cookie("login","true");

            response.addCookie(cookie);
            return "登陆成功";
        }
        return "用户名或密码错误";
    }

    @ApiOperation(value = "带请求参数和cookies的post请求获得用户列表",httpMethod = "POST")
    @RequestMapping(value = "/getUserList",method = RequestMethod.POST)
    //带请求参数和cookies的post请求,直接传参数用到@RequestBod其实是json格式
  public String getUserList(HttpServletRequest request, @RequestBody User u){


        User u1; //特别注意懒加载和预加载区别;这里是局部变量
        //获取cookies信息
        Cookie[] cookies = request.getCookies();
        //验证cookies是否合法
        for(Cookie c:cookies){
            if(c.getName().equals("login") && c.getValue().equals("true") &&
                    u.getUserName().equals("liusan") && u.getPassWord().equals("123456")){
                u1 =new User(); //用懒加载返回的是数值，预加载返回是对象地址
                u1.setName("牛五");
                u1.setAge(19);
                u1.setGender("man");

                return u1.toString();

            }

        }

        return  "获取用户列表失败！";

    }



}
