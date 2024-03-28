package com.loga.ui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class Popup {
    private final Stage popup;
    private static Popup instance;

    private Popup(){
        this.popup = new Stage();
        try {
            AnchorPane root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/popup.fxml")));
            popup.setTitle("LOGA | Opération");
            popup.setScene(new Scene(root,800,600));
            popup.getIcons().add(new Image("/assets/img/logo.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Popup getInstance(){
        if(instance==null){
            instance = new Popup();
        }
        return instance;
    }

    public void show(){
        popup.show();
        popup.centerOnScreen();
    }

    public void hide(){
        popup.hide();
    }
}
