/*import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.annotation.PreDestroy;


@SpringBootApplication
@ComponentScan("com.autest")
public class Application {


    public static void main(String[] args) throws Exception{
        SpringApplication.run(Application.class,args);
    }
}*/

//指定特定端口号等配置信息，需用application.properties里面按格式书写配置



package com.autest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.annotation.PreDestroy;

@EnableScheduling
@SpringBootApplication
//万能启动
public class Application {

    private static ConfigurableApplicationContext context;

    public static void main(String[] args){

        Application.context = SpringApplication.run(Application.class,args);
    }

    @PreDestroy
    public void close(){
        Application.context.close();
    }
}
