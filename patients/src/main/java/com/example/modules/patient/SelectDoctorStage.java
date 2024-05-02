package com.example.modules.patient;

import com.example.pojo.Doctor;
import com.example.service.PatientService;
import com.example.service.serviceImpl.PatientServiceImpl;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
//`SelectDoctorStage` 类是一个 JavaFX 界面，用于查询和展示医生信息
// 包括一个文本输入框用于输入医生姓名、一个查询按钮，以及一个文本区域用于显示查询到的医生详细信息。

/**
 * @author SaltedFish
 */
public class SelectDoctorStage extends Stage {
    PatientService patientService=new PatientServiceImpl();
    TextArea logArea = new TextArea();
    public SelectDoctorStage(){
        this.setMinWidth(1120);
        this.setMinHeight(630);
        BackgroundFill backgroundFill = new BackgroundFill(Color.rgb(89,118,186), null, null);
        Background background = new Background(backgroundFill);
        VBox vBox = new VBox();
        vBox.setBackground(background);

        // 创建查询按钮
        Button queryButton = new PatientButton("查询");
        // 创建文本框用于输入查询信息
        Field queryTextField = new Field();
        // 为按钮设置点击事件监听器，点击时调用方法a
        queryButton.setOnAction(event -> {
            Doctor doctorInfo = patientService.selectDoctorByName(queryTextField.getUsername());
            if (doctorInfo != null) {
                logArea.appendText(formatDoctorInfo(doctorInfo)); // 使用格式化的信息更新logArea
            } else {
                logArea.appendText("没找到该名字：" + queryTextField.getUsername() + "\n\n");
            }
        });
        // 将按钮和文本框添加到水平箱子中
        vBox.getChildren().addAll(queryButton, queryTextField);
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
        this.setTitle("查询医生信息");
        this.setScene(scene);

    }
    private String formatDoctorInfo(Doctor doctor) {
        return String.format(
                "医生姓名: %s\n部门: %s\n学历: %s\n性别: %s\n年龄: %s\n\n",
                doctor.getDoctorName(),
                doctor.getDepartment(),
                doctor.getEducation(),
                a(doctor.getGender()),
                doctor.getAge()
        );
    }
    private String a(String a){
        if ("1".equals(a)){
            return "男";
        }else{
            return "女";
        }
    }
}
