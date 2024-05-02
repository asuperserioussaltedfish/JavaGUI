package com.example.pojo;

import com.example.service.ManagePatientService;
import com.example.service.serviceImpl.ManagePatientServiceServiceImpl;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import lombok.Data;

/**
 * 消息实体类
 * @author SaltedFish
 */
public class Message {
    ManagePatientService managePatientService =new ManagePatientServiceServiceImpl();
    private LongProperty id;
    private StringProperty content;
    private LongProperty receiverId;
    private LongProperty senderId;
    private LongProperty messageTime;
    private IntegerProperty messageStatus;
    private StringProperty patientName;
    public Message() {
        this.id = new SimpleLongProperty();
        this.content = new SimpleStringProperty();
        this.receiverId = new SimpleLongProperty();
        this.senderId = new SimpleLongProperty();
        this.messageTime = new SimpleLongProperty();
        this.messageStatus = new SimpleIntegerProperty();
    }
    public Message(Long id,String content,Long receiverId,Long senderId,Long messageTime,Integer messageStatus) {
        this.id = new SimpleLongProperty(id);
        this.content = new SimpleStringProperty(content);
        this.receiverId = new SimpleLongProperty(receiverId);
        this.senderId = new SimpleLongProperty(senderId);
        this.messageTime = new SimpleLongProperty(messageTime);
        this.messageStatus = new SimpleIntegerProperty(messageStatus);
    }
    // 标准的属性获取和设置方法
    public String getPatientName(){
        return patientName.get();
    }
    public void setPatientName(String value){
        patientName.set(value);
    }
    public Long getId() {
        return id.get();
    }

    public void setId(Long value) {
        id.set(value);
    }

    public String getContent() {
        return content.get();
    }

    public void setContent(String value) {
        content.set(value);
    }

    public Long getReceiverId() {
        return receiverId.get();
    }

    public void setReceiverId(Long value) {
        receiverId.set(value);
    }

    public Long getSenderId() {
        return senderId.get();
    }

    public void setSenderId(Long value) {
        senderId.set(value);
    }

    public Long getMessageTime() {
        return messageTime.get();
    }

    public void setMessageTime(Long value) {
        messageTime.set(value);
    }

    public Integer getMessageStatus() {
        return messageStatus.get();
    }

    public void setMessageStatus(Integer value) {
        messageStatus.set(value);
    }

    // 属性本身的方法，可用于数据绑定
    public LongProperty idProperty() {
        return id;
    }

    public StringProperty contentProperty() {
        return content;
    }

    public LongProperty receiverIdProperty() {
        return receiverId;
    }

    public LongProperty senderIdProperty() {
        return senderId;
    }

    public LongProperty messageTimeProperty() {
        return messageTime;
    }

    public IntegerProperty messageStatusProperty() {
        return messageStatus;
    }
}