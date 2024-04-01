package com.foretruff.spring.database.repository;

import com.foretruff.spring.bpp.Auditing;
import com.foretruff.spring.bpp.InjectBean;
import com.foretruff.spring.bpp.Transaction;
import com.foretruff.spring.database.entity.Company;
import com.foretruff.spring.database.pool.ConnectionPool;
import jakarta.annotation.PostConstruct;

import java.util.Optional;

@Transaction
@Auditing
public class CompanyRepository implements CrudRepository<Integer, Company> {
    @InjectBean
    private ConnectionPool connectionPool;

    @PostConstruct
    private void init(){
        System.out.println("init company repository");
    }

    @Override
    public Optional<Company> findById(Integer id) {
        System.out.println("find by id method");
        return Optional.of(new Company(id));
    }

    @Override
    public void delete(Company entity) {
        System.out.println("delete method");
    }
}
