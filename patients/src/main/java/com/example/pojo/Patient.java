package com.example.pojo;

import lombok.Data;

/**
 * 用户实体类
 * @author  SaltedFish
 */
@Data
public class Patient {
    private Long id;
    private String account;
    private String password;
    private Integer age;
    private String gender;
    private String patientName;
    private String avatar;
    public Patient(Long id , String account, String password,String patientName,
                   Integer age, String gender, String avatar) {
        this.id=id;
        this.password=password;
        this.patientName=patientName;
        this.account=account;
        this.age=age;
        this.gender=gender;
        this.avatar=avatar;
    }
    public  Patient() {
    }
}
