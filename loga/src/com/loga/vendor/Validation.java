package com.loga.vendor;

import com.loga.ui.AlertError;
import javafx.scene.control.*;

import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;
import java.time.format.DateTimeFormatter;

public class Validation
{
    private static DateTimeFormatter dateTimeFormatter;
    
    public static boolean is_empty_field(TextField jfxTextField) {
        if (jfxTextField.getText().trim().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Erreur liée au formulaire");
            alert.setContentText("Veuillez remplir le formulaire");
            alert.showAndWait();
            jfxTextField.requestFocus();
            return true;
        }
        return false;
    }
    
    public static boolean is_empty_area(TextArea jfxTextField) {
        if (jfxTextField.getText().trim().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Erreur liée au formulaire");
            alert.setContentText("Veuillez remplir le formulaire");
            alert.showAndWait();
            jfxTextField.requestFocus();
            return true;
        }
        return false;
    }
    
    public static boolean is_input_size(TextField jfxTextField, int size) {
        if (jfxTextField.getText().trim().length() > size) {
            AlertError.getInstance().setHeaderText("Erreur liée au formulaire");
            AlertError.getInstance().setContentText("Le nombre de caractères maximal pour ce champ est de " + size);
            AlertError.getInstance().showAndWait();
            jfxTextField.requestFocus();
            return false;
        }
        return true;
    }
    
    public static boolean is_valid_email(TextField jfxTextField) {
        String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
        if (!jfxTextField.getText().trim().matches(regex)) {
            AlertError.getInstance().setHeaderText("Erreur liée au formulaire");
            AlertError.getInstance().setContentText("L'email est invalide.");
            AlertError.getInstance().showAndWait();
            jfxTextField.requestFocus();
            return false;
        }
        return true;
    }
    
    public static boolean is_valid_period(DatePicker debut, DatePicker fin) {
        if (((LocalDate)debut.getValue()).isAfter((ChronoLocalDate)fin.getValue())) {
            AlertError.getInstance().setHeaderText("Erreur liée au formulaire");
            AlertError.getInstance().setContentText("La date de début doit venir avant la date fin.");
            AlertError.getInstance().showAndWait();
            debut.requestFocus();
            return false;
        }
        return true;
    }
    
    public static boolean check_selected(ComboBox jfxComboBox) {
        if (jfxComboBox.getSelectionModel().getSelectedIndex() == -1) {
            AlertError.getInstance().setHeaderText("Erreur liée au formulaire");
            AlertError.getInstance().setContentText("Veuillez effectuer un choix.");
            AlertError.getInstance().showAndWait();
            jfxComboBox.requestFocus();
            return true;
        }
        return false;
    }
    
    public static LocalDate string_to_date(String text) {
        LocalDate date = null;
        if (text != null && !text.trim().isEmpty()) {
            date = LocalDate.parse(text, Validation.dateTimeFormatter);
        }
        return date;
    }
    
    public static String date_to_string(LocalDate localDate) {
        String text = null;
        if (localDate != null) {
            text = Validation.dateTimeFormatter.format(localDate);
        }
        return text;
    }
    
    static {
        Validation.dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    }
}
