package com.example.demospringoracle.entity;

import lombok.Data;

import javax.persistence.*;

@Entity(name = "tbl_user_role")
@Data
public class UserRole {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int user_role_id;

    @Column(name = "user_id")
    private int userId;

    @Column(name = "role_id")
    private int roleId;

}
