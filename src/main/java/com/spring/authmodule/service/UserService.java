package com.spring.authmodule.service;

import com.spring.authmodule.dao.UserDetails;
import com.spring.authmodule.dto.UserDto;

import java.util.List;

public interface UserService {

    UserDto addUser(UserDto user) throws Exception;

    UserDto userLogin(UserDto dto) throws Exception;

}
