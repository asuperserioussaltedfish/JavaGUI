package com.example.modules.login;

import com.example.pojo.Doctor;
import com.example.pojo.Patient;
import com.example.service.LoginService;
import com.example.service.serviceImpl.LoginServiceServiceImpl;
import com.example.vo.ErrorCode;
import com.example.vo.Result;
import javafx.animation.TranslateTransition;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;

/**
 *  登录按钮
 * @author SaltedFish
 */
public class LoginButton extends Button {
    Patient doctor =new Patient();
    LoginService loginService =new LoginServiceServiceImpl();
    LoginPasswordField loginPasswordField=new LoginPasswordField();
    LoginUsernameField loginUsernameField =new LoginUsernameField();
    /**跟踪登录状态，默认未登录*/
    BooleanProperty loginProperty = new SimpleBooleanProperty(false);
    public LoginButton(String text) {
        // 调用父类Button的构造方法，设置按钮上显示的文本
        super(text);
        //设置按钮最大尺寸
        this.setMaxSize(100,30);
        // 为按钮设置ID,便于在css文件中操作
        this.setId("login-button");
        // 添加CSS样式表
        this.getStylesheets().add(String.valueOf(getClass().getResource("/css/login/login.css")));
        // 为按钮添加鼠标点击事件处理器
        this.addEventHandler(MouseEvent.MOUSE_CLICKED,event -> {
            doctor.setAccount(loginUsernameField.getUsername());
            doctor.setPassword(loginPasswordField.getPassword());
            Result result = loginService.login(doctor);
            if (result.getCode()==200){
                System.out.println("成功");
                System.out.println("username："+ doctor.getAccount());
                System.out.println("password："+ doctor.getPassword());
                loginProperty.set(true);
            }else if(result.getCode()== ErrorCode.USERNAME_IS_NULL.getCode()){
                shakeLoginUsernameField(loginUsernameField);
            }else if (result.getCode()== ErrorCode.PASSWORD_IS_NULL.getCode()){
                shakeLoginPasswordField(loginPasswordField);
            }else {
                shakeLoginUsernameField(loginUsernameField);
                shakeLoginPasswordField(loginPasswordField);
                loginPasswordField.showErrorMessage("登录名或密码错误");
            }
        });
    }
    public void setLoginUsernameField(LoginUsernameField loginUsernameField){
        this.loginUsernameField=loginUsernameField;
    }
    public void setLoginPasswordField(LoginPasswordField loginPasswordField){
        this.loginPasswordField=loginPasswordField;
    }
    /**
     * 监听登录状态
     * @return BooleanProperty
     */
    public BooleanProperty loginProperty(){
        return loginProperty;
    }
    /**
        抖动用户名弹窗
     */
    private void shakeLoginUsernameField(LoginUsernameField control) {
        TranslateTransition tt = new TranslateTransition(Duration.millis(50), control);
        tt.setFromX(0);
        tt.setByX(10);
        tt.setCycleCount(6);
        tt.setAutoReverse(true);
        tt.playFromStart();
    }
    /**
     抖动密码弹窗
     */
    private void shakeLoginPasswordField(LoginPasswordField control) {
        TranslateTransition tt = new TranslateTransition(Duration.millis(50), control);
        tt.setFromX(0);
        tt.setByX(10);
        tt.setCycleCount(6);
        tt.setAutoReverse(true);
        tt.playFromStart();
    }
}
