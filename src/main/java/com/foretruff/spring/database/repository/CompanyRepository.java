package com.foretruff.spring.database.repository;

import com.foretruff.spring.database.entity.Company;
import org.springframework.data.repository.CrudRepository;

public interface CompanyRepository extends CrudRepository<Company, Integer> {

}