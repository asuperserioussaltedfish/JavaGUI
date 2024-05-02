package com.example.application;

import com.example.modules.login.LoginStage;
import com.example.modules.manage.ManageStage;
import javafx.stage.Stage;

/**
 * @author SaltedFish
 * 主启动类
 */
public class Application extends javafx.application.Application {
    public static void main(String[] args) {
        //自动调用start方法，传入参数--主窗口
        launch();
    }
    @Override
    public void start(Stage primaryStage) {
        LoginStage loginStage = new LoginStage();
        loginStage.show();
//        new ManageStage().show();
    }
}


