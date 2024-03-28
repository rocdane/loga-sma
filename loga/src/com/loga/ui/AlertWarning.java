package com.loga.ui;

import javafx.scene.control.Alert;

public class AlertWarning extends Alert
{
    private static AlertWarning alert;
    
    private AlertWarning() {
        super(AlertType.WARNING);
    }
    
    public static Alert getInstance() {
        if (AlertWarning.alert == null) {
            AlertWarning.alert = new AlertWarning();
        }
        return AlertWarning.alert;
    }
}
