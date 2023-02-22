package com.spring.authmodule.repository;

import com.spring.authmodule.dao.RoleDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepo extends JpaRepository<RoleDetails,Integer> {

}
