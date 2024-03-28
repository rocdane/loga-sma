package com.loga.app.controller;

import com.loga.api.Manage;
import com.loga.app.dao.AutomobileRepository;
import com.loga.app.dao.ClientRepository;
import com.loga.app.dao.DossierRepository;
import com.loga.model.Automobile;
import com.loga.model.Client;
import com.loga.model.Dossier;
import com.loga.ui.AlertConfirm;
import com.loga.ui.AlertError;
import com.loga.ui.AlertInfo;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.util.StringConverter;

import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class AutomobileController implements Initializable
{
    List<Client> allClients;
    List<Automobile> allAutomobiles;
    Automobile currentAuto;
    Client currentClient;
    long clientID=0;


    @FXML
    private Text nombre_auto;

    @FXML
    private AnchorPane content;

    @FXML
    private TabPane automobileTab;

    @FXML
    private Tab tabNew;

    @FXML
    private AnchorPane newTabContent;

    @FXML
    private GridPane newAutoForm;

    @FXML
    private ComboBox<Client> client;

    @FXML
    private TextField newImmatriculation;

    @FXML
    private TextField newMarque;

    @FXML
    private TextField newModele;

    @FXML
    private ComboBox<Automobile.Compteur> newTypeCompteur;

    @FXML
    private TextField newCompteur;

    @FXML
    private TextField newChassis;

    @FXML
    private GridPane newDossierForm;

    @FXML
    private TextField newReference;

    @FXML
    private TextField newPmec;

    @FXML
    private TextField newPuissance;

    @FXML
    private TextField newCylindre;

    @FXML
    private TextField newTransmission;

    @FXML
    private HBox btnArea;

    @FXML
    private Button btnEnregistrer;

    @FXML
    private Tab tabList;

    @FXML
    private AnchorPane listTabContent;

    @FXML
    private TextField filtre_automobile;

    @FXML
    private TableView<Automobile> automobileTable;

    @FXML
    private TableColumn<Automobile, Long> id;

    @FXML
    private TableColumn<Automobile, String> immatriculation;

    @FXML
    private TableColumn<Automobile, String> chassis;

    @FXML
    private TableColumn<Automobile, String> marque;

    @FXML
    private TableColumn<Automobile, String> modele;

    @FXML
    private TableColumn<Automobile, String> typeCompteur;

    @FXML
    private TableColumn<Automobile, Integer> compteur;

    @FXML
    private Tab tabDetail;

    @FXML
    private AnchorPane detailTabContent;

    @FXML
    private VBox formApplication2;

    @FXML
    private GridPane newAutoForm1;

    @FXML
    private TextField autoImmatriculation;

    @FXML
    private TextField autoMarque;

    @FXML
    private TextField autoModele;

    @FXML
    private ComboBox<Automobile.Compteur> autoTypeCompteur;

    @FXML
    private TextField autoCompteur;

    @FXML
    private TextField autoChassis;

    @FXML
    private VBox formApplication11;

    @FXML
    private GridPane newDossierForm1;

    @FXML
    private TextField autoReference;

    @FXML
    private TextField autoPmec;

    @FXML
    private TextField autoPuissance;

    @FXML
    private TextField autoCylindre;

    @FXML
    private TextField autoTransmission;
    
    @FXML
    void apply(ActionEvent event) {
            try {
                Automobile automobile = currentAuto;
                automobile.setChassis(autoChassis.getText().trim());
                automobile.setMarque(autoMarque.getText().trim());
                automobile.setCompteur(Integer.parseInt(autoCompteur.getText().trim()));
                automobile.setTypeCompteur(autoTypeCompteur.getSelectionModel().getSelectedItem().name());
                automobile.setPuissance(autoPuissance.getText().trim());
                automobile.setCylindre(autoCylindre.getText().trim());
                automobile.setPmec(autoPmec.getText().trim());
                automobile.setTransmission(autoTransmission.getText().trim());
                AutomobileRepository automobileRepository = new AutomobileRepository();
                automobileRepository.update(automobile);

                Dossier dossier = currentAuto.getDossier();
                dossier.setAutomobile(automobile);
                dossier.setReference(autoReference.getText().trim());
                dossier.setDateMaj(new Date());
                DossierRepository dossierRepository = new DossierRepository();
                dossierRepository.update(dossier);

                AlertInfo.getInstance().setTitle("Info");
                AlertInfo.getInstance().setHeaderText("Enregistrement automobile");
                AlertInfo.getInstance().setContentText("Automobile mis à jour avec succès.");
                AlertInfo.getInstance().show();
            } catch (Exception e) {
                AlertError.getInstance().setTitle("Erreur");
                AlertError.getInstance().setHeaderText("Enregistrement automobile");
                AlertError.getInstance().setContentText("Echec lors de la mise à jour de l'Automobile.\n"+e.getMessage());
                AlertError.getInstance().showAndWait();
                e.printStackTrace();
            }
        emptyField();
        selectTab(2);
        readAll();
    }
    
    @FXML
    void archive(ActionEvent event) {
        AlertConfirm.getInstance().setTitle("Confirmation");
        AlertConfirm.getInstance().setHeaderText("Archivage d'automobile");
        AlertConfirm.getInstance().setContentText("Votre action supprimera l'automobile\n et son dossier. Voulez-vous continuer?");
        AlertConfirm.getInstance().showAndWait();
        AutomobileRepository automobileRepository = new AutomobileRepository();
        Automobile automobile = automobileRepository.findById(currentAuto.getId());
        try {
            automobileRepository.delete(automobile);
            AlertInfo.getInstance().setTitle("Info");
            AlertInfo.getInstance().setHeaderText("Enregistrement automobile");
            AlertInfo.getInstance().setContentText("Automobile archivé avec succès");
            AlertInfo.getInstance().show();
            selectTab(2);
            readAll();
        } catch (Exception e) {
            AlertError.getInstance().setTitle("Erreur");
            AlertError.getInstance().setHeaderText("Suppression automobile");
            AlertError.getInstance().setContentText("Echec lors de la suppression de l'Automobile.\n"+e.getMessage());
            AlertError.getInstance().showAndWait();
            e.printStackTrace();
        }
    }
    
    @FXML
    void delete(ActionEvent event) {
        if (automobileTable.getSelectionModel().getSelectedItem() != null) {
            AlertConfirm.getInstance().setTitle("Confirmation");
            AlertConfirm.getInstance().setHeaderText("Archivage d'automobile");
            AlertConfirm.getInstance().setContentText("Votre action supprimera l'automobile\n et son dossier. Voulez-vous continuer?");
            AlertConfirm.getInstance().showAndWait();
            AutomobileRepository automobileRepository = new AutomobileRepository();
            Automobile automobile = automobileRepository.findById(currentAuto.getId());
            try {
                automobileRepository.delete(automobile);
                AlertInfo.getInstance().setTitle("Info");
                AlertInfo.getInstance().setHeaderText("Enregistrement automobile");
                AlertInfo.getInstance().setContentText("Automobile archivé avec succès");
                AlertInfo.getInstance().show();
                selectTab(2);
                readAll();
            } catch (Exception e) {
                AlertError.getInstance().setTitle("Erreur");
                AlertError.getInstance().setHeaderText("Suppression automobile");
                AlertError.getInstance().setContentText("Echec lors de la suppression de l'Automobile.\n"+e.getMessage());
                AlertError.getInstance().showAndWait();
                e.printStackTrace();
            }
        }
    }
    
    @FXML
    void edit(ActionEvent event) {
        autoImmatriculation.setText(currentAuto.getImmatriculation());
        autoChassis.setText(currentAuto.getChassis());
        autoMarque.setText(currentAuto.getMarque());
        autoModele.setText(currentAuto.getModele());
        autoCompteur.setText(String.valueOf(currentAuto.getCompteur()));
        autoReference.setText(currentAuto.getDossier().getReference());
        autoPuissance.setText(currentAuto.getPuissance());
        autoCylindre.setText(currentAuto.getCylindre());
        autoTransmission.setText(currentAuto.getTransmission());
        autoPmec.setText(currentAuto.getPmec());
        selectTab(3);
    }

    @FXML
    void filtrer_automobile(KeyEvent event) {
        FilteredList<Automobile> items = new FilteredList<>(FXCollections.observableArrayList(allAutomobiles));
        items.setPredicate(item ->{
            String lower = filtre_automobile.getText().toLowerCase();
            String upper = filtre_automobile.getText().toUpperCase();
            if(item.getImmatriculation().contains(lower))
                return true;
            else
                return item.getImmatriculation().contains(upper);
        });
        SortedList<Automobile> sorted = new SortedList<>(items);
        automobileTable.setItems(sorted);
    }

    @FXML
    void filtrer_client(KeyEvent event) {
        FilteredList<Client> items = new FilteredList<>(FXCollections.observableArrayList(allClients));
        items.setPredicate(item ->{
            String lower = client.getEditor().getText().toLowerCase();
            String upper = client.getEditor().getText().toUpperCase();
            if(item.getRaisonSociale().contains(lower))
                return true;
            else
                return item.getRaisonSociale().contains(upper);
        });
        SortedList<Client> sorted = new SortedList<>(items);
        client.setItems(sorted);
    }

    @FXML
    void print(ActionEvent event) {
        AlertConfirm.getInstance().setTitle("Confirmation");
        AlertConfirm.getInstance().setHeaderText("Impression");
        AlertConfirm.getInstance().setContentText("Vous allez imprimer la liste des automobiles. Voulez-vous continuer?");
        AlertConfirm.getInstance().showAndWait();
    }
    
    @FXML
    void quit(ActionEvent event) {
        emptyField();
        selectTab(2);
    }
    
    @FXML
    void reset(ActionEvent event) {
        emptyField();
    }

    @FXML
    void select_automobile(MouseEvent event) {
        currentAuto = automobileTable.getSelectionModel().getSelectedItem();
    }
    
    @FXML
    void submit(ActionEvent event) {
        Client client = Manage.getInstance().afficherClient(clientID);

        Automobile automobile = new Automobile();
        automobile.setImmatriculation(newImmatriculation.getText().trim());
        automobile.setChassis(newChassis.getText().trim());
        automobile.setMarque(newMarque.getText().trim());
        automobile.setModele(newModele.getText().trim());
        automobile.setTypeCompteur(newTypeCompteur.getSelectionModel().getSelectedItem().name());
        automobile.setCompteur(Integer.parseInt(newCompteur.getText().trim()));
        automobile.setPuissance(newPuissance.getText().trim());
        automobile.setPmec(newPmec.getText().trim());
        automobile.setCylindre(newCylindre.getText().trim());
        automobile.setTransmission(newTransmission.getText().trim());

        Dossier dossier = new Dossier();
        dossier.setDateOuverture(new Date());
        dossier.setDateMaj(new Date());
        dossier.setAutomobile(automobile);
        dossier.setReference("GMCPLUS/"+automobile.getImmatriculation()+"/"+client.getRaisonSociale());

        client.addAutomobile(automobile);
        client.addDossier(dossier);

        try {
            Manage.getInstance().enregistrerDossier(dossier);
            AlertInfo.getInstance().setTitle("Info");
            AlertInfo.getInstance().setHeaderText("Enregistrement automobile");
            AlertInfo.getInstance().setContentText("Automobile enregistré avec succès");
            AlertInfo.getInstance().show();
            selectTab(2);
            readAll();
            emptyField();
        } catch (Exception e) {
            AlertError.getInstance().setTitle("Erreur");
            AlertError.getInstance().setHeaderText("Enregistrement automobile");
            AlertError.getInstance().setContentText("Echec lors de l'enregistrement de l'Automobile.\n"+e.getMessage());
            AlertError.getInstance().showAndWait();
            e.printStackTrace();
        }
    }
    
    public void selectTab(int i) {
        automobileTab.getSelectionModel().select(getTab(i));
    }
    
    public Tab getTab(int i) {
        Tab tab = null;
        switch (i) {
            case 1: {
                tab = tabNew;
                break;
            }
            case 2: {
                tab = tabList;
                break;
            }
            case 3: {
                tab = tabDetail;
                break;
            }
        }
        return tab;
    }
    
    void emptyField() {
        client.getEditor().setText("");
        newImmatriculation.setText("");
        newChassis.setText("");
        newMarque.setText("");
        newModele.setText("");
        newCompteur.setText("");
        newReference.setText("");
        newPmec.setText("");
        newPuissance.setText("");
        newCylindre.setText("");
        newTransmission.setText("");

        autoImmatriculation.setText("");
        autoChassis.setText("");
        autoMarque.setText("");
        autoModele.setText("");
        autoCompteur.setText("");
        autoReference.setText("");
        autoPuissance.setText("");
        autoCylindre.setText("");
        autoTransmission.setText("");
        autoPmec.setText("");
    }
    
    void readAll() {
        automobileTable.getItems().clear();
        AutomobileRepository automobileRepository = new AutomobileRepository();
        this.allAutomobiles = automobileRepository.findAll();
        automobileTable.setItems(FXCollections.observableArrayList(FXCollections.observableArrayList(allAutomobiles)));
        nombre_auto.setText(String.valueOf(allAutomobiles.size()));
    }

    void readClient(){
        ClientRepository clientRepository = new ClientRepository();
        this.allClients = clientRepository.findAll();
        client.setItems(FXCollections.observableArrayList(allClients));
    }
    
    public void initialize(URL location, ResourceBundle resources) {

        client.setConverter(new StringConverter<Client>() {
            @Override
            public String toString(Client object) {
                if(object==null) return null;
                return object.toString();
            }

            @Override
            public Client fromString(String string) {
                ClientRepository clientRepository = new ClientRepository();
                String auto = client.getEditor().getText().trim();
                return clientRepository.find(auto);
            }
        });

        readClient();

        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        immatriculation.setCellValueFactory(new PropertyValueFactory<>("immatriculation"));
        marque.setCellValueFactory(new PropertyValueFactory<>("marque"));
        modele.setCellValueFactory(new PropertyValueFactory<>("modele"));
        chassis.setCellValueFactory(new PropertyValueFactory<>("chassis"));
        typeCompteur.setCellValueFactory(new PropertyValueFactory<>("typeCompteur"));
        compteur.setCellValueFactory(new PropertyValueFactory<>("compteur"));
        newTypeCompteur.getItems().setAll(Automobile.Compteur.values());
        autoTypeCompteur.getItems().setAll(Automobile.Compteur.values());
        selectTab(1);

        automobileTab.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Tab>() {
            public void changed(ObservableValue<? extends Tab> observableValue, Tab tab, Tab t1) {
                if (tab.getTabPane().getSelectionModel().getSelectedIndex() == 1) {
                    readAll();
                }
            }
        });

        client.addEventHandler(ActionEvent.ACTION, event -> {
            if (client.getSelectionModel().getSelectedIndex() != -1) {
                clientID = client.getSelectionModel().getSelectedItem().getId();
                currentClient = client.getValue();
            }
        });
    }
}
