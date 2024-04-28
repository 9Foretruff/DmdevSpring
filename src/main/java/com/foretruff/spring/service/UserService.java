package com.foretruff.spring.service;

import com.foretruff.spring.database.repository.CompanyRepository;
import com.foretruff.spring.database.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final CompanyRepository companyRepository;
}