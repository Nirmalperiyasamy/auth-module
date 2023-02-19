package com.spring.authmodule.controller;

import com.spring.authmodule.dao.UserDetails;
import com.spring.authmodule.dto.UserDto;
import com.spring.authmodule.repository.AuthModule;
import com.spring.authmodule.service.UserImplementation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("api")
@Slf4j
@Validated
public class Controller {
    @Autowired
    private UserImplementation userImplementation;

    @PostMapping("/")
    public ResponseEntity<?> addRegister(@RequestBody @Valid UserDto dto) {
        try {
            log.info("Controller add register ");
            UserDto userDetail = new UserDto();
            userDetail = userImplementation.addRegister(dto);

            return ResponseEntity.ok(userDetail);

        } catch (Exception e) {
            log.error("error");
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> userLogin(@RequestBody @Valid UserDto dto) {
        try {
            UserDto userDetails = new UserDto();
            userDetails = userImplementation.userLogin(dto);

            return ResponseEntity.ok("login successfully");

        } catch (ArithmeticException errorMessage) {

            return ResponseEntity.badRequest().body(errorMessage.getMessage());

        } catch (Exception errorMessage) {

            return ResponseEntity.badRequest().body(errorMessage.getMessage());
        }
    }

    @GetMapping("/all")
    public List<UserDetails> getAll(@RequestBody UserDto dto) {
        return userImplementation.getAll();
    }


}
