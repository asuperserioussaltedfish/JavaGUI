package com.example.modules.manage;

import com.example.pojo.Column;
import com.example.pojo.Doctor;
import com.example.service.ManageDoctorsService;
import com.example.service.serviceImpl.ManageDoctorsServiceServiceImpl;
import com.example.vo.Result;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;
import javafx.util.Callback;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class TableView extends StackPane {
    private ManageDoctorsService manageDoctorsService = new ManageDoctorsServiceServiceImpl();
    com.example.utils.TableView tableView=new com.example.utils.TableView();
    public TableView() {
        getChildren().add(tableView);
        setPadding(new Insets(20));
        List<Column> columns = Arrays.asList(
                new Column("医生编码", "id"),
                new Column("姓名", "doctorName"),
                new Column("性别", "gender"),
                new Column("学历", "education"),
                new Column("年龄", "age")
        );
        TableColumn<Doctor, Void> deleteColumn = new TableColumn<>("删除");
        Callback<TableColumn<Doctor, Void>, TableCell<Doctor, Void>> cellFactory = new Callback<>() {
            @Override
            public TableCell<Doctor, Void> call(final TableColumn<Doctor, Void> param) {
                // 创建一个自定义的TableCell来持有按钮
                final TableCell<Doctor, Void> cell = new TableCell<>() {
                    // 创建按钮实例
                    private final Button btn = new ManageButton("删除");
                    { // 初始化块，为按钮设置点击事件的处理程序
                        btn.setOnAction((event) -> {
                            Doctor data = getTableView().getItems().get(getIndex());
                            Result isSuccess = manageDoctorsService.deleteDoctor(data.getId());
                            if (isSuccess.getCode()==200) {
                                showSuccessAlert();
                                // 这里同时需要更新表的数据，这取决于 service 层的实现是否已经更新了数据模型。
                                getTableView().getItems().remove(data);
                            }
                        });
                    }
                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btn);
                        }
                    }
                };
                return cell;
            }
        };
        deleteColumn.setCellFactory(cellFactory);
        // 使用自定义TableView的方法添加列
        tableView.createColumns(columns);
        // 将删除列添加到TableView中
        tableView.getTableView().getColumns().add(deleteColumn);
        // 获取所有医生的数据
        List<Doctor> allDoctors = manageDoctorsService.getAllDoctor();
        ObservableList<Doctor> data = FXCollections.observableArrayList(allDoctors);

        // 设置数据到表格视图中
        tableView.setItems(data);
    }
    private void showSuccessAlert() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("操作成功");
        alert.setHeaderText(null);
        alert.setContentText("删除成功！");
        alert.showAndWait();
    }
}
