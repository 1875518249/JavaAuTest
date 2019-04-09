package com.autest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.annotation.PreDestroy;

@EnableScheduling
@SpringBootApplication
//万能启动，单例启动
public class Application {
    //启动程序采用单例懒加载模式，再启动会使用预损毁
    private static ConfigurableApplicationContext context;

    public static void main(String[] args){

        Application.context = SpringApplication.run(Application.class,args);
    }
    //预损毁
    @PreDestroy
    public void close(){
        Application.context.close();
    }
}
