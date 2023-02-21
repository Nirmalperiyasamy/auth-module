package com.spring.authmodule.repository;

import com.spring.authmodule.dao.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepo extends JpaRepository<UserDetails,Integer>{
    UserDetails findByusername(String username);
}
