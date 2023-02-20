package com.spring.authmodule.dao;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "demo")
@Getter
@Setter
public class UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private int id;
    private String userName;
    private String password;


}
