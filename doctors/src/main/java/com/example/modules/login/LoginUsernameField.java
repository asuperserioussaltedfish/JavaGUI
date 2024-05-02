package com.example.modules.login;

import com.example.utils.Colors;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

public class LoginUsernameField extends BorderPane {
    HBox hBox;
    /**创建一个文本输入框*/
    TextField textField = new TextField();
    /**创建线型图形，用于装饰*/
    Rectangle line;
    /**创建标签，用作图标显示*/
    Label icon = new Label();
    /**创建边界布局面板，用于自定义输入框样式*/
    BorderPane usernameField = new BorderPane();
    public LoginUsernameField(){
        usernameField.setMaxSize(280,40);
        usernameField.setId("username-field");
        usernameField.getStylesheets().add(String.valueOf(getClass().getResource("/css/login/login.css")));
        // 添加鼠标进入事件处理程序，改变样式以反映悬停状态
        usernameField.addEventHandler(MouseEvent.MOUSE_ENTERED,event -> {
            // 如果文本框未获得焦点
            if(!textField.isFocused()){
                // 更改边框颜色、图标文本填充和提示文本颜色w
                usernameField.setStyle("-fx-border-color:#E0CEFF");
                icon.setStyle("-fx-text-fill: #E0CEFF");
                textField.setStyle("-fx-prompt-text-fill: #E0CEFF");
            }
        });
        // 添加鼠标退出事件处理程序，当鼠标离开时恢复原样式
        usernameField.addEventHandler(MouseEvent.MOUSE_EXITED,event -> {
            if(!textField.isFocused()){
                usernameField.setStyle("-fx-border-color: #4A004A");
                icon.setStyle("-fx-text-fill: #4A004A");
                textField.setStyle("-fx-prompt-text-fill: #4A004A");
            }
        });
        // 加载字体图标
        Font font = Font.loadFont(getClass().getResourceAsStream("/icons/icomoon.ttf"),28);
        // 设置图标的字体和内容
        icon.setFont(font);
        icon.setText("\ue908");
        icon.setTextFill(Colors.GRAY);
        icon.setId("icon");
        // 添加样式文件到图标
        icon.getStylesheets().add(String.valueOf(getClass().getResource("/css/login/login.css")));
        // 创建线型装饰图形，并且绑定图标的颜色属性
        line = new Rectangle(1.5,26);
        line.setFill(Colors.GRAY);
        line.fillProperty().bind(icon.textFillProperty());
        // 设置文本框高度和提示文本
        textField.setPrefHeight(40);
        textField.setPromptText("请输入用户名");
        textField.setId("text-field");
        textField.getStylesheets().add(String.valueOf(getClass().getResource("/css/login/login.css")));
        //监听器
        textField.focusedProperty().addListener((observable, oldValue, newValue) -> {
            // 当文本框获得焦点时
            if(newValue) {
                textField.setPromptText("");
                usernameField.setStyle("-fx-border-color: #E0CEFF");
                icon.setStyle("-fx-text-fill: #E0CEFF");
            } else{
                textField.setPromptText("请输入用户名");
                textField.setStyle("-fx-prompt-text-fill: #4A004A");
                usernameField.setStyle("-fx-border-color: #4A004A");
                icon.setStyle("-fx-text-fill: #4A004A");
            }
        });
        // 将图标、装饰线、文本框加入到HBox布局容器中
        hBox = new HBox(icon,line,textField);
        hBox.setAlignment(Pos.CENTER_LEFT);
        // 将HBox放入usernameField的中心
        usernameField.setCenter(hBox);
        // 创建一个标签用于显示"Username"
        Label label = new Label("Username");
        label.setId("label");
        // 将标签和usernameField放入到VBox布局容器中
        VBox vBox = new VBox(label,usernameField);
        vBox.setAlignment(Pos.CENTER_LEFT);
        // 设置自定义的UsernameField的最大尺寸并将VBox放入中心
        this.setMaxSize(280,50);
        this.setCenter(vBox);

    }
    public String getUsername(){
        return textField.getText();
    }
}
