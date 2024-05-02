package com.example.modules.manage;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * @author SaltedFish
 */
public class ManageStage extends Stage {
    public ManageStage() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/demo/view1.fxml"));
        Font.loadFont(getClass().getResourceAsStream("义启-破苍穹.ttf"), 16);
        Scene scene = new Scene(fxmlLoader.load(), 1120, 630);
        this.setTitle("Patient");
        this.setScene(scene);
    }
}
