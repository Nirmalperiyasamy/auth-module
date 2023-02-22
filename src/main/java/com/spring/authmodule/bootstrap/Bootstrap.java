package com.spring.authmodule.bootstrap;

import com.spring.authmodule.dao.Role;
import com.spring.authmodule.dao.UserDetails;
import com.spring.authmodule.repository.AdminRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class Bootstrap {
    @Autowired
    private AdminRepo adminRepo;

    @PostConstruct
    private void postConstructor() {
        if (!isUsernameExist("Gokul")) {
            UserDetails admin = new UserDetails();
            admin.setUsername("Gokul");
            admin.setPassword("1234");
            admin.setRole(Role.YES);
            adminRepo.save(admin);
        }

    }

    public boolean isUsernameExist(String userName) {
        return adminRepo.existsByUsername(userName);
    }
}

