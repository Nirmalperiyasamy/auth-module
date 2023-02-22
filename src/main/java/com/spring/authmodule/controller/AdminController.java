package com.spring.authmodule.controller;

import com.spring.authmodule.dao.UserDetails;
import com.spring.authmodule.dto.UserDto;
import com.spring.authmodule.service.AdminImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static constant.Constant.*;

@RestController
@RequestMapping(ADMIN)
public class AdminController {
    @Autowired
    private AdminImpl adminImpl;

    @GetMapping(ALL)
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(adminImpl.getAll());
    }

    @PutMapping(USERNAME)
    public ResponseEntity<?> updateUser(@PathVariable String username, @Valid @RequestBody UserDto dto) {
        dto = adminImpl.updateUser(username, dto);
        return ResponseEntity.ok(dto);
    }

    @GetMapping(USERNAME)
    public ResponseEntity<?> getByName(@PathVariable String username) {
        return ResponseEntity.ok(adminImpl.getByusername(username));
    }

}
