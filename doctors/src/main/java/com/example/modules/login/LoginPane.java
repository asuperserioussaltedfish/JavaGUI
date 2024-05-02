package com.example.modules.login;

import javafx.beans.property.BooleanProperty;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 * 登录面板
 * @author SaltedFish
 */
public class LoginPane extends BorderPane {
    /**登录状态  BooleanProperty-->监听绑定属性变化 */
    BooleanProperty loginProperty;
    public LoginPane(){
        // 加载自定义字体
        Font.loadFont(getClass().getResourceAsStream("/icons/MV Boli.ttf"), 36);
        // 创建艺术字文本
        Text text = new Text("doctor login");

        // 设置艺术字使用的字体、大小、颜色
        text.setFont(Font.font("MV Boli", 50));
        text.setFill(Paint.valueOf("#4A004A"));

        // 将图片设置为背景图片
        Image backgroundImage = new Image("image/login.jpg");
        BackgroundImage backgroundImg = new BackgroundImage(backgroundImage,
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, false));

        this.setBackground(new Background(backgroundImg));
        // 设置用户名输入框
        LoginUsernameField loginUsernameField = new LoginUsernameField();
        // 设置密码输入框
        LoginPasswordField loginPasswordField = new LoginPasswordField();
        // 设置登录按钮
        LoginButton loginButton = new LoginButton("登录");
        loginButton.setLoginUsernameField(loginUsernameField);
        loginButton.setLoginPasswordField(loginPasswordField);
        // 将登录按钮的登录属性保存到loginProperty
        loginProperty = loginButton.loginProperty();
        // 创建一个垂直盒子,添加文本、用户名、密码、登录控件

        VBox vBox = new VBox(text,loginUsernameField,loginPasswordField,loginButton);
        //设置vBox子元素间距
        vBox.setSpacing(29);
        //vBox内容居中显示
        vBox.setAlignment(Pos.CENTER);
        vBox.setMinWidth(500);
        this.setLeft(vBox);
        // 设置LoginPane最大尺寸
        vBox.setMaxSize(630,1120);
    }
    /**
     * 监听登录状态
     * @return BooleanProperty
     */
    public BooleanProperty loginProperty(){
        return loginProperty;
    }
}
