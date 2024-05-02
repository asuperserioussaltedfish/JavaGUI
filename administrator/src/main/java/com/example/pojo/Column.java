package com.example.pojo;

import lombok.Data;

@Data
public class Column {

    private String title;
    private String fieldName;

    public Column(String title, String fieldName) {
        this.title = title;
        this.fieldName = fieldName;
    }

}