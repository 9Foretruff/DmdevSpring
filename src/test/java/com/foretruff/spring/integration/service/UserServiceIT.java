package com.foretruff.spring.integration.service;

import com.foretruff.spring.database.pool.ConnectionPool;
import com.foretruff.spring.integration.annotation.IT;
import com.foretruff.spring.service.UserService;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;

@IT
@RequiredArgsConstructor
//@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class UserServiceIT {

    private final UserService userService;
    private final ConnectionPool pool1;

    @Test
//    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    void test() {
        System.out.println();
    }

    @Test
    void test2() {
        System.out.println();
    }

}