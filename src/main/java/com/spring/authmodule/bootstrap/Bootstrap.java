package com.spring.authmodule.bootstrap;

import com.spring.authmodule.dao.Role;
import com.spring.authmodule.dao.RoleDetails;
import com.spring.authmodule.dao.UserDetails;
import com.spring.authmodule.repository.AdminRepo;
import com.spring.authmodule.repository.RoleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class Bootstrap {
    @Autowired
    private AdminRepo adminRepo;
    @Autowired
    private RoleRepo roleRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostConstruct
    private void postConstructor() {
        if (!isUsernameExist("Gokul")) {
            RoleDetails role = new RoleDetails();
            role.setRole(Role.ADMIN);
            role = roleRepo.save(role);

            UserDetails admin = new UserDetails();
            admin.setUsername("Gokul");
            admin.setRole(role);
            admin.setPassword(passwordEncoder.encode("1234"));
            adminRepo.save(admin);
        }

    }

    public boolean isUsernameExist(String userName) {
        return adminRepo.existsByUsername(userName);
    }
}

