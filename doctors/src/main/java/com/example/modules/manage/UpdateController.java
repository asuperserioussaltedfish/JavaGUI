package com.example.modules.manage;

import com.example.pojo.Patient;
import com.example.service.ManagePatientService;
import com.example.service.serviceImpl.ManagePatientServiceServiceImpl;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import java.io.File;
import javafx.scene.control.TextField;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;

public class UpdateController {
    ManagePatientService managePatientService=new ManagePatientServiceServiceImpl();

    @FXML
    private ImageView imageView;
    @FXML
    private TextField patientIdField;

    @FXML
    private TextField patientAccountField;

    @FXML
    private TextField patientPasswordField;

    @FXML
    private TextField patientNameField;

    @FXML
    private TextField patientConditionField;

    @FXML
    private TextField patientAgeField;

    @FXML
    private ChoiceBox<String> patientGenderChoiceBox;
    @FXML
    private Button abc;

    private Patient patient123;

    public void getPatient(Patient patient) {
        this.patient123 = patient;
        System.out.println(345);
        System.out.println(patient.getId());
    }
    //    修改图片
    @FXML
    public void handleChangeImage() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("选择图片");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("所有图片", "*.png", "*.jpg", "*.jpeg", "*.bmp", "*.gif"),
                new FileChooser.ExtensionFilter("PNG", "*.png"),
                new FileChooser.ExtensionFilter("JPEG", "*.jpg", "*.jpeg"),
                new FileChooser.ExtensionFilter("BMP", "*.bmp"),
                new FileChooser.ExtensionFilter("GIF", "*.gif")
        );
        File file = fileChooser.showOpenDialog(imageView.getScene().getWindow());
        if (file != null) {
            Image image = new Image(file.toURI().toString());
            imageView.setImage(image);
        }
    }
    @FXML
    public void handleUpDataPatient() {
        patientIdField.setText("123");
        // 获取用户输入的数据
        Long patientId = Long.valueOf(patientIdField.getText());
        String patientAccount = patientAccountField.getText();
        String patientPassword = patientPasswordField.getText();
        String patientName = patientNameField.getText();
        String patientCondition = patientConditionField.getText();
        Integer patientAge = Integer.parseInt(patientAgeField.getText());
        String patientGender = patientGenderChoiceBox.getValue();
        Patient patient = new Patient(patientId, patientAccount, patientName, patientGender, patientAge, patientPassword);
        System.out.println(patient.getPatientId());
        //TODO:修改
        //TODO:修改
        //TODO:修改
//        managePatientService.updatePatient(patient,managePatientService.getPatientByPatientId(patient123.getPatientId()).get(0).getAccount());
//        managePatientService.updatePatient(patient,managePatientService.getPatientByPatientId(patient123.getPatientId()).get(0).getAccount());
        Stage stage = (Stage)abc.getScene().getWindow();
        stage.close();

////        获取的这个图片只是一个路径，需要将其转换为Image对象
//        System.out.println(imageView.getImage());
        // 修改数据之后弹出提示框
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("修改成功");
        alert.setHeaderText(null);
        alert.setContentText("患者信息已成功更新！");
        alert.showAndWait();
    }

}