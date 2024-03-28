package com.loga.ui;

import javafx.scene.control.Alert;

public class AlertInfo extends Alert
{
    private static AlertInfo alert;
    
    private AlertInfo() {
        super(AlertType.INFORMATION);
    }
    
    public static Alert getInstance() {
        if (AlertInfo.alert == null) {
            AlertInfo.alert = new AlertInfo();
        }
        return AlertInfo.alert;
    }
}
