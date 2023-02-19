package com.spring.authmodule.repository;

//import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;

import com.spring.authmodule.dao.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthModule extends JpaRepository<UserDetails, Integer> {
    boolean existsByUserName(String userName);

    UserDetails findByUserName(String userName);
}
