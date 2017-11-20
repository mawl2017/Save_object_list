package com.example.ma.save_object_list.entity;

import java.io.Serializable;

/**
 * Created by ma on 2017/8/11.
 */
public class TestClass implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private String name;
    private String gender;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
    public TestClass(String name, String gender) {
        super();
        this.name = name;
        this.gender = gender;
    }

    public TestClass() {
    }
}
