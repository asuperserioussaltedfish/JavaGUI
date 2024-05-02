package com.example.pojo;

import lombok.Data;

/**
 * 用户实体类
 * @author  SaltedFish
 */
@Data
public class Doctor {
    private Long id;
    private String account;
    private String password;
    private Integer age;
    private String gender;
    private String education;
    private String department;
    private String doctorName;
    private String avatar;
    public Doctor(Long id , String account, String password,String doctorName,
                  Integer age, String gender, String education, String department,String avatar) {
        this.id=id;
        this.password=password;
        this.account=account;
        this.age=age;
        this.gender=gender;
        this.education=education;
        this.department=department;
        this.doctorName=doctorName;
        this.avatar=avatar;
    }
    public Doctor() {
    }
    public String doctorName() {
        return this.doctorName;
    }

    public void setName(String name) {
        this.doctorName = name;
    }
}
