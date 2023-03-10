package com.spring.authmodule.dao;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private int id;
    private String username;

    private String password;

    @OneToOne
    private RoleDetails role;
}
