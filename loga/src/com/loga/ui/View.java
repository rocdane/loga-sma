package com.loga.ui;

import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class View extends Stage
{
    private static View view;
    private Stage gui;
    public static AnchorPane content;

    private View() {
        this.gui = new Stage();
        this.gui.setResizable(true);
        this.gui.centerOnScreen();
    }

    public static View getInstance() {
        if (View.view == null) {
            View.view = new View();
        }
        return View.view;
    }

    public static void show(String text) {
    }

    public Stage getGui() {
        return this.gui;
    }
}
