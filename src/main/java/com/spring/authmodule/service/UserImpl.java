package com.spring.authmodule.service;

import com.spring.authmodule.dao.Role;
import com.spring.authmodule.dao.RoleDetails;
import com.spring.authmodule.dao.UserDetails;
import com.spring.authmodule.dto.UserDto;
import com.spring.authmodule.exceptionhandler.CustomException;
import com.spring.authmodule.repository.RoleRepo;
import com.spring.authmodule.repository.UserRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserImpl implements UserService, UserDetailsService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private RoleRepo roleRepo;


    public boolean isUsernameExist(String userName) {
        return userRepo.existsByUsername(userName);
    }

    @Override
    public UserDto addUser(UserDto user) {
        if (isUsernameExist(user.getUsername())) throw new CustomException("user already exist");

        UserDetails entity = new UserDetails();
        BeanUtils.copyProperties(user, entity);
        entity = userRepo.save(entity);
        BeanUtils.copyProperties(entity, user);
        RoleDetails details = new RoleDetails();
        details.setRole(Role.USER);
        roleRepo.save(details);
        return user;

    }


    @Override
    public UserDto userLogin(UserDto dto) {
        UserDetails user = getUserDetail(dto.getId());
        if (user.getPassword().equals(dto.getPassword())) {
            return dto;
        }
        throw new CustomException("Wrong password");
    }

    public UserDetails getUserDetail(Integer id) {
        return userRepo.findById(id).orElseThrow(() -> new CustomException("User not registered"));
    }


    @Override
    public org.springframework.security.core.userdetails.UserDetails
    loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails details = userRepo.findByUsername(username);
        List<GrantedAuthority> authorities = List.of((GrantedAuthority) () -> details.getRole().getRole().name());

        return new User(details.getUsername(), details.getPassword(), authorities);
    }

}
