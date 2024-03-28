package com.loga;

import com.loga.api.Manage;
import com.loga.api.Server;
import com.loga.app.container.Container;
import com.loga.app.container.ManagerContainer;
import com.loga.app.container.MarketerContainer;
import com.loga.app.container.RepairerContainer;
import com.loga.model.Profile;
import com.loga.model.User;
import com.loga.ui.View;
import javafx.application.Application;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Task;
import javafx.concurrent.Worker;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Date;
import java.util.Objects;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        javafx.concurrent.Service<Void> load = new javafx.concurrent.Service<Void>() {
            @Override
            protected Task<Void> createTask() {
                return new Task<Void>() {
                    @Override
                    protected Void call() throws Exception {

                        new Server().main(null);

                        new Container().main(null);

                        ManagerContainer.main(null);

                        RepairerContainer.main(null);

                        MarketerContainer.main(null);

                        Profile profile = new Profile();
                        profile.setNom("sabi");
                        profile.setPrenom("rochdane");
                        profile.setContact("rochdanesabi@loga.com");
                        profile.setPoste("IT");
                        profile.setDateAjout(new Date());

                        User user = new User();
                        user.setUsername(profile.getPrenom().trim().toLowerCase());
                        user.setPassword(profile.getNom().trim().toUpperCase());
                        user.setRole(User.Role.ADMINISTRATEUR.name());
                        user.setActive(true);
                        user.setProfile(profile);
                        profile.setUser(user);

                        Manage.getInstance().enregistrerProfile(profile);

                        return null;
                    }
                };
            }
        };

        load.stateProperty().addListener((ObservableValue<? extends Worker.State> observableValue, Worker.State oldValue, Worker.State newValue) -> {
            switch (newValue){
                case FAILED:
                case CANCELLED:
                case SUCCEEDED:
                    Parent primary = null;
                    try {
                        primary = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/auth.fxml")));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    View.getInstance().getGui().setTitle("LOGA | Login");
                    assert primary != null;
                    View.getInstance().getGui().setScene(new Scene(primary,800,600));
                    View.getInstance().getGui().getIcons().add(new Image("/assets/img/logo.png"));
                    View.getInstance().getGui().centerOnScreen();
                    View.getInstance().getGui().show();
            }
        });

        load.start();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
