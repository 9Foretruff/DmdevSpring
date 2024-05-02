package com.foretruff.spring.database.repository;

import com.foretruff.spring.database.entity.Role;
import com.foretruff.spring.database.entity.User;
import com.foretruff.spring.dto.PersonalInfo;
import com.foretruff.spring.dto.UserFilter;

import java.util.List;

public interface FilterUserRepository {

    List<User> findAllByFilter(UserFilter userFilter);

    List<PersonalInfo> findAllByCompanyIdAndRole(Integer companyId, Role role);

}
