package com.foretruff.spring;

import com.foretruff.spring.config.DatabaseProperties;
import com.foretruff.spring.database.pool.ConnectionPool;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
//@EntityScan
public class ApplicationRunner {

    public static void main(String[] args) {
        var context = SpringApplication.run(ApplicationRunner.class, args);
        System.out.println(context.getBean("pool1", ConnectionPool.class));
        System.out.println(context.getBean(DatabaseProperties.class));
    }

}
