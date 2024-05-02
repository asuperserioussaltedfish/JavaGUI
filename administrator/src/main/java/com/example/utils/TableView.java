package com.example.utils;

import com.example.pojo.Column;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

import java.util.List;
import java.util.function.Consumer;

public class TableView extends VBox {

    private static final String STYLE_SHEET = String.valueOf(TableView.class.getResource("/css/manage/cf-table-view.css"));
    private javafx.scene.control.TableView tableView = new javafx.scene.control.TableView();
    private Pagination pagination = new Pagination(100, 1);
    private HBox hBox = new HBox(pagination);

    public TableView() {
        initLayout();
    }

    /**
     * 创建TableColumn
     *
     * @param columns
     */
    public void createColumns(List<Column> columns) {
        ObservableList tableViewColumns = tableView.getColumns();
        columns.forEach(v -> {
            TableColumn tableColumn = new TableColumn<>(v.getTitle());
            tableColumn.setCellValueFactory(new PropertyValueFactory(v.getFieldName()));
            tableColumn.setSortable(false);
            tableViewColumns.add(tableColumn);
        });

    }

    /**
     * SINGLE 单选，MULTIPLE 多选
     *
     * @param selectionMode
     */
    public void setSelectionMode(SelectionMode selectionMode) {
        tableView.getSelectionModel().setSelectionMode(selectionMode);
    }

    /**
     * 给表格填充数据
     */
    public <T> void setItems(List<T> list) {
        ObservableList<T> observableList = FXCollections.observableArrayList(list);
        tableView.setItems(observableList);
    }

    public javafx.scene.control.TableView getTableView() {
        return tableView;
    }

    public void setPageCount(int pageCount) {
        pagination.setPageCount(pageCount);
    }

    public void setPageIndex(int pageIndex) {
        pagination.setPageIndex(pageIndex);
    }

    public int getPageIndex() {
        return pagination.getPageIndex();
    }

    /**
     * 页码发送变化触发
     *
     * @param consumer
     */
    public void pageIndexChange(Consumer<Integer> consumer) {
        pagination.pageIndexProperty().addListener((observableValue, number, t1) -> consumer.accept(t1.intValue() + 1));
    }

    private void initLayout() {
        getChildren().addAll(tableView, hBox);
        VBox.setVgrow(tableView, Priority.ALWAYS);
        tableView.setColumnResizePolicy(javafx.scene.control.TableView.CONSTRAINED_RESIZE_POLICY);
        tableView.getStyleClass().addAll("cf-table-view", "scroll-bar-style");//.cf-scroll-bar-style为滚动条样式
        hBox.getStyleClass().add("pagination-container");
        getStyleClass().add("cf-table");
        getStylesheets().add(STYLE_SHEET);
    }

}
