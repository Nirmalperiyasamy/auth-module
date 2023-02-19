package com.spring.authmodule.service;

import com.spring.authmodule.dao.UserDetails;
import com.spring.authmodule.dto.UserDto;
import com.spring.authmodule.repository.AuthModule;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserImplementation implements UserService {
    @Autowired
    private AuthModule authModule;

    public boolean isUsernameExist(String userName) {
        return authModule.existsByUserName(userName);
    }

    @Override
    public UserDto addRegister(UserDto user) throws Exception {
        if (isUsernameExist(user.getUserName())) {
            throw new Exception("user alredy exist");
        } else {
            UserDetails entity = new UserDetails();
            BeanUtils.copyProperties(user, entity);
            entity = authModule.save(entity);
            BeanUtils.copyProperties(entity, user);

            return user;
        }

    }

    @Override
    public List<UserDetails> getAll() {
        List<UserDetails> emp = authModule.findAll();

        return emp;
    }

    @Override
    public UserDto userLogin(UserDto dto) throws Exception {
        if (isUsernameExist(dto.getUserName())) {
            UserDetails user = authModule.findByUserName(dto.getUserName());
            if (user.getPassword().equals(dto.getPassword())) {

                return dto;

            } else {

                throw new ArithmeticException("Wrong password");
            }
        } else {

            throw new Exception("user not registered");
        }
    }
}
