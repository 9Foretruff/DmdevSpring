package com.foretruff.spring.integration.http.contoller;

import com.foretruff.spring.database.entity.Role;
import com.foretruff.spring.dto.UserCreateEditDto;
import com.foretruff.spring.integration.IntegrationTestBase;
import com.foretruff.spring.integration.annotation.IT;
import lombok.RequiredArgsConstructor;
import org.hamcrest.collection.IsCollectionWithSize;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static com.foretruff.spring.dto.UserCreateEditDto.Fields.birthDate;
import static com.foretruff.spring.dto.UserCreateEditDto.Fields.companyId;
import static com.foretruff.spring.dto.UserCreateEditDto.Fields.firstname;
import static com.foretruff.spring.dto.UserCreateEditDto.Fields.lastname;
import static com.foretruff.spring.dto.UserCreateEditDto.Fields.role;
import static com.foretruff.spring.dto.UserCreateEditDto.Fields.username;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrlPattern;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@AutoConfigureMockMvc
@RequiredArgsConstructor
class UserControllerTest extends IntegrationTestBase {

    private final MockMvc mockMvc;

    @Test
    void findAll() throws Exception {
        mockMvc.perform(get("/users"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(view().name("user/users"))
                .andExpect(model().attributeExists("users"))
                .andExpect(model().attribute("users", IsCollectionWithSize.hasSize(5)));
    }

    @Test
    void create() throws Exception {
        mockMvc.perform(post("/users")
                .param(username, "test@gmail.com")
                .param(firstname, "test")
                .param(lastname, "test")
                .param(role, "ADMIN")
                .param(companyId, "1")
                .param(birthDate, "2000-01-01")
        ).andExpectAll(
                status().is3xxRedirection(),
                redirectedUrlPattern("/users/*")
        );
    }

}