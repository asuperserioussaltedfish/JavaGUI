package com.example.modules.manage;

import com.example.service.DoctorService;
import com.example.service.ManagePatientService;
import com.example.service.serviceImpl.DoctorServiceImpl;
import com.example.service.serviceImpl.ManagePatientServiceServiceImpl;
import com.example.utils.UserThreadLocal;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;

public class UpdatePasswordController {

    ManagePatientService managePatientService=new ManagePatientServiceServiceImpl();
    DoctorService doctorService=new DoctorServiceImpl();
    @FXML
    private TextField doctorName; // ID应该与FXML文件中的TextField组件匹配
    @FXML
    private TextField accountField; // ID应该与FXML文件中的TextField组件匹配

    @FXML
    private TextField oldPasswordField; // ID应该与FXML文件中的TextField组件匹配

    @FXML
    private TextField newPasswordField; // ID应该与FXML文件中的TextField组件匹配;


    public void upDataPassWord(MouseEvent event) {
        try {
            // 加载新的FXML文件
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/demo/UpdatePassword.fxml"));
            Parent root = loader.load();
            // 获取当前场景和舞台
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            // 设置新场景到舞台
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            // 处理异常
        }
    }

    public void yuyueClick(MouseEvent event) {
        try {
            // 加载新的FXML文件
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/demo/Appointment.fxml"));
            Parent root = loader.load();
            // 获取当前场景和舞台
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            // 设置新场景到舞台
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            // 处理异常
        }
    }

    @FXML
    public void initialize() {
        String doctorName = doctorService.getPatientByDoctorAccount(UserThreadLocal.get().getAccount()).get(0).getDoctorName();
        String account = doctorService.getPatientByDoctorAccount(UserThreadLocal.get().getAccount()).get(0).getAccount();
        // 设置TextField的默认值等
        this.doctorName.setText(doctorName);
        accountField.setText(account);

    }

    //    获取数据
    @FXML
    private void genggaipassword(MouseEvent event) {
        String account = accountField.getText(); // 获取账号TextField的文本
        String oldPassword = oldPasswordField.getText(); // 获取原密码TextField的文本
        String newPassword = newPasswordField.getText(); // 获取新密码TextField的文本
        if (oldPassword.equals(doctorService.getPatientByDoctorAccount(UserThreadLocal.get().getAccount()).get(0).getPassword())){
            doctorService.updatePassword(oldPassword,newPassword,doctorService.getPatientByDoctorAccount(UserThreadLocal.get().getAccount()).get(0).getId());
            JOptionPane.showMessageDialog(null, "密码更新成功！");
        }else{
            // 原密码不匹配的提示
            JOptionPane.showMessageDialog(null, "原密码不正确，请重新输入。");
        }
    }

    public void huanzheguanli(MouseEvent event) {
        try {
            // 加载新的FXML文件
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/demo/view1.fxml"));
            Parent root = loader.load();
            // 获取当前场景和舞台
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            // 设置新场景到舞台
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            // 处理异常
        }
    }

    public void tongzhi(MouseEvent event) {
        try {
            // 加载新的FXML文件
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/demo/notice.fxml"));
            Parent root = loader.load();
            // 获取当前场景和舞台
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            // 设置新场景到舞台
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            // 处理异常
        }
    }
}
