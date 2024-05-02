package com.example.modules.patient;

import com.example.pojo.Doctor;
import com.example.pojo.Message;
import com.example.service.PatientService;
import com.example.service.serviceImpl.PatientServiceImpl;
import com.example.utils.UserThreadLocal;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.List;
//`SelectMessageStage` 类是一个 JavaFX 界面，用于患者查询和显示与医生相关的留言信息，
// 包含一个查询按钮和一个文本区域，用于展示查询到的留言详情。
/**
 * @author SaltedFish
 */
public class SelectMessageStage extends Stage {
    PatientService patientService=new PatientServiceImpl();
    TextArea logArea = new TextArea();
    public SelectMessageStage(){
        this.setMinWidth(1120);
        this.setMinHeight(630);
        BackgroundFill backgroundFill = new BackgroundFill(Color.rgb(89,118,186), null, null);
        Background background = new Background(backgroundFill);
        VBox vBox = new VBox();
        vBox.setBackground(background);

        // 创建查询按钮
        Button queryButton = new PatientButton("查询");
        // 为按钮设置点击事件监听器，点击时调用方法a
        queryButton.setOnAction(event -> {
            List<Message> messages = patientService.retrieveMessagesForDoctor(patientService.selectPatientByAccount("patient5").getId());
            logArea.appendText(formatDoctorInfo(messages)); // 使用格式化的信息更新logArea
        });
        // 将按钮和文本框添加到水平箱子中
        vBox.getChildren().addAll(queryButton);
        vBox.setAlignment(Pos.CENTER);
        vBox.setMinWidth(100);
        vBox.setMinHeight(100);
        vBox.setSpacing(30);
//        vBox.setMinWidth(300);
//        vBox.setMinHeight(300);
        // 创建场景，将水平箱子作为根节点
        vBox.getChildren().add(logArea);
        Scene scene = new Scene(vBox, 300, 100);
        // 设置舞台
        this.setTitle("查询医生留言");
        this.setScene(scene);

    }
    private String formatDoctorInfo(List<Message> messages) {
        StringBuilder sb = new StringBuilder();
        for (Message message : messages) {
                sb.append(String.format(
                        "id: %s\n内容: %s\n医生id: %s\n患者id: %s\n\n",
                        message.getId(),
                        message.getContent(),
                        message.getReceiverId(),
                        message.getSenderId()
                ));
            }
        return sb.toString();
    }
}