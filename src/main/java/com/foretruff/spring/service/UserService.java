package com.foretruff.spring.service;

import com.foretruff.spring.database.entity.Company;
import com.foretruff.spring.database.repository.CrudRepository;
import com.foretruff.spring.database.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final CrudRepository<Integer,Company> companyRepository;

    public UserService(UserRepository userRepository,
                       CrudRepository<Integer,Company> companyRepository) {
        this.userRepository = userRepository;
        this.companyRepository = companyRepository;
    }

}
