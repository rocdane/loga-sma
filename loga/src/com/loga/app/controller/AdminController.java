package com.loga.app.controller;

import com.loga.api.Manage;
import com.loga.app.dao.*;
import com.loga.model.*;
import com.loga.ui.AlertConfirm;
import com.loga.ui.AlertError;
import com.loga.ui.AlertInfo;
import com.loga.ui.Popup;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.concurrent.Task;
import javafx.concurrent.Worker;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class AdminController implements Initializable
{
    Atelier currentAtelier;
    Espace currentEspace;
    Titre currentTitre;
    Service currentService;

    @FXML
    private AnchorPane content;

    @FXML
    private TextField f_username;

    @FXML
    private PasswordField f_password;

    @FXML
    private ComboBox<User.Role> f_role;

    @FXML
    private TextField f_id;

    @FXML
    private TableView<User> table_users;

    @FXML
    private TableColumn<User, Long> t_id;

    @FXML
    private TableColumn<User, String> t_username;

    @FXML
    private TableColumn<User, String> t_password;

    @FXML
    private TableColumn<User, String> t_role;

    @FXML
    private Tab tab_settings;

    @FXML
    private TextField atelier_raison_sociale;

    @FXML
    private TextField atelier_adresse;

    @FXML
    private TextField atelier_contact;

    @FXML
    private TextField atelier_mention_legale;

    @FXML
    private TextField code_espace;

    @FXML
    private TextField intitule_espace;

    @FXML
    private TableView<Espace> table_espace;

    @FXML
    private TableColumn<Espace, Long> espace_id;

    @FXML
    private TableColumn<Espace, String> espace_code;

    @FXML
    private TableColumn<Espace, String> espace_intitule;

    @FXML
    private TextField designation_service;

    @FXML
    private TableView<Service> table_service;

    @FXML
    private TableColumn<Service, Long> service_id;

    @FXML
    private TableColumn<Service, String> service_designation;

    @FXML
    private TextField designation_titre;

    @FXML
    private TableView<Titre> table_titre;

    @FXML
    private TableColumn<Titre, Long> titre_id;

    @FXML
    private TableColumn<Titre, String> titre_designation;

    @FXML
    void cancel(ActionEvent event) {
        f_id.setText("");
        f_username.setText("");
        f_password.setText("");
    }

    @FXML
    void delete(ActionEvent event) {
        try {
            Manage.getInstance().supprimerUtilisateur(table_users.getSelectionModel().getSelectedItem());
            AlertInfo.getInstance().setHeaderText("Succès d'archivage !!!");
            AlertInfo.getInstance().setContentText("Utilisateur supprimé avec succès.");
            AlertInfo.getInstance().show();
        } catch (Exception e) {
            e.printStackTrace();
        }
        read();
    }

    @FXML
    void save(ActionEvent event) {
        User user = new User();
        if (f_username.getText().equals("") || f_password.getText().equals("")) {
            AlertError.getInstance().setHeaderText("Informations incomplètes");
            AlertError.getInstance().setContentText("Vous n'avez pas saisies toutes les informations.");
            AlertError.getInstance().show();
        }
        else {
            user.setUsername(f_username.getText().trim());
            user.setPassword(f_password.getText().trim());
            user.setRole((f_role.getSelectionModel().getSelectedItem()).toString());
            if (f_id.getText().equals("")) {
                UserRepository userRepository = new UserRepository();
                try {
                    userRepository.save(user);
                    AlertInfo.getInstance().setHeaderText("Succès d'enregistrement !!!");
                    AlertInfo.getInstance().setContentText("Utilisateur enregistré avec succès.");
                    AlertInfo.getInstance().show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            else {
                user.setId(Long.parseLong(f_id.getText().trim()));
                UserRepository userRepository = new UserRepository();
                try {
                    userRepository.update(user);
                    AlertInfo.getInstance().setHeaderText("Succès de mise à jour !!!");
                    AlertInfo.getInstance().setContentText("Utilisateur mis à jour avec succès.");
                    AlertInfo.getInstance().show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        read();
    }

    @FXML
    void annuler_espace(ActionEvent event) {
        if(currentEspace!=null){
            AlertConfirm.getInstance().setHeaderText("Espace de travail");
            AlertConfirm.getInstance().setContentText("Voulez-vous supprimer cet espace de réparation ?");
            if(AlertConfirm.getInstance().getButtonTypes().equals(ButtonType.OK)){
                EspaceRepository espaceRepository = new EspaceRepository();
                try {
                    espaceRepository.delete(currentEspace);
                } catch (Exception e) {
                    AlertError.getInstance().setHeaderText("Espace de travail");
                    AlertError.getInstance().setContentText("Echec lors de la suppression de l'espace de travail\n"+e.getMessage());
                    AlertError.getInstance().showAndWait();
                    e.printStackTrace();
                }
            }else{
                event.consume();
            }
        }
        readEspace();
        refresh();
    }

    @FXML
    void annuler_service(ActionEvent event) {
        if(currentService!=null){
            AlertConfirm.getInstance().setHeaderText("Service de l'entreprise");
            AlertConfirm.getInstance().setContentText("Voulez-vous supprimer ce service de votre entreprise ?");
            if(AlertConfirm.getInstance().getButtonTypes().equals(ButtonType.OK)){
                ServiceRepository serviceRepository = new ServiceRepository();
                try {
                    serviceRepository.delete(currentService);
                } catch (Exception e) {
                    AlertError.getInstance().setHeaderText("Service de l'entreprise");
                    AlertError.getInstance().setContentText("Echec lors de la suppression du service\n"+e.getMessage());
                    AlertError.getInstance().showAndWait();
                    e.printStackTrace();
                }

            }else{
                event.consume();
            }
        }
        readService();
        resetService();
    }

    @FXML
    void annuler_titre(ActionEvent event) {
        if(currentTitre!=null){
            AlertConfirm.getInstance().setHeaderText("Titre de l'employé");
            AlertConfirm.getInstance().setContentText("Voulez-vous supprimer ce titre d'employé ?");
            if(AlertConfirm.getInstance().getButtonTypes().equals(ButtonType.OK)){
                TitreRepository titreRepository = new TitreRepository();
                try {
                    titreRepository.delete(currentTitre);
                } catch (Exception e) {
                    AlertError.getInstance().setHeaderText("Titre de l'employé");
                    AlertError.getInstance().setContentText("Echec lors de la suppression du titre\n"+e.getMessage());
                    AlertError.getInstance().showAndWait();
                    e.printStackTrace();
                }
            }else{
                event.consume();
            }
        }
        readTitre();
        resetTitre();
    }

    @FXML
    void select_espace(MouseEvent event) {
        currentEspace = table_espace.getSelectionModel().getSelectedItem();
    }

    @FXML
    void select_service(MouseEvent event) {
        currentService = table_service.getSelectionModel().getSelectedItem();
    }

    @FXML
    void select_titre(MouseEvent event) {
        currentTitre = table_titre.getSelectionModel().getSelectedItem();
    }

    @FXML
    void valider_atelier(ActionEvent event) {
        AtelierRepository atelierRepository = new AtelierRepository();
        Atelier atelier = atelierRepository.findById(currentAtelier.getId());
        atelier.setRaisonSociale(atelier_raison_sociale.getText().trim());
        atelier.setMentionLegale(atelier_mention_legale.getText().trim());
        atelier.setContact(atelier_contact.getText().trim());
        atelier.setAdresse(atelier_adresse.getText().trim());
        try {
            atelierRepository.update(atelier);
            AlertInfo.getInstance().setHeaderText("Information de l'entreprise");
            AlertInfo.getInstance().setContentText("Informations mis à jour avec succès.");
            AlertInfo.getInstance().showAndWait();
        } catch (Exception e) {
            AlertError.getInstance().setHeaderText("Information de l'entreprise");
            AlertError.getInstance().setContentText("Echec lors de la mise à jour des informations.");
            AlertError.getInstance().showAndWait();
            e.printStackTrace();
        }
    }

    @FXML
    void valider_espace(ActionEvent event) {
        EspaceRepository espaceRepository = new EspaceRepository();
        Espace espace = espaceRepository.findById(currentEspace.getId());
        try {
            if(espace!=null){
                espace.setCode(code_espace.getText().trim());
                espace.setIntitule(intitule_espace.getText().trim());
                espaceRepository.update(espace);
            }else {
                espace=new Espace();
                espace.setCode(code_espace.getText().trim());
                espace.setIntitule(intitule_espace.getText().trim());
                espaceRepository.save(espace);
            }
            AlertInfo.getInstance().setHeaderText("Information de l'entreprise");
            AlertInfo.getInstance().setContentText("Informations mis à jour avec succès.");
            AlertInfo.getInstance().showAndWait();
        } catch (Exception e) {
            AlertError.getInstance().setHeaderText("Information de l'entreprise");
            AlertError.getInstance().setContentText("Echec lors de la mise à jour des informations.");
            AlertError.getInstance().showAndWait();
            e.printStackTrace();
        }
        readEspace();
        resetEspace();
    }

    @FXML
    void valider_service(ActionEvent event) {
        ServiceRepository serviceRepository = new ServiceRepository();
        Service service = serviceRepository.findById(currentService.getId());
        try {
            if(service!=null){
                service.setDesignation(designation_service.getText().trim());
                serviceRepository.update(service);
            }else {
                service = new Service();
                service.setDesignation(designation_service.getText().trim());
                serviceRepository.save(service);
            }
            AlertInfo.getInstance().setHeaderText("Information de l'entreprise");
            AlertInfo.getInstance().setContentText("Informations mis à jour avec succès.");
            AlertInfo.getInstance().showAndWait();
        } catch (Exception e) {
            AlertError.getInstance().setHeaderText("Information de l'entreprise");
            AlertError.getInstance().setContentText("Echec lors de la mise à jour des informations.");
            AlertError.getInstance().showAndWait();
            e.printStackTrace();
        }
        readService();
        resetService();
    }

    @FXML
    void valider_titre(ActionEvent event) {

        Titre titre = Manage.getInstance().afficherTitre(currentTitre.getId());

        if(titre!=null){
            titre.setDesignation(designation_titre.getText().trim());
        }else {
            titre = new Titre();
            titre.setDesignation(designation_titre.getText().trim());
        }

        try {
            Manage.getInstance().enregistrerTitre(titre);
            AlertInfo.getInstance().setHeaderText("Information de l'entreprise");
            AlertInfo.getInstance().setContentText("Informations mis à jour avec succès.");
            AlertInfo.getInstance().showAndWait();
        } catch (Exception e) {
            AlertError.getInstance().setHeaderText("Information de l'entreprise");
            AlertError.getInstance().setContentText("Echec lors de la mise à jour des informations.");
            AlertError.getInstance().showAndWait();
            e.printStackTrace();
        }
        readTitre();
        resetTitre();
    }
    
    public AdminController() {
    }

    public void read() {
        table_users.setItems(FXCollections.observableArrayList(Manage.getInstance().listerUtilisateur()));
    }

    public void readAtelier(){
        currentAtelier = Manage.getInstance().afficherAtelier(1);
        atelier_raison_sociale.setText(currentAtelier.getRaisonSociale());
        atelier_adresse.setText(currentAtelier.getAdresse());
        atelier_contact.setText(currentAtelier.getContact());
        atelier_mention_legale.setText(currentAtelier.getMentionLegale());
    }

    public void readEspace(){
        table_espace.setItems(FXCollections.observableArrayList(Manage.getInstance().listerEspace()));
    }

    public void readService(){
        table_service.setItems(FXCollections.observableArrayList(Manage.getInstance().listerService()));
    }

    public void readTitre(){
        table_titre.setItems(FXCollections.observableArrayList(Manage.getInstance().listerTitre()));
    }

    public void resetService(){
        currentService=null;
        designation_service.setText("");
    }

    public void resetTitre(){
        currentTitre=null;
        designation_titre.setText("");
    }

    public void resetEspace(){
        currentEspace=null;
        code_espace.setText("");
        intitule_espace.setText("");
    }

    public void refresh(){
        Popup.getInstance().show();

        javafx.concurrent.Service<Void> load = new javafx.concurrent.Service<Void>() {
            @Override
            protected Task<Void> createTask() {
                return new Task<Void>() {
                    @Override
                    protected Void call() throws Exception {
                        read();

                        readAtelier();

                        readEspace();

                        readService();

                        readTitre();

                        return null;
                    }
                };
            }
        };

        load.stateProperty().addListener((ObservableValue<? extends Worker.State> observableValue,Worker.State oldValue,Worker.State newValue) -> {
            switch (newValue){
                case FAILED:
                case CANCELLED:
                case SUCCEEDED:
                    Popup.getInstance().hide();
            }
        });

        load.start();
    }
    
    public void initialize(URL location, ResourceBundle resources) {
        f_role.getItems().setAll(User.getRoles());

        t_id.setCellValueFactory((TableColumn.CellDataFeatures<User, Long> r)->new ReadOnlyObjectWrapper<>(r.getValue().getId()));
        t_username.setCellValueFactory((TableColumn.CellDataFeatures<User, String> r)->new ReadOnlyObjectWrapper<>(r.getValue().getUsername()));
        t_password.setCellValueFactory((TableColumn.CellDataFeatures<User, String> r)->new ReadOnlyObjectWrapper<>(r.getValue().getPassword()));
        t_role.setCellValueFactory((TableColumn.CellDataFeatures<User, String> r)->new ReadOnlyObjectWrapper<>(r.getValue().getRole()));

        espace_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        espace_code.setCellValueFactory(new PropertyValueFactory<>("code"));
        espace_intitule.setCellValueFactory(new PropertyValueFactory<>("intitule"));

        service_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        service_designation.setCellValueFactory(new PropertyValueFactory<>("designation"));

        titre_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        titre_designation.setCellValueFactory(new PropertyValueFactory<>("designation"));

        refresh();
    }
}
