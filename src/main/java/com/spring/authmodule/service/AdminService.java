package com.spring.authmodule.service;

import com.spring.authmodule.dto.UserDto;

import java.util.List;

public interface AdminService {

    List<UserDto> getAll();

    UserDto updateUser(String username, UserDto dto);

}
