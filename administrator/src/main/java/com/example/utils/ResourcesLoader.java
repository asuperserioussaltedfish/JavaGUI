package com.example.utils;
import java.net.URL;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.InputStream;
import java.net.URL;

public class ResourcesLoader {

    public static URL loadURL(String path) {
        URL resource = ResourcesLoader.class.getResource(path);
        if (resource == null) {
            System.err.println("----------------------------------------------> resourceLoadError : " + path);
        }
        return resource;
    }

    public static String load(String path) {
        return loadURL(path).toExternalForm();
    }

    public static InputStream loadStream(String path) {
        return ResourcesLoader.class.getResourceAsStream(path);
    }

    public static Image loadFxImage(String path) {
        return new Image(load(path));
    }

    public static ImageView loadFxImageView(String path, double width, double height) {
        ImageView imageView = new ImageView(loadFxImage(path));
        imageView.setFitWidth(width);
        imageView.setFitHeight(height);
        return imageView;
    }
}
