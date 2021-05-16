package com.example.demospringoracle.model;

import java.io.Serializable;

public class StudentDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private long id;
    private String name;
    private int age;
    private String address;


    public StudentDTO() {
    }



    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
