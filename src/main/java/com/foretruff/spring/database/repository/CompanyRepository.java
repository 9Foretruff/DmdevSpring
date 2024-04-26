package com.foretruff.spring.database.repository;

import com.foretruff.spring.bpp.Auditing;
import com.foretruff.spring.bpp.Transaction;
import com.foretruff.spring.database.entity.Company;
import com.foretruff.spring.database.pool.ConnectionPool;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Slf4j
@Repository
@Scope(BeanDefinition.SCOPE_SINGLETON)
@Transaction
@Auditing
@RequiredArgsConstructor
public class CompanyRepository implements CrudRepository<Integer, Company> {

    @Qualifier("pool1")
    private final ConnectionPool pool1;

    private final List<ConnectionPool> pools;

    @Value("${db.pool.size}")
    private final Integer poolSize;

    @PostConstruct
    private void init() {
        log.info("init company repository");
    }

    @Override
    public Optional<Company> findById(Integer id) {
        log.info("find by id method");
        return Optional.of(new Company(id, null, Collections.emptyMap()));
    }

    @Override
    public void delete(Company entity) {
        log.info("delete method");
    }
}
