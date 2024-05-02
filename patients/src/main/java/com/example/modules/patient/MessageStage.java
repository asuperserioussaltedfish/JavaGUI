package com.example.modules.patient;

import com.example.pojo.Doctor;
import com.example.pojo.Message;
import com.example.service.PatientService;
import com.example.service.serviceImpl.PatientServiceImpl;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.List;
//`MessageStage` 类是一个 JavaFX 界面，用于实现患者向医生发送留言的功能，
// 包括输入医生编号和留言内容的字段、发送按钮，以及一个用于显示操作结果的文本区域。
/**
 * @author SaltedFish
 */

public class MessageStage extends Stage {
    private PatientService patientService = new PatientServiceImpl();
    private TextArea logArea = new TextArea();
    private Field doctorIdField = new Field();
    private TextArea messageContentArea = new TextArea();

    public MessageStage() {
        this.setMinWidth(1120);
        this.setMinHeight(630);
        BackgroundFill backgroundFill = new BackgroundFill(Color.rgb(89, 118, 186), null, null);
        Background background = new Background(backgroundFill);
        VBox vBox = new VBox();
        vBox.setBackground(background);

        // 创建Label
        Label doctorIdLabel = new Label("医生编号:");
        Label messageLabel = new Label("留言内容:");

        // 创建发送按钮
        Button sendButton = new PatientButton("发送");

        // 为发送按钮设置点击事件监听器
        sendButton.setOnAction(event -> {
            String doctorId = doctorIdField.getUsername().trim();
            String messageContent = messageContentArea.getText().trim();
            if(!doctorId.isEmpty() && !messageContent.isEmpty()){
                String result = patientService.leaveMessageToDoctor( Long.valueOf(5),Long.valueOf(doctorId),messageContent, System.currentTimeMillis(),0).getMsg();
                logArea.appendText("留言结果: " + result + "\n");
            } else {
                logArea.appendText("医生编号和留言内容不能为空！\n");
            }
        });

        // 创建HBox布局，添加labels和fields以及button
        HBox inputRowDoctorId = new HBox(10, doctorIdLabel, doctorIdField);
        HBox inputRowMessage = new HBox(10, messageLabel, messageContentArea);
        inputRowDoctorId.setAlignment(Pos.CENTER);
        inputRowMessage.setAlignment(Pos.CENTER);

        // 将控件添加到VBox中
        vBox.getChildren().addAll(inputRowDoctorId, inputRowMessage, sendButton, logArea);
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(10);

        // 创建场景，将VBox作为根节点
        Scene scene = new Scene(vBox);
        this.setTitle("给医生留言");
        this.setScene(scene);
    }
}
