package com.shunzi.ssm.entity;

/**
 * Created by Administrator on 2017/7/12.
 */
public class Test {
    private Integer id;
    private String userName;
    private String userPassword;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }


    @Override
    public String toString() {
        return "id>>>>>"+id+";userName>>>>>"+userName+";userPassword>>>>>"+userPassword;
    }
}
