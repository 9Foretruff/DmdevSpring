package com.foretruff.spring.service;

import com.foretruff.spring.database.entity.Company;
import com.foretruff.spring.database.repository.CrudRepository;
import com.foretruff.spring.database.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final CrudRepository<Integer, Company> companyRepository;
}