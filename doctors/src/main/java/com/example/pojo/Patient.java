package com.example.pojo;

import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import lombok.Data;

/**
 * 用户实体类
 * @author  SaltedFish
 */
public class Patient {
    private LongProperty id;
    private SimpleStringProperty account;
    private  SimpleStringProperty patientName;
    private  SimpleStringProperty gender;
    private SimpleIntegerProperty age;
    private  SimpleStringProperty moreInfo;
    private  SimpleStringProperty caozuoInfo;
    private SimpleStringProperty password;
    private SimpleStringProperty avatar;
    public Patient(Long id, String name, String gender, String moreInfo, String caozuoInfo) {
        this.id = new SimpleLongProperty(id);
        this.patientName = new SimpleStringProperty(name);
        this.gender = new SimpleStringProperty(gender);
        this.moreInfo = new SimpleStringProperty(moreInfo);
        this.caozuoInfo = new SimpleStringProperty(caozuoInfo);
    }
    public Patient(Long id ,String account, String name, String gender, int age,String password){
        this.id=new SimpleLongProperty(id);
        this.account = new SimpleStringProperty(account);
        this.patientName = new SimpleStringProperty(name);
        this.gender = new SimpleStringProperty(gender);
        this.age = new SimpleIntegerProperty(age);
        this.password = new SimpleStringProperty(password);
    }
    public Patient(String account, String name, String gender, int age,String password){
        this.account = new SimpleStringProperty(account);
        this.patientName = new SimpleStringProperty(name);
        this.gender = new SimpleStringProperty(gender);
        this.age = new SimpleIntegerProperty(age);
        this.password = new SimpleStringProperty(password);
    }
    public Patient() {
        this.id=new SimpleLongProperty();
        this.account = new SimpleStringProperty();
        this.patientName = new SimpleStringProperty();
        this.gender = new SimpleStringProperty();
        this.age = new SimpleIntegerProperty();
        this.password = new SimpleStringProperty();
        this.avatar = new SimpleStringProperty();
        this.caozuoInfo=new SimpleStringProperty();
        this.moreInfo=new SimpleStringProperty();
    }

    public Long getPatientId() {
        return id.get();
    }

    public String getGender() {
        return gender.get();
    }


    public void setGender(String value) {
        gender.set(value);
    }

    public String getAccount() {
        return account.get();
    }
    public void setId(Long value){
        id.set(value);
    }
    public Long getId(){
        return id.get();
    }
    public void setAccount(String value) {
        account.set(value);
    }
    public String getPatientName() {
        return patientName.get();
    }
    public void setPatientName(String value) {
        patientName.set(value);
    }
    public Integer getAge() {
        return age.get();
    }


    public void setAge(int value) {
        age.set(value);
    }
    public String getPassword() {
        return password.get();
    }
    public String getCaoZuoInfo() {
        return caozuoInfo.get();
    }

    public void setPassword(String value) {
        password.set(value);
    }
    public String getAvatar() {
        return avatar.get();
    }


    public void setAvatar(String value) {
        avatar.set(value);
    }
    // 属性本身的方法，可用于数据绑定
    public SimpleLongProperty patientIdProperty() {
        return (SimpleLongProperty) id;
    }

    public SimpleStringProperty nameProperty() {
        return patientName;
    }

    public SimpleStringProperty genderProperty() {
        return gender;
    }

    public SimpleStringProperty moreInfoProperty() {
        return moreInfo;
    }

    public SimpleStringProperty cacaoInfoProperty() {
        return caozuoInfo;
    }


    // 获取属性的方法

    public String getName() {
        return patientName.get();
    }


    public String getMoreInfo() {
        return moreInfo.get();
    }

    // 设置属性的方法
    public void setPatientIdId(Long value) {
        id.set(value);
    }

    public void setName(String value) {
        patientName.set(value);
    }


    public void setMoreInfo(String value) {
        moreInfo.set(value);
    }


}
