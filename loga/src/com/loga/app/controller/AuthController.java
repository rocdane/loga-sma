package com.loga.app.controller;

import com.loga.api.Authenticate;
import com.loga.model.User;
import com.loga.ui.*;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.concurrent.Worker;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.Collection;
import java.util.Objects;

public class AuthController
{
    @FXML
    private AnchorPane auth_area;

    @FXML
    private TextField username;

    @FXML
    private PasswordField password;

    @FXML
    private ProgressIndicator loader;

    @FXML
    private TextField username_up;

    @FXML
    private PasswordField new_password;

    @FXML
    private PasswordField new_password_confirmed;

    @FXML
    private ProgressIndicator loader_signup;

    public void emptyContent() {
        ObservableList<Node> nodes = (ObservableList<Node>) auth_area.getChildren();
        auth_area.getChildren().removeAll((Collection)nodes);
    }

    public AnchorPane getContent() {
        return auth_area;
    }

    public void setContent(String view) {
        emptyContent();
        try {
            VBox fxml = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(view)));
            fxml.setMinSize(auth_area.getWidth(),auth_area.getHeight());
            fxml.setPrefSize(auth_area.getWidth(),auth_area.getHeight());
            fxml.setMaxSize(auth_area.getWidth(),auth_area.getHeight());
            getContent().getChildren().add(fxml);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void forgetPassword() {
        setContent("/fxml/auth-signup.fxml");
    }

    @FXML
    void to_login(){
        setContent("/fxml/auth-signin.fxml");
    }

    @FXML
    void login(ActionEvent event) {

        loader.setVisible(true);

        javafx.concurrent.Service<Void> load = new javafx.concurrent.Service<Void>() {
            @Override
            protected Task<Void> createTask() {
                return new Task<Void>() {
                    @Override
                    protected Void call() throws Exception {
                        Authenticate.getInstance().login(username.getText().trim(), password.getText().trim());
                        return null;
                    }
                };
            }
        };

        load.stateProperty().addListener((ObservableValue<? extends Worker.State> observableValue, Worker.State oldValue, Worker.State newValue) -> {
            switch (newValue){
                case FAILED:
                case CANCELLED:
                    loader.setVisible(false);
                    AlertError.getInstance().setHeaderText("Ouverture de session");
                    AlertError.getInstance().setContentText("Nom d'utilisateur / mot de passe incorrect ou utilisateur non autorisé!!!");
                    AlertError.getInstance().showAndWait();
                    break;
                case SUCCEEDED:
                    try {
                        Parent root = FXMLLoader.load(Objects.requireNonNull(this.getClass().getResource("/fxml/main.fxml")));
                        View.getInstance().getGui().setTitle("LOGA | Accueil");
                        View.getInstance().getGui().setScene(new Scene(root));
                        View.getInstance().getGui().centerOnScreen();
                    }
                    catch (IOException e) {
                        e.printStackTrace();
                    }
            }
        });

        load.start();
    }

    @FXML
    void update(ActionEvent event){
        if(new_password.getText().trim().equals(new_password_confirmed.getText().trim())){

            loader_signup.setVisible(true);

            javafx.concurrent.Service<Void> load = new javafx.concurrent.Service<Void>() {
                @Override
                protected Task<Void> createTask() {
                    return new Task<Void>() {
                        @Override
                        protected Void call() throws Exception {
                            User user = new User();
                            user.setUsername(username_up.getText().trim());
                            user.setPassword(new_password.getText().trim());
                            Authenticate.getInstance().updateUser(user);
                            return null;
                        }
                    };
                }
            };

            load.stateProperty().addListener((ObservableValue<? extends Worker.State> observableValue, Worker.State oldValue, Worker.State newValue) -> {
                switch (newValue){
                    case FAILED:
                    case CANCELLED:
                        loader_signup.setVisible(false);
                        AlertError.getInstance().setHeaderText("Authentification");
                        AlertError.getInstance().setContentText("Le nom d'utilisateur n'existe pas!!!");
                        AlertError.getInstance().showAndWait();
                        break;
                    case SUCCEEDED:
                        try {
                            Parent root = FXMLLoader.load(Objects.requireNonNull(this.getClass().getResource("/fxml/main.fxml")));
                            View.getInstance().getGui().setTitle("LOGA | Accueil");
                            View.getInstance().getGui().setScene(new Scene(root));
                            View.getInstance().getGui().centerOnScreen();
                        }
                        catch (IOException e) {
                            e.printStackTrace();
                        }
                }
            });
            load.start();
        }else {
            event.consume();
            AlertError.getInstance().setHeaderText("Authentification");
            AlertError.getInstance().setContentText("Les mots de passe ne sont pas identiques!!!");
            AlertError.getInstance().showAndWait();
        }
    }
}
