package com.foretruff.spring.service;

import com.foretruff.spring.database.repository.CompanyRepository;
import com.foretruff.spring.dto.CompanyReadDto;
import com.foretruff.spring.listener.entity.AccessType;
import com.foretruff.spring.listener.entity.EntityEvent;
import com.foretruff.spring.mapper.CompanyReadMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CompanyService {
    private final CompanyRepository companyRepository;
    private final CompanyReadMapper companyReadMapper;
    private final ApplicationEventPublisher eventPublisher;


    public Optional<CompanyReadDto> findById(Integer id) {
        return companyRepository.findById(id)
                .map(company -> {
                    eventPublisher.publishEvent(new EntityEvent(company, AccessType.READ));
                    return companyReadMapper.map(company);
                });
    }


    public List<CompanyReadDto> findAll() {
        return companyRepository.findAll().stream()
                .map(companyReadMapper::map)
                .toList();
    }

}
