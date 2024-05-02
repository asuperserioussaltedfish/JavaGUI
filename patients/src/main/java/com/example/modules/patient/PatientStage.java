package com.example.modules.patient;

import com.example.modules.login.LoginButton;
import com.example.utils.DatabaseUtil;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
//`PatientStage` 类是一个 JavaFX 界面，作为患者服务的主界面，提供了查询医生信息、预约医生、
// 查看和发送医生留言等功能的按钮，并通过简洁的界面布局方便用户进行相关操作
/**
 * @author SaltedFish
 */
public class PatientStage extends Stage {

    public  PatientStage(){

        this.setMinWidth(1120);
        this.setMinHeight(630);
        // 创建按钮
        Button button1 = new PatientButton("查询医生信息");
        Button button2 = new PatientButton("预约医生");
        Button button3 = new PatientButton("查看医生留言");
        Button button4 = new PatientButton("给医生留言");

        // 为按钮设置事件处理
        button1.setOnAction(event -> {
            new SelectDoctorStage().show();
        });
        button2.setOnAction(event -> {
            new AppointmentStage().show();
        });
        button3.setOnAction(event -> {
            new SelectMessageStage().show();
        });
        button4.setOnAction(event -> {
            new MessageStage().show();
        });
        BackgroundFill backgroundFill = new BackgroundFill(Color.rgb(89,118,186), null, null);
        Background background = new Background(backgroundFill);
        // 创建垂直布局容器并添加按钮
        VBox vBox = new VBox();
        vBox.setBackground(background);

        vBox.getChildren().addAll(button1, button2, button3, button4);
        vBox.setAlignment(Pos.CENTER);
        vBox.setMinWidth(100);
        vBox.setMinHeight(100);
        vBox.setSpacing(30);
        // 创建场景，将VBox布局放置在其中，大小为300x200像素
        Scene scene = new Scene(vBox, 300, 200);
        // 设置舞台，添加场景，并显示
        this.setTitle("医生服务");
        this.setScene(scene);
        this.show();
    }

}
