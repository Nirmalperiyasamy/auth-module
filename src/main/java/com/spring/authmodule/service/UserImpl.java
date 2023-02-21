package com.spring.authmodule.service;

import com.spring.authmodule.dao.Status;
import com.spring.authmodule.dao.UserDetails;
import com.spring.authmodule.dto.UserDto;
import com.spring.authmodule.exceptionhandler.UserAlreadyExist;
import com.spring.authmodule.exceptionhandler.UserNotRegistered;
import com.spring.authmodule.exceptionhandler.WrongPassword;
import com.spring.authmodule.repository.UserRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserImpl implements UserService {
    @Autowired
    private UserRepo userRepo;


    public boolean isUsernameExist(String userName) {
        return userRepo.existsByUsername(userName);
    }

    @Override
    public UserDto addUser(UserDto user) {
        if (isUsernameExist(user.getUsername())) {
            throw new UserAlreadyExist();
        } else {
            UserDetails entity = new UserDetails();
            entity.setStatus(Status.NO);
            BeanUtils.copyProperties(user, entity);
            entity = userRepo.save(entity);
            BeanUtils.copyProperties(entity, user);

            return user;
        }
    }


    @Override
    public UserDto userLogin(UserDto dto) {
        if (isUsernameExist(dto.getUsername())) {
            UserDetails user = userRepo.findByUsername(dto.getUsername());
            if (user.getPassword().equals(dto.getPassword())) {
                return dto;
            } else {
                throw new WrongPassword();
            }
        } else {
            throw new UserNotRegistered();
        }
    }

}
