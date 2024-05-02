package com.example.modules.manage;

import javafx.scene.control.Button;

/**
 *  登录按钮
 * @author SaltedFish
 */
public class PatientButton extends Button {
    public PatientButton(String text) {
        // 调用父类Button的构造方法，设置按钮上显示的文本
        super(text);
        //设置按钮最大尺寸
        this.setMinSize(100, 30);
        // 为按钮设置ID,便于在css文件中操作
        this.setId("login-button");
        // 添加CSS样式表
        this.getStylesheets().add(String.valueOf(getClass().getResource("/css/login/login.css")));
    }
}
