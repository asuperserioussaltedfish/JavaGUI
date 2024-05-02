package com.example.modules.manage;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * @author SaltedFish
 */
public class ManageStage extends Stage {

    public ManageStage() {
        initUI();
    }
    private void initUI() {

        this.setTitle("医生管理");
        // 创建布局
        BorderPane borderPane = new BorderPane();
        // 创建内容面板
        ManagePane managePane=new ManagePane();

        // 设置布局
        borderPane.setCenter(managePane);
        // 创建场景，指定布局和大小
        Scene scene = new Scene(borderPane, 1120,630);
        // 应用样式表
        scene.getStylesheets().add("/css/manage/stage.css");
        // 设置场景
        setScene(scene);
        // 设置标题
        setTitle("管理员管理");
        // 设置最小尺寸
        setMinWidth(1120);
        setMinHeight(630);
        // 禁止窗口缩放
        setResizable(false);
    }

}