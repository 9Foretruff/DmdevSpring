package com.foretruff.spring.dto;

import org.springframework.beans.factory.annotation.Value;

import java.time.LocalDate;

public interface PersonalInfo2 {
    String getFirstname();

    String getLastname();

    String getBirthDate();

    @Value("#{target.firstname + ' ' + target.lastname}")
    String getFullName();

}
