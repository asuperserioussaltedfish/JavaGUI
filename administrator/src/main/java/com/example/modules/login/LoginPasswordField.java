package com.example.modules.login;

import com.example.utils.Colors;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

public class LoginPasswordField extends BorderPane {
    /**css文件*/
    private static final String LOGIN_CSS = "/css/login/login.css";
    /**UI组件*/
    private HBox hBox;
    private final Label lock = new Label();
    private final Label eye = new Label();
    /**隐藏文本的密码输入字段*/
    private final  PasswordField passwordField = new PasswordField();
    /**显示密码文本*/
    private final TextField textField = new TextField();
    /**密码输入区域布局*/
    private final BorderPane passwordMainField = new BorderPane();
    /**表示密码是否可见*/
    boolean isVisible = false;
    public LoginPasswordField(){
        //改bug
        // 添加给textField鼠标进入的事件，使提示文字颜色变化
        textField.addEventHandler(MouseEvent.MOUSE_ENTERED, event -> {
            if (!textField.isFocused()) {
                textField.setStyle("-fx-prompt-text-fill: #FFFFCC");
            }
        });
        // 恢复提示文字颜色
        textField.addEventHandler(MouseEvent.MOUSE_EXITED, event -> {
            if (!textField.isFocused()) {
                textField.setStyle("-fx-prompt-text-fill: #4A2500");
            }
        });

        passwordMainField.setMaxSize(280,40);
        passwordMainField.setId("username-field");
        passwordMainField.getStylesheets().add(String.valueOf(getClass().getResource(LOGIN_CSS)));
        // 监听器,反映悬停状态
        passwordMainField.addEventHandler(MouseEvent.MOUSE_ENTERED, event -> {
            if(!passwordField.isFocused() & !textField.isFocused()){
                // 更改边框颜色、图标文本填充、提示文本颜色
                passwordMainField.setStyle("-fx-border-color: #FFFFCC");
                lock.setStyle("-fx-text-fill: #FFFFCC");
                passwordField.setStyle("-fx-prompt-text-fill: #FFFFCC");
            }
        });
        // 监听器，鼠标离开时恢复原样式
        passwordMainField.addEventHandler(MouseEvent.MOUSE_EXITED, event -> {
            if(!passwordField.isFocused() & !textField.isFocused()){
                passwordMainField.setStyle("-fx-border-color: #4A2500");
                lock.setStyle("-fx-text-fill: #4A2500");
                passwordField.setStyle("-fx-prompt-text-fill: #4A2500");
            }
        });
        // 加载字体图标
        Font font = Font.loadFont(getClass().getResourceAsStream("/icons/icomoon.ttf"),28);
        // 设定锁图标的样式
        lock.setFont(font);
        lock.setText("\ue904");
        lock.setTextFill(Colors.GRAY);
        lock.setId("icon");
        lock.getStylesheets().add(String.valueOf(getClass().getResource(LOGIN_CSS)));
        // 设置分割线样式，与锁图标颜色绑定
        Rectangle line = new Rectangle(1.5, 26);
        line.setFill(Colors.GRAY);
        line.fillProperty().bind(lock.textFillProperty());
        // 设置眼睛图标样式，与锁颜色绑定
        eye.setFont(Font.font(font.getFamily(),20));
        eye.setText("\ue905");
        eye.textFillProperty().bind(lock.textFillProperty());
        // 点击眼睛图标切换密码是否可见
        eye.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            if (!isVisible) {

                // 密码显示时的样式设置
                eye.setText("\ue900");
                // 同步textField和passwordField，并更新样式
                textField.setText(passwordField.getText());
                textField.setPrefHeight(40);
                textField.setId("text-field");
                textField.getStylesheets().add(String.valueOf(getClass().getResource(LOGIN_CSS)));
                hBox.getChildren().set(2, textField);

                isVisible = true;
                textField.requestFocus();
                textField.positionCaret(textField.getText().length());

                // 更新显示密码时的边框颜色和提示文本颜色
                passwordMainField.setStyle("-fx-border-color: #FFFFCC");
                lock.setStyle("-fx-text-fill: #FFFFCC");
                textField.setStyle("-fx-prompt-text-fill: #FFFFCC");
                textField.focusedProperty().addListener(((observable, oldValue, newValue) -> {
                    if(newValue) {
                        textField.setPromptText("");
                        passwordMainField.setStyle("-fx-border-color: #FFFFCC");
                        lock.setStyle("-fx-text-fill: #FFFFCC");
                    } else {
                        textField.setPromptText("请输入密码");
                        textField.setStyle("-fx-prompt-text-fill: #4A2500");
                        passwordField.setStyle("-fx-prompt-text-fill: #4A2500");
                        passwordMainField.setStyle("-fx-border-color: #4A2500");
                        lock.setStyle("-fx-text-fill: #4A2500");
                    }
                }));
            } else {
                // 密码隐藏时的样式设置
                eye.setText("\ue905");
                // 同步passwordField和textField，并更新样式
                passwordField.setText(textField.getText());
                hBox.getChildren().set(2, passwordField);
                isVisible = false;
                passwordField.requestFocus();
                passwordField.positionCaret(passwordField.getText().length());
            }
        });
        // 设定密码字段的样式和提示文本
        passwordField.setPrefHeight(40);
        passwordField.setPromptText("请输入密码");
        passwordField.setId("text-field");
        passwordField.getStylesheets().add(String.valueOf(getClass().getResource(LOGIN_CSS)));
        passwordField.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue) {
                passwordField.setPromptText("");
                passwordMainField.setStyle("-fx-border-color: #FFFFCC");
                lock.setStyle("-fx-text-fill: #FFFFCC");
            } else {
                passwordField.setPromptText("请输入密码");
                passwordField.setStyle("-fx-prompt-text-fill: #4A2500");
                passwordMainField.setStyle("-fx-border-color: #4A2500");
                lock.setStyle("-fx-text-fill: #4A2500");
            }
        });
        // 设置水平布局，并添加进BorderPane的中心位置
        hBox = new HBox(lock, line, passwordField,eye);
        hBox.setAlignment(Pos.CENTER_LEFT);
        passwordMainField.setCenter(hBox);
        // 设置标签和VBox布局的样式
        Label label = new Label("Password");
        label.setId("label");
        VBox vBox = new VBox(label, passwordMainField);
        vBox.setAlignment(Pos.CENTER_LEFT);
        this.setMaxSize(280,50);
        this.setCenter(vBox);
        //错误密码提示
        vBox.getChildren().add(errorMessageLabel);
        // 默认不显示
        errorMessageLabel.setVisible(false);
        errorMessageLabel.setStyle("-fx-text-fill: #FF3838;");
    }
    public String getPassword(){
        return isVisible ? textField.getText() : passwordField.getText();
    }
    private final Label errorMessageLabel = new Label();
    /**
     *     展示错误信息
     */
    public void showErrorMessage(String message) {
        errorMessageLabel.setText(message);
        errorMessageLabel.setVisible(true);
    }

}
