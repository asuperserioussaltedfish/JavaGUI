package com.example.modules.manage;

import com.example.pojo.Appointment;
import com.example.pojo.Patient;
import com.example.service.DoctorService;
import com.example.service.ManagePatientService;
import com.example.service.serviceImpl.DoctorServiceImpl;
import com.example.service.serviceImpl.ManagePatientServiceServiceImpl;
import com.example.utils.DatabaseUtil;
import com.example.utils.UserThreadLocal;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AppointmentController {
    ManagePatientService managePatientService=new ManagePatientServiceServiceImpl();
    @FXML
    private TableView<Appointment> tableView; // 修改类型为TableView<Patient>
    DoctorService doctorService=new DoctorServiceImpl();
    @FXML
    private TableColumn<Appointment, String> patientIdColumn;
    @FXML
    private TableColumn<Appointment, String> nameColumn;
    @FXML
    private TableColumn<Appointment, String> genderColumn;
    @FXML
    private TableColumn<Appointment, String> moreInfoColumn;
    @FXML
    private TableColumn<Appointment, Void> caozuoInfoColumn; // 修改类型为TableColumn<Patient, Void>
    @FXML
    private Pagination pagination;

    @FXML
    private Text textd;
    @FXML
    private TextField inputsearch;

    @FXML
    private void initialize() {
        // 设置列和数据属性之间的关联
        patientIdColumn.setCellValueFactory(new PropertyValueFactory<>("doctorId"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("patientId"));
        genderColumn.setCellValueFactory(new PropertyValueFactory<>("appointmentTime"));
        moreInfoColumn.setCellValueFactory(new PropertyValueFactory<>("appointment_status"));
        //禁止列能够移动
        patientIdColumn.setReorderable(false);
        nameColumn.setReorderable(false);
        genderColumn.setReorderable(false);
        moreInfoColumn.setReorderable(false);
        caozuoInfoColumn.setReorderable(false);
        // 给操作模块加上删除按钮（删除逻辑在这写）
        caozuoInfoColumn.setCellFactory(column -> new TableCell<>() {

            private final Button btn1 = new ManageButton("取消预约");
            {
                btn1.setOnAction(event -> {
                    Appointment patient = getTableView().getItems().get(getIndex());
                    // 删除
//                    cancelAppointment( managePatientService.getAppointmentsByDoctor2(
//                            doctorService.getPatientByDoctorAccount(
//                                    UserThreadLocal.get().getAccount()).get(0).getId(),
//                            patient.getPatientId()).get(0).getId());
//                    tableView.getItems().remove(patient);
                });
            }
            private final Button btn2 = new ManageButton("接受预约");
            {
                btn2.setOnAction(event -> {
                    Appointment patient = getTableView().getItems().get(getIndex());
//                    approveAppointment(
//                            managePatientService.getAppointmentsByDoctor2(
//                                    doctorService.getPatientByDoctorAccount(
//                                            UserThreadLocal.get().getAccount()).get(0).getId(),
//                                    patient.getPatientId()).get(0).getId());
                });
            }
            //HBOX布局
            private final HBox hBox = new HBox(btn1, btn2);
            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                //单元格内无内容  就是空
                if (empty) {
                    setGraphic(null);
                } else {
                    hBox.setAlignment(Pos.CENTER); // 设置HBox居中对齐
                    btn1.setMaxWidth(Double.MAX_VALUE); // 设置按钮宽度最大，占满HBox
                    btn2.setMaxWidth(Double.MAX_VALUE); // 设置按钮宽度最大，占满HBox
                    // 设置HBox布局宽度同单元格宽度一样
                    hBox.setMaxWidth(Double.MAX_VALUE);
                    // 将按钮添加到HBox布局中，同时设置按钮拉伸至最大
                    HBox.setHgrow(btn1, Priority.ALWAYS);
                    HBox.setHgrow(btn2, Priority.ALWAYS);
                    setGraphic(new HBox(btn1, btn2));
                }
            }
        });
//        // 这里应该是预约状态
//        moreInfoColumn.setCellFactory(column -> new TableCell<>() {
//            @Override
//            protected void updateItem(String item, boolean empty) {
//                super.updateItem(item, empty);
//                if (empty || item == null) {
//                    setText(null);
//                    setGraphic(null);
//                } else {
//                    // 设定文本
//                    setText(item);
//                    // 为每个单元格添加点击事件
//                }
//            }
//        });
        List<Appointment> allPatients = managePatientService.getAppointmentsByDoctor(doctorService.getPatientByDoctorAccount(UserThreadLocal.get().getAccount()).get(0).getId());

        ObservableList<Appointment> patientData = null;
        ArrayList list=new ArrayList();
        // 创建一个包含Patient对象的ObservableList
        for (Appointment patient:allPatients) {
            System.out.println(patient.getPatientId());
            Appointment patient1=new Appointment(patient.getPatientId(), doctorService.getPatientByDoctorAccount(UserThreadLocal.get().getAccount()).get(0).getId(),System.currentTimeMillis(),0);
            list.add(patient1);

        }
        patientData = FXCollections.observableArrayList(list);
        // 失焦
        Platform.runLater(() -> {
            // 将焦点传递给根Pane或Scene的根节点
            tableView.requestFocus();
        });
        tableView.setItems(patientData);
    }
    private final ObservableList<Patient> data = FXCollections.observableArrayList();

    // 更改预约信息状态：取消预约
    public void cancelAppointment(Long appointmentId) {
        String sql = "UPDATE appointments SET appointment_status = ? WHERE appointment_id = ?";
        executeUpdateAppointmentStatus(appointmentId, -1);
    }

    // 更改预约信息状态：同意预约
    public void approveAppointment(Long appointmentId) {
        String sql = "UPDATE appointments SET appointment_status = ? WHERE appointment_id = ?";
        executeUpdateAppointmentStatus(appointmentId, 1);
    }

    // 执行更新预约状态的通用方法
    private void executeUpdateAppointmentStatus(Long appointmentId, int status) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = DatabaseUtil.getConnection();
            String sql = "UPDATE appointments SET appointment_status = ? WHERE appointment_id = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, status);
            pstmt.setLong(2, appointmentId);

            int affectedRows = pstmt.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Updating appointment failed, no rows affected.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            DatabaseUtil.closeConnection(conn);
        }
    }

    // 点击搜索栏搜索功能
    @FXML
    public void onSearchButtonAction(ActionEvent actionEvent) {
        String searchText = inputsearch.getText(); // 获取输入框的文本
        performSearch(searchText); // 执行搜索逻辑
    }

    // 执行搜索并更改数据
    private void performSearch(String searchText) {
        ArrayList<Appointment> objects = new ArrayList<>();
        for (Appointment patient : managePatientService.getAppointmentsByDoctor1( doctorService.getPatientByDoctorAccount(UserThreadLocal.get().getAccount()).get(0).getId(),searchText)) {
            System.out.println(patient.getPatientId());
            Appointment patient1=new Appointment(patient.getPatientId(), doctorService.getPatientByDoctorAccount(UserThreadLocal.get().getAccount()).get(0).getId(),System.currentTimeMillis(),0);
            objects.add(patient1);
        }
        tableView.setItems(FXCollections.observableArrayList(objects));
        System.out.println("执行搜索，搜索内容为: " + searchText);
    }
    public void yuyueClick(MouseEvent event) {
        try {
            // 加载新的FXML文件
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/demo/Appointment.fxml"));
            Parent root = loader.load();

            // 获取当前场景和舞台
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            // 设置新场景到舞台
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            // 处理异常
        }
    }

    public void tongzhiclick(MouseEvent mouseEvent) {
        try {
            // 加载新的FXML文件
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/demo/notice.fxml"));
            Parent root = loader.load();

            // 获取当前场景和舞台
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();

            // 设置新场景到舞台
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            // 处理异常
        }
    }


    public void upDataPassWord(MouseEvent event) {
        try {
            // 加载新的FXML文件
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/demo/UpdatePassword.fxml"));
            Parent root = loader.load();
            // 获取当前场景和舞台
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            // 设置新场景到舞台
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            // 处理异常
        }
    }

    public void tongzhi(MouseEvent event) {
        try {
            // 加载新的FXML文件
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/demo/notice.fxml"));
            Parent root = loader.load();
            // 获取当前场景和舞台
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            // 设置新场景到舞台
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            // 处理异常
        }
    }

    public void huanzheguanli(MouseEvent event) {
        try {
            // 加载新的FXML文件
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/demo/view1.fxml"));
            Parent root = loader.load();
            // 获取当前场景和舞台
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            // 设置新场景到舞台
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            // 处理异常
        }
    }
}