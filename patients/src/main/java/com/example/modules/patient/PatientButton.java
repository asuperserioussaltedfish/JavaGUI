package com.example.modules.patient;

import com.example.modules.login.LoginPasswordField;
import com.example.modules.login.LoginUsernameField;
import com.example.pojo.Patient;
import com.example.service.LoginService;
import com.example.service.serviceImpl.LoginServiceServiceImpl;
import com.example.vo.ErrorCode;
import com.example.vo.Result;
import javafx.animation.TranslateTransition;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;

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
