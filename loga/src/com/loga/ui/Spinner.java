package com.loga.ui;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Popup;

import java.io.IOException;
import java.util.Objects;

public class Spinner {
    private static Spinner instance;
    private Popup popup;

    private Spinner(){
        try {
            AnchorPane root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/popup.fxml")));
            this.popup = new Popup();
            this.popup.getContent().add(root);
            this.popup.sizeToScene();
            this.popup.focusedProperty();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Spinner getInstance(){
        if(instance==null){
            instance = new Spinner();
        }
        return instance;
    }

    public void show(){
        popup.show(View.getInstance().getGui());
    }

    public void hide(){
        popup.hide();
    }
}
