package com.example.modules.manage;

import com.example.pojo.Message;
import com.example.pojo.Patient;
import com.example.service.DoctorService;
import com.example.service.ManagePatientService;
import com.example.service.serviceImpl.DoctorServiceImpl;
import com.example.service.serviceImpl.ManagePatientServiceServiceImpl;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class NoticeController {
    ManagePatientService managePatientService=new ManagePatientServiceServiceImpl();
    DoctorService doctorService=new DoctorServiceImpl();
    @FXML
    private TableView<Message> tableView; // 修改类型为TableView<Patient>

    @FXML
    private TableColumn<Message, String> patientIdColumn;
    @FXML
    private TableColumn<Message, String> nameColumn;
    @FXML
    private TableColumn<Message, String> genderColumn;
    @FXML
    private TableColumn<Message, String> moreInfoColumn;
    @FXML
    private TableColumn<Message, Void> caozuoInfoColumn; // 修改类型为TableColumn<Patient, Void>
    @FXML
    private Pagination pagination;

    @FXML
    private Text textd;
    @FXML
    private TextField inputsearch;

    @FXML
    private void initialize() {
        // 设置列和数据属性之间的关联
        patientIdColumn.setCellValueFactory(new PropertyValueFactory<>("senderId"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("receiverId"));
        genderColumn.setCellValueFactory(new PropertyValueFactory<>("messageTime"));
        moreInfoColumn.setCellValueFactory(new PropertyValueFactory<>("content"));
        //禁止列能够移动
        patientIdColumn.setReorderable(false);
        nameColumn.setReorderable(false);
        genderColumn.setReorderable(false);
        moreInfoColumn.setReorderable(false);
        caozuoInfoColumn.setReorderable(false);
        // 给操作模块加上删除按钮（删除逻辑在这写）
        caozuoInfoColumn.setCellFactory(column -> new TableCell<>() {

//            private final Button btn1 = new ManageButton("删除");
//            {
//                btn1.setOnAction(event -> {
//                    Patient patient = getTableView().getItems().get(getIndex());
//                    // 删除
//                    deleteDoctor(patient);
////                    tableView.getItems().remove(patient);
//                });
//            }
            private final Button btn2 = new ManageButton("回复");
            {
                btn2.setOnAction(event -> {
                    Message patient = getTableView().getItems().get(getIndex());
                        TextInputDialog dialog = new TextInputDialog();
                        dialog.setTitle("回复病人");
                        dialog.setHeaderText("请输入您的回复:");
                        dialog.setContentText("回复:");
                        // 显示对话框并等待回复
                        Optional<String> result = dialog.showAndWait();
                        // 处理回复内容
                        result.ifPresent(reply -> {
                            managePatientService.leaveMessageToPatient(doctorService.getPatientByDoctorAccount(UserThreadLocal.get().getAccount()).get(0).getId(), patient.getId(),reply, System.currentTimeMillis(),0);
                        });
                });
            }
            //HBOX布局
            private final HBox hBox = new HBox( btn2);
            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                //单元格内无内容  就是空
                if (empty) {
                    setGraphic(null);
                } else {
                    hBox.setAlignment(Pos.CENTER); // 设置HBox居中对齐
//                    btn1.setMaxWidth(Double.MAX_VALUE); // 设置按钮宽度最大，占满HBox
                    btn2.setMaxWidth(Double.MAX_VALUE); // 设置按钮宽度最大，占满HBox
                    // 设置HBox布局宽度同单元格宽度一样
                    hBox.setMaxWidth(Double.MAX_VALUE);
                    // 将按钮添加到HBox布局中，同时设置按钮拉伸至最大
//                    HBox.setHgrow(btn1, Priority.ALWAYS);
                    HBox.setHgrow(btn2, Priority.ALWAYS);
                    setGraphic(new HBox(btn2));
                }
            }
        });
        // 给更多模块添加一个双击触发的内容框，展示这名患者的详细信息
        moreInfoColumn.setCellFactory(column -> new TableCell<>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                    setGraphic(null);
                } else {
                    // 设定文本
                    setText(item);
                    // 为每个单元格添加点击事件
                    this.setOnMouseClicked(event -> {
                        // 双击打开一个窗口，该窗口展示该患者的基本信息
                        if (event.getClickCount() > 1) {
                            Message patient = getTableView().getItems().get(getIndex());
                            try {
                                showMoreInfo(patient);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
            }
        });
        List<Message> allPatients = managePatientService.retrieveMessagesForDoctor(doctorService.getPatientByDoctorAccount(UserThreadLocal.get().getAccount()).get(0).getId());
        ObservableList<Message> patientData = null;
        ArrayList list=new ArrayList();
        // 创建一个包含Patient对象的ObservableList
        for (Message patient:allPatients) {
            Message patient1=new Message(patient.getId(), patient.getContent(), patient.getReceiverId(),patient.getSenderId(),
                    patient.getMessageTime(),patient.getMessageStatus());
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
    // 实现删除Patient，
    private void deleteDoctor(Patient patient) {
        // 创建确认对话框
        Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmationAlert.setTitle("确认删除");
        confirmationAlert.setHeaderText(null);
        confirmationAlert.setContentText("您确定要删除该患者信息吗？");
        // 显示对话框并等待用户响应
        Optional<ButtonType> result = confirmationAlert.showAndWait();
        // 判断用户点击的是不是“确定”
        if (result.isPresent() && result.get() == ButtonType.OK) {
            // 如果用户确实点击了“确定”，执行删除操作
            managePatientService.deletePatient(patient.getPatientId());
        }
    }

    // 展示患者详细信息
    public void showMoreInfo(Message patient) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/demo/detail.fxml"));
        Parent root = loader.load();

        // 现在获取FXMLLoader关联的控制器实例。
        DetailController detailController = loader.getController();
        // 使用患者姓名作为参数调用getPatientName。
//        xiangxiController.getPatientName(patient.getPatientName());

        // 创建一个新的舞台
        Stage stage = new Stage();
        stage.setTitle("患者详细信息");
        stage.setScene(new Scene(root));

        // 显示舞台之前调用initialize或类似方法进行最终初始化。
        detailController.initialize();

        stage.show();
    }


    // 点击搜索栏搜索功能
    @FXML
    public void onSearchButtonAction(ActionEvent actionEvent) {
        String searchText = inputsearch.getText(); // 获取输入框的文本
        performSearch(searchText); // 执行搜索逻辑
    }

    // 执行搜索并更改数据
    private void performSearch(String searchText) {
        ArrayList<Message> objects = new ArrayList<>();
        Long patientId=managePatientService.getPatientByPatientName(searchText).get(0).getPatientId();
        for (Message patient:managePatientService.retrieveMessagesForDoctor1(doctorService.getPatientByDoctorAccount(UserThreadLocal.get().getAccount()).get(0).getId(),patientId)) {
            Message patient1=new Message(patient.getId(), patient.getContent(), patient.getReceiverId(),patient.getSenderId(),
                    patient.getMessageTime(),patient.getMessageStatus());
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
    public void addshowMoreInfo(MouseEvent mouseEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/demo/add.fxml"));
        Parent root = loader.load();
        // 创建一个新的舞台
        Stage stage = new Stage();
        stage.setTitle("添加患者");
        stage.setScene(new Scene(root));
        stage.show();
    }
    private void showReviseInfo(Patient patient) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/demo/update.fxml"));
        Parent root = loader.load();
        UpdateController UpdateController = new UpdateController();
        UpdateController.getPatient(patient);
        // 创建一个新的舞台
        Stage stage = new Stage();
        stage.setTitle("修改患者信息");
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void huanzheguanli(MouseEvent mouseEvent) {
        try {
            // 加载新的FXML文件
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/demo/view1.fxml"));
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
}