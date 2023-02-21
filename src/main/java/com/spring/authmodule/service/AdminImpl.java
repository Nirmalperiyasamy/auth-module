package com.spring.authmodule.service;

import com.spring.authmodule.dao.Status;
import com.spring.authmodule.dao.UserDetails;
import com.spring.authmodule.dto.UserDto;
import com.spring.authmodule.repository.AdminRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminImpl implements AdminService {

    @Autowired
    AdminRepo adminRepo;
    @Autowired
    UserService userService;

    public List<UserDetails> getAll() {
        return adminRepo.findAll();
    }

    public UserDto updateUser(String username, UserDto dto) {

        UserDetails detail = adminRepo.findByusername(username);
        detail.setUsername(dto.getUsername());
        detail.setPassword(dto.getPassword());
        adminRepo.save(detail);
        BeanUtils.copyProperties(detail, dto);

        return dto;
    }

    public UserDto getByusername(String username) {
        UserDetails details = adminRepo.findByusername(username);
        UserDto dto = new UserDto();
        BeanUtils.copyProperties(details, dto);

        return dto;
    }

    public UserDetails admin() {
        UserDetails admin = new UserDetails();
        admin.setStatus(Status.YES);
        admin.setUsername("Gokul");
        admin.setPassword("1234");
        adminRepo.save(admin);
        return admin;
    }
}
