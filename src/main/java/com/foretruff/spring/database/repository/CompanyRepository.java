package com.foretruff.spring.database.repository;

import com.foretruff.spring.bpp.InjectBean;
import com.foretruff.spring.database.pool.ConnectionPool;

public class CompanyRepository {
    @InjectBean
    private ConnectionPool connectionPool;


}
