package com.mt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author DELL
 */
@SpringBootApplication
public class SpringbootDatabaseApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootDatabaseApplication.class, args);
        System.out.println("run方法执行完毕");
    }

}
