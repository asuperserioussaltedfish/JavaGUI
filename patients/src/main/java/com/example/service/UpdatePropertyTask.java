package com.example.service;

import javafx.concurrent.Task;
import javafx.application.Platform;

public class UpdatePropertyTask extends Task<Void> {

    private final int propertyValue;
    private final int itemId;

    public UpdatePropertyTask(int itemId, int newValue) {
        this.itemId = itemId;
        this.propertyValue = newValue;
    }

    @Override
    protected Void call() throws Exception {
        Platform.runLater(() -> {
        });
        
        return null;
    }
}