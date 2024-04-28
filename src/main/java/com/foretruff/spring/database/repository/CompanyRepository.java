package com.foretruff.spring.database.repository;

import com.foretruff.spring.database.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CompanyRepository extends JpaRepository<Company, Integer> {

    // не очень способ :(
    Optional<Company> findByName(@Param("name2") String name);

    //Collection , Stream(batch,close)
    List<Company> findAllByNameContainingIgnoreCase(String fragment);

}