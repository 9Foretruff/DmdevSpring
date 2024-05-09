package com.foretruff.spring.integration.http.contoller;

import com.foretruff.spring.database.entity.Role;
import com.foretruff.spring.integration.IntegrationTestBase;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static com.foretruff.spring.dto.UserCreateEditDto.Fields.birthDate;
import static com.foretruff.spring.dto.UserCreateEditDto.Fields.companyId;
import static com.foretruff.spring.dto.UserCreateEditDto.Fields.firstname;
import static com.foretruff.spring.dto.UserCreateEditDto.Fields.lastname;
import static com.foretruff.spring.dto.UserCreateEditDto.Fields.role;
import static com.foretruff.spring.dto.UserCreateEditDto.Fields.username;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrlPattern;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@AutoConfigureMockMvc
@RequiredArgsConstructor
class UserControllerTest extends IntegrationTestBase {

    private final MockMvc mockMvc;

    @BeforeEach
    void init() {
//        List<GrantedAuthority> roles = Arrays.asList(Role.ADMIN, Role.USER); // уже пофиксили
//        var testUser = new User("test@gmail.com", "test", roles);
//        var testingAuthenticationToken = new TestingAuthenticationToken(testUser, testUser.getPassword(), testUser.getAuthorities());
//
//        var securityContext = SecurityContextHolder.createEmptyContext();
//        securityContext.setAuthentication(testingAuthenticationToken);
//        SecurityContextHolder.setContext(securityContext);
    }

    @Test
    @WithMockUser(username = "test@gmail.com", password = "test", authorities = {"ADMIN", "USER"})
    void findAll() throws Exception {
        mockMvc.perform(get("/users")
                        .with(user("test@gmail.com").authorities(Role.ADMIN).password("test")))
                .andExpect(status().is2xxSuccessful())
                .andExpect(view().name("user/users"))
                .andExpect(model().attributeExists("users"));
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