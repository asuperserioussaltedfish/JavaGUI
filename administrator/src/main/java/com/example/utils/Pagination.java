package com.example.utils;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.control.Control;
import javafx.scene.control.Skin;

import java.util.Arrays;
import java.util.List;

public class Pagination extends Control {
    private static final String STYLE_SHEET = String.valueOf(TableView.class.getResource("/css/manage/cf-pagination.css"));
    private static final String STYLE_CLASS = "cf-pagination";
    //
    private final IntegerProperty pageCount = new SimpleIntegerProperty(0); // 总页数
    private final IntegerProperty pageIndex = new SimpleIntegerProperty(1); // 当前页
    private final IntegerProperty pagerCount = new SimpleIntegerProperty(7); // 最大按钮数

    public Pagination() {
        initialize();
    }

    public Pagination(int pages, int pageIndex) {
        setPageCount(pages);
        setPageIndex(pageIndex);
        initialize();
    }

    /***********************************************/

    public int getPageCount() {
        return pageCount.get();
    }

    public void setPageCount(int pageCount) {
        this.pageCount.set(pageCount);
    }

    public IntegerProperty pageCountProperty() {
        return pageCount;
    }

    /***********************************************/

    public int getPageIndex() {
        return pageIndex.get();
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex.set(pageIndex);
    }

    public IntegerProperty pageIndexProperty() {
        return pageIndex;
    }

    /***********************************************/

    public int getPagerCount() {
        return pagerCount.get();
    }

    private static final List<Integer> PAGER_COUNT_LIST = Arrays.asList(5, 7, 9, 11);

    /**
     * @param pagerCount ： 可用值：[5,7,9,11]。默认:7
     */
    public void setPagerCount(int pagerCount) {
        if (PAGER_COUNT_LIST.contains(pagerCount)) {
            this.pagerCount.set(pagerCount);
        } else {
            this.pagerCount.set(7);
        }
    }

    public IntegerProperty pagerCountProperty() {
        return pagerCount;
    }

    private void initialize() {
        setMaxSize(USE_PREF_SIZE, USE_PREF_SIZE);
        getStyleClass().add(STYLE_CLASS);
    }
    @Override
    protected Skin<?> createDefaultSkin() {
        return new PaginationSkin(this);
    }
//
    @Override
    public String getUserAgentStylesheet() {
        return STYLE_SHEET;
    }

}
