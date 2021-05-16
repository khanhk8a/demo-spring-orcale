package com.example.demospringoracle.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "TBL_ROLE")
@Data
public class Role {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;

    @Column
    private String name;

    @Column
    private String des;

}
