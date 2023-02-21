package com.spring.authmodule.controller;

import com.spring.authmodule.dao.UserDetails;
import com.spring.authmodule.dto.UserDto;
import com.spring.authmodule.service.UserImpl;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static constant.Constant.*;

@RestController
@RequestMapping(USER)
@Slf4j
@Validated
public class AuthController {

    @Autowired
    private UserImpl userImplementation;

    @GetMapping("ping")
    public ResponseEntity<?> ping() {
        return ResponseEntity.ok("PONG!");
    }

    @PostMapping(ADD)
    public ResponseEntity<?> addUser(@RequestBody @Valid UserDto dto) {

        log.info("Controller add register ");
        UserDto userDetail = new UserDto();
        userDetail = userImplementation.addUser(dto);
        return ResponseEntity.ok(userDetail);
    }

    @PostMapping(LOGIN)
    public ResponseEntity<?> userLogin(@RequestBody @Valid UserDto dto) {

        UserDto userDetails = userImplementation.userLogin(dto);

        return ResponseEntity.ok("login successfully");

    }
}
