package com.foretruff.spring.config;

import com.foretruff.spring.database.pool.ConnectionPool;
import com.foretruff.spring.database.repository.UserRepository;
import com.foretruff.web.config.WebConfiguration;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.Scope;

//@ImportResource("classpath:application.xml")
@Import(WebConfiguration.class)
@Configuration(proxyBeanMethods = true)
public class ApplicationConfiguration {

    @Bean("pool2")
    @Scope(BeanDefinition.SCOPE_SINGLETON)
    public ConnectionPool pool2(@Value("${db.username}") String username) {
        return new ConnectionPool(username, 20);
    }

    @Bean
    public ConnectionPool pool3() {
        return new ConnectionPool("test-pool", 25);
    }

//    @Bean("userRepository2")
//    @Profile("prod|web")
//    //      ! & |
//    public UserRepository userRepository2(@Qualifier("pool2") ConnectionPool connectionPool) {
//        return new UserRepository(connectionPool);
//    }
//
//    @Bean("userRepository4")
//    public UserRepository userRepository4() {
//        return new UserRepository(pool3());
//    }
//
//    @Bean
//    public UserRepository userRepository3() {
//        var connectionPool1 = pool3();
//        var connectionPool2 = pool3();
//        var connectionPool3 = pool3();
//        return new UserRepository(pool3());
//    }

}