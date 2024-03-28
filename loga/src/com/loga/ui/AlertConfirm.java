package com.loga.ui;

import javafx.scene.control.Alert;

public class AlertConfirm extends Alert
{
    private static AlertConfirm alert;
    
    private AlertConfirm() {
        super(AlertType.CONFIRMATION);
    }
    
    public static Alert getInstance() {
        if (AlertConfirm.alert == null) {
            AlertConfirm.alert = new AlertConfirm();
        }
        return AlertConfirm.alert;
    }
}
