package com.foretruff.spring.dto;

import com.foretruff.spring.database.entity.Role;
import com.foretruff.spring.validation.UserInfo;
import com.foretruff.spring.validation.group.CreateAction;
import com.foretruff.spring.validation.group.UpdateAction;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Value;
import lombok.experimental.FieldNameConstants;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

@Value
@FieldNameConstants
@UserInfo(groups = UpdateAction.class)
public class UserCreateEditDto {

    @Email
    String username;

    @NotBlank(groups = CreateAction.class)
    String rawPassword;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate birthDate;

    //    @NotNull
    String firstname;

    //    @NotNull
    @Size(min = 3, max = 64)
    String lastname;

    Role role;

    Integer companyId;

    MultipartFile image;
}
