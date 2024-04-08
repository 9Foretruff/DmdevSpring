package com.foretruff.spring.database.repository;

import com.foretruff.spring.bpp.Auditing;
import com.foretruff.spring.bpp.Transaction;
import com.foretruff.spring.database.entity.Company;
import com.foretruff.spring.database.pool.ConnectionPool;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Scope(BeanDefinition.SCOPE_SINGLETON)
@Transaction
@Auditing
public class CompanyRepository implements CrudRepository<Integer, Company> {

    private final ConnectionPool pool1;

    private final List<ConnectionPool> pools;

    private final Integer poolSize;

    public CompanyRepository(@Qualifier("pool1") ConnectionPool pool1,
                             List<ConnectionPool> pools,
                             @Value("${db.pool.size}") Integer poolSize) {
        this.pool1 = pool1;
        this.pools = pools;
        this.poolSize = poolSize;
    }

    @PostConstruct
    private void init() {
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
