package com.example.modules.manage;

import com.example.pojo.Doctor;
import com.example.service.ManageDoctorsService;
import com.example.service.serviceImpl.ManageDoctorsServiceServiceImpl;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * @author SaltedFish
 */
public class AddStage extends Stage {
    ManageDoctorsService manageDoctorsService = new ManageDoctorsServiceServiceImpl();
    public AddStage() {
            // 创建弹出窗口
            Stage dialogStage = new Stage();
            dialogStage.setTitle("添加医生信息");

            // 使用GridPane布局
            GridPane grid = new GridPane();
            grid.setHgap(10);
            grid.setVgap(10);
            grid.setPadding(new Insets(20, 150, 10, 10));

            // 创建标签和文本框
            TextField accountField = new TextField();
            grid.add(new Label("账号:"), 0, 0);
            grid.add(accountField, 1, 0);

            TextField passwordField = new TextField();
            grid.add(new Label("密码:"), 0, 1);
            grid.add(passwordField, 1, 1);

            TextField ageField = new TextField();
            grid.add(new Label("年龄:"), 0, 2);
            grid.add(ageField, 1, 2);

            TextField genderField = new TextField();
            grid.add(new Label("性别:"), 0, 3);
            grid.add(genderField, 1, 3);

            TextField educationField = new TextField();
            grid.add(new Label("学历:"), 0, 4);
            grid.add(educationField, 1, 4);

            TextField departmentField = new TextField();
            grid.add(new Label("部门:"), 0, 5);
            grid.add(departmentField, 1, 5);

            TextField doctorNameField = new TextField();
            grid.add(new Label("医生姓名:"), 0, 6);
            grid.add(doctorNameField, 1, 6);

            TextField avatarField = new TextField();
            grid.add(new Label("头像:"), 0, 7);
            grid.add(avatarField, 1, 7);

            // 创建提交按钮
            Button submitButton = new Button("确定添加");
            grid.add(submitButton, 1, 8);

            submitButton.setOnAction(e -> {
                // 从文本框中获取数据
                String account = accountField.getText();
                String password = passwordField.getText();
                Integer age = Integer.parseInt(ageField.getText());
                String gender = genderField.getText();
                String education = educationField.getText();
                String department = departmentField.getText();
                String doctorName = doctorNameField.getText();
                String avatar = avatarField.getText();

                manageDoctorsService.addDoctor(new Doctor(account, password, gender, age, education, department, doctorName));

                // 关闭弹出窗口
                dialogStage.close();
            });

            // 设置场景和显示
            Scene scene = new Scene(grid);
            dialogStage.setScene(scene);
            dialogStage.show();
    }

}