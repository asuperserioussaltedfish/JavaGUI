package com.example.modules.login;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


public class LoginStage extends Stage {
    public LoginStage() {
        //无标题栏
//        this.initStyle(StageStyle.UNDECORATED);

        //设置标题
        this.setTitle("患者登录");
        //创建布局
        BorderPane borderPane = new BorderPane();
        //创建场景，指定布局
        Scene scene = new Scene(borderPane,1120,630);
        LoginPane loginPane = new LoginPane();
        // 将登录面板设置在BorderPane的中心
        borderPane.setCenter(loginPane);
        // 将场景设置到主舞台上
        this.setScene(scene);
        // 设置最小尺寸
        this.setMinWidth(1120);
        this.setMinHeight(630);
        // 禁止窗口缩放
        this.setResizable(false);
        //         监听注册状态，注册状态变化时触发下列操作
        loginPane.loginProperty().addListener(((observable, oldValue, newValue) -> {
            // 关闭主舞台
            this.close();
        }));
    }
}