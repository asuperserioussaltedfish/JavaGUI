package com.example.pojo;

import lombok.Data;

/**
 * @author SaltedFish
 */
@Data
public class Message {
    private String id;
    private String content;
    private Long receiverId;
    private Long senderId;
    private Long messageTime;
    private Integer messagesStatus;

}
