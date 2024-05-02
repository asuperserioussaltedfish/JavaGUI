package com.example.modules.manage;

import com.example.service.ManageDoctorsService;
import com.example.service.serviceImpl.ManageDoctorsServiceServiceImpl;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.awt.event.ActionEvent;
import java.io.IOException;

/**
 * @author SaltedFish
 */
public class ManagePane extends BorderPane {
    ManageDoctorsService manageDoctorsService =new ManageDoctorsServiceServiceImpl();
    public ManagePane() {
        // 将图片设置为背景图片
        Image backgroundImage = new Image("/image/manage.png");
        BackgroundImage backgroundImg = new BackgroundImage(backgroundImage,
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, false));
        this.setBackground(new Background(backgroundImg));
        // 加载自定义字体
        Font.loadFont(getClass().getResourceAsStream("/icons/MV Boli.ttf"), 36);
        // 创建艺术字文本
        Text text = new Text("doctor manage");

        // 设置艺术字使用的字体、大小、颜色
        text.setFont(Font.font("MV Boli", 50));
        text.setFill(Paint.valueOf("#4A2500"));
        // 创建按钮
        GridPane gridPane = new GridPane();
        StackPane root = new StackPane(gridPane);
        Button addButton = new ManageButton("添加医生");
        addButton.setOnAction(event -> {
            new AddStage();
        });
        Button deleteButton = new ManageButton("搜索");
        deleteButton.setOnAction(event -> {
        });
        Button updatePassword = new ManageButton("修改密码");
        updatePassword.setOnAction(event -> {
            new updatePasswordStage();
        });
        Button zuozhen = new ManageButton("设定医生坐诊时间");
        zuozhen.setOnAction(event -> {
                // 创建一个新的 Stage
                Stage stage = new Stage();
                // 设置布局
                VBox layout = new VBox(10);
                layout.setAlignment(Pos.CENTER);
                // 创建输入框
                TextField timeStartInput = new TextField();
                timeStartInput.setPromptText("输入坐诊开始时间");
                TextField timeEndInput = new TextField();
                timeEndInput.setPromptText("输入坐诊结束时间");
                TextField doctorId = new TextField();
                timeEndInput.setPromptText("输入医生编号");
                // 创建提交按钮
                Button submitButton = new Button("提交");
                submitButton.setOnAction(e -> {
                    try {
                        Long timeStart = Long.parseLong(timeStartInput.getText());
                        Long timeEnd = Long.parseLong(timeEndInput.getText());
                        manageDoctorsService.updateDoctorSchedule(timeStart,timeEnd,Long.valueOf(doctorId.getText()));
                    } catch (NumberFormatException ex) {
                        System.out.println("请输入有效的时间戳");
                    }
                });
                // 添加组件到布局
                layout.getChildren().addAll(timeStartInput, timeEndInput,doctorId, submitButton);
                // 设置场景和舞台
                Scene scene = new Scene(layout, 300, 200);
                stage.setTitle("设置医生坐诊时间");
                stage.setScene(scene);
                stage.show();
        });
        // 实现水平排列,10像素的间隙
        HBox buttonBox = new HBox(10);
        buttonBox.getChildren().addAll(addButton, deleteButton,updatePassword,zuozhen);
        // 居中对齐
        buttonBox.setAlignment(Pos.CENTER);
        // HBox内边距
        buttonBox.setPadding(new Insets(10));
        VBox topContainer = new VBox(5);
        topContainer.getChildren().addAll(text, buttonBox);
        topContainer.setAlignment(Pos.CENTER);
        this.setTop(topContainer);
        TableView manageTableView = new TableView();
        manageTableView.getStylesheets().add(String.valueOf(getClass().getResource("/css/manage/manage.css")));
        this.setCenter(manageTableView);
        Label doctorManagementLabel = new Label("");
        VBox leftColumn = new VBox(doctorManagementLabel);
        leftColumn.setSpacing(10);
        leftColumn.setPadding(new Insets(130));
        leftColumn.setAlignment(Pos.TOP_CENTER);
        this.setLeft(leftColumn);
    }
    public static void display() {
    }
}