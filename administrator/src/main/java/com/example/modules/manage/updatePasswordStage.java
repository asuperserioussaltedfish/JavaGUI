package com.example.modules.manage;

import com.example.pojo.Doctor;
import com.example.service.AdminService;
import com.example.service.ManageDoctorsService;
import com.example.service.serviceImpl.AdminServiceImpl;
import com.example.service.serviceImpl.ManageDoctorsServiceServiceImpl;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * @author SaltedFish
 */
public class updatePasswordStage extends Stage {
    AdminService adminService=new AdminServiceImpl();
    public updatePasswordStage() {
            // 创建弹出窗口
            Stage dialogStage = new Stage();
            dialogStage.setTitle("修改密码");
            GridPane grid = new GridPane();
            grid.setHgap(10);
            grid.setVgap(10);
            grid.setPadding(new Insets(20, 150, 10, 10));
            TextField accountField = new TextField();
            grid.add(accountField, 1, 0);
            TextField passwordField = new TextField();
            grid.add(new Label("原密码:"), 0, 1);
            grid.add(passwordField, 1, 1);
            TextField ageField = new TextField();
            grid.add(new Label("现密码:"), 0, 2);
            grid.add(ageField, 1, 2);

            // 创建提交按钮
            Button submitButton = new Button("确定修改");
            grid.add(submitButton, 1, 8);

            submitButton.setOnAction(e -> {
                // 从文本框中获取数据
                String password = passwordField.getText();
                String newPassword = ageField.getText();
                adminService.updatePassword(password,newPassword);

                // 关闭弹出窗口
                dialogStage.close();
            });

            // 设置场景和显示
            Scene scene = new Scene(grid);
            dialogStage.setScene(scene);
            dialogStage.show();
    }

}