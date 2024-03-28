package com.loga.ui;

import javafx.scene.control.Alert;

public class AlertError extends Alert
{
    private static AlertError alert;
    
    private AlertError() {
        super(AlertType.ERROR);
    }
    
    public static Alert getInstance() {
        if (AlertError.alert == null) {
            AlertError.alert = new AlertError();
        }
        return AlertError.alert;
    }
}
