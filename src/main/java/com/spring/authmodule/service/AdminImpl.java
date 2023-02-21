package com.spring.authmodule.service;

import com.spring.authmodule.dao.Role;
import com.spring.authmodule.dao.UserDetails;
import com.spring.authmodule.dto.UserDto;
import com.spring.authmodule.repository.AdminRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdminImpl implements AdminService {

    @Autowired
    AdminRepo adminRepo;
    @Autowired
    UserService userService;

    public List<UserDto> getAll() {
        List<UserDetails> details = adminRepo.findAll();
        List<UserDto> dto = convertToDtoList(details);
        return dto;
    }

    public UserDto convertToDto(UserDetails detail) {
        UserDto dto = new UserDto();
        dto.setUsername(detail.getUsername());
        dto.setPassword(detail.getPassword());
        return dto;
    }

    public List<UserDto> convertToDtoList(List<UserDetails> detail) {
        List<UserDto> dto = new ArrayList<>();
        for (UserDetails userDetails : detail) {
            dto.add(convertToDto(userDetails));
        }
        return dto;
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

}
