package com.spring.authmodule.service;

import com.spring.authmodule.dao.UserDetails;
import com.spring.authmodule.dto.UserDto;

import java.util.List;

public interface UserService {

    UserDto addRegister(UserDto user) throws Exception;

    List<UserDetails> getAll();

    UserDto userLogin(UserDto dto) throws Exception;
}
