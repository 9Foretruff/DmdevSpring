package com.foretruff.spring.database.repository;

import com.foretruff.spring.bpp.Auditing;
import com.foretruff.spring.bpp.Transaction;
import com.foretruff.spring.database.entity.Company;
import com.foretruff.spring.database.pool.ConnectionPool;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;
import java.util.Optional;

@Transaction
@Auditing
public class CompanyRepository implements CrudRepository<Integer, Company> {

    //    @Resource(name = "pool1")
    @Autowired
    //    @Qualifier("pool1")
    private ConnectionPool pool1;

    @Autowired
    private List<ConnectionPool> pools;

    @Value("${db.pool.size}")
    private Integer poolSize;

    @PostConstruct
    private void init() {
        System.out.println("init company repository");
    }

    public void setPool1(/*@Autowired*/ ConnectionPool pool1) {
        this.pool1 = pool1;
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
