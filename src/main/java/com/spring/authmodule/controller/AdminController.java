package com.spring.authmodule.controller;

import com.spring.authmodule.dao.UserDetails;
import com.spring.authmodule.dto.UserDto;
import com.spring.authmodule.service.AdminImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static constant.Constant.*;

@RestController
@RequestMapping(ADMIN)
public class AdminController {
    @Autowired
    private AdminImpl adminImpl;

    @GetMapping(ALL)
    public List<UserDetails> getAll(@RequestBody UserDto dto) {
        return adminImpl.getAll();
    }

    @PutMapping("/{username}")
    public ResponseEntity<?> updateUser(@PathVariable String username, @RequestBody UserDto dto) {
        dto = adminImpl.updateUser(username, dto);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/")
    public UserDetails admin() {
        return adminImpl.admin();
    }

    @GetMapping("/{username}")
    public ResponseEntity<?> getByName(@PathVariable String username) {
        return ResponseEntity.ok(adminImpl.getByusername(username));
    }


}
