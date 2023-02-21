package com.spring.authmodule.dao;

import lombok.Getter;
import lombok.Setter;
import lombok.Value;

import javax.persistence.*;

@Entity
@Table(name = "demo")
@Getter
@Setter

public class UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private int id;
    private String username;

    private String password;

    @Enumerated(EnumType.STRING)
    private Status status;
}
