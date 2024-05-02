package com.example.modules.manage;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.control.Button;

/**
 *  登录按钮
 * @author SaltedFish
 */
public class ManageButton extends Button {
        /**跟踪登录状态，默认未登录*/
        BooleanProperty loginProperty = new SimpleBooleanProperty(false);
        public ManageButton(String text) {
            super(text);
            this.setMaxSize(100,30);
            this.setId("login-button");
            this.getStylesheets().add(String.valueOf(getClass().getResource("/com/example/demo/login.css")));
        }
}
