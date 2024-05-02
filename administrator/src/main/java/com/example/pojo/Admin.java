package com.example.pojo;

import lombok.Data;

/**
 * 管理员
 * @author  SaltedFish
 */
@Data
public class Admin {
    private Long adminId;
    private String account;
    private String adminPassword;
    private Integer age;
    private String gender;
    private String adminName;
    public Admin(Long adminId , String account, String adminPassword,Integer age,String gender,String adminName) {
        this.adminId=adminId;
        this.adminPassword=adminPassword;
        this.account=account;
        this.age=age;
        this.gender=gender;
        this.adminName=adminName;
    }
    public Admin() {
    }
}
