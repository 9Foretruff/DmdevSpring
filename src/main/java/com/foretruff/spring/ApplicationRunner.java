package com.foretruff.spring;

import com.foretruff.spring.config.ApplicationConfiguration;
import com.foretruff.spring.database.pool.ConnectionPool;
import com.foretruff.spring.database.repository.CrudRepository;
import com.foretruff.spring.service.CompanyService;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.Serializable;

public class ApplicationRunner {
    public static void main(String[] args) {
        String value = "hello";
        System.out.println(CharSequence.class.isAssignableFrom(value.getClass()));
        System.out.println(BeanFactoryPostProcessor.class.isAssignableFrom(value.getClass()));
        System.out.println(Serializable.class.isAssignableFrom(value.getClass()));

        try (var context = new AnnotationConfigApplicationContext()) {
//      clazz -> Map<String,Object>
            context.register(ApplicationConfiguration.class);
            context.getEnvironment().setActiveProfiles("prod","web");
            context.refresh();

            var connectionPool = context.getBean("pool1", ConnectionPool.class);
            System.out.println(connectionPool);

            var companyService = context.getBean("companyService", CompanyService.class);
            System.out.println(companyService.findById(1));
        }
    }
}