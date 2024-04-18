package com.foretruff.spring.service;

import com.foretruff.spring.database.entity.Company;
import com.foretruff.spring.database.repository.CrudRepository;
import com.foretruff.spring.dto.CompanyReadDto;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CompanyService {
    private final UserService userService;
    private final CrudRepository<Integer, Company> companyRepository;


    public CompanyService(UserService userService,
                          CrudRepository<Integer, Company> companyRepository) {
        this.userService = userService;
        this.companyRepository = companyRepository;
    }

    public Optional<CompanyReadDto> findById(Integer id) {
        return companyRepository.findById(id)
                .map(company -> new CompanyReadDto(company.id()));
    }

}
