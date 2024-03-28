package com.loga.app.controller;

import com.loga.api.Authenticate;
import com.loga.model.Session;
import com.loga.ui.AlertConfirm;
import com.loga.ui.FullCalendarView;
import com.loga.ui.View;
import com.loga.vendor.*;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.time.YearMonth;
import java.util.Collection;
import java.util.Objects;
import java.util.ResourceBundle;

public class GuiController extends Access implements Initializable
{
    private static GuiController instance;

    private static Session session;

    public GuiController() {
        instance = this;
    }

    public static GuiController getInstance(){
        return instance;
    }

    public static Session getSession() {
        return session;
    }

    public static void setSession(Session session) {
        GuiController.session = session;
    }

    @FXML
    private AnchorPane content;

    @FXML
    private Text navigation;

    @FXML
    void menu_admin(ActionEvent event) {
        if("ADMINISTRATEUR".equals(session.getUser().getRole())){
            setContent("/fxml/admin.fxml");
            View.getInstance().getGui().setTitle("GMC PLUS | Administrateur");
            setNavigation("Administrateur");
        }else{
            limitAccess();
        }
    }

    @FXML
    void menu_article(ActionEvent event) {
        if ("UTILISATEUR".equals(session.getUser().getRole())) {
            limitAccess();
        } else {
            setContent("/fxml/fourniture.fxml");
            View.getInstance().getGui().setTitle("GMC PLUS | Atelier | Fourniture");
            setNavigation("Fourniture");
        }
    }
    
    @FXML
    void menu_atelier(ActionEvent event) {
        setContent("/fxml/home-atelier.fxml");
        View.getInstance().getGui().setTitle("GMC PLUS | Atelier");
        setNavigation("Atelier");
    }
    
    @FXML
    void menu_automobile(ActionEvent event) {
        setContent("/fxml/automobile.fxml");
        View.getInstance().getGui().setTitle("GMC PLUS | Automobile");
        setNavigation("Automobile");
    }
    
    @FXML
    void menu_client(ActionEvent event) {
        setContent("/fxml/client.fxml");
        View.getInstance().getGui().setTitle("GMC PLUS | Client");
        setNavigation("Client");
    }
    
    @FXML
    void menu_reception(ActionEvent event) {
        setContent("/fxml/reception.fxml");
        View.getInstance().getGui().setTitle("GMC PLUS | Réception");
        setNavigation("Réception");
    }
    
    @FXML
    void menu_reparation(ActionEvent event) {
        if ("UTILISATEUR".equals(session.getUser().getRole())) {
            limitAccess();
        } else {
            setContent("/fxml/reparation.fxml");
            View.getInstance().getGui().setTitle("GMC PLUS | Atelier | Reparation");
            setNavigation("Réparation");
        }
    }
    
    @FXML
    void menu_atelier_agenda(ActionEvent event) {
        setContent("/fxml/agenda.fxml");
        View.getInstance().getGui().setTitle("GMC PLUS | Atelier | Agenda");
        AgendaController.getInstance().agenda_tab_content.getChildren().add(new FullCalendarView(YearMonth.now()).getView());
        setNavigation("Agenda");
    }
    
    @FXML
    void menu_atelier_dossier(ActionEvent event) {
        setContent("/fxml/dossier.fxml");
        View.getInstance().getGui().setTitle("GMC PLUS | Atelier | Dossier");
        setNavigation("Dossier");
    }
    
    @FXML
    void menu_atelier_profil(ActionEvent event) {
        if ("UTILISATEUR".equals(session.getUser().getRole())) {
            limitAccess();
        } else {
            setContent("/fxml/profile.fxml");
            View.getInstance().getGui().setTitle("GMC PLUS | Atelier | Profile");
            setNavigation("Profil");
        }
    }
    
    @FXML
    void menu_atelier_stock(ActionEvent event) {
        if ("UTILISATEUR".equals(session.getUser().getRole())) {
            limitAccess();
        } else {
            setContent("/fxml/stock.fxml");
            View.getInstance().getGui().setTitle("GMC PLUS | Atelier | Stock");
            setNavigation("Stock");
        }
    }

    @FXML
    void menu_atelier_tresor(ActionEvent event) {
        if ("UTILISATEUR".equals(session.getUser().getRole())) {
            limitAccess();
        } else {
            setContent("/fxml/tresor.fxml");
            View.getInstance().getGui().setTitle("GMC PLUS | Atelier | Trésor");
            setNavigation("Trésor");
        }
    }
    
    @FXML
    void versHome(ActionEvent event) {
        setContent("/fxml/home.fxml");
        View.getInstance().getGui().setTitle("GMC PLUS | Accueil");
        setNavigation("Accueil");
    }
    
    @FXML
    void versReparation(ActionEvent event) {
        if ("UTILISATEUR".equals(session.getUser().getRole())) {
            limitAccess();
        } else {
            setContent("/fxml/reparation.fxml");
            View.getInstance().getGui().setTitle("GMC PLUS | Atelier | Réparation");
            setNavigation("Réparation");
        }
    }
    
    @FXML
    void versDashboard(ActionEvent event) {
        setContent("/fxml/dashboard.fxml");
        View.getInstance().getGui().setTitle("GMC PLUS | Tableau de bord");
        setNavigation("Tableau de bord");
    }
    
    @FXML
    void versAutomobile(ActionEvent event) {
        setContent("/fxml/automobile.fxml");
        View.getInstance().getGui().setTitle("GMC PLUS | Automobile");
        setNavigation("Automobile");
    }
    
    @FXML
    void versDossier(ActionEvent event) {
        if("UTILISATEUR".equals(session.getUser().getRole())){
            limitAccess();
        }else {
            setContent("/fxml/dossier.fxml");
            View.getInstance().getGui().setTitle("GMC PLUS | Atelier | Dossier");
            setNavigation("Dossier");
        }
    }
    
    @FXML
    void versReception(ActionEvent event) {
        setContent("/fxml/reception.fxml");
        View.getInstance().getGui().setTitle("GMC PLUS | Réception");
        setNavigation("Réception");
    }

    @FXML
    void versProfil(ActionEvent event){
        // TODO : afficher le profil de l'utilisateur
    }

    @FXML
    void versMessagerie(ActionEvent event){
        // TODO : afficher l'interface de messagerie
    }

    @FXML
    void versNotification(ActionEvent event){
        //TODO : afficher les notifications
    }

    @FXML
    void logout(ActionEvent event) {
        try {
            Authenticate.getInstance().logout(session);
            Parent auth = FXMLLoader.load((URL)Objects.requireNonNull(getClass().getResource("/fxml/auth.fxml")));
            View.getInstance().getGui().setScene(new Scene(auth));
            View.getInstance().getGui().setTitle("GMC PLUS | Login");
            View.getInstance().getGui().centerOnScreen();
            session=null;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void help(ActionEvent event) {
        setContent("/fxml/support.fxml");
        View.getInstance().getGui().setTitle("GMC PLUS | A propos");
        setNavigation("A propos");
    }
    
    public void emptyContent() {
        ObservableList<Node> nodes = (ObservableList<Node>)content.getChildren();
        content.getChildren().removeAll((Collection)nodes);
    }
    
    public AnchorPane getContent() {
        return content;
    }
    
    public void setContent(String view) {
        emptyContent();
        try {
            AnchorPane fxml = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(view)));
            fxml.setMinSize(content.getWidth(),content.getHeight());
            fxml.setPrefSize(content.getWidth(),content.getHeight());
            fxml.setMaxSize(content.getWidth(),content.getHeight());
            content.getChildren().add(fxml);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setNavigation(String link){
        navigation.setText(link);
    }

    public void initialize(URL url, ResourceBundle resourceBundle) {

        View.getInstance().getGui().maximizedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                View.getInstance().getGui().setMaximized(true);
            }
        });

        View.getInstance().getGui().setOnCloseRequest(event -> {
            AlertConfirm.getInstance().setTitle("Fermeture de l'application");
            AlertConfirm.getInstance().setContentText("Nous allons vous déconnecter de la session.");
            if(AlertConfirm.getInstance().showAndWait().get().equals(ButtonType.OK)){
                session=null;
                System.exit(0);
            }else{
                event.consume();
            }
        });
    }
}
