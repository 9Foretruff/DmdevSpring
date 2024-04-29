package com.foretruff.spring.database.repository;

import com.foretruff.spring.database.entity.Role;
import com.foretruff.spring.database.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface UserRepository extends JpaRepository<User, Long> {

    @Query("select u from User u " +
           "where u.firstname like %:firstName% and u.lastname like %:lastName%")
    List<User> findAllBy(String firstName, String lastName);

    @Query(value = "SELECT * FROM users u WHERE u.username = :username ",
            nativeQuery = true)
    List<User> findAllByUsername(String username);

    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query("update User u " +
           "set u.role = :role " +
           "where u.id in (:ids)")
    int updateRole(Role role, Long... ids);
}