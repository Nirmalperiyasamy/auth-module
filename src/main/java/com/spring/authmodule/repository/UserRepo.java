package com.spring.authmodule.repository;

import com.spring.authmodule.dao.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<UserDetails, Integer> {
    boolean existsByUserName(String userName);

    UserDetails findByUserName(String userName);
}
