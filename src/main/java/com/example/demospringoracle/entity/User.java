package com.example.demospringoracle.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "TBL_USER")
@Data
public class User {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;
    private String password;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "TBL_USER_ROLE", joinColumns = {
        @JoinColumn(name = "USER_ID") }, inverseJoinColumns = {
        @JoinColumn(name = "ROLE_ID") })
    private List<Role> roles;

}
