package com.foretruff.spring.integration.service;

import com.foretruff.spring.database.entity.Role;
import com.foretruff.spring.dto.UserCreateEditDto;
import com.foretruff.spring.integration.IntegrationTestBase;
import com.foretruff.spring.service.UserService;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

@RequiredArgsConstructor
public class UserServiceIT extends IntegrationTestBase {

    public static final Long USER_1 = 1L;
    public static final Integer COMPANY_1 = 1;
    private final UserService userService;

    @Test
    void findAll() {
        var result = userService.findAll();
        assertThat(result).hasSize(5);
    }

    @Test
    void findById() {
        var maybeUser = userService.findById(USER_1);
        assertTrue(maybeUser.isPresent());
        maybeUser.ifPresent(user -> assertEquals("ivan@gmail.com", user.getUsername()));
    }

    @Test
    void create() {
        var userDto = new UserCreateEditDto(
                "test@gmail.com",
                LocalDate.now(),
                "Maksym",
                "Rokitko",
                Role.ADMIN,
                COMPANY_1
        );
        var actualResult = userService.create(userDto);
        assertAll(
                () -> assertEquals(userDto.getUsername(), actualResult.getUsername()),
                () -> assertEquals(userDto.getBirthDate(), actualResult.getBirthDate()),
                () -> assertEquals(userDto.getFirstname(), actualResult.getFirstname()),
                () -> assertEquals(userDto.getLastname(), actualResult.getLastname()),
                () -> assertEquals(userDto.getCompanyId(), actualResult.getCompany().id()),
                () -> assertSame(userDto.getRole(), actualResult.getRole())
        );
    }

    @Test
    void update() {
        var userDto = new UserCreateEditDto(
                "test@gmail.com",
                LocalDate.now(),
                "Maksym",
                "Rokitko",
                Role.ADMIN,
                COMPANY_1
        );

        var actualResult = userService.update(USER_1, userDto);

        assertTrue(actualResult.isPresent());
        actualResult.ifPresent(user -> {
            assertEquals(userDto.getUsername(), user.getUsername());
            assertEquals(userDto.getBirthDate(), user.getBirthDate());
            assertEquals(userDto.getFirstname(), user.getFirstname());
            assertEquals(userDto.getLastname(), user.getLastname());
            assertEquals(userDto.getCompanyId(), user.getCompany().id());
            assertSame(userDto.getRole(), user.getRole());
        });
    }

    @Test
    void delete() {
        assertFalse(userService.delete(-124L));
        assertTrue(userService.delete(USER_1));
    }

}