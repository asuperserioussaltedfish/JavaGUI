package com.example.modules.manage;

import com.example.pojo.Patient;
import com.example.service.ManagePatientService;
import com.example.service.serviceImpl.ManagePatientServiceServiceImpl;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 *
 * @author SaltedFish
 */
public class DetailController {
    ManagePatientService managePatientService=new ManagePatientServiceServiceImpl();
    private String patientName;
    @FXML
    private ChoiceBox<String> patientGenderChoiceBox;
    @FXML
    private TextField patientIdField;
    @FXML
    private TextField patientAccountField;
    @FXML
    private TextField patientNameField;
    @FXML
    private TextField patientAgeField;
    @FXML
    private TextField patientConditionField;
    @FXML
    private TextField patientPasswordField;
    @FXML
    private TextField patientTimeField;
    public DetailController(){

    }
    @FXML
    public void initialize() {
        List<Patient> patientByPatientName = managePatientService.getPatientByPatientName(patientName);
        // 获取到的数据
        patientIdField.setText(patientByPatientName.get(0).getPatientId().toString());
        patientAccountField.setText(patientByPatientName.get(0).getAccount());
        patientNameField.setText(patientByPatientName.get(0).getPatientName());
        patientAgeField.setText(patientByPatientName.get(0).getAge().toString());
        String illness = managePatientService.selectIllnessById(patientByPatientName.get(0).getPatientId()).get(0).getIllness();
        if (illness==null){
            illness="暂无信息";
        }
        patientConditionField.setText(illness);
        patientPasswordField.setText(patientByPatientName.get(0).getPassword());

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        // 使用SimpleDateFormat对象，将时间戳转换为Date对象
        Date date = new Date(managePatientService.selectIllnessById(patientByPatientName.get(0).getPatientId()).get(0).getIllnessTime());
        // 使用SimpleDateFormat对象的format方法来格式化Date对象为我们指定的日期字符串格式
        String formattedDate = sdf.format(date);
        patientTimeField.setText(formattedDate);
        patientGenderChoiceBox.setValue(patientByPatientName.get(0).getGender()); // 设置默认值
        patientGenderChoiceBox.setDisable(true); // 禁用 ChoiceBox
    }
    public void getPatientName(String patientName) {
        this.patientName = patientName;
        System.out.println(patientName);
    }
}
