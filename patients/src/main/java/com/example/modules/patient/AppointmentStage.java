package com.example.modules.patient;

import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import com.example.service.PatientService;
import com.example.service.serviceImpl.PatientServiceImpl;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
//`AppointmentStage` 类是一个 JavaFX 界面类，用于实现病人预约医生的功能。它包含输入字段（医生编号和预约日期），一个预约按钮，
// 和一个文本区域用于显示操作结果。当用户输入信息并点击预约按钮时，它会调用后端服务进行预约处理，并在文本区域显示预约成功或失败的结果。
/**
 *
 * @author SaltedFish
 */

public class AppointmentStage extends Stage {
    private PatientService patientService = new PatientServiceImpl();
    private TextArea logArea = new TextArea();
    private Field doctorIdField = new Field();
    private DatePicker appointmentDatePicker = new DatePicker();

    public AppointmentStage() {
        this.setMinWidth(1120);
        this.setMinHeight(630);
        BackgroundFill backgroundFill = new BackgroundFill(Color.rgb(89, 118, 186), null, null);
        Background background = new Background(backgroundFill);
        VBox vBox = new VBox();
        vBox.setBackground(background);

        // 创建Label
        Label doctorIdLabel = new Label("医生编号:");
        Label appointmentDateLabel = new Label("预约时间:");

        // 创建预约按钮
        Button bookAppointmentButton = new Button("预约");

        // 为预约按钮设置点击事件监听器
        bookAppointmentButton.setOnAction(event -> {
            String doctorId = doctorIdField.getUsername().trim();
            LocalDate appointmentDate = appointmentDatePicker.getValue();
            if (doctorId.isEmpty() || appointmentDate == null) {
                logArea.appendText("医生编号和预约时间不能为空！\n");
            } else {
                // 将LocalDate转换为时间戳
                long timestamp = appointmentDate.atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli();

                // 调用patientService的bookAppointment方法，传入时间戳
                String result = a(patientService.bookAppointment(Long.valueOf(doctorId), 5L, timestamp));
                logArea.appendText("预约结果: " + result + "\n");
            }
        });

        // 创建HBox布局，添加labels和fields以及button
        HBox inputRowDoctorId = new HBox(10, doctorIdLabel, doctorIdField);
        HBox inputRowAppointmentDate = new HBox(10, appointmentDateLabel, appointmentDatePicker);
        inputRowDoctorId.setAlignment(Pos.CENTER);
        inputRowAppointmentDate.setAlignment(Pos.CENTER);

        // 将控件添加到VBox中
        vBox.getChildren().addAll(inputRowDoctorId, inputRowAppointmentDate, bookAppointmentButton, logArea);
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(10);

        // 创建场景，将VBox作为根节点
        Scene scene = new Scene(vBox);
        this.setTitle("预约医生");
        this.setScene(scene);
    }
    public String a(boolean a){
        if (a){
            return "预约成功";
        }else {
            return "预约失败";
        }
    }
}