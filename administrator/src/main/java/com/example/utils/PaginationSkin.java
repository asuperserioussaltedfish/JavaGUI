package com.example.utils;

import javafx.beans.binding.Bindings;
import javafx.collections.ObservableList;
import javafx.css.PseudoClass;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.SkinBase;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;

import java.util.concurrent.Callable;

public class PaginationSkin extends SkinBase<Pagination> {

    private final Label first;
    private final Label last;
    private final Label prev; // 上一个
    private final Label next; // 下一个
    private final Label quickPrev; // 快速上一页
    private final Label quickNext; // 快速下一页
    private final HBox pagerBox;

    public PaginationSkin(Pagination control) {

        super(control);
        Path leftArrow = new Path();
        leftArrow.getElements().add(new MoveTo(10, 0));
        leftArrow.getElements().add(new LineTo(0, 5));
        leftArrow.getElements().add(new LineTo(10, 10));
        leftArrow.setStrokeWidth(2);
        leftArrow.setStroke(Color.BLACK);

        prev = getPaginationLabel(0);
        prev.setGraphic(leftArrow);
//        prev.setGraphic(FontIcon.of(AntDesignIconsOutlined.LEFT));
        Path rightArrow = new Path();
        rightArrow.getElements().add(new MoveTo(0, 0));
        rightArrow.getElements().add(new LineTo(10, 5));
        rightArrow.getElements().add(new LineTo(0, 10));
        rightArrow.setStrokeWidth(2);
        rightArrow.setStroke(Color.BLACK);

// 创建一个Label并使用Path作为图标
        next = getPaginationLabel(0);
        next.setGraphic(rightArrow);

//        next.setGraphic(FontIcon.of(AntDesignIconsOutlined.RIGHT));
        quickPrev = getPaginationLabel(0);
        quickNext = getPaginationLabel(0);
        pagerBox = new HBox();
        first = getPaginationLabel(1);
        last = getPaginationLabel(1);
        last.textProperty().bind(control.pageCountProperty().asString());
        HBox container = new HBox(prev, first, quickPrev, pagerBox, quickNext, last, next);
        //显示隐藏
        last.managedProperty().bind(control.pageCountProperty().greaterThan(1));
        last.visibleProperty().bind(last.managedProperty());
        pagerBox.managedProperty().bind(control.pageCountProperty().greaterThan(2));
        pagerBox.visibleProperty().bind(pagerBox.managedProperty());
        // styleClass
        container.getStyleClass().add("container");
        pagerBox.getStyleClass().add("pager-box");
        getChildren().add(container);
        initEvent(control);
    }

    private void initEvent(Pagination control) {
        Path doubleLeftArrow = new Path();
        // 箭头的顶端
        doubleLeftArrow.getElements().add(new MoveTo(20 / 2.0, 0));
        // 箭头向下的斜线
        doubleLeftArrow.getElements().add(new LineTo(10 / 2.0, 10 / 2.0));
        // 回到箭头顶端
        doubleLeftArrow.getElements().add(new MoveTo(20 / 2.0, 0));
        // 箭头向上的斜线
        doubleLeftArrow.getElements().add(new LineTo(10 / 2.0, -10 / 2.0));
// 箭头的顶端
        doubleLeftArrow.getElements().add(new MoveTo(30 / 2.0, 0));
        // 箭头向下的斜线
        doubleLeftArrow.getElements().add(new LineTo(20 / 2.0, 10 / 2.0));
        // 回到箭头顶端
        doubleLeftArrow.getElements().add(new MoveTo(30 / 2.0, 0));
        // 箭头向上的斜线
        doubleLeftArrow.getElements().add(new LineTo(20 / 2.0, -10 / 2.0));
        // 线宽
        doubleLeftArrow.setStrokeWidth(3 / 2.0);
        doubleLeftArrow.setStroke(Color.BLACK);
        doubleLeftArrow.setStrokeLineCap(javafx.scene.shape.StrokeLineCap.ROUND);

        Path doubleRightArrow = new Path();
        doubleRightArrow.getElements().add(new MoveTo(10 / 2.0, 0));
        doubleRightArrow.getElements().add(new LineTo(20 / 2.0, 10 / 2.0));
        doubleRightArrow.getElements().add(new MoveTo(10 / 2.0, 0));
        doubleRightArrow.getElements().add(new LineTo(20 / 2.0, -10 / 2.0));
        // 第二个箭头，留一些间隔
        doubleRightArrow.getElements().add(new MoveTo(0, 0));
        doubleRightArrow.getElements().add(new LineTo(10 / 2.0, 10 / 2.0));
        doubleRightArrow.getElements().add(new MoveTo(0, 0));
        doubleRightArrow.getElements().add(new LineTo(10 / 2.0, -10 / 2.0));
        doubleRightArrow.setStrokeWidth(3 / 2.0);
        doubleRightArrow.setStroke(Color.BLACK);
        doubleRightArrow.setStrokeLineCap(javafx.scene.shape.StrokeLineCap.ROUND);


        quickPrev.graphicProperty().bind(Bindings.
                createObjectBinding((Callable<Node>) () ->
                        doubleRightArrow));
        quickNext.graphicProperty().bind(Bindings.
                createObjectBinding((Callable<Node>) () ->doubleLeftArrow
                ));
        // 是否显示pres，nexts按钮
        quickPrev.managedProperty().bind(control.pageIndexProperty().greaterThan(control.pagerCountProperty().divide(2).add(1)).and(control.pageCountProperty().greaterThan(control.pagerCountProperty())));
        quickPrev.visibleProperty().bind(quickPrev.managedProperty());
        quickNext.managedProperty().bind(control.pageCountProperty().subtract(control.pageIndexProperty()).greaterThan(control.pagerCountProperty().divide(2)).and(control.pageCountProperty().greaterThan(control.pagerCountProperty())));
        quickNext.visibleProperty().bind(quickNext.managedProperty());
        // 按钮数量发生变化
        pagerChanged(control);
        control.pageCountProperty().addListener((observable, oldValue, newValue) -> {
            // 更新数字按钮
            pagerChanged(control);
        });
        // 监听当前页码发生变化
        activeChanged(control.getPageIndex());
        control.pageIndexProperty().addListener((observable, oldValue, newValue) -> {
            // 更新伪类
            activeChanged(newValue.intValue());
        });
        // 首页，末页点击事件
        first.setOnMouseClicked(event -> control.setPageIndex(1));
        last.setOnMouseClicked(event -> control.setPageIndex(control.getPageCount()));
        // 上一页，下一页，快速上一页，快速下一页
        prev.setOnMouseClicked(event -> control.setPageIndex(control.getPageIndex() > 1 ? control.getPageIndex() - 1 : control.getPageIndex()));
        next.setOnMouseClicked(event -> control.setPageIndex(control.getPageIndex() < control.getPageCount() ? control.getPageIndex() + 1 : control.getPageIndex()));
        quickPrev.setOnMouseClicked(event -> control.setPageIndex(Math.max(control.getPageIndex() - 5, 1)));
        quickNext.setOnMouseClicked(event -> control.setPageIndex(Math.min(control.getPageIndex() + 5, control.getPageCount())));
    }

    /**
     * 初始化按钮
     */
    private void pagerChanged(Pagination c) {
        int pageCount = c.getPageCount();
        if (pageCount < 1) {
            pageCount = 1;
        }
        int pagerCount = Math.min(pageCount, c.getPagerCount());
        ObservableList<Node> children = pagerBox.getChildren();
        children.clear();
        for (int i = 2; i < pagerCount; i++) {
            children.add(getPaginationLabel(i));
        }
    }

    /**
     * 动态添加 active 伪类，更改页码的css样式
     */

    private static final PseudoClass ACTIVE_CLASS_SELECTED = PseudoClass.getPseudoClass("active");

    private void activeChanged(int pageIndex) {
        Pagination c = getSkinnable();
        first.pseudoClassStateChanged(ACTIVE_CLASS_SELECTED, pageIndex == 1);
        last.pseudoClassStateChanged(ACTIVE_CLASS_SELECTED, pageIndex == c.getPageCount());
        ObservableList<Node> children = pagerBox.getChildren();
        int size = children.size();
        if (quickPrev.isManaged() && quickNext.isManaged()) {
            int i = pageIndex - c.getPagerCount() / 2 + 1;
            for (int j = 0; j < size; j++) {
                Label L = (Label) children.get(j);
                L.setText(String.valueOf(i));
                L.pseudoClassStateChanged(ACTIVE_CLASS_SELECTED, pageIndex == Integer.parseInt(L.getText()));
                i++;
            }
            return;
        }
        for (int i = 0; i < size; i++) {
            Label L = (Label) children.get(i);
            if (!quickPrev.isManaged() && quickNext.isManaged()) {
                L.setText(String.valueOf(i + 2));
            }
            if (quickPrev.isManaged() && !quickNext.isManaged()) {
                L.setText(String.valueOf(c.getPageCount() - (size - i)));
            }
            L.pseudoClassStateChanged(ACTIVE_CLASS_SELECTED, pageIndex == Integer.parseInt(L.getText()));
        }
    }

    private Label getPaginationLabel(int i) {
        Pagination c = getSkinnable();
        Label paginationLabel = new Label(i == 0 ? null : String.valueOf(i));
        paginationLabel.getStyleClass().add("pagination-label");
        if (i != 0) { // 数字按钮点击事件
            paginationLabel.setOnMouseClicked(event -> {
                Label L = (Label) event.getSource();
                c.setPageIndex(Integer.parseInt(L.getText()));
            });
        }
        return paginationLabel;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void layoutChildren(final double x, final double y, final double w, final double h) {
        super.layoutChildren(x, y, w, h);
    }
}
