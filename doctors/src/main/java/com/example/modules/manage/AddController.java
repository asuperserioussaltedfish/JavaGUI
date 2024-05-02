package com.example.modules.manage;

import com.example.pojo.Patient;
import com.example.pojo.PatientIllness;
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

public class AddController {
    ManagePatientService managePatientService =new ManagePatientServiceServiceImpl();
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
//   添加图片
    @FXML
    private void handleChangeImage() {
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
    private void handleAddPatient() {
        // 获取用户输入的数据

//        Long patientId = Long.valueOf(patientIdField.getText());
        String patientAccount = patientAccountField.getText();
        String patientPassword = patientPasswordField.getText();
        String patientName = patientNameField.getText();
        String patientCondition = patientConditionField.getText();
        int patientAge = Integer.parseInt(patientAgeField.getText());
        String patientGender = patientGenderChoiceBox.getValue();
        Patient patient = new Patient(Long.valueOf(1),patientAccount,patientName,patientGender,patientAge,patientPassword);
        managePatientService.addPatient(patient);

        managePatientService.addIllness(new PatientIllness(managePatientService.getPatientByPatientName(patientName).get(0).getPatientId(),System.currentTimeMillis(),patientCondition));
        Stage stage = (Stage)abc.getScene().getWindow();
        stage.close();
        // 弹出提示信息对话框
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("添加成功");
        alert.setHeaderText(null);
        alert.setContentText("患者信息已成功添加！");
        alert.showAndWait();
    }

}